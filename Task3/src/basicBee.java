import java.util.ArrayList;


public class basicBee extends genericMovement {

	protected point flower = null;
	private point temp;
	
	public basicBee() {
		super();
		speed = 5;
	}

	@Override
	public point[] nextLocation()
	{
		return super.nextLocation();
	}

	@Override
	protected boolean checkHeading() {
		return super.checkHeading();
	}

	@Override
	protected void turn() {
		super.turn();
	}

	@Override
	protected void flipHeading() {
		super.flipHeading();
	}

	@Override
	protected void newHeading() {
		super.newHeading();
	}
	
	@Override
	protected void newCount(){
		super.newCount();
	}
	
	@Override
	protected void changeCount(int i) {
		super.changeCount(i);
	}
	
	@Override
	public boolean seeObject(point p, double d, double a)
	{
		return super.seeObject(p, d, a);
	}
	
	@Override
	protected double nextDistance(genericMovement p2) {
		return super.nextDistance(p2);
	}
	
	@Override
	public void collisionCheckLocal(ArrayList<genericMovement> objects)
	{
		for (int i = 0; i < objects.size();i++)
		{
			genericMovement o = objects.get(i);
			if (seeObject(o.current, 55, 30))
			{
				if (o.name == "bee")
				{
				}
				else if(o.name == "scout")
				{
				}
			}
		}
	}
	
	@Override 
	public void setHeading(genericMovement genericMovement) {
		super.setHeading(genericMovement);
	}

	public void watchMeDance(ScoutBee scoutBee) {
		this.heading = new point(scoutBee.current, current);
		heading.changeLength(speed);
		//this.flower = scoutBee.getFlower();
		System.out.println("Watching the dance");
		temp = new point();
	}
	
	public void turned(char dir, boolean up)
	{
		if (up)
			temp.increment(dir);
		else
			temp.decrament(dir);
	}
	
	public void DanceComplete(ScoutBee scoutBee) {
		//this.flower = scoutBee.getFlower();
		this.flower = temp;
		System.out.println("Watched the dance");
	}
	
	public point getFlower()
	{
		return flower;
	}

}
