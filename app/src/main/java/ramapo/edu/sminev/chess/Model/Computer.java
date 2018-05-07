package ramapo.edu.sminev.chess.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import ramapo.edu.sminev.chess.View.BoardView;


public class Computer extends Player {

    public Computer() {
        super();
    }

    /**/
    /*
    public double evaluteScore();

    NAME
            evaluateScore() - evaluates the state of the board.

    SYNOPSIS
            double evaluateScore();

    DESCRIPTION

            This heuristic function evaluates the state of the board.
            It takes 5 things in account. How many of each type of pieces are in each player
            The number of isolated, doubled and block pawns for each player. And the number of legal
            moves.


    RETURNS

            The evaluated score for the state of the board.

    AUTHOR

            Stanislav Minev

    DATE

            4/20/2018

    */
    /**/
    public double evaluateScore() {
        //Get the number of current pieces in a hashmap
        Vector<Map<String, Integer>> pieces = getCurrentPieces();
        int turn = GameState.getTurn();
        int oppositeTurn = GameState.getOppositeTurn();
        double score = //Calculate the number of kings
                9 * (pieces.get(turn).get("QUEEN") - pieces.get(oppositeTurn).get("QUEEN")) + //Calculate the number of queens
                5 * (pieces.get(turn).get("ROOK") - pieces.get(oppositeTurn).get("ROOK")) + //Calculate the number of rooks
                3 * (pieces.get(turn).get("BISHOP") - pieces.get(oppositeTurn).get("BISHOP") + //Calculate the number of bishops
                        pieces.get(turn).get("KNIGHT") - pieces.get(oppositeTurn).get("KNIGHT")) + //Calculate the number of knights
                pieces.get(turn).get("PAWN") - pieces.get(oppositeTurn).get("PAWN") + //Calculate the number of pawns
                0.5 * (getNumIsolatedPawns(turn) - getNumIsolatedPawns(oppositeTurn) + //Calculate the number of isolated pawns
                        getNumDoubledPawns(turn) - getNumDoubledPawns(oppositeTurn) + //Calculate the number of doubled pawns
                        getNumBlockedPawns(turn) - getNumBlockedPawns(oppositeTurn)) + //Calculate the number of blocked pawns
                0.1 * (getNumLegalMoves(turn) - getNumLegalMoves(oppositeTurn)); //Calculate the number of legal moves
        return score;
    }

