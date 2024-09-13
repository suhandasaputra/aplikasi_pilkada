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
 * Matajari Mitra Solusi
 *
 * @author adiwibowo
 */
public class InquiryToAccountBank {

    private static Logger log = Logger.getLogger(InquiryToAccountBank.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
//        HashMap result = dp.getToAccount(input.get(FieldParameter.toAccount).toString());

//        if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
        input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
        input.put(FieldParameter.toAccount, input.get(FieldParameter.toAccount));
        input.put(FieldParameter.name, "ADI WIBOWO");
        input.put(FieldParameter.bank_name, input.get(FieldParameter.bank_name));
        input.put(FieldParameter.amount, input.get(FieldParameter.amount).toString());
//        } else {
//            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
//        }
//        dp = null;
        return input;
    }
}
