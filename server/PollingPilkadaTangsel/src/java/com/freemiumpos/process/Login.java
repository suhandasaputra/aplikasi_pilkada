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
public class Login {

    private static Logger log = Logger.getLogger(Login.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {

        DatabaseProcess dp = new DatabaseProcess();
//        HashMap version = dp.getVersion(input);
        try {
//            if (String.valueOf(version.get(FieldParameter.versionCode)).equals(input.get(FieldParameter.versionCode).toString())) {
            HashMap result = dp.getUserDetail(input);
            if (dp.CheckPassword(input)) {

                if ("-".equals(result.get(FieldParameter.pin).toString())) {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeNeedToCreatePin);
                    input.put(RuleNameParameter.resp_desc, "Anda Perlu Create Pin untuk melakukan transaksi");
                } else {
                    if (result.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                        if (Integer.valueOf(result.get(FieldParameter.numfaillogin).toString()) < 6) {

                            if (result.get(FieldParameter.status).equals("2") || result.get(FieldParameter.status).equals("1")) {
                                if (result.get(FieldParameter.userpassword).equals(input.get(FieldParameter.password))) {
                                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
                                    input.put(FieldParameter.password, result.get(FieldParameter.userpassword));
                                    input.put(FieldParameter.username, result.get(FieldParameter.username));
                                    input.put(FieldParameter.userlogin, result.get(FieldParameter.userlogin));
                                    input.put(FieldParameter.hpNumber, result.get(FieldParameter.hpNumber));
                                    input.put(FieldParameter.pin, result.get(FieldParameter.pin));
                                    input.put(FieldParameter.address, result.get(FieldParameter.address));
                                    input.put(FieldParameter.userlevel, result.get(FieldParameter.userlevel));
                                    input.put(FieldParameter.ktp, result.get(FieldParameter.ktp));
                                    input.put(FieldParameter.status, result.get(FieldParameter.status));
                                    input.put(FieldParameter.verified, result.get(FieldParameter.verified));
                                    dp.resetNumfaillogin(result.get(FieldParameter.userlogin).toString());
                                } else {
                                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                                    input.put(RuleNameParameter.resp_desc, "Password salah, harap cek kembali password anda");
                                    dp.updateNumfaillogin(result.get(FieldParameter.userlogin).toString());
                                }
                            } else {
                                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                                input.put(RuleNameParameter.resp_desc, "User sudah tidak aktif, harap hubungi call center");
                            }
                        } else {
                            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                            input.put(RuleNameParameter.resp_desc, "Salah password terlalu sering, sementara account di nonaktifkan harap hubungi call center");
                        }
                    } else {
                        input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                        input.put(RuleNameParameter.resp_desc, "Username salah, harap cek kembali username anda");
                    }
                }
            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                input.put(RuleNameParameter.resp_desc, "OTP SALAH");
            }
        } catch (Exception e) {

            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeVersionNotMatch);
            input.put(RuleNameParameter.url_update_aps, RuleNameParameter.url_update_value);
            input.put(RuleNameParameter.resp_desc, "Versi aplikasi tidak sesuai dengan versi aplikasi playstore");
        }
        dp = null;
        return input;
    }
}
