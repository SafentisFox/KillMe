import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;

/**
 * This class manages the rounds of the game
 */
public class RoundManager {
    /**
     * allAnswers containing the answers to the question
     * quantityOfUniqueRounds is the number of unique rounds
     * ui user interface
     */
    private QuestionManager questionManager;
    private QuestionCategory category;
    private Question question;
    private ArrayList<String> allAnswers;
    private ArrayList<Boolean> resultsFromPlayers;
    private int quantityOfUniqueRounds;
    private UI ui;
    private GUIlogic guiLogic;

    public RoundManager(GUIlogic gl) {
        questionManager = new QuestionManager();
        quantityOfUniqueRounds = 5;
        resultsFromPlayers = new ArrayList<>();
        guiLogic = gl;
        //ui = new UI();
    }

    /**
     * @return the number of different types of questions that the class has
     */
    public int getQuantityOfUniqueRounds() {
        return quantityOfUniqueRounds;
    }

    /**
     * This method manages how the correct answer works and accordingly affects the player's points
     *
     * @param numOfQuestions Is an integer that indicates how many different questions
     *                       will be played in the round
     * @param player         Is a Player object that contains the player of the specific round
     */
    public boolean correctAnswer(int numOfQuestions, ArrayList<Player> players) {
        category = questionManager.getRandomCategory(); // selects a random category
        if (category == null) {
            //ui.outOfCategories();
            return false; // Out of questions
        }
        int i=0;
        boolean existsQuestions=true;
        while(i<numOfQuestions && existsQuestions) {
            question = questionManager.getRandomQuestion(category); // select a random question
            if (question == null) {
                //ui.outOfQuestions();
                existsQuestions=false; // Out of questions in this category
            } else {
                //ui.questionsKind(0);
                //ui.outputNameAndScore(players); // output all players name and score
            }
            allAnswers = questionManager.getRandomAnswers(question); // select randoms answers of the question

            BooleanProperty roundDone = new SimpleBooleanProperty(false);

            resultsFromPlayers = guiLogic.correctAnswerRound(question, allAnswers);

            int cnt = 0;
            for (Player player : players) {
                if (resultsFromPlayers.get(cnt)) { // check if tha question was answered correctly
                    player.winPoints(1000); // points are added to the player
                } else {
                    player.lossPoints(1000); // point are removed from the player
                }
                cnt += 1;
            }
            //ui.afterAnswer(resultsFromPlayers, question, 1000);
            i+=1;
        }
        return true;
    }

    /**
     * This method manages how the betting works and accordingly affects the player's points
     *
     * @param numOfQuestion Is an int number that indicates how many different questions
     *                      will be played in this round
     * @param player        Is a Player object that contains the player of the specific round
     */
    public boolean betting(int numOfQuestion, ArrayList<Player> players) {
        category = questionManager.getRandomCategory(); // selects a random category
        if (category == null) {
            //ui.outOfCategories();
            return false; // Out of questions
        }
        int bet;
        int i=0;
        boolean existsQuestions=true;
        while(i<numOfQuestion && existsQuestions) {
            question = questionManager.getRandomQuestion(category); // selects a random question
            if (question == null) {
                //ui.outOfQuestions();
                existsQuestions=false; // Out of questions in this category
            } else {
                ui.questionsKind(1);
                //ui.outputNameAndScore(players); // output player's name and score
            }
            allAnswers = questionManager.getRandomAnswers(question); // select randoms answers of the question
            bet = ui.inputOutputBetting(category); // Output for betting
            //resultsFromPlayers = ui.inputOutputCorrectAnswer(question, allAnswers);
            int cnt = 0;
            for (Player player : players) {
                if (resultsFromPlayers.get(cnt)) { // check if tha question was answered correctly
                    player.winPoints(1000); // points are added to the player
                } else {
                    player.lossPoints(1000); // point are removed from the player
                }
                cnt += 1;
            }
            i+=1;
        }
        return true;
    }

