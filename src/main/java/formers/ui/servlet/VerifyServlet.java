package formers.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import formers.boundary.ui.submitter.AccountService;
import formers.boundary.ui.submitter.AccountServiceImpl;
import formers.core.authentication.Authorization;

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
        String target = request.getParameter("target");

        AccountService acservice = new AccountServiceImpl();
        boolean success = acservice.verifyLogIn(user, password);
        Authorization authority = acservice.getAuthority(user);

        if (target == null) {
            // Will only be null when User accesses a page that is ignored by the filter.
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
        }

        if (success) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("authority", authority);
            System.out.println(request.getContextPath() + target);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + target));
        } else {
            request.setAttribute("error", "Incorrect Username or Password!");
            request.getRequestDispatcher("login").forward(request, response);
        }
    }

}
