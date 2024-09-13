/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
//import javax.json.JsonArray;
import org.apache.log4j.Logger;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class UpdateOrderPOS {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(DatabaseProcess.class);

    public HashMap process(HashMap inputMap) {
        try {
            //save sebelum kirim ke MOST
//            HashMap responseMOST = new HashMap();

            //checkPerbedaan table
            inputMap.put(FieldParameter.order_number, dp.getOrderNumberByResi(inputMap.get(FieldParameter.noresi).toString()));
            inputMap.put(FieldParameter.table_number, inputMap.get(FieldParameter.table_number).toString().toUpperCase());
            if (!inputMap.get(FieldParameter.table_number).toString().equals("-") && !inputMap.get(FieldParameter.table_number).toString().equals("")) {
                HashMap checkingTable = dp.CheckingTableUpdate(inputMap);
                //check perbedaan stock
                HashMap checking = dp.CheckingUpdateStock(inputMap);
                HashMap checking2 = dp.CheckingStock(checking);
                if (checkingTable.get(FieldParameter.resp_code).toString().equals("0000")) {
                    //reversal stock dulu
                    if (checking2.get(FieldParameter.resp_code).toString().equals("0000")) {
                        if (dp.updateOrdertoLogTrxHM(inputMap)) {
                            //update new stock
                            dp.updateStock(checking);
                            if (!checkingTable.get(FieldParameter.old_table).toString().toUpperCase().equals("TAKE AWAY") && !checkingTable.get(FieldParameter.old_table).toString().toUpperCase().equals("DELIVERY")) {
                                dp.updateTable(inputMap, "1", checkingTable.get(FieldParameter.old_table).toString());
                            }
                            if (!inputMap.get(FieldParameter.table_number).toString().toUpperCase().equals("TAKE AWAY") && !inputMap.get(FieldParameter.table_number).toString().toUpperCase().equals("DELIVERY")) {
                                dp.updateTable(inputMap, "0", inputMap.get(FieldParameter.table_number).toString());
                            }

                            inputMap.put(RuleNameParameter.resp_code, "0000");
                        } else {
                            inputMap.put(RuleNameParameter.resp_code, "0001");
                            inputMap.put(RuleNameParameter.resp_desc, "Failed.");
//
                        }
                    } else {
                        inputMap.put(RuleNameParameter.resp_code, "0080");
                        inputMap.put(RuleNameParameter.resp_desc, checking2.get(FieldParameter.list).toString());

                    }
                } else {
                    inputMap.put(RuleNameParameter.resp_code, "0081");
                    inputMap.put(RuleNameParameter.resp_desc, checkingTable.get(FieldParameter.list).toString());
                }
            } else {
                inputMap.put(RuleNameParameter.resp_code, "0082");
                inputMap.put(RuleNameParameter.resp_desc, "Please select table, take away or delivery order");
            }

            dp = null;
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
        }
        return inputMap;
    }
}
