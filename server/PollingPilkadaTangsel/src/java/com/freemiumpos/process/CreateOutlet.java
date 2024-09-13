/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
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
public class CreateOutlet {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            //save sebelum kirim ke MOST
//            HashMap responseMOST = new HashMap();
//brand_id
//generate outlet_id by brand
            int lastOutletid = Integer.valueOf(dp.getLastOutlet_id(inputMap)) + 1;
            inputMap.put(FieldParameter.outlet_id, StringFunction.pad(String.valueOf(lastOutletid), 2, "0", "Right"));

//outlet_name
//alamat
            if (dp.addOutlets(inputMap)) {
                inputMap.put(RuleNameParameter.resp_code, "0000");
                inputMap.put(RuleNameParameter.resp_desc, "Successfull.");
                inputMap.put(FieldParameter.fullid, inputMap.get(FieldParameter.brand_id).toString() + inputMap.get(FieldParameter.outlet_id).toString());
//                inputMap.put(FieldParameter.userlogin, inputMap.get(FieldParameter.email).toString());
//                NotifikasiPendaftaranMail notif = new NotifikasiPendaftaranMail();
//                notif.notifPendaftaran(inputMap);
            } else {
                inputMap.put(RuleNameParameter.resp_code, "0003");
                inputMap.put(RuleNameParameter.resp_desc, "Failed.");

            }

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Failed.");
        }
        dp.CreateLogManage(inputMap, JsonProcess.generateJson(inputMap), inputMap.get(RuleNameParameter.resp_code).toString(), this.getClass().getSimpleName());
        dp = null;

        return inputMap;
    }
}
