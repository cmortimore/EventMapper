package com.force.api.demo;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class EventMapperServer {

    public static void main(String[] args) throws Exception{

        System.err.println("Starting EventMapper");
        System.err.println("PORT: " + System.getenv("PORT"));
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new EventMapper()),"/");


        server.start();
        server.join();
    }


}
