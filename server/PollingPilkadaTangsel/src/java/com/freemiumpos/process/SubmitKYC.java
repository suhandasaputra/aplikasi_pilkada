/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.agentmanagement.database.AgentDBProc;
import com.freemium.function.CheckFileFolder;
import com.freemium.function.SendHttpProcess;
import com.freemium.function.SendMailNotification;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.apache.commons.codec.binary.Base64;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class SubmitKYC {

    SendHttpProcess http = new SendHttpProcess();
    private static Logger log = Logger.getLogger(SubmitKYC.class);
    private AgentDBProc db = new AgentDBProc();

    public HashMap process(HashMap input) throws ParseException, UnsupportedEncodingException, FileNotFoundException, IOException {
         input.put(RuleNameParameter.resp_code, "0000");
                input.put(FieldParameter.verified, "2");
                input.put(RuleNameParameter.resp_desc, "Successful");
                Base64 decoder = new Base64();
                byte[] img_ktp = decoder.decode(input.get(FieldParameter.img_ktp).toString());
                byte[] img_self = decoder.decode(input.get(FieldParameter.img_self).toString());
                byte[] img_profile = decoder.decode(input.get(FieldParameter.img_profile).toString());
                CheckFileFolder.checkFolder(StaticParameter.path);
                CheckFileFolder.checkFolder(StaticParameter.path + input.get(FieldParameter.userlogin).toString());
                FileOutputStream osf = new FileOutputStream(new File(StaticParameter.path + input.get(FieldParameter.userlogin).toString() + StaticParameter.slash + "img_ktp.png"));
                osf.write(img_ktp);
                osf.flush();
                osf.close();
                osf = new FileOutputStream(new File(StaticParameter.path + input.get(FieldParameter.userlogin).toString() + StaticParameter.slash1 + "img_self.png"));
                osf.write(img_self);
                osf.flush();
                osf.close();
                osf = new FileOutputStream(new File(StaticParameter.path + input.get(FieldParameter.userlogin).toString() + StaticParameter.slash1 + "img_profile.png"));
                osf.write(img_profile);
                osf.flush();
                osf.close();
                input.put(FieldParameter.img_ktp, StaticParameter.my_public_ip + input.get(FieldParameter.userlogin).toString() + StaticParameter.slash + "img_ktp.png");
                input.put(FieldParameter.img_self, StaticParameter.my_public_ip + input.get(FieldParameter.userlogin).toString() + StaticParameter.slash + "img_self.png");
                input.put(FieldParameter.img_profile, StaticParameter.my_public_ip + input.get(FieldParameter.userlogin).toString() + StaticParameter.slash + "img_profile.png");
                if (db.accountVerify(input)) {
                    input.put(RuleNameParameter.resp_code, "0000");
                    input.put(FieldParameter.verified, "2");
                    input.put(RuleNameParameter.resp_desc, "Successful");
                } else {
                    input.put(RuleNameParameter.resp_code, "0001");
                    input.put(RuleNameParameter.resp_desc, "Gagal Upload");
                }
            
            
            
            
            
        return input;
    }
}
