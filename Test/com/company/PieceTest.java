package com.company;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Tests the Piece class
 */
public class PieceTest {
    @Test
    public void testPieceClass() {
        Piece piece = new Piece("BLACK");
        assertThat(piece.getPieceColor(), is("BLACK"));
    }
}