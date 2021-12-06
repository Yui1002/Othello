package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerTest {
    private Player player;

    @BeforeAll
    public void createConstructorOfPlayerClass() {
        player = new Player("Player1", "BLACK", 0, true);
    }

    @Test
    public void shouldGetPlayersColor() {
        assertEquals("BLACK", player.getPlayerColor());
    }

    @Test
    public void shouldGetPlayersName() {
        assertEquals("Player1", player.getPlayerName());
    }

    @Test
    public void shouldGetPlayersPoints() {
        player.setPoints(5);
        assertEquals(5, player.getPoints());
    }

    @Test
    public void shouldGetPlayersTurn() {
        player.setTurn(false);
        assertFalse(player.getTurn());
    }

}