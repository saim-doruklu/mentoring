package com.mentoring.daos;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * CheckStyle checks:
 * Type name 'hibernateUtil' must match pattern '^[A-Z][a-zA-Z0-9]*$'. Class name changed
 *
 * PMD checks:
 * SystemPrintln: Replaced System.err.println() with Logger
 * UseUtilityClass: Made class constructor private
 * AvoidCatchingThrowable: Caught HibernateException instead of throwable
 */
public final class HibernateUtil {

    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class.getName());
    private static final String CONFIG_ERROR = "Hibernate configuration error";

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateUtil(){}

    private static SessionFactory buildSessionFactory() {
        try {
            
            Configuration  configuration = new Configuration().configure();
            
            return configuration.buildSessionFactory();
            
        } catch (HibernateException ex) {

            LOGGER.log(Level.WARNING, CONFIG_ERROR, ex);
            
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
    	
        return SESSION_FACTORY;
    }

    public static Session getSession() {
      return SESSION_FACTORY.openSession();
    }

    public static void shutdown() {
        
        getSessionFactory().close();
    }

}