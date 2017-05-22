package edu.hm.cbrammer.stachl.swa.controller;

import io.jsonwebtoken.*;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by stach on 22-May-17.
 */
public class AuthorizationHandler extends HandlerWrapper {

    private static String KEY = "NSfRkNVR0CsgJ324iJFnCQ5CnR/FNrR2TkbSIaYbcOSqpg5oxggpassWmJFx9CxhUKl02T8fCw3Ds+4C5NgzEA==";

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String JWT = baseRequest.getHeader("Authorization");

        String[] requestArray = JWT.split(" ");

        if (requestArray.length == 1) {

            sendResponse(response,Response.SC_BAD_REQUEST,"Missing token. Format: 'bearer %TOKEN%'");

            return;
        } else {
            JWT = requestArray[1];
        }
        try {

            Jwts.parser().setSigningKey(KEY).parseClaimsJws(JWT);

            super.handle(target, baseRequest, request, response);


            //OK, we can trust this JWT

        } catch (Exception e) {

            sendResponse(response,Response.SC_UNAUTHORIZED,"Bad JWT Token. Request new one from Authorization Server");

        }

    }

    private void sendResponse(HttpServletResponse response, int status, String message) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.setContentType("application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", status);
        jsonObject.put("detail", message);

        jsonObject.write(response.getWriter());
        response.getWriter().flush();
    }
}
