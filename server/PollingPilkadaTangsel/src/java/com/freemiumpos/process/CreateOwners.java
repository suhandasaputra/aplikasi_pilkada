/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.function.CheckEmailValidOrNot;
import com.freemium.function.JsonProcess;
import com.freemium.function.NotifikasiPendaftaranMail;
import com.freemium.function.StringFunction;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
//import javax.json.JsonArray;
import org.apache.log4j.Logger;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class CreateOwners {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            //save sebelum kirim ke MOST
//            HashMap responseMOST = new HashMap();
            if (CheckEmailValidOrNot.isValid(inputMap.get(FieldParameter.email).toString())) {

                HashMap check = new HashMap();
                check.put(FieldParameter.userlogin, inputMap.get(FieldParameter.email).toString());
                check = dp.getEmployeeDetail(check);
                if (!check.get(FieldParameter.resp_code).toString().equals("0000")) {
                    inputMap.put(FieldParameter.brand_id, StringFunction.getCurrentDateYYYYMMDDHHMMSS() + dp.BrandSeq());
                    HashMap rs = dp.CreateOwners(inputMap);
                    if (rs.get(FieldParameter.resp_code).toString().equals("0000")) {
//                inputMap.put(FieldParameter.brand_name, "Sample Brand Name");
//                inputMap.put(FieldParameter.brand_level, "0");
                        if (dp.CreateBrand(inputMap)) {
                            inputMap.put(FieldParameter.outlet_id, "01");
//                    inputMap.put(FieldParameter.outlet_name, "Sample Outlet Name");
//                    inputMap.put(FieldParameter.alamat, "Sample Alamat Outlet");
//                    inputMap.put(FieldParameter.header_receipt, "Sample Header Receipt Outlet");
//                    inputMap.put(FieldParameter.footer_receipt, "Sample Footer Receipt Outlet");
//                    inputMap.put(FieldParameter.info, "Sample Info Outlet");
//                    inputMap.put(FieldParameter.shifting, "0");
                            if (dp.CreateOutlets(inputMap)) {
                                inputMap.put(RuleNameParameter.resp_code, "0000");
                                inputMap.put(FieldParameter.userlogin, inputMap.get(FieldParameter.email).toString());
                                NotifikasiPendaftaranMail notif = new NotifikasiPendaftaranMail();
                                notif.notifPendaftaran(inputMap);
                            } else {
                                inputMap.put(RuleNameParameter.resp_code, "0003");
                                inputMap.put(RuleNameParameter.resp_desc, "Failed.");

                            }
                        } else {
                            inputMap.put(RuleNameParameter.resp_code, "0002");
                            inputMap.put(RuleNameParameter.resp_desc, "Failed.");

                        }
                    } else {
                        inputMap.put(RuleNameParameter.resp_code, "0001");
                        inputMap.put(RuleNameParameter.resp_desc, "SignUp Failed.");
                    }
                } else {
                    inputMap.put(RuleNameParameter.resp_code, "0004");
                    inputMap.put(RuleNameParameter.resp_desc, "The email you entered is already registered. Please sign in to continue.");
                }

            } else {
                inputMap.put(RuleNameParameter.resp_code, "0005");
                inputMap.put(RuleNameParameter.resp_desc, "The email you entered is not valid.");
            }

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Failed.");
        }
//        inputMap.put(FieldParameter.userlogin, inputMap.get(FieldParameter.email).toString());
//        dp.CreateLogManage(inputMap, JsonProcess.generateJson(inputMap), inputMap.get(RuleNameParameter.resp_code).toString(), this.getClass().getSimpleName());
        dp = null;

        return inputMap;
    }
}
