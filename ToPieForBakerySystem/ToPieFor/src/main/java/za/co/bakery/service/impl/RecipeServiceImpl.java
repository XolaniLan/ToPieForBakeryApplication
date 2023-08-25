package za.co.bakery.service.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.IngredientDao;
import za.co.bakery.dao.RecipeDao;
import za.co.bakery.dao.UnitDao;
import za.co.bakery.model.Ingredient;
import za.co.bakery.model.Recipe;
import za.co.bakery.service.RecipeService;

public class RecipeServiceImpl implements RecipeService, ProcessRequest {

    private RecipeDao recipeDao;
    private IngredientDao ingredientDao;
    private UnitDao unitDao;
    private String recipeView;

    public RecipeServiceImpl(RecipeDao recipeDao, IngredientDao ingredientDao, UnitDao unitDao) {
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
        this.unitDao = unitDao;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeDao != null ? recipeDao.getAllRecipes() : null;
    }

    @Override
    public Recipe getRecipeByName(String recipeName) {

        if (recipeName == null && recipeName.isEmpty()) {
            return null;
        }
        return recipeDao != null ? recipeDao.getRecipeByName(recipeName) : null;

    }

    @Override
    public List<String> getRecipeIngredients(int recipeId) {
        return recipeDao != null ? recipeDao.getRecipeIngredients(recipeId) : null;
    }

    @Override
    public boolean addRecipeIngredient(Recipe recipe) {
        return recipe != null ? recipeDao.addRecipeIngredient(recipe) : false;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        return recipe != null ? recipeDao.addRecipe(recipe) : false;
    }

    @Override
    public boolean activateRecipe(Recipe recipe) {
        return recipe != null ? recipeDao.activateRecipe(recipe) : false;
    }

