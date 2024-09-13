/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.agentmanagement.database.AgentDBProc;
import com.freemium.function.SendHttpProcess;
import com.freemium.function.SendMailNotification;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class ShowImageProfile {

    SendHttpProcess http = new SendHttpProcess();
    private static Logger log = Logger.getLogger(ShowImageProfile.class);
    private AgentDBProc db = new AgentDBProc();

    public HashMap process(HashMap input) throws ParseException, UnsupportedEncodingException {
           HashMap result = db.getImgProfile(input);
                if (result.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                    input.put(FieldParameter.img_profile, result.get(FieldParameter.img_profile));
                    input.put(FieldParameter.resp_code, RuleNameParameter.respcodeSuccess);
                    input.put(RuleNameParameter.resp_desc, "Successful");
                } else {
                    input.put(FieldParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                    input.put(FieldParameter.resp_desc, "Load Foto Profile gagal.");
                }
        return input;
    }
}
