package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.bakery.dao.RoleDao;
import za.co.bakery.model.Role;

public class RoleDaoImpl implements RoleDao {

    private static RoleDaoImpl roleDaoImpl = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public RoleDaoImpl(Connection con) {
        this.con = con;
    }

    public static RoleDaoImpl getInstance(Connection dbCon) {
        if (roleDaoImpl == null) {
            roleDaoImpl = new RoleDaoImpl(dbCon);
        }
        return roleDaoImpl;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roleList = new ArrayList<>();

        if (con == null) {
            return roleList;
        }
        try {
            ps = con.prepareStatement("SELECT roleId, roleDescription");
            rs = ps.executeQuery();

            while (rs.next()) {
                roleList.add(new Role(rs.getInt("roleId"), rs.getInt("roleDescription"), rs.getBoolean("isActive")));
            }
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        return roleList;
    }

    @Override
    public Role getRoleByDescription(int roleDescription, boolean active) {
        Role role = null;

        if (con == null) {
            return role;
        }
        try {
            ps = con.prepareStatement("SELECT roleId, roleDescription, isActive FROM role WHERE roleDescription = ?");
            ps.setBoolean(roleDescription, active);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Role(rs.getInt("roleId"), rs.getInt("roleDescription"), rs.getBoolean("isActive"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return role;
    }

    @Override
    public Role getRoleByRoleId(int roleId) {
        Role role = null;

        if (con == null) {
            return role;
        }
        try {
            ps = con.prepareStatement("SELECT roleId, roleDescription FROM role WHERE roldId = ?");
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Role(rs.getInt("roleId"), rs.getInt("roleDescription"), rs.getBoolean("isActive"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean addRole(Role role) {
        boolean add = false;

        if (con == null) {
            return add;
        }
        try {
            ps = con.prepareStatement("INSERT INTO role(roleID, roleDescription) VALUES(?,?) ");
            ps.setInt(1, role.getRoleId());
            ps.setInt(2, role.getRoleDescription());

            if (ps.executeUpdate() > 0) {
                add = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return add;
    }

    @Override
    public boolean activateRole(Role role) {
        boolean acti = false;

        if (con == null) {
            return acti;
        }
        try {
            ps = con.prepareStatement("UPDATE role SET isActive = ? WHERE roleId = ?");
            ps.setBoolean(1, role.getIsActive());
            ps.setInt(2, role.getRoleId());
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return acti;
    }

    @Override
    public boolean editRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
