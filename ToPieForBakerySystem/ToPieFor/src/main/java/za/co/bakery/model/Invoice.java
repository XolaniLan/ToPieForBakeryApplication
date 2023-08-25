package za.co.bakery.model;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice {

    private int invoiceId;
    private int orderId;
    private float totalAmount;
    private boolean status;
    private LocalDate date;

    public Invoice() {
    }

    public Invoice(int invoiceId, int orderId, float totalAmount, boolean status, LocalDate date) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.date = date;
    }

    public Invoice(float totalAmount, LocalDate date) {
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public Invoice(int orderId, float totalAmount, LocalDate date) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.date = date;
    }
    

    public Invoice(int invoiceId, int orderId, boolean status, LocalDate date) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.status = status;
        this.date = date;
    }

    public Invoice(int orderId, boolean status, LocalDate date) {
        this.orderId = orderId;
        this.status = status;
        this.date = date;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.invoiceId;
        hash = 89 * hash + this.orderId;
        hash = 89 * hash + (this.status ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.date);
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
        final Invoice other = (Invoice) obj;
        if (this.invoiceId != other.invoiceId) {
            return false;
        }
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceId=" + invoiceId + ", orderId=" + orderId + ", status=" + status + ", date=" + date + '}';
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

}
