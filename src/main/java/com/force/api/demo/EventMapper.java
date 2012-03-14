package com.force.api.demo;

import com.force.api.ApiConfig;
import com.force.api.ForceApi;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EventMapper extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.err.println("Init Servlet");
        String forceURL = System.getenv("FORCE_URL");
        if (forceURL == null || forceURL.equals("")) {
            //force://login.salesforce.com:443?user=admin@db.com&password=test1234&oauth_key=3MVG99OxTyEMCQ3hoszdSdiXWom2XQ0K_NfXZn1rjwjSGL1LdofLvRjRcIFY.HmHznE_Z8MJuXy5zkkcC.V_6&oauth_secret=5629166396566573549
            System.err.println("Please set FORCE_URL environment variable");
        } else {
            ApiConfig c = new ApiConfig().setForceURL(forceURL);
            ForceApi api = new ForceApi(c);
            System.err.println("Integration Identity: " + api.getIdentity().getId());
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().print("EventMapper");
    }


}