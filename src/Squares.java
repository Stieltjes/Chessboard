/**
 * <p>This specifies the info each square holds</p>
 *
 * @author Benjamin Wang, Set B
 * @version 1.0
 */

public class Squares implements java.io.Serializable{
    private Pieces state;
    private int player;

    /**
    * <p>This is the constructor, the square starts out being empty.</p>
    */
    public Squares(){
        state = Pieces.empty;
        player = -1;
    }

    /**
    * <p>This constructs a certain piece, and the player it belongs to.</p>
    * @param thepiece, blackorwhite
    */   
    public Squares(Pieces thepiece, int blackorwhite){
        state = thepiece;
        player = blackorwhite;
    }

    /**
    * <p>This changes the state of a square, giving it a new piece.</p>
    * @param newpiece
    */      
    public void setstate(Pieces newpiece){
        state = newpiece;
    }
    
    /**
    * <p>This sets player whose piece occupies the particular square.</p>
    * @param newpiece
    */ 
    public void setplayer(int newplayer){
        player = newplayer;
    }

    /**
    * <p>This returns the piece on the square.</p>
    */     
    public Pieces getstate(){
        return state;
    }
    
    /**
    * <p>This returns the player whose piece is on the square.</p>
    */     
    public int getplayer(){
        return player;
    }
    
}
