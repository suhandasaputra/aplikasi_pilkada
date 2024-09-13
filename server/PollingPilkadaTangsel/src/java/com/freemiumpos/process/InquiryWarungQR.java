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
import com.freemium.parameter.StaticParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class InquiryWarungQR {

    private static Logger log = Logger.getLogger(InquiryWarungQR.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        HashMap result = dp.getWarungqrDetail(input);
        if (result.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
            input.put(FieldParameter.resp_code, RuleNameParameter.respcodeSuccess);
            input.put(RuleNameParameter.merchantid, result.get(RuleNameParameter.merchantid).toString());
            input.put(RuleNameParameter.merchantname, result.get(RuleNameParameter.merchantname).toString());
            input.put(StaticParameter.biayaadmin, result.get(StaticParameter.biayaadmin).toString());
            input.put(RuleNameParameter.Total, String.valueOf(Integer.valueOf(input.get(FieldParameter.amount).toString()) + Integer.valueOf(result.get(StaticParameter.biayaadmin).toString())));
        } else {
            input.put(FieldParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
        }

        dp = null;
        return input;
    }
}
