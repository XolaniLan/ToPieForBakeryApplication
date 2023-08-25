package za.co.bakery.service;

import java.util.List;
import za.co.bakery.model.Role;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRoleByDescription(int roleDescription, boolean active);

    public Role getRoleByRoleId(int roleId);

    public boolean editRole(Role role);

    public boolean addRole(Role role);

    public boolean activateRole(Role role);
}
