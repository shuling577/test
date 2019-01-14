/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import database.DBoperations;
import module.ShoppingCart1;
import java.util.ArrayList;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
public class ShoppingCarttListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        DBoperations dbOps = new DBoperations();
        String username = (String)(se.getSession().getAttribute("user"));
        
        ArrayList<ShoppingCart1> list = ((ArrayList<ShoppingCart1>)se.getSession().getAttribute("shoppingCart1"));
        for(int i=0;i<list.size();i++){
            dbOps.addToCart(username, list.get(i).getProductID(), list.get(i).getNumberUnitsOrdered());
        }
        
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
    }
}
