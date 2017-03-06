import java.util.ArrayList;

import cs480viewer.task5.Agent;
import cs480viewer.task5.AgentManager;
import cs480viewer.task5.E_TrackMode;
import cs480viewer.task5.Viewer;

//=============================================================================================================================================================
public class CS480Task4Template
{
   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public static void main(final String[] args)
   {
      new CS480Task4Template();
   }

   private final Viewer _viewer;

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public CS480Task4Template()
   {
      _viewer = new Viewer("task4_1.trk", 100);

      doTest1();
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   private void doTest1()
   {
      // this block provides control over displaying individual tracks; use the T key to toggle all on and off simultaneously. It applies to record mode only;
      // your settings are not retained for grading
      // {
      AgentManager agentManager = _viewer.getAgentManager();

      Agent agentBlimpActual = agentManager.getAgent("blimp-actual");
      Agent agentBlimpBelieved = agentManager.getAgent("blimp-believed");

      agentBlimpActual.setTrackMode(E_TrackMode.HORIZONTAL);
      agentBlimpBelieved.setTrackMode(E_TrackMode.HORIZONTAL);
      // }
      genericMovement b = new genericMovement(true);
      
      for (int i = 0; i < 300; i ++)
      {
    	  point[] bpoint = b.nextLocation();
    	  System.out.println(bpoint[1]);
         //_viewer.doAddEvent("blimp-actual", ppoint[0].x, ppoint[0].y, ppoint[0].z, ppoint[1].z, 0, 0);
         _viewer.doAddEvent("blimp-believed", bpoint[0].x, bpoint[0].y, bpoint[0].z, bpoint[1].z, bpoint[1].y, 0);


         _viewer.doAdvanceEventClock();
      }
   }
   
}
