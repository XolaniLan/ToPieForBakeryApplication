package za.co.bakery.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.impl.IngredientDaoImpl;
import za.co.bakery.dao.impl.UnitDaoImpl;
import za.co.bakery.db.manager.DBManager;
import za.co.bakery.service.impl.IngredientServiceImpl;

@WebServlet(name = "IngredientController", urlPatterns = {"/IngredientController"})
public class IngredientController extends HttpServlet {

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProcessRequest pr = RequestActionFactoryIngredient.createProcess(request);
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
abstract class RequestActionFactoryIngredient {

    public static ProcessRequest createProcess(HttpServletRequest request) {

        DBManager dbManager = null;
        ServletContext sc = request.getServletContext();
        if (sc != null) {
            dbManager = (DBManager) sc.getAttribute("dbman");
        }
        if (dbManager == null) {
            return null;
        }
        return new IngredientServiceImpl(IngredientDaoImpl.getInstance(dbManager.getConnection()), UnitDaoImpl.getInstance(dbManager.getConnection()));

    }
}
