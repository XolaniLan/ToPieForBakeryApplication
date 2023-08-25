package za.co.bakery.dao;

import java.util.List;
import za.co.bakery.model.OrderLineItem;

public interface OrderLineItemDao {

    public List<OrderLineItem> getAllOrderLineItems();

    public List<OrderLineItem> getOrderLineItemByUnitCost(float unitCost);

    public OrderLineItem getOrderLineItemByOrderLineItemId(int orderLineItemId);

    public boolean addOrderLineItem(OrderLineItem orderLineItem);

    public boolean editOrderLineItem(OrderLineItem orderLineItem);

}
