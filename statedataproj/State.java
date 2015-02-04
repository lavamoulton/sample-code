package stateData;

//***************************************************************************
// State        Author: Matthew Powers
// 
// CSC 1051 - Project 10 - Hunger and Homelessness Awareness week project.
// See http://www.csc.villanova.edu/~map/1051/f14/proj10.html
//
// Starter code for datatype representing data from one state. 
//    NONE OF THE METHODS are yet correctly implemented, but the draw() method
//    contains some code to help get started with the graphics. 
// 
//***************************************************************************
import java.awt.*;
import java.util.Scanner;
import java.util.Random;

public class State
{
    private String st;                // two-letter code for state
    private int[] info;               // array of data for this state
    private String[] label;           // array of data labels for this state
    public final static int NUM = 21; // number of data topics (21 numeric columns)
    private int focusCol;         // column corresponding to the data we want to see
    private String blurb;
    
    
    public State(String line, String headings) 
    {
      label = new String [22];
      info = new int [21];
      Scanner Scan = new Scanner(line);
      Scanner scanHead = new Scanner(headings);
      Scan.useDelimiter("\t");
      scanHead.useDelimiter("\t");
      int count = 0;
      st = Scan.next();
      while (Scan.hasNext())
         {
         info [count] = Scan.nextInt();
         count++;
         }
      
      count = 0;
      while (scanHead.hasNext())
         {
         label [count] = scanHead.next();
         count++;
         }
      focusCol = 11;
    }
        // constructor: sets up state, info, and label using Strings corresponding to:
        //     1) a line of data (ie, data for a state)
        //          - stored in state and the array info
        //     2) headings (the first line in the dataset)
        //          - stored in the array label
        //    Also sets focusCol to a default value (col 3, ie,  total unsheltered homeless).
      
    
    public static int getRandomFocus() 
      {
      int result = 0;
      Random rand = new Random();
      result = rand.nextInt(21)+1;
      return result;
      }
     
     
     // returns int representing focus on random col
     // NOTE: this is a static method that can be used in StatsPanel to choose a focus at
     //  random to be used with *all* the State objects on a particular run
   
    public void setFocus(int i) {
      focusCol = i;
    } // sets focus to i
   
    
    
    public String blurb()
    {  // returns a blurb about the state with particular focus column
    blurb = st + ": ";
    blurb += label[focusCol];
    blurb+= ": " + info[focusCol];
    return blurb;
    }
    
   
   
    public void draw (Graphics p, int x, int y)  // displays info about this State
                           // including blurb() and dots representing data from focus column
    {
      Random rand = new Random();
      int i = 0;
      Color color = new Color(0, 0, rand.nextInt(150)+80);
      p.setColor(color);
      p.fillRect(x, y, 1200, 120);
      
      p.setColor(Color.black);
      while (i < info[focusCol]) 
      {
         p.fillOval(rand.nextInt(1200)+x,rand.nextInt(120)+y-1,3,3);
         i++;
      }
      
      // a transparent dark blue color so that text shows up against the dots
      p.setColor(new Color(0, 0, (float) 0.5, (float) 0.5)); 
      
      p.fillRect(x + 5, y + 15, 700, 30);
      p.setColor(Color.yellow);
      p.setFont(new Font("Helvetica", Font.BOLD, 20));
      p.drawString(blurb(), x+15, y+40);
   }
}