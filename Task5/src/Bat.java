import java.util.ArrayList;


public class Bat extends genericMovement {

	public Bat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bat(boolean dimension) {
		super(dimension);
		// TODO Auto-generated constructor stub
	}

	public Bat(String nm) {
		super(nm);
		// TODO Auto-generated constructor stub
	}

	public Bat(String nm, boolean dimension) {
		super(nm, dimension);
		// TODO Auto-generated constructor stub
	}

	public Bat(double sp) {
		super(sp);
		// TODO Auto-generated constructor stub
	}

	public Bat(double sp, boolean dimension) {
		super(sp, dimension);
		// TODO Auto-generated constructor stub
	}

	public Bat(String nm, double sp) {
		super(nm, sp);
		// TODO Auto-generated constructor stub
	}

	public Bat(String nm, double sp, boolean dimension) {
		super(nm, sp, dimension);
		//this.current = new point(0,0,0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void trackTarget(point next)
	{
		if(turn == 0 && pitch == 0)
		{
			//adjust turn and pitch to move towards target
			point n = new point(next, current);
			point t = new point(this.getTarget(), current);
			double a = n.angleForm2(t);
			double a2 = n.angle(t);
			
			checkTurnDirection(t, n, a);
			
			if(Double.isNaN(a))
			{
				a = 0;
			}
			
			turn = a;

			this.count = 20;
		}
		
		if (current.distance(this.getTarget()) < 10)
		{
			this.deleteTarget();
		}
	}
	
	


	public ray createRay()
	{
		ray r = new ray(current);
		return r;
	}
	
	public void solveTarget(ArrayList<ray> r)
	{
		for(int i = 0; i < r.size(); i++)
		{
			ray r1 = r.get(i);
			if(r1.hitPoint.size() != 0)
			{
				for(int j = 0; j < r1.hitPoint.size(); j++)
				{
					if(this.getTarget() == null)
					{
						this.setTarget(r1.hitPoint.get(j));
					}
					else
					{
						if(this.current.angle(getTarget()) > this.current.distance(r1.hitPoint.get(j).getCurrent()))
						{
							this.setTarget(r1.hitPoint.get(j));
						}
					}
				}
			}
		}
	}

	public ArrayList<genericMovement> checkDistance(ArrayList<genericMovement> bugs) {
		for(int i = 0; i < bugs.size(); i++)
		{
			if(this.distance(bugs.get(i)) <= 5)
			{
				if(this.getTargetObject() != null)
				{
					if(this.getTargetObject().getName().equals(bugs.get(i).getName()))
					{
						((Bug)bugs.get(i)).setDead(true);
						this.deleteTarget();
					}
				}
			}
		}
		return bugs;
	}

	
}
