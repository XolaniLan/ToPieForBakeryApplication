package za.co.bakery.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Order {

    private int orderId;
    private String username;
    private String deliveryAddress;
    private float totAmount;
    private LocalDate orderDate;
    private boolean status;
    private LocalTime time;

    private List<OrderLineItem> orderLineitems;

    public Order() {
    }
    
     public Order(int orderId, String username, LocalDate orderDate, boolean status) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(int orderId, String username, String deliveryAddress, float totAmount, LocalDate orderDate, boolean status) {
        this.orderId = orderId;
        this.username = username;
        this.deliveryAddress = deliveryAddress;
        this.totAmount = totAmount;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(int orderId, String username, String deliveryAddress, float totAmount, LocalDate orderDate, boolean status, LocalTime time) {
        this.orderId = orderId;
        this.username = username;
        this.deliveryAddress = deliveryAddress;
        this.totAmount = totAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.time = time;
    }

    public Order(String username, String deliveryAddress, float totAmount, LocalDate orderDate, List<OrderLineItem> orderLineitems, LocalTime time) {
        this.username = username;
        this.deliveryAddress = deliveryAddress;
        this.totAmount = totAmount;
        this.orderDate = orderDate;
        this.time = time;
        this.orderLineitems = orderLineitems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public float getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(float totAmount) {
        this.totAmount = totAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean stat) {
        this.status = stat;
    }

    public List<OrderLineItem> getOrderLineitems() {
        return orderLineitems;
    }

    public void setOrderLineitems(List<OrderLineItem> orderLineitems) {
        this.orderLineitems = orderLineitems;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.orderId;
        hash = 83 * hash + Objects.hashCode(this.username);
        hash = 83 * hash + Float.floatToIntBits(this.totAmount);
        hash = 83 * hash + Objects.hashCode(this.orderDate);
        hash = 83 * hash + Objects.hashCode(this.status);
        hash = 83 * hash + Objects.hashCode(this.time);
        hash = 83 * hash + Objects.hashCode(this.orderLineitems);
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
        final Order other = (Order) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.deliveryAddress != other.deliveryAddress) {
            return false;
        }
        if (Float.floatToIntBits(this.totAmount) != Float.floatToIntBits(other.totAmount)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }

        if (!Objects.equals(this.orderLineitems, other.orderLineitems)) {
            return false;
        }
        return true;
    }

}
