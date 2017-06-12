package edu.hm.cbrammer.stachl.swa.controller;


import edu.hm.cbrammer.stachl.swa.models.Book;
import edu.hm.cbrammer.stachl.swa.models.Disc;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/media")
public class MediaResource
{

    private final MediaService mediaService;

    @Inject
    public MediaResource(MediaService mediaService)
    {
        this.mediaService = mediaService;
    }

    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book)
    {
        MediaServiceResult result = mediaService.addBook(book);

        return Response.status(result.getStatus())
                .entity(result.getAsJSON())
                .build();
    }

    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("isbn") String isbn)
    {
        return mediaService.getBook(isbn);
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Book[] getBooks()
    {
        return mediaService.getBooks();
    }

    @PUT
    @Path("/books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn") String isbn)
    {

        MediaServiceResult result = mediaService.updateBook(book, isbn);
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
    public Response createDisc(Disc disc)
    {

        MediaServiceResult result = mediaService.addDisc(disc);

        return Response.status(result.getStatus())
                .entity(result.getAsJSON())
                .build();

    }

    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Disc getDisc(@PathParam("barcode") String barcode)
    {
        return mediaService.getDisc(barcode);
    }

    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Disc[] getDiscs()
    {
        return mediaService.getDiscs();
    }

    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode") String barcode)
    {
        MediaServiceResult result = mediaService.updateDisc(new Disc(disc.getTitle(), barcode, disc.getDirector(), disc.getFsk()));
        return Response.status(result.getStatus())
                .entity(result.getAsJSON())
                .build();
    }
}
