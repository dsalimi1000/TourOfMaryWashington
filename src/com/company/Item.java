/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Item {
    private String name;
    private String startingLocation;
    private String message;
    private String currentLineBack;

    public Item(){
        name = null;
        message = null;
    }
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
    public String getCurrentLineBack(){
        return currentLineBack;
    }
    public String getStartingLocation(){
        return startingLocation;
    }
    public String getName(){
        return name;
    }
    public String getMessage(){
        return message;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
