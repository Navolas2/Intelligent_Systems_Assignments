import java.util.ArrayList;

import cs480viewer.task1.Viewer;

//=============================================================================================================================================================
public class CS480Task1Template
{
   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public static void main(final String[] args)
   {
      new CS480Task1Template();
   }

   private final Viewer _viewer;

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public CS480Task1Template()
   {
      _viewer = new Viewer("track1_4.trk");

      doTest1();
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   private void doTest1()
   {
	  //ArrayList<point> points= birdFlight.startingPoint(0,00,0);
	  //points = birdFlight.addPoint(points, -500, 00, -500);
	  //points = birdFlight.addPoint(points, 100, 00, -500);
	  //points = birdFlight.addPoint(points, 300, 00, 500);
	  //points = birdFlight.addPoint(points, -500, 00, -500);
	  ArrayList<point> points= birdFlight.startingPoint();
	  for (int i = 0; i < 10; i++){points = birdFlight.addPoint(points);}
      for (int i = 0; i < points.size()-1; i++)
      {
    	  point r = birdFlight.calcRotate(points.get(i), points.get(i+1));
         // call this one per movement; the arguments are id (use "bird"), x, y, z, yaw, pitch, and roll
         _viewer.doAddEvent("bird", points.get(i).x, points.get(i).y, points.get(i).z, 0, 0, 0);
         //_viewer.doAddEvent("bird", points.get(i).x, points.get(i).y, points.get(i).z, r.z, r.y, r.x);
         // call this after each movement; the argument is the delay in milliseconds
         _viewer.doAdvanceEventClock(50);
      }
   }
}
