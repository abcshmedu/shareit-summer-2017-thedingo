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
            response.setCharacterEncoding("UTF-8");
            response.setStatus(Response.SC_BAD_REQUEST);
            response.setContentType("application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "400");
            jsonObject.put("detail", "Missing token. Format: 'bearer %TOKEN%'");

            jsonObject.write(response.getWriter());
            response.getWriter().flush();


            return;
        } else {
            JWT = requestArray[1];
        }
        try {

            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(JWT).getBody();

            super.handle(target, baseRequest, request, response);


            //OK, we can trust this JWT

        } catch (Exception e) {
            
            response.setCharacterEncoding("UTF-8");
            response.setStatus(Response.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "401");
            jsonObject.put("detail", "Bad JWT Token. Request new one from Authorization Server");

            jsonObject.write(response.getWriter());
            response.getWriter().flush();
        }

    }
}
