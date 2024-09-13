package com.freemiumpos.process;

///*
// * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.balimuda.process;
//
//import com.ppob.database.DatabaseProcess;
//import com.ppob.entity.Messagein;
//import com.balimuda.function.SendHttpProcess;
//import com.ppob.function.JsonProcess;
//import com.ppob.function.StringFunction;
//import com.ppob.parameter.FieldParameter;
//import com.ppob.parameter.ProcessingCode;
//import com.ppob.parameter.RuleNameParameter;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.HashMap;
//
///**
// *
// * author MATAJARI MITRA SOLUSI
// */
//public class PasswordAuthentication {
//
//    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
//    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
//    private DatabaseProcess dp = new DatabaseProcess();
//    Messagein msgin = new Messagein();
//
//    public HashMap process(HashMap input) throws Exception {
//        System.out.println("Masuk ke Inquiry Authentication");
//        msgin.setInput(input);
//        msgin.getInput().put(RuleNameParameter.proccode, ProcessingCode.authProccode);
//        msgin.setMsgid(timestamp + msgin.getInput().get(FieldParameter.username).toString() + msgin.getInput().get(FieldParameter.refNumber).toString());
//        SendHttpProcess http = new SendHttpProcess();
//        String code = StringFunction.getCurrentTimemmHHss();
//        String message = "username=" + "lenamagdalena"
//                + "&password=" + "gFCnHKdF4l"
//                + "&cmd=" + "send"
//                + "&text=" + "Nomor verifikasi anda adalah : " + code
//                + "&phone=" + msgin.getInput().get(FieldParameter.hpNumber)
//                + "&unicode=" + "0";
//        String resp = http.sendHttpRequest("https://www.textmagic.com/app/api?", message);
//        HashMap response = JsonProcess.decodeJson(resp);
//        System.out.println("Response : " + response);
//        if (response.get("sent_text").toString() != "" || response.get("sent_text").toString() != null) {
//            System.out.println("Masuk ke else if sukses");
//            dp.authCodeInput((String) msgin.getInput().get(FieldParameter.hpNumber), code);
//            msgin.getInput().put(RuleNameParameter.resp_code, "0000");
//            msgin.getInput().put(RuleNameParameter.resp_desc, "Successful");
//        } else {
//            if (response.get("error_code").toString().equals("9")) {
//                msgin.getInput().put(RuleNameParameter.resp_code, "0099");
//                msgin.getInput().put(RuleNameParameter.resp_desc, "Salah nomor handphone");
//            } else if (response.get("error_code").toString().equals("5")) {
//                msgin.getInput().put(RuleNameParameter.resp_code, "0005");
//                msgin.getInput().put(RuleNameParameter.resp_desc, "0005");
//            } else if (response.get("error_code").toString().equals("2")) {
//                dp.authCodeInput((String) msgin.getInput().get(FieldParameter.hpNumber), code);
//                msgin.getInput().put(RuleNameParameter.resp_code, "11110");
//                msgin.getInput().put(RuleNameParameter.resp_desc, "Saldo habis");
//            }
//        }
//        return msgin.getInput();
//
//    }
//}
