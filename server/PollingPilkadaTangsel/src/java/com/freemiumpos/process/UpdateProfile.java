/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.agentmanagement.database.AgentDBProc;
//import com.matajari.param.FieldParameter;
//import com.matajari.param.RuleNameParameter;
//import com.mms.agent.database.AgentDBProc;
import com.freemium.database.DatabaseProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class UpdateProfile {

    private static Logger log = Logger.getLogger(UpdateProfile.class);

    public HashMap process(HashMap input) {
        DatabaseProcess dp = new DatabaseProcess();
        AgentDBProc userdb = new AgentDBProc();
        if (userdb.agentCheckPassword(input)) {
            if (userdb.changeProfile(input)) {
                input.put(FieldParameter.resp_code, RuleNameParameter.respcodeSuccess);
                input.put(FieldParameter.resp_desc, "Successful");
            } else {
                input.put(FieldParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                input.put(FieldParameter.resp_desc, "Failed");
            }
        } else {
            input.put(FieldParameter.resp_code, RuleNameParameter.respcodePasswordFailed);
            input.put(FieldParameter.resp_desc, "Password salah, mohon teliti kembali");
        }
        userdb = null;
        return input;
    }
}