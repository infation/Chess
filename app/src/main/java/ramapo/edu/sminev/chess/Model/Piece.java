package ramapo.edu.sminev.chess.Model;



public class Piece {
    public enum PieceType {PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING};
    private int m_xposition;
    private int m_yposition;
    private int m_color;
    private int m_drawableId;
    private PieceType m_type;

    public Piece(){
        m_xposition = 0;
        m_yposition = 0;
        m_type = null;
        m_color = 0;
    }

    public Piece(int a_xposition, int a_yposition, int a_type){
        m_xposition = a_xposition;
        m_yposition = a_yposition;

    }

    public int getColor(){ return m_color;}

    public void setColor(int a_color) {m_color = a_color;}

    public int getXpos() {
        return m_xposition;
    }

    public void setXpos(int a_xposition) {
        m_xposition = a_xposition;
    }

    public int getYpos() {
        return m_yposition;
    }

    public void setYpos(int a_yposition) {
        m_yposition = a_yposition;
    }

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

}
