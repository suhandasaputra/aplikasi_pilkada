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
public class CreateConditionReportPolling {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(CreateConditionReportPolling.class);

    public HashMap process(HashMap inputMap) {
        try {
            String report_number = String.valueOf(Integer.parseInt(dp.GetReportNumber(inputMap.get(FieldParameter.userid).toString())) + 1);
            Base64 decoder = new Base64();
            byte[] imgBytes = decoder.decode(inputMap.get(FieldParameter.img_report).toString());
            CheckFileFolder.checkFolder(StaticParameter.path);
            CheckFileFolder.checkFolder(StaticParameter.path + StaticParameter.condition_report);
            File file = new File(StaticParameter.path + StaticParameter.condition_report + StaticParameter.slash + inputMap.get(FieldParameter.userid).toString().toLowerCase() + report_number + ".png");
            FileOutputStream osf = new FileOutputStream(file);
            osf.write(imgBytes);
            osf.flush();
            osf.close();
            String url = (StaticParameter.my_public_ip + StaticParameter.condition_report + StaticParameter.slash + inputMap.get(FieldParameter.userid).toString().toLowerCase() + report_number  + ".png").replace(" ", "%20");
            inputMap.put(FieldParameter.img_url, url);
            if (dp.CreateConditionReportPolling(inputMap)) {
                inputMap.put(RuleNameParameter.resp_code, "0000");
                inputMap.put(RuleNameParameter.resp_desc, "Success Create Report Condition");
            } else {
                inputMap.put(RuleNameParameter.resp_code, "0001");
                inputMap.put(RuleNameParameter.resp_desc, "Failed Create Report Condition");

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
