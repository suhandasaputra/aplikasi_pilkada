/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilkada.process;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.pilkada.db.DatabaseProcess;
import com.pilkada.model.Opt_distict;
import com.pilkada.model.Opt_tps;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author suhanda
 */
public class OptionTpsServlet extends HttpServlet {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(OptionTpsServlet.class);
    DatabaseProcess dp = new DatabaseProcess();

    public OptionTpsServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("addvol")) {
            String subdis = request.getParameter("subdis");
            ArrayList<Opt_tps> listBank = new ArrayList<>();
            listBank = dp.getAllTps(subdis);
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(listBank);
            JsonArray jsonArray = element.getAsJsonArray();
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        } else if (action.equalsIgnoreCase("addact")) {
            ArrayList<Opt_tps> listBank = new ArrayList<>();
            listBank = dp.getAllTpsAct();
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(listBank);
            JsonArray jsonArray = element.getAsJsonArray();
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        }

//        ArrayList<Opt_tps> listBank = new ArrayList<>();
//        listBank = dp.getAllTps();
//        Gson gson = new Gson();
//        JsonElement element = gson.toJsonTree(listBank);
//        JsonArray jsonArray = element.getAsJsonArray();
//        response.setContentType("application/json");
//        response.getWriter().print(jsonArray);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String forward = "";
//        String action = request.getParameter("action");
//        if (action.equalsIgnoreCase("addbank")) {
//
//            Model_Bank bn = new Model_Bank();
//            bn.setBankcode(request.getParameter("add_bankcode"));
//            bn.setBankname(request.getParameter("add_bankname"));
//            String status = dp.addBank(bn);
//            if (status.equalsIgnoreCase("00")) {
//                String activitas = "menambahkan bank " + request.getParameter("add_bankcode");
//                RequestDispatcher rd = getServletContext().getRequestDispatcher("/lbk");
//                response.setContentType("text/html;charset=UTF-8");
//                PrintWriter out = response.getWriter();
//                out.println("<div class=\"alert alert-success status-custom\">\n"
//                        + "     <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
//                        + "     <i class=\"icon fa fa-check\"></i>" +"success add bank"+ "\n"
//                        + "</div>");
//                rd.include(request, response);
//            } else if (status.equalsIgnoreCase("01")) {
//                RequestDispatcher rd = getServletContext().getRequestDispatcher("/lbk");
//                PrintWriter out = response.getWriter();
//                out.println("<div class=\"alert alert-danger status-custom\">\n"
//                        + "     <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
//                        + "     <i class=\"icon fa fa-warning\"></i>" + "failed add bank" + "\n"
//                        + "</div>");
//                rd.include(request, response);
//            } else {
//                RequestDispatcher rd = getServletContext().getRequestDispatcher("/lbk");
//                PrintWriter out = response.getWriter();
//                out.println("<div class=\"alert alert-danger status-custom\">\n"
//                        + "     <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
//                        + "     <i class=\"icon fa fa-warning\"></i>" + "failed" + "\n"
//                        + "</div>");
//                rd.include(request, response);
//            }
//        }
    }
}
