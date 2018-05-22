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
import static dao.DAORoute.prepareListRoutes;
import static service.ErrorLog.logError;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

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

            String theCommand = request.getParameter("command");
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

                case "DELETEROUTE":
                    deleteRoute(request, response);
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
                    request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            logError("Failed to process command.", e);
        }
    }


    private void deleteBus(HttpServletRequest request, HttpServletResponse response) {
        DAOBus daoBus = new DAOBus();

        int busID = Integer.parseInt(request.getParameter("busID"));
        daoBus.deleteBus(busID);

        // renew buses list
        prepareListBuses(request, response);
        try {
            request.getRequestDispatcher("busList.jsp").forward(request, response);
        } catch (Exception e) {
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
        } catch (Exception e) {
            logError("Failed go delete driver from the list.", e);
        }
    }

    private void deleteRoute(HttpServletRequest request, HttpServletResponse response) {
        DAORoute daoRoute = new DAORoute();

        int routeID = Integer.parseInt(request.getParameter("routeID"));
        daoRoute.deleteRoute(routeID);

        // renew routes list
        prepareListRoutes(request, response);
        try {
            request.getRequestDispatcher("routeList.jsp").forward(request, response);
        } catch (Exception e) {
            logError("Failed go delete route from the list.", e);
        }
    }


    private void addBus(HttpServletRequest request, HttpServletResponse response) {
        String busModel = request.getParameter("busModel");
        DAOBus daoBus = new DAOBus();
        String page = daoBus.addBus(busModel);

        // renew buses list
        prepareListBuses(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to add new bus.", e);
        }
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response) {
        String driverName = request.getParameter("driverName");
        DAODriver daoDriver = new DAODriver();
        String page = daoDriver.addDriver(driverName);
        //renew drivers list
        prepareListDrivers(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to add new bus.", e);
        }
    }

    private void addRoute(HttpServletRequest request, HttpServletResponse response) {
        String routeName = request.getParameter("routeName");
        DAORoute daoRoute = new DAORoute();
        String page = daoRoute.addRoute(routeName);
        prepareListRoutes(request,response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to add new route.", e);
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
            prepareListRoutes(request, response);
        }
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to login.", e);
        }
    }
}