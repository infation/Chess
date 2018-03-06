package ramapo.edu.sminev.chess.Model;


import android.widget.Toast;

import java.util.Vector;

import ramapo.edu.sminev.chess.View.BoardView;

public class GameState {


    private static Player[] players;
    private static Piece board[][];
    private static int turn;
    private static boolean isCheck;
    private static Piece simulatedMove = null;

    public static void setIsCheck(boolean a_isCheck){
        isCheck = a_isCheck;
    }

    public static boolean isCheck(){
        return isCheck;
    }

    public static void initializeGame(){
        players = new Player[2];
        players[0] = new Human();
        players[1] = new Computer();
        board = new Piece[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = null;
            }
        }
        turn = 1;
        initializePieces();
        isCheck = false;
    }


    public static Piece[][] getBoard(){
        return board;
    }
    public static int getTurn(){ return turn; }
    public static void switchTurn() {
        if(turn == 0){
            turn = 1;
        }
        else{
            turn = 0;
        }
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
        if(checkIfCastlingMove(oldLoc, newLoc)) {
            switchTurn();
            setIsCheck(isKingInCheck());
            return;
        }
        Vector<Location> moves = board[oldLoc.row][oldLoc.col].getPredefinedMoves(oldLoc);
        board[oldLoc.row][oldLoc.col].simulateMoves(moves, oldLoc);
        for(int i = 0; i < moves.size(); i++){
            if(newLoc.row == moves.get(i).row && newLoc.col == moves.get(i).col){
                if(board[oldLoc.row][oldLoc.col] != null) {
                    board[oldLoc.row][oldLoc.col].setMoved();
                    if(board[newLoc.row][newLoc.col]!=null){
                        players[board[newLoc.row][newLoc.col].getColor()].addPiece(board[newLoc.row][newLoc.col]);
                    }
                    board[newLoc.row][newLoc.col] = board[oldLoc.row][oldLoc.col];
                    board[oldLoc.row][oldLoc.col] = null;
                    switchTurn();
                    //BoardView.clearView(a_oldLoc.convertToId());
                    //BoardView.updateMove();
                    BoardView.update(oldLoc, newLoc);
                    setIsCheck(isKingInCheck());
                }
            }
        }
    }

    public static boolean checkIfCastlingMove(Location oldLoc, Location newLoc){
        if(board[oldLoc.row][oldLoc.col]!=null && board[oldLoc.row][oldLoc.col].getType()== Piece.PieceType.KING){
            Vector<Location> moves = board[oldLoc.row][oldLoc.col].getPredefinedMoves(oldLoc);
            for(int i = 0; i < moves.size(); i++) {
                if(moves.get(i).row == newLoc.row && moves.get(i).col == newLoc.col) {
                    if (oldLoc.row == newLoc.row) {
                        if (oldLoc.col + 2 == newLoc.col) {
                            board[oldLoc.row][oldLoc.col].setMoved();
                            board[newLoc.row][newLoc.col] = board[oldLoc.row][oldLoc.col];
                            board[oldLoc.row][oldLoc.col + 1] = board[oldLoc.row][7];
                            board[oldLoc.row][oldLoc.col] = null;
                            board[oldLoc.row][7] = null;
                            BoardView.updateAll();
                            BoardView.clearLocation(oldLoc);
                            BoardView.clearLocation(new Location(oldLoc.row, 7));
                            return true;
                        }
                        if (oldLoc.col - 2 == newLoc.col) {
                            board[oldLoc.row][oldLoc.col].setMoved();
                            board[newLoc.row][newLoc.col] = board[oldLoc.row][oldLoc.col];
                            board[oldLoc.row][oldLoc.col - 1] = board[oldLoc.row][0];
                            board[oldLoc.row][oldLoc.col] = null;
                            board[oldLoc.row][0] = null;
                            BoardView.updateAll();
                            BoardView.clearLocation(oldLoc);
                            BoardView.clearLocation(new Location(oldLoc.row, 0));
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    public static void startSimulation(Location oldLoc, Location newLoc) {
        //if(board[oldLoc.row][oldLoc.col] != null) {
        board[newLoc.row][newLoc.col] = board[oldLoc.row][oldLoc.col];
        board[oldLoc.row][oldLoc.col] = null;
    }

    public static void endSimulation(Location oldLoc, Location newLoc, Piece a_piece){
        board[oldLoc.row][oldLoc.col] = board[newLoc.row][newLoc.col];
        board[newLoc.row][newLoc.col] = a_piece;
    }

    public static boolean isKingInCheck(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j]!=null&& board[i][j].getType()== Piece.PieceType.KING && board[i][j].getColor()==turn ) {
                    return ((King)board[i][j]).isInCheck(new Location(i,j));
                    //System.out.println(check);
                }
            }
        }
        return false;
    }

    public static boolean isCheckMate(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j]!=null && board[i][j].getColor()==turn ) {
                    Vector<Location> moves =board[i][j].getPredefinedMoves(new Location(i,j));
                    for(int k = 0; k<moves.size();k++){
                        board[i][j].simulateMoves(moves, new Location(i,j));
                    }
                    if(moves.size()!=0){
                        return false;
                    }
                    //return ((King)board[i][j]).isInCheck(new Location(i,j));
                    //System.out.println(check);
                }
            }
        }
        return true;
    }

    /*public static boolean isPromotionMove(Location oldLoc, Location newLoc){
        return true;
    }*/


    public static Vector<Location> getPieceMovesUnderCheck(Location a_loc){
        Vector<Location> moves =board[a_loc.row][a_loc.col].getPredefinedMoves(a_loc);
        board[a_loc.row][a_loc.col].simulateMoves(moves, a_loc);
        return moves;
    }

    public static boolean isCorrectSelection(Location a_loc){
        if(board[a_loc.row][a_loc.col]!=null&&board[a_loc.row][a_loc.col].getColor()==turn){
            if(isCheck){
                if(getPieceMovesUnderCheck(a_loc).size()==0){
                    return false;
                }
                return true;
            }
            return true;
        }
        return false;
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


    public static Player[] getPlayers(){
        return players;
    }
/*
    public Piece getSquare(int x, int y){
        return board[x][y];
    }

    public void checkMoves(){

    }*/
}
