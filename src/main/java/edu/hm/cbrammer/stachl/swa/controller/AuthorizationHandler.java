package edu.hm.cbrammer.stachl.swa.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by stach on 22-May-17.
 */
public class AuthorizationHandler extends HandlerWrapper {

    private static String KEY = "NSfRkNVR0CsgJ324iJFnCQ5CnR/FNrR2TkbSIaYbcOSqpg5oxggpassWmJFx9CxhUKl02T8fCw3Ds+4C5NgzEA==";

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String JWT = baseRequest.getHeader("Authorization");

        JWT = JWT.split(" ")[1];

        try {

            Jwts.parser().setSigningKey(KEY).parseClaimsJws(JWT);

            super.handle(target, baseRequest, request, response);

          //OK, we can trust this JWT

        } catch (SignatureException | MalformedJwtException e) {
            System.out.println("Error");
        }

    }
}
