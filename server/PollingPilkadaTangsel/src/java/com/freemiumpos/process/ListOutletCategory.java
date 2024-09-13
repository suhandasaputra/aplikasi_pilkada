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
public class ListOutletCategory {

    private static Logger log = Logger.getLogger(ListOutletCategory.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        HashMap result = dp.getListOutletCategory(input.get(FieldParameter.fullid).toString());
        HashMap CheckDevice = dp.checkIntegratedDevice(input);

        if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
            input.put(FieldParameter.info, result.get(FieldParameter.info));
            input.put(FieldParameter.alamat, result.get(FieldParameter.alamat));
            input.put(FieldParameter.header_receipt, result.get(FieldParameter.header_receipt));
            input.put(FieldParameter.footer_receipt, result.get(FieldParameter.footer_receipt));
            input.put(FieldParameter.outlet_name, result.get(FieldParameter.outlet_name));
            if (CheckDevice.get(FieldParameter.resp_code).equals("0000")) {
                input.put(FieldParameter.most_integrated, "TRUE");
                CheckDevice.remove("resp_code");
                input.put(FieldParameter.most_parameters, CheckDevice);
            } else {
                input.put(FieldParameter.most_integrated, "FALSE");
            }
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
            input.put(FieldParameter.list, result.get("list"));
        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
        }
        dp = null;
        return input;
    }
}
