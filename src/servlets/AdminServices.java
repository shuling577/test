/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBoperations;
import module.Product;
import module.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "AdminServices", urlPatterns = {"/AdminServices"})
public class AdminServices extends HttpServlet {

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
        
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String usernameA = request.getParameter("usernameA");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        
        String action1 = request.getParameter("action1");
        String productID = request.getParameter("productID");
        String productDesc = request.getParameter("productDesc");
        String productUnitPrice = request.getParameter("productUnitPrice");
        String productUnitsInStock = request.getParameter("productUnitsInStock");
        
        String description = request.getParameter("description");
        String unitPrice = request.getParameter("unitPrice");
        String unitsInStock = request.getParameter("unitsInStock");
        DBoperations dbOps = new DBoperations();
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Product> products = new ArrayList<Product>();
        
        if(action != null){
            if(action.equals("Reset")){
                if(dbOps.resetPassword(username)){
                    request.setAttribute("message", "Password for '"+username+"' reset");
                }
            }else if(action.equals("Delete")){
                if(dbOps.deleteUser(username)){
                    request.setAttribute("message", "User '"+username+"' deleted");
                }
            }else{
                if(dbOps.toggleStatus(username)){
                    request.setAttribute("message", "User type for '"+username+"' toggled");
                }
            }
        }else if(dbOps.validateUsername(usernameA)){
            
            request.setAttribute("message", "Error adding new user '"+usernameA+"' to system!");
            
        }else if(usernameA == null || password1 == null || password2 == null){
            
            
        }else if(!password1.equals(password2)){
            
            request.setAttribute("message","Passwords do not match!");
            
        }else if(usernameA.equals("") || password1.equals("") || password2.equals("")){
            request.setAttribute("message","Please enter information!");
            
        }else {
            
            if(dbOps.addUser(usernameA, password2)){
                request.setAttribute("message","New user '"+usernameA+"' added to system");
        
            }
        }
        
        if(action1 != null){
            if(action1.equals("Update")){
                if(dbOps.updateProduct(productID, productDesc, productUnitPrice, productUnitsInStock)){
                    request.setAttribute("message1","Product '"+productDesc+"' updated");
                }
            }else {
                if(dbOps.deleteProduct(productID)){
                    request.setAttribute("message1","Product '"+productDesc+"' deleted from system");
                }
            }
        }else if(description == null || unitPrice == null || unitsInStock == null){
            
            
        }else if(description.equals("") || unitPrice.equals("") || unitsInStock.equals("")){
            request.setAttribute("message1","Please enter information!");
            
        }else {
            if(dbOps.addProduct(description, unitPrice, unitsInStock)){
            System.out.println(description);
            request.setAttribute("message1","Product '"+description+"' added to system");
        
            }
        }
        
        //display the user table
        String userTable = dbOps.getUsers();
        if(!userTable.equals("")){
            String[] rows = userTable.split(";");
            for(String row:rows){
                
                String[] detail = row.split(",");
                User user = new User(detail[0],null,detail[1]);
                users.add(user);
            }
            
        }
        //display the product table
        String productTable = dbOps.getProduct();
        if(!productTable.equals("")){
            String[] rows = productTable.split(";");
            for(String row:rows){
                
                String[] detail = row.split(",");
                Product product = new Product(Integer.parseInt(detail[0]),detail[1],
                                         Float.parseFloat(detail[2]),Integer.parseInt(detail[3]));
                products.add(product);
            }
            
        }
        
        request.setAttribute("productList", products);
        request.setAttribute("userList", users);
        request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        
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
