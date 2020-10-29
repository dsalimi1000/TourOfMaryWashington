/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

import java.util.*;

/**
 * This class is used to create and manipulate Location objects
 * @author Mohammad Daud Salimi
 * @version 1.0
 */
public class Location {
    private String name;
    private String description;
    private Boolean haveVisited;
    private Boolean isOutside;
    private ArrayList<Door> doors;
    private Hashtable<String,Item> items;
    private String currentLineBack;
    private int numDoors;

    public Location(String namE){
        name = namE;
    }

    /**
     * creates a location object by scanning the file scanned by s scanner.
     * @param s , scanner which is read
     */
    public Location(Scanner s){
        String currentLine = s.nextLine();
        boolean keepOn = true;
        if(currentLine.equals("*****")){
            keepOn = false;
            name = "pointer";
            description = "pointer";
        }
        else{
            name = currentLine.trim();
        }
        String describe = "";
        while(keepOn){
            currentLine = s.nextLine();
            if(currentLine.equals("+++")||(currentLine.equals("*****"))){
                keepOn = false;
            }
            else {
                describe = (describe+currentLine);
            }
        }
        description = describe;
        haveVisited = false;
        currentLineBack = currentLine;
        doors = new ArrayList<Door>(1);
        items = new Hashtable<String,Item>(1);
        numDoors = 0;
    }
    /**
     * returns the name of the location
     * @return name, String, name of location
     */
    public String getName(){
        return name;
    }

    /**
     * sets the name of the location
     * @param n name of location
     */
    public void setName(String n){
        name = n;
    }

    /**
     * returns the description of the location
     * @return String, description
     */
    public String getDescription(){
        haveVisited = true;
        return description;
    }

    /**
     * sets the description of the location
     * @param d description
     */
    public void setDescription(String d){
        description = d;
    }

    /**
     * Returns the combined String of all door object's direction descriptions at the location
     * @return String, all descriptions of door directions
     */
    public String getDoors(){
        int i;
        int count = 0;
        String allDoors = "";
        String anotherDoor;
        for(i=numDoors; i>0; i--){
            anotherDoor = (doors.get(count).describe());
            count++;
            allDoors = allDoors+ "   "+count + ".  " +anotherDoor+ "\n";
        }
        return allDoors;
    }
    public String getItems() {
        Set<String> names = items.keySet();
        String allItems = "";
        for (String key : names) {
            allItems = allItems+(items.get(key).getName() + ": " + items.get(key).getMessage()+"\n");
        }
        return allItems;
    }

    /**
     * checks if the user selected direction has a valid door which to pass through.
     * If so, that location is returned, if not, the current location is returned.
     * @param dir user desired direction
     * @return Location, the location to be travelled to.
     */
    public Location leaveLocation(String dir) {
        ArrayList<Location> possibleDoors = new ArrayList<Location>(1);
        Random ranX = new Random();
        int count = 0;
        int options = 0;
        int i;
        Location decidedLocation = doors.get(0).getLeaving();
        for (i = numDoors; i > 0; i--) {
            if (doors.get(count).getDirection().equals(dir)) {
                possibleDoors.add(doors.get(count).getEntering());
                options++;
            }
            count++;
        }
        if (options == 1) {
            decidedLocation = possibleDoors.get(0);
        }
        if (options > 1) {
            decidedLocation = possibleDoors.get(ranX.nextInt(options-1));
        }
        return decidedLocation;
    }

    /**
     * adds a door object to the doors ArrayList
     * @param door a door object
     */
    public void addDoor(Door door){
        doors.add(door);
        numDoors++;
    }
    public void addItem(Item item){
        items.put(item.getName(),item);
    }
    public Item getItem(String name){
        return items.get(name);
    }
    public void removeItem(String item){
        items.remove(item);
    }

    /**
     * returns the current line being read by the scanner s
     * @return current line of file being read by scanner s
     */
    public String getCurrentLineBack(){
        return currentLineBack;
    }

    /**
     * returns the true false statement, haveVisited
     * @return boolean, if the location has been visited or not.
     */
    public boolean haveVisited(){
        return haveVisited;
    }
}
