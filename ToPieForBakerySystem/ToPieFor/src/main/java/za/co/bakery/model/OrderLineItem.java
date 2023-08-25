package za.co.bakery.model;

public class OrderLineItem {

    private int orderLineItemId;
    private int orderId;
    private int itemId;
    private int quantity;
    private double discount;
    private float unitCost;

    public OrderLineItem() {
    }

    
    public OrderLineItem(int itemId, int quantity, float unitCost) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }
    
    public OrderLineItem(int quantity, double discount, float unitCost, int itemId) {     
        this.quantity = quantity;
        this.discount = discount;
        this.unitCost = unitCost;
        this.itemId = itemId;
    }

    public OrderLineItem(int orderLineItemId, int quantity, double discount, float unitCost) {
        this.orderLineItemId = orderLineItemId;
        this.quantity = quantity;
        this.discount = discount;
        this.unitCost = unitCost;
    }

    public OrderLineItem(int orderLineItemId, int orderId, int itemId, int quantity, double discount, float unitCost) {
        this.orderLineItemId = orderLineItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.discount = discount;
        this.unitCost = unitCost;
    }

    public OrderLineItem(int orderId, int itemId, int quantity, double discount, float unitCost) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.discount = discount;
        this.unitCost = unitCost;
    }

    public int getOrderLineItemId() {
        return orderLineItemId;
    }

    public void setOrderLineItemId(int orderLineItemId) {
        this.orderLineItemId = orderLineItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(float unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.orderLineItemId;
        hash = 43 * hash + this.orderId;
        hash = 43 * hash + this.itemId;
        hash = 43 * hash + this.quantity;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.discount) ^ (Double.doubleToLongBits(this.discount) >>> 32));
        hash = 43 * hash + Float.floatToIntBits(this.unitCost);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderLineItem other = (OrderLineItem) obj;
        if (this.orderLineItemId != other.orderLineItemId) {
            return false;
        }
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.itemId != other.itemId) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.discount != other.discount) {
            return false;
        }
        if (Float.floatToIntBits(this.unitCost) != Float.floatToIntBits(other.unitCost)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderlineItem{" + "orderLineItemId=" + orderLineItemId + ", orderId=" + orderId + ", itemId=" + itemId + ", quantity=" + quantity + ", discount=" + discount + ", unitCost=" + unitCost + '}';
    }

}
