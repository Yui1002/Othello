package com.company;

import javax.swing.*;
import java.awt.*;

public class Piece implements Icon {
    private String color;

    /**
     * Creates a piece with a color (black/white)
     * @param color color of the piece
     */
    public Piece(String color) {
        this.color = color;
    }

    /**
     * Set the color of the piece
     * @param color color of piece
     */
    public void setPieceColor(String color) {
        this.color = color;
    }

    /**
     * Get the color of the piece
     * @return color of the piece
     */
    public String getPieceColor() {
        return this.color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        if(color.equals("BLACK")) {
            g2.setColor(Color.BLACK);
        } else if(color.equals("WHITE")) {
            g2.setColor(new Color(255,228,181));
        } else {
            g2.setColor(Color.WHITE);
        }
        g2.fillOval(x, y, getIconWidth() - 1, getIconHeight() - 1);
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return 20;
    }

    @Override
    public int getIconHeight() {
        return 20;
    }
}
