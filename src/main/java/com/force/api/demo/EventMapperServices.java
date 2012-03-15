package com.force.api.demo;

import com.force.api.*;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class EventMapperServices extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ForceApi api = null;
        String path = req.getPathInfo();

        //Do I have a user specific session?  If not use the general purpose session
        ApiSession s = (ApiSession)req.getSession(true).getAttribute(EventMapperMain.FORCE_SESSION);
        if ( s != null) {
            api = new ForceApi(EventMapperMain.c,s);
        } else {
            api = new ForceApi(EventMapperMain.c);
        }

        if ( path.contains("/event/")) {

            ObjectMapper mapper = new ObjectMapper();
            String[] pathComponents = path.split("/");
            Event event = api.getSObject("Event__c", pathComponents[2]).as(Event.class);
            resp.getWriter().print(mapper.writeValueAsString(event));

        } else if ( path.equals("/events")) {

            ObjectMapper mapper = new ObjectMapper();
            QueryResult<Event> qr = api.query("select id, name, lat__c, lon__c from Event__c order by SortOrder__c asc", Event.class);
            resp.getWriter().print(mapper.writeValueAsString(qr.getRecords()));

        }

    }

}