    @Override
    public boolean editRecipe(Recipe recipe) {
        return recipe != null ? recipeDao.editRecipe(recipe) : false;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        if (recipeId < 0) {
            return null;
        }
        return recipeDao == null ? null : recipeDao.getRecipeById(recipeId);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        Recipe recipe = null;
        List<Recipe> recipes = null;
        List<Ingredient> ingredientList = null;
        List<String> recipeIngredients = null;
        int recipeId = 0;
        int ingredientId = 0;
        int quantity = 0;
        String recipeName = "";
        String description = "";
        Boolean isActive = Boolean.parseBoolean("isActive");

        String action = request.getParameter("act");
        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {

                case "addingredients":
                    recipeId = Integer.parseInt(request.getParameter("recipeId"));

                    recipe = recipeDao.getRecipeById(recipeId);

                    if (recipe != null) {
                        request.setAttribute("recipe", recipe);
                    } else {
                        System.out.println("Empty");
                        request.setAttribute("ans", "Recipe not found");
                    }

                    ingredientList = ingredientDao.getAllIngridients();
                    request.setAttribute("allIngredients", ingredientList);

                    ingredientId = Integer.parseInt(request.getParameter("ingredientId"));
                    quantity = Integer.parseInt(request.getParameter("quantity"));

                    if (recipeId > 0) {
                        if (addRecipeIngredient(new Recipe(recipeId, ingredientId, quantity)));
                        request.setAttribute("added", "ingredient added");
                    } else {
                        request.setAttribute("ans", "ingredient not added.");
                    }
                    
                    recipeIngredients = recipeDao.getRecipeIngredients(recipeId);

                    if (recipeIngredients != null && !recipeIngredients.isEmpty()) {
                        request.setAttribute("allRecipeIngredients", recipeIngredients);
                    } else {
                        request.setAttribute("ans", "No user surveys found");
                    }

                    recipeView = "addRecipeIngredients.jsp";
                    break;

                case "addrecipe":

                     recipeName = request.getParameter("recipeName");
                     description = request.getParameter("description");

                    if (recipeName != null && !recipeName.trim().isEmpty()) {
                        recipeName = recipeName.trim();
                    }

                    if (!recipeName.isEmpty()) {
                        if (addRecipe(new Recipe(recipeName, description))) {
                            request.setAttribute("added", "Recipe added.");
                        } else {
                            request.setAttribute("ans", "Recipe not added.");
                        }
                    }

                    recipes = recipeDao.getAllRecipes();
                    if (recipes != null && !recipes.isEmpty()) {

                        request.setAttribute("allRecipes", recipes);

                    } else {
                        request.setAttribute("ans", "No recipes, Yet!");
                    }
                    recipeView = "viewRecipes.jsp";
                    break;

                case "allrecipes":

                    recipes = recipeDao.getAllRecipes();
                    if (recipes != null && !recipes.isEmpty()) {

                        request.setAttribute("allRecipes", recipes);

                    } else {
                        request.setAttribute("ans", "No recipes, Yet!");
                    }
                    recipeView = "viewRecipes.jsp";
                    break;
                case "recipebyname":
                    recipeName = request.getParameter("recipeName");
                    recipe = recipeDao.getRecipeByName(recipeName);

                    if (recipe != null) {
                        request.setAttribute("recipe", recipe);
                    } else {
                        request.setAttribute("ans", "recipe not found");
                    }
                    break;
                case "edit":
                    System.out.println("edit");
                    recipeName = request.getParameter("recipeName");

                    description = request.getParameter("description");

                    try {
                        recipeId = Integer.parseInt(request.getParameter("recipeId"));
                    } catch (NumberFormatException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (!recipeName.isEmpty() && recipeId > 0) {
                        if (editRecipe(new Recipe(recipeId, recipeName, description))) {
                            request.setAttribute("ans", "recipe updated");
                        } else {
                            request.setAttribute("ans", "recipe not updated");
                        }

                    }

                    recipes = recipeDao.getAllRecipes();
                    if (recipes != null && !recipes.isEmpty()) {

                        request.setAttribute("allRecipes", recipes);

                    } else {
                        request.setAttribute("ans", "No recipes, Yet!");
                    }
                    recipeView = "viewRecipes.jsp";
                    break;
                case "activate":
                    isActive = Boolean.parseBoolean(request.getParameter("isActive"));

                    try {
                        recipeId = Integer.parseInt(request.getParameter("recipeId"));
                    } catch (NumberFormatException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (recipeId > 0) {
                        System.out.println("in");
                        if (activateRecipe(new Recipe(recipeId, isActive))) {
                            request.setAttribute("ans", "recipe updated");
                        } else {
                            request.setAttribute("ans", "recipe not updated");
                        }
                    }
                    recipes = recipeDao.getAllRecipes();
                    if (recipes != null && !recipes.isEmpty()) {

                        request.setAttribute("allRecipes", recipes);

                    } else {
                        request.setAttribute("ans", "No recipes, Yet!");
                    }
                    recipeView = "viewRecipes.jsp";
                    break;

                case "recipebyid":
                    System.out.println("id");
                    recipeId = Integer.parseInt(request.getParameter("recipeId"));
                    recipe = recipeDao.getRecipeById(recipeId);

                    if (recipe != null) {
                        request.setAttribute("recipe", recipe);
                    } else {
                        System.out.println("Empty");
                        request.setAttribute("ans", "Recipe not found");
                    }
                    recipeView = "editRecipe.jsp";
                    break;
                case "recipeingredient":
                    System.out.println("id");
                    recipeId = Integer.parseInt(request.getParameter("recipeId"));
                    recipe = recipeDao.getRecipeById(recipeId);

                    if (recipe != null) {
                        request.setAttribute("recipe", recipe);
                    } else {
                        System.out.println("Empty");
                        request.setAttribute("ans", "Recipe not found");
                    }

                    recipeIngredients = recipeDao.getRecipeIngredients(recipeId);

                    if (recipeIngredients != null && !recipeIngredients.isEmpty()) {
                        request.setAttribute("allRecipeIngredients", recipeIngredients);
                    } else {
                        request.setAttribute("ans", "No user surveys found");
                    }

                    ingredientList = ingredientDao.getAllIngridients();
                    request.setAttribute("allIngredients", ingredientList);

                    recipeView = "addRecipeIngredients.jsp";
                    break;

            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(recipeView);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }

}
