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
//public class ListVoucher {
//     private static Logger log = Logger.getLogger(ListVoucher.class);
//     private Tempmsg tempmsg = new Tempmsg();
//
//    public HashMap process(HashMap input) {
//        DatabaseProcess dp = new DatabaseProcess();
//        HashMap result = dp.getListVoucher(input.get(FieldParameter.trxType).toString());
//
//        if (result.get(FieldParameter.respCode).equals(RuleNameParameter.respcodeSuccess)) {
//            input.put(FieldParameter.respCode, RuleNameParameter.respcodeSuccess);
//            input.put(FieldParameter.listVoucher, result.get("list"));
//        } else {
//            input.put(FieldParameter.respCode, RuleNameParameter.respcodeProcessFailed);
//        }
//
//        dp = null;
//        return input;
//    }
//}
