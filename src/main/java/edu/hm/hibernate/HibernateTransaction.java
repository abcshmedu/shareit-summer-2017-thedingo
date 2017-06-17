package edu.hm.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateTransaction implements AutoCloseable
{

    private final Session session;
    private final Transaction transaction;

    public HibernateTransaction()
    {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
    }

    public void saveOrUpdate(Object o)
    {
        session.saveOrUpdate(o);
    }

    @Override
    public void close() throws Exception
    {
        transaction.commit();
    }
}
