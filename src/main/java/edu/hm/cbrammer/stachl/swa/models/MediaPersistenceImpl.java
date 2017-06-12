package edu.hm.cbrammer.stachl.swa.models;

import edu.hm.cbrammer.stachl.swa.thirdparty.Isbn;
import edu.hm.hibernate.HibernateTransaction;
import edu.hm.hibernate.HibernateUtils;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MediaPersistenceImpl implements MediaPersistence
{

    @Override
    public Book[] getBooks()
    {
        CriteriaBuilder builder = HibernateUtils.getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);

        Query<Book> q = HibernateUtils.getSessionFactory().getCurrentSession().createQuery(query);

        List<Book> results = q.getResultList();
        return results.toArray(new Book[results.size()]);
    }

    @Override
    public Book getBookIfExists(Isbn isbn)
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
            t.persist(book.getIsbn());
            t.persist(book);
        } catch (Exception e)
        {

        }
    }


    @Override
    public Disc[] getDiscs()
    {
        return new Disc[0];
    }

    @Override
    public Disc getDiscIfExists(String barcode)
    {
        return null;
    }

    @Override
    public void updateOrCreate(Disc disc)
    {

    }
}
