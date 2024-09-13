/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
//import javax.json.JsonArray;
import org.apache.log4j.Logger;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class DeleteShoppingVoucher {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            //save sebelum kirim ke MOST
//            HashMap responseMOST = new HashMap();
            HashMap userdetail = dp.getEmployeeDetail(inputMap);
            inputMap.put(FieldParameter.userlevel, userdetail.get(FieldParameter.userlevel).toString());
            HashMap disc = dp.getDetailShoppingVoucher(inputMap);
            if (Integer.valueOf(inputMap.get(FieldParameter.userlevel).toString()) < Integer.valueOf(disc.get(FieldParameter.voucherlevel).toString())) {
                inputMap.put(RuleNameParameter.resp_code, "0001");
                inputMap.put(RuleNameParameter.resp_desc, "User level can't delete this voucher.");
            } else {
                if (dp.DeleteShoppingDiscount(inputMap)) {
                    inputMap.put(RuleNameParameter.resp_code, "0000");
                    inputMap.put(RuleNameParameter.resp_desc, "Successfull..");

                } else {
                    inputMap.put(RuleNameParameter.resp_code, "0001");
                    inputMap.put(RuleNameParameter.resp_desc, "Failed.");

                }
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
        }
        dp.CreateLogManage(inputMap, JsonProcess.generateJson(inputMap), inputMap.get(RuleNameParameter.resp_code).toString(), this.getClass().getSimpleName());
        dp = null;

        return inputMap;
    }
}
