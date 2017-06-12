package edu.hm.cbrammer.stachl.swa.models;

public class MediaPersistenceImpl implements MediaPersistence
{
    @Override
    public Book[] getBooks()
    {
        return new Book[0];
    }

    @Override
    public Book getBookIfExists(String isbn)
    {
        return null;
    }

    @Override
    public void updateOrCreate(Book book)
    {

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
