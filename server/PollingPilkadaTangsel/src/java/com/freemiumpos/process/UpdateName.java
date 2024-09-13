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
public class UpdateName {

    SendHttpProcess http = new SendHttpProcess();
    private static Logger log = Logger.getLogger(UpdateName.class);
    private AgentDBProc db = new AgentDBProc();

    public HashMap process(HashMap input) throws ParseException, UnsupportedEncodingException {
        if (db.CheckPassword(input)) {
            String userlogin = input.get(FieldParameter.userlogin).toString();
            if (db.changeProfile(input)) {
                input.put(FieldParameter.resp_code, RuleNameParameter.respcodeSuccess);
                input.put(RuleNameParameter.resp_desc, "Successful");
                log.info("user  (" + userlogin + ") sukses melakukan perubahan profile.");
                SendMailNotification sendmail = new SendMailNotification();
                sendmail.sendNotifikasi(userlogin);
            } else {
                input.put(FieldParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                input.put(FieldParameter.resp_desc, "Update profil gagal");
            }
        } else {
            input.put(FieldParameter.resp_code, RuleNameParameter.respcodePasswordFailed);
            input.put(FieldParameter.resp_desc, "Password salah, mohon teliti kembali");
        }
        return input;
    }
}
