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
public class ListAllOutlet {

    private static Logger log = Logger.getLogger(ListAllOutlet.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        input.put(FieldParameter.list, "");
        String fullid = input.get(FieldParameter.fullid).toString();
//        String brand = fullid.substring(0, fullid.length() - 2);
        HashMap result = dp.GetListOutletBrand(input.get(FieldParameter.userlogin).toString());
        input.put(FieldParameter.resp_code, "0000");
        input.put(FieldParameter.list, result.get(FieldParameter.list));
        dp = null;
        return input;
    }
}
