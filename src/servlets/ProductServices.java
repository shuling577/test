/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBoperations;
import module.ShoppingCart1;
import module.Product;
import java.io.IOException;
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
@WebServlet(name = "ProductServices", urlPatterns = {"/ProductServices"})
public class ProductServices extends HttpServlet {

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
        
        DBoperations dbOps = new DBoperations();
        ArrayList<Product> products = new ArrayList<Product>();
        HttpSession session = request.getSession();
        String productUnitPrice = request.getParameter("productUnitPrice");
        String productDesc = request.getParameter("productDesc"); 
        String quantity = request.getParameter("quantity");
        String productID = request.getParameter("productID");
        String username = (String)session.getAttribute("user");
        String shoppingCart = request.getParameter("shoppingCart");
        String back = request.getParameter("back");
        ArrayList<ShoppingCart1> list;
        
        //get the number of order
        if(productID != null){
           list = (ArrayList<ShoppingCart1>)session.getAttribute("shoppingCart1");
           if(list == null){
               list  = new ArrayList<ShoppingCart1>();
               session.setAttribute("shoppingCart1", list);
           }
           ShoppingCart1 cart = new ShoppingCart1(Integer.parseInt(productID),productDesc,Float.parseFloat(productUnitPrice),Integer.parseInt(quantity));
           list.add(cart);
           session.setAttribute("cartNum", list.size());
        }else if(shoppingCart != null){ //go to the shopping cart
            request.getRequestDispatcher("/ShoppingCartServices").forward(request, response);
        }else if(back != null){
            list = (ArrayList<ShoppingCart1>)session.getAttribute("shoppingCart1");
            session.setAttribute("cartNum", list.size());
        }else{
            list = (ArrayList<ShoppingCart1>)session.getAttribute("shoppingCart1");
            String cartTable = dbOps.getShoppingCart(username);
            System.out.println(cartTable);
            if(!cartTable.equals("")){
            
                String[] rows1 = cartTable.split(";");
                for(String row:rows1){
                    String[] detail = row.split(",");
                    ShoppingCart1 cart1 = new ShoppingCart1(Integer.parseInt(detail[0]),detail[1],Float.parseFloat(detail[2]),Integer.parseInt(detail[3]));
                    list.add(cart1);
                }
                if(dbOps.deleteWholeCart(username)){
                    System.out.println("delete");
                }
                session.setAttribute("cartNum", list.size());
            }else{
                session.setAttribute("cartNum", 0);
            }
        }
        
        //get the data from database
        String productsTable = dbOps.getProduct();
        String[] rows = productsTable.split(";");
        for(String row:rows){
            String[] detail = row.split(",");
            Product product = new Product(Integer.parseInt(detail[0]),detail[1],Float.parseFloat(detail[2]),Integer.parseInt(detail[3]));
            products.add(product);
        }
        request.setAttribute("productsTable", products);
        
        request.getRequestDispatcher("/WEB-INF/browse.jsp").forward(request, response);
        
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
