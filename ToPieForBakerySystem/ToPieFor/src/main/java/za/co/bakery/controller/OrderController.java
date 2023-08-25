package za.co.bakery.controller;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.dao.impl.InvoiceDaoImpl;
import za.co.bakery.dao.impl.ItemDaoImpl;
import za.co.bakery.dao.impl.OrderDaoImpl;
import za.co.bakery.db.manager.DBManager;
import za.co.bakery.service.impl.OrderServiceImpl;

@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProcessRequest pr = RequestActionFactoryOrder.createProcess(request);
        if (pr != null) {
            pr.processTheRequest(request, response);
            pr.processTheResponse(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

abstract class RequestActionFactoryOrder {

    public static ProcessRequest createProcess(HttpServletRequest request) {
        DBManager dbManager = null;
        ServletContext sc = request.getServletContext();

        if (sc != null) {
            dbManager = (DBManager) sc.getAttribute("dbman");
        }
        if (dbManager == null) {
            return null;
        }

        return new OrderServiceImpl(OrderDaoImpl.getInstance(dbManager.getConnection()),ItemDaoImpl.getInstance(dbManager.getConnection()),InvoiceDaoImpl.getInstance(dbManager.getConnection()));
    }

}
