package za.co.bakery.dao;

import java.time.LocalDate;
import java.util.List;
import za.co.bakery.model.Invoice;

public interface InvoiceDao {

    public List<Invoice> getAllInvoices();

    public Invoice getLastInsertedInvoice();

    public Invoice getInvoiceById(int invoiceId);

    public Invoice getInvoiceByStatus(boolean status);

    public Invoice getInvoiceByDate(LocalDate datePaid);

    public boolean addInvoice(Invoice invoice);

    public boolean editInvoice(Invoice invoice);
}
