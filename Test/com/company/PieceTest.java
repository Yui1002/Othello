package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Piece class
 */
public class PieceTest {

    @Test
    public void shouldCreatePieceColor() {
        Piece piece = new Piece("UNSELECTED");
        piece.setPieceColor("BLACK");
        assertEquals("BLACK", piece.getPieceColor());
    }
}