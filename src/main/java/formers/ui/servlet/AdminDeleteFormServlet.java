package formers.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import formers.boundary.exception.FormersException;
import formers.core.authentication.Authorization;
import formers.core.exception.DatabaseException;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.users.Player;

/**
 * Servlet implementation class AdminDeleteFormServlet
 */
public class AdminDeleteFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        Player player = new Player();

        HttpSession session = request.getSession();

        String formIdToBeDeleted = request.getParameter("requestID");
        Authorization authority = (Authorization)session.getAttribute("authority");
        String user = (String)session.getAttribute("user");

        try {
            player.deleteAllTracesOf(formIdToBeDeleted, user, authority);
        } catch (DatabaseException | InsufficientAuthorityException e) {
            throw new FormersException(e.getMessage());
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
