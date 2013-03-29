package server;

import java.util.*;

/**
 * Vote.class
 * 
 * Represents a single issue to be voted on.
 * 
 * @author Sam Beed B0632953
 */

public class Vote {
    
    String question;
    Map<String, Integer> answers;
    
    public Vote(String newQuestion) {
        question = newQuestion;
        answers = new HashMap<String, Integer>();
        
        //Test Data
        addAnswer("Option A");
        addAnswer("Option B");
        addAnswer("Option C");
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
    
}
