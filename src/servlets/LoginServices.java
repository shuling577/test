/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBoperations;
import module.ShoppingCart1;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "LoginServices", urlPatterns = {"/LoginServices"})
public class LoginServices extends HttpServlet {

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
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String logout = request.getParameter("logout");
        DBoperations dbOps = new DBoperations();
        
        if(logout!=null){
            
            //destroy the seesion
            HttpSession session = request.getSession();
            session.invalidate();
            
            request.setAttribute("message", "Logged out");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }else if(username == null || password == null){
            
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }else if(username.equals("") || password.equals("")){
        
            request.setAttribute("message", "Both values are required!");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }else{
            
            if(dbOps.validateUser(username, password)){
                //if the valid user check the box,then set the cookie
                //if not, destroy the cookie
                if(rememberMe != null){
                    
                    Cookie usernameCookie =  new Cookie("username",username);
                    usernameCookie.setMaxAge(86400);
                    response.addCookie(usernameCookie);
                }else{
                    Cookie[] cookies=request.getCookies();
                
                    for(Cookie c:cookies){
                        if(c.getName().equals("username")){
                            c.setMaxAge(0);
                            response.addCookie(c);
                        }
                    }
                }
                
                HttpSession session = request.getSession();
                //check whether the user is admin
                if(dbOps.isAdmin(username)){
                
                    session.setAttribute("user", username);
                    request.setAttribute("admin", username);
                    request.getRequestDispatcher("/AdminServices").forward(request, response);
                }else{
                    
                    session.setAttribute("user", username);
                    session.setAttribute("shoppingCart1", new ArrayList<ShoppingCart1>());
                    
                    request.setAttribute("user", username);
                    request.getRequestDispatcher("/ProductServices").forward(request, response);
                }
                
            }else{
                request.setAttribute("message","Invalid username or password!");
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
