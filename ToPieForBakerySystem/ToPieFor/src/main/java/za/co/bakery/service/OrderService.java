package za.co.bakery.service;

import java.time.LocalDate;
import java.util.List;
import za.co.bakery.model.Order;

public interface OrderService {

    public List<Order> getOrderByUsername(String username);

    public List<Order> getOrderByOrderDate(LocalDate orderDate);

    public List<Order> getOrderByStatus(boolean status);

    public List<Order> getAllOrders();

    public Order getOrderById(int orderId);

    public boolean addAnOrder(Order order);

    public boolean editOrder(Order order);
}
