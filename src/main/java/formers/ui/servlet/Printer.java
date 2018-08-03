package formers.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class Printer
 */
public class Printer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    final static Logger logger = LoggerFactory.getLogger(Printer.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Printer() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        logger.info("Using this logger {}", logger.getClass());
        logger.info("Logged in as " + user);
        // Set the response message MIME type (in Content-Type response header)
        response.setContentType("text/html");

        String cssTag = "<link rel='stylesheet' type='text/css' href='css/main.css'>";
        PrintWriter out = response.getWriter();
        // Write the response message (in an HTML page) to display "Hello, world!"
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println(cssTag);
        out.println("<title>Logged In</title></head>");
        out.println("<body><h1>Logged In!</h1>");
        out.println("<h1>You have just performed a POST REQUEST</h1>");
        out.println("<p>Your Username is " + user + "</p>");
        out.println("<p>Your Password is " + password + "</p>");
        out.println("<a class='linkbtn' href='mainadmin'>Continue As Admin</a>");
        out.println("<a class='linkbtn' href='mainuser'>Continue As User</a>");
        out.println("</body></html>");
    }
}