    public boolean stopTheChronometer(int numOfQuestion, ArrayList<Player> players){
        category = questionManager.getRandomCategory(); // selects a random category
        if (category == null) {
            //ui.outOfCategories();
            return false; //Out of questions
        }
        int i=0;
        boolean existsQuestions=true;
        ArrayList<Double> TimeResultsFromPlayers=new ArrayList<>();
        while(i<numOfQuestion && existsQuestions) {
            question = questionManager.getRandomQuestion(category); // selects a random question
            if (question == null) {
                //ui.outOfQuestions();
                existsQuestions=false; // Out of questions in this category
            }
            else {
                ui.questionsKind(1);
                //ui.outputNameAndScore(players); // output player's name and score
                }
            //TimeResultsFromPlayers = ui.inputOutputChronometer(question, allAnswers);
                // returns the time for each player
                // in the order finalized at the beginning of the game
                // if the answer was wrong then loss the time replacing with 0, no adding points

            int cnt = 0;
            for (Player player : players) {
                //player+=TimeResultsFromPlayers.get(cnt);
                cnt += 1;
            }
            //ui.afterAnswer(resultsFromPlayers, question, 1000);
            i+=1;
            }
        return true;
    }

    // Only 2 players can play this game mode
    public boolean fastAnswer(int numOfQuestion, ArrayList<Player> players){
        category = questionManager.getRandomCategory(); // selects a random category
        if (category == null) {
            //ui.outOfCategories();
            return false; // Out of questions
        }
        int i=0;
        boolean existsQuestions=true;
        while(i<numOfQuestion && existsQuestions){
            question = questionManager.getRandomQuestion(category); // selects a random question
            if (question == null) {
                //ui.outOfQuestions();
                existsQuestions=false; // Out of questions in this category
            } else {
                ui.questionsKind(1);
                //ui.outputNameAndScore(players); // output player's name and score
            }
            //resultsFromPlayers = ui.inputOutputFastAnswer(question, allAnswers);
            //returns only two values
            //first true or false if one of two players answer correct
            //second true or false if the answer is from first player

            if(resultsFromPlayers.get(0)){ // One of two players answer correct
                if(resultsFromPlayers.get(1)){ // player one answer correct
                    players.get(0).winPoints(1000);
                    players.get(1).winPoints(500);

                }
                else{ // player two answer correct
                    players.get(0).winPoints(500);
                    players.get(1).winPoints(1000);
                }
            }
            i+=1;
        }
        return true;
    }
    // Only 2 players can play this game mode
    public boolean thermometer (int numOfQuestion, ArrayList<Player> players){
        category = questionManager.getRandomCategory(); // selects a random category
        if (category == null) {
            //ui.outOfCategories();
            return false; // Out of questions
        }

        int i=0;
        boolean existsQuestions=true;
        while(i<numOfQuestion && existsQuestions) {
            int[] playersResults={0,0}; // results is how many questions each player has found
            while ((playersResults[0] != 5 || playersResults[1] != 5) && existsQuestions) {
                question = questionManager.getRandomQuestion(category); // selects a random question
                if (question == null) {
                    //ui.outOfQuestions();
                    existsQuestions=false; // Out of questions in this category
                } else {
                    ui.questionsKind(1);
                    //ui.outputNameAndScore(players); // output player's name and score
                }
                //resultsFromPlayers = ui.inputOutputCorrectAnswer(question, allAnswers);
                int cnt = 0;
                for (Boolean answer : resultsFromPlayers) {
                    if(answer){
                        playersResults[cnt]+=1;
                    }
                    cnt += 1;
                }

                //ui.afterAnswer(resultsFromPlayers, question, 0);
            }
            for(i=0;i<players.size();i++) {
                if (playersResults[i] == 5) {
                    players.get(i).winPoints(5000); // points are added to the player
                }
            }
            i+=1;
        }
        return true;
    }

}
