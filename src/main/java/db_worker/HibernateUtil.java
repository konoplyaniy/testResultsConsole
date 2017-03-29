package db_worker;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

   /* private static Session currentSession;

    private static Transaction currentTransaction;

    public static Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public static Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public static void closeCurrentSession() {
        if (currentSession != null && currentSession.isOpen()) {
            currentSession.close();
            currentSession = null;
        }
    }

    public static void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.flush();
        if (currentSession != null && currentSession.isOpen()) {
            currentSession.close();
            currentSession = null;
            System.out.println("close session with transaction");
        } else {
            System.out.println("session with tr is closed already");
        }
        *//*currentSession.close();*//*
        *//*closeSession();*//*
    }

    public static Session getCurrentSession() {
        return currentSession;
    }*/

}