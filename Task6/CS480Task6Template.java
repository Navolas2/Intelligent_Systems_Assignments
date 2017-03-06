import cs480viewer.task5.Agent;
import cs480viewer.task5.AgentManager;
import cs480viewer.task5.E_TrackMode;
import cs480viewer.task5.Viewer;

//=============================================================================================================================================================
public class CS480Task6Template
{
   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public static void main(final String[] args)
   {
      new CS480Task6Template();
   }

   private final Viewer _viewer;

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public CS480Task6Template()
   {
      _viewer = new Viewer("task6_3_version2.trk", 0);

      doTest1();
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   private void doTest1()
   {
      AgentManager agentManager = _viewer.getAgentManager();

      Agent agentAnt = agentManager.getAgent("ant");

      agentAnt.setTrackMode(E_TrackMode.HORIZONTAL);
      food f = new food("food", false);
      point fc = f.getCurrent();
      pesticide p = new pesticide("poison", false);
      point pc = p.getCurrent();
      _viewer.doAddEvent("food", fc.x, 0, fc.z, 0, 0, 0);
      _viewer.doAddEvent("pesticide", pc.x, 0, pc.z, 0, 0, 0);

      Ant a = new Ant("Ant", 5, false);
      a.initHeight(f, p);
      while(!a.isDone())
      {
    	  a.nextDesire(f, p);
    	  point[] apoint = a.nextLocation();
         _viewer.doAddEvent("ant", apoint[0].x, apoint[0].y, apoint[0].z, apoint[1].z, apoint[1].y, 0);

         _viewer.doAdvanceEventClock();
      }
   }
}
