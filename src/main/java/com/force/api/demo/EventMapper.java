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



        ForceApi api = new ForceApi(new ApiConfig()
    .setUsername("admin@cmort.org")
    .setPassword("test1234")
    .setClientId("3MVG9PhR6g6B7ps45QoRvhVGGMgobZgOdHAgdldNFg3gANvnTkhpjPSGdmY_O1s0RYW2YBdzBUdb_zUUkEA0q")
    .setClientSecret("2196529513255626584"));


        System.err.println("Id: " + api.getIdentity().getId());








    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().print("EventMapper");
    }

    public static void main(String[] args) throws Exception{
        System.err.println("Starting EventMapper");
        System.err.println("PORT: " + System.getenv("PORT"));
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new EventMapper()),"/*");
        server.start();
        server.join();
    }
}