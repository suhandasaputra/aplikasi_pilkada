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
import com.ppobcore.switching.RedeemSwitch;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class RedeemPoin {

    private static Logger log = Logger.getLogger(RedeemPoin.class);
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHss").format(Calendar.getInstance().getTime());
    private Tempmsg tempmsg = new Tempmsg();
    Messagein msgin = new Messagein();
    private DatabaseProcess dp = new DatabaseProcess();
    RedeemSwitch redeemSwitch = new RedeemSwitch();
    SendHttpProcess http = new SendHttpProcess();
    HashMap response = null;

    public HashMap process(HashMap input) throws Exception {

        msgin.setInput(input);
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());
        //Inquiry Product redeem 
        HashMap redeemItem = dp.getRedeemItemDetail(input);
        if (redeemItem.get(RuleNameParameter.resp_code).equals("0000")) {
            if (redeemItem.get(RuleNameParameter.otherparam).equals("INTERNAL")) {
                msgin = dp.getSendToConn(redeemItem.get(FieldParameter.product_code).toString(), msgin);
            } else {
            }
            AgentDBProc dbAgent = new AgentDBProc();
            LimitProcResponse lpr = dbAgent.checkPoin(msgin.getInput().get(FieldParameter.userlogin).toString(),
                    redeemItem.get(RuleNameParameter.amount).toString());
            String responseWeb = "";
            if (lpr.getStatus().equals("L00")) {
                String urlPath = SocketConnectionSingleton.getInstance().getWebConn().get(msgin.getSendto()).toString();
                String reqSendMessage = redeemSwitch.switchPrepaidReloadParamGenerate(msgin.getInput(), redeemItem);
                tempmsg.setBankcode(msgin.getSendto());
                tempmsg.setFromaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
                tempmsg.setToaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
                tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
                tempmsg.setMsgid(msgin.getMsgid());
                tempmsg.setAmount(redeemItem.get(RuleNameParameter.amount).toString());
                tempmsg.setNoref(msgin.getInput().get(RuleNameParameter.rrn).toString());
                tempmsg.setProccode(msgin.getInput().get(FieldParameter.procCode).toString());
                tempmsg.setTransactionid(msgin.getInput().get(FieldParameter.procCode).toString());
                tempmsg.setProductcode(redeemItem.get(RuleNameParameter.product_code).toString());
                tempmsg.setCustNo(msgin.getInput().get(RuleNameParameter.cust_detail).toString());
                tempmsg.setTrxidbackend("");
                tempmsg.setFromSocket(msgin.getFromsocket());
                tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
                tempmsg.setReqbiller(reqSendMessage);
                tempmsg.setCurr_bal(lpr.getCurr2());
                tempmsg.setPrev_bal(lpr.getPrev2());
                dp.saveMessageToTempmsgRedeemPoin(msgin, tempmsg);
                log.info("\n" + msgin.getMsgid() + " : (Biller) Message outgoing : " + urlPath + "req=" + reqSendMessage + "\n");
                responseWeb = http.sendHttpRequest(urlPath, "req=" + reqSendMessage);
                log.info("\n" + msgin.getMsgid() + " : (Biller) Message incoming : " + responseWeb + "\n");
                if (responseWeb.equals("timeout")) {
                    msgin.getInput().put(RuleNameParameter.resp_code, "0068");
                    msgin.getInput().put(RuleNameParameter.resp_desc, ResponseCodeSingleton.getInstance().getResponseCodeDesc("0068"));
                    dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, "0068", "0068");
                    response = msgin.getInput();
                } else if (responseWeb.equals("error")) {
                    msgin.getInput().put(RuleNameParameter.resp_code, "0020");
                    msgin.getInput().put(RuleNameParameter.resp_desc, ResponseCodeSingleton.getInstance().getResponseCodeDesc("0020"));
                    LimitProcResponse lprtimeout = dbAgent.reversalLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                            redeemItem.get(RuleNameParameter.amount).toString());
                    dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, "0020", "0020");
                    response = msgin.getInput();
                } else {
                    msgin.setMessage(responseWeb);
                    response = JsonProcess.decodeJson(msgin.getMessage());
                    if (response.get(FieldParameter.resp_code) != null) {
                        switch (response.get(FieldParameter.resp_code).toString()) {
                            case "0000":
                                redeemSwitch.switchPaymentResponseGenerate(response, msgin, redeemItem);//set secret code
                                dbAgent.insertUseBonusPoin(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                        response.get(RuleNameParameter.rrn).toString(),
                                        response.get(RuleNameParameter.product_code).toString(),
                                        redeemItem.get(RuleNameParameter.amount).toString(),
                                        String.valueOf(lpr.getPrev2()),
                                        String.valueOf(lpr.getCurr2())
                                );
                                break;
                            case "0068":
                                dbAgent.insertUseBonusPoin(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                        response.get(RuleNameParameter.rrn).toString(),
                                        response.get(RuleNameParameter.product_code).toString(),
                                        redeemItem.get(RuleNameParameter.amount).toString(),
                                        String.valueOf(lpr.getPrev2()),
                                        String.valueOf(lpr.getCurr2()));
                                break;
                            default:
                                LimitProcResponse lprfailed = dbAgent.reversalLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                        String.valueOf(msgin.getHargajual()));
                                break;
                        }
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

            dbAgent = null;
            lpr = null;
        } else {
            msgin.getInput().put(RuleNameParameter.resp_code, "0080");
            msgin.getInput().put(RuleNameParameter.resp_desc, ResponseCodeSingleton.getInstance().getResponseCodeDesc("0080"));
//            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), responseWeb, "0068", "0068");
            response = msgin.getInput();
        }
        return response;
    }

}
