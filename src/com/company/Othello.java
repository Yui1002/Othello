package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This represents the othello board
 */

public class Othello extends JPanel implements ActionListener {

    // Fields
    private final static int SIZE = 8;
    private JButton[][] grid;
    private Piece[][] board;
    private Location loc;
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label;
    private String message;
    private String turn;
    private String stone;
    private String rev_stone;
    private int black_count;
    private int white_count;

    /**
     * Constructor for the Othello class, set up a board
     */
    public Othello() {
        // initialize the color of piece and players
        // player 1 : BLACK
        // player 2 : WHITE
        stone = "BLACK";
        rev_stone = "WHITE";
        turn = "Player 1";
        black_count = 2;
        white_count = 2;

        // set up frame
        frame = new JFrame("Othello Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);

        panel1 = new JPanel(new FlowLayout());
        panel1.setPreferredSize(new Dimension(420, 150));

        message = "<html><body><br />Player1: BLACK<br /> Player2: WHITE <br /><br /> Current turn: " + turn + "<br /><br /> Black: " + black_count + "<br /> White: " + white_count;
        label = new JLabel(message);
        panel1.add(label);

        panel2 = new JPanel(new GridLayout(SIZE, SIZE));
        panel2.setPreferredSize(new Dimension(420, 400));
        panel2.setBackground(Color.LIGHT_GRAY);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(initBoard(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Initialize a board
     * @return the othello board
     */
    public JComponent initBoard() {
        grid = new JButton[SIZE][SIZE];
        board = new Piece[SIZE][SIZE];

        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(i == 3 && j == 3 || i == 4 && j == 4) {
                    board[i][j] = new Piece("BLACK");
                    grid[i][j] = new JButton(board[i][j]); // paint a circle with the color
                } else if(i == 3 && j == 4 || i == 4 && j == 3) {
                    board[i][j] = new Piece("WHITE");
                    grid[i][j] = new JButton(board[i][j]);
                } else {
                    board[i][j] = new Piece("UNSELECTED");
                    grid[i][j] = new JButton(board[i][j]);
                }
                grid[i][j].addActionListener(this);
                grid[i][j].setActionCommand(i + "" + j);
                panel2.add(grid[i][j]);
            }
        }
        return panel2;
    }

    /**
     * Get the coordinates of the clicked cell
     * Check if the piece meets the requirements to put on the cell
     * If it does, set the piece
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String coordinates = e.getActionCommand();
        int x = Integer.parseInt(coordinates.substring(0, 1));
        int y = Integer.parseInt(coordinates.substring(1));
        loc = new Location(x, y);
        setLocation(loc, board[loc.x()][loc.y()]);

        if(checkLocation(loc) && checkColor(getLocation(loc)) && !checkGameEnd(black_count, white_count)) {
            setPiece(loc, getLocation(loc));
        } else {
            System.out.println("You are not allowed to place here");
        }
    }

    /**
     * Set the location
     * @param loc location of the piece
     * @param piece the piece selected
     */
    public void setLocation(Location loc, Piece piece) {
         board[loc.x()][loc.y()] = piece;
    }

    /**
     * Get the piece corresponding to the location
     * @param loc location of the piece
     * @return Piece
     */
    public Piece getLocation(Location loc) {
        return board[loc.x()][loc.y()];
    }

    /** *
     * Check the location
     * @param loc location of the piece
     * @return true if the location is ok, otherwise return false
     */
    public boolean checkLocation(Location loc) {
        return loc.x() >= 0 && loc.x() < SIZE && loc.y() >= 0 && loc.y() < SIZE;
    }

    /**
     * Check the color
     * @param piece the piece selected
     * @return true if the color of piece is "UNSELECTED" otherwise return false
     */
    public boolean checkColor(Piece piece) {
        return piece.getPieceColor().equals("UNSELECTED");
    }

    /**
     * Get the total of counts. Check if the game ends
     * @return true if the game ends, otherwise return false
     */
    public boolean checkGameEnd(int blackCount, int whiteCount) {
        int sum = blackCount + whiteCount;
        return sum >= 64 || blackCount == 0 || whiteCount == 0;
    }

    /**
     * Update the number of each piece. Update the message.
     */
    public void updateCounts() {
        black_count = 0;
        white_count = 0;
        for(Piece[] b : board) {
            for(Piece p : b) {
                if(p.getPieceColor().equals("BLACK")) {
                    black_count++;
                } else if(p.getPieceColor().equals("WHITE")) {
                    white_count++;
                }
            }
        }
        // update message
        updateMessage(black_count, white_count);
    }

    /**
     * Update the message with updated each color counts
     * @param blackCount the number of black pieces
     * @param whiteCount the number of white pieces
     */
    public void updateMessage(int blackCount, int whiteCount) {
        message = "<html><body><br />Player1: BLACK<br /> Player2: WHITE <br /><br /> Current turn: " + turn + "<br /><br /> Black: " + blackCount + "<br /> White: " + whiteCount;
        label.setText(message);
    }

    /**
     * Check for 8 directions to see if the piece is allowed to place the grid
     * @param loc The location where the piece was placed.
     * @param piece The piece placed
     */
    public void setPiece(Location loc, Piece piece) {
        if
        (
        !checkTop(loc, piece) &&
        !checkBottom(loc, piece) &&
        !checkLeft(loc, piece) &&
        !checkRight(loc, piece) &&
        !checkTopLeftDiagonal(loc, piece) &&
        !checkTopRightDiagonal(loc, piece) &&
        !checkBottomLeftDiagonal(loc, piece) &&
        !checkBottomRightDiagonal(loc, piece)
        ) {
            System.out.println("You are not allowed to place here");
        } else {
            // swap the player
            turn = (turn.equals("Player 1")) ? "Player 2" : "Player 1";

            // swap the color
            String next_rev_stone = stone;
            stone = rev_stone;
            rev_stone = next_rev_stone;

            // update the count of each pieces
            updateCounts();
        }
    }

    /**
     * Check to see if there is a piece to turn over in the direction of upper neighbor
     * If it is, turn the piece over until the same piece as the user-selected one appears
     *
     * @param loc the location of piece the user clicks
     */
    public boolean checkTop(Location loc, Piece piece) {
        if(loc.x() == 0) return false;

        Piece next = board[loc.x() - 1][loc.y()];

        if(next.getPieceColor().equals(rev_stone)) {
            for(int i = 2; true; i++) {
                if(loc.x() - i < 0) return false;
                if(board[loc.x() - i][loc.y()].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x() - i][loc.y()].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        if(stone.equals("BLACK")) {
                            board[loc.x() - t][loc.y()].setPieceColor("BLACK");
                        } else {
                            board[loc.x() - t][loc.y()].setPieceColor("WHITE");
                        }
                    }
                    piece.setPieceColor(stone);
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public boolean checkBottom(Location loc, Piece piece) {
        if(loc.x() == 7) return false;
        Piece next = board[loc.x() + 1][loc.y()];

        if(next.getPieceColor().equals(rev_stone)) {
            for(int i = 2; true; i++) {
                if(loc.x() + i >= SIZE) return false;
                if(board[loc.x() + i][loc.y()].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x() + i][loc.y()].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        if(stone.equals("BLACK")) {
                            board[loc.x() + t][loc.y()].setPieceColor("BLACK");
                        } else {
                            board[loc.x() + t][loc.y()].setPieceColor("WHITE");
                        }
                    }
                    piece.setPieceColor(stone);
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public boolean checkLeft(Location loc, Piece piece) {
        if(loc.y() == 0) return false;
        Piece next = board[loc.x()][loc.y() - 1];

        if(next.getPieceColor().equals(rev_stone)) {
            for(int i = 2; true; i++) {
                if(loc.y() - i < 0) return false;
                if(board[loc.x()][loc.y() - i].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x()][loc.y() - i].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        if(stone.equals("BLACK")) {
                            board[loc.x()][loc.y() - t].setPieceColor("BLACK");
                        } else {
                            board[loc.x()][loc.y() - t].setPieceColor("WHITE");
                        }
                    }
                    piece.setPieceColor(stone);
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public boolean checkRight(Location loc, Piece piece) {
        if(loc.y() == 7) return false;
        Piece next = board[loc.x()][loc.y() + 1];

        if(next.getPieceColor().equals(rev_stone)) {
            for(int i = 2; true; i++) {
                if(loc.y() + i >= SIZE) return false;
                if(board[loc.x()][loc.y() + i].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x()][loc.y() + i].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        if(stone.equals("BLACK")) {
                            board[loc.x()][loc.y() + t].setPieceColor("BLACK");
                        } else {
                            board[loc.x()][loc.y() + t].setPieceColor("WHITE");
                        }
                    }
                    piece.setPieceColor(stone);
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public boolean checkTopLeftDiagonal(Location loc, Piece piece) {
        if(loc.x() == 0 || loc.y() == 0) return false;
        Piece next = board[loc.x() - 1][loc.y() - 1];

        if(next.getPieceColor().equals(rev_stone)) {
            for(int i = 2; true; i++) {
                if(loc.x() - i < 0 || loc.y() - i < 0) return false;
                if(board[loc.x() - i][loc.y() - i].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x() - i][loc.y() - i].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        if(stone.equals("BLACK")) {
                            board[loc.x()- t][loc.y() - t].setPieceColor("BLACK");
                        } else {
                            board[loc.x() - t][loc.y() - t].setPieceColor("WHITE");
                        }
                    }
                    piece.setPieceColor(stone);
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public boolean checkTopRightDiagonal(Location loc, Piece piece) {
        if(loc.x() == 0 || loc.y() == 7) return false;
        Piece next = board[loc.x() - 1][loc.y() + 1];

        if(next.getPieceColor().equals(rev_stone)) {
            for(int i = 2; true; i++) {
                if(loc.y() + i >= SIZE) return false;
                if(loc.x() - i < 0) return false;
                if(board[loc.x() - i][loc.y() + i].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x() - i][loc.y() + i].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        if(stone.equals("BLACK")) {
                            board[loc.x()- t][loc.y() + t].setPieceColor("BLACK");
                        } else {
                            board[loc.x() - t][loc.y() + t].setPieceColor("WHITE");
                        }
                    }
                    piece.setPieceColor(stone);
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public boolean checkBottomLeftDiagonal(Location loc, Piece piece) {
        if(loc.x() == 7 || loc.y() == 0) return false;
        Piece next = board[loc.x() + 1][loc.y() - 1];

        if(next.getPieceColor().equals(rev_stone)) {
            for(int i = 2; true; i++) {
                if(loc.y() - i < 0) return false;
                if(loc.x() + i >= SIZE) return false;
                if(board[loc.x() + i][loc.y() - i].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x() + i][loc.y() - i].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        if(stone.equals("BLACK")) {
                            board[loc.x() + t][loc.y() - t].setPieceColor("BLACK");
                        } else {
                            board[loc.x() + t][loc.y() - t].setPieceColor("WHITE");
                        }
                    }
                    piece.setPieceColor(stone);
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public boolean checkBottomRightDiagonal(Location loc, Piece piece) {
        if(loc.x() == 7 || loc.y() == 7) return false;
        Piece next = board[loc.x() + 1][loc.y() + 1];

        if(next.getPieceColor().equals(rev_stone)) {
            for(int i = 2; true; i++) {
                if(loc.y() + i >= SIZE) return false;
                if(loc.x() + i >= SIZE) return false;
                if(board[loc.x() + i][loc.y() + i].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x() + i][loc.y() + i].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        if(stone.equals("BLACK")) {
                            board[loc.x() + t][loc.y() + t].setPieceColor("BLACK");
                        } else {
                            board[loc.x() + t][loc.y() + t].setPieceColor("WHITE");
                        }
                    }
                    piece.setPieceColor(stone);
                    return true;
                }
            }
        } else {
            return false;
        }
    }
}
