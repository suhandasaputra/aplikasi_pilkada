/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

//import com.balimuda.parameter.StaticParameter;
import com.freemium.database.DatabaseProcess;
import com.freemium.entity.Tempmsg;
import com.freemium.function.JsonProcess;
import com.freemium.function.SendMailSSL;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameter;
import com.freemium.parameter.StaticParameterCuso;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class SendDetailTransactionByRRN {

    private static Logger log = Logger.getLogger(SendDetailTransactionByRRN.class);
    private Tempmsg tempmsg = new Tempmsg();

    public HashMap process(HashMap input) throws ParseException, UnsupportedEncodingException {
        HashMap detail = new HashMap();
        DatabaseProcess dp = new DatabaseProcess();
        HashMap result = dp.getDetailTransaction(input.get(FieldParameter.userlogin).toString(), input.get(FieldParameter.rrn).toString());
        HashMap UserDetail = dp.getUserDetail(input);
        HashMap getTrxDetail = dp.getDetailTransaction(input.get(FieldParameter.userlogin).toString(), input.get(FieldParameter.rrn).toString());
        HashMap TrxDetail = (HashMap) getTrxDetail.get(FieldParameter.detail);
        HashMap ProductDetail = new HashMap();

        switch (TrxDetail.get(FieldParameter.proccode).toString()) {
            case "260000":
                ProductDetail = dp.getWarungqrName(TrxDetail.get(RuleNameParameter.merchantid).toString());
                TrxDetail.put(FieldParameter.customer_id, TrxDetail.get(RuleNameParameter.merchantid).toString());
                TrxDetail.put(FieldParameter.data, ProductDetail);
                detail.put(FieldParameter.feejual, TrxDetail.get(FieldParameter.feejual).toString());
                break;
            case "280000":
                ProductDetail = dp.getProductDetail(TrxDetail.get(FieldParameter.product_code).toString());
                detail.put(FieldParameter.feejual, TrxDetail.get(FieldParameter.feejual).toString());
                break;
            case "400001":
                detail.put(FieldParameter.fromAccount, TrxDetail.get(FieldParameter.fromAccount).toString());
                detail.put(FieldParameter.toAccount, TrxDetail.get(FieldParameter.toAccount).toString());
                detail.put(StaticParameter.pesan, TrxDetail.get(StaticParameter.pesan).toString());
                ProductDetail = dp.getProductDetail(TrxDetail.get(FieldParameter.product_code).toString());
                TrxDetail.put(FieldParameter.customer_id, TrxDetail.get(FieldParameter.toAccount).toString());
                TrxDetail.put(FieldParameter.data, ProductDetail);
                detail.put(FieldParameter.feejual, TrxDetail.get(FieldParameter.feejual).toString());

                break;
            case "400004":
                detail.put(FieldParameter.fromAccount, TrxDetail.get(FieldParameter.fromAccount).toString());
                detail.put(FieldParameter.toAccount, TrxDetail.get(FieldParameter.toAccount).toString());
                detail.put(StaticParameter.pesan, TrxDetail.get(StaticParameter.pesan).toString());
                ProductDetail = dp.getProductDetail(TrxDetail.get(FieldParameter.product_code).toString());
                TrxDetail.put(FieldParameter.customer_id, TrxDetail.get(FieldParameter.toAccount).toString());
                TrxDetail.put(FieldParameter.data, ProductDetail);
                detail.put(FieldParameter.feejual, TrxDetail.get(FieldParameter.feejual).toString());

                break;
            case "400005":
                detail.put(FieldParameter.fromAccount, TrxDetail.get(FieldParameter.fromAccount).toString());
                detail.put(FieldParameter.toAccount, TrxDetail.get(FieldParameter.toAccount).toString());
                detail.put(StaticParameter.pesan, TrxDetail.get(StaticParameter.pesan).toString());
                ProductDetail = dp.getProductDetail(TrxDetail.get(FieldParameter.product_code).toString());
                TrxDetail.put(FieldParameter.customer_id, TrxDetail.get(FieldParameter.toAccount).toString());
                TrxDetail.put(FieldParameter.data, ProductDetail);
                detail.put(FieldParameter.feejual, TrxDetail.get(FieldParameter.feejual).toString());

                break;
            case "400009":
                //            System.out.println("BA : "+TrxDetail.get(StaticParameterCuso.no_ba).toString());
                detail.put(FieldParameter.fromAccount, TrxDetail.get(FieldParameter.fromAccount).toString());
                detail.put(FieldParameter.toAccount, TrxDetail.get(FieldParameter.toAccount).toString());
                detail.put(StaticParameter.pesan, TrxDetail.get(StaticParameter.pesan).toString());
                detail.put(StaticParameterCuso.no_ba, TrxDetail.get(StaticParameterCuso.no_ba).toString());
                ProductDetail = dp.getProductDetail(TrxDetail.get(FieldParameter.product_code).toString());
                TrxDetail.put(FieldParameter.customer_id, TrxDetail.get(FieldParameter.toAccount).toString());
                TrxDetail.put(FieldParameter.data, ProductDetail);
                break;
            case "400010":
                //            System.out.println("BA : "+TrxDetail.get(StaticParameterCuso.no_ba).toString());
                detail.put(FieldParameter.fromAccount, TrxDetail.get(FieldParameter.fromAccount).toString());
                detail.put(FieldParameter.toAccount, TrxDetail.get(FieldParameter.toAccount).toString());
                detail.put(StaticParameter.pesan, TrxDetail.get(StaticParameter.pesan).toString());
                detail.put(StaticParameterCuso.no_ba, TrxDetail.get(StaticParameterCuso.no_ba).toString());
                ProductDetail = dp.getProductDetail(TrxDetail.get(FieldParameter.product_code).toString());
                TrxDetail.put(FieldParameter.customer_id, TrxDetail.get(FieldParameter.toAccount).toString());
                TrxDetail.put(FieldParameter.data, ProductDetail);
                break;
            default:
                ProductDetail = dp.getProductDetail(TrxDetail.get(FieldParameter.product_code).toString());
                detail.put(FieldParameter.feejual, "-");

                break;
        }
        detail.put(FieldParameter.proccode, TrxDetail.get(FieldParameter.proccode).toString());
        detail.put(FieldParameter.productName, ProductDetail.get(FieldParameter.productName).toString());
        detail.put(FieldParameter.userlogin, UserDetail.get(FieldParameter.userlogin).toString());
        detail.put(FieldParameter.email, UserDetail.get(FieldParameter.email).toString());
        detail.put(FieldParameter.username, UserDetail.get(FieldParameter.username).toString());
        detail.put(FieldParameter.hpNumber, UserDetail.get(FieldParameter.hpNumber).toString());
        detail.put(FieldParameter.amount, TrxDetail.get(FieldParameter.amount).toString());
        detail.put(FieldParameter.req_datetime, TrxDetail.get(FieldParameter.req_datetime).toString());
        detail.put(FieldParameter.customer_id, TrxDetail.get(FieldParameter.customer_id).toString());
        detail.put(FieldParameter.rrn, TrxDetail.get(FieldParameter.rrn).toString());
        detail.put(FieldParameter.transactioncode, TrxDetail.get(FieldParameter.transactioncode).toString());
        HashMap data = (HashMap) TrxDetail.get(FieldParameter.data);
        if (data.containsKey(RuleNameParameter.SwitcherRef)) {
            detail.put(RuleNameParameter.SwitcherRef, data.get(RuleNameParameter.SwitcherRef).toString());
        } else {
            detail.put(RuleNameParameter.SwitcherRef, "-");
        }
        if (TrxDetail.containsKey(RuleNameParameter.additional_data)) {
            detail.put(FieldParameter.additional_data, TrxDetail.get(FieldParameter.additional_data).toString());
        } else {
            detail.put(RuleNameParameter.additional_data, "-");

        }
        if (data.containsKey(RuleNameParameter.Pesan)) {
            System.out.println("pesan : " + data.get(RuleNameParameter.Pesan).toString());
            detail.put(RuleNameParameter.Pesan, data.get(RuleNameParameter.Pesan).toString());
        } else {
            detail.put(RuleNameParameter.Pesan, "-");
        }
        SendMailSSL sendmail = new SendMailSSL();
        String done = sendmail.sendDetailTrxToEmail(detail);
        if (done.equals(RuleNameParameter.respcodeSuccess)) {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeSuccess);
            //            HashMap detail = (HashMap) result.get(FieldParameter.detail);
            input.put(FieldParameter.detail, result.get(FieldParameter.detail));
        } else {
            input.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeProcessFailed);
            input.put(RuleNameParameter.resp_desc, "Send to E-Mail Failed.");
        }
        dp = null;
        return input;

    }
}
