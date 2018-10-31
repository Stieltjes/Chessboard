/**
 * <p>This is how the board is broken down to 8x8 squares</p>
 *
 * @author Benjamin Wang, Set B
 * @version 1.0
 */
import java.io.*;

public class ChessBoard implements java.io.Serializable{
    private Squares[][] squarely = new Squares[8][8];
    private int selected, turn;

    
    /**
    * <p>This is the constructor, with the pieces distributed at move 0 of a game.</p>
    */
    public ChessBoard(){
        turn = 1;
        selected = 0;
        squarely[0][0] = new Squares(Pieces.Rook, 1);
        squarely[0][1] = new Squares(Pieces.Knight,1);
        squarely[0][2] = new Squares(Pieces.Bishop,1);
        squarely[0][3] = new Squares(Pieces.King,1);
        squarely[0][4] = new Squares(Pieces.Queen,1);
        squarely[0][5] = new Squares(Pieces.Bishop,1);
        squarely[0][6] = new Squares(Pieces.Knight,1);
        squarely[0][7] = new Squares(Pieces.Rook,1);
        for (int i=0; i<8; i++) squarely[1][i] = new Squares(Pieces.Pawn,1);
        squarely[7][0] = new Squares(Pieces.Rook, 0);
        squarely[7][1] = new Squares(Pieces.Knight,0);
        squarely[7][2] = new Squares(Pieces.Bishop,0);
        squarely[7][3] = new Squares(Pieces.King,0);
        squarely[7][4] = new Squares(Pieces.Queen,0);
        squarely[7][5] = new Squares(Pieces.Bishop,0);
        squarely[7][6] = new Squares(Pieces.Knight,0);
        squarely[7][7] = new Squares(Pieces.Rook,0);
        for (int i=0; i<8; i++) squarely[6][i] = new Squares(Pieces.Pawn,0);
        for (int i=2; i<6;i++){
            for (int j=0; j<8;j++) squarely[i][j] = new Squares();
        }
    }

    /**
    * <p>This returns the piece at a given location.</p>
    *
    * @param x,y
    */
    public Pieces getpiece(int x, int y){
        return squarely[x][y].getstate();
    }    
    
    /**
    * <p>This shows whose piece it is at a given location.</p>
    *
    * @param x,y
    */
    public int getplayer(int x, int y){
        return squarely[x][y].getplayer();
    }

    /**
    * <p>This sets the piece at a given location.</p>
    *
    * @param x,y
    */    
    public void setpiece(int x, int y, Pieces newpiece){
        squarely[x][y].setstate(newpiece);
    }
    
    /**
    * <p>This sets whose piece it is at a given location.</p>
    *
    * @param x,y
    */

    public void setplayer(int x, int y, int newplayer){
        squarely[x][y].setplayer(newplayer);
    }

    /**
    * <p>This displays the piece as a letter.</p>
    *
    * @param x,y
    */
    public String display(int x, int y){
        if (this.getpiece(x, y)==Pieces.Rook) return "R";
        else if (this.getpiece(x,y)==Pieces.Knight) return "H";
        else if (this.getpiece(x,y)==Pieces.Bishop) return "B";
        else if (this.getpiece(x,y)==Pieces.King) return "K";
        else if (this.getpiece(x,y)==Pieces.Queen) return "Q";
        else if (this.getpiece(x,y)==Pieces.Pawn) return "P";
        else return "";
    }

    /**
    * <p>This sets the turn.</p>
    *
    * @param turnnum
    */    
    public void setturn(int turnnum){
        turn = turnnum;
    }
    
    /**
    * <p>This displays the turn.</p>
    *
    * @param x,y
    */    
    public int getturn(){
        return turn;
    }
    
    /**
    * <p>Selected is the condition on whether a piece has been selected is whether it's waiting to be played.</p>
    *
    * @param currstatus
    */
    public void setselected (int currstatus){
        selected = currstatus;
    }
    
    /**
    * <p>This returns the status on whether a piece has been selected. 0 means it has not. 1 means it has.</p>
    */
    public int getselected(){
        return selected;
    }
}
