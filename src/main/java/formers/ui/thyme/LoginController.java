package formers.ui.thyme;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

public class LoginController implements IFormersController {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext,
            ITemplateEngine templateEngine) {

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        String target = (String)request.getAttribute("target");
        String error = (String)request.getAttribute("error");
        String message = (String)request.getAttribute("message");

        ctx.setVariable("target", target);
        ctx.setVariable("error", error);
        ctx.setVariable("message", message);

        try {
            templateEngine.process("login", ctx, response.getWriter());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
