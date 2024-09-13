/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.function.CheckFileFolder;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import org.apache.commons.codec.binary.Base64;
//import javax.json.JsonArray;
import org.apache.log4j.Logger;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class UpdateCandidate {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(UpdateCandidate.class);

    public HashMap process(HashMap inputMap) {
        try {
            
            Base64 decoder = new Base64();
            byte[] imgBytes = decoder.decode(inputMap.get(FieldParameter.img_profile).toString());
            CheckFileFolder.checkFolder(StaticParameter.path);
            CheckFileFolder.checkFolder(StaticParameter.path + StaticParameter.candidate);
            File file  = new File(StaticParameter.path + StaticParameter.candidate +  StaticParameter.slash + inputMap.get(FieldParameter.candidate_name).toString().toLowerCase() + ".png");
            FileOutputStream osf = new FileOutputStream(file);
            osf.write(imgBytes);
            osf.flush();
            osf.close();
            String url = (StaticParameter.my_public_ip + StaticParameter.candidate + StaticParameter.slash  + inputMap.get(FieldParameter.candidate_name).toString().toLowerCase() +".png").replace(" ", "%20");
            inputMap.put(FieldParameter.img_url, url);
            inputMap = dp.UpdateCandidatePolling(inputMap);

            if (inputMap.get(FieldParameter.resp_code).equals("0000")) {

                inputMap.put(RuleNameParameter.resp_desc, "Success Update Candidate");
            } else {
                inputMap.put(RuleNameParameter.resp_code, "0001");
                inputMap.put(RuleNameParameter.resp_desc, "Failed Update Candidate");

            }

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Failed.");
//            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
        }

        return inputMap;
    }
}
