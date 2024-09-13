/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilkada.process;

import com.bo.function.JsonProcess;
import com.pilkada.db.DatabaseProcess;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author matajari
 */
@WebServlet(name = "DelProdServlet", urlPatterns = {"/DelProdServlet"})
public class DelBankServlet extends HttpServlet {

    DatabaseProcess dp = new DatabaseProcess();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String data = "";
//        String line = "";
//
//        while ((line = in.readLine()) != null) {
//            data += line;
//        }
//        HashMap gg = JsonProcess.decodeJson(data);
//        String trancodeid = gg.get("bankcode").toString();
//        String status = dp.deletebank(trancodeid);
//        
//        HashMap res = new HashMap();
//        res.put("status", status);
//        if (status == "00") {
//            response.getWriter().print(JsonProcess.generateJson(res));
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//        } else {
//            response.getWriter().print(JsonProcess.generateJson(res));
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
