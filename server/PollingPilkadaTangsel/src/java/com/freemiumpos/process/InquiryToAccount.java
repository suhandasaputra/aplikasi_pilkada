/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Tempmsg;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * Matajari Mitra Solusi
 *
 * @author adiwibowo
 */
public class InquiryToAccount {

    private static Logger log = Logger.getLogger(InquiryToAccount.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        if (!input.get(FieldParameter.userlogin).toString().equals(input.get(FieldParameter.toAccount).toString())) {
            HashMap result = dp.getToAccount(input.get(FieldParameter.toAccount).toString());
            String admin = dp.getBiayaAdmin(input.get(FieldParameter.procCode).toString());
            if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
                input.put(FieldParameter.name, result.get(FieldParameter.name));
                input.put(FieldParameter.hpNumber, result.get(FieldParameter.hpNumber));
                input.put(FieldParameter.amount, input.get(FieldParameter.amount).toString());
                input.put(StaticParameter.biayaadmin, admin);
                input.put(RuleNameParameter.Total, String.valueOf(Integer.valueOf(input.get(FieldParameter.amount).toString()) + Integer.valueOf(admin)));
            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                input.put(RuleNameParameter.resp_desc, "Akun tujuan belum terdaftar.");
            }
        } else {
            System.out.println("masok kdaskdsadkk");

            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
            input.put(RuleNameParameter.resp_desc, "Maaf, tidak bisa transfer ke akun yang sama.");
        }
        dp = null;
        return input;
    }
}
