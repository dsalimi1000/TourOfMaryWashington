/**
 * I hereby pledge upon my word of honor that I have neither given nor received unauthorized help on this work
 * Mohammad Daud Salimi
 */
package com.company;

import java.io.File;
import java.util.Hashtable;

/**
 * This class is used to create manipulate campus objects.
 * @author Mohammad Daud Salimi
 * @version 2.0
 */
public class Campus {
    private String campusName;
    private Hashtable<String, Location> locations;
    private Location startingLocation;
    private String fileName;

    /**
     * Creates a new Campus object using filename.
     * @param filename the name of the campus
     */
    public Campus(String filename) {
        campusName = null;
        locations = new Hashtable(1);
        startingLocation = null;
        fileName = filename;

    }

    /**
     * adds a location object to the locations hashtable
     * @param location location to be added
     */
    public void addLocation(Location location) {
        locations.put(location.getName(), location);
    }

    /**
     * removes a location from the locations hashtable
     * @param location location to be removed
     */
    public void removeLocation(Location location) {
        locations.remove(location.getName());
    }

    /**
     * returns the location from the locations hashtable
     * @param name String, the name of the locations desired
     * @return Location, a location object
     */
    public Location getLocation(String name) {
        return locations.get(name);
    }

    /**
     * Sets the name of the campus
     * @param n the name of the campus
     */
    public void setCampusName(String n) {
        campusName = n;
    }

    /**
     * returns the name of the campus
     * @return String, name of campus
     */
    public String getCampusName() {
        return campusName;
    }

    /**
     * sets the name of the file
     * @param filename name of file
     */
    public void setFileName(String filename) {
        fileName = filename;
    }

    /**
     * returns the name of the file
     * @return String, name of file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * sets the starting location of tour
     * @param location starting location
     */
    public void setStartingLocation(Location location) {
        startingLocation = location;
    }

    /**
     * returns the starting location of the tour
     * @return Location, the starting location
     */
    public Location getStartingLocation() {
        return startingLocation;
    }
}

