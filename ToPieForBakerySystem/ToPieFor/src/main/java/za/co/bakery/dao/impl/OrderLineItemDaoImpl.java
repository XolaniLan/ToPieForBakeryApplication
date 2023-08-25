package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.bakery.dao.OrderLineItemDao;
import za.co.bakery.model.OrderLineItem;

public class OrderLineItemDaoImpl implements OrderLineItemDao {

    private static OrderLineItemDaoImpl orderLineItemDaoImpl = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public OrderLineItemDaoImpl(Connection con) {
        this.con = con;
    }

    public static OrderLineItemDaoImpl getInstance(Connection dbCon) {
        if (orderLineItemDaoImpl == null) {
            orderLineItemDaoImpl = new OrderLineItemDaoImpl(dbCon);
        }

        return orderLineItemDaoImpl;
    }

    @Override
    public List<OrderLineItem> getAllOrderLineItems() {
        List<OrderLineItem> orderlineitemList = new ArrayList<>();

        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT orderLineItemId, orderId, itemId, quantity, unitCost, discount FROM orderlineitem");
                rs = ps.executeQuery();
                while (rs.next()) {
                    orderlineitemList.add(new OrderLineItem(rs.getInt("orderLineItemId"), rs.getInt("orderId"), rs.getInt("itemId"), rs.getInt("quantity"),rs.getDouble("discount"), rs.getFloat("unitCost")));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return orderlineitemList;
        }
        return orderlineitemList;

    }

    @Override
    public List<OrderLineItem> getOrderLineItemByUnitCost(float unitCost) {
        List<OrderLineItem> orderlineitemList = new ArrayList<>();

        if (con != null) {

            try {
                ps = con.prepareStatement("SELECT orderLineItemId, orderId, itemId, quantity, unitCost, discount FROM orderlineitem WHERE orderLineItemId = ?");
                ps.setFloat(1, unitCost);
                rs = ps.executeQuery();

                while (rs.next()) {
                    orderlineitemList.add(new OrderLineItem(rs.getInt("orderLineItemId"), rs.getInt("orderId"), rs.getInt("itemId"), rs.getInt("quantity"), rs.getFloat("unitCost"), (float) rs.getDouble("discount")));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return orderlineitemList;
        }

        return orderlineitemList;

    }

    @Override
    public OrderLineItem getOrderLineItemByOrderLineItemId(int orderLineItemId) {
        OrderLineItem orderLineItem = null;
        if (con != null) {

            try {
                ps = con.prepareStatement("SELECT orderLineItemId, orderId, itemId, quantity, unitCost, discount FROM orderlineitem WHERE orderLineItemId = ?");
                ps.setInt(1, orderLineItemId);
                rs = ps.executeQuery();

                while (rs.next()) {
                    return new OrderLineItem(rs.getInt("orderLineItemId"), rs.getInt("orderId"), rs.getInt("itemId"), rs.getInt("quantity"), rs.getFloat("unitCost"), (float) rs.getDouble("discount"));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return orderLineItem;
        }
        return orderLineItem;
    }

    @Override
    public boolean addOrderLineItem(OrderLineItem orderLineItem) {
        boolean add = false;

        if (con != null) {

            try {
                ps = con.prepareStatement("INSERT INTO orderlineitem(orderId, itemId, quantity, unitCost, discount) VALUES(?,?,?,?,?)");
                ps.setInt(1, orderLineItem.getOrderId());
                ps.setInt(2, orderLineItem.getItemId());
                ps.setInt(3, orderLineItem.getQuantity());
                ps.setFloat(4, orderLineItem.getUnitCost());
                ps.setDouble(5, orderLineItem.getDiscount());

                if (ps.executeUpdate() > 0) {
                    add = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error:" + ex.getMessage());
            }
            return add;
        }

        return add;
    }

    @Override
    public boolean editOrderLineItem(OrderLineItem orderLineItem) {
        boolean edit = false;

        if (con != null) {

            try {
                ps = con.prepareStatement("");
                ps.setInt(1, orderLineItem.getOrderLineItemId());
                ps.setInt(2, orderLineItem.getOrderId());
                ps.setInt(3, orderLineItem.getItemId());
                ps.setInt(4, orderLineItem.getQuantity());
                ps.setFloat(5, orderLineItem.getUnitCost());
                ps.setDouble(6, orderLineItem.getDiscount());

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

}
