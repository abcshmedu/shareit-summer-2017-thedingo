package edu.hm.cbrammer.stachl.swa.controller;


import edu.hm.cbrammer.stachl.swa.thirdparty.Isbn;
import edu.hm.cbrammer.stachl.swa.models.Book;
import edu.hm.cbrammer.stachl.swa.models.Disc;
import edu.hm.cbrammer.stachl.swa.models.MediaPersistence;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class MediaServiceImpl implements MediaService
{
    private final MediaPersistence mediaPersistence;

    @Inject
    public MediaServiceImpl(MediaPersistence mediaPersistence)
    {
        this.mediaPersistence = mediaPersistence;
    }

    @Override
    public MediaServiceResult addBook(Book book)
    {
        final MediaServiceResult result;
        if (mediaPersistence.getBookIfExists(book.getIsbn()) != null)
        {
            result = new MediaServiceResult(Response.Status.BAD_REQUEST, String.format("Book with ISBN '%s' already exists.", book.getIsbn()));
        }
        else
        {
            mediaPersistence.updateOrCreate(book);
            result = new MediaServiceResult(Response.Status.CREATED, String.format("Book with ISBN '%s' was successfully created.", book.getIsbn()));
        }
        return result;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc)
    {
        final MediaServiceResult result;
        if (mediaPersistence.getDiscIfExists(disc.getBarcode()) != null)
        {
            result = new MediaServiceResult(Response.Status.BAD_REQUEST, String.format("Disc with Barcode '%s' already exists.", disc.getBarcode()));
        }
        else
        {
            mediaPersistence.updateOrCreate(disc);
            result = new MediaServiceResult(Response.Status.CREATED, String.format("Disc with Barcode '%s' was successfully created.", disc.getBarcode()));
        }
        return result;
    }

    @Override
    public MediaServiceResult updateBook(Book book, String isbn)
    {
        final MediaServiceResult result;

        // Check Isbn
        final String checkedIsbn;
        try
        {
            checkedIsbn = Isbn.of(isbn);
        } catch (IllegalArgumentException e)
        {
            return new MediaServiceResult(Response.Status.BAD_REQUEST, String.format("The given ISBN (%s) was not valid", isbn));
        }


        final Book savedBook = mediaPersistence.getBookIfExists(checkedIsbn);
        if (savedBook != null)
        {
            final String newTitle = book.getTitle().trim().isEmpty() ? savedBook.getTitle() : book.getTitle().trim();
            final String newAuthor = book.getAuthor().trim().isEmpty() ? savedBook.getAuthor() : book.getAuthor().trim();

            mediaPersistence.updateOrCreate(new Book(newTitle, newAuthor, checkedIsbn));

            result = new MediaServiceResult(Response.Status.OK, String.format("Book with ISBN '%s' was successfully updated.", book.getIsbn()));
        }
        else
        {
            result = new MediaServiceResult(Response.Status.NOT_FOUND, String.format("Book with ISBN '%s' wasn't found.", book.getIsbn()));
        }
        return result;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc)
    {
        final MediaServiceResult result;
        final Disc savedDisc = mediaPersistence.getDiscIfExists(disc.getBarcode());
        if (savedDisc != null)
        {

            final String newTitle = disc.getTitle().trim().isEmpty() ? savedDisc.getTitle() : disc.getTitle().trim();
            final String newDirector = disc.getDirector().trim().isEmpty() ? savedDisc.getDirector() : disc.getDirector().trim();
            final int newFsk = disc.getFsk() == -1 ? savedDisc.getFsk() : disc.getFsk();

            mediaPersistence.updateOrCreate(new Disc(newTitle, disc.getBarcode(), newDirector, newFsk));

            result = new MediaServiceResult(Response.Status.OK, String.format("Disc with Barcode '%s' was successfully updated.", disc.getBarcode()));
        }
        else
        {
            result = new MediaServiceResult(Response.Status.NOT_FOUND, String.format("Disc with Barcode '%s' wasn't found.", disc.getBarcode()));
        }
        return result;
    }

    @Override
    public Book[] getBooks()
    {
        return mediaPersistence.getBooks();
    }

    @Override
    public Book getBook(String isbn)
    {
        try
        {
            final String checkedIsbn = Isbn.of(isbn);
            return mediaPersistence.getBookIfExists(checkedIsbn);
        } catch (IllegalArgumentException e)
        {
            return null;
        }
    }

    @Override
    public Disc[] getDiscs()
    {
        return mediaPersistence.getDiscs();
    }

    @Override
    public Disc getDisc(String barcode)
    {
        return mediaPersistence.getDiscIfExists(barcode);
    }
}
