package za.co.bakery.model;

public class Role {

    private int roleId;
    private int roleDescription;
    private boolean isActive;

    public Role() {
    }

    public Role(int roleId, int roleDescription, boolean isActive) {
        this.roleId = roleId;
        this.roleDescription = roleDescription;
        this.isActive = isActive;
    }

    public Role(int roleId, int roleDescription) {
        this.roleId = roleId;
        this.roleDescription = roleDescription;
        this.isActive = isActive;
    }

    public Role(int roleDescription, boolean isActive) {
        this.roleDescription = roleDescription;
        this.isActive = isActive;
    }

    public Role(boolean isActive) {
        this.isActive = isActive;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(int roleDescription) {
        this.roleDescription = roleDescription;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.roleId;
        hash = 83 * hash + this.roleDescription;
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
        final Role other = (Role) obj;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.roleDescription != other.roleDescription) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Role{" + "roleId=" + roleId + ", roleDescription=" + roleDescription + '}';
    }

}
