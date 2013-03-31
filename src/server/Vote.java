package server;

import java.io.Serializable;
import java.util.*;

/**
 * Vote.class
 * 
 * Represents a single issue to be voted on.
 * 
 * @author Sam Beed B0632953
 */

public class Vote implements Serializable {
    
    String question;
    Map<String, Integer> answers;
    
    public Vote(String newQuestion) {
        question = newQuestion;
        answers = new HashMap<String, Integer>();
    }
    
    /**
     * Returns the question.
     * @return 
     */
    public String getQuestion() {
        return question;
    }
    
    /**
     * Returns the answers.
     */
    public Map<String, Integer> getAnswers() {
        return answers;
    }
    
    /**
     * Adds a new answer.
     * @param newAnswer 
     */
    public void addAnswer(String newAnswer) {
        answers.put(newAnswer, 0);
    }
    
    /**
     * Increments an answer.
     * @param answer 
     */
    public void voteOnAnswer(String currentVote) {
        answers.put(currentVote, answers.get(currentVote) + 1);
    }
    
    /**
     * To String.
     * @return 
     */
    @Override
    public String toString() {
        String output = question + "," + answers.toString();
        
        return output;
    }
    
}
