/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.agentmanagement.database.AgentDBProc;
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
public class ChangePin {

    private static Logger log = Logger.getLogger(ChangePin.class);

    public HashMap process(HashMap input) throws ParseException, UnsupportedEncodingException {
        AgentDBProc agentdb = new AgentDBProc();
        if (agentdb.changeAgentPin(input.get(FieldParameter.userlogin).toString(),
                input.get(FieldParameter.password).toString())) {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
            SendMailNotification sendmail = new SendMailNotification();
            sendmail.sendNotifikasi(input.get(FieldParameter.userlogin).toString());
        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
        }
        input.put(FieldParameter.resp_desc, "Gagal ubah pin, harap check kembali");
        agentdb = null;
        return input;
    }
}
