package za.co.bakery.dao;

import java.util.List;
import za.co.bakery.model.Unit;

public interface UnitDao {

    public List<Unit> getAllUnits();

    public Unit getUnitById(int unitId);

    public Unit getUnitByDescription(int unitDescription);
}
