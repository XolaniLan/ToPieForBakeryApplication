package za.co.bakery.model;

import java.util.Objects;

public class User {

    private int roleId;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String contactNumber;
    private String email;
    private boolean isActive;

    public User() {
    }

    public User(int roleId, String username, String password, String name, String surname, String contactNumber, String email, boolean isActive) {
        this.roleId = roleId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.email = email;
        this.isActive = isActive;
    }

    public User(int roleId, String username, String password, String name, String surname, String contactNumber, String email) {
        this.roleId = roleId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public User(String password, String name, String surname, String contactNumber, String email) {
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username,boolean isActive) {
        this.username = username;
        this.isActive = isActive;
    }

    public User(String username, String password, String name, String surname, String contactNumber, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public User(int role, String username, String password, String name, String surname, String contactNumber, String email, Boolean active) {
        this.roleId = roleId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.email = email;
        this.isActive = isActive;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash = 17 * hash + this.roleId;
        hash = 17 * hash + Objects.hashCode(this.username);
        hash = 17 * hash + Objects.hashCode(this.password);
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.surname);
        hash = 17 * hash + Objects.hashCode(this.contactNumber);
        hash = 17 * hash + Objects.hashCode(this.email);
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
        final User other = (User) obj;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.contactNumber, other.contactNumber)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + ", roleId=" + roleId + ", username=" + username + ", password=" + password + ", name=" + name + ", surname=" + surname + ", contactNumber=" + contactNumber + ", email=" + email + '}';
    }

}
