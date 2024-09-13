/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.entity.Tempmsg;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameter;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * Matajari Mitra Solusi
 *
 * @author adiwibowo
 */
public class InquiryZIS {

    private static Logger log = Logger.getLogger(InquiryZIS.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) {
        HashMap result = new HashMap();
        result.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
        result.put(FieldParameter.name, input.get(FieldParameter.name));
        result.put(FieldParameter.hpNumber, input.get(FieldParameter.hpNumber));
        result.put(FieldParameter.email, input.get(FieldParameter.email));
        result.put(FieldParameter.amount, input.get(FieldParameter.amount).toString());
        result.put(StaticParameter.biayaadmin, 0);
        result.put(FieldParameter.jenisdana, input.get(FieldParameter.jenisdana).toString());
        result.put(FieldParameter.detail, input.get(FieldParameter.detail).toString());
        result.put(RuleNameParameter.Total, String.valueOf(Integer.valueOf(input.get(FieldParameter.amount).toString()) + 0));
        return result;
    }
}
