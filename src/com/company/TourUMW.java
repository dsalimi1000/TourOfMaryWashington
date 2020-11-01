/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 * This is a program which takes a user on a tour of a campus.
 *
 * @author Mohammad Daud Salimi
 * @version 1.0
 */
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is a program which takes a user on a tour of a campus.
 * @author Mohammad Daud Salimi
 * @version 2.0
 */
public class TourUMW {

    public static void main(String[] args) throws InterruptedException {
        promptUser();
    }

    /**
     * This method takes user input to move around a campus,
     * as well as interact with objects in the campus,
     * using a user selected filename which is turned into a
     * formatted campus object.
     */
    public static void promptUser() throws InterruptedException {
        Scanner stdin = new Scanner(System.in);
        boolean invalid = true;
        Scanner s = null;
        String filename = null;
        System.out.println("Hello and welcome to Canvas Tour.");
        System.out.println("To get Started, begin by entering a valid text file");
        System.out.println();
        while (invalid) {
            System.out.print("Enter the file desired:  ");
            filename = stdin.next();
            try {
                File umwFile = new File(filename);
                s = new Scanner(umwFile);
                invalid = false;
            } catch (FileNotFoundException e) {
                System.out.println("Invalid file try another");
            }
        }
        Campus campus = setUpCampus(s, filename);
        TourStatus tourStatus = new TourStatus();
        tourStatus.setCampus(campus);
        boolean invalidOption;
        boolean continueTour = true;
        stdin = new Scanner(System.in);
        String input = null;
        String greeting = null;
        boolean first = true;
        boolean welcome = false;
        Thread thread = new Thread();
        UserInputCommand user = new MovementCommand("n");
        while (continueTour) {
            invalidOption = true;
            if (first == true) {
                if (!(tourStatus.getCurrentLocation().haveVisited())) {
                    System.out.println((tourStatus.getCurrentLocation().getDescription()) + "\n");
                    Thread.sleep(3000);
                    System.out.println("You are currently at " + tourStatus.getCurrentLocation().getName() + "\n");
                    Thread.sleep(2000);
                    System.out.println("   Directions:" + "\n" + tourStatus.getCurrentLocation().getDoors() + "\n" +
                            "   Items:" + "\n" + tourStatus.getCurrentLocation().getItems());
                }
            }
            if (welcome) {
                System.out.print(greeting);
            }
            while (invalidOption) {
                System.out.println("Select:" + "\n" + "m:  Move" + "\n" + "p:  Pickup Item" + "\n" + "d:  Drop Item" +
                        "\n" + "b:  Backpack" + "\n" + "q:  Quit");
                input = stdin.nextLine();
                if (!(input.equals("m") || (input.equals("p") || (input.equals("d") || (input.equals("b") ||
                        input.equals("q")||(input.equals("move")||(input.equals("pickup")||
                        (input.equals("drop")||(input.equals("backpack")||(input.equals("quit")||
                                (input.equals("n")||(input.equals("s")||input.equals("e")||
                                        (input.equals("w")))))))))))))) {
                    user = new InvalidCommand(input);
                    System.out.println(user.carryOut(tourStatus));
                } else {
                    invalidOption = false;
                }
            }
            if (input.equals("m")||input.equals("n")||(input.equals("s")||(input.equals("e")||
                    input.equals("w")))) {
                invalidOption = true;
                if(input.equals("m")){
                while (invalidOption) {
                    System.out.println("Select a direction (n s e w):" + "\n" + tourStatus.getCurrentLocation().getDoors());
                    input = stdin.nextLine();
                    if (!(input.equals("n") || (input.equals("s") || (input.equals("e") || (input.equals("w")))))) {
                        user = new InvalidCommand(input);
                        System.out.println(user.carryOut(tourStatus));
                    } else {
                        invalidOption = false;
                    }
                }
                }
                user = new MovementCommand(input);
                greeting = user.carryOut(tourStatus);
                first = false;
                welcome = true;
                if(greeting.equals("That doesn't lead anywhere")){
                    System.out.println(greeting);
                    first = welcome = false;
                }
            }
            if (input.equals("p")||(input.equals("pickup"))) {
                invalidOption = true;
                boolean empty = false;
                String currentItems = tourStatus.getCurrentLocation().getItems();
                if (currentItems.equals("   No items at this location")) {
                    System.out.println(currentItems);
                    invalidOption = false;
                    empty = true;
                }
                while (invalidOption) {
                    System.out.println(tourStatus.getCurrentLocation().getItems() + "\n" +
                            "Enter the exact name of an Item to pick up:  ");
                    input = stdin.nextLine();
                    Item m = tourStatus.getCurrentLocation().getItem(input);
                    if (m == null) {
                        user = new InvalidCommand(input);
                        System.out.println(user.carryOut(tourStatus));
                    }
                    if (!(m == null)) {
                        invalidOption = false;
                    }
                }
                if (!(empty)) {
                    user = new PickupCommand(input);
                    System.out.println(user.carryOut(tourStatus));
                }
                welcome = false;
            }
            if (input.equals("d")||(input.equals("drop"))) {
                invalidOption = true;
                boolean empty = false;
                if (tourStatus.getNumBackpackItems() == 0) {
                    empty = true;
                    invalidOption = false;
                    System.out.println("Your backpack is empty");
                }
                while (invalidOption) {
                    System.out.println(tourStatus.listBackpackItems() + "\n" +
                            "Enter the exact name of an Item to drop:  ");
                    input = stdin.nextLine();
                    Item m = tourStatus.getItemFromBackpack(input);
                    if (m == null) {
                        user = new InvalidCommand(input);
                        System.out.println(user.carryOut(tourStatus));
                    }
                    if (!(m == null)) {
                        invalidOption = false;
                    }
                }
                if (!(empty)) {
                    user = new DropCommand(input);
                    System.out.println(user.carryOut(tourStatus));
                }
                welcome = false;
            }
            if (input.equals("b")||(input.equals("backpack"))) {
                if (tourStatus.getNumBackpackItems() > 0) {
                    user = new BackpackCommand();
                    System.out.print(user.carryOut(tourStatus));
                } else {
                    System.out.println("Your backpack is empty");
                }
                welcome = false;
            }
            if (input.equals("q")||(input.equals("quit"))) {
                continueTour = false;
            }
        }
    }

