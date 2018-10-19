package com.mentoring.daos;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            
            Configuration  configuration = new Configuration().configure();
            
            return configuration.buildSessionFactory();
            
        } catch (Throwable ex) {
            
            System.err.println("Initial SessionFactory creation failed." + ex);
            
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
    	
        return sessionFactory;
    }

    public static Session getSession() {
      return sessionFactory.openSession();
    }

    public static void shutdown() {
        
        getSessionFactory().close();
    }

}