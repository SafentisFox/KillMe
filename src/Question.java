import java.util.ArrayList;

/**
 * The <code>Question</code> class is meant to represents the quiz's questions.
 *
 * A question is comprised of it's <code>body</code>, it's <code>correctAnswer</code> and the list of it's <code>incorrectAnswers</code>
 */
public class Question {

    private String body;
    private String correctAnswer;
    private ArrayList<String> incorrectAnswers;

    /**
     * @param body  Is a string containing the question
     * @param correctAnswer  Is a string containing the correct answer of question
     * @param incorrectAnswers Is a ArrayList containing strings of all the incorrect answers of question
     */
    public Question(String body, String correctAnswer, ArrayList<String> incorrectAnswers){
        this.body=body;
        this.correctAnswer=correctAnswer;
        this.incorrectAnswers = new ArrayList<>(incorrectAnswers);
    }

    /**
     * Copy constructor
     * @param question The object to be copied
     */
    public Question(Question question) {
        body = question.getBody();
        correctAnswer = question.getCorrectAnswer();
        incorrectAnswers = new ArrayList<>(question.getIncorrectAnswers());
    }

    /**
     * @return The question
     */
    public String getBody(){
        return body;
    }

    /**
     * @return The correct answer
     */
    public String getCorrectAnswer(){
        return correctAnswer;
    }

    /**
     * @return The incorrect answers
     */
    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
