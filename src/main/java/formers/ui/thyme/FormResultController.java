package formers.ui.thyme;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import formers.boundary.exception.FormersException;
import formers.core.authentication.Authorization;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.users.Player;

public class FormResultController implements IFormersController {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext,
            ITemplateEngine templateEngine) throws FormersException {

        String requestID = request.getParameter("requestID");
        Authorization authority = (Authorization)request.getSession().getAttribute("authority");
        Player player = new Player();
        List<FormResponse> listResponse;
        try {
            listResponse = player.viewResultsOfAForm(requestID, authority);
        } catch (InsufficientAuthorityException e) {
            throw new FormersException(e.getMessage());
        }
        FormFormat format = player.viewForm(requestID);

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("resultlist", listResponse);
        ctx.setVariable("format", format);

        try {
            templateEngine.process("formresult", ctx, response.getWriter());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
