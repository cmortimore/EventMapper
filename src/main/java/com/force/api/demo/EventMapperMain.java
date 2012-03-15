package com.force.api.demo;

import com.force.api.ApiConfig;
import com.force.api.ApiVersion;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 *
 */
public class EventMapperMain {

    public static ApiConfig c;
    public static String FORCE_SESSION = "FORCE_SESSION";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{

        String FORCE_USER = System.getenv("FORCE_USER");
        String FORCE_PASSWORD = System.getenv("FORCE_PASSWORD");
        String FORCE_CLIENT_ID = System.getenv("FORCE_CLIENT_ID");
        String FORCE_CLIENT_SECRET = System.getenv("FORCE_CLIENT_SECRET");
        String FORCE_REDIRECT_URI = System.getenv("FORCE_REDIRECT_URI");
        String FORCE_LOGIN_URL = System.getenv("FORCE_LOGIN_URL");

        c = new ApiConfig()
                .setUsername(FORCE_USER)
                .setPassword(FORCE_PASSWORD)
                .setClientId(FORCE_CLIENT_ID)
                .setClientSecret(FORCE_CLIENT_SECRET)
                .setRedirectURI(FORCE_REDIRECT_URI)
                .setLoginEndpoint(FORCE_LOGIN_URL)
                .setApiVersion(ApiVersion.V24);



        String webappDirLocation = "src/main/webapp/";

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "5000";
        }

        Server server = new Server(Integer.valueOf(webPort));
        WebAppContext root = new WebAppContext();

        root.setContextPath("/");
        root.setDescriptor(webappDirLocation+"/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);

        //Parent loader priority is a class loader setting that Jetty accepts.
        //By default Jetty will behave like most web containers in that it will
        //allow your application to replace non-server libraries that are part of the
        //container. Setting parent loader priority to true changes this behavior.
        //Read more here: http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
        root.setParentLoaderPriority(true);

        server.setHandler(root);

        server.start();
        server.join();
    }

}
