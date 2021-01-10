package JavaFXgui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;


public class GuiRoundController {
    @FXML
    private Button ans1;
    @FXML
    private Button ans2;
    @FXML
    private Button ans3;
    @FXML
    private Button ans4;
    @FXML
    private Button enter;

    private int player1selection, player2selection;
    private boolean roundDone;

    @FXML
    private Label questionLabel;

    public ArrayList<Integer> correctRound(ArrayList<String> info) {
        player1selection = 0;
        player2selection = 0;
        roundDone = false;
        ArrayList<Integer> results = new ArrayList<>();

        questionLabel.setText(info.get(0));
        ans1.setText(info.get(1));
        ans2.setText(info.get(2));
        ans3.setText(info.get(3));
        ans4.setText(info.get(4));

        Stage stage = (Stage) questionLabel.getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case Q:setPlayerChoice(player1selection, 1, 1); player1selection = 1; break;
                    case W:setPlayerChoice(player1selection, 2, 1); player1selection = 2; break;
                    case E:setPlayerChoice(player1selection, 3, 1); player1selection = 3; break;
                    case R:setPlayerChoice(player1selection, 4, 1); player1selection = 4; break;
                    case U:setPlayerChoice(player2selection, 1, 2); player2selection = 1; break;
                    case I:setPlayerChoice(player2selection, 2, 2); player2selection = 2; break;
                    case O:setPlayerChoice(player2selection, 3, 2); player2selection = 3; break;
                    case P:setPlayerChoice(player2selection, 4, 2); player2selection = 4; break;
                }
            }
        });

        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        return results;
    }

    private void setPlayerChoice(int oldChoice, int newChoice, int player) {
        switch (oldChoice) {
            case 0: setPlayerChoice2(newChoice, player); break;
            case 1: String old = ans1.getText();
                old = old.replace(player == 1 ? " (P1)" : " (P2)", "");
                ans1.setText(old);
                setPlayerChoice2(newChoice, player);
                break;
            case 2: old = ans2.getText();
                old = old.replace(player == 1 ? " (P1)" : " (P2)", "");
                ans2.setText(old);
                setPlayerChoice2(newChoice, player);
                break;
            case 3: old = ans3.getText();
                old = old.replace(player == 1 ? " (P1)" : " (P2)", "");
                ans3.setText(old);
                setPlayerChoice2(newChoice, player);
                break;
            case 4: old = ans4.getText();
                old = old.replace(player == 1 ? " (P1)" : " (P2)", "");
                ans4.setText(old);
                setPlayerChoice2(newChoice, player);
                break;
        }
    }

    private void setPlayerChoice2(int newChoice, int player) {
        switch (newChoice) {
            case 0:break;
            case 1: String old = ans1.getText();
                old = old.concat(player == 1 ? " (P1)" : " (P2)");
                ans1.setText(old);
                break;
            case 2: old = ans2.getText();
                old = old.concat(player == 1 ? " (P1)" : " (P2)");
                ans2.setText(old);
                break;
            case 3: old = ans3.getText();
                old = old.concat(player == 1 ? " (P1)" : " (P2)");
                ans3.setText(old);
                break;
            case 4: old = ans4.getText();
                old = old.concat(player == 1 ? " (P1)" : " (P2)");
                ans4.setText(old);
                break;
        }
    }




}
