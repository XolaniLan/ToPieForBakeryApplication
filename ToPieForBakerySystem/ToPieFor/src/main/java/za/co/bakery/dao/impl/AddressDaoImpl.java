package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.bakery.dao.AddressDao;
import za.co.bakery.model.Address;

public class AddressDaoImpl implements AddressDao {

    private static AddressDaoImpl addressDaoImpl = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AddressDaoImpl(Connection con) {
        this.con = con;
    }

    public static AddressDaoImpl getInstance(Connection dbCon) {
        if (addressDaoImpl == null) {
            addressDaoImpl = new AddressDaoImpl(dbCon);
        }
        return addressDaoImpl;
    }

    @Override
    public List<Address> getAllAddressesByUsername(String username, boolean active) {
        List<Address> addressList = new ArrayList<>();

        if (con == null) {
            return addressList;
        }

        try {
            ps = con.prepareStatement("SELECT addressId, username, isActive FROM useraddress WHERE username = ?,?");
            ps.setString(1, username);
            ps.setBoolean(2, active);
            rs = ps.executeQuery();

            while (rs.next()) {
                addressList.add(new Address(rs.getInt("addressId"), rs.getString("address1"), rs.getString("address2"), rs.getString("province"), rs.getString("city"), rs.getString("code"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return addressList;
    }

    @Override
    public Address getAddressByAddresId(int addressId) {
        Address address = null;

        if (con == null) {
            return address;
        }

        try {
            ps = con.prepareStatement("SELECT addressId, addr1, addr2, province, city , isActive, code FROM address WHERE addressId = ?");
            ps.setInt(1, addressId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Address(rs.getInt("addressId"), rs.getString("addr1"), rs.getString("addr2"), rs.getString("province"), rs.getString("city"), rs.getString("code"), rs.getBoolean("isActive"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return address;
    }

    @Override
    public boolean addAddress(Address address) {
        boolean add = false;

        if (con == null) {
            return add;
        }

        try {
            ps = con.prepareStatement("INSERT INTO address(addr1, addr2, province, city, code) VALUES(?,?,?,?,?)");

            ps.setString(1, address.getAddress1());
            ps.setString(2, address.getAddress2());
            ps.setString(3, address.getProvince());
            ps.setString(4, address.getCity());
            ps.setString(5, address.getCode());

            if (ps.executeUpdate() > 0) {
                add = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        if (add == true) {
            addToUserAddress();
        }
        return add;
    }

    @Override
    public boolean editAddress(Address address) {
        boolean edit = false;

        if (con == null) {
            return edit;
        }

        try {

            ps = con.prepareStatement("UPDATE address SET addr1 = ?, addr2 = ?, province = ?, city = ?, code ? WHERE addressId = ?");
            ps.setString(1, address.getAddress1());
            ps.setString(2, address.getAddress2());
            ps.setString(3, address.getProvince());
            ps.setString(4, address.getCity());
            ps.setString(5, address.getCode());
            ps.setInt(6, address.getAddressId());

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

    @Override
    public boolean activateAddress(Address address) {
        boolean activate = false;

        if (con == null) {
            return activate;
        }
        try {
            ps = con.prepareStatement("UPDATE address SET isActive = ? , WHERE addressId = ?");
            ps.setBoolean(1, address.getIsActive());

            if (ps.executeUpdate() > 0) {
                activate = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return activate;
    }

    @Override
    public boolean addToUserAddress() {
        boolean add = false;
        
        if (con == null) {
            return add;
        }

        try {
            ps = con.prepareStatement("INSERT INTO useraddress (username, addressId) "
                    + "SELECT (SELECT username FROM user ORDER BY username DESC LIMIT 1) AS username, "
                    + "(SELECT addressId FROM address ORDER BY addressId DESC LIMIT 1) AS addressId");

            if (ps.executeUpdate() > 0) {
                add = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
        return add;
    }
}
