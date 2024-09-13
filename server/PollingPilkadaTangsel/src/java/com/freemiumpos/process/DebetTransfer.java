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
public class DebetTransfer {

    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    private DatabaseProcess dp = new DatabaseProcess();
    Messagein msgin = new Messagein();
    HashMap response = null;
    private Tempmsg tempmsg = new Tempmsg();
    private static Logger log = Logger.getLogger(DebetTransfer.class);

    public HashMap process(HashMap input) throws Exception {
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
        tempmsg.setToaccount(msgin.getInput().get(FieldParameter.toAccount).toString());
        tempmsg.setAmount(msgin.getInput().get(FieldParameter.amount).toString());
        msgin = dp.getSendToConn(msgin.getInput().get(RuleNameParameter.product_code).toString(), msgin);
//        log.info("check verified broo");

        if (dp.checkVerified(msgin.getInput().get(FieldParameter.userlogin).toString())) {
//            log.info("verified broo");
//            if (dp.checkVerified(msgin.getInput().get(FieldParameter.toAccount).toString())) {
//            dp.getDetailFromEmoney(msgin);

            //check max limit destination account
//                if (dp.checkMaxBalance(msgin.getInput().get(FieldParameter.toAccount).toString(), (msgin.getInput().get(RuleNameParameter.amount).toString()))) {
            AgentDBProc fromdbAgent = new AgentDBProc();
            LimitProcResponse fromlpr = fromdbAgent.checkLimit(msgin.getInput().get(FieldParameter.userlogin).toString(),
                    msgin.getInput().get(FieldParameter.amount).toString());
//            log.info("fromlpr.userlogin() : " + msgin.getInput().get(FieldParameter.userlogin).toString());
//            log.info("fromlpr.amount() : " + msgin.getInput().get(FieldParameter.amount).toString());
//            log.info("fromlpr.getStatus() : " + fromlpr.getStatus());

            if (fromlpr.getStatus().equals("L00")) {
                tempmsg.setCurr_bal(fromlpr.getCurr());
                tempmsg.setPrev_bal(fromlpr.getPrev());
                dp.saveMessageToTempmsgTransfer(msgin, tempmsg);
//                input.put(FieldParameter.resp_code, "0000");
//                input.put(FieldParameter.resp_desc, "Succesfull");
                msgin.getInput().put(FieldParameter.resp_code, "0000");
                response = msgin.getInput();

            } else if (fromlpr.getStatus().equals("L05")) {
                msgin.getInput().put(FieldParameter.resp_code, "0051");
//                input.put(FieldParameter.resp_code, "0051");
                msgin.getInput().put(FieldParameter.resp_desc, "Saldo Anda tidak mencukupi");
//                input.put(FieldParameter.resp_desc, "Saldo Anda tidak mencukupi");
                response = msgin.getInput();
            } else {

//                input.put(FieldParameter.resp_code, "0061");
//                input.put(FieldParameter.resp_desc, "Saldo Anda telah mencapai limit");
//
                msgin.getInput().put(FieldParameter.resp_code, "0061");
                msgin.getInput().put(FieldParameter.resp_desc, "Saldo Anda telah mencapai limit");
                response = msgin.getInput();
            }
            fromdbAgent = null;
            fromlpr = null;
//                }
//                } else {
//                    msgin.getInput().put(FieldParameter.resp_code, "0001");
//                    msgin.getInput().put(FieldParameter.resp_desc, "Transaksi ditolak, saldo partner melebihi batas silahkan hubungi call center");
//                    response = msgin.getInput();
//                }
//            } else {
//                msgin.getInput().put(FieldParameter.resp_code, "0001");
//                msgin.getInput().put(FieldParameter.resp_desc, "Account partner belum terverifikasi");
//                response = msgin.getInput();
//            }
        } else {
//            input.put(FieldParameter.resp_code, "0001");
//            input.put(FieldParameter.resp_desc, "Account anda belum terverifikasi");
            msgin.getInput().put(FieldParameter.resp_code, "0001");
            msgin.getInput().put(FieldParameter.resp_desc, "Account anda belum terverifikasi");
            response = msgin.getInput();
        }

        return response;
    }
}
