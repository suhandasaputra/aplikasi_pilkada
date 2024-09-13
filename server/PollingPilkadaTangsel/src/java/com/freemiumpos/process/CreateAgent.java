/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.agentmanagement.database.AgentDBProc;
import com.freemium.database.DatabaseProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class CreateAgent {

    private static Logger log = Logger.getLogger(CreateAgent.class);
    private AgentDBProc db = new AgentDBProc();

    public HashMap process(HashMap input) {
  
                DatabaseProcess dp2 = new DatabaseProcess();
                HashMap version = dp2.getVersion(input);
                if (String.valueOf(version.get(FieldParameter.versionCode)).equals(input.get(FieldParameter.versionCode).toString())) {
                    if (!input.containsKey(FieldParameter.refer)) {
                        input.put(FieldParameter.refer, "0");
                    }
                    input = db.createAgent(input);
                    if (input.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                        input.put(FieldParameter.resp_code, "0000");
                        input.put(FieldParameter.resp_desc, "Successful");
                    } else {
                        input.put(FieldParameter.resp_code, "0001");
                        input.put(FieldParameter.resp_desc, "no handphone sudah pernah didaftarkan, silahkan masuk melalui menu login");
                    }
                } else {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeVersionNotMatch);
                    input.put(RuleNameParameter.resp_desc, "Aplikasi membutuhkan update terbaru");
                    input.put(RuleNameParameter.url_update_aps, RuleNameParameter.url_update_value);
                }
        return input;
    }
}
