/**I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 * This is a program which takes a user on a tour of a campus.
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
 * @version 1.0
 */
public class TourUMW {

    public static void main(String[] args) {
        promptUser();
    }

    /**
     * This method takes user input to move around a campus using a user selected filename which is turned into a
     * formatted campus object.
     */
    public static void promptUser() {
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
        invalid = true;
        boolean invalidOption = true;
        boolean continueTour = true;
        String direction = null;
        System.out.println(campus.getStartingLocation().getDescription());
        while (continueTour) {
            invalid = true;
            invalidOption = true;
            System.out.println(tourStatus.getCurrentLocation().getDoors());
            System.out.println("Where would you like to go next or quit? (n, s, e, w,) or (q)?:  ");
            while (invalidOption) {
                direction = stdin.next();
                if (direction.equals("n") || direction.equals("s") || direction.equals("e") || direction.equals("w")
                        || direction.equals("q")) {
                    invalidOption = false;
                } else {
                    System.out.print("Invalid option, enter a valid option or quit (n, s, e, w) or (q):  ");
                }
            }
            if (direction.equals("q")) {
                continueTour = false;
                invalid = false;
            }
            while (invalid) {
                Location moveChecker = tourStatus.getCurrentLocation();
                tourStatus.updateTourLocation(direction);
                if (moveChecker == tourStatus.getCurrentLocation()) {
                    System.out.println("That direction doesn't lead anywhere, enter another direction" +
                            " (n, s, e, w) or (q):  ");
                    direction = stdin.next();
                } else {
                    System.out.println("You are now at " + tourStatus.getCurrentLocation().getName());
                    if (tourStatus.getCurrentLocation().haveVisited() == false) {
                        System.out.println(tourStatus.getCurrentLocation().getDescription());
                    }
                    invalid = false;
                }
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

