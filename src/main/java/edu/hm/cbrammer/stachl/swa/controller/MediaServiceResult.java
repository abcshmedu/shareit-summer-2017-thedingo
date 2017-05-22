package edu.hm.cbrammer.stachl.swa.controller;

import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class MediaServiceResult
{

    private final Response.Status status;
    private final String errorMessage;
    private final Map<String, String> additionalFields = new HashMap<>();

    public Response.Status getStatus()
    {
        return status;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public MediaServiceResult(Response.Status status, String errorMessage)
    {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public void addToJSON(String key, String value)
    {
        additionalFields.put(key, value);
    }

    public String getAsJSON()
    {
        JSONObject object = new JSONObject();
        object.append("code", getStatus().getStatusCode());
        object.append("detail", getErrorMessage());

        for (Map.Entry<String, String> entry : additionalFields.entrySet())
        {
            object.append(entry.getKey(), entry.getValue());
        }

        return object.toString();
    }


}