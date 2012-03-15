package com.force.api.demo;

import com.force.api.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.MessageFormat;
import java.util.List;


public class EventMapper extends HttpServlet {

    private static ApiConfig c;
    private static String FORCE_SESSION = "FORCE_SESSION";

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
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

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ForceApi api = null;
        HttpSession session = req.getSession(true);
        String path = req.getPathInfo();

        if (path.equals("/css")) {

            resp.getWriter().print(readFile("./src/main/webapp/index.css"));

        } else if (path.equals("/mapper")) {

            resp.getWriter().print(readFile("./src/main/webapp/mapper.js"));

        } else if ( path.equals("/login")) {

            String url = Auth.startOAuthWebServerFlow(new AuthorizationRequest()..apiConfig(c));
            resp.sendRedirect(url);

        } else if ( path.equals("/oauth")) {

            ApiSession s = Auth.completeOAuthWebServerFlow(new AuthorizationResponse().apiConfig(c).code(req.getParameter("code")));
            session.setAttribute(FORCE_SESSION, s);
            resp.sendRedirect("/");

        } else {

            String displayName;
            ApiSession s = (ApiSession)session.getAttribute(FORCE_SESSION);

            if ( s != null) {
                api = new ForceApi(c,s);
                displayName = api.getIdentity().getDisplayName();
            } else {
                api = new ForceApi(c);
                displayName = "<a href='/login'>Login</a>";
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

            } else {

                String[] args = new String[1];
                args[0] = displayName;
                MessageFormat html;
                html = new MessageFormat(readFile("./src/main/webapp/index.html"));
                resp.getWriter().print(html.format(args));

            }

        }

    }



    private String readFile(String fileName) {

        File file = new File(fileName);

        char[] buffer = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            buffer = new char[(int)file.length()];
            int i = 0;
            int c = bufferedReader.read();
            while (c != -1) {
                buffer[i++] = (char)c;
                c = bufferedReader.read();
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new String(buffer);
    }



    public static void main(String[] args) throws Exception{

        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new EventMapper()),"/*");
        server.start();
        server.join();

    }


}