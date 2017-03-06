
public class ray extends genericMovement {

	boolean dead = false;
	point base;
	
	public ray(point p, double space, point heading)
	{		
		super();
		current = p;
		base = p;
		this.heading = heading;
		double var = (Math.random() * 1.5) - .7;
		this.heading.changeLength(space + var);
		speed = space + var; 

		count = 800;
	}
	
	@Override
	public point[] nextLocation()
	{
		point[] p = super.nextLocation();
		if(p[0].boundcheck(510, -510, 200,-200, 510, -510))
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
}
