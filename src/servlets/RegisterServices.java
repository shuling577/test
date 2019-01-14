/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBoperations;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "RegisterServices", urlPatterns = {"/RegisterServices"})
public class RegisterServices extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        DBoperations dbOps = new DBoperations();
        
        if(dbOps.validateUsername(username)){
            
            request.setAttribute("message", "Error registering new account,please try again with a different user name!");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else if(username == null || password1 == null || password2 == null){
            
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else if(!password1.equals(password2)){
            
            request.setAttribute("message","Passwords do not match!");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else if(username.equals("") || password1.equals("") || password2.equals("")){
            request.setAttribute("message","Please enter information to register!");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else{
            
            if(dbOps.addUser(username, password2)){
                request.setAttribute("message","Register complete,please log in");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
            
        }
        
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
