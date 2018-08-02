package formers.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import formers.core.authentication.Authorization;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        HttpSession session = request.getSession(false);

        boolean authenticated = (session != null)
                && (session.getAttribute("user") != null)
                && (session.getAttribute("authority") == Authorization.ADMIN);

        String currentURI = request.getServletPath();
        String currentQuery = request.getQueryString();
        if (currentQuery == null) {
            currentQuery = "";
        }
        if (currentURI == null
                || currentURI.equals("/viewform")
                || currentURI.equals("/respondform")
                || currentURI.startsWith("/css")
                || currentURI.startsWith("/js")
                || currentURI.startsWith("/verify")
                || currentURI.startsWith("/login")
                || currentURI.startsWith("/newuser")
                || currentURI.startsWith("/accountcreated")
                || currentURI.startsWith("/redirect.jsp")) {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        } else if (authenticated) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute("error", "Please login before proceeeding!");
            request.setAttribute("target", currentURI + "?" + currentQuery);
            request.getRequestDispatcher("login").forward(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
