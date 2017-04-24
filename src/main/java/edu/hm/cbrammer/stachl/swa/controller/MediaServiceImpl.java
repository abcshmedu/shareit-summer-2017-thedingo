package edu.hm.cbrammer.stachl.swa.controller;

import edu.hm.cbrammer.stachl.swa.models.Book;
import edu.hm.cbrammer.stachl.swa.models.Disc;
import edu.hm.cbrammer.stachl.swa.models.Medium;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class MediaServiceImpl implements MediaService
{
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Disc> discs = new HashMap<>();

    @Override
    public MediaServiceResult addBook(Book book)
    {
        final MediaServiceResult result;
        if(books.containsKey(book.getIsbn()))
        {
            result = new MediaServiceResult(Response.Status.BAD_REQUEST, String.format("Book with ISBN '%s' already exists.", book.getIsbn()));
        }
        else
        {
            books.put(book.getIsbn(),book);
            result = new MediaServiceResult(Response.Status.CREATED, String.format("Book with ISBN '%s' was successfully created.", book.getIsbn()));
        }
        return result;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc)
    {
        final MediaServiceResult result;
        if(books.containsKey(disc.getBarcode()))
        {
            result = new MediaServiceResult(Response.Status.BAD_REQUEST, String.format("Disc with Barcode '%s' already exists.", disc.getBarcode()));
        }
        else
        {
            discs.put(disc.getBarcode(),disc);
            result = new MediaServiceResult(Response.Status.CREATED, String.format("Disc with Barcode '%s' was successfully created.", disc.getBarcode()));
        }
        return result;
    }

    @Override
    public MediaServiceResult updateBook(Book book)
    {
        final MediaServiceResult result;
        if(books.containsKey(book.getIsbn()))
        {
            final Book currentBook = books.get(book.getIsbn());

            final String newTitle = book.getTitle().trim().isEmpty() ? currentBook.getTitle() : book.getTitle().trim();
            final String newAuthor = book.getAuthor().trim().isEmpty() ? currentBook.getAuthor() : book.getAuthor().trim();

            books.put(book.getIsbn(), new Book(newTitle,newAuthor,book.getIsbn()));

            result = new MediaServiceResult(Response.Status.CREATED, String.format("Book with ISBN '%s' was successfully updated.", book.getIsbn()));
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
        if(books.containsKey(disc.getBarcode()))
        {
            final Disc currentDisc = discs.get(disc.getBarcode());

            final String newTitle = disc.getTitle().trim().isEmpty() ? currentDisc.getTitle() : disc.getTitle().trim();
            final String newDirector = disc.getDirector().trim().isEmpty() ? currentDisc.getDirector() : disc.getDirector().trim();
            final int newFsk = disc.getFsk() == -1 ? currentDisc.getFsk() : disc.getFsk();

            discs.put(disc.getBarcode(), new Disc(newTitle,disc.getBarcode(),newDirector,newFsk));

            result = new MediaServiceResult(Response.Status.CREATED, String.format("Disc with Barcode '%s' was successfully updated.", disc.getBarcode()));
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
        return books.values().toArray(new Book[books.size()]);
    }

    @Override
    public Disc[] getDiscs()
    {
        return discs.values().toArray(new Disc[books.size()]);
    }
}
