package test;

import edu.hm.cbrammer.stachl.swa.controller.MediaResource;
import edu.hm.cbrammer.stachl.swa.controller.MediaService;
import edu.hm.cbrammer.stachl.swa.controller.MediaServiceImpl;
import edu.hm.cbrammer.stachl.swa.controller.MediaServiceResult;
import edu.hm.cbrammer.stachl.swa.models.Book;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;


public class MediaResourceTest {

    private static MediaResource mediaResource = new MediaResource();

    @Test
    public void createBook() throws Exception {

        Response result = mediaResource.createBook(new Book("A","B", "1337"));
        assertEquals(result.getStatus(),Response.Status.CREATED.getStatusCode());
    }

    @Test
    public void createExistingBook() throws Exception {

        mediaResource.createBook(new Book("A","B", "1338"));
        Response result = mediaResource.createBook(new Book("A","B", "1338"));
        assertEquals(result.getStatus(),Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void getBook() throws Exception {
        Book testBook = new Book("C", "D", "1234");
        mediaResource.createBook(testBook);
        Book book = mediaResource.getBook("1234");
        assertEquals(book,testBook);
    }

    @Test
    public void getBooks() throws Exception {
        Book testBook = new Book("E", "F",  "5678");
        Book testBook2 = new Book("G", "H",  "9123");
        mediaResource.createBook(testBook);
        mediaResource.createBook(testBook2);

        Book[] books = mediaResource.getBooks();

        boolean book1Found = false;
        boolean book2Found = false;
        for(Book book: books)
        {
            if(book.equals(testBook))
                book1Found = true;
            if(book.equals(testBook2))
                book2Found = true;
        }

        assertTrue(book1Found && book2Found);
    }

    @Test
    public void updateBookTitle() throws Exception {
        Book testBook = new Book("T", "I", "58686");
        mediaResource.createBook(testBook);
        Response response = mediaResource.updateBook(new Book("Hallo","", ""),testBook.getIsbn());
        assertEquals(response.getStatus(),Response.Status.OK.getStatusCode());
        Book result = mediaResource.getBook(testBook.getIsbn());
        assertEquals(result.getTitle(), "Hallo");
        assertEquals(result.getAuthor(), testBook.getAuthor());
    }

    @Test
    public void updateBookAuthor() throws Exception {
        Book testBook = new Book("T", "I", "78965");
        mediaResource.createBook(testBook);
        Response response = mediaResource.updateBook(new Book("","olive", ""),testBook.getIsbn());
        assertEquals(response.getStatus(),Response.Status.OK.getStatusCode());
        Book result = mediaResource.getBook(testBook.getIsbn());
        assertEquals(result.getTitle(), testBook.getTitle());
        assertEquals(result.getAuthor(), "olive");
    }

    @Test
    public void updateBookAISBN() throws Exception {
        Book testBook = new Book("T", "I", "78965");
        mediaResource.createBook(testBook);
        Response response = mediaResource.updateBook(new Book("","", "test"),testBook.getIsbn());
        assertEquals(response.getStatus(),Response.Status.OK.getStatusCode());
        Book result = mediaResource.getBook(testBook.getIsbn());
        assertEquals(result.getTitle(), testBook.getTitle());
        assertEquals(result.getAuthor(), testBook.getAuthor());
        Book notFound = mediaResource.getBook("test");
        assertEquals(notFound,null);
    }

    @Test
    public void updateBookNotFound() throws Exception {

        Response response = mediaResource.updateBook(new Book("","olive", ""),"irgendwas");
        assertEquals(response.getStatus(),Response.Status.NOT_FOUND.getStatusCode());

    }
    @Test
    public void createDisc() throws Exception {

    }
    @Test
    public void createExistingDisc() throws Exception {
    }

    @Test
    public void getDisc() throws Exception {
    }

    @Test
    public void getDiscs() throws Exception {
    }

    @Test
    public void updateDisc() throws Exception {
    }

}