package controller;

import org.apache.log4j.Logger;
import service.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String locale=request.getParameter("theLocale");
//        request.getServletContext().setAttribute("theLocale", locale);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserUtil userUtil = new UserUtil();
//        String locale=request.getParameter("theLocale");
//        request.getServletContext().setAttribute("theLocale", locale);
        String loginName = request.getParameter("nameInput");
        String loginPassword = request.getParameter("passInput");

        String page = userUtil.getUserPage(loginName, loginPassword);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Failed to login.");
            logger.error(e.getLocalizedMessage());
        }
    }
}