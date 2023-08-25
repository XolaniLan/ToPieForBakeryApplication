package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import za.co.bakery.dao.ItemDao;
import za.co.bakery.model.Item;

public class ItemDaoImpl implements ItemDao {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private static ItemDaoImpl itemDaoImpl = null;

    public ItemDaoImpl(Connection con) {
        this.con = con;
    }

    public static ItemDaoImpl getInstance(Connection dbCon) {
        if (itemDaoImpl == null) {
            itemDaoImpl = new ItemDaoImpl(dbCon);
        }
        return itemDaoImpl;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();

        if (con == null) {
            return itemList;
        }

        try {
            ps = con.prepareStatement("SELECT itemId, categoryId, recipeId, price, itemName, description, nutritionalValue, warnings, image, isActive FROM item");
            rs = ps.executeQuery();

            while (rs.next()) {
                itemList.add(new Item(rs.getInt("itemId"), rs.getInt("categoryId"), rs.getInt("recipeId"), rs.getFloat("price"), rs.getString("itemName"), rs.getString("description"), rs.getString("nutritionalValue"), rs.getString("warnings"), rs.getString("image"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return itemList;
    }

    public List<Item> getAllActiveItems() {
        List<Item> itemList = new ArrayList<>();

        if (con == null) {
            return itemList;
        }

        try {
            ps = con.prepareStatement("SELECT itemId, categoryId, recipeId, price, itemName, description, nutritionalValue, warnings, image, isActive FROM item WHERE isActive = 1");
            rs = ps.executeQuery();

            while (rs.next()) {
                itemList.add(new Item(rs.getInt("itemId"), rs.getInt("categoryId"), rs.getInt("recipeId"), rs.getFloat("price"), rs.getString("itemName"), rs.getString("description"), rs.getString("nutritionalValue"), rs.getString("warnings"), rs.getString("image"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return itemList;
    }

    @Override
    public List<Item> getAllItemsByCategory(int categoryId) {
        List<Item> itemList = new ArrayList<>();

        if (con == null) {
            return itemList;
        }

        try {
            ps = con.prepareStatement("SELECT itemId, categoryId, recipeId, price, itemName, description, nutritionalValue, warnings, image, isActive FROM item WHERE categoryId = ? AND isActive = 1");
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();

            while (rs.next()) {
                itemList.add(new Item(rs.getInt("itemId"), rs.getInt("categoryId"), rs.getInt("recipeId"), rs.getFloat("price"), rs.getString("itemName"), rs.getString("description"), rs.getString("nutritionalValue"), rs.getString("warnings"), rs.getString("image"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return itemList;
    }

    @Override
    public List<Item> getAllItemsByPrice(float price) {
        List<Item> itemList = new ArrayList<>();

        if (con == null) {
            return itemList;
        }

        try {
            ps = con.prepareStatement("SELECT itemId, categoryId, recipeId, price, itemName, description, nutritionalValue, warnings, image, isActive FROM item WHERE price = ?");
            ps.setFloat(1, price);
            rs = ps.executeQuery();

            while (rs.next()) {
                itemList.add(new Item(rs.getInt("itemId"), rs.getInt("categoryId"), rs.getInt("recipeId"), rs.getFloat("price"), rs.getString("itemName"), rs.getString("description"), rs.getString("nutritionalValue"), rs.getString("warnings"), rs.getString("image"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return itemList;
    }

    @Override
    public Item getItemById(int itemId) {
        Item item = null;

        if (con == null) {
            return item;
        }

        try {
            ps = con.prepareStatement("SELECT itemId, categoryId, recipeId, price, itemName, description, nutritionalValue, warnings, image, isActive FROM item WHERE itemId = ?");
            ps.setInt(1, itemId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Item(rs.getInt("itemId"), rs.getInt("categoryId"), rs.getInt("recipeId"), rs.getFloat("price"), rs.getString("itemName"), rs.getString("description"), rs.getString("nutritionalValue"), rs.getString("warnings"), rs.getString("image"), rs.getBoolean("isActive"));
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return item;

    }

    @Override
    public Item getItemNameById(int itemId) {
        Item item = null;

        if (con == null) {
            return item;
        }

        try {
            ps = con.prepareStatement("SELECT itemName FROM item WHERE itemId = ?");
            ps.setInt(1, itemId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Item(rs.getString("itemName"));
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return item;

    }

    @Override
    public Item getItemByName(String itemName) {
        Item item = null;

        if (con == null) {
            return item;
        }

        try {
            ps = con.prepareStatement("SELECT itemId, categoryId, recipeId, price, itemName, description, nutritionalValue, warnings, image, isActive FROM item WHERE itemName = ?");
            ps.setString(1, itemName);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Item(rs.getInt("itemId"), rs.getInt("categoryId"), rs.getInt("recipeId"), rs.getFloat("price"), rs.getString("itemName"), rs.getString("description"), rs.getString("nutritionalValue"), rs.getString("warnings"), rs.getString("image"), rs.getBoolean("isActive"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return item;
    }

    @Override
    public boolean addItem(Item item) {
        boolean add = false;

        if (con == null) {
            return add;
        }

        try {
            ps = con.prepareStatement("INSERT INTO item( categoryId, recipeId, price, itemName, description, nutritionalValue, warnings, image) VALUES(?,?,?,?,?,?,?,?)");
            ps.setInt(1, item.getCategoryId());
            ps.setInt(2, item.getRecipeId());
            ps.setFloat(3, item.getPrice());
            ps.setString(4, item.getItemName());
            ps.setString(5, item.getDescription());
            ps.setString(6, item.getNutritionalValue());
            ps.setString(7, item.getWarnings());
            ps.setString(8, item.getImageUrl());

            if (ps.executeUpdate() > 0) {
                add = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return add;
    }

    @Override
    public boolean editItem(Item item) {
        boolean edit = false;

        if (con == null) {
            return edit;
        }
        try {

            ps = con.prepareStatement("UPDATE item SET categoryId=?, recipeId=?, itemName=?, price=?, description=?, nutritionalValue=?, warnings=?, image=? WHERE itemId = ?");
            ps.setInt(1, item.getCategoryId());
            ps.setInt(2, item.getRecipeId());
            ps.setString(3, item.getItemName());
            ps.setFloat(4, item.getPrice());
            ps.setString(5, item.getDescription());
            ps.setString(6, item.getNutritionalValue());
            ps.setString(7, item.getWarnings());
            ps.setString(8, item.getImageUrl());
            ps.setInt(9, item.getItemId());

            if (ps.executeUpdate() > 0) {
                edit = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
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

    @Override
    public boolean activateItem(Item item) {
        boolean activate = false;

        if (con == null) {
            return activate;
        }
        try {
            ps = con.prepareStatement("UPDATE item SET isActive = ? WHERE itemId = ?");
            ps.setBoolean(1, item.getIsActive());
            ps.setInt(2, item.getItemId());

            if (ps.executeUpdate() > 0) {
                activate = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());

        }
        return activate;

    }

    @Override
    public Set<Integer> getAllItemIds() {
        Set<Integer> itemIdSet = new HashSet();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT itemId FROM item");
                rs = ps.executeQuery();
                while (rs.next()) {
                    itemIdSet.add(rs.getInt("itemId"));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return itemIdSet;
    }
}
