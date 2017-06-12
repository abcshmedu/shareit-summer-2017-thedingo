package test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;
import edu.hm.cbrammer.stachl.swa.controller.MediaResource;
import edu.hm.cbrammer.stachl.swa.controller.MediaService;
import edu.hm.cbrammer.stachl.swa.controller.MediaServiceImpl;
import edu.hm.cbrammer.stachl.swa.models.Book;
import edu.hm.cbrammer.stachl.swa.models.Disc;
import edu.hm.cbrammer.stachl.swa.models.MediaPersistence;
import edu.hm.cbrammer.stachl.swa.models.MediaPersistenceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MediaResourceTest
{

    private static MediaResource mediaResource;// = new MediaResource(null);
    private static Injector injector;

    @BeforeClass
    public static void setUp() throws Exception
    {
        injector = Guice.createInjector(new ServletModule()
        {
            @Override
            protected void configureServlets()
            {
                bind(MediaService.class).to(MediaServiceImpl.class);
                bind(MediaPersistence.class).to(MediaPersistenceImpl.class);
            }
        });

        mediaResource = injector.getInstance(MediaResource.class);
    }

    @Test
    public void createBook() throws Exception
    {

        Response result = mediaResource.createBook(new Book("A", "B", "1337"));
        assertEquals(result.getStatus(), Response.Status.CREATED.getStatusCode());
    }

    @Test
    public void createExistingBook() throws Exception
    {

        mediaResource.createBook(new Book("A", "B", "1338"));
        Response result = mediaResource.createBook(new Book("A", "B", "1338"));
        assertEquals(result.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void getBook() throws Exception
    {
        Book testBook = new Book("C", "D", "1234");
        mediaResource.createBook(testBook);
        Book book = mediaResource.getBook("1234");
        assertEquals(book, testBook);
    }

    @Test
    public void getBooks() throws Exception
    {
        Book testBook = new Book("E", "F", "5678");
        Book testBook2 = new Book("G", "H", "9123");
        mediaResource.createBook(testBook);
        mediaResource.createBook(testBook2);

        Book[] books = mediaResource.getBooks();

        boolean book1Found = false;
        boolean book2Found = false;
        for (Book book : books)
        {
            if (book.equals(testBook))
                book1Found = true;
            if (book.equals(testBook2))
                book2Found = true;
        }

        assertTrue(book1Found && book2Found);
    }

    @Test
    public void updateBookTitle() throws Exception
    {
        Book testBook = new Book("T", "I", "58686");
        mediaResource.createBook(testBook);
        Response response = mediaResource.updateBook(new Book("Hallo", "", ""), testBook.getIsbn());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        Book result = mediaResource.getBook(testBook.getIsbn());
        assertEquals(result.getTitle(), "Hallo");
        assertEquals(result.getAuthor(), testBook.getAuthor());
    }

    @Test
    public void updateBookAuthor() throws Exception
    {
        Book testBook = new Book("T", "I", "78965");
        mediaResource.createBook(testBook);
        Response response = mediaResource.updateBook(new Book("", "olive", ""), testBook.getIsbn());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        Book result = mediaResource.getBook(testBook.getIsbn());
        assertEquals(result.getTitle(), testBook.getTitle());
        assertEquals(result.getAuthor(), "olive");
    }

    @Test
    public void updateBookISBN() throws Exception
    {
        Book testBook = new Book("T", "I", "78965");
        mediaResource.createBook(testBook);
        Response response = mediaResource.updateBook(new Book("", "", "test"), testBook.getIsbn());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        Book result = mediaResource.getBook(testBook.getIsbn());
        assertEquals(result.getTitle(), testBook.getTitle());
        assertEquals(result.getAuthor(), testBook.getAuthor());
        Book notFound = mediaResource.getBook("test");
        assertEquals(notFound, null);
    }

    @Test
    public void updateBookNotFound() throws Exception
    {

        Response response = mediaResource.updateBook(new Book("", "olive", ""), "irgendwas");
        assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());

    }

    @Test
    public void createDisc() throws Exception
    {

        Response result = mediaResource.createDisc(new Disc("A", "kkkkk", "C", 12));
        assertEquals(result.getStatus(), Response.Status.CREATED.getStatusCode());

    }

    @Test
    public void createExistingDisc() throws Exception
    {

        mediaResource.createDisc(new Disc("A", "B", "C", 12));
        Response result = mediaResource.createDisc(new Disc("A", "B", "C", 12));
        assertEquals(result.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void getDisc() throws Exception
    {
        Disc testDisc = new Disc("C", "D", "author", 12);
        mediaResource.createDisc(testDisc);
        Disc disc = mediaResource.getDisc("D");
        assertEquals(disc, testDisc);

    }

    @Test
    public void getDiscs() throws Exception
    {

        Disc testDisc = new Disc("E", "F", "james", 16);
        Disc testDisc2 = new Disc("G", "H", "hannes", 18);
        mediaResource.createDisc(testDisc);
        mediaResource.createDisc(testDisc2);

        Disc[] discs = mediaResource.getDiscs();

        boolean disc1Found = false;
        boolean disc2Found = false;
        for (Disc disc : discs)
        {
            if (disc.equals(testDisc))
                disc1Found = true;
            if (disc.equals(testDisc2))
                disc2Found = true;
        }

        assertTrue(disc1Found && disc2Found);
    }

    @Test
    public void updateDiscTitle() throws Exception
    {
        Disc testDisc = new Disc("T", "I", "inge", 15);
        mediaResource.createDisc(testDisc);
        Response response = mediaResource.updateDisc(new Disc("Hallo", "", "", -1), testDisc.getBarcode());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        Disc result = mediaResource.getDisc(testDisc.getBarcode());
        assertEquals(result.getTitle(), "Hallo");
        assertEquals(result.getDirector(), testDisc.getDirector());
        assertEquals(result.getFsk(), testDisc.getFsk());
    }

    @Test
    public void updateDiscDirector() throws Exception
    {

        Disc testDisc = new Disc("h", "df", "inge", 15);
        mediaResource.createDisc(testDisc);
        Response response = mediaResource.updateDisc(new Disc("", "", "jürgen", -1), testDisc.getBarcode());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        Disc result = mediaResource.getDisc(testDisc.getBarcode());
        assertEquals(result.getTitle(), testDisc.getTitle());
        assertEquals(result.getDirector(), "jürgen");
        assertEquals(result.getFsk(), testDisc.getFsk());
    }

    @Test
    public void updateDiscFSK() throws Exception
    {

        Disc testDisc = new Disc("T", "zuuu", "inge", 15);
        mediaResource.createDisc(testDisc);
        Response response = mediaResource.updateDisc(new Disc("", "", "", 7), testDisc.getBarcode());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        Disc result = mediaResource.getDisc(testDisc.getBarcode());
        assertEquals(result.getTitle(), testDisc.getTitle());
        assertEquals(result.getDirector(), testDisc.getDirector());
        assertEquals(result.getFsk(), 7);
    }

    @Test
    public void updateDiscBarcode() throws Exception
    {

        Disc testDisc = new Disc("T", "I", "inge", 15);
        mediaResource.createDisc(testDisc);
        Response response = mediaResource.updateDisc(new Disc("", "haaaaa", "", -1), testDisc.getBarcode());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        Disc result = mediaResource.getDisc(testDisc.getBarcode());
        assertEquals(result.getTitle(), testDisc.getTitle());
        assertEquals(result.getDirector(), testDisc.getDirector());
        assertEquals(result.getFsk(), testDisc.getFsk());
        result = mediaResource.getDisc("haaaaa");
        assertEquals(result, null);
    }

    @Test
    public void updateDiscNotFound() throws Exception
    {

        Response response = mediaResource.updateDisc(new Disc("", "", "", -1), "i don't exist");
        assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());


    }

}