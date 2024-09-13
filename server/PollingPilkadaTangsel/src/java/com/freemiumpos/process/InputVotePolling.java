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
public class InputVotePolling {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(InputVotePolling.class);

    public HashMap process(HashMap inputMap) {
        try {
            HashMap resultdistrict = dp.GetDistrict(inputMap);
            Base64 decoder = new Base64();
            byte[] imgBytes = decoder.decode(inputMap.get(FieldParameter.img_vote).toString());
            CheckFileFolder.checkFolder(StaticParameter.path);
            CheckFileFolder.checkFolder(StaticParameter.path + resultdistrict.get(FieldParameter.district));
            CheckFileFolder.checkFolder(StaticParameter.path + resultdistrict.get(FieldParameter.district) + StaticParameter.slash + resultdistrict.get(FieldParameter.subdistrict).toString());
            CheckFileFolder.checkFolder(StaticParameter.path + resultdistrict.get(FieldParameter.district) + StaticParameter.slash + resultdistrict.get(FieldParameter.subdistrict).toString() + StaticParameter.slash + inputMap.get(FieldParameter.voting_place).toString());
            File file  = new File(StaticParameter.path + resultdistrict.get(FieldParameter.district) + StaticParameter.slash + resultdistrict.get(FieldParameter.subdistrict).toString() + StaticParameter.slash + inputMap.get(FieldParameter.voting_place).toString() + StaticParameter.slash + "img_vote_" + inputMap.get(FieldParameter.voting_place).toString() + ".png");
            FileOutputStream osf = new FileOutputStream(file);
            osf.write(imgBytes);
            osf.flush();
            osf.close();
            String url = (StaticParameter.my_public_ip + resultdistrict.get(FieldParameter.district) + StaticParameter.slash + resultdistrict.get(FieldParameter.subdistrict).toString() + StaticParameter.slash + inputMap.get(FieldParameter.voting_place).toString() + StaticParameter.slash + "img_vote_" + inputMap.get(FieldParameter.voting_place).toString() + ".png").replace(" ", "%20");
            inputMap.put(FieldParameter.img_url, url);
            inputMap = dp.InputVotingPolling(inputMap);

            if (inputMap.get(FieldParameter.resp_code).equals("0000")) {

                inputMap.put(RuleNameParameter.resp_desc, "Success Input Vote");
            } else {
                inputMap.put(RuleNameParameter.resp_code, "0001");
                inputMap.put(RuleNameParameter.resp_desc, "Failed Input Vote");

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
