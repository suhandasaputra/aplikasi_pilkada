/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.agentmanagement.database.AgentDBProc;
import com.freemium.function.SendHttpProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class ResetPin {

    SendHttpProcess http = new SendHttpProcess();
    private static Logger log = Logger.getLogger(ResetPin.class);

    public HashMap process(HashMap input) {

        AgentDBProc agentdb = new AgentDBProc();
            if (agentdb.createAgentPin(input.get(FieldParameter.userlogin).toString(), input.get(FieldParameter.pin).toString())) {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                input.put(FieldParameter.resp_desc, "Gagal create pin, harap check kembali");

            }
        agentdb = null;
        return input;
    }
}
