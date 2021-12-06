package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Location class
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LocationTest {
    private Location loc;

    @BeforeAll
    public void setConstructorOfLocationClass() {
        loc = new Location(1,2);
    }

    @Test
    public void shouldGetLocationOfX() {
        assertEquals(1, loc.x());
    }

    @Test
    public void shouldGetLocationOfY() {
        assertEquals(2, loc.y());
    }

    @Test
    public void checkPassedLocationIsSameAsSetLocation() {
        assertTrue(loc.equals(new Location(1,2)));
        assertFalse(loc.equals(new Location(2,4)));
    }
}