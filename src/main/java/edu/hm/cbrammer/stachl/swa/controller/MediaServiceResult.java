package edu.hm.cbrammer.stachl.swa.controller;

import javax.ws.rs.core.Response;

/**
 * Created by stach on 12-Apr-17.
 */
public enum MediaServiceResult {
    OK(Response.Status.OK),

    CREATED(Response.Status.CREATED),

    BAD_REQUEST(Response.Status.BAD_REQUEST),

    UNAUTHORIZED(Response.Status.UNAUTHORIZED),

    NOT_FOUND(Response.Status.NOT_FOUND),

    INTERNAL_SERVER_ERROR(Response.Status.INTERNAL_SERVER_ERROR);


    private final Response.Status status;

    MediaServiceResult(Response.Status status){

        this.status = status;

    }

    public int getCode() {
        return status.getStatusCode();
    }

    public Response.Status getStatus() {
        return status;
    }

}
