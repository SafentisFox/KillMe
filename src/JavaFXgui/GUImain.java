package JavaFXgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GUImain extends Application {

    private boolean roundDone;
    private GameData gameData;
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        roundDone = false;
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
        Parent root = loader.load();
        //System.out.println(gameData.isSetupDone());
        primaryStage.setTitle("Buzz Game");
        primaryStage.setUserData(gameData);
        System.out.println(gameData.isSetupDone());
        primaryStage.setScene(new Scene(root, 950, 600));
        primaryStage.show();
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public boolean isRoundDone() {
        return roundDone;
    }

    public void test(GameData gd) throws Exception {
        this.gameData = gd;
        System.out.println(gd.isSetupDone());
        //launch();
        start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ArrayList<Integer> correctAnswerRound(ArrayList<String> info) {
        ArrayList<Integer> results = new ArrayList<>();
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("CorrectAnswerRound.fxml")));
            Parent root = loader.load();
            GuiRoundController roundController = loader.getController();
            stage.setScene(new Scene(root));
            results = roundController.correctRound(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
