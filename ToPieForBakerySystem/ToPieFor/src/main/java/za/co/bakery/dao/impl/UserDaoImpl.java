package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.bakery.dao.UserDao;
import za.co.bakery.model.User;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl userDaoImpl = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private UserDaoImpl(Connection con) {
        this.con = con;
    }

    public static UserDaoImpl getInstance(Connection dbCon) {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl(dbCon);
        }
        return userDaoImpl;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        if (con == null) {
            return userList;
        }
        try {
            ps = con.prepareStatement("SELECT  username,roleId, password, name, surname, contactNumber, email, isActive FROM user");
            rs = ps.executeQuery();

            while (rs.next()) {
                userList.add(new User(rs.getInt("roleId"), rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("surname"), rs.getString("contactNumber"), rs.getString("email"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return userList;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;

        if (con == null) {
            return user;
        }

        try {
            ps = con.prepareStatement("SELECT roleId, username, password, name, surname, contactNumber, email, isActive FROM user WHERE username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new User(rs.getInt("roleId"), rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("surname"), rs.getString("contactNumber"), rs.getString("email"), rs.getBoolean("isActive"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return user;

    }

    @Override
    public boolean userLogin(String username, String password, int role) {
        boolean login = false;

        if (con == null) {
            return login;
        }

        try {
            ps = con.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ? AND roleId = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, role);
            rs = ps.executeQuery();
            if (rs.next()) {
                login = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error!!: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Could not close resultset: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Could not close: " + ex.getMessage());
                }
            }
        }
        return login;
    }

    @Override
    public boolean addAUser(User user
    ) {
        boolean add = false;

        if (con == null) {
            return add;
        }
        try {
            ps = con.prepareStatement("INSERT INTO user(roleId, username, password, name, surname, contactNumber, email) VALUES(?,?,?,?,?,?,?)");
            ps.setInt(1, user.getRoleId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSurname());
            ps.setString(6, user.getContactNumber());
            ps.setString(7, user.getEmail());

            if (ps.executeUpdate() > 0) {
                add = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return add;
    }

    @Override
    public boolean editUser(User user
    ) {
        boolean edit = false;
        if (con != null) {
            try {

                ps = con.prepareStatement("UPDATE user SET password = ? ,name = ?, surname = ?, contactNumber = ?, email = ? WHERE username = ?");
                ps.setString(1, user.getPassword());
                ps.setString(2, user.getName());
                ps.setString(3, user.getSurname());
                ps.setString(4, user.getContactNumber());
                ps.setString(5, user.getEmail());
                ps.setString(6, user.getUsername());
                if (ps.executeUpdate() > 0) {
                    edit = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return edit;
    }

    @Override
    public boolean activateUser(User user
    ) {
        boolean activate = false;

        if (con == null) {
            return activate;
        }
        try {
            ps = con.prepareCall("UPDATE user SET isActive = ? WHERE username =? ");
            ps.setBoolean(1, user.getIsActive());
            ps.setString(2, user.getUsername());
            if (ps.executeUpdate() > 0) {
                activate = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return activate;
    }

}
