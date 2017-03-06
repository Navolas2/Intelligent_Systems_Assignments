import java.util.ArrayList;


public class RayFactory {
	point base;
	double angle;
	int spacing;
	private int delayCount = 0;
	genericMovement tar;
	
	public RayFactory(point z, int dist, genericMovement target)
	{
		base = z;
		spacing = dist;
		tar = target;
	}
	
	public RayFactory(double x, double y, double z,  int dist, genericMovement target)
	{
		base = new point(x,y,z);
		spacing = dist;
		tar = target;
	}
	
	public ray[] nextRay()
	{
		point b = new point(base.x, base.y, base.z - 5);
		point t = new point(tar.current);
		point c = new point(t, base);
		point h = tar.heading;
		//h.divide(.5);
		ray[] rays = new ray[40];
		int j = 0;
		for(int i = 0; i < 40; i++)
		{
			rays[j] = new ray(base, spacing, c);
			t = t.add(h);
			c = new point(t, base);
			j++;
		}
		return rays;
	}
	
	public ray[] raysNextLocation(ray[] r)
	{
		int deadCount = 0;
		for (int i = 0; i < r.length; i++)
		{
			r[i].nextLocation();
			if(r[i].dead)
			{
				deadCount++;
			}
		}
		if (deadCount > 0)
		{
			if(deadCount >= r.length)
				return null;
			else
			{
				ArrayList<ray> r2 = new ArrayList<ray>();
				for (int i = 0; i < r.length; i++)
				{
					if(!r[i].dead)
					{
						r2.add(r[i]);
					}
				}
				Object[] t = r2.toArray();
				ray[] out = new ray[t.length];
				for(int i = 0; i < out.length; i++)
				{
					out[i] = (ray) t[i];
				}
				return out;
			}
		}
		else		
			return r;
	}
	
	public point[] nextLocations(ray[] r)
	{
		point[] p = new point[r.length];
		for (int i = 0; i < r.length; i++)
		{
			p[i] = r[i].current;
		}
		return p;
	}
	
	public void setTarget(genericMovement m)
	{
		tar = m;
	}
}
