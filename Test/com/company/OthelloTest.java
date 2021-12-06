package com.company;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OthelloTest {

    private Othello othello;
    private JButton[][] grid;
    private Piece[][] board;
    private Player player1;
    private Player player2;

    @BeforeAll
    public void createConstructorOfOthelloClass() {
        othello = new Othello();
        grid = othello.getGrid();
        board = othello.getBoard();
    }

    @Test
    public void testActionListener() {
        Location loc = new Location(1,2);
        Piece piece = new Piece("BLACK");

        othello.setLocation(loc, piece);
        assertDoesNotThrow(() -> grid[loc.x()][loc.y()].doClick());
    }

    @Test
    public void shouldGetLocation() {
        Location loc = new Location(1,2);
        Piece piece = new Piece("BLACK");
        othello.setLocation(loc, piece);

        assertEquals(board[1][2], othello.getLocation(loc));
    }

    @Test
    public void shouldCheckLocation() {
        Location loc = new Location(1,2);
        Location loc2 = new Location(-1,2);
        Location loc3 = new Location(1, -2);
        Location loc4 = new Location(-1,-1);

        assertTrue(othello.checkLocation(loc));
        assertFalse(othello.checkLocation(loc2));
        assertFalse(othello.checkLocation(loc3));
        assertFalse(othello.checkLocation(loc4));
    }

    @Test
    public void shouldCheckColor() {
        Piece piece = new Piece("UNSELECTED");
        Piece piece2 = new Piece("BLACK");

        assertTrue(othello.checkColor(piece));
        assertFalse(othello.checkColor(piece2));
    }

    @Test
    public void shouldCheckGameHasEnded() {
        assertTrue(othello.checkGameEnd(20,50));
        assertTrue(othello.checkGameEnd(10,0));
        assertFalse(othello.checkGameEnd(20,30));
    }

    @Test
    public void shouldGetEachPlayersPoints() {

        player1 = new Player("Player1", "BLACK", 0, true);
        player2 = new Player("Player2", "WHITE", 0, false);

        player1.setPoints(othello.updateCounts()[0]);
        player2.setPoints(othello.updateCounts()[1]);

        assertEquals(2, player1.getPoints());
        assertEquals(2, player2.getPoints());
    }

    @Test
    public void displayUpdatedMessage() {

        player1 = new Player("Player1", "BLACK", 0, true);
        player2 = new Player("Player2", "WHITE", 0, false);

        player1.setPoints(20);
        player2.setPoints(34);

        othello.updateMessage(player1.getPoints(), player2.getPoints());
        String message = "<html><body><br />" +
                "Player1: BLACK<br /> " +
                "Player2: WHITE <br /><br /> " +
                "Current turn: " + (player1.getTurn() ? player1.getPlayerName() : player2.getPlayerName()) + "<br /><br /> " +
                "Black: " + player1.getPoints() + "<br /> " +
                "White: " + player2.getPoints();
        assertEquals(message, othello.updateMessage(player1.getPoints(), player2.getPoints()));
    }

    @Test
    public void displayGameOverMessageWhenTheGameIsOver() {
        player1 = new Player("Player1", "BLACK", 0, true);
        player2 = new Player("Player2", "WHITE", 0, false);

        player1.setPoints(32);
        player2.setPoints(32);
        String message = "<html><body><br /> Draw";
        assertEquals(message, othello.gameOverMessage(player1.getPoints(), player2.getPoints()));

        player1.setPoints(34);
        player2.setPoints(30);
        message = "<html><body><br />" + player1.getPlayerName() + " won!";
        assertEquals(message, othello.gameOverMessage(player1.getPoints(), player2.getPoints()));

        player1.setPoints(30);
        player2.setPoints(34);
        message = "<html><body><br />" + player2.getPlayerName() + " won!";
        assertEquals(message, othello.gameOverMessage(player1.getPoints(), player2.getPoints()));
    }

    @Test
    public void testCheckTop() {
        // Case1: the x-coordinate of the piece is 0
        Location loc = new Location(0, 1);
        Piece piece = new Piece("BLACK");

        // Case2: the piece right above has the same color
        Location loc2 = new Location(5,4);
        Piece piece2 = new Piece("BLACK");

        // Case3: meets the requirements to flip over(passed)
        Location loc3 = new Location(5,3);
        Location loc4 = new Location(6,3);
        Piece piece3 = new Piece("WHITE");
        Piece piece4 = new Piece("BLACK");

        // Case4: the piece two above is out of bounds
        Location loc5 = new Location(0,2);
        Location loc6 = new Location(1,2);
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("BLACK");

        // Case5: the piece two above has the "UNSELECTED" color
        Location loc7 = new Location(3,2);
        Location loc8 = new Location(4,2);
        Location loc9 = new Location(5,2);
        Piece piece7 = new Piece("WHITE");
        Piece piece8 = new Piece("WHITE");
        Piece piece9 = new Piece("BLACK");

        othello.setLocation(loc3, piece3);
        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);

        assertFalse(othello.checkTop(loc, piece)); //Case1
        assertFalse(othello.checkTop(loc2, piece2)); //Case2
        assertTrue(othello.checkTop(loc4, piece4)); //Case3
        assertFalse(othello.checkTop(loc6, piece6)); //Case4
        assertFalse(othello.checkTop(loc9, piece9)); //case5
    }

    @Test
    public void testCheckBottom() {
        // Case1: the x-coordinate of the piece is 7
        Location loc = new Location(7, 1);
        Piece piece = new Piece("BLACK");

        // Case2: the piece right below has the same color
        Location loc2 = new Location(2,3);
        Piece piece2 = new Piece("BLACK");

        // Case3: meets the requirements to flip over(passed)
        Location loc3 = new Location(2,4);
        Location loc4 = new Location(1,4);
        Piece piece3 = new Piece("WHITE");
        Piece piece4 = new Piece("BLACK");

        // Case4: the piece two below is out of bounds
        Location loc5 = new Location(7,2);
        Location loc6 = new Location(6,2);
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("BLACK");

        // Case5: the piece two below has the "UNSELECTED" color
        Location loc7 = new Location(3,5);
        Location loc8 = new Location(4,5);
        Location loc9 = new Location(2,5);
        Piece piece7 = new Piece("WHITE");
        Piece piece8 = new Piece("WHITE");
        Piece piece9 = new Piece("BLACK");

        othello.setLocation(loc3, piece3);
        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);

        assertFalse(othello.checkBottom(loc, piece)); //Case1
        assertFalse(othello.checkBottom(loc2, piece2)); //Case2
        assertTrue(othello.checkBottom(loc4, piece4)); //Case3
        assertFalse(othello.checkBottom(loc6, piece6)); // case4
        assertFalse(othello.checkBottom(loc9, piece9)); //case5
    }

    @Test
    public void testCheckLeft() {
        // Case1: the y-coordinate of the piece is 0
        Location loc = new Location(1, 0);
        Piece piece = new Piece("BLACK");

        // Case2: the piece on the left has the same color
        Location loc2 = new Location(4,5);
        Piece piece2 = new Piece("BLACK");

        // Case3: meets the requirements to flip over(passed)
        Location loc3 = new Location(3,5);
        Location loc4 = new Location(3,6);
        Piece piece3 = new Piece("WHITE");
        Piece piece4 = new Piece("BLACK");

        // Case4: the second piece to the left is out of bounds
        Location loc5 = new Location(6,0);
        Location loc6 = new Location(6,1);
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("BLACK");

        // Case5: the second piece to the left has the "UNSELECTED" color
        Location loc7 = new Location(7,3);
        Location loc8 = new Location(7,4);
        Location loc9 = new Location(7,5);
        Piece piece7 = new Piece("WHITE");
        Piece piece8 = new Piece("WHITE");
        Piece piece9 = new Piece("BLACK");

        othello.setLocation(loc3, piece3);
        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);

        assertFalse(othello.checkLeft(loc, piece)); //Case1
        assertFalse(othello.checkLeft(loc2, piece2)); //Case2
        assertTrue(othello.checkLeft(loc4, piece4)); //Case3
        assertFalse(othello.checkLeft(loc6, piece6)); // case4
        assertFalse(othello.checkLeft(loc9, piece9)); //case5
    }

    @Test
    public void testCheckRight() {
        // Case1: the y-coordinate of the piece is 7
        Location loc = new Location(0, 7);
        Piece piece = new Piece("BLACK");

        // Case2: the piece on the right has the same color
        Location loc2 = new Location(3,2);
        Piece piece2 = new Piece("BLACK");

        // Case3: meets the requirements to flip over(passed)
        Location loc3 = new Location(4,2);
        Location loc4 = new Location(4,1);
        Piece piece3 = new Piece("WHITE");
        Piece piece4 = new Piece("BLACK");

        // Case4: the second piece to the right is out of bounds
        Location loc5 = new Location(5,7);
        Location loc6 = new Location(5,6);
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("BLACK");

        // Case5: the second piece to the right has "UNSELECTED" color
        Location loc7 = new Location(6,4);
        Location loc8 = new Location(6,5);
        Location loc9 = new Location(6,3);
        Piece piece7 = new Piece("WHITE");
        Piece piece8 = new Piece("WHITE");
        Piece piece9 = new Piece("BLACK");

        othello.setLocation(loc3, piece3);
        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);

        assertFalse(othello.checkRight(loc, piece)); //Case1
        assertFalse(othello.checkRight(loc2, piece2)); //Case2
        assertTrue(othello.checkRight(loc4, piece4)); //Case3
        assertFalse(othello.checkRight(loc6, piece6)); // case4
        assertFalse(othello.checkRight(loc9, piece9)); //case5
    }

    @Test
    public void testCheckTopLeftDiagonal() {
        // Case1: the x-coordinate of the piece is 0, or the y-coordinate of the piece is 0
        Location loc = new Location(0, 1);
        Location loc2 = new Location(1,0);
        Piece piece = new Piece("BLACK");
        Piece piece2 = new Piece("BLACK");

        // Case2: the piece on the top left diagonal has the same color
        Location loc3 = new Location(5,5);
        Piece piece3 = new Piece("BLACK");

        // Case3: meets the requirements to flip over(success)
        Location loc4 = new Location(1,4);
        Location loc5 = new Location(2,5);
        Location loc6 = new Location(3,6);
        Location loc7 = new Location(4,7);
        Piece piece4 = new Piece("BLACK");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("WHITE");
        Piece piece7 = new Piece("BLACK");

        // Case4: the second piece to the top left diagonal is out of bounds
        Location loc8 = new Location(0,5);
        Location loc9 = new Location(1,6);
        Piece piece8 = new Piece("WHITE");
        Piece piece9 = new Piece("BLACK");

        // Case5: the second piece to the top left diagonal has the "UNSELECTED" color
        Location loc10 = new Location(4,1);
        Location loc11 = new Location(5,2);
        Location loc12 = new Location(6,3);
        Piece piece10 = new Piece("WHITE");
        Piece piece11 = new Piece("WHITE");
        Piece piece12 = new Piece("BLACK");

        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);
        othello.setLocation(loc10, piece10);
        othello.setLocation(loc11, piece11);
        othello.setLocation(loc12, piece12);

        assertFalse(othello.checkTopLeftDiagonal(loc, piece)); //Case1
        assertFalse(othello.checkTopLeftDiagonal(loc2, piece2));//Case1
        assertFalse(othello.checkTopLeftDiagonal(loc3, piece3)); //Case2
        assertTrue(othello.checkTopLeftDiagonal(loc7, piece7)); //Case3
        assertFalse(othello.checkTopLeftDiagonal(loc9, piece9)); // case4
        assertFalse(othello.checkTopLeftDiagonal(loc12, piece12)); //case5
    }

    @Test
    public void testCheckTopRightDiagonal() {
        // Case1: the x-coordinate of the piece is 0, or the y-coordinate of the piece is 7
        Location loc = new Location(0, 1);
        Location loc2 = new Location(1, 7);
        Piece piece = new Piece("BLACK");
        Piece piece2 = new Piece("BLACK");

        // Case2: the piece on the top right diagonal has the same color
        Location loc3 = new Location(5,3);
        Piece piece3 = new Piece("BLACK");

        // Case3: meets the requirement to flip over
        Location loc4 = new Location(4,6);
        Location loc5 = new Location(5,5);
        Location loc6 = new Location(6,4);
        Location loc7 = new Location(7,3);
        Piece piece4 = new Piece("BLACK");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("WHITE");
        Piece piece7 = new Piece("BLACK");

        // Case4: the second piece to the top right diagonal is out of bounds
        Location loc8 = new Location(6,7);
        Location loc9 = new Location(7,6);
        Piece piece8 = new Piece("WHITE");
        Piece piece9 = new Piece("BLACK");

        // Case5: the second piece to the top right diagonal has the "UNSELECTED" color
        Location loc10 = new Location(1,2);
        Location loc11 = new Location(2,1);
        Location loc12 = new Location(3,0);
        Piece piece10 = new Piece("WHITE");
        Piece piece11 = new Piece("WHITE");
        Piece piece12 = new Piece("BLACK");

        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);
        othello.setLocation(loc10, piece10);
        othello.setLocation(loc11, piece11);
        othello.setLocation(loc12, piece12);

        assertFalse(othello.checkTopRightDiagonal(loc, piece));//Case1
        assertFalse(othello.checkTopRightDiagonal(loc2, piece2));//Case1
        assertFalse(othello.checkTopRightDiagonal(loc3, piece3)); //Case2
        assertTrue(othello.checkTopRightDiagonal(loc7, piece7)); //Case3
        assertFalse(othello.checkTopRightDiagonal(loc9, piece9)); // case4
        assertFalse(othello.checkTopRightDiagonal(loc12, piece12)); //case5
    }

    @Test
    public void testCheckBottomLeftDiagonal() {
        // Case1: the x-coordinate of the piece is 7, or the y-coordinate of the piece is 0
        Location loc = new Location(7, 1);
        Location loc2 = new Location(1, 0);
        Piece piece = new Piece("BLACK");
        Piece piece2 = new Piece("BLACK");

        // Case2: the piece on the bottom left diagonal has the same color
        Location loc3 = new Location(2,4);
        Piece piece3 = new Piece("BLACK");

        // Case3: meets the requirements to flip over(passed)
        Location loc4 = new Location(6,4);
        Location loc5 = new Location(5,5);
        Location loc6 = new Location(4,6);
        Location loc7 = new Location(3,7);
        Piece piece4 = new Piece("BLACK");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("WHITE");
        Piece piece7 = new Piece("BLACK");

        // Case4: the second piece to the bottom left diagonal is out of bounds
        Location loc8 = new Location(7,5);
        Location loc9 = new Location(6,6);
        Piece piece8 = new Piece("WHITE");
        Piece piece9 = new Piece("BLACK");

        // Case5: the second piece to the bottom left diagonal has the "UNSELECTED" color
        Location loc10 = new Location(2,1);
        Location loc11 = new Location(1,2);
        Location loc12 = new Location(0,3);
        Piece piece10 = new Piece("WHITE");
        Piece piece11 = new Piece("WHITE");
        Piece piece12 = new Piece("BLACK");

        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);
        othello.setLocation(loc10, piece10);
        othello.setLocation(loc11, piece11);
        othello.setLocation(loc12, piece12);

        assertFalse(othello.checkBottomLeftDiagonal(loc, piece));//Case1
        assertFalse(othello.checkBottomLeftDiagonal(loc2, piece2));//Case1
        assertFalse(othello.checkBottomLeftDiagonal(loc3, piece3)); //Case2
        assertTrue(othello.checkBottomLeftDiagonal(loc7, piece7)); //Case3
        assertFalse(othello.checkBottomLeftDiagonal(loc9, piece9)); // case4
        assertFalse(othello.checkBottomLeftDiagonal(loc12, piece12)); //case5
    }

    @Test
    public void testCheckBottomRightDiagonal() {
        // Case1: the x-coordinate of the piece is 7, or the y-coordinate of the piece is 7
        Location loc = new Location(7, 1);
        Location loc2 = new Location(1, 7);
        Piece piece = new Piece("BLACK");
        Piece piece2 = new Piece("BLACK");

        // Case2: the piece on the bottom right diagonal has the same color
        Location loc3 = new Location(2,2);
        Piece piece3 = new Piece("BLACK");

        // Case3: meets the requirements to flip over
        Location loc4 = new Location(7,4);
        Location loc5 = new Location(6,3);
        Location loc6 = new Location(5,2);
        Location loc7 = new Location(4,1);
        Piece piece4 = new Piece("BLACK");
        Piece piece5 = new Piece("WHITE");
        Piece piece6 = new Piece("WHITE");
        Piece piece7 = new Piece("BLACK");

        // Case4: the second piece to the bottom right diagonal is out of bounds
        Location loc8 = new Location(7,7);
        Location loc9 = new Location(6,6);
        Piece piece8 = new Piece("WHITE");
        Piece piece9 = new Piece("BLACK");

        // Case5: the second piece to the bottom right diagonal has the "UNSELECTED" color
        Location loc10 = new Location(3,6);
        Location loc11 = new Location(2,5);
        Location loc12 = new Location(1,4);
        Piece piece10 = new Piece("WHITE");
        Piece piece11 = new Piece("WHITE");
        Piece piece12 = new Piece("BLACK");

        othello.setLocation(loc4, piece4);
        othello.setLocation(loc5, piece5);
        othello.setLocation(loc6, piece6);
        othello.setLocation(loc7, piece7);
        othello.setLocation(loc8, piece8);
        othello.setLocation(loc9, piece9);
        othello.setLocation(loc10, piece10);
        othello.setLocation(loc11, piece11);
        othello.setLocation(loc12, piece12);

        assertFalse(othello.checkBottomRightDiagonal(loc, piece));//Case1
        assertFalse(othello.checkBottomRightDiagonal(loc2, piece2));//Case1
        assertFalse(othello.checkBottomRightDiagonal(loc3, piece3)); //Case2
        assertTrue(othello.checkBottomRightDiagonal(loc7, piece7)); //Case3
        assertFalse(othello.checkBottomRightDiagonal(loc9, piece9)); // case4
        assertFalse(othello.checkBottomRightDiagonal(loc12, piece12)); //case5
    }
}
