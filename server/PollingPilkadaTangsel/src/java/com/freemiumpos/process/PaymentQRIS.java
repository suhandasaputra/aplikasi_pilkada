/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.function.JsonProcess;
import com.freemium.function.SendHttpProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.MostParameter;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameter;
import java.util.HashMap;
//import javax.json.JsonArray;
import org.apache.log4j.Logger;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class PaymentQRIS {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            //save sebelum kirim ke MOST
//            HashMap responseMOST = new HashMap();
            HashMap detailQRIS = dp.GetDetailQRISTransaction(inputMap);
            inputMap.put(FieldParameter.noresi, detailQRIS.get(FieldParameter.noresi));
            inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
            inputMap.put(RuleNameParameter.resp_code, "0000");
            HashMap CheckDevice = dp.getDeviceDetail(detailQRIS);

//            System.out.println("inputMap payment qris : " + detailQRIS);
//            System.out.println("detail : " + detailQRIS.get(FieldParameter.status).toString());
            if (detailQRIS.get(FieldParameter.status).toString().equals("0")) {

                if (dp.PaymentQRIS(inputMap, "1")) {
                    dp.updateTable(detailQRIS, "1", detailQRIS.get(FieldParameter.table_number).toString());
                    dp.RecordItemSalesDetail(detailQRIS);
                    dp.updateSummaryReportDay(detailQRIS);
                    dp.updateJurnalTrxCustomer(detailQRIS);
                    if (CheckDevice.get(FieldParameter.resp_code).equals("0000")) {
                        CheckDevice.put(MostParameter.PAYMENT_METHODE, "1");
//                        CheckDevice.put(MostParameter.CARD_NUMBER, inputMap.get(FieldParameter.cardNumber).toString());
                        String reqMsg = JsonProcess.generateJson(CheckDevice);
                        String respMsg = new SendHttpProcess().sendHttpRequest(StaticParameter.url_most, reqMsg);
                    }
                    inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
                    inputMap.put(RuleNameParameter.resp_code, "0000");
                } else {
                    inputMap.put(RuleNameParameter.resp_code, "0001");
                    inputMap.put(RuleNameParameter.resp_desc, "Failed.");
                }
            } else {

                inputMap.put(RuleNameParameter.resp_code, "3333");
                inputMap.put(RuleNameParameter.resp_desc, "Tagihan Sudah Lunas.");

            }

            dp = null;
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_desc, "Message request tidak sesuai");
        }
        return inputMap;
    }
//
//    public static void main(String[] args) {
//        String a[] = "1,5,3,7,4".split(",");
//          Arrays.sort(a);
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i]);
//        }
//        System.out.println("");
//      
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i]);
//        }
//    }
}
