package edu.hm.cbrammer.stachl.swa.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

/**
 * Created by stach on 12-Apr-17.
 */
@Path("/media")
public class MediaResource {

    @POST
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(){

    }

    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn ){

    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(){}

    @PUT
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn ){

    }

    //
    // Discs
    //

    @POST
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDisc(){

    }

    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode ){

    }

    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(){}

    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode ){

    }
}
