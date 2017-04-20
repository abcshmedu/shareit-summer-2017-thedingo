package edu.hm.cbrammer.stachl.swa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.cbrammer.stachl.swa.models.Book;
import edu.hm.cbrammer.stachl.swa.models.Disc;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by stach on 12-Apr-17.
 */
@Path("/media")
public class MediaResource {

    private final MediaService mediaService;

    public MediaResource(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {

        MediaServiceResult result = mediaService.addBook(book);

        return Response.status(result.getStatus())
                .entity(result.getAsJSON())
                .build();
    }

    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("isbn") String isbn) {

        return getSingleBook(isbn);
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Book[] getBooks() {
        return (Book[])mediaService.getBooks();
    }

    @PUT
    @Path("/books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn") String isbn) {

        MediaServiceResult result = mediaService.updateBook(new Book(book.getTitle(),book.getAuthor(),isbn));
        return Response.status(result.getStatus())
                .entity(result.getAsJSON())
                .build();
    }

    //
    // Discs
    //

    @POST
    @Path("/discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc) {

        MediaServiceResult result = mediaService.addDisc(disc);

        return Response.status(result.getStatus())
                .entity(result.getAsJSON())
                .build();

    }

    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Disc getDisc(@PathParam("barcode") String barcode) {
        return getSingleDisc(barcode);
    }

    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Disc[] getDiscs() {
        return (Disc[])mediaService.getDiscs();
    }

    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode") String barcode) {
        MediaServiceResult result = mediaService.updateDisc(new Disc(disc.getTitle(),barcode,disc.getDirector(), disc.getFsk()));
        return Response.status(result.getStatus())
                .entity(result.getAsJSON())
                .build();
    }

    private Book getSingleBook(String isbn){
        return (Book)Arrays.stream(mediaService.getBooks()).filter(b -> ((Book)b).getIsbn() == isbn).findFirst().get();
    }

    private Disc getSingleDisc(String barcode){
        return (Disc)Arrays.stream(mediaService.getDiscs()).filter(b -> ((Disc)b).getBarcode() == barcode).findFirst().get();
    }
}
