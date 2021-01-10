import JavaFXgui.GUImain;
import JavaFXgui.GameData;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GUIlogic {

    private GUImain guiMain;


    private ArrayList<Player> playerArrayList;
    private int playerCount;
    private int roundCount;
    private int questionCount;
    private GameData gameData;

    public GUIlogic(GameData gameData) {

        guiMain = new GUImain();

        playerArrayList = new ArrayList<>();
        playerCount = 0;
        roundCount = 0;
        questionCount = 0;

        this.gameData = gameData;
        guiMain.setGameData(gameData);
    }

    public void startGame() {
        guiMain.setGameData(gameData);
        Platform.startup(() -> {

            Stage stage = new Stage();
            try {
                guiMain.start(stage);
            } catch (Exception ex) {ex.printStackTrace();}
        });
    }

    public ArrayList<Boolean> correctAnswerRound(Question question, ArrayList<String> answers) {
        ArrayList<String> info = new ArrayList<>();
        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<Boolean> finalResults = new ArrayList<>();
        info.add(question.getBody());
        info.addAll(answers);
        results = guiMain.correctAnswerRound(info);
        for (int i :
                results) {
            if (i == 0) {
                finalResults.add(false);
            } else {
                if (question.getCorrectAnswer().equals(answers.get(i))) {
                    finalResults.add(true);
                } else {
                    finalResults.add(false);
                }
            }
        }
        return finalResults;
    }


   //public Integer[] getGameData(String[] playerNames) {

    //}



}
