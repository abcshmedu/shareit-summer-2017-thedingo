package edu.hm.cbrammer.stachl.swa.models;

import edu.hm.cbrammer.stachl.swa.thirdparty.Isbn;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Book extends Medium
{
    private String author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ISBN", unique = true, updatable = false)
    private Isbn isbn;

    public Book(String title, String author, Isbn isbn)
    {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    private Book()
    {
        super("");
        this.author = "";
        this.isbn = null;
    }

    public String getAuthor()
    {
        return author;
    }

    public Isbn getIsbn()
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

    @Override
    public String toString()
    {
        return "Book{" +
                "author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
