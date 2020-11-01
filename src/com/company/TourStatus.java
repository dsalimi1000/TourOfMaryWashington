/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

import java.util.Hashtable;
import java.util.Set;

/**
 * This class is used to create and manipulate TourStatus objects.
 *
 * @author Mohammad Daud Salimi
 * @version 2.0
 */
public class TourStatus {
    private Campus campus;
    private Location currentLocation;
    private Hashtable<String, Item> backpack;
    private int numBackpackItems;

    /**
     * Creates a new TourStatus object with null values
     */
    public TourStatus() {
        campus = null;
        currentLocation = null;
    }

    /**
     * take a campus object to set the campus to be kept track of
     *
     * @param c the campus object to be set
     */
    public void setCampus(Campus c) {
        campus = c;
        currentLocation = campus.getStartingLocation();
        backpack = new Hashtable<String, Item>(1);
    }

    /**
     * returns the campus object
     *
     * @return Campus, campus object
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * sets the location the user is currently in during the tour, after a move
     *
     * @param location current user location in tour
     */
    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }

    /**
     * returns the current location of the user in the tour
     *
     * @return Location, the current location
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * checks if the direction the user wants to travel to has a door to a location, if not, the current location is
     * not updated.
     *
     * @param dir the direction the user wishes to travel
     */
    public void updateTourLocation(String dir) {
        Location possibleLeave = campus.getLocation(currentLocation.getName()).leaveLocation(dir);
        if (currentLocation == possibleLeave) {
        } else {
            currentLocation = possibleLeave;
        }
    }

    /**
     * adds an item to the backpack hashtable and removes the same item from the current location
     * @param item item added to backpack and removed from current location
     */
    public void addToBackpack(Item item) {
        backpack.put(item.getName(), item);
        currentLocation.removeItem(item.getName());
        numBackpackItems++;
    }

    /**
     * removes an item from the backpack hashtable
     * @param item item removed from backpack hashtable
     */
    public void removeFromBackpack(Item item) {
        backpack.remove(item.getName());
        currentLocation.addItem(item);
        numBackpackItems--;
    }

    /**
     * returns the combined string of each item's name within the backpack hashtable
     * @return String, the combined string of each item's name in the backpack hashtable
     */
    public String listBackpackItems() {
        Set<String> names = backpack.keySet();
        String allItems = "";
        for (String key : names) {
            allItems = allItems + (backpack.get(key).getName() + "\n");
        }
        return allItems;
    }

    /**
     * returns an item from the backpack hashtable by it's String name.
     * @param name String name of the object
     * @return Item, the item by the name, (name)
     */
    public Item getItemFromBackpack(String name) {
        return backpack.get(name);
    }

    /**
     * returns the current number of item objects present in the backpack hashtable
     * @return int, number of item objects in the backpack hashtable
     */
    public int getNumBackpackItems() {
        return numBackpackItems;
    }
}

