package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PrimitiveIterator;
import java.util.Scanner;

/**
 * This represents the othello board
 */

public class Othello extends JPanel implements ActionListener {

    private final static int SIZE = 8;
    private final static String BLACK = "B";
    private final static String WHITE = "W";
    private final static String EMPTY = "E";
    private int cntBlack = 0;
    private int cntWhite = 0;
    private JButton[][] grid;
    private final JFrame frame;
    private boolean hasGameEnded;
    private String firstPiece;
    private Location loc;
    private Piece pieces;
    private String piece;
    private String reversedPiece;
    private int player = 1;
    private boolean isEmpty;
    private JLabel label;
    private JPanel panel;

    /**
     * Constructor for Othello class, set up the board
     * B: Black, W: White, E: Empty
     */
    public Othello() {
        frame = new JFrame("Othello Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);

        panel = new JPanel();
        panel.setBounds(10,50,400,400);
        panel.setLayout(new GridLayout(SIZE,SIZE));
        panel.setBackground(Color.LIGHT_GRAY);

        frame.add(initBoard());
        frame.setVisible(true);
    }

    public JComponent initBoard() {
        grid = new JButton[SIZE][SIZE];
        hasGameEnded = false;

        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i == 3 && j == 3 || i == 4 && j == 4) {
                    pieces = new Piece("BLACK");
                    loc = new Location(i, j);
                    grid[i][j] = new JButton(pieces);
                } else if(i == 3 && j == 4 || i == 4 && j == 3) {
                    pieces = new Piece("WHITE");
                    loc = new Location(i, j);
                    grid[i][j] = new JButton(pieces);
                } else {
                    pieces = new Piece(null);
                    loc = new Location(i, j);
                    grid[i][j] = new JButton(pieces);
                }
                grid[i][j].addActionListener(this);
                grid[i][j].setActionCommand(i + "" + j);
                panel.add(grid[i][j]);
            }
        }
        return panel;
    }

    /**
     * Get coordinates the user clicked
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//        String coordinates = e.getActionCommand();
//        int x = Integer.parseInt(coordinates.substring(0, 1));
//        int y = Integer.parseInt(coordinates.substring(1));
//        loc = new Location(x, y);
        System.out.println(pieces.getPieceColor());
        System.out.println(loc.x());
        System.out.println(loc.y());

//        setPiece(loc);
    }

    /**
     * Decide which color is put at the beginning of the game
     * @return the color of first piece the player puts
     */
    public String choosePieceColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the color of the first piece to be placed. Select B for black, W for white. > ");

        firstPiece = piece = scanner.next();
        return piece;
    }

    /**
     * Get the color of reversed piece of the first piece
     * @return the color of reversed piece of the first piece
     */
    public String setReversedPiece() {
        reversedPiece = (piece.equals(BLACK) ? WHITE : BLACK);
        System.out.println("Player1: " + firstPiece + ", Player2: " + reversedPiece);
        return reversedPiece;
    }

    /**
     * checks if the place is empty, then allows user to turn the stone
     * @param loc location of the piece where user clicks
     */
    public void setPiece(Location loc) {
        if(grid[loc.x()][loc.y()].getText() != EMPTY) {
            System.out.println("そこには置けません");
        } else {
            turnOverPiece(loc);
        }
    }


    /**
     * checks for 8 directions
     */
    public void turnOverPiece(Location loc) {
        checkTop(loc);
//        checkBottom(loc);
//        checkLeft(loc);
//        checkRight(loc);
//        checkTopLeftDiagonal(loc); //左上の駒をチェック
//        checkTopRightDiagonal(loc); //右上
//        checkBottomLeftDiagonal(loc); //左下
//        checkBottomRightDiagonal(loc); // 右下
    }

    /**
     * Check to see if there is a piece to turn over in the direction of upper neighbor
     * If it is, turn the piece over until the same piece as the user-selected one appears
     *
     * @param loc the location of piece the user clicks
     */
    public void checkTop(Location loc) { // x = 5, y = 4
        // [たて、横]
        JButton next = grid[loc.x() - 1][loc.y()];
        System.out.println(loc.x() - 1);
        System.out.println(loc.y());

//        if(next.getText().equals(reversedPiece)) {
//            for(int i = 2; true; i++) {
//                if(loc.x() - i < 0 || grid[loc.x() - i][loc.y()].getText().equals(EMPTY)) {
//                    break;
//                } else if(grid[loc.x() - i][loc.y()].getText().equals(piece)) {
//                    // 自駒と同じ色
//                    for(int t = 1; t < i; t++) {
//                        grid[loc.x() - t][loc.y()].setText(piece);
//                        if(piece == "B") {
//                            grid[loc.x() - t][loc.y()].setBackground(Color.BLACK);
//                        } else {
//                            grid[loc.x() - t][loc.y()].setBackground(Color.WHITE);
//                        }
//                        grid[loc.x() - t][loc.y()].setOpaque(true);
//                        grid[loc.x() - t][loc.y()].setBorderPainted(false);
//                    }
//                    grid[loc.x()][loc.y()].setText(piece);
//                    if(piece == "B") {
//                        grid[loc.x()][loc.y()].setBackground(Color.BLACK);
//                    } else {
//                        grid[loc.x()][loc.y()].setBackground(Color.WHITE);
//                    }
//                    grid[loc.x()][loc.y()].setOpaque(true);
//                    grid[loc.x()][loc.y()].setBorderPainted(false);
//                    break;
//                }
//            }
//        }
    }

    /**
     *
     * @param loc
     */
    public void checkBottom(Location loc) {
        JButton next = grid[loc.x() + 1][loc.y()];

        if(next.getText().equals(reversedPiece)) {
            for(int i = 2; true; i++) {
                if(loc.x() + i > grid.length || grid[loc.x() + i][loc.y()].getText().equals(EMPTY)) {
                    break;
                } else if(grid[loc.x() + i][loc.y()].getText().equals(piece)) {
                    // 自駒と同じ色
                    for(int t = 1; t < i; t++) {
                        grid[loc.x() + t][loc.y()].setText(piece);
                        if(piece == "B") {
                            grid[loc.x() + t][loc.y()].setBackground(Color.BLACK);
                        } else {
                            grid[loc.x() + t][loc.y()].setBackground(Color.WHITE);
                        }
                        grid[loc.x() + t][loc.y()].setOpaque(true);
                        grid[loc.x() + t][loc.y()].setBorderPainted(false);
                    }
                    grid[loc.x()][loc.y()].setText(piece);
                    if(piece == "B") {
                        grid[loc.x()][loc.y()].setBackground(Color.BLACK);
                    } else {
                        grid[loc.x()][loc.y()].setBackground(Color.WHITE);
                    }
                    grid[loc.x()][loc.y()].setOpaque(true);
                    grid[loc.x()][loc.y()].setBorderPainted(false);
                    break;
                }
            }
        }
    }

    public void checkLeft(Location loc) {
        JButton next = grid[loc.x()][loc.y() - 1];

        if(next.getText().equals(reversedPiece)) {
            for(int i = 2; true; i++) {
                if(loc.y() - i < 0 || grid[loc.x()][loc.y() - i].getText().equals(EMPTY)) {
                    break;
                } else if(grid[loc.x()][loc.y() - i].getText().equals(piece)) {
                    // 自駒と同じ色
                    for(int t = 1; t < i; t++) {
                        grid[loc.x()][loc.y() - t].setText(piece);
                    }
                    grid[loc.x()][loc.y()].setText(piece);
                    break;
                }
            }
        }
    }

    public void checkRight(Location loc) {
        JButton next = grid[loc.x()][loc.y() + 1];

        if(next.getText().equals(reversedPiece)) {
            for(int i = 2; true; i++) {
                if(loc.y() + i > grid.length || grid[loc.x()][loc.y() + i].getText().equals(EMPTY)) {
                    break;
                } else if(grid[loc.x()][loc.y() + i].getText().equals(piece)) {
                    // 自駒と同じ色
                    for(int t = 1; t < i; t++) {
                        grid[loc.x()][loc.y() + t].setText(piece);
                    }
                    grid[loc.x()][loc.y()].setText(piece);
                    break;
                }
            }
        }
    }

    public void checkTopLeftDiagonal(Location loc) { // 左上
        JButton next = grid[loc.x() - 1][loc.y() - 1];

        if(next.getText().equals(reversedPiece)) {
            for(int i = 2; true; i++) {
                if(loc.x() - i < 0 || loc.y() - i < 0 || grid[loc.x() - i][loc.y() - i].getText().equals(EMPTY)) {
                    break;
                } else if(grid[loc.x() - i][loc.y() - i].getText().equals(piece)) {
                    // 自駒と同じ色
                    for(int t = 1; t < i; t++) {
                        grid[loc.x() - t][loc.y() - t].setText(piece);
                    }
                    grid[loc.x()][loc.y()].setText(piece);
                    break;
                }
            }
        }
    }

    public void checkBottomLeftDiagonal(Location loc) { // 左下
        JButton next = grid[loc.x() + 1][loc.y() - 1];

        if(next.getText().equals(reversedPiece)) {
            for(int i = 2; true; i++) {
                if(loc.x() + i > grid.length || loc.y() - i < 0 || grid[loc.x() + i][loc.y() - i].getText().equals(EMPTY)) {
                    break;
                } else if(grid[loc.x() + i][loc.y() - i].getText().equals(piece)) {
                    // 自駒と同じ色
                    for(int t = 1; t < i; t++) {
                        grid[loc.x() + t][loc.y() - t].setText(piece);
                    }
                    grid[loc.x()][loc.y()].setText(piece);
                    break;
                }
            }
        }
    }

    public void checkTopRightDiagonal(Location loc) { // 右上
        JButton next = grid[loc.x() - 1][loc.y() + 1];

        if(next.getText().equals(reversedPiece)) {
            for(int i = 2; true; i++) {
                if(loc.x() - i < 0 || loc.y() + i > grid.length || grid[loc.x() - i][loc.y() + i].getText().equals(EMPTY)) {
                    break;
                } else if(grid[loc.x() - i][loc.y() + i].getText().equals(piece)) {
                    // 自駒と同じ色
                    for(int t = 1; t < i; t++) {
                        grid[loc.x() - t][loc.y() + t].setText(piece);
                    }
                    grid[loc.x()][loc.y()].setText(piece);
                    break;
                }
            }
        }
    }

    public void checkBottomRightDiagonal(Location loc) { // 右下
        JButton next = grid[loc.x() + 1][loc.y() + 1];

        if(next.getText().equals(reversedPiece)) {
            for(int i = 2; true; i++) {
                if(loc.x() + i > grid.length || loc.y() + i > grid.length || grid[loc.x() + i][loc.y() + i].getText().equals(EMPTY)) {
                    break;
                } else if(grid[loc.x() + i][loc.y() + i].getText().equals(piece)) {
                    // 自駒と同じ色
                    for(int t = 1; t < i; t++) {
                        grid[loc.x() + t][loc.y() + t].setText(piece);
                    }
                    grid[loc.x()][loc.y()].setText(piece);
                    break;
                }
            }
        }
    }

}
