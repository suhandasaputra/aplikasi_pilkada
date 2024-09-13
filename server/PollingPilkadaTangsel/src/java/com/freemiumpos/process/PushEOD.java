/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class PushEOD {

    DatabaseProcess dp = new DatabaseProcess();

    public HashMap process(HashMap inputMap) {
        try {
            dp.cleanData();
            inputMap.put(RuleNameParameter.resp_code, "0000");
            inputMap.put(RuleNameParameter.resp_desc, "CLEAN");

            dp = null;
        } catch (Exception e) {
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_desc, "Message request tidak sesuai");
        }
        return inputMap;
    }
}
