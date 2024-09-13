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
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.ProcessingCode;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class Overbooking {

    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    private DatabaseProcess dp = new DatabaseProcess();
    Messagein msgin = new Messagein();
    HashMap response = null;
    private Tempmsg tempmsg = new Tempmsg();
    private static Logger log = Logger.getLogger(Overbooking.class);

    public HashMap process(HashMap input) throws Exception {
        input.put(StaticParameter.biayaadmin, dp.getBiayaAdmin(input.get(FieldParameter.procCode).toString()));
        HashMap DanaMasuk = new HashMap();
        DanaMasuk = input;
        msgin.setInput(input);
        msgin.setRefer(input.get(FieldParameter.refer).toString());
        log.info(msgin.getInput());
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());
        tempmsg.setNoref(msgin.getInput().get(FieldParameter.rrn).toString());
        tempmsg.setProccode(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setTransactionid(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setProductcode(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
        msgin.setNo_ba(input.get(FieldParameter.no_ba).toString());

//        if (!msgin.getInput().get(StaticParameterCuso.no_ba).toString().equals("")) {
//            tempmsg.setFromaccount(msgin.getInput().get(StaticParameterCuso.no_ba).toString());
//        } else {
        tempmsg.setFromaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        msgin.getInput().put(FieldParameter.fromAccount, msgin.getInput().get(FieldParameter.userlogin).toString());
//        }
        tempmsg.setCustNo(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setToaccount(msgin.getInput().get(FieldParameter.toAccount).toString());
        tempmsg.setAmount(msgin.getInput().get(FieldParameter.amount).toString());
        msgin = dp.getSendToConnPrepaid(msgin.getInput().get(FieldParameter.procCode).toString(), msgin, msgin.getRefer());
        String amount = String.valueOf(Integer.valueOf(tempmsg.getAmount()) + Integer.valueOf(dp.getBiayaAdmin(tempmsg.getProccode())));
        dp.saveMessageToTempmsgTransfer(msgin, tempmsg);
        if (dp.checkVerified(msgin.getInput().get(FieldParameter.userlogin).toString())) {
//            if (dp.checkVerified(msgin.getInput().get(FieldParameter.toAccount).toString())) {
                if (dp.checkMaxBalance(msgin.getInput().get(FieldParameter.toAccount).toString(), tempmsg.getAmount())) {
                    AgentDBProc fromdbAgent = new AgentDBProc();
                    LimitProcResponse fromlpr = fromdbAgent.checkLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                            amount);
                    if (fromlpr.getStatus().equals("L00")) {
                        tempmsg.setCurr_bal(fromlpr.getCurr());
                        tempmsg.setPrev_bal(fromlpr.getPrev());
//                        log.info("LPR1 : " + tempmsg.getCurr_bal() + " | " + tempmsg.getPrev_bal());
                        AgentDBProc dbAgent = new AgentDBProc();
                        LimitProcResponse lpr = dbAgent.receiveMoney(msgin.getInput().get(FieldParameter.toAccount).toString(),
                                tempmsg.getAmount());
                        if (lpr.getStatus().equals("L00")) {
                            HashMap all = new HashMap();
                            all.put("F0", input);
                            all.put("F1", timestamp + msgin.getInput().get(FieldParameter.toAccount).toString() + tempmsg.getNoref());
                            all.put("F2", tempmsg.getNoref() + input.get(FieldParameter.toAccount).toString().substring(input.get(FieldParameter.toAccount).toString().length() - 3, input.get(FieldParameter.toAccount).toString().length()));
                            all.put("F3", ProcessingCode.danaMasuk);
                            all.put("F4", ProcessingCode.danaMasuk);
                            all.put("F5", ProcessingCode.danaMasuk);
                            all.put("F6", "qma");
                            all.put("F7", input.get(FieldParameter.toAccount).toString());
                            all.put("F8", input.get(FieldParameter.toAccount).toString());
                            all.put("F9", msgin.getInput().get(FieldParameter.userlogin).toString());
                            all.put("F10", tempmsg.getAmount());
                            all.put("F11", lpr.getPrev());
                            all.put("F12", lpr.getCurr());
                            all.put("F15", input.get(FieldParameter.toAccount).toString());
                            all.put("F16", msgin.getRefer());
//                            System.out.println(all.get("F!"));
                            dp.saveMessageToTempmsgDanaMasuk(all);
                            dp.updateStatusTransfer(msgin, tempmsg);
                            msgin.getInput().put(FieldParameter.resp_code, "0000");
                            String tcdode = dp.getProductCode(msgin.getMsgid());
                            msgin.getInput().put(FieldParameter.productName, dp.getProductDetail(tcdode));
                            msgin.getInput().put(StaticParameter.biayaadmin, dp.getBiayaAdmin(tempmsg.getProccode()));
                            msgin.getInput().put(RuleNameParameter.Total, amount);
                            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), JsonProcess.generateJson(msgin.getInput()), "0000", "0000");

                            String tcdode2 = dp.getProductCode(all.get("F1").toString());
                            DanaMasuk.put(FieldParameter.procCode, ProcessingCode.danaMasuk);
                            DanaMasuk.put(FieldParameter.product_code, ProcessingCode.danaMasuk);
                            DanaMasuk.put(FieldParameter.productName, dp.getProductDetail(tcdode2));
//                            DanaMasuk.put(RuleNameParameter.Total, DanaMasuk.get(FieldParameter.amount).toString());
                            DanaMasuk.remove(StaticParameter.biayaadmin);
                            DanaMasuk.remove(RuleNameParameter.Total);
                            DanaMasuk.put(FieldParameter.rrn, all.get("F2").toString());
                            dp.updateTempmsgDanaMasuk(all.get("F1").toString(), JsonProcess.generateJson(DanaMasuk), "0000", "0000");
                            response = msgin.getInput();
                            response.put(RuleNameParameter.Total, amount);

                        } else {
                            msgin.getInput().put(FieldParameter.resp_code, "0061");
                            msgin.getInput().put(FieldParameter.resp_desc, lpr.getStatusDesc());
                            response = msgin.getInput();
                        }
                        dbAgent = null;
                        lpr = null;
                    } else if (fromlpr.getStatus().equals("L05")) {
                        msgin.getInput().put(FieldParameter.resp_code, "0051");
                        msgin.getInput().put(FieldParameter.resp_desc, "Saldo Anda tidak mencukupi");
                        response = msgin.getInput();
                    } else {

                        msgin.getInput().put(FieldParameter.resp_code, "0061");
                        msgin.getInput().put(FieldParameter.resp_desc, "Saldo Anda telah mencapai limit");
                        response = msgin.getInput();
                    }
                    fromdbAgent = null;
                    fromlpr = null;
                } else {
                    msgin.getInput().put(FieldParameter.resp_code, "0001");
                    msgin.getInput().put(FieldParameter.resp_desc, "Transaksi ditolak, saldo partner melebihi batas silahkan hubungi call center");
                    response = msgin.getInput();
                }
//            } else {
//                msgin.getInput().put(FieldParameter.resp_code, "0001");
//                msgin.getInput().put(FieldParameter.resp_desc, "Account partner belum terverifikasi");
//                response = msgin.getInput();
//            }
        } else {
            msgin.getInput().put(FieldParameter.resp_code, "0001");
            msgin.getInput().put(FieldParameter.resp_desc, "Account anda belum terverifikasi");
            response = msgin.getInput();
        }

        return response;
    }
}
