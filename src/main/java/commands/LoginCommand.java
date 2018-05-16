package commands;

import controller.ICommand;
import org.apache.log4j.Logger;
import service.UserUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class LoginCommand implements ICommand {
    private static Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserUtil userUtil = new UserUtil();
        String loginName=request.getParameter("nameInput");
        String loginPassword=request.getParameter("passInput");
        Locale locale=request.getLocale();
        response.setLocale(locale);
        String page=userUtil.getUserPage(loginName,loginPassword);
//        request.getRequestDispatcher(page).forward(request,response);
//        response.getWriter().write(loginName+" "+loginPassword);
//        User loggedUser = User.isUserValid(request.getParameter("nameInput"), request.getParameter("passInput"));
//        if (loggedUser != null) {
//            logger.info(loggedUser.getUserName() + " logged in.");
//            request.getServletContext().setAttribute("loggedUser", loggedUser);
//            response.getWriter().print(Display.showPage(loggedUser, request, 1));
//        } else {
//            logger.info("Username/Password error!");
//            return "/error.jsp";
//        }
        return page;
    }
}
