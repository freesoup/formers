package formers.ui.thyme;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import formers.boundary.exception.FormersException;
import formers.core.authentication.Authorization;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormFormat;
import formers.core.users.Player;
import formers.factory.ObjectsFactory;

public class AdminDashboardController implements IFormersController {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext,
            ITemplateEngine templateEngine) throws FormersException {

        HttpSession session = request.getSession(false);
        String user = (String)session.getAttribute("user");
        Authorization authority = (Authorization)session.getAttribute("authority");

        Player player = ObjectsFactory.getPlayer();
        List<FormFormat> listFormat;
        try {
            listFormat = player.viewAllForm(user, authority);
        } catch (InsufficientAuthorityException e) {
            throw new FormersException(e.getMessage());
        }

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("listFormat", listFormat);

        try {
            templateEngine.process("mainadmin", ctx, response.getWriter());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
