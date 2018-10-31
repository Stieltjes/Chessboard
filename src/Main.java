/**
 * <p>This program draws a chess board with pieces, which can move onto any unoccupied space.</p>
 *
 * @author Benjamin Wang, Set B
 * @version 1.0
 */

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Style Options");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Board panel = new Board();
        frame.getContentPane().add(panel);
        
        frame.pack();
        frame.setVisible(true);
    }
}
