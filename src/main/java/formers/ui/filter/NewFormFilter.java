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
import formers.core.form.utils.FormID;

/**
 * Servlet Filter implementation class NewFormFilter
 */
public class NewFormFilter implements Filter {

    /**
     * Default constructor.
     */
    public NewFormFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) Helps to set the Authority level and user
     *      before a view of the form. Which should be open to everyone.
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        HttpSession session = request.getSession(false);

        boolean isStranger = (session == null)
                || (session.getAttribute("user") == null)
                || (session.getAttribute("authority") == null);

        if (isStranger) {
            session = request.getSession();
            session.setAttribute("user", FormID.generateFormID());
            session.setAttribute("authority", Authorization.STRANGER);
        }
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
