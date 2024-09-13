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
public class RequestOTP2 {

    private static Logger log = Logger.getLogger(RequestOTP2.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        HashMap version = dp.getVersion(input);
        try {
            if (String.valueOf(version.get(FieldParameter.versionCode)).equals(input.get(FieldParameter.versionCode).toString())) {
                HashMap result = dp.getUserDetail(input);
                if (result.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                    if (input.get(FieldParameter.userlogin).toString().equals(result.get(FieldParameter.userlogin).toString())
                            && input.get(FieldParameter.hpNumber).toString().equals(result.get(FieldParameter.hpNumber).toString())) //                        result.put(FieldParameter.userlogin, rs.getString("agent_id"));
                    {
                        if (dp.requestOTP(result.get(FieldParameter.hpNumber).toString(), result.get(FieldParameter.rrn).toString())) {
                            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
                            input.put(FieldParameter.hpNumber, result.get(FieldParameter.hpNumber));
                            input.put(FieldParameter.username, result.get(FieldParameter.username));
                            input.put(FieldParameter.userlogin, result.get(FieldParameter.userlogin));
                        } else {
                            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                            input.put(RuleNameParameter.resp_desc, "Gagal Kirim OTP, silahkan coba beberapa saat lagi.");
                        }
                    } else {
                        input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                        input.put(RuleNameParameter.resp_desc, "Username / No HP anda tidak valid");
                    }

                } else {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                    input.put(RuleNameParameter.resp_desc, "Username / No HP anda tidak terdaftar");
                }
            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeVersionNotMatch);
                input.put(RuleNameParameter.resp_desc, "Aplikasi membutuhkan update terbaru");
                input.put(RuleNameParameter.url_update_aps, RuleNameParameter.url_update_value);

            }
        } catch (Exception e) {

            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeVersionNotMatch);
            input.put(RuleNameParameter.resp_desc, "Versi aplikasi tidak sesuai dengan versi aplikasi playstore");
            input.put(RuleNameParameter.url_update_aps, RuleNameParameter.url_update_value);

        }
        dp = null;
        return input;
    }
}
