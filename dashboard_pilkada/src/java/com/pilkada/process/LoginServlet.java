/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilkada.process;

import com.bo.function.FunctionSupportMB;
import com.pilkada.db.DatabaseProcess;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pri.vate.klas.function.Func1;
import pri.vate.klas.function.Func5;

/**
 *
 * @author matajari
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private Func1 f1 = new Func1();
    private Func5 f5 = new Func5();
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LoginServlet.class);

    DatabaseProcess dp = new DatabaseProcess();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String password = FunctionSupportMB.encryptOneWayDataSave(request.getParameter("pass"));

        try {
            if (dp.validate(user, password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user_id", user);
                response.sendRedirect(request.getContextPath() + "/db");
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/lg");
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<div class=\"alert alert-danger status-custom\">\n"
                        + "     <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
                        + "     <i class=\"icon fa fa-check\"></i>Incorrect User / Password \n"
                        + "</div>");
                rd.include(request, response);
                log.info("\n" + "Incorrect Username / Password" + "\n");
                System.out.println("Incorrect Username / Password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
