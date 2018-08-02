package formers.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.ITemplateEngine;

import formers.core.authentication.Authorization;
import formers.ui.thyme.FormersApp;
import formers.ui.thyme.IFormersController;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        boolean authenticated = (session != null)
                && (session.getAttribute("user") != null)
                && (session.getAttribute("authority") == Authorization.ADMIN);

        if (authenticated) {
            response.sendRedirect(response.encodeRedirectURL("mainadmin"));
        } else {
            process(request, response);
        }
    }

    private boolean process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            FormersApp application = new FormersApp(this.getServletContext());

            // This prevents triggering engine executions for resource URLs
            if (request.getRequestURI().startsWith("/css")
                    ||
                    request.getRequestURI().startsWith("/images")
                    ||
                    request.getRequestURI().startsWith("/favicon")) {
                return false;
            }

            /*
             * Query controller/URL mapping and obtain the controller that will process the request. If no controller is
             * available, return false and let other filters/servlets process the request.
             */
            IFormersController controller = application.resolveControllerForRequest(request);
            if (controller == null) {
                return false;
            }

            /*
             * Obtain the TemplateEngine instance.
             */
            ITemplateEngine templateEngine = application.getTemplateEngine();

            /*
             * Write the response headers
             */
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            /*
             * Execute the controller and process view template, writing the results to the response writer.
             */
            controller.process(
                    request, response, this.getServletContext(), templateEngine);

            return true;

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
