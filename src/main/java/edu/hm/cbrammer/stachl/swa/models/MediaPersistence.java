package edu.hm.cbrammer.stachl.swa.models;

import edu.hm.cbrammer.stachl.swa.thirdparty.Isbn;

public interface MediaPersistence {
    Book[] getBooks();
    Book getBookIfExists(Isbn isbn);
    void updateOrCreate(Book book);

    Disc[] getDiscs();
    Disc getDiscIfExists(String barcode);
    void updateOrCreate(Disc disc);
}
