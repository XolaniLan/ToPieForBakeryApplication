package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import za.co.bakery.dao.InvoiceDao;
import za.co.bakery.model.Invoice;

public class InvoiceDaoImpl implements InvoiceDao {

    private static InvoiceDaoImpl invoiceDaoImpl = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private InvoiceDaoImpl(Connection con) {
        this.con = con;
    }

    public static InvoiceDaoImpl getInstance(Connection dbCon) {
        if (invoiceDaoImpl == null) {
            invoiceDaoImpl = new InvoiceDaoImpl(dbCon);
        }
        return invoiceDaoImpl;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoiceList = new ArrayList<>();

        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT invoiceId, orderId, date, status from invoice");
                rs = ps.executeQuery();

                while (rs.next()) {
                    invoiceList.add(new Invoice(rs.getInt("invoiceId"), rs.getInt("orderId"), rs.getBoolean("status"), LocalDate.parse(rs.getString("date"))));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return invoiceList;
        }

        return invoiceList;
    }

    @Override
    public Invoice getInvoiceById(int invoiceId) {
        Invoice invoice = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT orderId, date, status FROM invoice WHERE invoiceId = ?");
                ps.setInt(1, invoiceId);
                rs = ps.executeQuery();

                while (rs.next()) {
                    return new Invoice(rs.getInt("orderId"), rs.getBoolean("status"), LocalDate.parse(rs.getString("date")));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return invoice;
        }

        return invoice;
    }
    
    

    @Override
    public Invoice getInvoiceByStatus(boolean status) {
        Invoice invoice = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT orderId, date, status FROM invoice WHERE status = ?");
                ps.setBoolean(1, status);
                rs = ps.executeQuery();

                while (rs.next()) {
                    return new Invoice(rs.getInt("orderId"), rs.getBoolean("status"), LocalDate.parse(rs.getString("date")));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return invoice;
        }

        return invoice;
    }

    @Override
    public Invoice getInvoiceByDate(LocalDate datePaid) {
        Invoice invoice = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT orderId, date, status FROM invoice WHERE status = ?");
                ps.setString(1, datePaid.toString());
                rs = ps.executeQuery();

                while (rs.next()) {
                    return new Invoice(rs.getInt("orderId"), rs.getBoolean("status"), LocalDate.parse(rs.getString("date")));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return invoice;
        }

        return invoice;
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
        boolean add = false;

        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT (SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1) AS orderId ");
                rs = ps.executeQuery();
                int orderId = 0;
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
                
                ps = con.prepareStatement("INSERT INTO invoice(orderId, date, totalAmount) VALUES(?,?,?)");
                ps.setInt(1, orderId);
                ps.setString(2, invoice.getDate().toString());
                ps.setFloat(3, invoice.getTotalAmount());

                if (ps.executeUpdate() > 0) {
                    add = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return add;
        }
        return add;
    }

    @Override
    public boolean editInvoice(Invoice invoice) {
        boolean edit = false;

        if (con != null) {
            try {
                ps = con.prepareStatement("");
                ps.setInt(1, invoice.getOrderId());
                ps.setString(2, invoice.getDate().toString());
                ps.setBoolean(3, invoice.getStatus());
                ps.setInt(4, invoice.getInvoiceId());

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
        return edit;
    }

    @Override
    public Invoice getLastInsertedInvoice() {
        Invoice invoice = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM invoice ORDER BY invoiceId DESC LIMIT 1");
                rs = ps.executeQuery();

                while (rs.next()) {
                    return new Invoice(rs.getInt("invoiceId"), rs.getInt("orderId"),rs.getFloat("totalAmount"), rs.getBoolean("status"), LocalDate.parse(rs.getString("date")));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            return invoice;
        }

        return invoice;}

}
