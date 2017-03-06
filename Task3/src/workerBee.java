
public class workerBee extends basicBee {
	
	public workerBee()
	{
		super();
		
	}
	
	@Override
	public point[] nextLocation()
	{
		point c = current;
		count = 10;
		if (flower != null)
		{
			point p2 = new point(flower, current);
			heading = p2;
			heading.changeLength(speed);
			if (current.distance(flower) <= 20)
			{
				flower = null;
				name = "happyBee";
			}
		}
		point[] f = super.nextLocation();
		if (flower == null)
		{
			f[0] = c;
			current = c;
		}
		return f;
	}
}
