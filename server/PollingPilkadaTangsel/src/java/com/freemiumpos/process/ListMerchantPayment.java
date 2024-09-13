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
//import com.ppob.parameter.FieldParameter;
//import com.ppob.parameter.RuleNameParameter;
//import java.util.HashMap;
//import org.apache.log4j.Logger;
//
///**
// *
// * author MATAJARI MITRA SOLUSI
// */
//public class ListMerchantPayment {
//     private static Logger log = Logger.getLogger(ListMerchantPayment.class);
//     private Tempmsg tempmsg = new Tempmsg();
//
//    public HashMap process(HashMap input) {
//        DatabaseProcess dp = new DatabaseProcess();
//        HashMap result = dp.getListMerchantPayment();
//
//        if (result.get(FieldParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
//            input.put(FieldParameter.resp_code, RuleNameParameter.respcodeSuccess);
//            input.put(FieldParameter.listPayment, result.get("list"));
//        } else {
//            input.put(FieldParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
//        }
//
//        dp = null;
//        log.info(tempmsg.getMsgid() + " : send message to mobile: " + input);
//        return input;
//    }
//}
