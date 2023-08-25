package za.co.bakery.dao;

import java.util.List;
import za.co.bakery.model.Role;

public interface RoleDao {

    public List<Role> getAllRoles();

    public Role getRoleByDescription(int roleDescription, boolean active);

    public Role getRoleByRoleId(int roleId);

    public boolean editRole(Role role);

    public boolean addRole(Role role);

    public boolean activateRole(Role role);

}
