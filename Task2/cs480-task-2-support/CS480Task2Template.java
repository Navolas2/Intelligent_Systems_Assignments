import cs480viewer.task2.Agent;
import cs480viewer.task2.AgentManager;
import cs480viewer.task2.E_TrackMode;
import cs480viewer.task2.Viewer;

//=============================================================================================================================================================
public class CS480Task2Template
{
   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public static void main(final String[] args)
   {
      new CS480Task2Template();
   }

   private final Viewer _viewer;

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public CS480Task2Template()
   {
      _viewer = new Viewer("task2_4_2.trk", 100);
    
      doTest1();
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   private void doTest1()
   {
      // this block provides control over displaying individual tracks; use the T key to toggle all on and off simultaneously. It applies to record mode only;
      // your settings are not retained for grading
      // {
      AgentManager agentManager = _viewer.getAgentManager();

      Agent agentDolphin = agentManager.getAgent("dolphin");

      agentDolphin.setTrackMode(E_TrackMode.HORIZONTAL_VERTICAL);
      // }

      int v;
      fishBrain[] f1 = new fishBrain[5];
      DolphinBrain d1 = new DolphinBrain();
      for (int j = 0; j < f1.length; j++)
      {
    	  f1[j] = new fishBrain();
      }
      for (int i = 0; i < 360; ++i)
      {
         //f1 = fishBrain.avoidCollision(f1);
         point[][] p = new point[f1.length][];
         point[][] d = new point[1][];
         for (int j = 0; j < f1.length; j++)
         {
       	  p[j] = f1[j].nextLocation();
         }
         d1.targetInRange(f1);
         d[0] = d1.nextLocation();
         _viewer.doAddEvent("dolphin", d[0][0].x, d[0][0].y, d[0][0].z, d[0][1].z, d[0][1].y, 0);

         _viewer.doAddEvent("fish1", p[0][0].x, p[0][0].y, p[0][0].z, p[0][1].z, 0, 0);
         _viewer.doAddEvent("fish2",p[1][0].x, p[1][0].y, p[1][0].z, p[1][1].z, 0, 0);
         _viewer.doAddEvent("fish3", p[2][0].x, p[2][0].y, p[2][0].z, p[2][1].z, 0, 0);
         _viewer.doAddEvent("fish4", p[3][0].x, p[3][0].y, p[3][0].z, p[3][1].z, 0, 0);
         _viewer.doAddEvent("fish5", p[4][0].x, p[4][0].y, p[4][0].z, p[4][1].z, 0, 0);

         _viewer.doAdvanceEventClock(); // call this to advance the time step; all doAddEvents above happen at the same time
      }
   }
}
