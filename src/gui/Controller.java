package gui;

import domain.Question;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import service.Service;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private ListView<Question> listView;
    @FXML
    private Button filterQuestionsButton;

    @FXML
    private TextField scoreTextField;

    @FXML
    private TextField textTextField;
    @FXML
    private TextArea answerTextArea;
    @FXML
    private ComboBox<Question> idCBox;
    @FXML
    private Button hintButton;

    @FXML
    private TextField hintTextField;

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @FXML
    void answer(ActionEvent event) {
        String answer= answerTextArea.getText();
        Question question= idCBox.getValue();
        if(answer.equals(question.getCorrectAnswer())){
            score+=question.getScore();
        }

        service.updateAnswer(question,answer);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Your score is: " + score);
        alert.show();
    }
    @FXML
    void filterByTextAndScore(ActionEvent event) {
       String inputText= textTextField.getText();
       Integer inputScore= Integer.parseInt(scoreTextField.getText());
       listView.setItems(FXCollections.observableArrayList(this.service.getQuestionsByWordsAndMinimumNbOfPoints(inputText,inputScore)));
       idCBox.setItems(FXCollections.observableArrayList(this.service.getQuestionsByWordsAndMinimumNbOfPoints(inputText,inputScore)));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listView.setItems(FXCollections.observableArrayList(this.service.getUnansweredQuestionsSortedByScore()));
        idCBox.setItems(FXCollections.observableArrayList(this.service.getUnansweredQuestionsSortedByScore()));
    }
    @FXML
    void hint(ActionEvent event) {

        hintTextField.setText(this.service.hintAnswer(idCBox.getValue()));
    }

}
