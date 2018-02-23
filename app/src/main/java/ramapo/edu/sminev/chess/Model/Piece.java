package ramapo.edu.sminev.chess.Model;


import java.util.Vector;

public class Piece {
    public enum PieceType {PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING};
    private int m_color;
    private int m_drawableId;
    private PieceType m_type;

    public Piece(){
        m_type = null;
        m_color = 0;
    }

    public static int convertToId(int x, int y){
        return y*8+x;
    }

    public static int[] parseId(int loc){
        int coords[] = new int[2];
        coords[0] = loc%8;
        coords[1] = loc/8;
        return coords;
    }

    public int getColor(){ return m_color;}

    public void setColor(int a_color) {m_color = a_color;}


    public PieceType getType() {
        return m_type;
    }

    public void setType(PieceType a_type) {
        this.m_type = a_type;
    }


    public int getDrawableId() {
        return m_drawableId;
    }

    public void setDrawableId(int a_drawableId) {
        this.m_drawableId = a_drawableId;
    }

    public Vector<Coords> getMoves(){return new Vector<>();}

}
