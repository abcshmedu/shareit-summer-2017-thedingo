package edu.hm.cbrammer.swa.models;

public class Book extends Medium
{
    private final String author;
    private final String isbn;

    public Book(String title, String author, String isbn)
    {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    private Book()
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
