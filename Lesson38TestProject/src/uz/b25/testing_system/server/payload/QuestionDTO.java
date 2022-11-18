package uz.b25.testing_system.server.payload;

public class QuestionDTO {
    private String categoryName;
    private String text;
    private String correctAnswer;
    private String wrongVariants;

    public QuestionDTO(String categoryName, String text,
                       String correctAnswer, String wrongVariants) {
        this.categoryName = categoryName;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.wrongVariants = wrongVariants;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongVariants() {
        return wrongVariants;
    }

    public void setWrongVariants(String wrongVariants) {
        this.wrongVariants = wrongVariants;
    }
}
