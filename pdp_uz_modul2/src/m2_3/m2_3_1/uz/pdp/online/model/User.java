package m2_3.m2_3_1.uz.pdp.online.model;

public class User {
    private long id;
    public String userName;
    public String email;
    private String password;
    private String name;
    private String address;

    public User() {
    }

    public User(String userName, String email, String password, String name, String address) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }
    public boolean changePassword(String newPassword){
        this.password=newPassword;
        return true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
