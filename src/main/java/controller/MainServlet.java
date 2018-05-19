package controller;

import dao.DAOBus;
import dao.DAODriver;
import dao.DAORoute;
import entities.Bus;
import entities.Driver;
import entities.Route;
import org.apache.log4j.Logger;
import service.BusUtil;
import service.RouteUtil;
import service.UserUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            // saving locale for the future
            String locale = request.getParameter("theLocale");
            request.getServletContext().setAttribute("theLocale", locale);

            // read the "command" parameter
            String theCommand = request.getParameter("command");
            System.out.println(theCommand);
            // if the command is missing, then default to listing students
            if (theCommand == null) {
                theCommand = "HOME";
            }

            // route to the appropriate method
            switch (theCommand) {

//                case "LISTDRIVERS":
//                    listDrivers(request, response);
//                    break;

                case "LOGIN":
                    login(request, response);
                    break;

                case "ADDBUS":
                    addBus(request, response);
                    break;

                case "ADDDRIVER":
                    addDriver(request, response);
                    break;

                case "ADDROUTE":
                    addRoute(request, response);
                    break;

                default:
//                    home(request, response);
            }

        } catch (Exception e) {
            logger.error("Failed to process command.");
            logger.error(e.getLocalizedMessage());
        }
    }

    private void listDrivers(HttpServletRequest request, HttpServletResponse response) {
        try {
            DAODriver daoDriver = new DAODriver();
            List<Driver> drivers = daoDriver.getDrivers();

            // add drivers to the request
            request.setAttribute("DRIVER_LIST", drivers);

        } catch ( Exception e) {
            logger.error("Failed go get drivers list.");
            logger.error(e.getLocalizedMessage());
        }
    }

    private void listRoutes(HttpServletRequest request, HttpServletResponse response) {
        try {
            DAORoute daoRoute = new DAORoute();
            List<Route> routes = daoRoute.getRoutes();

            // add routes to the request
            request.setAttribute("ROUTES_LIST", routes);

        } catch (Exception e) {
            logger.error("Failed go get routes list.");
            logger.error(e.getLocalizedMessage());
        }
    }

    private void listBuses(HttpServletRequest request, HttpServletResponse response) {
        try {
            DAOBus daoBus = new DAOBus();
            List<Bus> buses = daoBus.getBuses();

            // add buses to the request
            request.setAttribute("BUSES_LIST", buses);

        } catch (Exception e) {
            logger.error("Failed go get buses list.");
            logger.error(e.getLocalizedMessage());
        }
    }

    private void addRoute(HttpServletRequest request, HttpServletResponse response) {
        String routeName = request.getParameter("routeName");
        RouteUtil routeUtil = new RouteUtil();
        String page = routeUtil.addRoute(routeName);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Failed to add new route.");
            logger.error(e.getLocalizedMessage());
        }
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response) {
        String driverName = request.getParameter("driverName");
        DAODriver daoDriver = new DAODriver();
        String page = daoDriver.addDriver(driverName);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Failed to add new bus.");
            logger.error(e.getLocalizedMessage());
        }
    }

    private void addBus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String busModel = request.getParameter("busModel");
        BusUtil busUtil = new BusUtil();
        String page = busUtil.addBus(busModel);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Failed to add new bus.");
            logger.error(e.getLocalizedMessage());
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        UserUtil userUtil = new UserUtil();

        String loginName = request.getParameter("nameInput");
        String loginPassword = request.getParameter("passInput");

        String page = userUtil.getUserPage(loginName, loginPassword);
        if (!page.equalsIgnoreCase("userNotFound.jsp")) {
            listDrivers(request,response);
        }
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Failed to login.");
            logger.error(e.getLocalizedMessage());
        }
    }
}