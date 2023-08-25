package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.bakery.dao.RecipeDao;
import za.co.bakery.model.Recipe;

public class RecipeDaoImpl implements RecipeDao {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private static RecipeDaoImpl recipeDaoImpl = null;

    public RecipeDaoImpl(Connection con) {
        this.con = con;
    }

    public static RecipeDaoImpl getInstance(Connection dbCon) {
        if (recipeDaoImpl == null) {
            recipeDaoImpl = new RecipeDaoImpl(dbCon);
        }
        return recipeDaoImpl;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        if (con == null) {
            return recipes;
        }
        try {
            ps = con.prepareStatement("SELECT recipeId, recipeName, description, isActive FROM recipe");
            rs = ps.executeQuery();

            while (rs.next()) {
                recipes.add(new Recipe(rs.getInt("recipeId"), rs.getString("recipeName"), rs.getString("description"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return recipes;
    }

    @Override
    public Recipe getRecipeByName(String recipeName) {
        try {
            ps = con.prepareStatement("SELECT recipeId, recipeName, description, isActive FROM recipe WHERE recipeName = ? ");
            ps.setString(1, recipeName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Recipe(rs.getInt("recipeId"), rs.getString("recipeName"), rs.getString("description"), rs.getBoolean("isActive"));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<String> getRecipeIngredients(int recipeId) {
        List<String> ingredientList = new ArrayList<>();

        if (con == null) {
            return ingredientList;
        }

        try {
            ps = con.prepareStatement("SELECT ingredient.name, recipeingredients.quantity "
                    + "FROM ingredient "
                    + "JOIN recipeingredients ON ingredient.ingredientId = recipeingredients.ingredientId "
                    + "JOIN recipe ON recipe.recipeId = recipeingredients.recipeId "
                    + "WHERE recipeingredients.recipeId = ?");
            ps.setInt(1, recipeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                ingredientList.add("name: " + name + " - Quanitity: " + quantity);
                
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return ingredientList;
    }

    @Override
    public boolean addRecipeIngredient(Recipe recipe) {
        boolean add = false;

        if (con == null) {
            return add;
        }

        try {
            ps = con.prepareStatement("INSERT INTO recipeingredients(ingredientId, recipeId, quantity) VALUES(?,?,?)");
            ps.setInt(1, recipe.getIngredientId());
            ps.setInt(2, recipe.getRecipeId());
            ps.setInt(3, recipe.getQuantity());

            if (ps.executeUpdate() > 0) {
                add = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return add;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {

        boolean add = false;

        if (con == null) {
            return add;
        }

        try {
            ps = con.prepareStatement("INSERT INTO recipe( recipeName, description) VALUES(?,?)");
            ps.setString(1, recipe.getRecipeName());
            ps.setString(2, recipe.getDescription());

            if (ps.executeUpdate() > 0) {
                add = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return add;
    }

    @Override
    public boolean activateRecipe(Recipe recipe) {
        boolean activate = false;

        if (con == null) {
            return activate;
        }
        try {
            ps = con.prepareStatement("UPDATE recipe SET isActive = ? WHERE recipeId = ?");
            ps.setBoolean(1, recipe.getIsActive());
            ps.setInt(2, recipe.getRecipeId());

            if (ps.executeUpdate() > 0) {
                activate = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return activate;
    }

    @Override
    public boolean editRecipe(Recipe recipe) {

        boolean edit = false;

        if (con != null) {

            try {
                ps = con.prepareStatement("UPDATE recipe SET recipeName = ?, description = ? WHERE recipeId = ?");
                ps.setString(1, recipe.getRecipeName());
                ps.setString(2, recipe.getDescription());
                ps.setInt(3, recipe.getRecipeId());

                if (ps.executeUpdate() > 0) {

                    edit = true;
                }

            } catch (SQLException e) {
                System.out.println("Error : " + e.getMessage());

            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
            }
            return edit;
        }
        return edit;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        Recipe recipe = null;

        if (con == null) {
            return recipe;
        }
        try {
            ps = con.prepareStatement("SELECT recipeId, recipeName, description, isActive FROM recipe WHERE recipeId =?");
            ps.setInt(1, recipeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Recipe(rs.getInt("recipeId"), rs.getString("recipeName"), rs.getString("description"), rs.getBoolean("isActive"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return recipe;
    }

}
