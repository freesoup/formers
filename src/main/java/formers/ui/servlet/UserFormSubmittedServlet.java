package formers.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import formers.boundary.ui.submitter.FormersSubmitter;
import formers.boundary.ui.submitter.FormersSubmitterImpl;
import formers.core.form.utils.FormID;
import formers.core.form.utils.FormResponse;

/**
 * Servlet implementation class FormResponseServlet
 * Displays the Form for User after submitting
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
        HttpSession session = request.getSession();
        String formID = session.getAttribute("formID").toString();
        String user;

        try {
            user = session.getAttribute("user").toString();
        } catch (NullPointerException npe) {
            user = FormID.generateFormID();
        }

        FormersSubmitter submitterInstance = new FormersSubmitterImpl();
        FormResponse submittedForm = submitterInstance.submitNewResponse(request.getParameterMap(), formID, user);

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
