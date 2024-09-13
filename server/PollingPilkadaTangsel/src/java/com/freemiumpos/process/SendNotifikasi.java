package com.freemiumpos.process;

///*
// * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.balimuda.process;
//
//import com.ppob.database.DatabaseProcess;
//import com.ppob.entity.Tempmsg;
//import com.ppob.function.SendMailNotification;
//import com.ppob.parameter.FieldParameter;
//import com.ppob.parameter.RuleNameParameter;
//import java.io.UnsupportedEncodingException;
//import java.text.ParseException;
//import java.util.HashMap;
//import org.apache.log4j.Logger;
//
///**
// *
// * author MATAJARI MITRA SOLUSI
// */
//public class SendNotifikasi {
//
//    private static Logger log = Logger.getLogger(SendNotifikasi.class);
//    private Tempmsg tempmsg = new Tempmsg();
//
//    public HashMap process(String userlogin) throws ParseException, UnsupportedEncodingException {
//        DatabaseProcess dp = new DatabaseProcess();
//        HashMap input = new HashMap();
//        input.put(FieldParameter.userlogin, userlogin);
//        HashMap UserDetail = dp.getUserDetail(input);
////        System.out.println("trxdetail  : " + JsonProcess.generateJson(TrxDetail));
//        HashMap detail = new HashMap();
//        detail.put(FieldParameter.userlogin, UserDetail.get(FieldParameter.userlogin).toString());
//        detail.put(FieldParameter.username, UserDetail.get(FieldParameter.username).toString());
//        detail.put(FieldParameter.hpNumber, UserDetail.get(FieldParameter.hpNumber).toString());
//
//        SendMailNotification sendmail = new SendMailNotification();
//        String done = sendmail.sendNotifikasi(detail);
////        String done = "0000";
//        if (done.equals(RuleNameParameter.respcodeSuccess)) {
//            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
//            //            HashMap detail = (HashMap) result.get(FieldParameter.detail);
//        } else {
//            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
//            input.put(RuleNameParameter.resp_desc, "Send to E-Mail Failed.");
//        }
//        dp = null;
//        return input;
//
//    }
//}
