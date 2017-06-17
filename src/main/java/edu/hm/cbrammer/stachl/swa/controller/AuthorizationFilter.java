package edu.hm.cbrammer.stachl.swa.controller;

import io.jsonwebtoken.Jwts;
import org.eclipse.jetty.server.Response;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by stach on 22-May-17.
 */
public class AuthorizationFilter implements Filter {

    private static String KEY = "NSfRkNVR0CsgJ324iJFnCQ5CnR/FNrR2TkbSIaYbcOSqpg5oxggpassWmJFx9CxhUKl02T8fCw3Ds+4C5NgzEA==";

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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if(servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;


            String JWT = request.getHeader("Authorization");
            if (JWT == null) {
                sendResponse(response, Response.SC_UNAUTHORIZED, "Bad JWT Token. Request new one from Authorization Server");
                return;
            }
            String[] requestArray = JWT.split(" ");

            if (requestArray.length == 1) {

                sendResponse(response, Response.SC_BAD_REQUEST, "Missing token. Format: 'bearer %TOKEN%'");

                return;
            } else {
                JWT = requestArray[1];
            }
            try {

                Jwts.parser().setSigningKey(KEY).parseClaimsJws(JWT);

                //OK, we can trust this JWT
                filterChain.doFilter(servletRequest, servletResponse);

            } catch (Exception e) {

                sendResponse(response, Response.SC_UNAUTHORIZED, "Bad JWT Token. Request new one from Authorization Server");

            }
        }



    }

    @Override
    public void destroy() {

    }
}
