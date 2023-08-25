package za.co.bakery.service;

import java.util.List;
import za.co.bakery.model.User;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserByUsername(String username);

    public boolean userLogin(String username, String password, int role);

    public boolean addAUser(User user);

    public boolean editUser(User user);

    public boolean activateUser(User user);
}
