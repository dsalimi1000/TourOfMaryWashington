/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

/**
 * This class is responsible for updating a TourStatus object's backpack items when they are picked up by
 * a user.  Also, returns a string describing the item and its message.
 * @author Mohammad Daud Salimi
 * @version 1.0
 */
public class PickupCommand implements UserInputCommand{
    private String itemName;

    /**
     * Constructor for PickupCommand objects, sets the name of the item to be picked up
     * @param item item to be picked up
     */
    public PickupCommand(String item){
        itemName = item;
    }

    @Override
    /**
     * Adds the item to the user's backpack and removes it from the current location in TourStatus.
     * The TourStatus object is updated and a string is returned describing which item was picked up
     * and it's message.
     * @return String, description of which item was picked up and it's message.
     */
    public String carryOut(TourStatus ts) {
        String output;
        output = "You Picked up "+(ts.getCurrentLocation().getItem(itemName).getName())+
                (":  "+(ts.getCurrentLocation().getItem(itemName).getMessage()));
        ts.addToBackpack(ts.getCurrentLocation().getItem(itemName));
        return output;
    }
}
