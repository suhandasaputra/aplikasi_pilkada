/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.agentmanagement.database.AgentDBProc;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class AccountVerify {

    private static Logger log = Logger.getLogger(AccountVerify.class);

    public HashMap process(HashMap input) {
        AgentDBProc userdb = new AgentDBProc();
//        if (userdb.agentCheckPassword(input)) {
        if (userdb.accountVerify(input)) {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
        }
//        } else {
//            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodePasswordFailed);
//        }
        input.put(RuleNameParameter.resp_desc, "gagal upload gambar.");
        userdb = null;
        return input;
    }
}
