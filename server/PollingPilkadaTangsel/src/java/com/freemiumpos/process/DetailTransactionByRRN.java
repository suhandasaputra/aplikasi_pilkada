/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Tempmsg;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameter;
import com.freemium.parameter.StaticParameterCuso;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class DetailTransactionByRRN {

    private static Logger log = Logger.getLogger(DetailTransactionByRRN.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        HashMap result = dp.getDetailTransaction(input.get(FieldParameter.fullid).toString(), input.get(FieldParameter.noresi).toString());
        if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
            String tccode = dp.getProductCodeByrrn(input.get(FieldParameter.noresi).toString());
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
            HashMap detail = (HashMap) result.get(FieldParameter.detail);
            switch (tccode) {
                case "260000":
                    detail.put(FieldParameter.productName, detail.get(RuleNameParameter.merchantname).toString());
                    detail.put(FieldParameter.amount, String.valueOf(Integer.valueOf(detail.get(FieldParameter.amount).toString()) - Integer.valueOf(detail.get(StaticParameter.biayaadmin).toString())));
                    break;
                case "400001":
                    detail.put(FieldParameter.productName, dp.getProductName(tccode));
                    detail.put(RuleNameParameter.Total, detail.get(FieldParameter.amount).toString());
                    detail.put(FieldParameter.amount, String.valueOf(Integer.valueOf(detail.get(FieldParameter.amount).toString()) - Integer.valueOf(detail.get(StaticParameter.biayaadmin).toString())));
                    break;
                case "400009":
                    detail.put(FieldParameter.productName, dp.getProductName(tccode));
                    detail.put(RuleNameParameter.Total, detail.get(FieldParameter.amount).toString());
                    detail.remove(FieldParameter.amount);
                    detail.remove(StaticParameter.biayaadmin);
                    break;
                default:
                    detail.put(FieldParameter.productName, dp.getProductName(tccode));
                    break;
            }
            input.put(FieldParameter.detail, detail);
        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
        }
        dp = null;
        return input;
    }
}
