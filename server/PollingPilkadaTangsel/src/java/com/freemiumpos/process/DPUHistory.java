package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Messagein;
import com.freemium.entity.ResponseCodeEntity;
import com.freemium.entity.Tempmsg;
import com.freemium.function.SendHttpProcess;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.ProcessingCode;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.singleton.ResponseCodeSingleton;
import com.freemium.singleton.SocketConnectionSingleton;
import com.ppobcore.switching.BillInquirySwitch;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class DPUHistory {

    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHss").format(Calendar.getInstance().getTime());
    private Tempmsg tempmsg = new Tempmsg();
    private DatabaseProcess dp = new DatabaseProcess();
    Messagein msgin = new Messagein();
    BillInquirySwitch billInquirySwitch = new BillInquirySwitch();
    private static Logger log = Logger.getLogger(DPUHistory.class);
    SendHttpProcess http = new SendHttpProcess();
    HashMap response = null;
    
    public HashMap process(HashMap input) throws Exception {
        msgin.setInput(input);
        msgin.getInput().put(FieldParameter.procCode, ProcessingCode.billInquiryProccode);
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());
        msgin = dp.getSendToConn(msgin.getInput().get(FieldParameter.product_code).toString(), msgin);
        String urlPath = SocketConnectionSingleton.getInstance().getWebConn().get(msgin.getSendto()).toString();
        String reqSendMessage = billInquirySwitch.switchInquiryParamGenerate(msgin.getInput());
        
        tempmsg.setBankcode(msgin.getSendto());
        tempmsg.setFromaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setToaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setMsgid(msgin.getMsgid());
        tempmsg.setNoref(msgin.getInput().get(RuleNameParameter.rrn).toString());
        tempmsg.setProccode(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setTransactionid(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setProductcode(msgin.getInput().get(RuleNameParameter.product_code).toString());
        tempmsg.setCustNo(msgin.getInput().get(RuleNameParameter.customer_id).toString());
        tempmsg.setTrxidbackend("");
        tempmsg.setFromSocket(msgin.getFromsocket());
        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setReqbiller(reqSendMessage);
//        dp.saveMessageToTempmsgInquiry(msgin, tempmsg);
        
        log.info("\n" + msgin.getMsgid() + " : (Biller) Message outgoing : " + urlPath + "req=" + reqSendMessage + "\n");
        String responseWeb = http.sendHttpRequest(urlPath, "req=" + reqSendMessage);
        log.info("\n" + msgin.getMsgid() + " : (Biller) Message incoming : " + responseWeb + "\n");
        
        msgin.setMessage(responseWeb);
        response = JsonProcess.decodeJson(msgin.getMessage());
        
        ResponseCodeEntity respcode = (ResponseCodeEntity) ResponseCodeSingleton.getInstance().getResponseCode().get(response.get(FieldParameter.resp_code));
        response.put(RuleNameParameter.resp_code, respcode.getCode());
        response.put(RuleNameParameter.resp_desc, respcode.getDesc());
//        dp.updateStatusReplyTempmsgBillInquiry(msgin.getMsgid(), responseWeb, response.get(FieldParameter.resp_code).toString(), response.get(FieldParameter.resp_code).toString());
        return response;
    }
}
