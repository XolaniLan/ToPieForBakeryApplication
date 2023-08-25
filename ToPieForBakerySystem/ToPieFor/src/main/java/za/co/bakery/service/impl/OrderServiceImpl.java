package za.co.bakery.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.PasswordAuthentication;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.InvoiceDao;
import za.co.bakery.dao.ItemDao;
import za.co.bakery.dao.OrderDao;
import za.co.bakery.model.Invoice;
import za.co.bakery.model.Item;
import za.co.bakery.model.Order;
import za.co.bakery.model.OrderLineItem;

import za.co.bakery.service.OrderService;

public class OrderServiceImpl implements OrderService, ProcessRequest {

    private OrderDao orderDao;
    private ItemDao itemDao;
    private InvoiceDao invoiceDao;
    private String orderView;

    public OrderServiceImpl(OrderDao orderDao, ItemDao itemDao, InvoiceDao invoiceDao) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
        this.invoiceDao = invoiceDao;
    }

    @Override
    public List<Order> getOrderByUsername(String username) {
        if (username == null && username.isEmpty()) {
            return null;
        }

        return orderDao != null ? orderDao.getOrderByUsername(username) : null;
    }

    @Override
    public List<Order> getOrderByOrderDate(LocalDate orderDate) {
        if (orderDate == null) {
            return null;
        }

        return orderDao != null ? orderDao.getOrderByOrderDate(orderDate) : null;
    }

    @Override
    public List<Order> getOrderByStatus(boolean status) {

        return orderDao != null ? orderDao.getOrderByStatus(status) : null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao != null ? orderDao.getAllOrders() : null;
    }

    @Override
    public Order getOrderById(int orderId) {
        if (orderId < 0) {
            return null;
        }

        return orderDao == null ? null : orderDao.getOrderById(orderId);
    }

    @Override
    public boolean addAnOrder(Order order) {
        return orderDao != null ? orderDao.addAnOrder(order) : false;
    }

    @Override
    public boolean editOrder(Order order) {
        return orderDao != null ? orderDao.editOrder(order) : false;
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orderList = null;
        Order order = null;
        int orderId = 0;
        float payment = 0;
        float totalAmount = 0;
        String username = "";
        String deliveryAddress = "";
        String cartInfo = "";
        boolean status = false;

        Invoice invoice = null;

        LocalDate orderDate = LocalDate.now();
        LocalTime time = LocalTime.now();

        ObjectMapper objectMapper = new ObjectMapper();
        List<OrderLineItem> orderLineItemList = null;
        OrderLineItem[] orderLineItems = null;

        String action = request.getParameter("act");

        System.out.println(action);

        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {

                case "gocheckout":
                    orderView = "checkout.jsp";
                    break;

                case "add":

                    username = request.getParameter("username");
                    deliveryAddress = request.getParameter("deliveryAddress");

                    try {
                        totalAmount = Float.parseFloat(request.getParameter("totalval"));
                    } catch (NumberFormatException nfe) {
                    }

                    if (username != null && !username.trim().isEmpty()) {
                        username = username.trim();
                    }

                    cartInfo = request.getParameter("cartlist");

                    try {
                        orderLineItems = objectMapper.readValue(cartInfo, OrderLineItem[].class);
                        orderLineItemList = objectMapper.readValue(cartInfo, new TypeReference<List<OrderLineItem>>() {
                        });

                    } catch (JsonProcessingException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }

                    //randomize a number
                    payment = (float) ((Math.random() * 10));
                    //check if the number is < or > the cart total and  redirect to decline or successful payment jsp
                    if (payment > 5) {
                        orderView = "paymentdeclined.jsp";
                    } else {
                        if (!username.isEmpty()) {
                            if (addAnOrder(new Order(username, deliveryAddress, totalAmount, orderDate, orderLineItemList, time))) {
                                invoiceDao.addInvoice(new Invoice(totalAmount, orderDate));
                                createEmail(username, orderLineItemList, totalAmount);
                                request.setAttribute("ans", "order added");
                            } else {
                                request.setAttribute("ans", "order not added");
                            }
                        }
                        orderView = "paymentsuccessful.jsp";
                    }

                    break;

                case "allorders":
                    orderList = orderDao.getAllOrders();

                    if (orderList != null && !orderList.isEmpty()) {
                        request.setAttribute("allorders", orderList);
                    } else {
                        request.setAttribute("ans", "no orders yet");
                    }
                    orderView = "orderView.jsp";
                    break;
                case "usernameorders":

                    username = request.getParameter("username");

                    orderList = orderDao.getOrderByUsername(username);

                    if (orderList != null && !orderList.isEmpty()) {
                        request.setAttribute("userorders", orderList);
                    } else {
                        request.setAttribute("ans", "no orders by user");
                    }
                    break;
                case "statusorders":
                    status = Boolean.parseBoolean(request.getParameter("status"));

                    orderList = orderDao.getOrderByStatus(status);

                    if (orderList != null && !orderList.isEmpty()) {
                        request.setAttribute("statusorders", orderList);
                    } else {
                        request.setAttribute("ans", "no orders by status");
                    }
                    break;
                case "dateorders":
                    orderDate = LocalDate.parse(request.getParameter("orderDate"));

                    orderList = orderDao.getOrderByOrderDate(orderDate);

                    if (orderList != null && !orderList.isEmpty()) {
                        request.setAttribute("dateorders", orderList);
                    } else {
                        request.setAttribute("ans", "no orders on this date");
                    }
                    break;
                case "idorders":
                    orderId = Integer.parseInt(request.getParameter("orderId"));

                    order = orderDao.getOrderById(orderId);

                    if (order != null) {
                        request.setAttribute("orderid", order);
                    } else {
                        request.setAttribute("ans", "Order not found");
                    }
                    break;

                case "edit":
                    break;

            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(orderView);

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }

    private void createEmail(String email, List<OrderLineItem> orderLineItems, float total) {
        String fromEmail = "topieforbakery2@gmail.com";
        String password = "jmswyppsiiadpexj";

        // SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create session
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create PDF invoice using iText or Apache PDFBox
            String invoicePath = "C:\\Users\\STUDIO 18\\Desktop\\ToPieFor\\ToPieFor\\src\\main\\webapp\\images\\invoice.pdf";

            createInvoicePDF(invoicePath, orderLineItems, total, email);

            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Invoice");

            // Create multipart message
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText("Dear valued customer" + ",\n\nThank you for your purchase on our product(s)! Please find the invoice attached.\n\nKind regards\nToPieFor Bakery");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();

            // Create PDF attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(invoicePath);
            multipart.addBodyPart(attachmentPart);

            // Set content
            message.setContent(multipart);

            // Send message
            new Thread(
                    new Runnable(){
                        public void run(){
                            try {
                                Transport.send(message);
                            } catch (MessagingException ex) {
                            }
                        }
                    }).start();
          //  Transport.send(message);

            System.out.println("Invoice sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createInvoicePDF(String invoicePath, List<OrderLineItem> orderLineItems, float total, String email) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(invoicePath));
            document.open();

            // Add invoice details
            Paragraph details = new Paragraph();
            LocalDate invoiceDate = LocalDate.now();
            details.add(new Phrase("To Pie For Invoice Details \t\t " + invoiceDate + " \n\n 16th, Midrand, Johannesburg, South Africa\n"
                    + "+2711 7576 334 \n"
                    + "topieforbakery2@gmail.com\n\n"
                    + "Invoice For: " + email +"\n\n",FontFactory.getFont(FontFactory.HELVETICA, 14)));
            
            document.add(details);

            // Add items table
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            table.addCell("Item Id");
            table.addCell("Item Name");
            table.addCell("Quantity");
            table.addCell("Price");

            Item item = null;

            // Add items
            for (OrderLineItem orderLineItem : orderLineItems) {
                item = itemDao.getItemNameById(orderLineItem.getItemId());
                table.addCell(String.valueOf(orderLineItem.getItemId()));
                table.addCell(item.getItemName());
                table.addCell(String.valueOf(orderLineItem.getQuantity()));
                table.addCell(String.valueOf(orderLineItem.getUnitCost()));
            }

            document.add(table);
            // Add total
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            Paragraph totalParagraph = new Paragraph();
            totalParagraph.add(new Phrase("Total: R" + decimalFormat.format(total), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
            totalParagraph.setAlignment(Element.ALIGN_RIGHT);
            totalParagraph.setSpacingBefore(10f);
            document.add(totalParagraph);

            document.close();

            System.out.println("Invoice PDF created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
