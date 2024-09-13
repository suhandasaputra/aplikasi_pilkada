/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
//import javax.json.JsonArray;
import org.apache.log4j.Logger;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class DigitalItemPurchase {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            if (dp.ChargeDigitalItemPurchase(inputMap)) {
                dp.updateSummaryReportDay(inputMap);

                inputMap.put(RuleNameParameter.resp_code, "0000");
                inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
                dp.updateLogTrxDigitalItem(inputMap);
//                dp.PaymentOrder(inputMap, "1");
            } else {
                inputMap.put(RuleNameParameter.resp_code, "0001");
                inputMap.put(RuleNameParameter.resp_desc, "Failed.");

            }
            dp = null;
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
        }
        return inputMap;
    }
}
