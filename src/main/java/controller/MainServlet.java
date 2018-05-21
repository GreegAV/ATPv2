package controller;

import dao.*;
import entities.*;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static dao.DAODriver.prepareListDrivers;
import static dao.DAOBus.prepareListBuses;
import static service.ErrorLog.logError;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            // saving locale for the future
            String locale = request.getParameter("theLocale");
            request.getServletContext().setAttribute("theLocale", locale);

            String theCommand = request.getParameter("command");
            System.out.println(theCommand);
            if (theCommand == null) {
                theCommand = "HOME";
            }

            // route to the appropriate method
            switch (theCommand) {

                case "DELETEDRIVER":
                    deleteDriver(request, response);
                    break;

                case "DELETEBUS":
                    deleteBus(request, response);
                    break;

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
            logError("Failed to process command.", e);
        }
    }

    private void deleteBus(HttpServletRequest request, HttpServletResponse response) {
        DAOBus daoBus = new DAOBus();

        int busID = Integer.parseInt(request.getParameter("busID"));
        daoBus.deleteBus(busID);

        // renew drivers list
        prepareListBuses(request, response);
        try {
            request.getRequestDispatcher("busList.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            logError("Failed go delete driver from the list.", e);
        }
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) {
        DAODriver daoDriver = new DAODriver();

        int driverID = Integer.parseInt(request.getParameter("driverID"));
        daoDriver.deleteDriver(driverID);

        // renew drivers list
        prepareListDrivers(request, response);
        try {
            request.getRequestDispatcher("driverList.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            logError("Failed go delete driver from the list.", e);
        }
    }

    private void listRoutes(HttpServletRequest request, HttpServletResponse response) {
        try {
            DAORoute daoRoute = new DAORoute();
            List<Route> routes = daoRoute.getRoutes();

            // add routes to the request
            request.setAttribute("ROUTES_LIST", routes);

        } catch (Exception e) {
            logError("Failed go get routes list.", e);
        }
    }

    private void listBuses(HttpServletRequest request, HttpServletResponse response) {
        try {
            DAOBus daoBus = new DAOBus();
            List<Bus> buses = daoBus.getBuses();

            // add buses to the request
            request.setAttribute("BUSES_LIST", buses);

        } catch (Exception e) {
            logError("Failed go get buses list.", e);
        }
    }

    private void addRoute(HttpServletRequest request, HttpServletResponse response) {
        String routeName = request.getParameter("routeName");
        RouteUtil routeUtil = new RouteUtil();
        String page = routeUtil.addRoute(routeName);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logError("Failed to add new route.", e);
        }
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response) {
        String driverName = request.getParameter("driverName");
        DAODriver daoDriver = new DAODriver();
        String page = daoDriver.addDriver(driverName, request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logError("Failed to add new bus.", e);
        }
    }

    private void addBus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String busModel = request.getParameter("busModel");
        DAOBus daoBus = new DAOBus();
        String page = daoBus.addBus(busModel,request,response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logError("Failed to add new bus.", e);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        UserUtil userUtil = new UserUtil();

        String loginName = request.getParameter("nameInput");
        String loginPassword = request.getParameter("passInput");

        String page = userUtil.getUserPage(loginName, loginPassword);
        if (!page.equalsIgnoreCase("userNotFound.jsp")) {
            prepareListDrivers(request, response);
            prepareListBuses(request, response);
        }
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logError("Failed to login.", e);
        }
    }
}