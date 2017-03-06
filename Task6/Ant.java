
public class Ant extends genericMovement {

	private double curHeight = 0;
	private boolean isDone = false;
	
	public Ant() {
		// TODO Auto-generated constructor stub
	}

	public Ant(boolean dimension) {
		super(dimension);
		// TODO Auto-generated constructor stub
	}

	public Ant(String nm) {
		super(nm);
		// TODO Auto-generated constructor stub
	}

	public Ant(String nm, boolean dimension) {
		super(nm, dimension);
		// TODO Auto-generated constructor stub
	}

	public Ant(double sp) {
		super(sp);
		// TODO Auto-generated constructor stub
	}

	public Ant(double sp, boolean dimension) {
		super(sp, dimension);
		// TODO Auto-generated constructor stub
	}

	public Ant(String nm, double sp) {
		super(nm, sp);
		// TODO Auto-generated constructor stub
	}

	public Ant(String nm, double sp, boolean dimension) {
		super(nm, sp, dimension);
		// TODO Auto-generated constructor stub
	}

	/*@Override
	public point[] nextLocation()
	{
		if(turn != 0)
		{
			stay();
		}
		else
		{
			move();
		}
		return super.nextLocation();
	}*/
	
	@Override
	public void newHeading()
	{
		
	}
	
	@Override
	public void flipHeading()
	{
		
	}
	
	public void initHeight(food f, pesticide p)
	{
		double d1, d2;
		d1 = current.distance(f.getCurrent());
		d2 = current.distance(p.getCurrent());
		d1 = f.raise(d1);
		d2 = p.raise(d2);
		curHeight = d1 + d2;
	}
	
	
	public void nextDesire(food f, pesticide p)
	{
		if(this.turn == 0)
		{
			double d1, d2, curH, curHN;
			curH = curHeight;
			point t = new point(current);
			double optimal = -1;
			t = t.add(heading);
			for(int i = 0; i < 360; i+= 30)
			{
				d1 = t.distance(f.getCurrent());
				d2 = t.distance(p.getCurrent());
				d1 = f.raise(d1);
				d2 = p.raise(d2);
				curHN = d1 + d2;
				if (curHN > curH)
				{
					optimal = i;
					curH = curHN;
				}
				//rotate t 30 degrees
				t.translate('-', current);
				t.rotateY(30);
				t.translate('+', current);	
			}
			
			if(optimal != -1)
			{
				move();
				if(optimal < 180)
				{
					this.turn = optimal;
					right = false;
				}
				else
				{
					double over = optimal - 180;
					over = 180 - over;
					over *= -1;
					turn = over;
					right = true;
				}
				//this.heading.rotateY(optimal);
			}
			else
			{
				
			}
			System.out.println("Optimal turn is:" + optimal);
			
			if(this.distance(f) < 10)
			{
				this.stay();
				System.out.println("I got my food");
				isDone = true;
			}
			if(this.distance(p) < 10)
			{
				this.stay();
				System.out.println("I am dead");
				isDone = true;
			}
		}
		this.count = 100;
	}
	
	
	public boolean isDone()
	{
		return isDone;
	}
}
