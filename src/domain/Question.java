package domain;

public class Question {

    private int id;
    private String text;
    private String correctAnswer;
    private Integer score;
    private String userAnswer;

    public Question(int id, String text, String correctAnswer, int score, String userAnswer) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.score = score;
        this.userAnswer = userAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
               // ", correctAnswer='" + correctAnswer + '\'' +
                ", score=" + score +
               // ", userAnswer='" + userAnswer + '\'' +
                '}';
    }
}
