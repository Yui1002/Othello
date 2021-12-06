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
    private String stone;
    private String rev_stone;
    private int black_count;
    private int white_count;
    private Player player1;
    private Player player2;
    private boolean hasGameEnded;

    /**
     * Constructor for the Othello class, set up a board
     */
    public Othello() {
        // initialize the color of piece and players
        // player 1 : BLACK
        // player 2 : WHITE
        stone = "BLACK";
        rev_stone = "WHITE";
        black_count = 2;
        white_count = 2;
        hasGameEnded = false;

        // set players (player1 is the first)
        player1 = new Player("Player1", "BLACK", 2, true);
        player2 = new Player("Player2", "WHITE", 2, false);

        // set up frame
        frame = new JFrame("Othello Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);

        panel1 = new JPanel(new FlowLayout());
        panel1.setPreferredSize(new Dimension(420, 150));

        message = "<html><body><br />Player1: " + player1.getPlayerColor() + "<br /> Player2:" + player2.getPlayerColor() + "<br /><br /> Current turn: " + (player1.getTurn() ? player1.getPlayerName() : player2.getPlayerName()) + "<br /><br /> Black: " + player1.getPoints() + "<br /> White: " + player2.getPoints();
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
        String command = e.getActionCommand();

        // Force click to change the color between the two pieces
        if(command.startsWith("doClick")) {
            int x = Integer.parseInt(command.substring(7, 8));
            int y = Integer.parseInt(command.substring(8, 9));
            String color = command.substring(9);

            board[x][y].setPieceColor(color);
            return;
        }

        int x = Integer.parseInt(command.substring(0, 1));
        int y = Integer.parseInt(command.substring(1));
        loc = new Location(x, y);
        setLocation(loc, board[loc.x()][loc.y()]);

        if(checkLocation(loc) && checkColor(getLocation(loc)) && !checkGameEnd(player1.getPoints(), player2.getPoints())) {
            setPiece(loc, getLocation(loc));
        } else {
            System.out.println("You are not allowed to put here");
        }
    }

    public JButton[][] getGrid() {
        return grid;
    }

    public Piece[][] getBoard() {
        return board;
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
    public boolean checkGameEnd(int p1Pts, int p2Pts) {
        int sum = p1Pts + p2Pts;

        return sum >= 64 || p1Pts == 0 || p2Pts == 0;
    }

    /**
     * Update the number of each piece. Update the message.
     */
    public int[] updateCounts() {
        // count from zero
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
        player1.setPoints(black_count);
        player2.setPoints(white_count);

        // update message
        updateMessage(player1.getPoints(), player2.getPoints());

        // check if the game has ended
        gameOverMessage(player1.getPoints(), player2.getPoints());

        return new int[]{player1.getPoints(), player2.getPoints()};
    }

    /**
     * Display a game over message
     * @param p1Pts the points of player 1
     * @param p2Pts the points of player 2
     */
    public String gameOverMessage(int p1Pts, int p2Pts) {
        if(!checkGameEnd(p1Pts, p2Pts)) return null;

        if(p1Pts == p2Pts) {
            message = "<html><body><br /> Draw";
        } else if(p1Pts > p2Pts) {
            message = "<html><body><br />" + player1.getPlayerName() + " won!";
        } else {
            message = "<html><body><br />" + player2.getPlayerName() + " won!";
        }
        label.setText(message);
        return message;
    }

    /**
     * Update the message with updated each color counts
     * @param p1Pts the number of black pieces
     * @param p2Pts the number of white pieces
     */
    public String updateMessage(int p1Pts, int p2Pts) {
        message = "<html><body><br />Player1: BLACK<br /> Player2: WHITE <br /><br /> Current turn: " + (player1.getTurn() ? player1.getPlayerName() : player2.getPlayerName()) + "<br /><br /> Black: " + p1Pts + "<br /> White: " + p2Pts;
        label.setText(message);
        return message;
    }

    /**
     * Check for 8 directions to see if the piece is allowed to put on the grid
     * @param loc The location where the piece was placed.
     * @param piece The piece placed
     */
    public void setPiece(Location loc, Piece piece) {

        boolean resultTop = checkTop(loc, piece);
        boolean resultBottom = checkBottom(loc, piece);
        boolean resultLeft = checkLeft(loc, piece);
        boolean resultRight = checkRight(loc, piece);
        boolean resultTopLeftDiagonal = checkTopLeftDiagonal(loc, piece);
        boolean resultTopRightDiagonal = checkTopRightDiagonal(loc, piece);
        boolean resultBottomLeftDiagonal = checkBottomLeftDiagonal(loc, piece);
        boolean resultBottomRightDiagonal = checkBottomRightDiagonal(loc, piece);

        if(!resultTop && !resultBottom && !resultLeft && !resultRight && !resultTopLeftDiagonal && !resultTopRightDiagonal && !resultBottomLeftDiagonal && !resultBottomRightDiagonal) {
            System.out.println("You are not allowed to put here");
        } else {
            // swap the player
            player1.setTurn(!player1.getTurn());
            player2.setTurn(!player2.getTurn());

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
                        int locX = loc.x() - t;
                        grid[loc.x() - t][loc.y()].setActionCommand("doClick" + locX + loc.y() + stone);
                        grid[loc.x() - t][loc.y()].doClick();
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
                        int locX = loc.x() + t;
                        grid[loc.x() + t][loc.y()].setActionCommand("doClick" + locX + loc.y() + stone);
                        grid[loc.x() + t][loc.y()].doClick();
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
                        int locY = loc.y() - t;
                        grid[loc.x()][loc.y() - t].setActionCommand("doClick" + loc.x() + locY + stone);
                        grid[loc.x()][loc.y() - t].doClick();
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
                        int locY = loc.y() + t;
                        grid[loc.x()][loc.y() + t].setActionCommand("doClick" + loc.x() + locY + stone);
                        grid[loc.x()][loc.y() + t].doClick();
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
                        int locX = loc.x() - t, locY = loc.y() - t;
                        grid[loc.x() - t][loc.y() - t].setActionCommand("doClick" + locX + locY + stone);
                        grid[loc.x() - t][loc.y() - t].doClick();
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
                if(loc.y() + i >= SIZE || loc.x() - i < 0) return false;
                if(board[loc.x() - i][loc.y() + i].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x() - i][loc.y() + i].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        int locX = loc.x() - t, locY = loc.y() + t;
                        grid[loc.x() - t][loc.y() + t].setActionCommand("doClick" + locX + locY + stone);
                        grid[loc.x() - t][loc.y() + t].doClick();
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
                if(loc.y() - i < 0 || loc.x() + i >= SIZE) return false;
                if(board[loc.x() + i][loc.y() - i].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x() + i][loc.y() - i].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        int locX = loc.x() + t, locY = loc.y() - t;
                        grid[loc.x() + t][loc.y() - t].setActionCommand("doClick" + locX + locY + stone);
                        grid[loc.x() + t][loc.y() - t].doClick();
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
                if(loc.y() + i >= SIZE || loc.x() + i >= SIZE) return false;
                if(board[loc.x() + i][loc.y() + i].getPieceColor().equals("UNSELECTED")) return false;

                if(board[loc.x() + i][loc.y() + i].getPieceColor().equals(stone)) {
                    for(int t = 1; t < i; t++) {
                        int locX = loc.x() + t, locY = loc.y() + t;
                        grid[loc.x() + t][loc.y() + t].setActionCommand("doClick" + locX + locY + stone);
                        grid[loc.x() + t][loc.y() + t].doClick();
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