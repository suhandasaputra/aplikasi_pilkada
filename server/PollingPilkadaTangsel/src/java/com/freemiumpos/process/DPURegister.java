/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Messagein;
import com.freemium.entity.Tempmsg;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.singleton.ResponseCodeSingleton;
import com.freemium.singleton.SocketConnectionSingleton;
import com.ppobcore.switching.RegisterSwitch;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class DPURegister {

    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHss").format(Calendar.getInstance().getTime());
    private DatabaseProcess dp = new DatabaseProcess();
    Messagein msgin = new Messagein();
    private Tempmsg tempmsg = new Tempmsg();
    RegisterSwitch registerSwitch = new RegisterSwitch();
    private static Logger log = Logger.getLogger(BillPayment.class);
    com.freemium.function.SendHttpProcess http = new com.freemium.function.SendHttpProcess();
    HashMap response = null;

    public HashMap process(HashMap input) throws Exception {
        msgin.setInput(input);
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());
        msgin = dp.getSendToConn(msgin.getInput().get(FieldParameter.product_code).toString(), msgin);

        String responseWeb = "";

        String urlPath = SocketConnectionSingleton.getInstance().getWebConn().get(msgin.getSendto()).toString();
        String reqSendMessage = registerSwitch.switchPaymentParamGenerate(msgin.getInput());
        tempmsg.setBankcode(msgin.getSendto());
        tempmsg.setFromaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setToaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setMsgid(msgin.getMsgid());
        tempmsg.setAmount((msgin.getInput().get(RuleNameParameter.amount) != null) ? msgin.getInput().get(RuleNameParameter.amount).toString() : "0");
        tempmsg.setNoref(msgin.getInput().get(RuleNameParameter.rrn).toString());
        tempmsg.setProccode(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setTransactionid(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setProductcode(msgin.getInput().get(RuleNameParameter.product_code).toString());
        tempmsg.setCustNo(msgin.getInput().get(RuleNameParameter.customer_id).toString());
        tempmsg.setTrxidbackend("");
        tempmsg.setFromSocket(msgin.getFromsocket());
        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setReqbiller(reqSendMessage);
        tempmsg.setCurr_bal(0);
        tempmsg.setPrev_bal(0);
        dp.saveMessageToTempmsgPayment(msgin, tempmsg);

        log.info("\n" + msgin.getMsgid() + " : (Biller) Message outgoing : " + urlPath + "req=" + reqSendMessage + "\n");
        responseWeb = http.sendHttpRequest(urlPath, "req=" + reqSendMessage);
        log.info("\n" + msgin.getMsgid() + " : (Biller) Message incoming : " + responseWeb + "\n");

        if (responseWeb.equals("timeout")) {
            msgin.getInput().put(RuleNameParameter.resp_code, "0068");
            msgin.getInput().put(RuleNameParameter.resp_desc, ResponseCodeSingleton.getInstance().getResponseCodeDesc("0068"));
            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, "0068", "0068");
        } else if (responseWeb.equals("error")) {
            msgin.getInput().put(RuleNameParameter.resp_code, "0020");
            msgin.getInput().put(RuleNameParameter.resp_desc, ResponseCodeSingleton.getInstance().getResponseCodeDesc("0020"));
            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, "0020", "0020");
        } else {
            msgin.setMessage(responseWeb);
            response = JsonProcess.decodeJson(msgin.getMessage());
            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, response.get(FieldParameter.resp_code).toString(), response.get(FieldParameter.resp_code).toString());
        }

        return response;
    }
}
