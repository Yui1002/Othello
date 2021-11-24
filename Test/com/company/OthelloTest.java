package com.company;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Tests the Othello class
 */
public class OthelloTest {

    // Fields
    private Othello othello;
    private Location loc;
    private Piece piece;

    public OthelloTest() {
        othello = new Othello();
        loc = new Location(5,5);
        piece = new Piece("UNSELECTED");
    }

    /**
     * test the check location method
     */
    @Test
    public void testCheckLocation() {
        Location loc2 = new Location(-1, 0);
        Location loc3 = new Location(0, -1);
        Location loc4 = new Location(0, 8);
        Location loc5 = new Location(8,0);
        Location loc6 = new Location(2,2);

        assertThat(othello.checkLocation(loc2), is(false));
        assertThat(othello.checkLocation(loc3), is(false));
        assertThat(othello.checkLocation(loc4), is(false));
        assertThat(othello.checkLocation(loc5), is(false));
        assertThat(othello.checkLocation(loc6), is(true));
    }

    /**
     * test the check color method
     */
    @Test
    public void testCheckColor() {
        Piece piece2 = new Piece("UNSELECTED");
        Piece piece3 = new Piece("BLACK");
        Piece piece4 = new Piece("WHITE");

        assertThat(othello.checkColor(piece2), is(true));
        assertThat(othello.checkColor(piece3), is(false));
        assertThat(othello.checkColor(piece4), is(false));
    }

    /**
     * test the check game end method
     */
    @Test
    public void testCheckGameEnd() {
        assertThat(othello.checkGameEnd(20, 45), is(true));
        assertThat(othello.checkGameEnd(0, 10), is(true));
        assertThat(othello.checkGameEnd(20, 30), is(false));
    }

    /**
     * test the check top method
     *
     * Case1: loc.x == 0
     * Case2: there are more than 2 white pieces between the 2 black pieces
     * Case3: a piece one above is the same color
     */
    @Test
    public void testCheckTop() {
        // Case1
        Location loc2 = new Location(0, 1);
        Piece piece2 = new Piece("UNSELECTED");

        // Case2
        Location loc3 = new Location(2,1);
        Location loc4 = new Location(3,1);
        Location loc5 = new Location(4,1);
        Location loc6 = new Location(5,1);
        Piece piece3 = new Piece("BLACK");
        Piece piece4 = new Piece("WHITE");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("UNSELECTED");

        // Case3
        Location loc7 = new Location(2,0);
        Location loc8 = new Location(1,0);
        Piece piece7 = new Piece("BLACK");
        Piece piece8 = new Piece("UNSELECTED");

        othello.setLocation(loc3, piece3);
        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);

