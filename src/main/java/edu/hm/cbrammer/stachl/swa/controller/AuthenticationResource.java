package edu.hm.cbrammer.stachl.swa.controller;

import edu.hm.cbrammer.stachl.swa.models.User;
import edu.hm.cbrammer.stachl.swa.models.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Base64;

import static edu.hm.cbrammer.stachl.swa.models.UserRepository.SECRET;

@Path("/")
public class AuthenticationResource
{
    public AuthenticationResource()
    {
    }

    @POST
    @Path("/auth")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTokenForUser(User user)
    {
        final MediaServiceResult result;
        if(UserRepository.authenticateUser(user))
        {
            result = new MediaServiceResult(Response.Status.OK,"Authentication successful.");

            String compactJws = Jwts.builder()
                    .setSubject(user.getName())
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
            result.addToJSON("token",compactJws);
        }
        else
        {
            result = new MediaServiceResult(Response.Status.UNAUTHORIZED,"Password or username was incorrect");
        }


        return Response.status(result.getStatus())
                .entity(result.getAsJSON())
                .build();
    }
}
