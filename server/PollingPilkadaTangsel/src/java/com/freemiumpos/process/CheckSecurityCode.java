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
public class CheckSecurityCode {

    private static Logger log = Logger.getLogger(CheckSecurityCode.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        
        System.out.println("ini input nyaaaaaaaaaa : "+input);
        DatabaseProcess dp = new DatabaseProcess();
//        HashMap version = dp.getVersion(input);
        try {
//            if (String.valueOf(version.get(FieldParameter.versionCode)).equals(input.get(FieldParameter.versionCode).toString())) {
            HashMap result = dp.getEmployeeDetail(input);
            if (result.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
//                if (Integer.valueOf(result.get(FieldParameter.numfailpin).toString()) < 6) {
                if (result.get(FieldParameter.userpassword).equals(input.get(FieldParameter.password))) {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
                    input.put(FieldParameter.username, result.get(FieldParameter.username));
                    input.put(FieldParameter.userlogin, result.get(FieldParameter.userlogin));
                    input.put(FieldParameter.userid, result.get(FieldParameter.userid));
                    input.put(FieldParameter.fullid, result.get(FieldParameter.fullid));
                    input.put(FieldParameter.brand_id, result.get(FieldParameter.brand_id));
                    input.put(FieldParameter.outlet_id, result.get(FieldParameter.outlet_id));
                    input.put(FieldParameter.userlevel, result.get(FieldParameter.userlevel));
                    input.put(FieldParameter.status, result.get(FieldParameter.status));
                    input.put(FieldParameter.tax_rate, result.get(FieldParameter.tax_rate));
                    if (input.get(FieldParameter.userlevel).toString().equals("4")) {
                        result = dp.GetListOutletBrand(result.get(FieldParameter.userlogin).toString());
                        input.put(FieldParameter.list, result.get(FieldParameter.list));
                    } else {
                        input.put(FieldParameter.list, "");
                    }
//                        dp.resetNumfailpin(result.get(FieldParameter.userlogin).toString());
//                        dp.updateLastLogin(result.get(FieldParameter.userlogin).toString());
                } else {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                    input.put(RuleNameParameter.resp_desc, "Incorrect Email or Password. Please try again.");
//                        dp.updateNumfailpin(result.get(FieldParameter.userlogin).toString());
                }
//                } else {
//                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
//                    input.put(RuleNameParameter.resp_desc, "Salah SecurityCode/pin terlalu sering, sementara account di nonaktifkan harap hubungi call center");
//                }
            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                input.put(RuleNameParameter.resp_desc, "Incorrect Email or Password. Please try again.");
            }
//            } else {
//                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeVersionNotMatch);
//                input.put(RuleNameParameter.resp_desc, "Aplikasi membutuhkan update terbaru");
//            }
        } catch (Exception e) {
//            System.out.println("e : " + e);
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeVersionNotMatch);
            input.put(RuleNameParameter.resp_desc, "The application version does not match the playstore application version");
            input.put(RuleNameParameter.url_update_aps, RuleNameParameter.url_update_value);
            e.printStackTrace();

        }
        dp = null;
        return input;
    }
}
