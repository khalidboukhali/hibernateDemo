package org.example.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory getSessionFactory(){
        try{
            // Load Hibernate configuration
            Configuration configuration = new Configuration().configure();
            // Build the SessionFactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to build SessionFactory: " + e.getMessage());
        }
    }
    public static Session getSession(){
        return getSessionFactory().openSession();
    }
}
