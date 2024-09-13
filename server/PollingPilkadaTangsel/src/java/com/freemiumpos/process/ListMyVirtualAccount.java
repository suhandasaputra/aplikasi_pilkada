/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.agentmanagement.database.AgentDBProc;
import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Tempmsg;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class ListMyVirtualAccount {

    private static Logger log = Logger.getLogger(ListMyVirtualAccount.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        //VA closed

        DatabaseProcess dp = new DatabaseProcess();
        if (dp.checkUserVA(input.get(FieldParameter.userlogin).toString())) {
//            check user sudah punya VA atau belum
            HashMap result = dp.getListMyVirtualAccount(input.get(FieldParameter.userlogin).toString());
            if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
                input.put(FieldParameter.listVirtualAccount, result.get("list"));
            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
            }
        } else {
            System.out.println("HARUSNYA MASUK CREATE VA broo");
//            jika belum punya VA, maka request create
            AgentDBProc agentdb = new AgentDBProc();
            if (agentdb.createVA(input)) {
                HashMap result = dp.getListMyVirtualAccount(input.get(FieldParameter.userlogin).toString());
                if (result.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
                    input.put(FieldParameter.listVirtualAccount, result.get("list"));
                } else {
                    input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                }
            } else {
                input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
                input.put(FieldParameter.resp_desc, "Gagal create VA Number, harap hubungi Call Center kami");

            }
        }
        dp = null;

//va closed
//        input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
//        input.put(FieldParameter.resp_desc, "Untuk sementara TOP-up VA belum dibuka.");
        return input;
    }
}
