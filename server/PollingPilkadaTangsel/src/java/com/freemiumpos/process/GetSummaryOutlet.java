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
public class GetSummaryOutlet {

    private static Logger log = Logger.getLogger(GetSummaryOutlet.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
         input = dp.SummaryTransactionOutlet(input);
//        if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
//            input.put(FieldParameter.brand_id, result.get(FieldParameter.brand_id));
//            input.put(FieldParameter.brand_name, result.get(FieldParameter.brand_name));
//            input.put(FieldParameter.brand_level, result.get(FieldParameter.brand_level));
//            input.put(FieldParameter.tax_rate, result.get(FieldParameter.tax_rate));
//            input.put(FieldParameter.date, result.get(FieldParameter.date));
//            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
//        } else {
//            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
//        }
        dp = null;
        return input;
    }
}
