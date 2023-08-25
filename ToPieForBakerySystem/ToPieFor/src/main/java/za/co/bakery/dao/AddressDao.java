package za.co.bakery.dao;

import java.util.List;
import za.co.bakery.model.Address;

public interface AddressDao {

    public List<Address> getAllAddressesByUsername(String username, boolean active);

    public Address getAddressByAddresId(int addressId);

    public boolean addAddress(Address address);
    
    public boolean addToUserAddress();

    public boolean editAddress(Address address);

    public boolean activateAddress(Address address);
}
