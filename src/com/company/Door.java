/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

import java.util.Scanner;

/**
 * This class is used to create and manipulate Door objects.
 *
 * @author Mohammad Daud Salimi
 * @version 2.0
 */
public class Door {
    private String direction;
    private Location leavingLocation;
    private Location enteringLocation;

    /**
     * constructor for a new Door object
     *
     * @param dir   direction which leads to door
     * @param leave location which is left from
     * @param enter Location which is entered to
     */
    public Door(String dir, Location leave, Location enter) {
        direction = dir;
        leavingLocation = leave;
        enteringLocation = enter;
    }

    public Door(Scanner s, Campus c) {

    }

    /**
     * returns a description of what location the door leads to in a direction
     *
     * @return String, description of direction and arrived at location
     */
    public String describe() {
        String description;
        description = "Head " + direction + " to get to " + enteringLocation.getName();
        return description;

    }

    /**
     * returns the location which is left by using this door
     *
     * @return Location, exited location
     */
    public Location getLeaving() {
        return leavingLocation;
    }

    /**
     * sets the location which is exited by using this door
     *
     * @param leave, exited location
     */
    public void setLeaving(Location leave) {
        leavingLocation = leave;
    }

    /**
     * returns the location which is entered by using this door
     *
     * @return Location, arrived at location
     */
    public Location getEntering() {
        return enteringLocation;
    }

    /**
     * sets the location which is exited by using this door
     *
     * @param enter, exited location
     */
    public void setEntering(Location enter) {
        enteringLocation = enter;
    }

    /**
     * returns the direction of the door
     *
     * @return direction by which the door is travelled
     */
    public String getDirection() {
        return direction;
    }

    /**
     * sets the direction of the door
     *
     * @param dir direction by which the door is travelled
     */
    public void setDirection(String dir) {
        direction = dir;
    }

}
