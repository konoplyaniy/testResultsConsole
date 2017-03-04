package hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
public abstract class BaseDao<K, T> {
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

    public  SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public abstract void persist(T entity);

    public abstract void update(T entity);

    public abstract T findById(K key);

    public abstract void delete(T entity);

    public abstract List<T> findAll();

    public abstract void deleteAll();

    private static final ThreadLocal<Session> session = new ThreadLocal<Session>();

    public static void closeSession() throws HibernateException {
        Session s = session.get();
        if (s != null) {
            s.close();
            session.remove();
        }
    }

    private Session currentSession;

    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
        closeSession();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.flush();
        currentSession.close();
        closeSession();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
}
