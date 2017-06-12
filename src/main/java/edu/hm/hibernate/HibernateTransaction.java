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

    public void persist(Object o)
    {
        session.persist(o);
    }

    public void persist(String s, Object o)
    {
        session.persist(s, o);
    }

    @Override
    public void close() throws Exception
    {
        transaction.commit();
    }
}
