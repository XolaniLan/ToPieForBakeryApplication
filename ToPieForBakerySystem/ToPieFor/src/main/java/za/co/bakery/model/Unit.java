package za.co.bakery.model;

import java.util.Objects;

public class Unit {

    private int unitId;
    private String unitDescription;

    public Unit() {
    }

    public Unit(int unitId, String unitDescription) {
        this.unitId = unitId;
        this.unitDescription = unitDescription;
    }

    public Unit(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.unitId;
        hash = 47 * hash + Objects.hashCode(this.unitDescription);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Unit other = (Unit) obj;
        if (this.unitId != other.unitId) {
            return false;
        }
        if (!Objects.equals(this.unitDescription, other.unitDescription)) {
            return false;
        }
        return true;
    }

  

    @Override
    public String toString() {
        return "Unit{" + "unitId=" + unitId + ", unitDescription=" + unitDescription + '}';
    }

}
