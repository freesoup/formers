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

        boolean authenticated = (session != null) && (session.getAttribute("user") != null);

        String currentURI = request.getRequestURI();
        if (currentURI.equals("/formers/")
                || currentURI.equals("/formers/viewform")
                || currentURI.startsWith("/formers/css")
                || currentURI.startsWith("/formers/js")
                || currentURI.startsWith("/formers/verify")
                || currentURI.startsWith("/formers/respondform")) {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        } else if (authenticated) {
            chain.doFilter(request, response);
        } else {
            System.out.println(currentURI + " has been filtered away due to lack of session");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
