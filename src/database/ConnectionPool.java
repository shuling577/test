/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author john
 */
public class ConnectionPool {
    private static ConnectionPool pool=null;
    private static DataSource dataSource=null;
    
    private ConnectionPool() {
        try {
            InitialContext ic=new InitialContext();
            dataSource=(DataSource)ic.lookup("java:/comp/env/jdbc/buystuff");
            
        } catch (NamingException ex) {
            System.out.println(ex);
        }
    }
    
    public static synchronized ConnectionPool getInstance() {
        if (pool==null) {
            pool=new ConnectionPool();
        }
        
        return pool;
    }
    
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        return null;
    }
    
    public void freeConnection(Connection conn) {
        try {
            conn.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
