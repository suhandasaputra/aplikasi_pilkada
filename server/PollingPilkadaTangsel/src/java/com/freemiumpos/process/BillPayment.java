package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Messagein;
import com.freemium.entity.Tempmsg;
import com.freemium.function.SendHttpProcess;
import com.freemium.function.CheckSumFunction;
import com.freemium.singleton.ResponseCodeSingleton;
import com.freemium.singleton.SocketConnectionSingleton;
import com.agentmanagement.database.AgentDBProc;
import com.agentmanagement.entity.LimitProcResponse;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.ppobcore.switching.BillPaymentCreditSwitch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class BillPayment {

    int n = 1;
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHss").format(Calendar.getInstance().getTime());
    private DatabaseProcess dp = new DatabaseProcess();
    Messagein msgin = new Messagein();
    private Tempmsg tempmsg = new Tempmsg();
    BillPaymentCreditSwitch billPaymentSwitch = new BillPaymentCreditSwitch();
    private static Logger log = Logger.getLogger(BillPayment.class);
    SendHttpProcess http = new SendHttpProcess();
    HashMap response = null;

    public HashMap process(HashMap input) throws Exception {
        msgin.setInput(input);
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());

//        msgin = dp.getSendToConn(msgin.getInput().get(FieldParameter.product_code).toString(), msgin);
        msgin = dp.getSendToConnPostpaid(msgin.getInput().get(FieldParameter.product_code).toString(), msgin, input.get(FieldParameter.refer).toString());
        if (msgin.getSendto() != null) {
                            msgin.getInput().put(FieldParameter.product_name, dp.getProductName(msgin.getInput().get(FieldParameter.product_code).toString()));

            if (!CheckSumFunction.verifyCheckSum((msgin.getInput().get(RuleNameParameter.amount).toString()), (msgin.getInput().get(FieldParameter.check_sum).toString()))) {
                msgin.getInput().put(RuleNameParameter.resp_code, "0052");
                msgin.getInput().put(RuleNameParameter.resp_desc, "Error CheckSum");
                dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), "Error CheckSum", "0052", "0052");
            } else {
                AgentDBProc dbAgent = new AgentDBProc();
                /////// multiple fee mkm
                if (input.containsKey(FieldParameter.additional_data)) {
                    n = input.get(FieldParameter.additional_data).toString().split("\\|").length / 2;
                }
                msgin.setPpob_profit(msgin.getPpob_profit() * n);
                msgin.setFeejual(msgin.getFeejual() * n);
                msgin.setFeebeli(msgin.getFeebeli() * n);
                msgin.setRef_profit(msgin.getRef_profit() * n);

                ////////////////////////
                int amount = Integer.parseInt(msgin.getInput().get(RuleNameParameter.amount).toString()) + (msgin.getFeejual());
                LimitProcResponse lprbiller = dbAgent.checkBalanceUs(msgin, String.valueOf(amount));
                if (lprbiller.getStatus().equals("B00")) {
                    tempmsg.setCurr_biller_bal(lprbiller.getCurr_biller_bal());
                    tempmsg.setPrev_biller_bal(lprbiller.getPrev_biller_bal());
                    LimitProcResponse lpr = dbAgent.checkLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                            String.valueOf(amount));
                    String responseWeb = "";
                    if (lpr.getStatus().equals("L00")) {

                        String reqSendMessage = billPaymentSwitch.switchPaymentParamGenerate(msgin.getInput());

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
                        tempmsg.setCurr_bal(lpr.getCurr());
                        tempmsg.setPrev_bal(lpr.getPrev());
                        if (msgin.getInput().containsKey(FieldParameter.no_ba)) {
                            msgin.setNo_ba(msgin.getInput().get(FieldParameter.no_ba).toString());
                        }

                        msgin.setUsr_cashback(Integer.valueOf(dbAgent.getBonusPoin(tempmsg.getProductcode(), msgin.getRefer())) * n);
                        dp.saveMessageToTempmsgPayment(msgin, tempmsg);
//                        log.info("\n" + msgin.getMsgid() + " : (Biller) Message outgoing : " + urlPath + "req=" + reqSendMessage + "\n");
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
                        } else if (responseWeb.equals("error")) {
                            msgin.getInput().put(RuleNameParameter.resp_code, "0020");
                            msgin.getInput().put(RuleNameParameter.resp_desc, ResponseCodeSingleton.getInstance().getResponseCodeDesc("0020"));
                            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, "0020", "0020");
                        } else {
//                            SendMailNotification sendmail = new SendMailNotification();

                            msgin.setMessage(responseWeb);
                            response = JsonProcess.decodeJson(msgin.getMessage());
//                            log.info("check prepaid : " + response);
                            switch (response.get(FieldParameter.resp_code).toString()) {
                                case "0000":
//                                    sendmail.sendNotifikasi(msgin.getInput().get(FieldParameter.userlogin).toString());
//                                    log.info("check prepaid : success");
                                    billPaymentSwitch.switchPaymentResponseGenerate(response, msgin);
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
                                    LimitProcResponse lprfail = dbAgent.reversalLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                            String.valueOf(amount));
                                    break;
                            }
//                            SendMailNotification sendmail = new SendMailNotification();
//                            sendmail.sendNotifikasi(input);
                            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, response.get(FieldParameter.resp_code).toString(), response.get(FieldParameter.resp_code).toString());
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
                    if (lprbiller.getStatus().equals("L05")) {
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
            }
        } else {
            msgin.getInput().put(FieldParameter.resp_code, "0040");
            msgin.getInput().put(FieldParameter.resp_desc, "maaf, produk sedang tidak tersedia, ulangi beberapa saat lagi");
            response = msgin.getInput();
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
