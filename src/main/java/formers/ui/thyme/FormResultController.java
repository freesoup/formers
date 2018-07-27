package formers.ui.thyme;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.users.AdminCore;

public class FormResultController implements IFormersController {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext,
            ITemplateEngine templateEngine) {

        String requestID = request.getParameter("requestID");
        AdminCore admin = new AdminCore();
        List<FormResponse> listResponse = admin.viewResultsOfAForm(requestID);
        FormFormat format = admin.viewForm(requestID);

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
