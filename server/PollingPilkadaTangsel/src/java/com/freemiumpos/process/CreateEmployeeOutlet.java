/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.function.ChangePasswordMail;
import com.freemium.function.CheckEmailValidOrNot;
import com.freemium.function.EmailNewUser;
import com.freemium.function.FunctionSupportMB;
import com.freemium.function.JsonProcess;
import com.freemium.function.StringFunction;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
//import javax.json.JsonArray;
import org.apache.log4j.Logger;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class CreateEmployeeOutlet {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            //save sebelum kirim ke MOST
//            HashMap responseMOST = new HashMap();
            if (CheckEmailValidOrNot.isValid(inputMap.get(FieldParameter.email).toString())) {
                HashMap check = new HashMap();
                check.put(FieldParameter.userlogin, inputMap.get(FieldParameter.email).toString());
                check = dp.getEmployeeDetail(check);
                if (!check.get(FieldParameter.resp_code).toString().equals("0000")) {
                    String newPass = StringFunction.generatePassword(8);
                    inputMap.put(FieldParameter.password, FunctionSupportMB.encryptOneWayDataSave(newPass));
                    inputMap.put(FieldParameter.pure, newPass);
                    inputMap.put(FieldParameter.userlogin, inputMap.get(FieldParameter.email).toString());
                    if (dp.CreateNewEmployee(inputMap)) {

                        EmailNewUser sendmail = new EmailNewUser();
                        String done = sendmail.notif(inputMap);

                        inputMap.put(RuleNameParameter.resp_code, "0000");
                    } else {
                        inputMap.put(RuleNameParameter.resp_code, "0001");
                        inputMap.put(RuleNameParameter.resp_desc, "Failed.");

                    }
                } else {
                    inputMap.put(RuleNameParameter.resp_code, "0004");
                    inputMap.put(RuleNameParameter.resp_desc, "The email you entered is already registered.");
                }
            } else {
                inputMap.put(RuleNameParameter.resp_code, "0005");
                inputMap.put(RuleNameParameter.resp_desc, "The email you entered is not valid.");
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Failed.");
//            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
        }
        inputMap.remove(FieldParameter.pure);
        dp.CreateLogManage(inputMap, JsonProcess.generateJson(inputMap), inputMap.get(RuleNameParameter.resp_code).toString(), this.getClass().getSimpleName());
        dp = null;

        return inputMap;
    }
}
