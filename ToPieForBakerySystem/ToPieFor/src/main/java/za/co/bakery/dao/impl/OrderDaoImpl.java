package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import za.co.bakery.dao.ItemDao;
import za.co.bakery.dao.OrderDao;
import za.co.bakery.model.Invoice;

import za.co.bakery.model.Order;
import za.co.bakery.model.OrderLineItem;

public class OrderDaoImpl implements OrderDao {

    private static OrderDaoImpl orderDaoImpl = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    


    private OrderDaoImpl(Connection con) {
        this.con = con;
    }

    public static OrderDaoImpl getInstance(Connection dbCon) {
        if (orderDaoImpl == null) {
            orderDaoImpl = new OrderDaoImpl(dbCon);
        }

        return orderDaoImpl;
    }

    @Override
    public List<Order> getOrderByUsername(String username) {
        List<Order> orderList = new ArrayList<>();

        if (con != null) {

            try {
                ps = con.prepareStatement("SELECT orderId, username, deliveryAddress, totalAmount, orderDate, status, time FROM orders WHERE username = ?");
                ps.setString(1, username);
                rs = ps.executeQuery();

                while (rs.next()) {
                    orderList.add(new Order(rs.getInt("orderId"), rs.getString("username"), rs.getString("deliveryAddress"), rs.getInt("totalAmount"), LocalDate.parse(rs.getString("orderDate")), rs.getBoolean("status"), LocalTime.parse(rs.getString("time"))));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return orderList;
        }

        return orderList;

    }

    @Override
    public List<Order> getOrderByOrderDate(LocalDate orderDate) {
        List<Order> orderList = new ArrayList<>();

        if (con != null) {

            try {
                ps = con.prepareStatement("SELECT orderId, username, deliveryAddress, totalAmount, orderDate, status, time FROM orders WHERE orderDate = ?");
                ps.setString(1, orderDate.toString());
                //localdate
                rs = ps.executeQuery();

                while (rs.next()) {
                    orderList.add(new Order(rs.getInt("orderId"), rs.getString("username"), rs.getString("deliveryAddress"), rs.getInt("totalAmount"), LocalDate.parse(rs.getString("orderDate")), rs.getBoolean("status"), LocalTime.parse(rs.getString("time"))));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return orderList;
        }

        return orderList;
    }

    @Override
    public List<Order> getOrderByStatus(boolean status) {
        List<Order> orderList = new ArrayList<>();

        if (con != null) {

            try {
                ps = con.prepareStatement("SELECT orderId, username, deliveryAddress, totalAmount, orderDate, status, time FROM orders WHERE status = ?");
                ps.setBoolean(1, status);
                rs = ps.executeQuery();

                while (rs.next()) {
                    orderList.add(new Order(rs.getInt("orderId"), rs.getString("username"), rs.getString("deliveryAddress"), rs.getInt("totalAmount"), LocalDate.parse(rs.getString("orderDate")), rs.getBoolean("status"), LocalTime.parse(rs.getString("time"))));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return orderList;
        }

        return orderList;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();

        if (con != null) {

            try {
                ps = con.prepareStatement("SELECT orderId, username, deliveryAddress, totalAmount, orderDate, status, time FROM orders");
                rs = ps.executeQuery();

                while (rs.next()) {
                    orderList.add(new Order(rs.getInt("orderId"), rs.getString("username"), LocalDate.parse(rs.getString("orderDate")), rs.getBoolean("status")));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return orderList;
        }

        return orderList;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        if (con != null) {

            try {
                ps = con.prepareStatement("SELECT orderId, username, deliveryAddress, totalAmount, orderDate, status, time FROM orders WHERE orderId = ?");
                ps.setInt(1, orderId);
                rs = ps.executeQuery();

                while (rs.next()) {
                    return new Order(rs.getInt("orderId"), rs.getString("username"), rs.getString("deliveryAddress"), rs.getInt("totalAmount"), LocalDate.parse(rs.getString("orderDate")), rs.getBoolean("status"), LocalTime.parse(rs.getString("time")));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return order;
        }

        return order;

    }

    @Override
    public boolean addAnOrder(Order order) {
        boolean add = false;
        double totAmount = 0.0;
        int orderId = 0;
        if (con != null) {
            try {
                con.setAutoCommit(false);
                ps = con.prepareStatement("INSERT INTO orders(username, deliveryAddress, totalAmount, orderDate, time) VALUES(?,?,?,?,?)");
                ps.setString(1, order.getUsername());
                ps.setString(2, order.getDeliveryAddress());
                ps.setFloat(3, order.getTotAmount());
                ps.setString(4, order.getOrderDate().toString());
                ps.setString(5, order.getTime().toString());
                if (ps.executeUpdate() < 1) {
                    throw new SQLException("Cannot insert order");
                }
                PreparedStatement ps1 = con.prepareStatement("SELECT LAST_INSERT_ID()");
                rs = ps1.executeQuery();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
                ItemDao itm = ItemDaoImpl.getInstance(con);
                Set<Integer> allItemIds = itm.getAllItemIds();
                List<OrderLineItem> orderLineItemList = order.getOrderLineitems();
                for (OrderLineItem orderLineItem : orderLineItemList) {
                    if (allItemIds.contains(orderLineItem.getItemId())) {
                        ps = con.prepareStatement("INSERT INTO orderlineitem(orderId, itemId, quantity, unitCost ) VALUES (?,?,?,?)");
                        ps.setInt(1, orderId);
                        ps.setInt(2, orderLineItem.getItemId());
                        ps.setInt(3, orderLineItem.getQuantity());
                        ps.setFloat(4, orderLineItem.getUnitCost());
                        if (ps.executeUpdate() < 1) {
                            throw new SQLException("Cannot insert orderLineItem");
                        }
                    }
                }
                ps = con.prepareStatement("UPDATE orders SET totalAmount = ? WHERE orderId = ?");
                ps.setDouble(1, order.getTotAmount());
                ps.setInt(2, orderId);
                if (ps.executeUpdate() > 0) {
                    con.commit();
                    add = true;
                }
                        
                ingredientAmount(order);
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                }
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
                try {
                    if (con != null) {
                        con.setAutoCommit(true);
                    }
                } catch (SQLException ex) {
                }
            }
            return add;
        }

        return add;
    }

    @Override
    public boolean editOrder(Order order) {
        boolean edit = false;

        if (con != null) {

            try {
                ps = con.prepareStatement("UPDATE orders SET username = ?, deliveryAddress = ?, totalAmount = ?, orderDate, status = ?, time = ? WHERE orderId = ?");
                ps.setString(1, order.getUsername());
                ps.setString(2, order.getDeliveryAddress());
                ps.setFloat(3, order.getTotAmount());
                ps.setString(4, order.getOrderDate().toString());
                ps.setBoolean(5, order.getStatus());
                ps.setString(6, order.getTime().toString());
                ps.setInt(7, order.getOrderId());

                if (ps.executeUpdate() > 0) {
                    edit = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error:" + ex.getMessage());
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

    public void ingredientAmount(Order order) {
        int ingredientAmountNeeded = 0;
        int ingredientAmountAvailable = 0;
        int recipeIngredient = 0;
        int count = 0;
        int totalQuantity = 0;
        List<OrderLineItem> orderLineItemList = new ArrayList<>();

        //List<Item> orderItemList = order.getItems();
        OrderLineItemDaoImpl orderLineItemDaoImpl = new OrderLineItemDaoImpl(con);

        orderLineItemList = order.getOrderLineitems();//

        // for (Item itm : itemList) {
        for (OrderLineItem olim : orderLineItemList) {

            System.out.println("========================");
            count = count + 1;

            //totalQuantity += olim.getQuantity();
            try {
                ps = con.prepareStatement("SELECT\n"
                        + "    ingredient.name AS \"Ingredient Name\",\n"
                        + "    recipeingredients.ingredientId,\n"
                        + "    recipeingredients.quantity AS \"RecipeIngredient\",\n"
                        + "    ingredient.quantityinStock AS \"IngredientAmountAvailable\"\n"
                        + "    \n"
                        + "FROM orderlineitem\n"
                        + "\n"
                        + "INNER JOIN\n"
                        + " item ON orderlineitem.itemId = item.itemId\n"
                        + " INNER JOIN\n"
                        + " recipeingredients ON item.recipeId = recipeingredients.recipeId\n"
                        + "INNER JOIN\n"
                        + " ingredient ON recipeingredients.ingredientId = ingredient.ingredientId");
                rs = ps.executeQuery();

                System.out.println("/n=================================");
                System.out.println("Number of products, needed: " + olim.getQuantity());
                System.out.println("/n=================================");

                while (rs.next()) {

                    recipeIngredient = rs.getInt("RecipeIngredient");
                    ingredientAmountAvailable = rs.getInt("IngredientAmountAvailable");

                    System.out.println("/n===============================================");
                    System.out.println("RecipeIngredient :" + recipeIngredient);
                    System.out.println("ingredientAmountAvailable :" + ingredientAmountAvailable);
                    int leftIngredientAmnt = ingredientAmountAvailable - (recipeIngredient * olim.getQuantity());
                    System.out.println("leftIngredientAmnt : " + leftIngredientAmnt);
                    System.out.println("/n===============================================");
                    ps = con.prepareStatement("UPDATE ingredient SET quantityinStock = ? WHERE ingredientId = ?");
                    ps.setInt(1, leftIngredientAmnt);
                    ps.setInt(2, rs.getInt("ingredientId"));

                    if (ps.executeUpdate() > 0) {

                        System.out.println("Total Updated");

                    }
                }

            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println("Count Total: " + count);
        }

    }

}
