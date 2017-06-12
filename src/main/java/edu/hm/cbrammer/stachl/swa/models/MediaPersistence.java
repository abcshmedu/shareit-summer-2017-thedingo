package edu.hm.cbrammer.stachl.swa.models;

public interface MediaPersistence {
    Book[] getBooks();
    Book getBookIfExists(String isbn);
    void updateOrCreate(Book book);

    Disc[] getDiscs();
    Disc getDiscIfExists(String barcode);
    void updateOrCreate(Disc disc);
}
