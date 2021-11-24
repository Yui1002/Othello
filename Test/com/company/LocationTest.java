package com.company;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Tests the Location class
 */
class LocationTest {
    private Location loc;

    public LocationTest() {
        loc = new Location(1,1);
    }

    @Test
    public void testX() {
        assertThat(loc.x(), is(1));
    }

    @Test
    public void testY() {
        assertThat(loc.y(), is(1));
    }

    @Test
    public void testEquals() {
        Location loc1 = new Location(1,1);
        Location loc2 = new Location(1,2);
        Location loc3 = null;

        assertThat(loc.equals(loc1), is(true));
        assertThat(loc.equals(loc2), is(false));
        assertThat(loc.equals(loc3), is(false));
    }

}