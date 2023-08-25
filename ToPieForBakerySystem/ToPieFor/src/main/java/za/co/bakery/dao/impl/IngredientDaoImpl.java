package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.bakery.dao.IngredientDao;
import za.co.bakery.model.Ingredient;

public class IngredientDaoImpl implements IngredientDao {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private static IngredientDaoImpl ingredientDaoImpl = null;

    public IngredientDaoImpl(Connection con) {
        this.con = con;
    }

    public static IngredientDaoImpl getInstance(Connection con) {
        if (ingredientDaoImpl == null) {
            ingredientDaoImpl = new IngredientDaoImpl(con);
        }
        return ingredientDaoImpl;
    }

    @Override
    public List<Ingredient> getAllIngridients() {

        List<Ingredient> ingredientList = new ArrayList<>();

        if (con == null) {
            return ingredientList;
        }

        try {
            ps = con.prepareStatement("SELECT ingredientId, unitId, quantityInStock, name, minimumStockLevel, isActive FROM ingredient");
            rs = ps.executeQuery();

            while (rs.next()) {
                ingredientList.add(new Ingredient(rs.getInt("ingredientId"), rs.getInt("unitId"), rs.getInt("quantityInStock"), rs.getString("name"), rs.getInt("minimumStockLevel"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return ingredientList;

    }

    @Override
    public Ingredient getIngredientByName(String name) {

        Ingredient ingredient = null;

        if (con == null) {
            return ingredient;
        }

        try {
            ps = con.prepareStatement("SELECT ingredientId, unitId, quantityInStock, name, minimumStockLevel, isActive  FROM ingredient WHERE name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Ingredient(rs.getInt("ingredientId"), rs.getInt("unitId"), rs.getInt("quantityInStock"), rs.getString("name"), rs.getInt("minimumStockLevel"), rs.getBoolean("isActive"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

       
        return ingredient;

    }

    @Override
    public boolean activateIngredient(Ingredient ingredient) {
        boolean retVal = false;

        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE ingredient SET isActive = ? WHERE ingredientId = ?");
            ps.setBoolean(1, ingredient.isIsActive());
            ps.setInt(2, ingredient.getIngredientId());

            if (ps.executeUpdate() > 0) {
                retVal = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());

        }
        return retVal;

    }

    @Override
    public boolean addIngredient(Ingredient ingredient) {
        boolean retVal = false;

        if (con == null) {
            return retVal;
        }

        try {
            ps = con.prepareStatement("INSERT INTO ingredient( quantityinStock, unitId, name, minimumstockLevel ) VALUES(?,?,?,?)");
            ps.setInt(1, ingredient.getQuantityInStock());
            ps.setInt(2, ingredient.getUnitId());
            ps.setString(3, ingredient.getName());
            ps.setInt(4, ingredient.getMinimumStockLevel());

            if (ps.executeUpdate() > 0) {
                retVal = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return retVal;

    }

    @Override
    public boolean addStock(Ingredient ingredient) {
        boolean stock = false;

        if (con == null) {
            return stock;
        }
        try {
            ps = con.prepareStatement("UPDATE ingredient SET quantityinStock = ? WHERE ingredientId = ?");
            ps.setInt(1, ingredient.getQuantityInStock());
            ps.setInt(2, ingredient.getIngredientId());

            if (ps.executeUpdate() > 0) {
                stock = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());

        }
        return stock;}
}
