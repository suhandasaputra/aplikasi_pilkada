/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilkada.process;

import com.bo.function.JsonProcess;
import com.bo.function.MessageProcess;
import com.bo.function.impl.HTTPProcessImpl;
import com.bo.function.impl.MessageProcessImpl;
import com.bo.parameter.FieldParameter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.pilkada.db.DatabaseProcess;
import com.pilkada.model.Model_Active;
import com.pilkada.parameter.StaticParameter;
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
public class ActiveServlet extends HttpServlet {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ActiveServlet.class);
    DatabaseProcess dp = new DatabaseProcess();

    public ActiveServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Model_Active> listUser = new ArrayList<>();
            listUser = dp.getAllActive();
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(listUser);
            JsonArray jsonArray = element.getAsJsonArray();

            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        } catch (ParseException ex) {
            Logger.getLogger(ActiveServlet.class.getName()).log(Level.SEVERE, null, ex);
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

        Model_Active pro = new Model_Active();
        pro.setId_subdistrict(gg.get("opt_subdistrict").toString());
        pro.setId_votingplace(gg.get("opt_tps").toString());
        pro.setTotal_voter(gg.get("total_voter").toString());
        String status = dp.addActive(pro);

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
