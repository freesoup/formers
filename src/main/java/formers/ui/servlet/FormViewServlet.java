package formers.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import formers.boundary.ui.presenter.FormersPresenter;
import formers.boundary.ui.presenter.FormersPresenterImpl;

/**
 * Servlet implementation class FormViewServlet
 */
public class FormViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormersPresenter presenter = new FormersPresenterImpl();
        String htmlCodes = presenter.viewForm();

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Viewing Form</title></head>");
        out.println("<body>");

        out.println("<form action='formsubmit' method='post'>");
        out.println(htmlCodes);
        out.println("<input type='submit' value='Submit Form'>  ");
        out.println("<input type='reset' value='Reset Form Fields'>");
        out.println("<form>");

        out.println("</body></html>");
        response.getWriter().append("Served at: ").append(request.getContextPath());
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
