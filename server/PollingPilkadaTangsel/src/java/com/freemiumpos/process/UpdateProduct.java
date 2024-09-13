/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.function.CheckFileFolder;
import com.freemium.function.JsonProcess;
import com.freemium.function.StringFunction;
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
public class UpdateProduct {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            String fullid = inputMap.get(FieldParameter.fullid).toString();
            String brand = fullid.substring(0, fullid.length() - 2);
            if (!inputMap.get(FieldParameter.image).toString().equals("-")) {
                Base64 decoder = new Base64();
                byte[] imgBytes = decoder.decode(inputMap.get(FieldParameter.image).toString());
                CheckFileFolder.checkFolder(StaticParameter.path);
                CheckFileFolder.checkFolder(StaticParameter.path + brand);
                CheckFileFolder.checkFolder(StaticParameter.path + brand + StaticParameter.slash + inputMap.get(FieldParameter.category).toString());
                FileOutputStream osf = new FileOutputStream(new File(StaticParameter.path + brand + StaticParameter.slash + inputMap.get(FieldParameter.category).toString() + StaticParameter.slash + inputMap.get(FieldParameter.product_code).toString() + ".png"));
                osf.write(imgBytes);
                osf.flush();
                osf.close();
                String url = (StaticParameter.my_public_ip + brand + StaticParameter.slash1 + inputMap.get(FieldParameter.category).toString() + StaticParameter.slash1 + inputMap.get(FieldParameter.product_code).toString() + ".png").replace(" ", "%20");
                inputMap.put(FieldParameter.img_url, url);
                if (dp.UpdateProduct(inputMap)) {
                    HashMap result = dp.getListProduct(inputMap.get(FieldParameter.fullid).toString(), inputMap.get(FieldParameter.category).toString());
                    inputMap.put(FieldParameter.list, result.get("list"));
                    inputMap.put(FieldParameter.resp_code, "0000");
                    inputMap.put(FieldParameter.resp_desc, "Successfull.");
                } else {
                    inputMap.put(FieldParameter.resp_code, "0001");
                    inputMap.put(FieldParameter.resp_desc, "Failed.");

                }
            } else {
                inputMap.put(FieldParameter.img_url, "-");
                if (dp.UpdateProduct(inputMap)) {
                    HashMap result = dp.getListProduct(inputMap.get(FieldParameter.fullid).toString(), inputMap.get(FieldParameter.category).toString());
                    inputMap.put(FieldParameter.list, result.get("list"));
                    inputMap.put(FieldParameter.resp_code, "0000");
                    inputMap.put(FieldParameter.resp_desc, "Successfull.");
                } else {
                    inputMap.put(FieldParameter.resp_code, "0001");
                    inputMap.put(FieldParameter.resp_desc, "Failed.");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            inputMap.put(FieldParameter.resp_code, "1007");
            inputMap.put(FieldParameter.resp_desc, "Failed.");
        }
        dp.CreateLogManage(inputMap, JsonProcess.generateJson(inputMap), inputMap.get(RuleNameParameter.resp_code).toString(), this.getClass().getSimpleName());
        dp = null;

        return inputMap;
    }
}
