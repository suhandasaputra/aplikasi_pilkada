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
public class CorpDeactivation {

    private static Logger log = Logger.getLogger(CorpDeactivation.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        try {
            input = dp.CorpDeactivation(input);
        } catch (Exception e) {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
            input.put(RuleNameParameter.resp_desc, "Deactivation Fail");
        }
        dp = null;
        return input;
    }
}
