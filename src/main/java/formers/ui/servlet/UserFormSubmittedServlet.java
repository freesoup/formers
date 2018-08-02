package formers.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import formers.boundary.exception.FormersException;
import formers.boundary.ui.submitter.FormersSubmitter;
import formers.boundary.ui.submitter.FormersSubmitterImpl;
import formers.core.authentication.Authorization;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormResponse;

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
            submittedForm = submitterInstance.submitNewResponse(request.getParameterMap(), formID, user, authority);
        } catch (InsufficientAuthorityException e) {
            // TODO Auto-generated catch block
            throw new FormersException(e.getMessage());
        }

        if (submittedForm == null) {
            response.getWriter().append("<h1>Error Submitting Form</h1>");
        } else {
            response.getWriter().append("<h1>Thank you for your submission</h1>");
        }

        response.getWriter().append("Serving form ").append(formID);

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
