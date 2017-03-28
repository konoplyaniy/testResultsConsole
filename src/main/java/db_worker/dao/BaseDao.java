package db_worker.dao;


import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class BaseDao<K, T> {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
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

    private Session currentSession;

    private static Transaction currentTransaction;

    public Session openCurrentSession() {
        if (currentSession == null || !currentSession.isOpen())
            currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        if (currentSession != null && currentSession.isOpen()) {
            currentSession.close();
            currentSession = null;
        }
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.flush();
        currentSession.close();
        /*closeSession();*/
    }

    public Session getCurrentSession() {
        return currentSession;
    }
}