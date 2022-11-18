package uz.b25.testing_system.server.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Question {
    private static Long counter=0L;
    {
        counter++;
        id=counter;
    }
    private Long id;
    private String subject;
    private String text;
    private String correctAnswer;
    private List<String> variants;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Question(String text, String correctAnswer, List<String> variants) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.variants = variants;
    }

    public Question(Long id,String  subject, String text,
                    String correctAnswer, List<String> variants) {
        this.id = id;
        this.subject= subject;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.variants = variants;
    }

    public Long getId() {
        return id;
    }

    public String  getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getVariants() {
        return variants;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", subjectId=" + subject +
                ", text='" + text + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", variants=" + variants +
                ", createdAt=" + createdAt +
                '}';
    }
}
