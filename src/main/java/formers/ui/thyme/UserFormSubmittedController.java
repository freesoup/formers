package formers.ui.thyme;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import formers.boundary.exception.FormersException;

public class UserFormSubmittedController implements IFormersController {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext,
            ITemplateEngine templateEngine) throws FormersException {

        String status = (String)request.getAttribute("status");

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("status", status);

        try {
            templateEngine.process("respondform", ctx, response.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
