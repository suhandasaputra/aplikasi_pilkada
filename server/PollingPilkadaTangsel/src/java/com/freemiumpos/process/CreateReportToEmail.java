/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.function.CheckFileFolder;
import com.freemium.function.JsonProcess;
import com.freemium.function.SendFileReport;
import com.freemium.function.StringFunction;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class CreateReportToEmail {

    String fileSource = null;
    String fileName = null;
    File file = null;
    String listTrx[] = null;
    String detailTrx[] = null;
    DatabaseProcess dp = new DatabaseProcess();

    public HashMap process(HashMap inputMap) {
        try {

            inputMap.put(RuleNameParameter.resp_code, "0001");
            inputMap.put(RuleNameParameter.resp_desc, "Failed.");

            inputMap = dp.ListAllTransaction(inputMap);
            int home = 0;
            fileName = inputMap.get(FieldParameter.fullid).toString() + "_report.xls";
            CheckFileFolder.checkFolder(StaticParameter.path_report);
            fileSource = StaticParameter.path_report + fileName;
            file = new File(fileSource);
            file.createNewFile();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Transaksi");
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
            HSSFRow rowhead = sheet.createRow((short) home);
            rowhead.createCell(0).setCellValue("TRANSACTION REPORT " + StaticParameter.APS_NAME);
            home += 2;
            rowhead = sheet.createRow((short) home);
//            HSSFRow rowhead = sheet.createRow((short) home);
            rowhead.createCell(0).setCellValue("No.");
            rowhead.createCell(1).setCellValue("PIC");
            rowhead.createCell(2).setCellValue("Noresi");
            rowhead.createCell(3).setCellValue("Date");
            rowhead.createCell(4).setCellValue("Amount");
            rowhead.createCell(5).setCellValue("Transaction Number");
            rowhead.createCell(6).setCellValue("Payment Method");
            rowhead.createCell(7).setCellValue("Status");
            home += 1;

            System.out.println("inputMapinputMap : " + inputMap);
            listTrx = inputMap.get(FieldParameter.list).toString().split(";");

            HSSFRow row;
            for (int i = 0; i < listTrx.length; i++) {
                row = sheet.createRow((short) home);
                detailTrx = listTrx[i].split("\\|");
                row.createCell(0).setCellValue(i + 1);
                for (int j = 0; j < detailTrx.length; j++) {
//                    bw.write(detailTrx[j]);
                    if (j == 5) {
                        row.createCell(j + 1).setCellValue(detailTrx[j]);
                        switch (detailTrx[j]) {
                            case "1":
                                row.createCell(j + 1).setCellValue("CASH");
                                break;
                            case "2":
                                row.createCell(j + 1).setCellValue("CARD");
//                                amountTotal += Integer.valueOf(detailTrx[3]);
                                break;
                            case "3":
                                row.createCell(j + 1).setCellValue("EMONEY");
                                break;
                            default:
                                row.createCell(j + 1).setCellValue("-");
                                break;

                        }
                    } else if (j == 6) {
                        row.createCell(j + 1).setCellValue(detailTrx[j]);
                        switch (detailTrx[j]) {
                            case "0":
                                row.createCell(j + 1).setCellValue("Unpaid");
                                break;
                            case "1":
                                row.createCell(j + 1).setCellValue("Paid");
//                                amountTotal += Integer.valueOf(detailTrx[3]);
                                break;
                            case "99":
                                row.createCell(j + 1).setCellValue("Cancelled");
                                break;

                        }
                    } else {
                        row.createCell(j + 1).setCellValue(detailTrx[j]);
                    }
                }
                home += 1;

            }

//            home += 1;
//            row = sheet.createRow((short) home);
//            row.createCell(3).setCellValue("TOTAL");
//            row.createCell(4).setCellValue(amountTotal);
            FileOutputStream fileOut = new FileOutputStream(fileSource);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

            SendFileReport Mail = new SendFileReport();
            if (Mail.execute(fileSource, fileName, inputMap.get(FieldParameter.email).toString())) {
                inputMap.put(RuleNameParameter.resp_code, "0000");
            }

        } catch (Exception e) {
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_desc, "Message request tidak sesuai");
        }
        dp.CreateLogManage(inputMap, JsonProcess.generateJson(inputMap), inputMap.get(RuleNameParameter.resp_code).toString(), this.getClass().getSimpleName());
        dp = null;

        return inputMap;
    }
}
