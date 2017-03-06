import java.util.ArrayList;

import cs480viewer.task3.Agent;
import cs480viewer.task3.AgentManager;
import cs480viewer.task3.E_TrackMode;
import cs480viewer.task3.Viewer;

//=============================================================================================================================================================
public class CS480Task3Template
{
   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public static void main(final String[] args)
   {
      new CS480Task3Template();
   }

   private final Viewer _viewer;

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public CS480Task3Template()
   {
      _viewer = new Viewer("task3_4_2.trk", 100);

      doTest1();
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   private void doTest1()
   {
      // this block provides control over displaying individual tracks; use the T key to toggle all on and off simultaneously. It applies to record mode only;
      // your settings are not retained for grading
      // {
      AgentManager agentManager = _viewer.getAgentManager();

      Agent agentscout = agentManager.getAgent("scout");

      agentscout.setTrackMode(E_TrackMode.HORIZONTAL);
      // }
      ArrayList<genericMovement> flower = new ArrayList<genericMovement>();
      flower.add(new genericMovement());
      flower.get(0).setName("flower");
      flower.get(0).current = new point();
      point f = flower.get(0).current;
      _viewer.doAddEvent("flower", f.x, 0, f.z, 0, 0, 0);

      ArrayList<genericMovement> bees = new ArrayList<genericMovement>();
      bees.add(new ScoutBee());
      bees.get(0).setName("scout");
      for (int i = 0; i < 4; i++)
      {
    	  bees.add(new workerBee());
    	  bees.get(i+1).setName("bee");
      }
      
      
      for (int j = 0; j < 5000; ++j)
      {
    	 //Checks if the scout bee can see the bees or flower 
         bees.get(0).collisionCheckLocal(bees);
         bees.get(0).collisionCheckLocal(flower);
         
         //bees.get(0).setHeading(flower.get(0));
         //gets the movement locations for the bees
         point[][] d = new point[5][];
         //checks if the bees will collide with each other
         genericMovement.collisionCheck(bees);
         for (int i = 0; i < 5; i++)
         {
        	 d[i] = bees.get(i).nextLocation();
         }
         _viewer.doAddEvent("scout", d[0][0].x, d[0][0].y, d[0][0].z, d[0][1].z, 0, 0);
        
         _viewer.doAddEvent("bee1", d[1][0].x, d[1][0].y, d[1][0].z, d[1][1].z, 0, 0);
         _viewer.doAddEvent("bee2", d[2][0].x, d[2][0].y, d[2][0].z, d[2][1].z, 0, 0);
         _viewer.doAddEvent("bee3", d[3][0].x, d[3][0].y, d[3][0].z, d[3][1].z, 0, 0);
         _viewer.doAddEvent("bee4", d[4][0].x, d[4][0].y, d[4][0].z, d[4][1].z, 0, 0);

         _viewer.doAdvanceEventClock();
      }
   }
}
