package formers.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import formers.boundary.authentication.submitter.AccountService;
import formers.boundary.authentication.submitter.AccountServiceImpl;

/**
 * Servlet implementation class AccountCreationServlet
 */
public class AccountCreationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCreationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        AccountService acservice = new AccountServiceImpl();
        boolean success = acservice.createAccount(request.getParameter("newuser"), request.getParameter("newpassword"));

        if (!success) {
            request.setAttribute("error", "This username has already been taken. Please choose something else.");
            request.getRequestDispatcher("newuser").forward(request, response);
        } else {
            request.setAttribute("message", "Your account has been created. Please try logging in.");
            request.getRequestDispatcher("login").forward(request, response);
        }
    }

}
