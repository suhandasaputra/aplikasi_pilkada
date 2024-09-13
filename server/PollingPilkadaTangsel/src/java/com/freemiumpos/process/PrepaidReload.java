/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Messagein;
import com.freemium.entity.Tempmsg;
import com.freemium.function.SendHttpProcess;
import com.freemium.singleton.ResponseCodeSingleton;
import com.freemium.singleton.SocketConnectionSingleton;
import com.agentmanagement.database.AgentDBProc;
import com.agentmanagement.entity.LimitProcResponse;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.ppobcore.switching.PrepaidReloadSwitch;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class PrepaidReload {

    private static Logger log = Logger.getLogger(PrepaidReload.class);
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHss").format(Calendar.getInstance().getTime());
    private Tempmsg tempmsg = new Tempmsg();
    Messagein msgin = new Messagein();
    private DatabaseProcess dp = new DatabaseProcess();
    PrepaidReloadSwitch prepaidReloadSwitch = new PrepaidReloadSwitch();
    SendHttpProcess http = new SendHttpProcess();
    HashMap response = null;

    public HashMap process(HashMap input) throws Exception {
        msgin.setInput(input);
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());
//        if (dp.checkSpamTRX(msgin)) {
        if (dp.checkSpamTRX(msgin)) {
            msgin.getInput().put(RuleNameParameter.resp_code, "0042");
            msgin.getInput().put(RuleNameParameter.resp_desc, "maaf, untuk transaksi selanjutnya mohon tunggu beberapa saat lagi.");//biller inactive
            response = msgin.getInput();
        } else {

//        msgin = dp.getSendToConn(msgin.getInput().get(FieldParameter.product_code).toString(), msgin);
            msgin = dp.getSendToConnPrepaid(msgin.getInput().get(FieldParameter.product_code).toString(), msgin, input.get(FieldParameter.refer).toString());
            if (msgin.getSendto() != null) {
                msgin.getInput().put(FieldParameter.product_name, dp.getProductName(msgin.getInput().get(FieldParameter.product_code).toString()));

                AgentDBProc dbAgent = new AgentDBProc();
                LimitProcResponse lprbiller = dbAgent.checkBalanceUs(msgin, String.valueOf(msgin.getHargajual()));

                if (lprbiller.getStatus().equals("B00")) {
                    tempmsg.setCurr_biller_bal(lprbiller.getCurr_biller_bal());
                    tempmsg.setPrev_biller_bal(lprbiller.getPrev_biller_bal());
                    LimitProcResponse lpr = dbAgent.checkLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                            String.valueOf(msgin.getHargajual()));
                    String responseWeb = "";
                    if (lpr.getStatus().equals("L00")) {
                        String reqSendMessage = prepaidReloadSwitch.switchPrepaidReloadParamGenerate(msgin.getInput());
                        tempmsg.setBankcode(msgin.getSendto());
                        tempmsg.setFromaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
                        tempmsg.setToaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
                        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
                        tempmsg.setMsgid(msgin.getMsgid());
                        tempmsg.setAmount((String.valueOf(msgin.getHargajual()) != null) ? String.valueOf(msgin.getHargajual()) : "0");
                        tempmsg.setNoref(msgin.getInput().get(RuleNameParameter.rrn).toString());
                        tempmsg.setProccode(msgin.getInput().get(FieldParameter.procCode).toString());
                        tempmsg.setTransactionid(msgin.getInput().get(FieldParameter.procCode).toString());
                        tempmsg.setProductcode(msgin.getInput().get(RuleNameParameter.product_code).toString());
                        tempmsg.setCustNo(msgin.getInput().get(RuleNameParameter.customer_id).toString());
                        tempmsg.setTrxidbackend("");
                        tempmsg.setFromSocket(msgin.getFromsocket());
                        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
                        tempmsg.setReqbiller(reqSendMessage);
                        tempmsg.setCurr_bal(lpr.getCurr());
                        tempmsg.setPrev_bal(lpr.getPrev());
                        if (msgin.getInput().containsKey(FieldParameter.no_ba)) {
                            msgin.setNo_ba(msgin.getInput().get(FieldParameter.no_ba).toString());
                        }
                        tempmsg.setPrev_bal(lpr.getPrev());
                        msgin.setUsr_cashback(Integer.valueOf(dbAgent.getBonusPoin(tempmsg.getProductcode(), msgin.getRefer())));
                        dp.saveMessageToTempmsgPrepaidReload(msgin, tempmsg);
//                    log.info("\n" + msgin.getMsgid() + " : (Biller) Message outgoing : " + urlPath + "req=" + reqSendMessage + "\n");
                        HashMap maskingReqMessage = JsonProcess.decodeJson(reqSendMessage);
                        maskingReqMessage.remove("agent_pass");
                        maskingReqMessage.remove("agent_id");
                        String urlPath = SocketConnectionSingleton.getInstance().getWebConn().get(msgin.getSendto()).toString();
                        log.info("\n" + msgin.getMsgid() + " : (Biller) Message outgoing to MiosCore : " + JsonProcess.generateJson(maskingReqMessage) + "\n");
                        responseWeb = http.sendHttpRequest(urlPath, "req=" + reqSendMessage);
                        HashMap maskingResMessage = JsonProcess.decodeJson(responseWeb);
                        maskingResMessage.remove("agent_pass");
                        maskingResMessage.remove("agent_id");
//                    log.info("\n" + msgin.getMsgid() + " : (Biller) Message incoming : " + responseWeb + "\n");
                        log.info("\n" + msgin.getMsgid() + " : (Biller) Message incoming from MiosCore : " + maskingResMessage + "\n");
                        if (responseWeb.equals("timeout")) {
                            msgin.getInput().put(RuleNameParameter.resp_code, "0068");
                            msgin.getInput().put(RuleNameParameter.resp_desc, ResponseCodeSingleton.getInstance().getResponseCodeDesc("0068"));
                            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, "0068", "0068");
                            response = msgin.getInput();
                        } else if (responseWeb.equals("error")) {
                            msgin.getInput().put(RuleNameParameter.resp_code, "0020");
                            msgin.getInput().put(RuleNameParameter.resp_desc, ResponseCodeSingleton.getInstance().getResponseCodeDesc("0020"));

                            LimitProcResponse lprtimeout = dbAgent.reversalLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                    String.valueOf(msgin.getHargajual()));
                            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, "0020", "0020");
                            response = msgin.getInput();
                        } else {
                            msgin.setMessage(responseWeb);
                            response = JsonProcess.decodeJson(msgin.getMessage());
                            if (response.get(FieldParameter.resp_code) != null) {
//                            SendMailNotification sendmail = new SendMailNotification();

                                switch (response.get(FieldParameter.resp_code).toString()) {
                                    case "0000":
//                                    sendmail.sendNotifikasi(msgin.getInput().get(FieldParameter.userlogin).toString());
                                        prepaidReloadSwitch.switchPaymentResponseGenerate(response, msgin);
                                        dbAgent.receivePoin(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                                String.valueOf(msgin.getUsr_cashback()), response);
                                        if (msgin.getRefer() != null && !msgin.getRefer().equals("0")) {
                                            dbAgent.receivePoin(msgin.getRefer(),
                                                    String.valueOf(msgin.getRef_profit()), response);
                                            response.put(FieldParameter.price, msgin.getHargajual());
                                        }
                                        break;
                                    case "0068":
//                                    sendmail.sendNotifikasi(msgin.getInput().get(FieldParameter.userlogin).toString());
                                        dbAgent.receivePoin(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                                String.valueOf(msgin.getUsr_cashback()), response);
                                        if (msgin.getRefer() != null && !msgin.getRefer().equals("0")) {
                                            dbAgent.receivePoin(msgin.getRefer(),
                                                    String.valueOf(msgin.getRef_profit()), response);
                                            response.put(FieldParameter.price, msgin.getHargajual());
                                        }
                                        break;
                                    default:
                                        LimitProcResponse lprfailed = dbAgent.reversalLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                                String.valueOf(msgin.getHargajual()));
                                        break;
                                }
//                            SendMailNotification sendmail = new SendMailNotification();
//                            sendmail.sendNotifikasi(input);
                                dp.updateStatusReplyTempmsgPrepaidReload(msgin.getMsgid(), responseWeb, response.get(FieldParameter.resp_code).toString(), response.get(FieldParameter.resp_code).toString());
                            } else {
                                response.put(FieldParameter.resp_code, "0001");
                                response.put(FieldParameter.resp_desc, "Transaksi gagal");
                                LimitProcResponse lprfailed = dbAgent.reversalLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                        String.valueOf(msgin.getHargajual()));
                                dp.updateStatusReplyTempmsgPrepaidReload(msgin.getMsgid(), responseWeb, "0001", "0001");
                            }
                        }
                    } else {
                        if (lpr.getStatus().equals("L05")) {
                            msgin.getInput().put(FieldParameter.resp_code, "0051");
                            response = msgin.getInput();
                        } else {
                            msgin.getInput().put(FieldParameter.resp_code, "0061");
                            response = msgin.getInput();
                        }
                        msgin.getInput().put(FieldParameter.resp_desc, lpr.getStatusDesc());
                    }
                    lpr = null;

                } else {
                    if (lprbiller.getStatus().equals("B05")) {
                        msgin.getInput().put(FieldParameter.resp_code, "0051");
                        response = msgin.getInput();
                    } else {
                        msgin.getInput().put(FieldParameter.resp_code, "0061");
                        response = msgin.getInput();
                    }
                    msgin.getInput().put(FieldParameter.resp_desc, lprbiller.getStatusDesc());
                }
                lprbiller = null;

                dbAgent = null;
            } else {
                msgin.getInput().put(FieldParameter.resp_code, "0040");
//            msgin.getInput().put(FieldParameter.resp_desc, "Unregister Product");
                msgin.getInput().put(FieldParameter.resp_desc, "maaf, produk sedang tidak tersedia, ulangi beberapa saat lagi");
                response = msgin.getInput();
            }

        }
        if (msgin.getInput().containsKey(FieldParameter.product_name)) {
            response.put(FieldParameter.product_name, msgin.getInput().get(FieldParameter.product_name).toString());
        }
        if (msgin.getInput().containsKey(FieldParameter.detail_tagihan)) {
            response.put(FieldParameter.detail_tagihan, msgin.getInput().get(FieldParameter.detail_tagihan).toString());
        }
        return response;

    }
}
