package uz.b25.testing_system.server.entity;

import java.time.LocalDateTime;

public class TestHistory {
    private static Long counter = 0L;
    {
        counter++;
        id = counter;
    }
    private final Long id;
    private User user;
    private String subject;
    private int quantity;
    private int score;
    private LocalDateTime createdAt = LocalDateTime.now();

    public TestHistory() {
    }

    public TestHistory(User user, String subject, int quantity, int score) {
        this.user = user;
        this.subject= subject;
        this.quantity = quantity;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "TestHistory{" +
                "user=" + user +
                ", subject='" + subject + '\'' +
                ", quantity=" + quantity +
                ", score=" + score +
                ", createdAt=" + createdAt +
                '}';
    }
}
