/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

/**
 * This class is responsible for updating a TourStatus object's backpack items when they are dropped by
 * a user.  Also, returns a string describing that the item has been dropped in the user's current location
 * @author Mohammad Daud Salimi
 * @version 1.0
 */
public class DropCommand implements UserInputCommand{
    private String itemName;

    /**
     * Constructor for DropCommand objects, sets the item name
     * @param item name of item
     */
    public DropCommand(String item){
        itemName = item;
    }

    @Override
    /**
     * Removes the item from the user's backpack in the TourStatus object and adds it to the location object's
     * items hashtable.  Returns a String which describes that the item has been dropped
     * in the user's current location
     * @return String, description that the item has been dropped in the user's current location
     */
    public String carryOut(TourStatus ts) {
        String output;
        output = ("The "+(ts.getItemFromBackpack(itemName).getName()+
                (" has been dropped in "+ts.getCurrentLocation().getName())));
        ts.removeFromBackpack(ts.getItemFromBackpack(itemName));
        return output;
    }
}
