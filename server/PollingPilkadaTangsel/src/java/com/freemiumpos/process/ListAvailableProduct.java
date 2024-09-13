/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Tempmsg;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class ListAvailableProduct {

    private static Logger log = Logger.getLogger(ListAvailableProduct.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        HashMap result = dp.getListAvailableProduct(input);
        if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
            input.put(RuleNameParameter.trancodeid, result.get(RuleNameParameter.listTrancodeid));
            input.put(RuleNameParameter.trancodename, result.get(RuleNameParameter.listTrancodename));
            input.put(RuleNameParameter.hargajual, result.get(RuleNameParameter.listHargajual));
            input.put(RuleNameParameter.detailproduct, result.get(RuleNameParameter.listDetailproduct));
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);

        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
        }
        dp = null;
        return input;
    }
}
