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
public class ChangePassword {

    private static Logger log = Logger.getLogger(ChangePassword.class);

    public HashMap process(HashMap input) {
        AgentDBProc userdb = new AgentDBProc();
        if (userdb.agentCheckPassword(input)) {
            if (userdb.agentCheckHistoryPassword(input)) {
                if (userdb.changeAgentPassword(input.get(FieldParameter.userlogin).toString(),
                    input.get(FieldParameter.password).toString())) {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);

                } else {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                }

            } else {
//                System.out.println("harus gagal disini bos");
//                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeupdatefail);
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                input.put(RuleNameParameter.resp_desc, "gagal ubah password, password sudah pernah digunakan");
            }
        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodePasswordFailed);
            input.put(RuleNameParameter.resp_desc, "gagal ubah password, harap check kembali");
        }
        userdb = null;
        return input;
    }
}
