package za.co.bakery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.bakery.dao.UnitDao;
import za.co.bakery.model.Unit;

public class UnitDaoImpl implements UnitDao {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private static UnitDaoImpl unitDaoImpl = null;

    public UnitDaoImpl(Connection con) {
        this.con = con;
    }

    public static UnitDaoImpl getInstance(Connection con) {
        if (unitDaoImpl == null) {
            unitDaoImpl = new UnitDaoImpl(con);
        }
        return unitDaoImpl;
    }

    @Override
    public List<Unit> getAllUnits() {

        List<Unit> unitList = new ArrayList<>();

        if (con == null) {
            return unitList;
        }

        try {
            ps = con.prepareStatement("SELECT unitId, unitDescription FROM unit");
            rs = ps.executeQuery();

            while (rs.next()) {
                unitList.add(new Unit(rs.getInt("unitId"), rs.getString("unitDescription")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return unitList;

    }

    @Override
    public Unit getUnitById(int unitId) {

        Unit unit = null;

        if (con == null) {
            return unit;
        }

        try {
            ps = con.prepareStatement("SELECT unitId, unitDescription FROM unit WHERE unitId = ?");
            ps.setInt(1, unitId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Unit(rs.getInt("unitId"), rs.getString("unitDescription"));
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return unit;

    }

    @Override
    public Unit getUnitByDescription(int unitDescription) {
        Unit unit = null;

        if (con == null) {
            return unit;
        }

        try {
            ps = con.prepareStatement("SELECT unitId, unitDescription FROM item WHERE unitDescription = ?");
            ps.setInt(1, unitDescription);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Unit(rs.getInt("unitId"), rs.getString("unitDescription"));
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return unit;
    }
    
   

}
