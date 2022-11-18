package uz.b25.testing_system.server.entity;

import uz.b25.testing_system.server.enums.Role;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String password;
    private double balance = 0d;
    private Role role = Role.USER;
    private LocalDateTime createdAt = LocalDateTime.now();

    public User(Long id, String fullName, String phoneNumber, String password) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }
}
