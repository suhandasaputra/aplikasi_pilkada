/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Messagein;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class InquiryBuyer {

    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    private DatabaseProcess dp = new DatabaseProcess();
    Messagein msgin = new Messagein();
    private static Logger log = Logger.getLogger(InquiryBuyer.class);
    HashMap response = null;

    public HashMap process(HashMap input) throws Exception {
        log.info("\n" + "Masuk ke Inquiry Buyer" + "\n");
        msgin.setInput(input);
        msgin.setMsgid(timestamp
                + msgin.getInput().get(FieldParameter.userlogin).toString()
                + msgin.getInput().get(FieldParameter.rrn).toString());
        if (dp.checkVerified(msgin.getInput().get(FieldParameter.userlogin).toString())) {

            dp.checkCode(msgin);
            String toaccount = msgin.getInput().get(FieldParameter.userlogin).toString();
//            log.info("INQ BUY : +  " + msgin.getInput().get(FieldParameter.toaccount) + " : " + toaccount);
            if (!msgin.getInput().get(FieldParameter.toaccount).equals(toaccount)) {
                msgin.getInput().put(RuleNameParameter.resp_desc, "Penerima Tidak Sesuai");
                msgin.getInput().put(RuleNameParameter.resp_code, "0001");
                response = msgin.getInput();

            } else if (msgin.getInput().get(RuleNameParameter.resp_code).equals("0000")) {
                msgin.getInput().put(RuleNameParameter.resp_desc, "Succesful");
                response = msgin.getInput();

            } else {
                msgin.getInput().put(RuleNameParameter.resp_desc, "Kode tidak ada atau sudah kadaluarsa");
                response = msgin.getInput();
            }
        } else {
            msgin.getInput().put(FieldParameter.resp_code, "0001");
            msgin.getInput().put(FieldParameter.resp_desc, "Account anda belum terverifikasi");
            response = msgin.getInput();
        }
        return response;
    }
}
