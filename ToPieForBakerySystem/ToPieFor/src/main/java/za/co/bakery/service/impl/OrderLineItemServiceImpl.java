package za.co.bakery.service.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.OrderLineItemDao;
import za.co.bakery.model.OrderLineItem;
import za.co.bakery.service.OrderLineItemService;

public class OrderLineItemServiceImpl implements OrderLineItemService, ProcessRequest {

    private OrderLineItemDao orderLineItemDao;
    private String orderlineitemView;

    public OrderLineItemServiceImpl(OrderLineItemDao orderLineItemDao) {
        this.orderLineItemDao = orderLineItemDao;
    }

    @Override
    public List<OrderLineItem> getAllOrderLineItems() {
        return orderLineItemDao != null ? orderLineItemDao.getAllOrderLineItems() : null;
    }

    @Override
    public List<OrderLineItem> getOrderLineItemByUnitCost(float unitCost) {
        if (unitCost < 0) {
            return null;
        }
        return orderLineItemDao == null ? null : orderLineItemDao.getOrderLineItemByUnitCost(unitCost);
    }

    @Override
    public OrderLineItem getOrderLineItemByOrderLineItemId(int orderLineItemId) {
        if (orderLineItemId < 0) {
            return null;
        }
        return orderLineItemDao == null ? null : orderLineItemDao.getOrderLineItemByOrderLineItemId(orderLineItemId);
    }

    @Override
    public boolean addOrderLineItem(OrderLineItem orderLineItem) {
        return orderLineItemDao != null ? orderLineItemDao.addOrderLineItem(orderLineItem) : false;
    }

    @Override
    public boolean editOrderLineItem(OrderLineItem orderLineItem) {
        return orderLineItemDao != null ? orderLineItemDao.editOrderLineItem(orderLineItem) : false;
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        
        int orderLineItemId = 0;
        int itemId = 0;
        int quantity = 0;
        float unitCost = 0;
        double discount = 0;
        
        List<OrderLineItem> orderLineItemList = null;
        OrderLineItem orderLineItem = null;

        String action = request.getParameter("act");
        System.out.println(action);

        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {
                case "add":
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                    unitCost = Float.parseFloat(request.getParameter("unitCost"));
                    discount = Double.parseDouble(request.getParameter("discount"));

                    if (quantity != 0 && unitCost != 0 && discount != 0);
                     {
                        if (addOrderLineItem(new OrderLineItem(quantity, discount, unitCost, itemId))) {
                            request.setAttribute("ans", "orderlineitem is added");
                        } else {
                            request.setAttribute("ans", "orderlineitem is not been added");
                        }
                    }

                    break;

                case "editorderlineitem":
                    orderLineItemId = Integer.parseInt(request.getParameter("orderLineItemId"));
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                    unitCost = Float.parseFloat(request.getParameter("unitCost"));
                    discount = Double.parseDouble(request.getParameter("discount"));

                    try {
                        orderLineItemId = Integer.parseInt(request.getParameter("orderLineItemId"));
                    } catch (NumberFormatException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    if (orderLineItemId != 0 && quantity != 0 && unitCost != 0 && discount != 0) {
                        if (editOrderLineItem(new OrderLineItem(orderLineItemId, quantity, discount, unitCost))) {
                            request.setAttribute("ans", "OrderLineItem updated");
                        } else {
                            request.setAttribute("ans", "OrderLineItem not updated");
                        }
                    }
                    break;
                case "getOrderLineItemByOrderLineItemId":

                    orderLineItemId = Integer.parseInt(request.getParameter("orderLineItemId"));
                    orderLineItemList = (List<OrderLineItem>) orderLineItemDao.getOrderLineItemByOrderLineItemId(orderLineItemId);

                    if (orderLineItemList != null) {
                        request.setAttribute("orderlineitem", orderLineItemList);
                    } else {
                        request.setAttribute("ans", "no orderlineitem found");
                    }
                    orderlineitemView = "";

                    break;
                case "getallorderlineitem":

                    orderLineItemList = orderLineItemDao.getAllOrderLineItems();

                    if (orderLineItemList != null && !orderLineItemList.isEmpty()) {
                        request.setAttribute("getAllOrderLineItems", orderLineItemList);
                    } else {
                        request.setAttribute("getAllOrderLineItems", "no order line items");
                    }
                    orderlineitemView = "";

                    break;
                case "getorderlineitembyunitcost":

                    unitCost = Float.parseFloat(request.getParameter("unitCost"));
                    orderLineItemList = (List<OrderLineItem>) orderLineItemDao.getOrderLineItemByUnitCost(unitCost);

                    if (orderLineItemList != null) {
                        request.setAttribute("orderlineitem", orderLineItemList);
                    } else {
                        request.setAttribute("ans", "no orderlineitem found");
                    }
                    orderlineitemView = "";

            }

        }

    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(orderlineitemView);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }
}
