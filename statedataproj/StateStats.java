package stateData;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;

//***************************************************************************
// StateStats        Author: Matthew Powers
// 
// CSC 1051 - Project 10 - Hunger and Homelessness Awareness week project.
// See http://www.csc.villanova.edu/~map/1051/f14/proj10.html
// 
//***************************************************************************
import javax.swing.JFrame;

import java.util.Scanner;

public class StateStats
{
   public static void main (String[] args) throws IOException 
   {
      JFrame frame = new JFrame ("Hunger Homelessness Awareness Week");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      File path = new File("statedata");
      Scanner scan = new Scanner(path);
      int count = 0;
      scan.useDelimiter("\t");
      String line = scan.nextLine();
      Scanner scanLine = new Scanner(line);
      scanLine.useDelimiter("\t");
      String[] info = new String[55];
      
      scan.useDelimiter("\n");
      count = 0;
      while (scan.hasNext()) {
    	  info[count] = scan.next();
    	  count++;
      }
      State[] stateInfo = new State[55]; // 54 states and territories plus totals
      
      count=0;
      while (count<55) {
    	  State state = new State(info[count], line);
    	  stateInfo[count] = state;
    	  count++;
      }
      
      frame.getContentPane().add(new StateStatsPanel(stateInfo));
      
      // Some code to test what we have so far:
      for (int i = 0; i < stateInfo.length; i++)
         if (stateInfo[i] == null)
            System.out.println(i + "\tdata not provided");
         else
            System.out.println(i + "\t" + stateInfo[i].blurb());

      frame.pack();
      frame.setVisible(true);
   }
}