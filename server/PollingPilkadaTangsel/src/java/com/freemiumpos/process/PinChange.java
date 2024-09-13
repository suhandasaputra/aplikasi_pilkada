/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.agentmanagement.database.AgentDBProc;
import com.freemium.database.DatabaseProcess;
import com.freemium.function.ChangePasswordMail;
import com.freemium.function.JsonProcess;
import com.freemium.function.SendHttpProcess;
import com.freemium.function.SendMailNotification;
import com.freemium.function.StringFunction;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import pri.vate.klas.function.Func5;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class PinChange {

    SendHttpProcess http = new SendHttpProcess();
    private static Logger log = Logger.getLogger(PinChange.class);
    DatabaseProcess dp = new DatabaseProcess();

    public HashMap process(HashMap inputMap) throws ParseException, UnsupportedEncodingException {
        try {
            HashMap result = dp.getEmployeeDetail(inputMap);
            if (result.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                if (result.get(FieldParameter.userpassword).equals(inputMap.get(FieldParameter.password))) {

                    if (dp.UpdateNewPassword(inputMap, inputMap.get(FieldParameter.new_password).toString())) {
                        inputMap.put(RuleNameParameter.resp_code, "0000");

                    } else {
                        inputMap.put(RuleNameParameter.resp_code, "0001");
                        inputMap.put(RuleNameParameter.resp_desc, "Failed.");

                    }
                }
            } else {
                inputMap.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                inputMap.put(RuleNameParameter.resp_desc, "User Tidak dikenali.");
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
        }
        inputMap.remove(FieldParameter.pure);
        dp.CreateLogManage(inputMap, JsonProcess.generateJson(inputMap), inputMap.get(RuleNameParameter.resp_code).toString(), this.getClass().getSimpleName());
        dp = null;

        return inputMap;
    }
}
