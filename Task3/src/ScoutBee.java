import java.util.ArrayList;


public class ScoutBee extends basicBee {
	ArrayList<basicBee> friends;
	int i = -1;
	boolean dancing = false;
	boolean z = false;
	boolean dance_done = false;
	
	public ScoutBee()
	{
		super();
		//current = new point(0,0,0);
		friends = new ArrayList<basicBee>();
		flower = null;
		//heading = new point(0,0, 50);
		heading.changeLength(speed);
	}
	
	@Override
	public point[] nextLocation()
	{
		if(flower != null)
		{
			if (i == -1 && friends.size() > 0){
				i = 0;
			}
			else if (i < friends.size() && i != -1)
			{
				if (turn == 0 && !dancing)
				{
					point nLoc = current.add(heading);
					point p2 = new point(friends.get(i).current, current);
					/*
					double angle = nLoc.angle(current, p2);
					if (angle < 0 && turn == 0)
					{
						turn = angle * -1;
						right = false;
					}
					else if (angle > 0 && turn == 0)
					{
						turn = angle;
						right = false;
					}
					else if(angle < 5 || angle > -5 && turn != 0)
					{
						turn = 0;
					}
					*/
					
					this.heading = p2;
					heading.changeLength(speed);
					
					if (current.distance(friends.get(i).current) <= 60)
					{
						//dance here
						System.out.println("I'm dancing!");
						friends.get(i).watchMeDance(this);
						dancing = true;
					}
				}
				else if (dancing && dance_done && turn == 0)
				{
					dance_done = false;
					friends.get(i).DanceComplete(this);
					dancing = false;
					heading.changeLength(speed);
					System.out.println("Dance done");
					newHeading();
					i++;
				}
			}
			else if (i == 5)
			{
				point p2 = new point(flower, current);
				heading = p2;
				heading.changeLength(speed);
			}
		}
		if(dancing)
		{
			dance();
		}
		return super.nextLocation();
	}
	
	private void dance() {
		heading.changeLength(.0001);
		if (turn == 0 && z == false)
		{
			z = true;
			turn = flower.x * 360;
		}
		else if (turn == 0)
		{
			z = false;
			right = true;
			turn = flower.z * -360;
			dance_done = true;
		}
		else
		{
			if (turn % 360 == 0)
			{
				if (z)
					friends.get(i).turned('x', turn > 0 ? true : false);
				else
					friends.get(i).turned('z', turn < 0 ? true : false);
			}
		}
	}

	@Override
	protected void newCount()
	{
		count = (int) (50 + (Math.random() * 70));
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
					if (friends.indexOf(o) == -1)
					{
						friends.add((basicBee)o);
					}
				}
				else if(o.name == "flower")
				{
					this.flower = o.current;
				}
			}
		}
	}

	@Override
	public void setHeading(genericMovement genericMovement) {
		if (flower == null || i == 5){
		heading = new point(genericMovement.current, this.current);
		heading.changeLength(speed);
		}
		else if (!genericMovement.name.equals("flower")){
			heading = new point(genericMovement.current, this.current);
			heading.changeLength(speed);
			}
	}
}
