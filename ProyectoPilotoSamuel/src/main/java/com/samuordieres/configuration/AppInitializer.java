package com.samuordieres.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
 
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
 
public class AppInitializer implements WebApplicationInitializer {
 
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context
          = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.samuordieres.configuration");   
 
        servletContext.addListener(new ContextLoaderListener(context));
 
        ServletRegistration.Dynamic dispatcher = servletContext
          .addServlet("dispatcher", new DispatcherServlet(context));
         
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
 
}
