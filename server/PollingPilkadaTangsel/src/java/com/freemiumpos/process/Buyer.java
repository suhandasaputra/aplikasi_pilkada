/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Messagein;
import com.freemium.entity.Tempmsg;
import com.freemium.function.CheckSumFunction;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class Buyer {

    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    private DatabaseProcess dp = new DatabaseProcess();
    Messagein msgin = new Messagein();
    private Tempmsg tempmsg = new Tempmsg();
    HashMap response = null;

    public HashMap process(HashMap input) throws Exception {
        System.out.println("MASOK BUYER");
        msgin.setInput(input);
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(RuleNameParameter.rrn).toString());
        tempmsg.setNoref(msgin.getInput().get(RuleNameParameter.rrn).toString());
        tempmsg.setProccode(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setTransactionid(msgin.getInput().get(FieldParameter.procCode).toString());
        tempmsg.setProductcode(msgin.getInput().get(RuleNameParameter.product_code).toString());
        tempmsg.setCustNo(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setFromaccount(msgin.getInput().get(FieldParameter.userlogin).toString());
        tempmsg.setToaccount(msgin.getInput().get(FieldParameter.toAccount).toString());
        tempmsg.setAmount(msgin.getInput().get(FieldParameter.amount).toString());
        tempmsg.setWithdrawalCode(msgin.getInput().get(FieldParameter.userlogin).toString() + msgin.getInput().get(FieldParameter.rrn).toString());
        System.out.println("MASOK BUYER2");

        msgin = dp.getSendToConn(msgin.getInput().get(RuleNameParameter.product_code).toString(), msgin);
        System.out.println("MASOK BUYER3");

        if (!CheckSumFunction.verifyCheckSum((msgin.getInput().get(RuleNameParameter.amount).toString()), (msgin.getInput().get(FieldParameter.check_sum).toString()))) {
            System.out.println("MASOK BUYER4 false");

            msgin.getInput().put(RuleNameParameter.resp_code, "0052");
            msgin.getInput().put(RuleNameParameter.resp_desc, "Error CheckSum");
            dp.updateStatusReplyTempmsgBillPayment(msgin.getMsgid(), "Error CheckSum", "0052", "0052");
        } else {
            System.out.println("MASOK BUYER4 true");

            if (dp.checkVerified(msgin.getInput().get(FieldParameter.userlogin).toString())) {
                System.out.println("MASOK BUYER verified");

                if (dp.checkAmount(msgin.getInput().get(FieldParameter.userlogin).toString(), msgin.getInput().get(FieldParameter.amount).toString())) {
                    System.out.println("MASOK save1");

                    dp.saveMessageToEmoney(msgin, tempmsg);
                    System.out.println("MASOK save2");

                    dp.saveMessageToTempmsgEmoney(msgin, tempmsg);
                    System.out.println("MASOK save3");

                    dp.updateStatusReplyTempmsg3(tempmsg.getMsgid(), "KODE : " + msgin.getInput().get(FieldParameter.userlogin).toString() + msgin.getInput().get(FieldParameter.rrn).toString());
                    msgin.getInput().put(FieldParameter.withdrawalCode, msgin.getInput().get(FieldParameter.userlogin).toString() + msgin.getInput().get(FieldParameter.rrn).toString());
                    msgin.getInput().put(FieldParameter.resp_code, "0000");
                    msgin.getInput().put(FieldParameter.resp_desc, "Successful");
                    response = msgin.getInput();
                } else {
                    msgin.getInput().put(FieldParameter.resp_code, "0001");
                    msgin.getInput().put(FieldParameter.resp_desc, "Saldo anda tidak mencukupi");
                    response = msgin.getInput();
                }
            } else {
                msgin.getInput().put(FieldParameter.resp_code, "0001");
                msgin.getInput().put(FieldParameter.resp_desc, "Account anda belum terverifikasi");
                response = msgin.getInput();
            }
        }
        return response;
    }
//    }
}
