package za.co.bakery.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.InvoiceDao;
import za.co.bakery.model.Invoice;
import za.co.bakery.service.InvoiceService;

public class InvoiceServiceImpl implements InvoiceService, ProcessRequest {

    private InvoiceDao invoiceDao;
    private String invoiceView;

    public InvoiceServiceImpl(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDao != null ? invoiceDao.getAllInvoices() : null;
    }

    @Override
    public Invoice getInvoiceById(int invoiceId) {
        if (invoiceId < 0) {
            return null;
        }

        return invoiceDao == null ? null : invoiceDao.getInvoiceById(invoiceId);
    }

    @Override
    public Invoice getInvoiceByStatus(boolean status) {
        if (status = false) {
            return null;
        }
        return invoiceDao == null ? null : invoiceDao.getInvoiceByStatus(status);
    }

    @Override
    public Invoice getInvoiceByDate(LocalDate datePaid) {
        if (datePaid == null) {
            return null;
        }

        return invoiceDao != null ? invoiceDao.getInvoiceByDate(datePaid) : null;
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
        return invoice == null ? false : invoiceDao.addInvoice(invoice);
    }

    @Override
    public boolean editInvoice(Invoice invoice) {
        return invoice == null ? false : invoiceDao.addInvoice(invoice);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        int orderId = 0;
        LocalDate datePaid = LocalDate.now();
        boolean status = false;

        List<Invoice> invoiceList = null;
        Invoice invoice = null;
        int invoiceId = 0;

        String action = request.getParameter("act");
        System.out.println(action);

        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {
                case "add":
                    try{
                     orderId = Integer.parseInt(request.getParameter("orderId"));
                     status = Boolean.parseBoolean(request.getParameter("status"));}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (orderId != 0 && orderId != 0);
                     {
                        if (addInvoice(new Invoice(orderId, status, datePaid))) {
                            request.setAttribute("ans", "invoice has been added");
                        } else {
                            request.setAttribute("ans", "invoice has not been added");
                        }
                    }
                    break;

                case "editInvoice":
                    
                    try{
                    invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
                    orderId = Integer.parseInt(request.getParameter("orderId"));
                    status = Boolean.parseBoolean(request.getParameter("status"));}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }
                    
                    if (invoiceId != 0) {
                        try {
                            invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
                        } catch (NumberFormatException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                        if (invoiceId != 0 && orderId > 0) {
                            if (editInvoice(new Invoice(invoiceId, orderId, status, datePaid))) {
                                request.setAttribute("ans", "Invoice Updated");
                            } else {
                                request.setAttribute("ans", "Invoice not Updated");

                            }
                        }

                    }
                    break;
                case "getinvoicebyid":

                    try{
                    invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
                    invoice = invoiceDao.getInvoiceById(invoiceId);}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (invoice != null) {
                        request.setAttribute("invoice", invoice);
                    } else {
                        request.setAttribute("invoice", "no invoice found");
                    }
                    invoiceView = "invoiceview.jsp";

                    break;

                case "invoicebystatus":
                    try{
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    invoice = invoiceDao.getInvoiceByStatus(status);}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (invoice != null) {
                        request.setAttribute("invoice", invoice);
                    } else {
                        request.setAttribute("invoice", "no invoice found");
                    }
                    invoiceView = "invoiceview.jsp";

                    break;
                case "viewallinvoices":
                    invoiceList = invoiceDao.getAllInvoices();
                    if (invoiceList != null && !invoiceList.isEmpty()) {
                        request.setAttribute("getallinvoices", invoiceList);
                    } else {
                        request.setAttribute("getallinvoices", "No Invoices Yet!");
                    }
                    invoiceView = "invoiceView.jsp";

                    break;
                case "getinvoicesbydatepaid":
                    invoice = invoiceDao.getInvoiceByDate(datePaid);
                    if (invoice != null) {
                        request.setAttribute("datePaid", invoice);
                    } else {
                        request.setAttribute("datePaid", "no datePaid found");
                    }
                    invoiceView = "invoiceview.jsp";
            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(invoiceView);

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }
}
