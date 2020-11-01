/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

/**
 * This class handles incorrect input from the user
 * @author Mohammad Daud Salimi
 * @version 1.0
 */
public class InvalidCommand implements UserInputCommand{
    private String invalidCommand;

    /**
     * Constructor for InvalidCommand objects, sets the String of the invalid command
     * @param invalid the incorrect input which the user inputted
     */
    public InvalidCommand(String invalid){
        invalidCommand = invalid;
    }

    @Override
    /**
     * Returns a string stating that the input was wrong and prompting to try again
     * @return String, prompts user to try again, stating the input was invalid
     */
    public String carryOut(TourStatus ts) {
        String output = "********INVALID INPUT********PLEASE TRY AGAIN********";
        return output;
    }
}