        assertThat(othello.checkTop(loc2, piece2), is(false));
        assertThat(othello.checkTop(loc6, piece6), is(true));
        assertThat(othello.checkTop(loc8, piece8), is(false));
    }

    /**
     * test the check bottom method
     *
     * Case1: loc.x == 7
     * Case2: there are more than 2 white pieces between the 2 black pieces
     * Case3: a piece one above is the same color
     */
    @Test
    public void testCheckBottom() {
        // Case1
        Location loc2 = new Location(7, 1);
        Piece piece2 = new Piece("UNSELECTED");

        // Case2
        Location loc3 = new Location(3,1);
        Location loc4 = new Location(4,1);
        Location loc5 = new Location(5,1);
        Location loc6 = new Location(2,1);
        Piece piece3 = new Piece("WHITE");
        Piece piece4 = new Piece("WHITE");
        Piece piece5 = new Piece("BLACK");
        Piece piece6 = new Piece("UNSELECTED");

        // Case3
        Location loc7 = new Location(2,0);
        Location loc8 = new Location(1,0);
        Piece piece7 = new Piece("BLACK");
        Piece piece8 = new Piece("UNSELECTED");

        othello.setLocation(loc3, piece3);
        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);

        assertThat(othello.checkBottom(loc2, piece2), is(false));
        assertThat(othello.checkBottom(loc6, piece6), is(true));
        assertThat(othello.checkBottom(loc8, piece8), is(false));
    }

    /**
     * test the check left method
     *
     * Case1: loc.y == 0
     * Case2: there are more than 2 white pieces between the 2 black pieces
     * Case3: a piece one above is the same color
     */
    @Test
    public void testCheckLeft() {
        // Case1
        Location loc2 = new Location(1, 0);
        Piece piece2 = new Piece("UNSELECTED");

        // Case2
        Location loc3 = new Location(6,1);
        Location loc4 = new Location(6,2);
        Location loc5 = new Location(6,3);
        Location loc6 = new Location(6,4);
        Piece piece3 = new Piece("BLACK");
        Piece piece4 = new Piece("WHITE");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("UNSELECTED");

        // Case3
        Location loc7 = new Location(7,1);
        Location loc8 = new Location(7,2);
        Piece piece7 = new Piece("BLACK");
        Piece piece8 = new Piece("UNSELECTED");

        othello.setLocation(loc3, piece3);
        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);

        assertThat(othello.checkLeft(loc2, piece2), is(false));
        assertThat(othello.checkLeft(loc6, piece6), is(true));
        assertThat(othello.checkLeft(loc8, piece8), is(false));
    }

    /**
     * test the check right method
     *
     * Case1: loc.y == 7
     * Case2: there are more than 2 white pieces between the 2 black pieces
     * Case3: a piece one above is the same color
     */
    @Test
    public void testCheckRight() {
        // Case1
        Location loc2 = new Location(1, 7);
        Piece piece2 = new Piece("UNSELECTED");

        // Case2
        Location loc3 = new Location(1,4);
        Location loc4 = new Location(1,5);
        Location loc5 = new Location(1,6);
        Location loc6 = new Location(1,3);
        Piece piece3 = new Piece("WHITE");
        Piece piece4 = new Piece("WHITE");
        Piece piece5 = new Piece("BLACK");
        Piece piece6 = new Piece("UNSELECTED");

        // Case3
        Location loc7 = new Location(0,6);
        Location loc8 = new Location(0,5);
        Piece piece7 = new Piece("BLACK");
        Piece piece8 = new Piece("UNSELECTED");

        othello.setLocation(loc3, piece3);
        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);

        assertThat(othello.checkRight(loc2, piece2), is(false));
        assertThat(othello.checkRight(loc6, piece6), is(true));
        assertThat(othello.checkRight(loc8, piece8), is(false));
    }

    /**
     * test the check top left diagonal method
     *
     * Case1: loc.x == 0 || loc.y == 0
     * Case2: there are more than 2 white pieces between the 2 black pieces
     * Case3: a piece one above is the same color
     */
    @Test
    public void testCheckTopLeftDiagonal() {
        // Case1
        Location loc2 = new Location(0, 1); // loc.x == 0
        Location loc3 = new Location(1, 0); // loc.y == 0
        Piece piece2 = new Piece("UNSELECTED");
        Piece piece3 = new Piece("UNSELECTED");

        // Case2
        Location loc4 = new Location(4,0);
        Location loc5 = new Location(5,1);
        Location loc6 = new Location(6,2);
        Location loc7 = new Location(7,3);
        Piece piece4 = new Piece("BLACK");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("WHITE");
        Piece piece7 = new Piece("UNSELECTED");

        // Case3
        Location loc8 = new Location(6,0);
        Location loc9 = new Location(7,1);
        Piece piece8 = new Piece("BLACK");
        Piece piece9 = new Piece("UNSELECTED");

        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);

        assertThat(othello.checkTopLeftDiagonal(loc2, piece2), is(false));
        assertThat(othello.checkTopLeftDiagonal(loc3, piece3), is(false));
        assertThat(othello.checkTopLeftDiagonal(loc7, piece7), is(true));
        assertThat(othello.checkTopLeftDiagonal(loc9, piece9), is(false));
    }

    /**
     * test the check top right diagonal method
     *
     * Case1: loc.x == 0 || loc.y == 7
     * Case2: there are more than 2 white pieces between the 2 black pieces
     * Case3: a piece one above is the same color
     */
    @Test
    public void testCheckTopRightDiagonal() {
        // Case1
        Location loc2 = new Location(0, 1); // loc.x == 0
        Location loc3 = new Location(7, 7); // loc.y == 7
        Piece piece2 = new Piece("UNSELECTED");
        Piece piece3 = new Piece("UNSELECTED");

        // Case2
        Location loc4 = new Location(4,7);
        Location loc5 = new Location(5,6);
        Location loc6 = new Location(6,5);
        Location loc7 = new Location(7,4);
        Piece piece4 = new Piece("BLACK");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("WHITE");
        Piece piece7 = new Piece("UNSELECTED");

        // Case3
        Location loc8 = new Location(6,7);
        Location loc9 = new Location(7,6);
        Piece piece8 = new Piece("BLACK");
        Piece piece9 = new Piece("UNSELECTED");

        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);

        assertThat(othello.checkTopRightDiagonal(loc2, piece2), is(false));
        assertThat(othello.checkTopRightDiagonal(loc3, piece3), is(false));
        assertThat(othello.checkTopRightDiagonal(loc7, piece7), is(true));
        assertThat(othello.checkTopRightDiagonal(loc9, piece9), is(false));
    }

    /**
     * test the check bottom left diagonal method
     *
     * Case1: loc.x == 7 || loc.y == 0
     * Case2: there are more than 2 white pieces between the 2 black pieces
     * Case3: a piece one above is the same color
     */
    @Test
    public void testCheckBottomLeftDiagonal() {
        // Case1
        Location loc2 = new Location(7, 1); // loc.x == 7
        Location loc3 = new Location(1, 0); // loc.y == 0
        Piece piece2 = new Piece("UNSELECTED");
        Piece piece3 = new Piece("UNSELECTED");

        // Case2
        Location loc4 = new Location(1,2);
        Location loc5 = new Location(2,1);
        Location loc6 = new Location(3,0);
        Location loc7 = new Location(0,3);
        Piece piece4 = new Piece("WHITE");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("BLACK");
        Piece piece7 = new Piece("UNSELECTED");

        // Case3
        Location loc8 = new Location(1,0);
        Location loc9 = new Location(0, 1);
        Piece piece8 = new Piece("BLACK");
        Piece piece9 = new Piece("UNSELECTED");

        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);

        assertThat(othello.checkBottomLeftDiagonal(loc2, piece2), is(false));
        assertThat(othello.checkBottomLeftDiagonal(loc3, piece3), is(false));
        assertThat(othello.checkBottomLeftDiagonal(loc7, piece7), is(true));
        assertThat(othello.checkBottomLeftDiagonal(loc9, piece9), is(false));
    }

    /**
     * test the check bottom right diagonal method
     *
     * Case1: loc.x == 7 || loc.y == 7
     * Case2: there are more than 2 white pieces between the 2 black pieces
     * Case3: a piece one above is the same color
     */
    @Test
    public void testCheckBottomRightDiagonal() {
        // Case1
        Location loc2 = new Location(7, 6); // loc.x == 7
        Location loc3 = new Location(1, 7); // loc.y == 7
        Piece piece2 = new Piece("UNSELECTED");
        Piece piece3 = new Piece("UNSELECTED");

        // Case2
        Location loc4 = new Location(1,5);
        Location loc5 = new Location(2,6);
        Location loc6 = new Location(3,7);
        Location loc7 = new Location(0,4);
        Piece piece4 = new Piece("WHITE");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("BLACK");
        Piece piece7 = new Piece("UNSELECTED");

        // Case3
        Location loc8 = new Location(1,7);
        Location loc9 = new Location(0,6);
        Piece piece8 = new Piece("BLACK");
        Piece piece9 = new Piece("UNSELECTED");

        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);

        assertThat(othello.checkBottomRightDiagonal(loc2, piece2), is(false));
        assertThat(othello.checkBottomRightDiagonal(loc3, piece3), is(false));
        assertThat(othello.checkBottomRightDiagonal(loc7, piece7), is(true));
        assertThat(othello.checkBottomRightDiagonal(loc9, piece9), is(false));
    }
}