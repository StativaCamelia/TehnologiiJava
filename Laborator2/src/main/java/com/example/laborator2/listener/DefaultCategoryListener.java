package com.example.laborator2.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener for servlet start-up. On start-up it will get a init parameter and saves it on the servlet context
 */
@WebListener
public class DefaultCategoryListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext c = servletContextEvent.getServletContext();
        String defaultCategory = c.getInitParameter("category");
        c.setAttribute("category", defaultCategory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
