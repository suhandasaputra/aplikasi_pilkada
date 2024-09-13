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
public class InquirySaldo {
     private static Logger log = Logger.getLogger(InquirySaldo.class);
     private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        HashMap result = dp.getSaldo(input.get(FieldParameter.userlogin).toString());

        if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
            input.put(FieldParameter.balance, result.get(FieldParameter.balance));
            input.put(FieldParameter.balance_poin, result.get(FieldParameter.balance_poin));
            input.put(FieldParameter.balance_day, result.get(FieldParameter.balance_day));
            input.put(FieldParameter.balance_month, result.get(FieldParameter.balance_month));
        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
        }
        dp = null;
        return input;
    }
}
