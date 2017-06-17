package edu.hm.cbrammer.stachl.swa.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book extends Medium
{
    private String author;

    @Id
    private String isbn;

    public Book(String title, String author, String isbn)
    {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    public Book()
    {
        super("");
        this.author = "";
        this.isbn = "";
    }

    public String getAuthor()
    {
        return author;
    }

    public String getIsbn()
    {
        return isbn;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (!getAuthor().equals(book.getAuthor())) return false;
        if (!getTitle().equals(book.getTitle())) return false;
        return getIsbn().equals(book.getIsbn());
    }
}
