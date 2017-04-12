package edu.hm.cbrammer.stachl.swa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.cbrammer.stachl.swa.models.Book;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by stach on 12-Apr-17.
 */
@Path("/media")
public class MediaResource {

    private final MediaService mediaService;

    public MediaResource(MediaService mediaService){
        this.mediaService = mediaService;
    }

    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(String json){

        ObjectMapper objectMapper = new ObjectMapper();
        Response response;
        try{
            Book book = objectMapper.readValue(json,Book.class);
            mediaService.addBook(book);
            //TODO Header Esponses from add Book
            response = null;
        }
        catch(IOException e){
            JSONObject object = new JSONObject();
            object.append("code", Response.Status.BAD_REQUEST.getStatusCode());
            object.append("detail", "Error: Json not parsable");
            response = Response.status(Response.Status.BAD_REQUEST)
                    .entity(object.toString())
                    .build();
        }
        return response;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn ){

    }

    //
    // Discs
    //

    @POST
    @Path("/discs")
    @Consumes(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode ){

    }
}
