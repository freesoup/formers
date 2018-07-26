package formers.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import formers.boundary.ui.presenter.FormersPresenter;
import formers.boundary.ui.presenter.FormersPresenterImpl;
import formers.boundary.ui.submitter.FormersSubmitter;
import formers.boundary.ui.submitter.FormersSubmitterImpl;
import formers.core.form.utils.FormFormat;

/**
 * Servlet implementation class FormSubmitServlet
 */
public class FormSubmitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormSubmitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormersSubmitter submitterInstance = new FormersSubmitterImpl();

        FormFormat submittedForm = submitterInstance.submitNewForm(request.getParameterMap());

        if (submittedForm != null) {
            FormersPresenter presenterInstance = new FormersPresenterImpl();

            String html = presenterInstance.viewForm(submittedForm);

            response.getWriter().append("<h1>Preview of your form</h1>");
            response.getWriter().append("<form>");
            response.getWriter().append(html);
            response.getWriter().append("</form>");

        } else {
            response.getWriter().append("Error during FormFormat Creation!<br>");
            response.getWriter().append("Served at: ").append(request.getContextPath());
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
