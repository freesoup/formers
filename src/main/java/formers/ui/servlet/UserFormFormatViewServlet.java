package formers.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import formers.boundary.exception.FormersException;
import formers.boundary.ui.presenter.FormersPresenter;
import formers.core.exception.DatabaseException;
import formers.factory.ObjectsFactory;

/**
 * Servlet implementation class FormViewServlet Displays the form for users to submit.
 */
public class UserFormFormatViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFormFormatViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormersPresenter presenter = ObjectsFactory.getFormsPresenter();
        String requestID = request.getParameter("requestID");
        String htmlCodes;
        Date expiry;
        try {
            htmlCodes = presenter.viewForm(requestID);
            expiry = presenter.getExpiry(requestID);
        } catch (DatabaseException de) {
            throw new FormersException(de.getMessage());
        }
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

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println(css);
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Formers Form</title></head>");
        out.println("<body>");
        out.println("<div id='page-container'>");
        out.println("<form action='respondform' method='post'>");

        out.println(htmlCodes);

        if (expiry.before(new Date())) {
            out.println("<p id='expired-msg'>This form is no longer allowing new responses</p>");
        } else {
            out.println("<button type='submit' value='Submit Form'>Submit Form</button>");
            out.println("<button type='reset' value='Reset Form Fields'>Reset Form Fields</button>");
        }

        out.println("<form>");
        out.println("</div>");
        out.println("</body></html>");

        HttpSession session = request.getSession();
        session.setAttribute("formID", requestID);
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
