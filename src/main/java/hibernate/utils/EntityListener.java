package hibernate.utils;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;

import javax.persistence.PostLoad;


/**
 * Created by Sergiy.K on 27-Jan-17.
 */
public class EntityListener extends EmptyInterceptor {
    @Override
    public void afterTransactionBegin(Transaction tx) {
        DBLogger.info("Connection created");
        super.afterTransactionBegin(tx);
    }

    @PostLoad
    void postLoad(Object object) {
        DBLogger.info("Load");
    }
}
