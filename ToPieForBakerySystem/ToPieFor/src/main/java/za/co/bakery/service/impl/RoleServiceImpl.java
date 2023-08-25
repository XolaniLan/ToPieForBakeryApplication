package za.co.bakery.service.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.RoleDao;
import za.co.bakery.model.Role;
import za.co.bakery.service.RoleService;

public class RoleServiceImpl implements RoleService, ProcessRequest {

    private RoleDao roleDao;
    private String roleView;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao == null ? null : roleDao.getAllRoles();
    }

    @Override
    public Role getRoleByDescription(int roleDescription, boolean active) {
        if (roleDescription < 0) {
            return null;
        }
        return roleDao == null ? null : roleDao.getRoleByDescription(roleDescription, active);
    }

    @Override
    public Role getRoleByRoleId(int roleId) {
        if (roleId < 0) {
            return null;
        }

        return roleDao == null ? null : roleDao.getRoleByRoleId(roleId);
    }

    @Override
    public boolean editRole(Role role) {
        return role == null ? false : roleDao.editRole(role);
    }

    @Override
    public boolean addRole(Role role) {
        return role == null ? false : roleDao.addRole(role);
    }

    @Override
    public boolean activateRole(Role role) {
        return role == null ? false : roleDao.activateRole(role);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        Role role = null;
        int roleId = 0;   
        int roleDescription = 0;
        Boolean isActive = false;
        List<Role> roleList = null;

        String action = request.getParameter("act");
        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {
                case "addrole":

                    roleId = Integer.parseInt(request.getParameter("roleId"));
                    roleDescription = Integer.parseInt("roleDescription");
                    
                    if (roleId != 0 && roleDescription != 0);
                     {
                        if (addRole(new Role(roleId, roleDescription))) {
                            request.setAttribute("ans", "roleId added.");
                        } else {
                            request.setAttribute("ans", "role not added");
                        }
                    }
                    break;

                case "activaterole":

                    roleId = Integer.parseInt(request.getParameter("roleId"));
                    roleDescription = Integer.parseInt("roleDescription");
                    isActive = Boolean.parseBoolean("isActive");

                    if (roleId != 0) {
                        try {
                            roleId = Integer.parseInt(request.getParameter("roleId"));
                        } catch (NumberFormatException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                        if (roleId != 0 && roleDescription > 0) {
                            if (activateRole(new Role(roleId, roleDescription, isActive))) {
                                request.setAttribute("ans", "Role activated");
                            } else {
                                request.setAttribute("ans", "Role not activated");
                            }
                        }
                    }
                    break;

                case "editrole":

                    roleId = Integer.parseInt(request.getParameter("roleId"));
                    roleDescription = Integer.parseInt("roleDescription");
                    isActive = Boolean.parseBoolean("isActive");

                    if (roleId != 0) {
                        try {
                            roleId = Integer.parseInt(request.getParameter("roleId"));
                        } catch (NumberFormatException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                        if (roleId != 0 && roleDescription > 0) {
                            if (editRole(new Role(roleId, roleDescription, isActive))) {
                                request.setAttribute("ans", "Role Updated");
                            } else {
                                request.setAttribute("ans", "Role not Updated");
                            }
                        }
                    }
                    break;

                case "getallrole":

                    roleList = roleDao.getAllRoles();

                    if (roleList != null && !roleList.isEmpty()) {
                        request.setAttribute("getRolesRoleId", roleList);
                    } else {
                        request.setAttribute("getRolesRoleId", "no role in this description");
                    }
                    roleView = "viewRoles.jsp";

                    break;

                case "getrolebydescription":

                    roleDescription = Integer.parseInt(request.getParameter("roleDescription"));
                    isActive = Boolean.parseBoolean(request.getParameter("isActive"));
                    role = roleDao.getRoleByDescription(roleDescription, isActive);

                    if (roleList != null && !roleList.isEmpty()) {

                        request.setAttribute("rolebydescription", role);
                    } else {
                        request.setAttribute("ans", "no roles yet");
                    }
                    roleView = "viewRoles.jsp";
                    break;

                case "getrolebyroleid":
                    roleId = Integer.parseInt(request.getParameter("roleId"));
                    roleList = (List<Role>) roleDao.getRoleByRoleId(roleId);

                    if (roleList != null) {
                        request.setAttribute("address", roleList);
                    } else {
                        request.setAttribute("ans", "no role found");
                    }
                    roleView = "viewRoles.jsp";

            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response
    ) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(roleView);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }
}
