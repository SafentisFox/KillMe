import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the user interface needed for the game to communicate with the user
 */
public class UI {
    private Scanner aScanner;

    public UI(){
        aScanner = new Scanner(System.in);
    }

    private void clearBuffer(){ //Should only be used after aScanner is called for non-Strings.
        if (aScanner.hasNextLine()) {
            aScanner.nextLine();
        }
    }

    /**
     * This method displays what is needed for the type of question correct answer
     * @param question is a Question object contains the question
     * @param answers is a ArrayList Object contains the answers of question
     * @return boolean value contains if the answer was correct or wrong
     */
    public boolean inputOutputCorrectAnswer(Question question, ArrayList<String> answers) {
        System.out.println(question.getBody());
        System.out.println("");//enter
        System.out.println("1. "+answers.get(0)+"\t"+"2. "+answers.get(1)+"\n"+"3. "+answers.get(2)+"\t"+"4. "+answers.get(3));
        //String answer=aScanner.nextLine();
        boolean flag = true;
        Boolean correct = null;
        while (flag){
            if (aScanner.hasNextInt()) {
                int answer = aScanner.nextInt();
                clearBuffer();
                if (!(answer > 0 && answer < 5)) {
                    System.out.println("Please give a valid answer.");
                } else {
                    flag = false;
                    correct = question.getCorrectAnswer().equals(answers.get(answer - 1));
                }
            } else {
                String answer = aScanner.nextLine();
                if (!answers.contains(answer)) {
                    System.out.println("Please give a valid answer.");
                } else {
                    flag = false;
                    correct = question.getCorrectAnswer().equals(answer);
                }
            }
        }
        return correct;
    }

    /**
     * This method shows if we found the correct answer and the points we earned or
     * the wrong answer and how many points we lost and shows us what the correct answer was
     * @param correct
     * @param question
     * @param points
     */
    public void afterAnswer(boolean correct, Question question, int points){
        if (correct) {
            System.out.println("CORRECT! You just won " + points + " points!");
        } else {
            System.out.println("Incorrect. The answer was "+ question.getCorrectAnswer() + ". " + "You lost " + points + " points.");
        }
    }

    /**
     * This method displays what is needed for the type of question bets
     * @param category is a QuestionCategory to print what category we play
     * @return an int number that contains the amount bet
     */
    public int inputOutputBetting(QuestionCategory category){
        System.out.println("The category is: "+category.getName());
        System.out.println("You may bet: [250, 500, 750, 1000]");
        System.out.println("Your bet: ");
        int bet=aScanner.nextInt();
        clearBuffer();
        while((bet!=250 && bet!=500) && (bet!=750 && bet!=1000)){
            System.out.println("Please pick one of the predetermined amounts: [250, 500, 750, 1000]");
            bet=aScanner.nextInt();
            clearBuffer();
        }
        return bet;
    }

    /**
     * This method welcomes us to the game
     */
    public void welcome(){
        System.out.println("Welcome, let's play Buzz!");
    }

    /**
     * This method print the name and score of player
     * @param player is a Player object
     */
    public void outputNameAndScore(Player player){
        System.out.println("Scores:\n"+player.getName()+" - ("+player.getPoints() + ")");
    }

    /**
     * This method print the final results of player
     * @param player is a Player object
     */
    public void printPlayerResults(Player player){
        System.out.println("Final Results!");
        System.out.println();//enter
        System.out.println(player.getName()+" - (" +player.getPoints() + ")");
    }

    /**
     * This method print the kind of question being played
     * @param select is an inr number that selects what kind of question is played to print it
     */
    public void questionsKind(int select){
        if(select==0){
            System.out.println("Round Type: Correct Answer!");
        }
        else{
            if(select==1){
                System.out.println("Round Type: Betting!");
            }
        }
    }

    /**
     * This method asks the user how many round are played
     * @return an int number contains how many round are played
     */
    public int numberOfRounds(){
        System.out.println("Please chose the number of rounds: ");
        int i = aScanner.nextInt();
        clearBuffer();
        return i;
    }

    /**
     * This method asks the user how many questions per round are played
     * @return an int number contains how many questions per round are played
     */
    public int numberOfQuestions(){
        System.out.println("Please chose the number of questions asked each round: ");
        int i = aScanner.nextInt();
        clearBuffer();
        return i;
    }

    /**
     * This method create a player with name it got from the user
     * @return an object Player
     */
    public Player playerSetUp() {
        System.out.println("Please chose the name: ");
        return new Player(1000,aScanner.nextLine());
    }

    /*public void outOfQuestions() {
        System.out.println("This category is out of questions!");
    }

    public void outOfCategories() {
        System.out.println("We are out of categories!");
    }*/

}
