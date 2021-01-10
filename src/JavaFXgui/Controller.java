package JavaFXgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.*;
import java.io.IOException;
import java.text.Format;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Button startButton, letsGoButton;

    @FXML
    private TextField playerCount;
    @FXML
    private TextField roundCount;
    @FXML
    private TextField questionCount;
    @FXML
    private TextField onePlayer,firstPlayer,secondPlayer;

    private GameData gameData;


    public void playButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) startButton.getScene().getWindow();
        gameData = (GameData) stage.getUserData();
        Parent root = FXMLLoader.load(getClass().getResource("GetInfoPage.fxml"));
        stage.setUserData(gameData);
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

    @FXML
    public void nextButtonAction(ActionEvent event) throws IOException {
        Alert wrongValuesAlert = new Alert(Alert.AlertType.WARNING);
        wrongValuesAlert.setTitle("Incorrect Input");
        wrongValuesAlert.setContentText("Please enter valid values!");

        Stage stage = (Stage) playerCount.getScene().getWindow();
        gameData = (GameData) stage.getUserData();

        try {
            int playerCountInt = Integer.parseInt(playerCount.getText());
            if (playerCountInt > 2 || playerCountInt < 1) throw new Exception("Out of bounds player count");
            int roundCountInt = Integer.parseInt(roundCount.getText());
            if (roundCountInt < 0) throw new Exception("Out of bounds round count");
            int questionCountInt = Integer.parseInt(questionCount.getText());
            if (questionCountInt < 0) throw new Exception("Out of bounds question count");

            System.out.println("test1");
            gameData.getList().add(playerCountInt);
            gameData.getList().add(roundCountInt);
            gameData.getList().add(questionCountInt);
            System.out.println("Test2");

            stage.setUserData(gameData);

            if (playerCountInt == 2) {
                System.out.println("test1");
                Parent root = FXMLLoader.load(getClass().getResource("TwoPlayersName.fxml"));
                System.out.println("test2");
                stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
                System.out.println("test3");
            } else {
                System.out.println("Test4");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("OnePlayerName.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
            }
        } catch (Exception exception) {
            //JOptionPane.showMessageDialog(null, "Please enter valid values!");
            exception.printStackTrace();
            wrongValuesAlert.show();
        }


    }

    public void letsGoButtonAction(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        gameData = (GameData) stage.getUserData();

        if (gameData.getList().get(0) == 2) {
            gameData.getNameList().add(firstPlayer.getText());
            gameData.getNameList().add(secondPlayer.getText());
        } else {
            gameData.getNameList().add(onePlayer.getText());
        }
        gameData.setupDoneProperty().set(true);
    }

    public boolean isSetupDone() {
        return gameData.isSetupDone();
    }
}
