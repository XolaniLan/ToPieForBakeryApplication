package za.co.bakery.service.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.IngredientDao;
import za.co.bakery.dao.UnitDao;
import za.co.bakery.model.Ingredient;
import za.co.bakery.model.Unit;
import za.co.bakery.service.IngredientService;

public class IngredientServiceImpl implements IngredientService, ProcessRequest {

    private IngredientDao ingredientDao;
    private UnitDao unitDao;
    private String ingredientView;

    public IngredientServiceImpl(IngredientDao ingredientDao, UnitDao unitDao) {
        this.ingredientDao = ingredientDao;
        this.unitDao = unitDao;
    }

    @Override
    public List<Ingredient> getAllIngridients() {

        return ingredientDao != null ? ingredientDao.getAllIngridients() : null;
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return name == null ? null : ingredientDao.getIngredientByName(name);
    }

    @Override
    public boolean activateIngredient(Ingredient ingridient) {
        return ingridient != null ? ingredientDao.activateIngredient(ingridient) : false;
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) {
        return ingredient != null ? ingredientDao.addIngredient(ingredient) : false;
    }

    @Override
    public boolean addStock(Ingredient ingredient) {
        return ingredient != null ? ingredientDao.addStock(ingredient) : false;
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        int unitId = 0;
        int quantityinStock = 0;
        int minimumstockLevel = 0;
        int newStock = 0;
        int newQuantity = 0;
        String name = "";

        List<Unit> unitList = null;
        List<Ingredient> ingredients = null;
        Ingredient ingredient = null;
        int ingredientId = 0;

        String action = request.getParameter("act");
        System.out.println(action);

        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {
                case "addto":
                    unitList = unitDao.getAllUnits();
                    request.setAttribute("allUnits", unitList);

                    ingredientView = "addIngredient.jsp";
                    break;
                    
                case "addingredient":
                    try{
                    unitId = Integer.parseInt(request.getParameter("unitId"));
                    quantityinStock = Integer.parseInt(request.getParameter("quantityinStock"));
                    name = request.getParameter("name");
                    minimumstockLevel = Integer.parseInt(request.getParameter("minimumstockLevel"));}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if ((quantityinStock != 0) && (name != null && !name.trim().isEmpty())) {
                        name = name.trim();
                    }

                    if (unitId > 0) {
                        if (addIngredient(new Ingredient(unitId, minimumstockLevel, quantityinStock, name))) {
                            request.setAttribute("addIngredient", "Ingredient was added successfully.");
                        } else {
                            request.setAttribute("ans", "Ingredient wasn't added.");
                        }
                    }
                    ingredients = ingredientDao.getAllIngridients();
                    if (ingredients != null && !ingredients.isEmpty()) {

                        request.setAttribute("getallingredients", ingredients);

                    } else {
                        request.setAttribute("getallingredients", "No recipes, Yet!");
                    }

                    ingredientView = "ingredientView.jsp";
                    break;

                case "viewallingredient":
                    ingredients = ingredientDao.getAllIngridients();
                    if (ingredients != null && !ingredients.isEmpty()) {

                        request.setAttribute("getallingredients", ingredients);

                    } else {
                        request.setAttribute("getallingredients", "No recipes, Yet!");
                    }

                    ingredientView = "ingredientView.jsp";
                    break;

                case "getingredientbyname":

                    try{
                    name = request.getParameter("name");}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    ingredient = ingredientDao.getIngredientByName(name);
                    if (ingredient != null) {
                        request.setAttribute("ingredient", ingredient);
                    } else {
                        request.setAttribute("ans", "No ingredient was found!");
                    }
                    ingredientView = "addStock.jsp";
                    break;
                    
                case "addstock":
                    try{
                    quantityinStock = Integer.parseInt(request.getParameter("quantityinStock"));
                    newStock = Integer.parseInt(request.getParameter("newStock"));
                    newQuantity = quantityinStock + newStock;}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    try {
                        ingredientId = Integer.parseInt(request.getParameter("ingredientId"));
                    } catch (Exception ex) {
                        System.out.println("Error:" + ex);
                    }

                    if (ingredientId > 0) {
                        if (addStock(new Ingredient(ingredientId, newQuantity))) {
                            request.setAttribute("added", "stock increased");
                        } else {
                            request.setAttribute("ans", "stock not added");
                        }
                    }

                    ingredients = ingredientDao.getAllIngridients();
                    if (ingredients != null && !ingredients.isEmpty()) {

                        request.setAttribute("getallingredients", ingredients);

                    } else {
                        request.setAttribute("getallingredients", "No recipes, Yet!");
                    }

                    ingredientView = "ingredientView.jsp";
                    break;

            }

        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ingredientView);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }

}