    /**
     * This method takes creates a Campus object from a specifically formatted text file.
     *
     * @param s        a scanner object attached to the user selected file
     * @param filename the name of the file
     * @return returns one campus object
     */
    public static Campus setUpCampus(Scanner s, String filename) {

        Campus campus = new Campus(filename);
        String campusName = s.nextLine();
        campus.setCampusName(campusName);
        s.nextLine();
        boolean keepOn = true;
        String currentLine = s.nextLine();
        if (currentLine.equals("Locations:")) {
            Location location = new Location(s);
            campus.addLocation(location);
            campus.setStartingLocation(location);
            while (keepOn) {
                if (location.getCurrentLineBack().equals("*****")) {
                    keepOn = false;
                } else {
                    location = new Location(s);
                    campus.addLocation(location);
                }
            }
        }
        keepOn = true;
        String leave;
        String direction;
        String enter;
        if (s.nextLine().equals("Doors:")) {
            while (keepOn) {
                currentLine = s.nextLine();
                if (currentLine.equals("+++")) {
                    currentLine = s.nextLine();
                }
                if (currentLine.equals("*****")) {
                    keepOn = false;
                }
                if (keepOn) {
                    leave = currentLine.trim();
                    currentLine = s.nextLine();
                    direction = currentLine.trim();
                    currentLine = s.nextLine();
                    enter = currentLine.trim();
                    Door door = new Door(direction, campus.getLocation(leave), campus.getLocation(enter));
                    campus.getLocation(leave).addDoor(door);
                }
            }
        }
        currentLine = s.nextLine();
        if (currentLine.equals("Items:")) {
            Item item = new Item(s);
            campus.getLocation(item.getStartingLocation()).addItem(item);
            keepOn = true;
            while (keepOn) {
                currentLine = item.getCurrentLineBack();
                if (currentLine.equals("*****")) {
                    keepOn = false;
                }
                if (keepOn == true) {
                    item = new Item(s);
                    currentLine = item.getCurrentLineBack();
                    if (currentLine.equals("*****")) {
                    } else {
                        campus.getLocation(item.getStartingLocation()).addItem(item);
                    }
                }
            }
        }
        return campus;
    }
}

