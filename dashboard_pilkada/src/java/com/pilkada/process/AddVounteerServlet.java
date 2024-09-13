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
import com.pilkada.db.DatabaseProcess;
import com.pilkada.parameter.StaticParameter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author suhanda
 */
public class AddVounteerServlet extends HttpServlet {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AddVounteerServlet.class);
    DatabaseProcess dp = new DatabaseProcess();

    public AddVounteerServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = "";
        String line = "";

        while ((line = in.readLine()) != null) {
            data += line;
        }
        HashMap gg = JsonProcess.decodeJson(data);

        String type = gg.get("type").toString();

        if (type.equals("step1")) {
            String id_user = gg.get("id_user").toString();
            String name = gg.get("name").toString();
            String phone_number = gg.get("phone_number").toString();
            String address = gg.get("address").toString();
            String subdistrict = gg.get("subdistrict").toString();
            String district = gg.get("district").toString();
            String city = gg.get("city").toString();
            String password = gg.get("password").toString();

            HashMap req = new HashMap();
            MessageProcess mp = new MessageProcessImpl();
            HTTPProcessImpl ht = new HTTPProcessImpl();
            req.put("procCode", "50001");
            req.put("userid", id_user);
            req.put("name", name);
            req.put("phonenumber", phone_number);
            req.put("address", address);
            req.put("subdistrict", subdistrict);
            req.put("district", district);
            req.put("city", city);
            req.put("password", password);

            String reqMsg = mp.encryptMessage(req);
            System.out.println("ini req nya : " + req);
            String responseWeb = ht.sendHttpRequest(StaticParameter.urlbackendserverpilkadaUAT, reqMsg);
            HashMap resp = mp.decryptMessage(responseWeb);
            System.out.println(" ini respon nya : " + resp);
            if (resp.get(FieldParameter.resp_code).equals("0000")) {
                response.getWriter().print(JsonProcess.generateJson(resp));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            } else {
                response.getWriter().print(JsonProcess.generateJson(resp));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }

        } else if (type.equals("step2")) {

            String province = gg.get("province").toString();
            String city = gg.get("city").toString();
            String district1 = gg.get("district1").toString();
            String subdistrict1 = gg.get("subdistrict1").toString();
            String userid1 = gg.get("userid1").toString();
            String phonenumber1 = gg.get("phonenumber1").toString();
            String tps = gg.get("tps").toString();

            HashMap req = new HashMap();
            MessageProcess mp = new MessageProcessImpl();
            HTTPProcessImpl ht = new HTTPProcessImpl();
            req.put("procCode", "50002");
            req.put("district", district1);
            req.put("subdistrict", subdistrict1);
            req.put("userid", userid1);
            req.put("phonenumber", phonenumber1);
            req.put("voting_place", tps);


            String reqMsg = mp.encryptMessage(req);
            System.out.println("ini req nya : " + req);
            String responseWeb = ht.sendHttpRequest(StaticParameter.urlbackendserverpilkadaUAT, reqMsg);
            HashMap resp = mp.decryptMessage(responseWeb);
            System.out.println(" ini respon nya : " + resp);
            if (resp.get(FieldParameter.resp_code).equals("0000")) {
                response.getWriter().print(JsonProcess.generateJson(resp));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            } else {
                response.getWriter().print(JsonProcess.generateJson(resp));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }

        }
    }
}
