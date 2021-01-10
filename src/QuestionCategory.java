import java.util.ArrayList;

/**
 * This class represents a category of Questions
 */
public class QuestionCategory {
    private String name;
    private ArrayList<Question> questions;

    /**
     * Constructor
     * @param name The name of the category
     * @param questions An arraylist containing the questions
     */
    public QuestionCategory(String name, ArrayList<Question> questions) {
        this.name = name;
        this.questions = new ArrayList<>();
        this.questions = questions;
    }

    /**
     * Copy Constructor
     * @param temp The Category to copy
     */
    public QuestionCategory(QuestionCategory temp) {
        name = temp.getName();
        questions = new ArrayList<>(temp.getQuestions());
    }

    /**
     * Returns the name of the category.
     * @return The name of the Category
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an ArrayList of the Questions
     * @return An ArrayList of the Questions
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Returns a Question from the given index
     * @param index The index of the Question to be returned
     * @return The question located in the index given
     */
    public Question getQuestion(int index) { return questions.get(index); }

    /**
     * Removes a question from the Category
     * @param index The index of the Question to be removed
     * @return The size of the category after the removal.
     */
    public int removeQuestion(int index) {
        int temp = questions.size();
        questions.remove(index);
        return temp;
    }
}
