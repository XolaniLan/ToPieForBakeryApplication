package za.co.bakery.model;

import java.util.Objects;

public class Address {

    private int addressId;
    private String address1;
    private String address2;
    private String province;
    private String city;
    private String code;
    private boolean isActive;

    public Address() {
    }

    public Address(int addressId, String address1, String address2, String province, String city, String code, boolean isActive) {
        this.addressId = addressId;
        this.address1 = address1;
        this.address2 = address2;
        this.province = province;
        this.city = city;
        this.code = code;
        this.isActive = isActive;
    }

    public Address(String address1, String address2, String province, String city, String code) {
        this.address1 = address1;
        this.address2 = address2;
        this.province = province;
        this.city = city;
        this.code = code;
    }

    public Address(String address1, String address2, String province, String city, String code, boolean isActive) {
        this.address1 = address1;
        this.address2 = address2;
        this.province = province;
        this.city = city;
        this.code = code;
        this.isActive = isActive;
    }

    public Address(boolean isActive) {
        this.isActive = isActive;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressID) {
        this.addressId = addressId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.addressId;
        hash = 79 * hash + Objects.hashCode(this.address1);
        hash = 79 * hash + Objects.hashCode(this.address2);
        hash = 79 * hash + Objects.hashCode(this.province);
        hash = 79 * hash + Objects.hashCode(this.city);
        hash = 79 * hash + Objects.hashCode(this.code);
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
        final Address other = (Address) obj;
        if (this.addressId != other.addressId) {
            return false;
        }
        if (!Objects.equals(this.address1, other.address1)) {
            return false;
        }
        if (!Objects.equals(this.address2, other.address2)) {
            return false;
        }
        if (!Objects.equals(this.province, other.province)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "addressID=" + addressId + ", address1=" + address1 + ", address2=" + address2 + ", province=" + province + ", city=" + city + ", code=" + code + '}';
    }

}
