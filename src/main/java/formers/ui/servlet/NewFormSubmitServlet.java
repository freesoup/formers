package formers.ui.servlet;

import formers.boundary.exception.FormersException;
import formers.boundary.ui.presenter.FormersPresenter;
import formers.boundary.ui.submitter.FormersSubmitter;
import formers.core.authentication.Authorization;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormFormat;
import formers.factory.ObjectsFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class FormSubmitServlet Admin's view after submitting a form format.
 */
public class NewFormSubmitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFormSubmitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormersSubmitter submitterInstance = ObjectsFactory.getFormsSubmitter();

        HttpSession session = request.getSession();
        String user = session.getAttribute("user").toString();
        Authorization authority = (Authorization)session.getAttribute("authority");

        FormFormat submittedForm;
        try {
            submittedForm = submitterInstance.submitNewForm(request.getParameterMap(), user, authority);
        } catch (InsufficientAuthorityException e) {
            throw new FormersException(e.getMessage());
        }
        String formID = submittedForm.getID();

        String css = "<link rel=\"stylesheet\"\r\n"
                +
                "    href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\"\r\n"
                +
                "    integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\"\r\n"
                +
                "    crossorigin=\"anonymous\">\r\n"
                +
                "<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" \r\n"
                +
                "          href=\"css/application.css\"/>\r\n"
                +
                "<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\r\n"
                +
                "    integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\r\n"
                +
                "    crossorigin=\"anonymous\"></script>\r\n"
                +
                "<script\r\n"
                +
                "    src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\"\r\n"
                +
                "    integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\"\r\n"
                +
                "    crossorigin=\"anonymous\"></script>\r\n"
                +
                "<script\r\n"
                +
                "    src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\"\r\n"
                +
                "    integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\"\r\n"
                +
                "    crossorigin=\"anonymous\"></script>\r\n"
                +
                "";

        if (submittedForm != null) {
            FormersPresenter presenterInstance = ObjectsFactory.getFormsPresenter();

            String html = presenterInstance.viewForm(submittedForm);

            response.getWriter().append("<!DOCTYPE html>");
            response.getWriter().append("<html><head>");
            response.getWriter().append(css);
            response.getWriter().append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            response.getWriter().append("<title>Form Preview</title></head>");
            response.getWriter().append("<body>");
            response.getWriter().append("<div id='page-container'>");
            response.getWriter().append("<h1>Preview of your form</h1>");
            response.getWriter().append("<form>");
            response.getWriter().append(html);
            response.getWriter().append("</form>");
            response.getWriter().append("You may share the form link over at<br>");
            response.getWriter()
                    .append("<input class='requestlink' value='" +  request.getServerName() + "/viewform?requestID="
                            + formID
                            + "'>");

            response.getWriter().append("<br>");
            response.getWriter().append("<a href='mainadmin'><button>Return to main page</button></a>");
            response.getWriter().append("</div>");
            response.getWriter().append("</body></html>");

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
