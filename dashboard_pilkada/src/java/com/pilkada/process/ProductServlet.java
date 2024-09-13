/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilkada.process;

import com.bo.function.JsonProcess;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.pilkada.db.DatabaseProcess;
import com.pilkada.model.Model_Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author suhanda
 */
public class ProductServlet extends HttpServlet {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ProductServlet.class);
    DatabaseProcess dp = new DatabaseProcess();

    public ProductServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Model_Product> listTransaction = new ArrayList<>();
            listTransaction = dp.getAllProduct();
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(listTransaction);
            JsonArray jsonArray = element.getAsJsonArray();

            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        } catch (ParseException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = "";
        String line = "";

        while ((line = in.readLine()) != null) {
            data += line;
        }
        HashMap gg = JsonProcess.decodeJson(data);

        Model_Product pro = new Model_Product();
        pro.setId_subdistrict(gg.get("id_subdistrict").toString());
        pro.setSubdistrict_name(gg.get("subdistrict_name").toString());
        pro.setDistrict(gg.get("district").toString());
        String status = dp.addProduct(pro);

        HashMap res = new HashMap();
        res.put("status", status);
        if (status.equals("00")) {
            response.getWriter().print(JsonProcess.generateJson(res));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        } else {
            response.getWriter().print(JsonProcess.generateJson(res));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
    }
}
