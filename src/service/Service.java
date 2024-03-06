package service;

import domain.Question;
import repository.Repository;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Question> getUnansweredQuestionsSortedByScore(){
        return this.repo.getQuestions().stream()
               //.filter(  question -> question.getUserAnswer() == null)
                .sorted(Comparator.comparing(Question::getScore))
        .collect(Collectors.toList());
    }

    public List<Question> getQuestionsByWordsAndMinimumNbOfPoints(String text, Integer score){
        return this.repo.getQuestions().stream()
                .filter( question -> question.getText().contains(text) )
                .filter(question -> question.getScore()>score)
                .collect(Collectors.toList());
    }

    public void updateAnswer(Question question, String answer){
        question.setUserAnswer(answer);
        try {
            this.repo.update(question);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String hintAnswer(Question question){
        String hint= question.getCorrectAnswer().substring(0,2);
        question.setScore(question.getScore()/2);
        return hint;
    }

}
