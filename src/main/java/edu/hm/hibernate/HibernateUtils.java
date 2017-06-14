package edu.hm.hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.nio.file.Paths;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}