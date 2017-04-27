package edu.hm.cbrammer.stachl.swa.controller;

import org.json.JSONObject;

import javax.ws.rs.core.Response;

/**
 * Created by stach on 12-Apr-17.
 */
public class MediaServiceResult {

    private final Response.Status status;
    private final String errorMessage;

    public Response.Status getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public MediaServiceResult(Response.Status status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getAsJSON(){
        JSONObject object = new JSONObject();
        object.append("code", getStatus().getStatusCode());
        object.append("detail", getErrorMessage());
        return object.toString();
    }

}
