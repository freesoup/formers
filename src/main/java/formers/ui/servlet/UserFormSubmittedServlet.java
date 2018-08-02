package formers.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.ITemplateEngine;

import formers.boundary.exception.FormersException;
import formers.boundary.ui.submitter.FormersSubmitter;
import formers.boundary.ui.submitter.FormersSubmitterImpl;
import formers.core.authentication.Authorization;
import formers.core.exception.DatabaseException;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormResponse;
import formers.ui.thyme.FormersApp;
import formers.ui.thyme.IFormersController;

/**
 * Servlet implementation class FormResponseServlet Displays the Form for User after submitting
 */
public class UserFormSubmittedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFormSubmittedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session;
        String formID;
        String user;
        Authorization authority;

        try {
            session = request.getSession();
            formID = session.getAttribute("formID").toString();
            user = session.getAttribute("user").toString();
            authority = (Authorization)session.getAttribute("authority");
        } catch (NullPointerException npe) {
            throw new FormersException(
                    "An error has occured where either 1) Your session has expired or 2) You do not have "
                            + "the correct session or 3) You are trying to access the page directly instead of "
                            + "through a form submission. Please login or re-submit the form.");
        }

        FormersSubmitter submitterInstance = new FormersSubmitterImpl();
        FormResponse submittedForm;
        try {
            try {
                submittedForm = submitterInstance.submitNewResponse(request.getParameterMap(), formID, user, authority);
            } catch (DatabaseException e) {
                throw new FormersException(e.getMessage());
            }
        } catch (InsufficientAuthorityException e) {
            // TODO Auto-generated catch block
            throw new FormersException(e.getMessage());
        }

        if (submittedForm != null) {
            request.setAttribute("status", "Thank you for your response!");
        } else {
            request.setAttribute("status", "There was an error submitting your form. Please try submitting again.");
        }

        process(request, response);
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
