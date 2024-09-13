///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.freemiumpos.process;
//
//import com.freemium.database.DatabaseProcess;
//import com.freemium.function.StringFunction;
//import com.freemium.parameter.FieldParameter;
//import com.freemium.parameter.RuleNameParameter;
//import java.util.HashMap;
////import javax.json.JsonArray;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author MATAJARI MITRA SOLUSI
// */
//public class PaymentPOS {
//
//    DatabaseProcess dp = new DatabaseProcess();
//    private static final Logger log = Logger.getLogger(DatabaseProcess.class);
//
//    public HashMap process(HashMap inputMap) {
//        try {
//            //save sebelum kirim ke MOST
////            HashMap responseMOST = new HashMap();
//            HashMap checking = dp.CheckingStock(inputMap);
//            if (checking.get(FieldParameter.resp_code).toString().equals("0000")) {
//                inputMap.put(RuleNameParameter.order_number, dp.updateJurnalDay(inputMap));
//                if (inputMap.get(FieldParameter.payment_method).toString().equals("3")) {
//                    HashMap QrisParameters = dp.getQRIS_detail(inputMap);
////                    System.out.println("QRIS : " + QrisParameters);
//                    String QrisCode = FieldParameter.TAG00 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.format_indicator).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.format_indicator).toString()
//                            + FieldParameter.TAG01 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.initiation_method).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.initiation_method).toString()
//                            + FieldParameter.TAG26 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.mcc1).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.mcc1).toString()
//                            + FieldParameter.TAG27 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.mcc2).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.mcc2).toString()
//                            + FieldParameter.TAG52 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.merchant_category).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.merchant_category).toString()
//                            + FieldParameter.TAG53 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.currency).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.currency).toString()
//                            + FieldParameter.TAG54 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.amount).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.amount).toString()
//                            + FieldParameter.TAG58 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.country_code).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.country_code).toString()
//                            + FieldParameter.TAG59 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.merchant_name).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.merchant_name).toString()
//                            + FieldParameter.TAG60 + StringFunction.pad(String.valueOf(QrisParameters.get(FieldParameter.merchant_city).toString().length()), 2, "0", "Right") + QrisParameters.get(FieldParameter.merchant_city).toString();
//                    inputMap.put(FieldParameter.qris_code, QrisCode);
//                    if (dp.saveLogTrxHM(inputMap)) {
//                        dp.updateStock(inputMap);
//                        inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
//                        inputMap.put(RuleNameParameter.resp_code, "0000");
//                        dp.updateLogTrxHM(inputMap);
//                    } else {
//                        inputMap.put(RuleNameParameter.resp_code, "0001");
//                        inputMap.put(RuleNameParameter.resp_desc, "unknown error");
//                    }
//                } else if (inputMap.get(FieldParameter.payment_method).toString().equals("2")) {
//                    if (dp.saveLogTrxHM(inputMap)) {
//                        dp.updateStock(inputMap);
//                        inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
//                        inputMap.put(RuleNameParameter.resp_code, "0000");
//                        dp.updateLogTrxHM(inputMap);
//                    } else {
//                        inputMap.put(RuleNameParameter.resp_code, "0001");
//                        inputMap.put(RuleNameParameter.resp_desc, "unknown error");
//                    }
//                } else if (inputMap.get(FieldParameter.payment_method).toString().equals("1")) {
//                    if (dp.saveLogTrxHM(inputMap)) {
//                        dp.updateStock(inputMap);
//                        inputMap.put(RuleNameParameter.resp_desc, "SUCCESSFULL");
//                        inputMap.put(RuleNameParameter.resp_code, "0000");
//                        dp.updateLogTrxHM(inputMap);
//                    } else {
//                        inputMap.put(RuleNameParameter.resp_code, "0001");
//                        inputMap.put(RuleNameParameter.resp_desc, "unknown error");
//                    }
//                }
//
//            } else {
//                inputMap.put(RuleNameParameter.resp_code, "0080");
//                inputMap.put(RuleNameParameter.resp_desc, checking.get(FieldParameter.list).toString());
//            }
//
//            dp = null;
//        } catch (Exception e) {
//            System.out.println("ERROR : " + e);
//            e.printStackTrace();
//            inputMap.put(RuleNameParameter.resp_code, "1007");
//            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
//        }
//        return inputMap;
//    }
//}
