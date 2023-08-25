package za.co.bakery.service;

import java.util.List;
import za.co.bakery.model.Address;

public interface AddressService {

    public List<Address> getAllAddressesByUsername(String username, boolean active);

    public Address getAddressByAddresId(int addressId);

    public boolean addAddress(Address address);

    public boolean editAddress(Address address);

    public boolean activateAddress(Address address);
}
