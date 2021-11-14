package com.company;

/**
 * Location class
 */
public class Location {
    private int x;
    private int y;

    /**
     * Constructor for location class
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * get x-coordinate
     * @return x-coordinate
     */
    public int x() {
        return x;
    }

    /**
     * get y-coordinate
     * @return y-coordinate
     */
    public int y() {
        return y;
    }

    /**
     * checks if two x, y coordinates are equal to each other
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if(object == this) return true;
        if(object instanceof Location) {
            Location loc = (Location) object;
            return (loc.x() == x && loc.y() == y);
        }
        return false;
    }
}
