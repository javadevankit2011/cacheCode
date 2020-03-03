package com.cache.rest;

import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent event)  { 
    	
    }
    
    public void contextInitialized(ServletContextEvent event)  { 
    	String config = "/ehcache1.xml";
    	URL url = getClass().getResource(config);
    	Cache cache = CacheManager.create(url).getCache("service");
    	ServletContext context =  event.getServletContext();
    	context.setAttribute("cache", cache);
    }
}
