/**
 * <p>This is where the everything is actually implemented, drawing and moving.</p>
 *
 * @author Benjamin Wang, Set B
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;


public class Board extends JPanel{
    
    JButton[][] buttons = new JButton[8][8];
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    JLabel label;
    int prefx = 60;
    int prefy = 60;
    ChessBoard newboard;
    int oldx = 0;
    int oldy = 0;

    /**
    * <p>This is the constructor</p>
    */
    public Board(){
        
        newboard = new ChessBoard();
                
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setText(newboard.display(i, j));
                if (newboard.getplayer(i, j)==0) buttons[i][j].setForeground(Color.RED);
                else buttons[i][j].setForeground(Color.BLUE);
                      
                buttons[i][j].setPreferredSize(new Dimension(prefx,prefy));
                if ((i + j) % 2 == 0) {
                    buttons[i][j].setBackground(Color.gray);
                } else {
                    buttons[i][j].setBackground(Color.white);
                }
                buttons[i][j].addActionListener(new ClickListener());
                add(buttons[i][j]);
            }
         }
        setPreferredSize(new Dimension(prefx*9,prefy*9+20));
        add(save);
        add(load);
        save.addActionListener(new FileListener());
        load.addActionListener(new FileListener());
    }

    /**
    * <p>This method checks for the validity of a movement. The piece cannot move if some other piece is blocking its way
    * unless it's the knight. Final destination also can't be the piece of the colour.
    *ox and oy are the co-ordinates of the original piece. nx and ny are the co-ordinates of the designation. The if and else
    * statements deals with each piece separately.
    * .</p>
    * @param ox, oy, nx, ny
    */    
    private boolean is_valid_move(int ox, int oy, int nx, int ny){
        String piece;
        int sign, dsign;
        int x1 = ox, x2 = nx, y1 = oy, y2 = ny;
        int count = 0;
        
        piece = newboard.display(ox, oy);
        
        if (ox == nx && oy == ny)
            return true;
        
        if (piece == "R") {
            if (ox == nx){
                sign = (ny > oy) ? 1 : -1;
                while ((y1=y1+sign)!=y2) {
                    if (newboard.getpiece(ox, y1)!=Pieces.empty)
                        return false;
                }
            } else if (oy == ny) {
                sign = (nx > ox) ? 1: -1;
                while ((x1=x1+sign)!=x2){
                    if (newboard.getpiece(x1,oy)!=Pieces.empty)
                        return false;
                }
            } else {return false;}
            if (newboard.getplayer(nx,ny)!=newboard.getplayer(ox,oy))
                return true;
        } else if (piece == "H") {
            if ((Math.pow(ox-nx, 2)+Math.pow(oy-ny,2))==5 && newboard.getplayer(nx, ny)!=newboard.getplayer(ox, oy))
                return true;
        } else if (piece == "B") {
            if (Math.abs(y2-y1)!=Math.abs(x2-x1))
                return false;
            sign = (nx > ox) ? 1 : -1;
            dsign = (ny > oy) ? 1 : -1;
            while ((x1=x1+sign)!=x2 &&(y1=y1+dsign)!=y2){
                if (newboard.getpiece(x1,y1)!=Pieces.empty)
                    return false;
            }
            if (newboard.getplayer(nx,ny)!=newboard.getplayer(ox,oy))
                return true;
        } else if (piece == "K") {
            if (((ny-oy)>1)||((nx-ox)>1))
                return false;
            if (newboard.getplayer(nx,ny)!=newboard.getplayer(ox,oy))
                return true;
        } else if (piece == "Q") {
            if (Math.abs(y2-y1)==Math.abs(x2-x1)){
                sign = (nx > ox) ? 1 : -1;
                dsign = (ny > oy) ? 1 : -1;
                while ((x1=x1+sign)!=x2 &&(y1=y1+dsign)!=y2){
                    if (newboard.getpiece(x1,y1)!=Pieces.empty)
                        return false;
                }
            } else if (ox == nx){
                sign = (ny > oy) ? 1 : -1;
                while ((y1=y1+sign)!=y2) {
                    if (newboard.getpiece(ox, y1)!=Pieces.empty)
                        return false;
                }
            } else if (oy == ny) {
                sign = (nx > ox) ? 1: -1;
                while ((x1=x1+sign)!=x2){
                    if (newboard.getpiece(x1,oy)!=Pieces.empty)
                        return false;
                }
            } else {return false;}
            if (newboard.getplayer(nx,ny)!=newboard.getplayer(ox,oy))
                    return true;
        } else if (piece == "P") {
            sign = (newboard.getplayer(ox,oy) == 0) ? 1 : -1;
            if (ox-nx == sign){
                if (Math.abs(ny-oy) == 1 && newboard.getplayer(nx,ny) == (sign+1)/2) return true;
                else if (ny == oy && newboard.getplayer(nx, ny) == -1) return true;
            } else if (oy == ny && ((ox/2)*2-3==sign*3) && (ox-nx == sign*2) && newboard.getplayer(ox-sign, oy)==-1 && newboard.getplayer(nx,ny)==-1) return true;
        }
        
        return false;
    }

    /**
    * <p>This is the listener that handles piece movement. If the move is valid, move the piece there (with possible capture)</p>
    * @param event
    */

    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
           
            if (newboard.getselected() == 0){
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (event.getSource() == buttons[i][j]) {
                            if ((newboard.getplayer(i,j)!=newboard.getturn()%2)&&newboard.getplayer(i,j)!=-1){
                                buttons[i][j].setForeground(Color.GREEN);
                                oldx = i;
                                oldy = j;
                                newboard.setselected(1);
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (event.getSource() == buttons[i][j]) {
                            if (is_valid_move(oldx, oldy, i, j)){
                                newboard.setplayer(i, j, newboard.getplayer(oldx, oldy));
                                newboard.setpiece(i,j,newboard.getpiece(oldx,oldy));
                                if (!(i==oldx && j == oldy)){
                                    newboard.setplayer(oldx, oldy, -1);
                                    newboard.setpiece(oldx, oldy, Pieces.empty);
                                    buttons[oldx][oldy].setText("");
                                    newboard.setturn(newboard.getturn()+1);
                                }
                                buttons[i][j].setText(newboard.display(i, j));
                                if (newboard.getplayer(i,j)==1) buttons[i][j].setForeground(Color.BLUE);
                                else buttons[i][j].setForeground(Color.red);
                                newboard.setselected(0);
                            } 
                        }
                    }
                }
                
            }
        }
    }

    /**
    * <p>This is the listener that handles save and load. A dialogue will pop up.</p>
    * @param event
    */    
    private class FileListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            Object source = event.getSource();
            String filename="";

            if (source == save){
                try {
                    filename = JOptionPane.showInputDialog("Please enter the file name you would like to save the game as");
                    FileOutputStream fileOut = new FileOutputStream(filename);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(newboard);
                    out.close();
                    fileOut.close();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        
            else {
                try {
                    filename = JOptionPane.showInputDialog("Please enter the file name you would like to load");
                    FileInputStream fileIn = new FileInputStream(filename);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    
                    newboard = (ChessBoard) in.readObject();
                    in.close();
                    fileIn.close();
                            
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            buttons[i][j].setText(newboard.display(i, j));
                            if (newboard.getplayer(i, j)==0) buttons[i][j].setForeground(Color.RED);
                            else buttons[i][j].setForeground(Color.BLUE);
                        }
                    }
                }
                catch (Exception ex){
                    System.out.println(filename+" not found");
                }            
            }
        }
    }
}
