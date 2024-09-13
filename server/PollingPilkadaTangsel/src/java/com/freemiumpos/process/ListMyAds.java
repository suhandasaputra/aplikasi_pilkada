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
public class ListMyAds {
     private static Logger log = Logger.getLogger(ListMyAds.class);
     private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        HashMap result = dp.getListAds(input.get(FieldParameter.cuid).toString());
        if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
            input.put(FieldParameter.listAds, result.get("list"));
        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
        }
        dp = null;
        return input;
    }
}