import java.util.ArrayList;


public class ray extends genericMovement {

	boolean dead = false;
	point base;
	ArrayList<Bug> hitPoint = new ArrayList<Bug>();
	
	public ray(point p)
	{		
		super("ray", 10);
		current = p;
		base = p;
		this.heading = new point(0,0,10);
		this.heading.changeLength(this.speed);
		count = 800;
	}
	
	@Override
	public point[] nextLocation()
	{
		point[] p = super.nextLocation();
		if(this.current.distance(base) >= 250)
		{
			dead = true;
		}
		return p;
	}
	
	@Override
	protected void flipHeading() 
	{
		count = count;
	}
	
	@Override
	protected void newHeading()
	{
		count = count;
	}
	
	@Override
	public void collisionCheckLocal(ArrayList<genericMovement> objects)
	{
		this.hitPoint.clear();
		for(int i = 0; i < objects.size(); i++)
		{
			if(!dead)
			{
				//true positive/true negative prob: ((62500distance 2)/62500)
				//false positive/false negative: (0.2 pFN )
				double chance = Math.random();
				if(!this.current.equal(this.base))
				{
					point tar = objects.get(i).current;
					point p = new point(current, base);
					point t = new point(tar, base);
					double a = t.angleForm2(p);
					double d1, d2;
					d1 = tar.distance(base);
					d2 = current.distance(base);
					if(Double.isNaN(a))
					{
						//System.out.println("NAN");
						a = 0;
					}
					point p2 = new point(p);
					point p3 = new point(p);
					p.rotateY(a);
					int status = 0;
					double Tp = (62500 - Math.pow(d2, 2))/62500;
					Tp = 1;//testing condition
					if(Math.abs(d2-d1) < 10)
					{
						boolean hit = false;
						int AngleCount = 0;
						while (!hit)
						{
							if (p.distance(t) <= 10 || p2.distance(t) <= 10)
							{
								hit = true;
								try
								{
									Bug temp = (Bug) objects.get(i);
									//System.out.println("I hit my target " + temp.getName());
									if(!temp.getDead())
									{
										if(Tp > chance)
										{
											//temp.setCurrent(p.distance(t) <= 10? p : p2);
											hitPoint.add(temp);
											temp.setSeen();
											status = 1;
											//System.out.println("True Positive");
										}
										else
										{
											status = -1;
											//System.out.println("False Negative");
										}
									}
								}
								catch(Exception e)
								{
									System.out.println("I didn't see a bug");
								}
							}
							
							p2.rotateY(1);
							AngleCount+=1;
							if(AngleCount > 360)
							{
								hit = true;
							}
						}
						if(status == 0 || status == -1)
						{
							chance = Math.random();
							double Fp = .2 * (1 - Tp);
							if(chance < Fp)
							{
								double fakeRot = Math.random() * 360;
								p3.rotateY(fakeRot);
								while(p3.boundcheck(400, -400, 400, -1, 400, -400))
								{
									fakeRot = Math.random() * 360;
									p3.rotateY(fakeRot);
								}
								Bug fake = new Bug("fakeBug", 5, false);
								fake.setCurrent(p3);
								hitPoint.add(fake);
								//System.out.println("False Positive");
							}
							else
							{
								//System.out.println("True Negative");
							}
						}
					}
					
				}
			}
		}
	}
	
	public static ArrayList<ray> cleanArray(ArrayList<ray> r)
	{
		for(int i = 0; i < r.size(); i++)
		{
			if (r.get(i).dead)
			{
				r.remove(i);
				i--;
			}
		}
		return r;
	}
}
