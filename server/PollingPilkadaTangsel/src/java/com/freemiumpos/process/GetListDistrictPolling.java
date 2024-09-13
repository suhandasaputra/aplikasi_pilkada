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
public class GetListDistrictPolling {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(GetListDistrictPolling.class);

    public HashMap process(HashMap inputMap) {
        try {
            inputMap =  dp.GetListDistrict(inputMap);
            
            if (inputMap.get(FieldParameter.resp_code).equals("0000")) {
                
                inputMap.put(RuleNameParameter.resp_desc, "Success Get List");
            } else {
                inputMap.put(RuleNameParameter.resp_code, "0001");
                inputMap.put(RuleNameParameter.resp_desc, "Failed Get List");

            }

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Failed.");
//            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
        }
      
        return inputMap;
    }
}
