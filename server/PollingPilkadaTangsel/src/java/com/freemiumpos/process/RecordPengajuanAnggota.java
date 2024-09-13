package com.freemiumpos.process;

///*
// * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.balimuda.process;
//
////import com.ppob.parameter.StaticParameterCuso;
//import com.ppob.parameter.StaticParameterCipas;
//import com.ppob.database.DatabaseProcess;
//import com.ppob.entity.Tempmsg;
//import com.ppob.function.JsonProcess;
//import com.ppob.function.SendHttpProcessCipas;
//import com.ppob.parameter.FieldParameter;
//import com.ppob.parameter.RuleNameParameter;
//import java.util.HashMap;
//import org.apache.log4j.Logger;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//
///**
// *
// * author MATAJARI MITRA SOLUSI
// */
//public class RecordPengajuanAnggota {
//
//    private static Logger log = Logger.getLogger(RecordPengajuanAnggota.class);
//    private Tempmsg tempmsg = new Tempmsg();
//
//    public HashMap process(HashMap input) {
//        DatabaseProcess dp = new DatabaseProcess();
//        try {
//            HashMap bodyReq = new HashMap();
//            bodyReq.put(StaticParameterCipas.nik, input.get(StaticParameterCipas.nik).toString());
//            bodyReq.put(StaticParameterCipas.userName, StaticParameterCipas.userName_value);
//            bodyReq.put(StaticParameterCipas.userPassword, StaticParameterCipas.userPassword_value);
//            String param = JsonProcess.generateJson(bodyReq);
//            log.info("reqCipas = " + StaticParameterCipas.url_cipas + " / " + param);
//            SendHttpProcessCipas http = new SendHttpProcessCipas();
//            String resultString = http.sendHttpRequest(StaticParameterCipas.url_cipas, param);
//            log.info("respCipas : " + resultString);
//            HashMap result = JsonProcess.decodeJson(resultString);
////            if (result.get("status").toString().equals("0")) {
//            Object object = null;
//            JSONArray arrayObj = null;
//            JSONParser jsonParser = new JSONParser();
//            object = jsonParser.parse(result.get(FieldParameter.data).toString());
//            arrayObj = (JSONArray) object;
//            dp.recordPengajuanAnggota(input, arrayObj.get(0).toString());
//            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
////            } else {
////                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
////            }
//        } catch (Exception e) {
//            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
//
////            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
//        }
//        dp = null;
//        return input;
//    }
//}
