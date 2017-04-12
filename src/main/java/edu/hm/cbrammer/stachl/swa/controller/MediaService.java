package edu.hm.cbrammer.stachl.swa.controller;

import edu.hm.cbrammer.stachl.swa.models.Book;
import edu.hm.cbrammer.stachl.swa.models.Disc;
import edu.hm.cbrammer.stachl.swa.models.Medium;

import javax.ws.rs.core.Response;

/**
 * Created by stach on 12-Apr-17.
 */
public interface MediaService {

    Response.Status addBook(Book book);

    Response.Status addDisc(Disc disc);

    Response.Status updateBook(Book book);

    Response.Status updateDisc(Disc disc);

    Medium[] getBooks();

    Medium[] getDiscs();
}
