import cs480viewer.task7.AgentManager;
import cs480viewer.task7.E_TrackMode;
import cs480viewer.task7.Viewer;

//=============================================================================================================================================================
public class CS480Task7Template
{
   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public static void main(final String[] args)
   {
      new CS480Task7Template();
   }

   private final Viewer _viewer;

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public CS480Task7Template()
   {
      _viewer = new Viewer("task7_4_3.trk", 0);

      doTest1();
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   private void doTest1()
   {
      AgentManager agentManager = _viewer.getAgentManager();

      agentManager.getAgent("uap").setTrackMode(E_TrackMode.HORIZONTAL_VERTICAL); // also consider E_TrackMode.HORIZONTAL
      terror t = new terror();
      while (t.weightCheck())
      {
    	  point[] p = t.nextLocation();
         _viewer.doAddEvent("uap", p[0].x, p[0].y, p[0].z, p[1].z, p[1].y, p[1].x);

         _viewer.doAdvanceEventClock();
      }
      System.out.println("Weight is zero of lower");
   }
}
