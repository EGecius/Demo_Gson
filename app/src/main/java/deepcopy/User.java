package deepcopy;

import android.annotation.SuppressLint;

import java.util.concurrent.ConcurrentHashMap;

public class User {

    private String firstName;
    private String lastName;

    @SuppressLint("UseSparseArrays")
    private final ConcurrentHashMap<Integer, Address> addressesHashMap = new ConcurrentHashMap<>();

    public User(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        addressesHashMap.put(0, address);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return addressesHashMap.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null)
            return false;
        return addressesHashMap != null ? addressesHashMap.equals(user.addressesHashMap)
                : user.addressesHashMap == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (addressesHashMap != null ? addressesHashMap.hashCode() : 0);
        return result;
    }

    // standard constructors, getters and setters
}
