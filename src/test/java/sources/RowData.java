package sources;

public class RowData {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String companyName;
    private String role;
    private String phoneNumber;

    public RowData(String firstName, String lastName, String companyName, String role, String address, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.companyName = companyName;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
