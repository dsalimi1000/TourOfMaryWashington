/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used for creating and manipulating item objects
 * @author Mohammad Daud Salimi
 * @version 2.0
 */
public class Item {
    private String name;
    private String startingLocation;
    private String message;
    private String currentLineBack;

    /**
     * Constructor for item objects, creates objects out of Strings from inputted file
     * @param s Scanner of the file used to create item objects.
     */
    public Item(Scanner s){
        String currentLine = s.nextLine();
        boolean keepOn = true;
        if(currentLine.equals("*****")){
            keepOn = false;
            name = "pointer";
            message = "pointer";
        }
        else{
            name = currentLine.trim();
        }
        if(keepOn) {
            currentLine = s.nextLine();
            startingLocation = currentLine.trim();
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
        message = describe;
        currentLineBack = currentLine;

    }

    /**
     * returns the current line being scanned
     * @return String, current line being scanned
     */
    public String getCurrentLineBack(){
        return currentLineBack;
    }

    /**
     * returns the name of starting location of the item
     * @return  String, name of starting location of the item
     */
    public String getStartingLocation(){
        return startingLocation;
    }

    /**
     * returns the name of the item
     * @return String, the name of the item
     */
    public String getName(){
        return name;
    }

    /**
     * returns the message of the item
     * @return String, the message of the item
     */
    public String getMessage(){
        return message;
    }

    /**
     * sets the name of the item to a new name
     * @param name new name of item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the message of the item to a new message
     * @param message new message of item
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
