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
public class CheckFirstTime {

    private static Logger log = Logger.getLogger(CheckFirstTime.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {

        DatabaseProcess dp = new DatabaseProcess();
        HashMap version = dp.getVersion(input);
        try {
            if (String.valueOf(version.get(FieldParameter.versionCode)).equals(input.get(FieldParameter.versionCode).toString())) {

                HashMap result = dp.getSINGLEuserDetail(input);
                if (result.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {

                    if ("2".equals(result.get(FieldParameter.status).toString())) {
//                        System.out.println("asdsadas");
//                        System.out.println("asdsadas"+);
                        if (dp.requestOTP(result.get(FieldParameter.hpNumber).toString(), input.get(FieldParameter.rrn).toString())) {
                            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeNeedToVerifyOTP);
                            input.put(RuleNameParameter.resp_desc, "Anda belum memverifikasi akun anda, masukan OTP.");
                            input.put(FieldParameter.hpNumber, result.get(FieldParameter.hpNumber));
                            input.put(FieldParameter.username, result.get(FieldParameter.username));
//                            input.put(FieldParameter.refNumber, result.get(FieldParameter.refNumber));    
                            input.put(FieldParameter.userlogin, result.get(FieldParameter.userlogin));
                        } else {
                            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeFailedToSendOTP);
                            input.put(RuleNameParameter.resp_desc, "Gagal Kirim OTP, silahkan coba beberapa saat lagi.");
                        }

                    } else if ("1".equals(result.get(FieldParameter.status).toString())) {
                        input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
                        input.put(FieldParameter.hpNumber, result.get(FieldParameter.hpNumber));
                        input.put(FieldParameter.username, result.get(FieldParameter.username));
                        input.put(FieldParameter.userlogin, result.get(FieldParameter.userlogin));
                    }
//                } else {
//                    HashMap result2 = dp.getLKDDetail(input);
//                    if (result2.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
//                        input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodedifferenceapps);
//                        input.put(RuleNameParameter.resp_desc, "No HP Anda terdaftar sebagai agent, mohon gunakan apps CUSO agent.");
                } else {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                    input.put(RuleNameParameter.resp_desc, "Username / No HP anda tidak terdaftar");
                }

//                }
            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeVersionNotMatch);
                input.put(RuleNameParameter.resp_desc, "Aplikasi membutuhkan update terbaru");
                input.put(RuleNameParameter.url_update_aps, RuleNameParameter.url_update_value);

            }
        } catch (Exception e) {
            e.printStackTrace();
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeVersionNotMatch);
            input.put(RuleNameParameter.resp_desc, "Versi aplikasi tidak sesuai dengan versi aplikasi playstore");
            input.put(RuleNameParameter.url_update_aps, RuleNameParameter.url_update_value);

        }
        dp = null;
        return input;
    }
}
