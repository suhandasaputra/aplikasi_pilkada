/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.parameter.StaticParameterCuso;
import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Tempmsg;
import com.freemium.function.JsonProcess;
import com.freemium.function.SendHttpProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class CorpActivation {

    private static Logger log = Logger.getLogger(CorpActivation.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
//        System.out.println("masuk corp activation");
        DatabaseProcess dp = new DatabaseProcess();
        try {

            String param = StaticParameterCuso.token + "=" + StaticParameterCuso.token_value
                    + StaticParameterCuso.and + StaticParameterCuso.cuid + "=" + input.get(StaticParameterCuso.cuid).toString()
                    + StaticParameterCuso.and + StaticParameterCuso.username + "=" + input.get(StaticParameterCuso.username).toString()
                    + StaticParameterCuso.and + StaticParameterCuso.password + "=" + input.get(StaticParameterCuso.password_cuso).toString();
            String param2 = StaticParameterCuso.token + "=" + StaticParameterCuso.token_value
                    + StaticParameterCuso.and + StaticParameterCuso.cuid + "=" + input.get(StaticParameterCuso.cuid).toString()
                    + StaticParameterCuso.and + StaticParameterCuso.username + "=" + input.get(StaticParameterCuso.username).toString()
                    + StaticParameterCuso.and + StaticParameterCuso.password + "=xxxxxxx";
            log.info("reqCuso = " + StaticParameterCuso.url_cuso + StaticParameterCuso.api_login + "?" + param2);
            SendHttpProcess http = new SendHttpProcess();
            String resultString = http.sendHttpRequest(StaticParameterCuso.url_cuso + StaticParameterCuso.api_login + "?" + param.replace(" ", "%20"), "");
            log.info("respCuso : " + resultString);
            HashMap result = JsonProcess.decodeJson(resultString);
            if (result.get("success").toString().equals("1")) {
                HashMap check = dp.getHistCorp(input.get(FieldParameter.userlogin).toString(), input.get(StaticParameterCuso.cuid).toString(),input.get(StaticParameterCuso.username).toString());
                if (!check.get(FieldParameter.resp_code).toString().equals("0000")) {
                    input = dp.CorpActivation(input);
                } else {
                    System.out.println(" masuk sini harusnya??");
                    input = dp.CorpReactivation(input);
                    input.put(FieldParameter.resp_code, "0000");
                    input.put(RuleNameParameter.resp_desc, "Success, user sudah pernah diaktifkan");
                }
            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
                input.put(RuleNameParameter.resp_desc, result.get("message").toString());
            }

        } catch (Exception e) {

            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
            input.put(RuleNameParameter.resp_desc, "Activation Fail");
        }
        dp = null;
        return input;
    }
}
