/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

import java.util.*;

/**
 * This class is used to create and manipulate Location objects
 * @author Mohammad Daud Salimi
 * @version 2.0
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
    private int numItems = 0;

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
                describe = (describe+"\n"+currentLine);
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
        int num = 0;
        String allDoors = "";
        String anotherDoor;
        for(i=numDoors; i>0; i--){
            num++;
            if(count==numDoors-1){
                anotherDoor = num + ".  "+(doors.get(count).describe());
            }
            else {
                anotherDoor = num + ".  " + (doors.get(count).describe())+"\n";
            }
            count++;
            allDoors = allDoors+ "   "+anotherDoor;
        }
        return allDoors;
    }

    /**
     * Returns the combined String of all item objects and their messages
     * @return String, all item objects and their messages
     */
    public String getItems() {
        String allItems;
        if (numItems > 0) {
            Set<String> names = items.keySet();
            allItems = "";
            for (String key : names) {
                allItems = "   "+allItems + (items.get(key).getName() + ": " + items.get(key).getMessage() + "\n");
            }
        }
        else{
            allItems = "   No items at this location";
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

    /**
     * adds an item object to the items hashtable
     * @param item an item object
     */
    public void addItem(Item item){
        items.put(item.getName(),item);
        numItems++;
    }

    /**
     * returns an item object from the items hashtable by the item's name
     * @param name String the name of the item
     * @return Item the item by the name, (name)
     */
    public Item getItem(String name){
        return items.get(name);
    }

    /**
     * removes an item from the items hashtable by the item's name
     * @param item the item by the name, (name)
     */
    public void removeItem(String item){
        items.remove(item);
        numItems--;
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
     * @return boolean, if the location has been visited or not
     */
    public boolean haveVisited(){
        return haveVisited;
    }

    /**
     * returns the number of items currently in the items hashtable
     * @return int number of items in items hashtable
     */
    public int getNumItems(){
        return numItems;
    }
}
