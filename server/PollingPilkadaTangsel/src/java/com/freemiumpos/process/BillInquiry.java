package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Messagein;
import com.freemium.entity.ResponseCodeEntity;
import com.freemium.entity.Tempmsg;
import com.freemium.function.SendHttpProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.ProcessingCode;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.StaticParameter;
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
public class BillInquiry {

    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHss").format(Calendar.getInstance().getTime());
    private Tempmsg tempmsg = new Tempmsg();
    private DatabaseProcess dp = new DatabaseProcess();
    Messagein msgin = new Messagein();
    BillInquirySwitch billInquirySwitch = new BillInquirySwitch();
    private static Logger log = Logger.getLogger(BillInquiry.class);
    SendHttpProcess http = new SendHttpProcess();
    HashMap response = null;

    public HashMap process(HashMap input) throws Exception {
        int n = 1;
        msgin.setInput(input);
        msgin.getInput().put(FieldParameter.procCode, ProcessingCode.billInquiryProccode);
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());
//        msgin = dp.getSendToConn(msgin.getInput().get(FieldParameter.product_code).toString(), msgin);
        msgin = dp.getSendToConnPostpaid(msgin.getInput().get(FieldParameter.product_code).toString(), msgin, input.get(FieldParameter.refer).toString());

        if (msgin.getSendto() != null) {

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
//            if (msgin.getInput().containsKey(FieldParameter.no_ba)) {
//                msgin.setNo_ba(msgin.getInput().get(FieldParameter.no_ba).toString());
//            }
            dp.saveMessageToTempmsgInquiry(msgin, tempmsg);

//            log.info("\n" + msgin.getMsgid() + " : (Biller) Message outgoing : " + urlPath + "req=" + reqSendMessage + "\n");
            HashMap maskingReqMessage = JsonProcess.decodeJson(reqSendMessage);
            maskingReqMessage.remove("agent_pass");
            maskingReqMessage.remove("agent_id");
            log.info("\n" + msgin.getMsgid() + " : (Biller) Message outgoing to MiosCore : " + JsonProcess.generateJson(maskingReqMessage) + "\n");
            String responseWeb = http.sendHttpRequest(urlPath, "req=" + reqSendMessage);
            HashMap maskingResMessage = JsonProcess.decodeJson(responseWeb);
            maskingResMessage.remove("agent_pass");
            maskingResMessage.remove("agent_id");
//                    log.info("\n" + msgin.getMsgid() + " : (Biller) Message incoming : " + responseWeb + "\n");
            log.info("\n" + msgin.getMsgid() + " : (Biller) Message incoming from MiosCore : " + maskingResMessage + "\n");
            System.out.println("(Biller) Message incoming from MiosCore : " + maskingResMessage + "\n");
            msgin.setMessage(responseWeb);
            response = JsonProcess.decodeJson(msgin.getMessage());

            ResponseCodeEntity respcode = (ResponseCodeEntity) ResponseCodeSingleton.getInstance().getResponseCode().get(response.get(FieldParameter.resp_code));
            if (response.get(FieldParameter.resp_code).toString().equals("0000")) {
                if (response.containsKey(FieldParameter.data)) {
                    HashMap Data = (HashMap) response.get(FieldParameter.data);
                    if (Data.containsKey(RuleNameParameter.TagihanBelumLunas)) {
                        n = Integer.valueOf(Data.get(RuleNameParameter.TagihanBelumLunas).toString());
                    }
                }
                response.put(StaticParameter.biayaadmin, String.valueOf(msgin.getFeejual() * n));
                response.put(RuleNameParameter.Total, String.valueOf(Integer.valueOf(response.get(FieldParameter.amount).toString()) + Integer.valueOf(response.get(StaticParameter.biayaadmin).toString())));
                response.put(RuleNameParameter.resp_code, respcode.getCode());
                response.put(RuleNameParameter.resp_desc, respcode.getDesc());
                dp.updateStatusReplyTempmsgBillInquiry(msgin.getMsgid(), responseWeb, response.get(FieldParameter.resp_code).toString(), response.get(FieldParameter.resp_code).toString());
            } else {
                response.put(RuleNameParameter.resp_code, respcode.getCode());
                response.put(RuleNameParameter.resp_desc, respcode.getDesc());
                dp.updateStatusReplyTempmsgBillInquiry(msgin.getMsgid(), responseWeb, response.get(FieldParameter.resp_code).toString(), response.get(FieldParameter.resp_code).toString());
            }
        } else {
            msgin.getInput().put(FieldParameter.resp_code, "0040");
            msgin.getInput().put(FieldParameter.resp_desc, "maaf, produk sedang tidak tersedia, ulangi beberapa saat lagi");

            response = msgin.getInput();
        }
        return response;

    }

}
