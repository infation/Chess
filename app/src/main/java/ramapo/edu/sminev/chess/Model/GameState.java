package ramapo.edu.sminev.chess.Model;


import java.util.Vector;

import ramapo.edu.sminev.chess.View.BoardView;

public class GameState {


    //private static Player[] players;
    private static Piece board[][];

    public static void initializeGame(){
        //players = new Player[2];
        //players[0] = new Human();
        //players[1] = new Computer();
        board = new Piece[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = null;
            }
        }
        initializePieces();
    }


    public static Piece[][] getBoard(){
        return board;
    }

    /*public void updateState(int a_oldId, int a_newId){

        for(int i = 0; i < players[0].getPieces().size();i++){
            if(players[0].getPieces().get(i).getCoords().convertToId() == a_oldId){
                players[0].getPieces().get(i).setCoords(Coords.parseId(a_newId));
                BoardView.clearView(a_oldId);
                BoardView.updateView(players);
            }
        }
    }*/

    public static void updateState(Location oldLoc, Location newLoc){
        BoardView.clearMoves(oldLoc);
        Vector<Location> moves = board[oldLoc.row][oldLoc.col].getPredefinedMoves(oldLoc);
        for(int i = 0; i < moves.size(); i++){
            if(newLoc.row == moves.get(i).row && newLoc.col == moves.get(i).col){
                if(board[oldLoc.row][oldLoc.col] != null) {
                    board[newLoc.row][newLoc.col] = board[oldLoc.row][oldLoc.col];
                    board[oldLoc.row][oldLoc.col] = null;
                    //BoardView.clearView(a_oldLoc.convertToId());
                    //BoardView.updateMove();
                    BoardView.update(oldLoc, newLoc);
                }
            }
        }
    }
    private static void initializePieces(){
        initializePawns();
        initializeRooks();
        initializeBishops();
        initializeKings();
        initializeKnights();
        initializeQueens();
    }

    private static void initializePawns(){
        for(int i= 0; i < 8; i++){
            board[1][i] = new Pawn(0, 1, i);
            board[6][i] = new Pawn(1, 6, i);
        }
    }

    private static void initializeQueens(){
        board[0][3] = new Queen(0,0,3);
        board[7][3] = new Queen(1, 7,3);
    }

    private static void initializeKings(){
        board[0][4] = new King(0,0,4);
        board[7][4] = new King(1,7,4);
    }

    private static void initializeBishops(){
        board[0][2] = new Bishop(0,0,2);
        board[0][5] = new Bishop(0,0,5);
        board[7][2] = new Bishop(1,7,2);
        board[7][5] = new Bishop(1,7,5);
    }

    private static void initializeKnights(){
        board[0][1] = new Knight(0,0,1);
        board[0][6] = new Knight(0,0,6);
        board[7][1] = new Knight(1,7,1);
        board[7][6] = new Knight(1,7,6);
    }

    private static void initializeRooks(){
        board[0][0] = new Rook(0,0,0);
        board[0][7] = new Rook(0,0,7);
        board[7][0] = new Rook(1,7,0);
        board[7][7] = new Rook(1,7,7);
    }
/*
    public Piece getSquare(int x, int y){
        return board[x][y];
    }

    public void checkMoves(){

    }*/
}