    /**/
    /*
    public int getNumLegalMoves

    NAME
            getNumLegalMoves(int color) - to get the number of legal moves.

    SYNOPSIS
            double getNumLegalMoves(int color);
                int color -> the color of the pieces, white for human, black for computer

    DESCRIPTION
            Calculates how many available moves are there for a player
            in the current state of the board.


    RETURNS
            The number of legal moves for a player.

    AUTHOR

            Stanislav Minev

    DATE

            4/20/2018

    */
    /**/
    public int getNumLegalMoves(int color) {
        int moves = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameState.getBoard()[i][j] != null && GameState.getBoard()[i][j].getColor() == color) {
                    moves += GameState.getNumPieceMoves(new Location(i, j));
                }
            }
        }
        return moves;
    }


    /**/
    /*
    public int getNumDoubledPawns

    NAME
            getNumDoubledPawns - get the number of doubled pawns.

    SYNOPSIS
            double getNumDoubledPawns(int color);
                int color -> the color of the pieces, white for human, black for computer

    DESCRIPTION
            Calculates how many doubled pawns are there for a player
            in the current state of the board.


    RETURNS
            The number of doubled pawns for a player.

    AUTHOR
            Stanislav Minev

    DATE

            4/20/2018

    */
    /**/
    public int getNumDoubledPawns(int color) {
        int numPawns = 0;
        boolean isDoubled;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //Assume that the pawn is not doubled
                if (GameState.getBoard()[i][j] != null && GameState.getBoard()[i][j].getType() == Piece.PieceType.PAWN && GameState.getBoard()[i][j].getColor() == color) {
                    isDoubled = false;
                    //Only check the column of the pawn
                    for (int row = 0; row < 8; row++) {
                        //Ignore the case of the same pawn being considered for the doubling rule
                        if (row == i) continue;
                        //If there is another pawn on the same column of same color, then the pawn is doubled
                        if (GameState.getBoard()[row][j] != null && GameState.getBoard()[row][j].getType() == Piece.PieceType.PAWN && GameState.getBoard()[row][j].getColor() == color)
                            isDoubled = true;
                    }
                    //Increase the number of doubled pawns
                    if (isDoubled) numPawns++;
                }
            }
        }
        return numPawns;
    }

    /**/
    /*
    public int getNumBlockedPawns

    NAME
            getNumBlockedPawns - get the number of blocked pawns.

    SYNOPSIS
            double getNumBlockedPawns(int color);
                int color -> the color of the pieces, white for human, black for computer

    DESCRIPTION
            Calculates how many blocked pawns are there for a player
            in the current state of the board.


    RETURNS
            The number of blocked pawns for a player.

    AUTHOR
            Stanislav Minev

    DATE
            4/20/2018

    */
    /**/
    public int getNumBlockedPawns(int color) {
        int numPawns = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameState.getBoard()[i][j] != null && GameState.getBoard()[i][j].getType() == Piece.PieceType.PAWN && GameState.getBoard()[i][j].getColor() == color) {
                    if (color == 1) {
                        //Don't check for out of bounds
                        if (i - 1 < 0) continue;
                        //Check if there is a piece in front that blocks the pawn
                        if (GameState.getBoard()[i - 1][j] != null)
                            numPawns++;
                    } else {
                        //Dont check fo out of bounds
                        if (i + 1 > 7) continue;
                        //Check if there is a piece in front that blocks the pawn
                        if (GameState.getBoard()[i + 1][j] != null)
                            numPawns++;
                    }
                }
            }
        }
        return numPawns;
    }

    /**/
    /*
    public int getNumIsolatedPawns

    NAME
            getNumIsolatedPawns - get the number of isolated pawns.

    SYNOPSIS
            double getNumIsolateddPawns(int color);
                int color -> the color of the pieces, white for human, black for computer

    DESCRIPTION
            Calculates how many isolated pawns are there for a player
            in the current state of the board.


    RETURNS
            The number of isolated pawns for a player.

    AUTHOR
            Stanislav Minev

    DATE
            4/20/2018

    */
    /**/
    public int getNumIsolatedPawns(int color) {
        int numPawns = 0;
        boolean isIsolated;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //Assume the pawn is isolated
                if (GameState.getBoard()[i][j] != null && GameState.getBoard()[i][j].getType() == Piece.PieceType.PAWN && GameState.getBoard()[i][j].getColor() == color) {
                    isIsolated = true;
                    for (int row = i - 1; row < i + 2; row++) {
                        for (int col = j - 1; col < j + 2; col++) {
                            //Don't Check for out of bound pieces
                            if (row < 0 || row > 7 || col < 0 || col > 7 || (row == i && col == j))
                                continue;
                            //If there is pawn in the vicinity, then the pawn is not isolated
                            if (GameState.getBoard()[row][col] != null && GameState.getBoard()[row][col].getType() == Piece.PieceType.PAWN
                                    && GameState.getBoard()[row][col].getColor() == color)
                                isIsolated = false;
                        }
                    }
                    //Increase the num of isolated pawns
                    if (isIsolated) numPawns++;
                }
            }
        }

        return numPawns;
    }

    /**/
    /*
    public Vector<Map<String,Integer>> getCurrentPieces();

    NAME
            getCurrentPieces - get the current pieces for each type of piece.

    SYNOPSIS
            Vector<Map<String, Integer>> getCurrentPieces();

    DESCRIPTION
            A function to get the current pieces with a hashmap that maps
            piece types to the number of pieces of this type. Used for the evaluation
            function for the computer startegy.


    RETURNS
            A vector of 2 hashmaps, each of which maps
            piece types to the number of pieces of this type for the specific color.

    AUTHOR
            Stanislav Minev

    DATE

            4/20/2018

    */
    /**/
    public Vector<Map<String, Integer>> getCurrentPieces() {
        //Initialize the vector and maps
        Vector<Map<String, Integer>> pieces = new Vector<>();
        pieces.add(new HashMap<String, Integer>());
        pieces.add(new HashMap<String, Integer>());
        //Initialize the values in the hashmaps
        for (int i = 0; i < 2; i++) {
            pieces.get(i).put("PAWN", 0);
            pieces.get(i).put("QUEEN", 0);
            pieces.get(i).put("KING", 0);
            pieces.get(i).put("BISHOP", 0);
            pieces.get(i).put("KNIGHT", 0);
            pieces.get(i).put("ROOK", 0);
        }
        //Go through the board and increase the values
        // if a piece of that color and type was found
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameState.getBoard()[i][j] != null) {
                    int color = GameState.getBoard()[i][j].getColor();
                    pieces.get(color).put(GameState.getBoard()[i][j].getType().toString(), pieces.get(color).get(GameState.getBoard()[i][j].getType().toString()) + 1);
                }
            }
        }
        return pieces;
    }


    public void play() {
        double maxScore;
        Location bestNewLoc = new Location();
        Location bestOldLoc = new Location();
        /*for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameState.getBoard()[i][j] != null && GameState.getTurn() == GameState.getBoard()[i][j].getColor()) {
                    Vector<Location> moves = GameState.getPieceMovesUnderCheck(new Location(i, j));
                    for (int n = 0; n < moves.size(); n++) {
                        Piece p = GameState.getBoard()[moves.get(n).row][moves.get(n).col];
                        GameState.startSimulation(new Location(i, j), moves.get(n));
                        double score = evaluateScore();
                        if (score > maxScore) {
                            bestOldLoc = new Location(i, j);
                            bestNewLoc = moves.get(n);
                            maxScore = score;
                        }
                        GameState.endSimulation(new Location(i, j), moves.get(n), p);
                    }
                }
            }
        }*/
        maxScore = minimax(bestOldLoc, bestNewLoc, 2);

        if (bestOldLoc.row == -1) {
            System.out.println("Checkmate or draw");
            return;
        }

        GameState.updateState(bestOldLoc, bestNewLoc);
        BoardView.showComputerMove(bestOldLoc, bestNewLoc);
    }

    public double minimax(Location oldLoc, Location newLoc, int depth){
        //Base case
        if(depth == 0){
            return evaluateScore();
        }
        double maxScore = Integer.MIN_VALUE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameState.getBoard()[i][j] != null && GameState.getTurn() == GameState.getBoard()[i][j].getColor()) {
                    Vector<Location> moves = GameState.getPieceMovesUnderCheck(new Location(i, j));
                    for (int n = 0; n < moves.size(); n++) {
                        Piece p = GameState.getBoard()[moves.get(n).row][moves.get(n).col];
                        GameState.startSimulation(new Location(i, j), moves.get(n));
                        /*if(GameState.getTurn() == 0){
                            GameState.switchTurn();
                            double score = minimax(new Location(i, j), moves.get(n), depth--, maxScore);
                            if (score > maxScore) {
                                oldLoc = new Location(i, j);
                                newLoc = moves.get(n);
                                maxScore = score;
                            }
                        }
                        else{
                            GameState.switchTurn();
                            double score = minimax(new Location(i, j), moves.get(n), depth--, maxScore);
                            if (score > maxScore) {
                                oldLoc = new Location(i, j);
                                newLoc = moves.get(n);
                                maxScore = score;
                            }

                        }*/
                        GameState.switchTurn();
                        double score = -minimax(new Location(), new Location(), depth-1);
                        if (score > maxScore) {
                            oldLoc.row = i;
                            oldLoc.col = j;
                            newLoc.row = moves.get(n).row;
                            newLoc.col = moves.get(n).col;
                            maxScore = score;
                        }
                        GameState.switchTurn();
                        GameState.endSimulation(new Location(i, j), moves.get(n), p);
                    }
                }
            }
        }
        return maxScore;
    }
}
