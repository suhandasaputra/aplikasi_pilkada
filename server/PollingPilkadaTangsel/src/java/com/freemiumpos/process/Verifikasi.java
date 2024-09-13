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
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class Verifikasi {

    private static Logger log = Logger.getLogger(Verifikasi.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        HashMap result = dp.getKodeDetail(input);
        if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
            if (result.get(FieldParameter.withdrawalCode).equals(input.get(FieldParameter.withdrawalCode))) {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
                input.put(RuleNameParameter.resp_desc, "Successful");
            } else {
                input.put(RuleNameParameter.resp_code, "1100");
                input.put(RuleNameParameter.resp_desc, "Kode salah, harap cek kembali kode anda");
            }
        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
            input.put(RuleNameParameter.resp_desc, "Tidak ada kode yang dimaksud");
        }
        dp = null;
        return input;
    }
}