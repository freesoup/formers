package formers.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import formers.boundary.ui.submitter.AccountService;
import formers.boundary.ui.submitter.AccountServiceImpl;

/**
 * Servlet implementation class VerifyServlet
 */
public class VerifyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyServlet() {
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
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        AccountService acservice = new AccountServiceImpl();
        boolean success = acservice.verifyLogIn(user, password);

        if (success) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(response.encodeRedirectURL("cool"));
        } else {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
        }
    }

}
