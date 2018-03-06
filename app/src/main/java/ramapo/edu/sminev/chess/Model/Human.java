package ramapo.edu.sminev.chess.Model;


import java.util.Vector;

public class Human extends Player{

    public Human(){
        super();
        //initializePieces();
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
            Pawn p = new Pawn(1);
            p.setCoords(6,i);
            addPiece(p);
        }
    }

    public void initializeQueen(){
        Queen q = new Queen(1);
        q.setCoords(7,3);
        addPiece(q);
    }

    public void initializeKing(){
        King k = new King(1);
        k.setCoords(7,4);
        addPiece(k);
    }

    public void initializeBishops(){
        Bishop b = new Bishop(1);
        b.setCoords(7,2);
        addPiece(b);
        b = new Bishop(1);
        b.setCoords(7,5);
        addPiece(b);
    }

    public void initializeKnights(){
        Knight k = new Knight(1);
        k.setCoords(7,1);
        addPiece(k);
        k = new Knight(1);
        k.setCoords(7,6);
        addPiece(k);
    }

    public void initializeRooks(){
        Rook r = new Rook(1);
        r.setCoords(7,0);
        addPiece(r);
        r = new Rook(1);
        r.setCoords(7,7);
        addPiece(r);
    }

    public void checkMove(int id){

    }*/
}
