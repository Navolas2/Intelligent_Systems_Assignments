
public class DolphinBrain extends fishBrain {

	private fishBrain target;
	private int lastTarget;
	boolean jumping = false;
	double jumpCount = 5;
	double jumpHeight = 50;
	boolean up = true;
	private static final double range = 100;
	private static final double degree = 35; //half the degree of vision, represents one side
	
	
	public DolphinBrain() {
		super();
		target = null;
		this.speed = this.speed * 1.5;
		lastTarget = -1;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public point[] nextLocation()
	{
		double j = Math.random();
		j= 1;
		if (j > .8 && !jumping){
		if (target != null)
		{
			if (!reachedTarget())
			{
				adjustHeading();
				changeCount(20);
			}
			else
			{
				target = null;
				super.newHeading();
			}
		}
		return super.nextLocation();
		}
		else
			return jump();
	}

	private point[] jump() {
		if (current.boundcheck(350, -350, 350, -350, 60, -60) && !jumping)
		{
			return super.nextLocation();
		}
		jumping = true;
		super.changeCount(10);
		if (up)
		{
			jumpCount--;
		}
		else
		{
			jumpCount++;
		}
		if ( jumpCount == -1){jumpCount = 0; up = false;}
		else if ( jumpCount == 5) {up = true; jumping = false;}
		if (jumping)
		{
			double res = (jumpCount / 5);
			res =  jumpHeight * res;
			if (!up){res *= -1;}
			heading.y = res;
		}
		else{ 
			heading.y = 0; 
			//super.newHeading();
			}
		return super.nextLocation();
	}

	private boolean reachedTarget() {
		double d = current.distance(target.getCurrent());
		return d <= 10;
	}

	private void adjustHeading() {
		point h = new point(target.getCurrent(), current);
		h.changeLength(speed);
		heading = h;
		System.out.println("Heading changed");
	}

	public void targetInRange(fishBrain[] fishes)
	{
		point nloc = current.add(heading);
		int i = 0;
		while (i < fishes.length && target == null && !jumping)
		{	
			if (i != lastTarget){			
			double d = current.distance(fishes[i].getCurrent());
			if (range >= d)
			{
				double angle = nloc.angle(current, fishes[i].getCurrent());
				if (angle < 0){angle *= -1;}
				if (angle < degree || angle > (360 - degree))
				
				{
					target = fishes[i];
					lastTarget = i;
				}
			}
			}
			i++;
		}
	}
}
