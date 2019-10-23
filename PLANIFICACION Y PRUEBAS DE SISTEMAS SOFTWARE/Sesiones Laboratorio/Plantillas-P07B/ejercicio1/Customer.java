package ppss;

public class Customer {
    private int _id;
    private String _firstName;
    private String _lastName;
    private String _street;
    private String _city;
    
    public Customer(int id, String firstName, String lastName) {
        _id = id;
        _firstName = firstName;
        _lastName = lastName;
    }
    
    public int getId() {
        return _id;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public String getStreet() {
        return _street;
    }

    public String getCity() {
        return _city;
    }

    public void setStreet(String street) {
        _street = street;
    }

    public void setCity(String city) {
        _city = city;
    }
    
}
