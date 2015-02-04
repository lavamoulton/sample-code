package stateData;

//***************************************************************************
// StateStatsPanel        Author: Matthew Powers
// 
// CSC 1051 - Project 10 - Hunger and Homelessness Awareness week project.
// See http://www.csc.villanova.edu/~map/1051/f14/proj10.html
// 
//***************************************************************************
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class StateStatsPanel extends JPanel
{
   private State[] stats;
   private int[] selected;
   private int focus; 
   private final int WIDTH = 1500, HEIGHT = 800;
   
   // Constructor: sets up stats, selected, and focus
   public StateStatsPanel(State[] stats)
   {
      this.stats = stats;
      setPreferredSize (new Dimension(WIDTH, HEIGHT));
      setBackground (Color.gray);
      selected = new int[5];
      randomize();
      focus = State.getRandomFocus();
   }
   // randomize(): Chooses 5 numbers at random (no repetitions)
   // and stores them in the selected array. 
   // (The numbers should be suitable as indices for the stats array, 
   //  i.e., should be in the range 0...??)
   private void randomize()
   {
      Random arbitrary = new Random();
      
      
      selected[0] = arbitrary.nextInt(55);
      selected[1] = arbitrary.nextInt(55);
      selected[2] = arbitrary.nextInt(55);
      selected[3] = arbitrary.nextInt(55);
      selected[4] = arbitrary.nextInt(55);
   }
   
   // paints this panel using selected data and the current focus
   public void paintComponent(Graphics p)
   {
      super.paintComponent(p);
      
      p.setColor(Color.white);
      p.setFont(new Font("Helvetica", Font.BOLD, 40));
      p.drawString("One Night in January 2014...", 40, 40);
      
      int position = 100;
      
      // ****** IMPLEMENT THIS ******
      // Now display the info for the 5 states, using the current focus.
      // Here is an example for the state in index 42: 
      for (int i=0; i<5; i++) {
	      stats[selected[i]].setFocus(focus);
	      stats[selected[i]].draw(p, 10, position+(position*i));
      }
      
   }
}