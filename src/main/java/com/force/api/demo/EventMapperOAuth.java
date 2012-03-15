package com.force.api.demo;

import com.force.api.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EventMapperOAuth extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        String path = req.getPathInfo();

        if ( path.equals("/login")) {

            String url = Auth.startOAuthWebServerFlow(new AuthorizationRequest().apiConfig(EventMapperMain.c));
            resp.sendRedirect(url);

        } else if ( path.equals("/callback")) {

            ApiSession s = Auth.completeOAuthWebServerFlow(new AuthorizationResponse().apiConfig(EventMapperMain.c).code(req.getParameter("code")));
            session.setAttribute(EventMapperMain.FORCE_SESSION, s);

            ForceApi api = new ForceApi(EventMapperMain.c,s);
            session.setAttribute("displayName", api.getIdentity().getDisplayName());

            resp.sendRedirect("/");

        }

    }

}