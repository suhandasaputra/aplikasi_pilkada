/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilkada.process;

import com.bo.function.MessageProcess;
import com.bo.function.impl.HTTPProcessImpl;
import com.bo.function.impl.MessageProcessImpl;
import com.bo.parameter.FieldParameter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.pilkada.db.DatabaseProcess;
import com.pilkada.model.Model_Bank;
import com.pilkada.model.Model_User;
import com.pilkada.parameter.StaticParameter;
import java.io.IOException;
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
public class UserServlet extends HttpServlet {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserServlet.class);
    DatabaseProcess dp = new DatabaseProcess();

    public UserServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Model_User> listUser = new ArrayList<>();
            listUser = dp.getAllUser();
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(listUser);
            JsonArray jsonArray = element.getAsJsonArray();

            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        } catch (ParseException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("adduser")) {
//            Model_User bn = new Model_User();
//            bn.setId_candidate(request.getParameter("id_candidate"));
//            bn.setCandidate_name(request.getParameter("candidate_name"));
//            bn.setImg_profile(request.getParameter("img_candidate"));
//            System.out.println("ini id candidate : " + request.getParameter("id_candidate"));
//            System.out.println("ini name candidate : " + request.getParameter("candidate_name"));
//            System.out.println("ini image candidate : " + request.getParameter("img_candidate"));
//
//            String status = dp.addCandidate(bn);
//            if (status.equalsIgnoreCase("00")) {
//                String activitas = "add candidate " + request.getParameter("candidate_name");
//                RequestDispatcher rd = getServletContext().getRequestDispatcher("/usr");
//                response.setContentType("text/html;charset=UTF-8");
//                PrintWriter out = response.getWriter();
//                out.println("<div class=\"alert alert-success status-custom\">\n"
//                        + "     <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
//                        + "     <i class=\"icon fa fa-check\"></i>" + "success add bank" + "\n"
//                        + "</div>");
//                rd.include(request, response);
//            } else if (status.equalsIgnoreCase("01")) {
//                RequestDispatcher rd = getServletContext().getRequestDispatcher("/usr");
//                PrintWriter out = response.getWriter();
//                out.println("<div class=\"alert alert-danger status-custom\">\n"
//                        + "     <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
//                        + "     <i class=\"icon fa fa-warning\"></i>" + "failed add bank" + "\n"
//                        + "</div>");
//                rd.include(request, response);
//            } else {
//                RequestDispatcher rd = getServletContext().getRequestDispatcher("/usr");
//                PrintWriter out = response.getWriter();
//                out.println("<div class=\"alert alert-danger status-custom\">\n"
//                        + "     <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
//                        + "     <i class=\"icon fa fa-warning\"></i>" + "failed" + "\n"
//                        + "</div>");
//                rd.include(request, response);
//            }
//
//            String id_candidate = request.getParameter("id_candidate");
            String candidate_name = request.getParameter("candidate_name");
            String img_candidate = request.getParameter("img_candidate");

            HashMap req = new HashMap();
            MessageProcess mp = new MessageProcessImpl();
            HTTPProcessImpl ht = new HTTPProcessImpl();
            req.put("procCode", "50015");
            req.put("candidate_name", candidate_name);
            req.put("img_profile", img_candidate);
            

            String reqMsg = mp.encryptMessage(req);
            System.out.println("ini req nya : " + req);
            String responseWeb = ht.sendHttpRequest(StaticParameter.urlbackendserverpilkadaUAT, reqMsg);
            HashMap resp = mp.decryptMessage(responseWeb);
            System.out.println(" ini respon nya : " + resp);
            if (resp.get(FieldParameter.resp_code).equals("0000")) {
                RequestDispatcher rd = request.getRequestDispatcher("/usr");
                PrintWriter out = response.getWriter();
                out.println("<div class=\"alert alert-success status-custom\">\n"
                        + "     <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
                        + "     <i class=\"icon fa fa-check\"></i>Success Add Candidate " + candidate_name + " \n"
                        + "</div>");
                rd.include(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/usr");
                PrintWriter out = response.getWriter();
                out.println("<div class=\"alert alert-danger status-custom\">\n"
                        + "     <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
                        + "     <i class=\"icon fa fa-check\"></i>Failed Add Candidate " + candidate_name + " \n"
                        + "</div>");
                rd.include(request, response);
            }

        }
    }
}
