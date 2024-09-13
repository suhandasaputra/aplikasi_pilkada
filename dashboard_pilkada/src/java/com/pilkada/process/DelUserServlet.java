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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author matajari
 */
@WebServlet(name = "DelProdServlet", urlPatterns = {"/DelProdServlet"})
public class DelUserServlet extends HttpServlet {

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
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = "";
        String line = "";

        while ((line = in.readLine()) != null) {
            data += line;
        }
        HashMap gg = JsonProcess.decodeJson(data);
//        String bankcode = gg.get("bankcode").toString();
//        String status = dp.deleteuser(bankcode);
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

        String id_candidate_edit = gg.get("id_candidate_edit").toString();

        HttpSession session = request.getSession(true);
        String userid = session.getAttribute("user_id").toString();

        HashMap req = new HashMap();
        MessageProcess mp = new MessageProcessImpl();
        HTTPProcessImpl ht = new HTTPProcessImpl();
        req.put("procCode", "50017");
        req.put("userid", userid);
        req.put("candidate_id", id_candidate_edit);

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
