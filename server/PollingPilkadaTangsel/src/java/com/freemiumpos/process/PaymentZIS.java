/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Messagein;
import com.freemium.entity.Tempmsg;
import com.freemium.singleton.ResponseCodeSingleton;
import com.agentmanagement.database.AgentDBProc;
import com.agentmanagement.entity.LimitProcResponse;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
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
public class PaymentZIS {

    private static Logger log = Logger.getLogger(PaymentZIS.class);
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHss").format(Calendar.getInstance().getTime());
    private Tempmsg tempmsg = new Tempmsg();
    Messagein msgin = new Messagein();
    private DatabaseProcess dp = new DatabaseProcess();
//    RedeemSwitch redeemSwitch = new RedeemSwitch();
//    SendHttpProcess http = new SendHttpProcess();
    HashMap response = null;

    public HashMap process(HashMap input) throws Exception {

        System.out.println("masok zis");
        msgin.setInput(input);
        input.remove(FieldParameter.pin);

        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());
//        int amount = Integer.valueOf(input.get(RuleNameParameter.amount).toString()) + Integer.valueOf(getWarungDetil.get(StaticParameter.biayaadmin).toString());

        tempmsg.setFromaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setToaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());

        tempmsg.setMsgid(msgin.getMsgid());
        tempmsg.setAmount(input.get(RuleNameParameter.amount).toString());
        tempmsg.setNoref(msgin.getInput().get(RuleNameParameter.rrn).toString());
        tempmsg.setProccode(msgin.getInput().get(FieldParameter.procCode).toString());

        tempmsg.setTransactionid(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setProductcode(msgin.getInput().get(FieldParameter.procCode).toString());

        tempmsg.setCustNo(input.get(FieldParameter.name).toString() + "|" + input.get(FieldParameter.hpNumber).toString() + "|" + input.get(FieldParameter.email).toString());
        tempmsg.setTrxidbackend("");
        tempmsg.setFromSocket(msgin.getFromsocket());
        tempmsg.setBankcode("qma");
        tempmsg.setBankcodefrom(msgin.getInput().get(FieldParameter.userlogin).toString());
        dp.saveMessageToTempmsgPayment(msgin, tempmsg);

        AgentDBProc dbAgent = new AgentDBProc();
        LimitProcResponse lpr = dbAgent.checkLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                input.get(FieldParameter.amount).toString());
        if (lpr.getStatus().equals("L00")) {
            if (dp.updateHoldingAccountZIS("D001", input.get(RuleNameParameter.amount).toString())) {
                tempmsg.setCurr_bal(lpr.getCurr());
                tempmsg.setPrev_bal(lpr.getPrev());
//                msgin.getInput().put(RuleNameParameter.merchantname, getWarungDetil.get(RuleNameParameter.merchantname));
                msgin.getInput().put(FieldParameter.resp_code, "0000");
                msgin.getInput().put(FieldParameter.amount, input.get(RuleNameParameter.amount).toString());
                dp.updateStatusReplyTempmsgZIS(msgin.getMsgid(), "0000", "0000", tempmsg, JsonProcess.generateJson(msgin.getInput()));
            } else {
//                lpr = 
                dbAgent.reversalLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                        input.get(FieldParameter.amount).toString());
//                dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), JsonProcess.generateJson(msgin.getInput()), "0001", "0001");
                dp.updateStatusReplyTempmsgZIS(msgin.getMsgid(), "0001", "0001", tempmsg, JsonProcess.generateJson(msgin.getInput()));

            }

//                log.info("kirim response : " + msgin.getInput().get(FieldParameter.resp_code));
//                log.info("kirim response : " + msgin.getInput().get(FieldParameter.amount));
            response = msgin.getInput();
            dbAgent = null;
            lpr = null;
        } else if (lpr.getStatus().equals("L05")) {
            msgin.getInput().put(RuleNameParameter.resp_code, "0001");
            msgin.getInput().put(RuleNameParameter.resp_desc, "Saldo Tidak Cukup/Melebihi Batas Transaksi Bulanan.");
            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), JsonProcess.generateJson(msgin.getInput()), "0001", "0001");
            response = msgin.getInput();

        } else {
            msgin.getInput().put(RuleNameParameter.resp_code, "0040");
            msgin.getInput().put(RuleNameParameter.resp_desc, ResponseCodeSingleton.getInstance().getResponseCodeDesc("0040"));
            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), JsonProcess.generateJson(msgin.getInput()), "0040", "0040");
            response = msgin.getInput();
        }
        return response;
    }

}
