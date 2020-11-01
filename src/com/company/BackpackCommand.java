/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

/**
 * This class handles the user input b or backpack
 * @author Mohammad Daud Salimi
 * @version 1.0
 */
public class BackpackCommand implements UserInputCommand{

    @Override
    /**
     * Returns a string concatenation of all backpack items in the TourStatus object
     * @return String, the concatenation of all backpack items
     */
    public String carryOut(TourStatus ts) {
        String output;
        output = ts.listBackpackItems();
        return output;
    }
}
