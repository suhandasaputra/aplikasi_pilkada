/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.function.CheckFileFolder;
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
 * @author herrysuganda
 */
public class UpdateProductPriceAndStock {

//    DatabaseProcess dp = new DatabaseProcess();
//    private static final Logger log = Logger.getLogger(DatabaseProcess.class);
//
//    public HashMap process(HashMap inputMap) {
//        try {
//            String fullid = inputMap.get(FieldParameter.fullid).toString();
////            String brand = fullid.substring(0, fullid.length() - 2);
////            String outlet = fullid.substring(fullid.length() - 2, fullid.length());
//            if (dp.UpdatePriceStockOutletProduct(inputMap)) {
////                String Category = dp.getCategoryByProductCode(inputMap);
////                inputMap.get(FieldParameter.product_code).toString()
//                inputMap.put(FieldParameter.category, dp.getCategoryByProductCode(inputMap));
//
//                HashMap result = dp.getListOutletProductManage(inputMap.get(FieldParameter.fullid).toString(), inputMap.get(FieldParameter.category).toString());
//                inputMap.put(FieldParameter.list, result.get("list"));
//                inputMap.put(FieldParameter.resp_code, "0000");
//                inputMap.put(FieldParameter.resp_desc, "Successfull.");
//            } else {
//                inputMap.put(FieldParameter.resp_code, "0001");
//                inputMap.put(FieldParameter.resp_desc, "Failed Update Price/Stock.");
//
//            }
//            dp = null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            inputMap.put(FieldParameter.resp_code, "1007");
//            inputMap.put(FieldParameter.resp_desc, "Message request tidak sesuai");
//        }
//        return inputMap;
//    }
}
