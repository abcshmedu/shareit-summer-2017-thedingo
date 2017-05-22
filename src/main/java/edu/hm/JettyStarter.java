package edu.hm;

import io.jsonwebtoken.impl.crypto.MacProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.security.Key;
import java.util.Base64;

/**
 * Start the application without an AppServer like tomcat.
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class JettyStarter {

    public static final String APP_URL = "/";
    public static final int PORT = 8084;
    public static final String WEBAPP_DIR = "./src/main/webapp/";

    /**
     * Deploy local directories using Jetty without needing a container-based deployment.
     * @param args unused
     * @throws Exception might throw for several reasons.
     */
    public static void main(String... args) throws Exception {

        Key secretKey = MacProvider.generateKey();
        System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));

        Server jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        System.out.println("Jetty listening on port " + PORT);
        jetty.join();
    }

}
