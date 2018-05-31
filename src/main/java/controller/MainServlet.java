package controller;

import dao.*;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static dao.DAOBus.prepareFullListBuses;
import static dao.DAODriver.prepareFullListDrivers;
import static dao.DAODriver.prepareListDrivers;
import static dao.DAOBus.prepareListBuses;
import static dao.DAORoute.prepareFullListRoutes;
import static dao.DAORoute.prepareListRoutes;
import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

                case "LOGOUT":
                    logout(request, response);
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

                case "SETBUS":
                    setBus(request, response);
                    break;

                case "SETDRIVER":
                    setDriver(request, response);
                    break;

                case "FREEBUS":
                    freeBus(request, response);
                    break;

                case "FREEDRIVER":
                    freeDriver(request, response);
                    break;

                case "FREEROUTE":
                    freeRoute(request, response);
                    break;

                case "CONFIRM":
                    confirmRoute(request, response);
                    break;

                case "CHANGEPAGE":
                    changePage(request, response);
                    break;

                default:
                    request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logError("Failed to process command.", e);
        }
    }

    private void changePage(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        String theLocale = request.getParameter("theLocale");
        String page = "admin.jsp?currentPage=" + currentPage + "&theLocale=" + theLocale;
        request.getServletContext().setAttribute("theLocale", theLocale);
        request.getServletContext().setAttribute("currentPage", currentPage);
        renewLists(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed change page.", e);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        String page = UserUtil.logout(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to logout.", e);
        }
    }

    private void confirmRoute(HttpServletRequest request, HttpServletResponse response) {
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        String page = DAODriver.setConfirmed(driverID);
        renewLists(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to confirm by driver.", e);
        }
    }

    private void freeRoute(HttpServletRequest request, HttpServletResponse response) {
        int routeID = Integer.parseInt(request.getParameter("routeID"));

        DAORoute.setRouteID(0, routeID);

        // renew routes list
        renewLists(request, response);
        try {
            request.getRequestDispatcher("routeList.jsp").forward(request, response);
        } catch (Exception e) {
            logError("Failed to free driver.", e);
        }
    }

    private void freeDriver(HttpServletRequest request, HttpServletResponse response) {
        int driverID = Integer.parseInt(request.getParameter("driverID"));

        String page=DAODriver.freeDriver(driverID);

        // renew drivers list
        renewLists(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to free driver.", e);
        }
    }

    private void setDriver(HttpServletRequest request, HttpServletResponse response) {
        int busID = Integer.parseInt(request.getParameter("busID"));
        int driverID = Integer.parseInt(request.getParameter("driverID"));
        String page = DAODriver.setBusID(busID, driverID);
        renewLists(request, response);

        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed go set bus to the route.", e);
        }
        logInfo("Bus " + busID + "is set to the driver " + driverID);
    }

    private void setBus(HttpServletRequest request, HttpServletResponse response) {
        int busID = Integer.parseInt(request.getParameter("busID"));
        int routeID = Integer.parseInt(request.getParameter("routeID"));
        String page;
        if (DAORoute.isFree(routeID)) {
            page = DAORoute.setRouteID(busID, routeID);
        } else {
            page = "admin.jsp";
        }
        renewLists(request, response);

        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed go set bus to the route.", e);
        }
        logInfo("Route " + routeID + "is set to the bus " + busID);
    }

    private void deleteBus(HttpServletRequest request, HttpServletResponse response) {

        int busID = Integer.parseInt(request.getParameter("busID"));
        DAOBus.deleteBus(busID);

        // renew buses list
        renewLists(request, response);
        try {
            request.getRequestDispatcher("busList.jsp").forward(request, response);
        } catch (Exception e) {
            logError("Failed go delete driver from the list.", e);
        }
    }

    private void freeBus(HttpServletRequest request, HttpServletResponse response) {

        int driverID = Integer.parseInt(request.getParameter("driverID"));
        int routeID = Integer.parseInt(request.getParameter("routeID"));

        DAODriver.setBusID(0, driverID);
        DAORoute.setRouteID(0, routeID);

        // renew buses list
        renewLists(request, response);
        try {
            request.getRequestDispatcher("busList.jsp").forward(request, response);
        } catch (Exception e) {
            logError("Failed to free bus.", e);
        }
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) {

        int driverID = Integer.parseInt(request.getParameter("driverID"));
        DAODriver.deleteDriver(driverID);

        // renew drivers list
        renewLists(request, response);
        try {
            request.getRequestDispatcher("driverList.jsp").forward(request, response);
        } catch (Exception e) {
            logError("Failed go delete driver from the list.", e);
        }
    }

    private void deleteRoute(HttpServletRequest request, HttpServletResponse response) {
        int routeID = Integer.parseInt(request.getParameter("routeID"));
        DAORoute.deleteRoute(routeID);

        // renew routes list
        renewLists(request, response);
        try {
            request.getRequestDispatcher("routeList.jsp").forward(request, response);
        } catch (Exception e) {
            logError("Failed go delete route from the list.", e);
        }
    }

    private void addBus(HttpServletRequest request, HttpServletResponse response) {
        String busModel = request.getParameter("busModel");
        String page = DAOBus.addBus(busModel);

        // renew list
        renewLists(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to add new bus.", e);
        }
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response) {
        String driverName = request.getParameter("driverName");
        String driverPassword = request.getParameter("driverPassword");
        String page = DAODriver.addDriver(driverName, driverPassword);
        //renew drivers list
        renewLists(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to add new bus.", e);
        }
    }

    private void addRoute(HttpServletRequest request, HttpServletResponse response) {
        String routeStart = request.getParameter("routeStart");
        String routeFinish = request.getParameter("routeFinish");
        String page = DAORoute.addRoute(routeStart + " - " + routeFinish);
        renewLists(request, response);
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to add new route.", e);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {

        String page = UserUtil.getUserPage(request, response);
        System.out.println(page);
        if (!page.equalsIgnoreCase("userNotFound.jsp")) {
            int currentPage = 0;
            request.getServletContext().setAttribute("currentPage", currentPage);
            renewLists(request, response);
        }
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            logError("Failed to login.", e);
        }
    }

    private void renewLists(HttpServletRequest request, HttpServletResponse response) {
        prepareListDrivers(request, response);
        prepareFullListDrivers(request, response);
        prepareListBuses(request, response);
        prepareFullListBuses(request, response);
        prepareFullListRoutes(request, response);
        prepareListRoutes(request, response);
    }
}