package com.company;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoorTest {
    private Location entering = new Location("Emerson Hall");
    private Location leaving = new Location("Baby Palace");
    private Door door = new Door("n",leaving, entering);
    @Test
    public void describe() {
        String description = door.describe();
        assertEquals(description, "Head n to get to Emerson Hall");
    }

    @Test
    public void getLeaving() {
        assertEquals(leaving, door.getLeaving());
    }

    @Test
    public void getEntering() {
        assertEquals(entering, door.getEntering());
    }

    @Test
    public void getDirection() {
        assertEquals("n", door.getDirection());
    }
}