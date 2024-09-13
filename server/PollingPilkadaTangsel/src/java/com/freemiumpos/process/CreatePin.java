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
public class CreatePin {

    SendHttpProcess http = new SendHttpProcess();
    private static Logger log = Logger.getLogger(CreatePin.class);

    public HashMap process(HashMap input) {

        AgentDBProc agentdb = new AgentDBProc();
//        if (agentdb.createVA(input)) {

            if (agentdb.createAgentPin(input.get(FieldParameter.userlogin).toString(), input.get(FieldParameter.pin).toString())) {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
//            //process create VA BNI
//            HashMap req = new HashMap();
//            req.put(FieldParameter.userlogin, input.get(FieldParameter.userlogin).toString());
//            req.put(RuleNameParameter.rrn, StringFunction.getGMTCurrentDateMMDDHHMMSS() + String.valueOf(System.currentTimeMillis() % 1000).substring(0, 2));
//            req.put(RuleNameParameter.req_datetime, StringFunction.getCurrentDateYYYYMMDDHHMMSS());
//            req.put(RuleNameParameter.amount, "0");
//            String reqMsg = JsonProcess.generateJson(req);
//            String respMsg = new SendHttpProcess().sendHttpRequest("http://103.56.205.219:8080/BNI_VA/CreateInvoice", reqMsg);
//            log.info("\n" + req.get(FieldParameter.rrn).toString() + " : (BNI VA) Message incoming : " + respMsg + "\n");
//            //process create VA BNI

            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                input.put(FieldParameter.resp_desc, "Gagal create pin, harap check kembali");

            }
//        } else {
//            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
//            input.put(FieldParameter.resp_desc, "Gagal create VA Number, harap hubungi Call Center kami");
//
//        }
        agentdb = null;
        return input;
    }
}
