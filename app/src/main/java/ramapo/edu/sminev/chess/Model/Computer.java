package ramapo.edu.sminev.chess.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;


public class Computer extends Player {

    public Computer(){
        super();
        //initializePieces();
    }

    public double evaluateScore(){
        Vector<Map<String, Integer>> pieces = getCurrentPieces();
        int turn = GameState.getTurn();
        int oppositeTurn = GameState.getOppositeTurn();
        double score = 200*(pieces.get(turn).get("KING") - pieces.get(oppositeTurn).get("KING"))+
                9*(pieces.get(turn).get("QUEEN") - pieces.get(oppositeTurn).get("QUEEN"))+
                5*(pieces.get(turn).get("ROOK") - pieces.get(oppositeTurn).get("ROOK"))+
                3*(pieces.get(turn).get("BISHOP") - pieces.get(oppositeTurn).get("BISHOP") +
                        pieces.get(turn).get("KNIGHT") - pieces.get(oppositeTurn).get("KNIGHT"))+
                        pieces.get(turn).get("PAWN") - pieces.get(oppositeTurn).get("PAWN") +
                0.5*(getNumIsolatedPawns(turn) - getNumIsolatedPawns(oppositeTurn)+
                        getNumDoubledPawns(turn) - getNumDoubledPawns(oppositeTurn)+
                        getNumBlockedPawns(turn) - getNumBlockedPawns(oppositeTurn)) +
                0.1*(getNumLegalMoves(turn) - getNumLegalMoves(oppositeTurn));
        return score;
    }

    public int getNumLegalMoves(int color){
        int moves = 0;
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameState.getBoard()[i][j] != null && GameState.getBoard()[i][j].getColor() == color) {
                    moves += GameState.getNumPieceMoves(new Location(i, j));
                }
            }
        }
        return moves;
    }

    public void play(){
        double maxScore = -3213213;
        Location bestNewLoc = null;
        Location bestOldLoc = null;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(GameState.getBoard()[i][j]!=null && GameState.getTurn() == GameState.getBoard()[i][j].getColor()) {
                    Vector<Location> moves = GameState.getPieceMovesUnderCheck(new Location(i,j));
                    for(int n = 0; n < moves.size(); n++){
                        Piece p = GameState.getBoard()[moves.get(n).row][moves.get(n).col];
                        GameState.startSimulation(new Location(i, j), moves.get(n));
                        double score = evaluateScore();
                        if(score > maxScore){
                            bestOldLoc = new Location(i, j);
                            bestNewLoc = moves.get(n);
                            maxScore = score;
                        }
                        GameState.endSimulation(new Location(i, j), moves.get(n), p);
                    }
                }
            }
        }

        if(bestOldLoc == null){
            System.out.println("Checkmate or draw");
            return;
        }

        System.out.println("MAX SCORE:  " + maxScore);
        GameState.updateState(bestOldLoc, bestNewLoc);
    }

    public int getNumDoubledPawns(int color){
        int numPawns = 0;
        boolean isDoubled;
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getType() == Piece.PieceType.PAWN &&GameState.getBoard()[i][j].getColor() == color){
                    isDoubled = false;
                    for(int row = 0 ; row < 8; row++) {
                        if(row == i) continue;
                        if(GameState.getBoard()[row][j]!=null&& GameState.getBoard()[row][j].getType() == Piece.PieceType.PAWN &&GameState.getBoard()[row][j].getColor() == color)
                           isDoubled = true;
                    }
                    if(isDoubled) numPawns++;
                }
            }
        }

        return numPawns;
    }

    public int getNumBlockedPawns(int color){
        int numPawns = 0;
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getType() == Piece.PieceType.PAWN &&GameState.getBoard()[i][j].getColor() == color){
                    if(color == 1) {
                        if(i - 1 < 0) continue;
                        if (GameState.getBoard()[i-1][j] != null)
                            numPawns++;
                    }
                    else{
                        if(i + 1 > 7) continue;
                        if (GameState.getBoard()[i+1][j] != null)
                            numPawns++;
                    }
                }
            }
        }
        return numPawns;
    }

    public int getNumIsolatedPawns(int color){
        int numPawns = 0;
        boolean isIsolated;
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(GameState.getBoard()[i][j]!=null && GameState.getBoard()[i][j].getType() == Piece.PieceType.PAWN &&GameState.getBoard()[i][j].getColor() == color){
                    isIsolated = true;
                    for(int row = i-1; row < i+2; row++) {
                        for (int col = j - 1; col < j + 2; col++){
                            if(row < 0 || row > 7 || col < 0 || col > 7 || (row==i&&col==j)) continue;
                            if(GameState.getBoard()[row][col]!=null&& GameState.getBoard()[row][col].getType() == Piece.PieceType.PAWN &&GameState.getBoard()[row][col].getColor() == color)
                                isIsolated = false;
                        }
                    }
                    if(isIsolated) numPawns++;
                }
            }
        }

        return numPawns;
    }

    public Vector<Map<String, Integer>> getCurrentPieces(){

        Vector<Map<String, Integer>> pieces = new Vector<>();
        pieces.add(new HashMap<String, Integer>());
        pieces.add(new HashMap<String, Integer>());
        for(int i = 0; i < 2; i++){
            pieces.get(i).put("PAWN", 0);
            pieces.get(i).put("QUEEN", 0);
            pieces.get(i).put("KING", 0);
            pieces.get(i).put("BISHOP", 0);
            pieces.get(i).put("KNIGHT", 0);
            pieces.get(i).put("ROOK", 0);
        }
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(GameState.getBoard()[i][j]!=null) {
                    int color = GameState.getBoard()[i][j].getColor();
                    pieces.get(color).put(GameState.getBoard()[i][j].getType().toString(), pieces.get(color).get(GameState.getBoard()[i][j].getType().toString())+1);
                }
            }
        }
        return pieces;
    }

    /*
    public void initializePieces(){
        initializePawns();
        initializeRooks();
        initializeBishops();
        initializeKing();
        initializeKnights();
        initializeQueen();
    }

    public void initializePawns(){
        for(int i= 0; i < 8; i++){
            Pawn p = new Pawn(0);
            p.setCoords(1,i);
            addPiece(p);
        }
    }

    public void initializeQueen(){
        Queen q = new Queen(0);
        q.setCoords(0,3);
        addPiece(q);
    }

    public void initializeKing(){
        King k = new King(0);
        k.setCoords(0,4);
        addPiece(k);
    }

    public void initializeBishops(){
        Bishop b = new Bishop(0);
        b.setCoords(0,2);
        addPiece(b);
        b = new Bishop(0);
        b.setCoords(0,5);
        addPiece(b);
    }

    public void initializeKnights(){
        Knight k = new Knight(0);
        k.setCoords(0,1);
        addPiece(k);
        k = new Knight(0);
        k.setCoords(0,6);
        addPiece(k);
    }

    public void initializeRooks(){
        Rook r = new Rook(0);
        r.setCoords(0,0);
        addPiece(r);
        r = new Rook(0);
        r.setCoords(0,7);
        addPiece(r);
    }*/
}
