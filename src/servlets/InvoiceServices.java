/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBoperations;
import module.ShoppingCart1;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "InvoiceServices", urlPatterns = {"/InvoiceServices"})
public class InvoiceServices extends HttpServlet {

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
        
        Date date = new Date();
        DBoperations dbOps = new DBoperations();
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("user");
        ArrayList<ShoppingCart1> list = ((ArrayList)session.getAttribute("shoppingCart1"));
        
        request.setAttribute("message", "ORDER COMPLETE");
        request.setAttribute("date", date.toString());
        request.getRequestDispatcher("/WEB-INF/invoice.jsp").forward(request, response);
    
        for(int i=0;i<list.size();i++){
            if(dbOps.updateProductInStock(list.get(i).getProductID(), list.get(i).getNumberUnitsOrdered())){
                System.out.println("update");
            }
        }
        
        list.clear();
        session.setAttribute("shoppingCart1", list);
        
        if(dbOps.deleteWholeCart(username)){
            System.out.println("delete");
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
