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
public class ListRedeemItem {

    private static Logger log = Logger.getLogger(ListRedeemItem.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        HashMap result = dp.getRedeemItemList();
        if (result.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
            input.put(FieldParameter.resp_code, RuleNameParameter.respcodeSuccess);
            input.put(FieldParameter.listRedeemItem, result.get("list"));
        } else {
            input.put(FieldParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
        }

        dp = null;
        return input;
    }
}
