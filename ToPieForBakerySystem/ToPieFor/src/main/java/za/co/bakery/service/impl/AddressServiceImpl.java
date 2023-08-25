package za.co.bakery.service.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.AddressDao;
import za.co.bakery.model.Address;
import za.co.bakery.service.AddressService;

public class AddressServiceImpl implements AddressService, ProcessRequest {

    private AddressDao addressDao;
    private String addressView;

    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public List<Address> getAllAddressesByUsername(String username, boolean active) {
        if (username.isEmpty() || active == false) {
            return null;
        }
        return addressDao == null ? null : addressDao.getAllAddressesByUsername(username, active);
    }

    @Override
    public Address getAddressByAddresId(int addressId) {
        if (addressId < 0) {
            return null;
        }
        return addressDao == null ? null : addressDao.getAddressByAddresId(addressId);
    }

    @Override
    public boolean addAddress(Address address) {
        return address == null ? false : addressDao.addAddress(address);
    }

    @Override
    public boolean editAddress(Address address) {
        return address == null ? false : addressDao.editAddress(address);
    }

    @Override
    public boolean activateAddress(Address address) {
        return address == null ? false : addressDao.activateAddress(address);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        int addressId = 0;
        String address1 = "";
        String address2 = "";
        String province = "";
        String city = "";
        String code = "";
        String username = "";
        boolean isActive = false;

        List<Address> addresses = null;
        Address address = null;

        String action = request.getParameter("act");
        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {
                case "addaddress":
                    System.out.println("inside");

                    try {
                        address1 = request.getParameter("address1");
                        address2 = request.getParameter("address2");
                        province = request.getParameter("province");
                        city = request.getParameter("city");
                        code = request.getParameter("code");
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if ((address1 != null && !address1.trim().isEmpty() && (address2 != null && !address2.trim().isEmpty()))) {
                        address1 = address1.trim();
                        address2 = address2.trim();
                    }

                    if (!address1.isEmpty() && !address2.isEmpty()) {
                        if (addAddress(new Address(address1, address2, province, city, code))) {
                            request.setAttribute("ans", "Address added.");
                            addressView = "login.jsp";

                        } else {
                            request.setAttribute("ans", "Address not added.");
                            addressView = "address.jsp";
                        }
                    }

                    break;

                case "useraddress":

                    try{
                    username = request.getParameter("username");
                    isActive = Boolean.parseBoolean(request.getParameter("isActive"));
                    addresses = addressDao.getAllAddressesByUsername(username, isActive);}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (addresses != null && !addresses.isEmpty()) {

                        request.setAttribute("allcategories", addresses);

                    } else {
                        request.setAttribute("ans", "No addresses, Yet!");
                    }
                    addressView = "addressview.jsp";
                    break;
                case "addressbyid":

                    try{
                    addressId = Integer.parseInt(request.getParameter("addressId"));
                    address = addressDao.getAddressByAddresId(addressId);}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (address != null) {
                        request.setAttribute("address", address);
                    } else {
                        request.setAttribute("ans", "no address found");
                    }

                    addressView = "addressview.jsp";
                    break;

                case "edit":
                    try{
                    address1 = request.getParameter("address1");
                    address2 = request.getParameter("address2");
                    province = request.getParameter("province");
                    city = request.getParameter("city");
                    code = request.getParameter("code");
                    isActive = Boolean.parseBoolean(request.getParameter("isActive"));}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (address1 != null) {
                        try {
                            addressId = Integer.parseInt(request.getParameter("addressId"));
                        } catch (NumberFormatException ex) {
                            System.out.println("Error:" + ex.getMessage());
                        }

                        if (!address1.isEmpty() && addressId > 0) {
                            if (editAddress(new Address(address1, address2, province, city, code, isActive))) {
                                request.setAttribute("ans", "Address updated.");
                            } else {
                                request.setAttribute("ans", "Address not updated.");
                            }
                        }
                    }
                    break;

                case "activate":
                    try{
                    address1 = request.getParameter("address1");
                    isActive = Boolean.parseBoolean(request.getParameter("isActive"));
                    }catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (address1 != null) {
                        try {
                            addressId = Integer.parseInt(request.getParameter("addressId"));
                        } catch (NumberFormatException ex) {
                            System.out.println("Error:" + ex.getMessage());
                        }

                        if (!address1.isEmpty() && addressId > 0) {
                            if (activateAddress(new Address(isActive))) {
                                request.setAttribute("ans", "Address updated.");
                            } else {
                                request.setAttribute("ans", "Address not updated.");
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(addressView);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }

    }

}
