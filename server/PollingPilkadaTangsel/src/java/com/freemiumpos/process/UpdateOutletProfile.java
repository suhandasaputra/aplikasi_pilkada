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
public class UpdateOutletProfile {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            //save sebelum kirim ke MOST
//            HashMap responseMOST = new HashMap();
            String fullid = inputMap.get(FieldParameter.fullid).toString();
            String brand = fullid.substring(0, fullid.length() - 2);
            if (!inputMap.get(FieldParameter.header_receipt).toString().equals("-")) {
                Base64 decoder = new Base64();
                byte[] imgBytes = decoder.decode(inputMap.get(FieldParameter.header_receipt).toString());
                CheckFileFolder.checkFolder(StaticParameter.path);
                CheckFileFolder.checkFolder(StaticParameter.path + brand);
                CheckFileFolder.checkFolder(StaticParameter.path + brand + "/outlet_profile");
//            CheckFileFolder.checkFolder(StaticParameter.path + brand + "/outlet_profile" + StaticParameter.slash + inputMap.get(FieldParameter.category).toString());
                FileOutputStream osf = new FileOutputStream(new File(StaticParameter.path + brand + StaticParameter.slash + "outlet_profile" + StaticParameter.slash + fullid + "header.png"));
                osf.write(imgBytes);
                osf.flush();
                osf.close();
                String url = (StaticParameter.my_public_ip + brand + StaticParameter.slash1 + "outlet_profile" + StaticParameter.slash1 + fullid + "header.png").replace(" ", "%20");
                inputMap.put(FieldParameter.header_receipt, url);

                if (dp.UpdateOutletProfile(inputMap)) {
                    inputMap.put(RuleNameParameter.resp_code, "0000");
                } else {
                    inputMap.put(RuleNameParameter.resp_code, "0001");
                    inputMap.put(RuleNameParameter.resp_desc, "Failed.");

                }
            } else {
                if (dp.UpdateOutletProfile2(inputMap)) {
                    inputMap.put(RuleNameParameter.resp_code, "0000");
                } else {
                    inputMap.put(RuleNameParameter.resp_code, "0001");
                    inputMap.put(RuleNameParameter.resp_desc, "Failed.");

                }
            }

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
        }
        dp.CreateLogManage(inputMap, JsonProcess.generateJson(inputMap), inputMap.get(RuleNameParameter.resp_code).toString(), this.getClass().getSimpleName());
        dp = null;

        return inputMap;
    }
}
