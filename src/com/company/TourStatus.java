/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

import java.util.Hashtable;
import java.util.Set;

/**
 * This class is used to create and manipulate TourStatus objects.
 * @author Mohammad Daud Salimi
 * @version 1.0
 */
public class TourStatus {
    private Campus campus;
    private Location currentLocation;
    private Hashtable<String,Item> backpack;

    /**
     * Creates a new TourStatus object with null values
     */
    public TourStatus (){
        campus = null;
        currentLocation = null;
    }

    /**
     * take a campus object to set the campus to be kept track of
     * @param c the campus object to be set
     */
    public void setCampus(Campus c){
        campus = c;
        currentLocation = campus.getStartingLocation();
        backpack = new Hashtable<String, Item>(1);
    }

    /**
     * returns the campus object
     * @return Campus, campus object
     */
    public Campus getCampus(){
        return campus;
    }

    /**
     * sets the location the user is currently in during the tour, after a move
     * @param location current user location in tour
     */
    public void setCurrentLocation(Location location){
        currentLocation = location;
    }

    /**
     * returns the current location of the user in the tour
     * @return Location, the current location
     */
    public Location getCurrentLocation(){
        return currentLocation;
    }

    /**
     * checks if the direction the user wants to travel to has a door to a location, if not, the current location is
     * not updated.
     * @param dir the direction the user wishes to travel
     */
    public void updateTourLocation(String dir){
        Location possibleLeave = campus.getLocation(currentLocation.getName()).leaveLocation(dir);
        if(currentLocation==possibleLeave){
        }
        else{
            currentLocation = possibleLeave;
        }
    }
    public void addToBackpack(Item item){
        backpack.put(item.getName(),item);
        currentLocation.removeItem(item.getName());
    }
    public void removeFromBackpack(Item item){
        backpack.remove(item.getName());
        currentLocation.addItem(item);
    }
    public String listBackpackItems(){
        Set<String> names = backpack.keySet();
        String allItems = "";
        for (String key : names) {
            allItems = allItems+(backpack.get(key).getName() + ": " + backpack.get(key).getMessage()+"\n");
        }
        return allItems;
    }
    }

