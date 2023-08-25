package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.bakery.dao.CategoryDao;
import za.co.bakery.model.Category;

public class CategoryDaoImpl implements CategoryDao {

    private static CategoryDaoImpl categoryDaoImpl = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private CategoryDaoImpl(Connection con) {
        this.con = con;
    }

    public static CategoryDaoImpl getInstance(Connection dbCon) {
        if (categoryDaoImpl == null) {
            categoryDaoImpl = new CategoryDaoImpl(dbCon);
        }
        return categoryDaoImpl;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();

        if (con == null) {
            return categoryList;
        }

        try {
            ps = con.prepareStatement("SELECT categoryId, name, description,isActive FROM category");
            rs = ps.executeQuery();

            while (rs.next()) {
                categoryList.add(new Category(rs.getInt("categoryId"), rs.getString("name"), rs.getString("description"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return categoryList;

    }
    
    public List<Category> getAllActiveCategories() {
        List<Category> categoryList = new ArrayList<>();

        if (con == null) {
            return categoryList;
        }

        try {
            ps = con.prepareStatement("SELECT categoryId, name, description,isActive FROM category WHERE isActive = 1");
            rs = ps.executeQuery();

            while (rs.next()) {
                categoryList.add(new Category(rs.getInt("categoryId"), rs.getString("name"), rs.getString("description"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return categoryList;

    }

    @Override
    public Category getCategoryById(int categoryId) {
        Category category = null;

        if (con == null) {
            return category;
        }

        try {
            ps = con.prepareStatement("SELECT categoryId, name, description, isActive FROM category WHERE categoryId = ?");
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Category(rs.getInt("categoryId"), rs.getString("name"), rs.getString("description"), rs.getBoolean("isActive"));
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return category;
    }

    @Override
    public boolean addCategory(Category category) {
        boolean add = false;

        if (con == null) {
            return add;
        }

        try {
            ps = con.prepareStatement("INSERT INTO category( name, description) VALUES(?,?)");

            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());

            if (ps.executeUpdate() > 0) {
                add = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return add;
    }

    @Override
    public boolean editCategory(Category category) {
        boolean edit = false;

        if (con == null) {
            return edit;
        }

        try {
            ps = con.prepareStatement("UPDATE category SET name = ?, description = ? WHERE categoryId = ?");
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getCategoryId());
            if (ps.executeUpdate() > 0) {
                edit = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return edit;
    }

    @Override
    public boolean activateCategory(Category category) {
        boolean activate = false;

        if (con == null) {
            return activate;
        }
        try {
            ps = con.prepareStatement("UPDATE category SET isActive = ? WHERE categoryId = ?");
            ps.setBoolean(1, category.getIsActive());
            ps.setInt(2, category.getCategoryId());
            
            if (ps.executeUpdate() > 0) {
                activate = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return activate;
    }

}
