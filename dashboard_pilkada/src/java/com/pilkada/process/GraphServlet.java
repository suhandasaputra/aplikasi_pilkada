/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilkada.process;

import com.bo.function.JsonProcess;
import com.pilkada.db.DatabaseProcess;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet to handle File upload request from Client
 *
 * @author suhanda
 */
public class GraphServlet extends HttpServlet {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GraphServlet.class);

    public GraphServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        DatabaseProcess dp = new DatabaseProcess();
        HashMap status = dp.getgrap();
        
        if (status.get("resp_code").toString().equals("0000")) {
            String hh = JsonProcess.generateJson(status);
            response.getWriter().print(hh);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            System.out.println("ini statusnya : "+status);
        } else {
            response.getWriter().print(status);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }

    }
}
