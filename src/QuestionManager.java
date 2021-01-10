import jdk.jfr.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is used to organise the different questions of the quiz.
 * It also supplies the rest of the project with methods that return random Categories, Questions and their answers in a randomized format.
 */

public class QuestionManager {

    private ArrayList<QuestionCategory> categories = new ArrayList<>();
    private final File input = new File("questionInputFile.txt");
    /**
     * The constructor of the class.
     * It reads the "questionInputFile.txt" and puts the questions it reads into the appropriate arraylists.
     */
    public QuestionManager() {
        try {
            Scanner fileScanner = new Scanner(input);
            while (fileScanner.hasNextLine()) {
                String tempS = fileScanner.nextLine();

                if (tempS.startsWith("C:")){
                    ArrayList<Question> tempList = new ArrayList<>();
                    String tempCategoryName = new String(tempS.substring(2));

                    Question tempQuestion=null;
                    String tempQuestionName=null;
                    String tempQuestionCorrectAnswer=null;
                    ArrayList<String> tempQuestionWrongAnswerList=null;

                    while (!tempS.startsWith("EndC")){
                        tempS = fileScanner.nextLine().trim();
                        if (tempS.startsWith("Q:") || tempS.startsWith("EndC")) {
                            if (tempQuestionName != null) {
                                tempList.add(new Question(tempQuestionName, tempQuestionCorrectAnswer, tempQuestionWrongAnswerList));
                            }
                            tempQuestionName = new String(tempS.substring(2));
                            tempQuestionWrongAnswerList = new ArrayList<>();
                        }
                        else if (tempS.startsWith("A:")) {
                            tempQuestionCorrectAnswer = new String(tempS.substring(2));
                        }
                        else if (tempS.startsWith("IA:")) {
                            tempQuestionWrongAnswerList.add(new String(tempS.substring(3)));
                        }
                    }
                    QuestionCategory tempCategory = new QuestionCategory(tempCategoryName, tempList);
                    categories.add(new QuestionCategory(tempCategory));
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Question input file not found.");
            e.printStackTrace();
        }
    }

    /**
     * Returns a random Category
     * @return a random Category
     */
    public QuestionCategory getRandomCategory() {
        if (categories.size() > 0) {
            int randomInt = ThreadLocalRandom.current().nextInt(categories.size());
            return categories.get(randomInt);
        } else {
            return null;
        }

    }

    /**
     * Returns a random Question of the given category.
     * @param cat The category from which the random questions is chosen
     * @return A random Question from the given category.
     */
    public Question getRandomQuestion(QuestionCategory cat) {
        if (cat.getQuestions().size() == 0)
            return null;
        int randomInt = ThreadLocalRandom.current().nextInt(cat.getQuestions().size());
        Question temp = new Question(cat.getQuestion(randomInt));
        if (removeQuestion(cat, randomInt))
            return null;
        return temp;
    }

    private boolean removeQuestion(QuestionCategory category, int index) {
        if (category.removeQuestion(index)==0){
            removeCategory(category);
            return true;
        }
        return false;
    }

    private void removeCategory(QuestionCategory category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals(category)) {
                categories.remove(i);
            }
        }
    }

    /**
     * Returns an ArrayList of Strings containing the possible answers of the question, randomized.
     * @param question The question we need the answers to.
     * @return An ArrayList of Strings containing the randomized answers.
     */
    public ArrayList<String> getRandomAnswers(Question question){
        ArrayList<String> tempIncAnswers = new ArrayList<>(question.getIncorrectAnswers());
        ArrayList<String> answers = new ArrayList<>();
        int correctAnswerInt = ThreadLocalRandom.current().nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == correctAnswerInt) {
                answers.add(question.getCorrectAnswer());
            } else {
                int temp = ThreadLocalRandom.current().nextInt(tempIncAnswers.size());
                answers.add(tempIncAnswers.get(temp));
                tempIncAnswers.remove(temp);
            }
        }
        return answers;
    }
}
