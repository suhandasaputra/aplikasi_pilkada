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
public class PaymentPOSOrder {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            //save sebelum kirim ke MOST
//            HashMap responseMOST = new HashMap();
            HashMap CheckDevice = dp.getDeviceDetail(inputMap);
//            System.out.println("MOST PAMETERS : " + JsonProcess.generateJson(CheckDevice));
            HashMap checking = dp.CheckingUpdateStock(inputMap);
            HashMap checking2 = dp.CheckingStock(checking);
            if (checking2.get(FieldParameter.resp_code).toString().equals("0000")) {
                dp.updateStock(checking);

                inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
                inputMap.put(RuleNameParameter.resp_code, "0000");

                if (inputMap.get(FieldParameter.payment_method).toString().equals("3")) {
                    HashMap QrisParameters = dp.getQRIS_detail(inputMap);
                    QrisParameters.put(FieldParameter.procCode, "01");
                    QrisParameters.remove(FieldParameter.resp_code);
                    QrisParameters.remove(FieldParameter.resp_desc);
                    SendHttpProcess http = new SendHttpProcess();
                    System.out.println("POS REQuest QRIS :  " + FieldParameter.url_qris_server + "?req=" + JsonProcess.generateJson(QrisParameters));
                    String ResponseQrisServer = http.sendHttpRequest(FieldParameter.url_qris_server, "req=" + JsonProcess.generateJson(QrisParameters));
                    System.out.println("POS RESPONSE QRIS :  " + ResponseQrisServer);
                    HashMap QrisResp = JsonProcess.decodeJson(ResponseQrisServer);
                    if (QrisResp.get(FieldParameter.resp_code).toString().equals("0000")) {
                        inputMap.put(FieldParameter.qris_code, QrisResp.get(FieldParameter.qris_code).toString());
                        if (dp.PaymentOrder(inputMap, "0")) {
                            inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
                            inputMap.put(RuleNameParameter.resp_code, "0000");
                        } else {
                            inputMap.put(RuleNameParameter.resp_code, "0098");
                            inputMap.put(RuleNameParameter.resp_desc, "Failed.");
                        }
                    } else {

                        inputMap.put(RuleNameParameter.resp_code, "0099");
                        inputMap.put(RuleNameParameter.resp_desc, "gagal generate QRIS");
                    }
                } else if (inputMap.get(FieldParameter.payment_method).toString().equals("2")) {
                    inputMap.put(FieldParameter.qris_code, "-");
                    if (dp.PaymentOrder(inputMap, "1")) {
                        dp.updateTable(inputMap, "1", inputMap.get(FieldParameter.table_number).toString());
                        dp.RecordItemSalesDetail(inputMap);
                        dp.updateSummaryReportDay(inputMap);
                        dp.updateJurnalTrxCustomer(inputMap);
                        inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
                        inputMap.put(RuleNameParameter.resp_code, "0000");
                        if (CheckDevice.get(FieldParameter.resp_code).equals("0000")) {
                            CheckDevice.put(MostParameter.PAYMENT_METHODE, "1");
                            CheckDevice.put(MostParameter.CARD_NUMBER, inputMap.get(FieldParameter.cardNumber).toString());
                            dp.SaveTaxReportTrx(inputMap, CheckDevice);

                            String reqMsg = JsonProcess.generateJson(CheckDevice);
                            System.out.println("Send to MOST : " + StaticParameter.url_most + reqMsg);
                            String respMsg = new SendHttpProcess().sendHttpRequest(StaticParameter.url_most, reqMsg);
                            System.out.println("Resp from MOST : " + respMsg);
                            HashMap respMost = JsonProcess.decodeJson(respMsg);
                            dp.UpdateTaxReportTrx(inputMap, respMost);

                        }
                    } else {
                        inputMap.put(RuleNameParameter.resp_code, "0001");
                        inputMap.put(RuleNameParameter.resp_desc, "Failed.");
                    }
                } else if (inputMap.get(FieldParameter.payment_method).toString().equals("1")) {

                    inputMap.put(FieldParameter.qris_code, "-");
                    if (dp.PaymentOrder(inputMap, "1")) {
                        dp.updateTable(inputMap, "1", inputMap.get(FieldParameter.table_number).toString());
                        dp.RecordItemSalesDetail(inputMap);
                        dp.updateSummaryReportDay(inputMap);
                        dp.updateJurnalTrxCustomer(inputMap);
                        inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
                        inputMap.put(RuleNameParameter.resp_code, "0000");
                        if (CheckDevice.get(FieldParameter.resp_code).equals("0000")) {
                            CheckDevice.put(MostParameter.PAYMENT_METHODE, "0");

                            dp.SaveTaxReportTrx(inputMap, CheckDevice);
                            String reqMsg = JsonProcess.generateJson(CheckDevice);
                            System.out.println("Send to MOST : " + StaticParameter.url_most + reqMsg);
                            String respMsg = new SendHttpProcess().sendHttpRequest(StaticParameter.url_most, reqMsg);
                            System.out.println("Resp from MOST : " + respMsg);
                            HashMap respMost = JsonProcess.decodeJson(respMsg);
                            dp.UpdateTaxReportTrx(inputMap, respMost);
                        }
                    } else {
                        inputMap.put(RuleNameParameter.resp_code, "0001");
                        inputMap.put(RuleNameParameter.resp_desc, "Failed.");
                    }
                }

            } else {
                inputMap.put(RuleNameParameter.resp_code, "0080");
                inputMap.put(RuleNameParameter.resp_desc, checking2.get(FieldParameter.list).toString());

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
