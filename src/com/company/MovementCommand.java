/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

/**
 * This class is responsible for updating a tourstatus object when the user selects the option to move
 * to a new location on the campus.  It also returns the current location's contents.
 * @author Mohammad Daud Salimi
 * @version 1.0
 */
public class MovementCommand implements UserInputCommand{
    private String direction;

    /**
     * Constructor for MovementCommand, sets a direction
     * @param dir direction user wishes to travel
     */
    public MovementCommand(String dir){
        direction = dir;
    }

    /**
     * Updates a TourStatus object by moving the user in their requested direction,
     * or keeping them in the same place if it isnt a valid direction.  Also, returns the contents of the
     * new location such as items and directions.
     * @param ts TourStatus object subject to changes
     * @return String, the contents of the new location
     */
    public String carryOut(TourStatus ts){
        Location old = ts.getCurrentLocation();
        ts.updateTourLocation(direction);
        Location neW = ts.getCurrentLocation();
        String output = null;
        boolean done = false;
        if(old.equals(neW)){
            output = "That doesn't lead anywhere";
            done = true;
        }
        if(!(done)) {
            if ((ts.getCurrentLocation().haveVisited()) && (!(old.equals(neW)))) {
                output = (ts.getCurrentLocation().getName()) + "\n"+
                        "  Directions:" + "\n" + (ts.getCurrentLocation().getDoors() + "\n") +
                        "  Items:" + "\n" + (ts.getCurrentLocation().getItems() + "\n");
            } else {
                output = (ts.getCurrentLocation().getName()) +
                        (ts.getCurrentLocation().getDescription() + "\n") +
                        "  Directions:" + "\n" + (ts.getCurrentLocation().getDoors() + "\n") +
                        "  Items:" + "\n" + (ts.getCurrentLocation().getItems() + "\n");
            }
        }
        return output;
    }
}
