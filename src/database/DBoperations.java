/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author john
 */
public class DBoperations {
    
    public boolean validateUser(String username,String password){
    
        boolean result = false;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try{
            
            Connection conn = pool.getConnection();
            String sql = "select count(*) from users where username=? and password=?;";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                int count = rs.getInt(1);
                System.out.println(count);
                if(count == 1){
                    result = true;
                }
            }
            rs.close();
            ps.close();
            pool.freeConnection(conn);
            
        }catch (Exception ex){
            System.out.println(ex);
        }
        return result;
    }
    
    public boolean isAdmin(String username){
    
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try{
        
            Connection conn = pool.getConnection();
            String sql = "select userType from users where username = ?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                String check = rs.getString(1);
                result = (check.equals("admin"));
                System.out.println(check);
            }
            rs.close();
            ps.close();
            pool.freeConnection(conn);
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        return result;
    }
    
    public String getProduct(){
    
        String result="";
        
        ConnectionPool pool=ConnectionPool.getInstance();
        
        try {
            Connection conn=pool.getConnection();
            String sql="select * from products;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                result = result + rs.getInt(1)+","+rs.getString(2)+","
                        +rs.getFloat(3)+","+rs.getInt(4)+";";
            }
            System.out.println(result);
            rs.close();
            ps.close();
            pool.freeConnection(conn);
            
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        return result;
    }
    
    public boolean addToCart(String username,int productID,int quantity){
    
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="insert into savedcart values(?,?,?);";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            ps.setInt(2, productID);
            ps.setInt(3, quantity);
            
            int rowsAffected = ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result;
    }
    
    public int cartNum(String username){
    
        int result = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="select count(*) from savedcart where username = ?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                result = rs.getInt(1);
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result;
    }
    
    public String getShoppingCart(String username){
    
        String result="";
        
        ConnectionPool pool=ConnectionPool.getInstance();
        
        try {
            Connection conn=pool.getConnection();
            String sql="SELECT p.productID,productDesc,productUnitPrice,numberUnitsOrdered FROM savedcart s JOIN products p ON (s.productID=p.productID) WHERE s.username=?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                result = result + rs.getInt(1)+","+rs.getString(2)+","
                        +rs.getFloat(3)+","+rs.getInt(4)+";";
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(conn);
            
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        return result;
    }
    
    public boolean updateCart(String username,String productID,String numberUnitsOrdered){
        
        boolean result = false;
        
        ConnectionPool pool=ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="update savedcart set username=? and productID=? and numberUnitsOrdered=?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            ps.setInt(2, Integer.parseInt(productID));
            ps.setInt(3, Integer.parseInt(numberUnitsOrdered));
            
            int rowsAffected = ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result; 
    }
    
    public boolean updateProductInStock(int productID,int numberUnitsOrdered){
        
        boolean result = false;
        
        ConnectionPool pool=ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="update products set productUnitsInStock=productUnitsInStock-? where productID=?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, numberUnitsOrdered);
            ps.setInt(2, productID);
            
            int rowsAffected = ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result; 
    }
    
    public boolean deleteWholeCart(String username){
        
        boolean result = false;
        
        ConnectionPool pool=ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="delete from savedcart where username=?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            
            int rowsAffected = ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result; 
    }
    
    public boolean validateUsername(String username){
    
        boolean result = false;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try{
            
            Connection conn = pool.getConnection();
            String sql = "select count(*) from users where username=?;";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                int count = rs.getInt(1);
                System.out.println(count);
                if(count == 1){
                    result = true;
                }
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(conn);
            
        }catch (Exception ex){
            System.out.println(ex);
        }
        return result;
    }
    
    public String getUsers(){
    
        String result = "";
        
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try{
            
            Connection conn = pool.getConnection();
            String sql = "select * from users;";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                result = result + rs.getString(1)+","+rs.getString(3)+";";
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(conn);
            
        }catch (Exception ex){
            System.out.println(ex);
        }
        return result;
    }
    
    public boolean addUser(String username,String password){
        
        boolean result = false;
        
        String sql = "insert into users(username,password) values(?,?);";
        ConnectionPool pool = ConnectionPool.getInstance();
        try{
        
            Connection conn = pool.getConnection();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            int rowsAffected =  ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            conn.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result;
    }
    
    public boolean resetPassword(String username){
    
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="UPDATE users SET PASSWORD='password' WHERE username=?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            
            int rowsAffected = ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result;
    }
    
    public boolean toggleStatus(String username){
    
        boolean result = false;
        
        ConnectionPool pool=ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="select userType from users where username=?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getString(1).equals("admin")){
                    PreparedStatement ps1 = conn.prepareCall("update users set userType='user' where username=?;"); 
                    ps1.setString(1, username);
                    
                    int rowsAffected = ps1.executeUpdate();
                    result = (rowsAffected > 0);
                }else{
                    PreparedStatement ps2 = conn.prepareCall("update users set userType='admin' where username=?;"); 
                    ps2.setString(1, username);
                    
                    int rowsAffected = ps2.executeUpdate();
                    result = (rowsAffected > 0);
                }
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result;            
    }
    
    public boolean deleteUser(String username){
        
        boolean result = false;
        
        ConnectionPool pool=ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="delete from users where username=?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            
            int rowsAffected = ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result; 
    }
    
    public boolean updateProduct(String productID,String productDesc,
                                 String productUnitPrice,String productUnitsInStock){
        
        boolean result = false;
        
        ConnectionPool pool=ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="update products set productDesc=?,productUnitPrice=?,productUnitsInStock=? where productID=?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, productDesc);
            ps.setFloat(2, Float.parseFloat(productUnitPrice));
            ps.setInt(3, Integer.parseInt(productUnitsInStock));
            ps.setInt(4, Integer.parseInt(productID));
            
            int rowsAffected = ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result; 
    }
    
    public boolean deleteProduct(String productID){
        
        boolean result = false;
        
        ConnectionPool pool=ConnectionPool.getInstance();
        
        try{
        
            Connection conn=pool.getConnection();
            String sql="delete from products where productID=?;";
            
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, Integer.parseInt(productID));
            
            int rowsAffected = ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            pool.freeConnection(conn);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result; 
    }
    
    public boolean addProduct(String productDesc,String productUnitPrice,String productUnitsInStock){
        
        boolean result = false;
        
        String sql = "insert into products(productDesc,productUnitPrice,productUnitsInStock) values(?,?,?);";
        ConnectionPool pool = ConnectionPool.getInstance();
        try{
        
            Connection conn = pool.getConnection();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productDesc);
            ps.setFloat(2, Float.parseFloat(productUnitPrice));
            ps.setInt(3, Integer.parseInt(productUnitsInStock));
            
            int rowsAffected =  ps.executeUpdate();
            
            result = (rowsAffected > 0);
            
            ps.close();
            conn.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return result;
    }
    
}
