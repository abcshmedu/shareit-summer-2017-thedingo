package edu.hm.cbrammer.stachl.swa.models;

import edu.hm.hibernate.HibernateTransaction;
import edu.hm.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MediaPersistenceImpl implements MediaPersistence
{

    public MediaPersistenceImpl()
    {
    }

    @Override
    public Book[] getBooks()
    {
        Transaction trans = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
        CriteriaBuilder builder = HibernateUtils.getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        Query<Book> q = HibernateUtils.getSessionFactory().getCurrentSession().createQuery(query);

        List<Book> results = q.getResultList();
        trans.commit();
        return results.toArray(new Book[results.size()]);
    }

    @Override
    public Book getBookIfExists(String isbn)
    {
        Transaction trans = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
        CriteriaBuilder builder = HibernateUtils.getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        query.where(builder.equal(root.get("isbn"), isbn));

        Query<Book> q = HibernateUtils.getSessionFactory().getCurrentSession().createQuery(query);

        List<Book> results = q.getResultList();
        trans.commit();
        return results.size() > 0 ? results.get(0) : null;
    }

    @Override
    public void updateOrCreate(Book book)
    {
        try (HibernateTransaction t = new
                HibernateTransaction())
        {
            t.saveOrUpdate(book);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public Disc[] getDiscs()
    {
        Transaction trans = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
        CriteriaBuilder builder = HibernateUtils.getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Disc> query = builder.createQuery(Disc.class);
        Root<Disc> root = query.from(Disc.class);

        Query<Disc> q = HibernateUtils.getSessionFactory().getCurrentSession().createQuery(query);

        List<Disc> results = q.getResultList();
        trans.commit();
        return results.toArray(new Disc[results.size()]);
    }

    @Override
    public Disc getDiscIfExists(String barcode)
    {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Disc> query = builder.createQuery(Disc.class);
        Root<Disc> root = query.from(Disc.class);

        query.where(builder.equal(root.get("barcode"), barcode));

        Query<Disc> q = session.createQuery(query);

        List<Disc> results = q.getResultList();
        trans.commit();
        return results.size() > 0 ? results.get(0) : null;
    }

    @Override
    public void updateOrCreate(Disc disc)
    {
        try (HibernateTransaction t = new
                HibernateTransaction())
        {
            t.saveOrUpdate(disc);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
