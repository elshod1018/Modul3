package uz.b25.testing_system.server.entity;

import java.time.LocalDateTime;

public class Subject {
    private static Long counter=0L;
    {
        counter++;
        id=counter;
    }
    private Long id;
    private String name;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Subject(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
