package za.co.bakery.service.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.CategoryDao;
import za.co.bakery.dao.ItemDao;
import za.co.bakery.dao.UserDao;
import za.co.bakery.model.Category;
import za.co.bakery.model.Item;
import za.co.bakery.model.User;
import za.co.bakery.service.UserService;

public class UserServiceImpl implements UserService, ProcessRequest {

    private UserDao userDao;
    private ItemDao itemDao;
    private CategoryDao categoryDao;

    private String userView;

    public UserServiceImpl(UserDao userDao, ItemDao itemDao, CategoryDao categoryDao) {
        this.userDao = userDao;
        this.itemDao = itemDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao == null ? null : userDao.getAllUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }

        return userDao == null ? null : userDao.getUserByUsername(username);
    }

    @Override
    public boolean userLogin(String username, String password, int role) {
        return userDao == null ? null : userDao.userLogin(username, password, role);
    }

    @Override
    public boolean addAUser(User user) {
        return user == null ? false : userDao.addAUser(user);
    }

    @Override
    public boolean editUser(User user) {
        return user == null ? false : userDao.editUser(user);
    }

    @Override
    public boolean activateUser(User user) {
        return user == null ? false : userDao.activateUser(user);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        String password = "";
        String name = "";
        String surname = "";
        String contactNumber = "";
        String username = "";
        Boolean isActive = false;

        int role = 0;
        User user = null;
        List<User> userList = null;
        List<Item> itemList = null;
        List<Category> categoryList = null;

        String action = request.getParameter("act");
        System.out.println(action);

        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {
                case "register":
                    System.out.println("inside");
                    password = request.getParameter("password");
                    name = request.getParameter("name");
                    surname = request.getParameter("surname");
                    contactNumber = request.getParameter("contactNumber");
                    username = request.getParameter("username");

                    if (username != null && !username.trim().isEmpty()) {
                        username = username.trim();
                        //validate
                    }
                    try {
                        role = Integer.parseInt(request.getParameter("role"));
                    } catch (NumberFormatException nfe) {
                    }

                    if (!username.isEmpty() && role > 0) {
                        if (addAUser(new User(role, username, password, name, surname, contactNumber, username))) {
                            request.setAttribute("ans", "The user was added successfully.");
                        } else {
                            request.setAttribute("ans", "The user was not added.");
                        }
                    }

                    userView = "addAddress.jsp";

                    break;
                case "login":
                    username = request.getParameter("username");
                    password = request.getParameter("password");
                    role = Integer.parseInt(request.getParameter("role"));

                    if ((username != null && !username.isEmpty()) && (password != null) && !password.isEmpty()) {
                        username = username.trim();
                        password = password.trim();
                        System.out.println("yes");

                        if (userLogin(username, password, role)) {
                            System.out.println("logged in");
                            itemList = null;
                            categoryList = categoryDao.getAllCategories();
                            categoryList = categoryDao.getAllActiveCategories();
                            if (categoryList != null && !categoryList.isEmpty()) {
                                request.setAttribute("allcategories", categoryList);
                            } else {
                                request.setAttribute("ans", "No categories found");
                            }

                            itemList = itemDao.getAllActiveItems();
                            if (itemList != null && !itemList.isEmpty()) {
                                request.setAttribute("allItems", itemList);
                            } else {
                                request.setAttribute("ans", "No items ");
                            }

                            if (role == 1) {
                                userView = "admin.jsp";
                            } else {
                                userView = "homePage.jsp";
                            }

                        } else {
                            System.out.println("nope");
                            userView = "login.jsp";
                        }
                    }
                    break;
                case "viewuser":

                    userList = userDao.getAllUsers();
                    if (userList != null && !userList.isEmpty()) {

                        request.setAttribute("allUsers", userList);

                    } else {
                        request.setAttribute("ans", "No users, Yet!");
                    }
                    userView = "userView.jsp";
                    break;
                case "userbyusername":
                    username = request.getParameter("username");
                    user = userDao.getUserByUsername(username);

                    if (user != null) {
                        request.setAttribute("user", user);
                    } else {
                        request.setAttribute("ans", "user not found");
                    }

                    userView = "editUser.jsp";
                    break;
                case "edit":
                    username = request.getParameter("username");
                    password = request.getParameter("password");
                    name = request.getParameter("name");
                    surname = request.getParameter("surname");
                    String email = request.getParameter("email");
                    contactNumber = request.getParameter("contactNumber");

                    System.out.println("info");
                    if (username != null && !username.isEmpty()) {
                        System.out.println(username);
                        if (editUser(new User(username, password, name, surname, contactNumber, email))) {
                            System.out.println("done");
                            request.setAttribute("ans", "User updated.");
                        } else {
                            System.out.println("fail");
                            request.setAttribute("ans", "User not updated.");
                        }

                    }

                    userList = userDao.getAllUsers();
                    if (userList != null && !userList.isEmpty()) {

                        request.setAttribute("allUsers", userList);

                    } else {
                        request.setAttribute("ans", "No users, Yet!");
                    }
                    userView = "userView.jsp";
                    break;

                case "activate":
                    username = request.getParameter("username");
                    isActive = Boolean.parseBoolean(request.getParameter("isActive"));

                    if (!username.isEmpty()) {
                        if (activateUser(new User(username, isActive))) {
                            request.setAttribute("ans", "User activated.");
                        } else {
                            request.setAttribute("ans", "User not activated.");
                        }
                    }

                    userList = userDao.getAllUsers();
                    if (userList != null && !userList.isEmpty()) {

                        request.setAttribute("allUsers", userList);

                    } else {
                        request.setAttribute("ans", "No users, Yet!");
                    }

                    userView = "userView.jsp";
                    break;
            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(userView);

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }

}
