/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Messagein;
import com.freemium.entity.Tempmsg;
import com.agentmanagement.database.AgentDBProc;
import com.agentmanagement.entity.LimitProcResponse;
import com.freemium.function.CheckSumFunction;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class Seller {

    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    private DatabaseProcess dp = new DatabaseProcess();
    Messagein msgin = new Messagein();
    HashMap response = null;
    private Tempmsg tempmsg = new Tempmsg();
    private static Logger log = Logger.getLogger(Seller.class);

    public HashMap process(HashMap input) throws Exception {
        System.out.println("masuk seller");
        msgin.setInput(input);
        log.info(msgin.getInput());
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());
        tempmsg.setNoref(msgin.getInput().get(FieldParameter.rrn).toString());
        tempmsg.setProccode(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setTransactionid(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setProductcode(msgin.getInput().get(RuleNameParameter.product_code).toString());
        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setFromaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setToaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        System.out.println("masuk seller");

        msgin = dp.getSendToConn(msgin.getInput().get(RuleNameParameter.product_code).toString(), msgin);
        System.out.println("masuk seller");

        if (dp.checkVerified(msgin.getInput().get(FieldParameter.userlogin).toString())) {
            System.out.println("masuk seller4");

            dp.getDetailFromEmoney(msgin);
            System.out.println("masuk seller42");
            System.out.println("amount : " + msgin.getInput().get(RuleNameParameter.amount).toString());
            System.out.println("userlogin : " + msgin.getInput().get(FieldParameter.userlogin).toString());
            System.out.println("userlogin : " + msgin.getInput().get(FieldParameter.userlogin).toString());
            if (dp.checkMaxBalance(msgin.getInput().get(FieldParameter.userlogin).toString(), (msgin.getInput().get(RuleNameParameter.amount).toString()))) {
                System.out.println("masuk seller5");

//                if (!CheckSumFunction.verifyCheckSum((msgin.getInput().get(RuleNameParameter.amount).toString()), (msgin.getInput().get(FieldParameter.check_sum).toString()))) {
//                    msgin.getInput().put(RuleNameParameter.resp_code, "0052");
//                    msgin.getInput().put(RuleNameParameter.resp_desc, "Error CheckSum");
//                    dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), "Error CheckSum", "0052", "0052");
//                } else {
                AgentDBProc fromdbAgent = new AgentDBProc();
                LimitProcResponse fromlpr = fromdbAgent.checkLimit(msgin.getInput().get(FieldParameter.fromAccount).toString(),
                        msgin.getInput().get(FieldParameter.amount).toString());
                if (fromlpr.getStatus().equals("L00")) {
                    System.out.println("masuk seller6");

                    tempmsg.setCurr_bal(fromlpr.getCurr());
                    tempmsg.setPrev_bal(fromlpr.getPrev());
                                        System.out.println("masuk seller7");

                    dp.updateStatusReplyTempmsg4(msgin.getInput().get(FieldParameter.msgId).toString(), "0000", "0000", tempmsg);
                    System.out.println("masuk seller72");

                    if (msgin.getInput().get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                                            System.out.println("masuk seller 8");

                        AgentDBProc dbAgent = new AgentDBProc();
                        LimitProcResponse lpr = dbAgent.receiveMoney(msgin.getInput().get(FieldParameter.userlogin).toString(),
                                msgin.getInput().get(FieldParameter.amount).toString());
                                                                    System.out.println("masuk seller 81");

                        if (lpr.getStatus().equals("L00")) {
                                            System.out.println("masuk seller 82");

                            tempmsg.setCurr_bal(lpr.getCurr());
                            tempmsg.setPrev_bal(lpr.getPrev());
                            
                            
                            dp.updateStatusReplyToEmoney(msgin, tempmsg);
                                                                        System.out.println("masuk seller 83");

                            dp.saveMessageToTempmsgEmoneySeller(msgin, tempmsg);
                                                                        System.out.println("masuk seller 84");

                            response = msgin.getInput();
                        } else {
                            msgin.getInput().put(FieldParameter.resp_code, "0061");
                            msgin.getInput().put(FieldParameter.resp_desc, lpr.getStatusDesc());
                            response = msgin.getInput();
                        }
                        dbAgent = null;
                        lpr = null;
                    } else {
                        msgin.getInput().put(RuleNameParameter.resp_desc, "Kode tidak ditemukan atau sudah kadaluarsa");
                        response = msgin.getInput();
                    }
                } else if (fromlpr.getStatus().equals("L05")) {
                    msgin.getInput().put(FieldParameter.resp_code, "0051");
                    msgin.getInput().put(FieldParameter.resp_desc, "Saldo partner tidak mencukupi");
                    response = msgin.getInput();
                } else {

                    msgin.getInput().put(FieldParameter.resp_code, "0061");
                    msgin.getInput().put(FieldParameter.resp_desc, "Saldo partner telah mencapai limit");
                    response = msgin.getInput();
                }
                fromdbAgent = null;
                fromlpr = null;
//                }
            } else {
                msgin.getInput().put(FieldParameter.resp_code, "0001");
                msgin.getInput().put(FieldParameter.resp_desc, "Transaksi ditolak, saldo anda melebihi batas silahkan hubungi call center");
                response = msgin.getInput();
            }
        } else {
            msgin.getInput().put(FieldParameter.resp_code, "0001");
            msgin.getInput().put(FieldParameter.resp_desc, "Account anda belum terverifikasi");
            response = msgin.getInput();
        }

        return response;
    }
}
