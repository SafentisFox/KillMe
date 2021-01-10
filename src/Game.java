import JavaFXgui.GameData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
/**
 * This class starts the game, defines the players and their data, selects the type of question randomly
 * and closes the game by displaying the results
 */
public class Game {
    /**
     * numofRounds contains the number of rounds
     * numOfQuestion contains the number of question per round
     * r1 contains a random number to choose the type of question
     * ui user interface
     */
    private int numOfRounds;
    private int numOfQuestions;
    private RoundManager rounds;
    private ArrayList<Player> players;
    private int twoPlayers;
    private String[] results2Players;
    private String resultIndividualGame;
    private Random r1;
    private UI ui;
    private GUIlogic guiLogic;
    private GameData gameData;


    /**
     * @param numOfRounds    Is an int number contains the number of rounds of the game
     * @param numOfQuestions Is an int number contains the number of questions per round
     */
    /*public Game(int numOfRounds, int numOfQuestions) {
        this.numOfRounds = numOfRounds;
        this.numOfQuestions = numOfQuestions;
        rounds = new RoundManager();
        r1 = new Random();
        ui = new UI();
        //players = ui.playerSetUp();
        if (players.size() == 2) {
            twoPlayers = 0; // Exists two players, can play all the questions modes where exists to the game
        } else {
            twoPlayers = 2; // Not exists two players to remove the questions modes where need only two players
        }
        results2Players = new String[2];
    }
     */

    public Game() {
        //ui = new UI();
        //ui.welcome();
        //numOfRounds = ui.numberOfRounds();
        //numOfQuestions = ui.numberOfQuestions();
        //players = ui.playerSetUp();
        gameData = new GameData(new ArrayList<>(), false, new ArrayList<>());
        guiLogic = new GUIlogic(gameData);
        rounds = new RoundManager(guiLogic);
        r1 = new Random();

        BooleanProperty setupDone = gameData.setupDoneProperty();
        setupDone.addListener(e -> {

            ArrayList<Integer> gameDataList = gameData.getList();
            ArrayList<String> playerNameList = gameData.getNameList();
            numOfRounds = gameDataList.get(1);
            numOfQuestions = gameDataList.get(2);
            players = new ArrayList<>();


            if (gameDataList.get(0) == 2) {
                twoPlayers = 0; // Exists two players, can play all the questions modes where exists to the game
                players.add(new Player(1000, playerNameList.get(0)));
                players.add(new Player(1000, playerNameList.get(1)));
            } else {
                twoPlayers = 2; // Not exists two players to remove the questions modes where need only two players
                players.add(new Player(1000, playerNameList.get(0)));
            }
            results2Players = new String[2];
            System.out.println("Ready to play");
            start();
        });
        guiLogic.startGame();

    }

    private void loadFromFile() {
        //File for 2 players
        try (BufferedReader reader = new BufferedReader(new FileReader("results2Players.txt"))) {
            results2Players = reader.readLine().split("/"); // read data
        } catch (IOException e) { // File not exists, there is no data
            results2Players[0] = "0";
            results2Players[1] = "0";
        }
        //File for individual game
        try (BufferedReader reader = new BufferedReader(new FileReader("resultsIndividualGame.txt"))) {
            resultIndividualGame = reader.readLine(); // read data
        } catch (IOException e) { // File not exists, there is no data
            resultIndividualGame = "N/A";
        }
    }

    /**
     * This method starts the game and manages its mode by randomly selecting one of the types.
     * Finally show the player's results
     */
    public void start() {
        loadFromFile();
        //GUI needed to take the data from file (String[] results2Players,String resultIndividualGame)

        int i = 0, randomKindQuestion;
        boolean existsQuestions = true;
        BooleanProperty roundDone = new SimpleBooleanProperty(false);

        while (i < numOfRounds && existsQuestions) { // Rounds of game
            //randomKindQuestion = r1.nextInt(rounds.getQuantityOfUniqueRounds() - twoPlayers); // Random number
            randomKindQuestion = 0;
            if (randomKindQuestion == 0) { // Correct Answers
                existsQuestions = rounds.correctAnswer(numOfQuestions, players);
            }
            if (randomKindQuestion == 1) { // Betting
                existsQuestions = rounds.betting(numOfQuestions, players);
            }
            if (randomKindQuestion == 2) { // Stop the chronometer
                existsQuestions = rounds.stopTheChronometer(numOfQuestions, players);
            }
            // Only 2 players can play this game modes
            if (randomKindQuestion == 3) { // Fast answer
                existsQuestions = rounds.fastAnswer(numOfQuestions, players);
            }
            if (randomKindQuestion == 4) { // Thermometer
                existsQuestions = rounds.thermometer(numOfQuestions, players);
            }
            i += 1;
        }
        //ui.printPlayerResults(players); // show player's result
        if (players.size() < 3) {
            saveToFile();
        }
    }

    private void saveToFile() {
        //File for 2 players
        if (players.size() == 2) {
            try(BufferedWriter writer=new BufferedWriter(new FileWriter("results2Players.txt"))){
                if(players.get(0).getPoints()>players.get(1).getPoints()){ // Player 1 wins
                    results2Players[0]=""+Integer.parseInt(results2Players[0])+1;
                }
                else{
                    if(players.get(1).getPoints()>players.get(0).getPoints()){ // Player 2 wins
                        results2Players[1]=""+Integer.parseInt(results2Players[1])+1;
                    }
                    else{ // Is Draw
                        results2Players[0]=""+Integer.parseInt(results2Players[0])+1;
                        results2Players[1]=""+Integer.parseInt(results2Players[1])+1;
                    }
                }
                writer.write(results2Players[0]+"/"+results2Players[1]);
            }
            catch(IOException e){
                // Needed GUI a warning window where will inform the user that cannot save the data
            }
        }
        //File for individual game
        else{
            try(BufferedWriter writer=new BufferedWriter(new FileWriter("resultsIndividualGame.txt"))){
                if(resultIndividualGame.equals("N/A")){ // First Game
                    writer.write(""+players.get(0).getPoints());
                }
                else{ // Exists data
                    if(Integer.parseInt(resultIndividualGame)<players.get(0).getPoints()){
                        writer.write(""+players.get(0).getPoints());
                    }
                }
            }
            catch(IOException e){
                // Needed GUI a warning window where will inform the user that cannot save the data
            }
        }
    }
}
