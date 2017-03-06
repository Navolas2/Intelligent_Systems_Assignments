import java.util.ArrayList;


public class genericMovement {
	//****************Member Variables****************//
	point heading;
	point current;
	private boolean stay = false;
	protected int count;
	private boolean ThreeD;
	protected double turn;
	protected double pitch = 0;
	protected boolean right;
	protected double turnInc = 10;
	protected double speed = 10;
	private point target = null;
	private genericMovement tar = null;
	protected String name;
	//****************End MEMBER VARIABLES****************//
	
	
	//****************Constructors****************//
	
	//creates an object with no name and moves in 2d
	public genericMovement ()
	{
		name = "";
		this.ThreeD = false;
		init();
	}
	
	//creates an object with no name and moves in 2d or 3d
	public genericMovement(boolean dimension)
	{
		name = "";
		this.ThreeD = dimension;
		init();
	}
	
	//creates an object with a given name that moves in 2d
	public genericMovement(String nm)
	{
		setName(nm);
		this.ThreeD = false;
		init();
	}
	
	//creates an object with a given name that moves in 2d or 3d
	public genericMovement(String nm, boolean dimension)
	{
		setName(nm);
		this.ThreeD = dimension;
		init();
	}
	
	//creates an object with the provided speed that moves in 2d
	public genericMovement(double sp)
	{
		name = "";
		this.ThreeD = false;
		init();
		this.speed = sp;
	}
	
	//creates an object with the provided speed that moves in 2d or 3d
	public genericMovement(double sp, boolean dimension)
	{
		name = "";
		this.ThreeD = dimension;
		init();
		this.speed = sp;
	}

	//creates an object with the provided speed and name. The object moves in 2d
	public genericMovement(String nm, double sp)
	{
		name = nm;
		this.ThreeD = false;
		init();
		this.speed = sp;
	}
	
	//creates an object with the provided speed and name. The object can moves in 2d or 3d
	public genericMovement(String nm, double sp, boolean dimension)
	{
		name = nm;
		this.ThreeD = dimension;
		init();
		this.speed = sp;
	}
	//****************END CONSTRUCTORS****************//
	
	
	//****************Initialization Functions****************//
	
	//sets up the basic information for the genericMovement object
	private void init()
	{
		createHeading();
		newCount();
		createCurrent();
		turn = 0;
	}
	
	//creates the current position for a genericMovement object
	private void createCurrent()
	{
		double x,y,z;
		double size, world, height;
		world = 800;
		size = 400;
		height = 500;
		x = (Math.random() * world);
		if (x > 400){x -= size; x = x *-1;}
		z = (Math.random() * world);
		if (z > 400){z -= size; z = z *-1;}
		if(ThreeD)
		{
			y = (Math.random() * height);
		}
		else
			y = 0;
		
		current = new point(x, y, z);
	}

	//creates the heading for a genericMovement object
	private void createHeading()
	{
		double x,y,z;
		
		x = Math.random() * 2;
		if (x > 1){x -= 1; x = x *-1;}
		
		z = Math.random() * 2;
		if (z > 1){z -= 1; z = z *-1;}
		
		if(ThreeD)
		{
			y = Math.random() * 2;
			if (y > 1){y -= 1; y = y *-1;}
		}
		else
		{
			y = 0;
		}
		heading = new point(x,y,z);
		heading.changeLength(speed);
	}
	//****************END INITIALIZATION FUNCTIONS****************//
	
	
	//****************Movement Functions****************//
	
	//returns an array or points containing the location of the object after moving one step
	//the first point contains the location of the object
	//the second point contains the current rotation of the object
	public point[] nextLocation()
	{
		//checks to see if it's time to change the heading
		
			if (count <= 0 && turn == 0 && pitch == 0)
			{
				newHeading();
			}
			
			if (turn != 0 || pitch != 0){turn();}
			else{heading.changeLength(speed);}
			point nloc = nextLoc();
			
			if (!(nloc.boundcheck (400, -400, 400,-1, 400, -400) && turn != 0 && pitch != 0))
			{
				count--;
			}
			if (nloc.boundcheck (400, -400, 600,-1, 400, -400) && turn == 0 && checkHeading() && pitch == 0)
			{
				flipHeading();
				nloc = nextLoc();
			}
			point rot = new point(heading);
			rot.convertRotation();
			//checks to see if the heading is too high
			//if the heading is too high, a new heading is called to adjust the pitch
			if(Math.abs(rot.y) > 76 && pitch == 0)
			{
				if(rot.y < 1)
					newHeadingThreeD(.6);
				else
					newHeadingThreeD(.85);
			}
			if(target != null)
			{
				trackTarget(nloc);
			}
			if(!stay)
			{
				current = nloc;
			}
			point [] ret = {current, rot};
			return ret;
		}
	
	//
	protected point nextLoc()
	{
		return current.add(heading);
	}
	
	
	protected void checkTurnDirection(point t, point n, double a) 
	{
		point rPoint = new point(n);
		rPoint.rotateY(-20);
		point lPoint = new point(n);
		lPoint.rotateY(20);
		double rRot = t.angle(rPoint);
		double lRot = t.angle(lPoint);
		if(rRot < lRot)
		{
			right = true;
		}
		else
		{
			right = false;
		}
		
	}
	
	//checks to see if the object will or will not be out of bounds after a set number of movements
	//somewhat prevents objects from getting stuck in constant turn loop
	protected boolean checkHeading()
	{
		point nloc = nextLoc();
		boolean out = true;
		int i = 0;
		while (out && i < 100){
			out = nloc.boundcheck(400, -400, 400,-1, 400, -400);
			nloc = nloc.add(heading);
			i++;
		}
		return out;
	}

	//turns the object smoothly. Each turn it rotates based on the turnInc at the top of the object
	protected void turn()
	{
			if (turn > turnInc || (turn * -1) > turnInc)
			{
				if (right){
					heading.rotateY(turnInc * -1);
				}
				else{
					heading.rotateY(turnInc);	
				}
				if (turn < 0)
				{
					turn += turnInc;
				}
				else
				{
					turn -= turnInc;
				}
			}
			else
			{
				heading.rotateY(turn);
				turn = 0;
			}
		if(ThreeD)
		{
			ChangePitch();
		}
		heading.changeLength(speed * .75);
	}

	//manages changing the pitch of the object
	protected void ChangePitch()
	{
		if (pitch < 0)
			{
				if (pitch * -1 > turnInc)
				{
					heading.rotateX(turnInc * -1);	
					pitch += turnInc;
				}
				else
				{
					heading.rotateX(pitch * -1);
					pitch = 0;
				}
			}
		else if (pitch > 0)
			{
				if (pitch > turnInc)
				{
					heading.rotateX(turnInc);			
					pitch -= turnInc;
				}
				else
				{
					heading.rotateX(pitch);
					pitch = 0;
				}
			}
		heading.changeLength(speed);
		}
	
	//called when the object needs to turn drastically such as when at the edge of the world
	protected void flipHeading() 
	{
		if(!ThreeD)
		{
			flipHeadingXZ();
		}
		else
		{
			flipHeadingThreeD();
		}
		turn();
		//heading.rotateY(rot);
		newCount();
	}

	//manages a heading flip in three dimensions
	private void flipHeadingThreeD() 
	{
		if (this.current.y > 450 && this.heading.y > 0)
		{
			double rot = (Math.random() * .5) + .5;
			rot = Math.acos(rot);
			rot = Math.toDegrees(rot);
			this.pitch = rot * -1;
		}
		else if(this.current.y < 50 && this.heading.y < 0)
		{
			double rot = (Math.random() * .5) + .5;
			rot = Math.acos(rot);
			rot = Math.toDegrees(rot);
			this.pitch = rot;
		}
		else
		{
			flipHeadingXZ();
		}
	}
	
	//used to flip the heading along the XZ axis
	private void flipHeadingXZ()
	{
		point next = nextLoc();
		point n = new point(next, current);
		point t = new point(new point(0,0,1), current);
		double a = n.angleForm2(t);
		
		checkTurnDirection(t, n, a);
		
		if(Double.isNaN(a))
		{
			a = 0;
		}
		
		turn = a;
		
		/*double rot = Math.random() - .5;
		right = (.5 > Math.random());
		//rot = 0;
		rot = Math.acos(rot);
		rot = Math.toDegrees(rot);
		turn = rot;*/
	}
	
	//called to set a new heading for the object so that it turns
	protected void newHeading()
	{
		if(!ThreeD)
		{
			newHeadingXZ();
			newCount();
		}
		else
		{
			double dir = Math.random();
			newHeadingThreeD(dir);
		}
	}
	
	//If the object is able to move in three dimensions this function is called
	//this function will either have the object turn, adjust it's pitch or both
	protected void newHeadingThreeD(double dir)
	{
		if(dir < .25)
		{
			pitch = 0;
			newHeadingXZ();
		}
		else if(dir < .5)
		{
			newHeadingXZ();
			dir = (Math.random() * .5) + .5;
			newHeadingThreeD(dir);
		}
		else
		{
			double rot = (Math.random() *.8) + .2;
			rot = Math.acos(rot);
			rot = Math.toDegrees(rot);
			if(dir < .75)
			{
				pitch = rot;
			}
			else
			{
				pitch = rot * -1;
			}
			//heading.rotateX(rot);
			newCount();
		}
	}
	
	//to reduce redundant code, this function manages if the object is turning in 2 dimensions. 
	private void newHeadingXZ()
	{
		double rot = (Math.random() * 2) - 1;
		right = (.5 > Math.random());
		rot = Math.acos(rot);
		rot = Math.toDegrees(rot);
		if (right){rot *= -1;}
		turn = rot;
		//heading.rotateY(rot);
		turn();
		newCount();
	}
	
	//this gives the object a new count on how long to go before it changes it's heading
	protected void newCount()
	{
		//count = 20;
		count = (int) (Math.random() * 40) + 5;
	}
		
	//checks to see the next distance between this object and another based on their next move
	protected double nextDistance(genericMovement p2) 
	{
		point next = nextLoc();
		point next2 = p2.nextLoc();
		return next.distance(next2);
	}
	
	protected void trackTarget(point next)
	{
		if(turn == 0 && pitch == 0)
		{
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
		if (target != tar.getCurrent())
		{
			this.target = tar.getCurrent();
		}
		
		if (current.distance(target) < 10)
		{
			deleteTarget();
		}
	}
	//**************** END MOVEMENT FUNCTIONS****************//	
		
	
	//****************Collision Functions****************//
	
	//This is used to check if two objects are going to collide or not.
	//The function takes in an ArrayList of genericMovement objects
	//It's sister function can also be used which takes in an array of genericMovement objects
	public static void collisionCheck(ArrayList<genericMovement> points)
	{
		for(int i = 0; i < points.size(); i++)
		{
			for (int j = i+1; j < points.size(); j++)
			{				
				genericMovement p1 = points.get(i);
				genericMovement p2 = points.get(j);
				point move1 = p1.current;
				point move2 = p2.current;
				
				double d = move1.distance(move2);
				double d2 = p1.nextDistance(p2);
				if (d <= 50 && d >= d2)
				{
					if (p1.name.equals(p2.name))
					{
						if (!p1.name.equals("happyBee"))
						{
							p1.newHeading();
							p2.newHeading();
						}
					}
					else if(p1.name.equals("scout"))
					{
						p1.newHeading();
					}
					else if(p2.name.equals("scout"))
					{
						p2.newHeading();
					}
				}
			}
		}
	}
	
	//sister/overloaded function to the above
	public static void collisionCheck(genericMovement[] points)
	{
		for(int i = 0; i < points.length; i++)
		{
			for (int j = i+1; j < points.length; j++)
			{				
				genericMovement p1 = points[i];
				genericMovement p2 = points[j];
				point move1 = p1.current;
				point move2 = p2.current;
				
				double d = move1.distance(move2);
				double d2 = p1.nextDistance(p2);
				//checks if the distance between the current position and the next position is decreasing
				if (d <= 50 && d >= d2)
				{
					p1.newHeading();
					p2.newHeading();
				}
			}
		}
	}
		
	//this function is used to check if an object sees a point.
	// it is given a point, the sight distance and an angle
	//can also be used to check the proximity of two objects
	public boolean seeObject(point p, double d, double a)
		{
			if (current.distance(p) <= d)
			{
				point nLoc = nextLoc();
				point p2 = new point(p, current);
				double angle = nLoc.angle(current, p2);
				if (angle < 0){
					angle *= -1;
					}
				if (angle <= a)
				{
					return true;
				}
			}
			return false;
		}
	
	//this method is more used for overloading
	//this method can have a variety of uses depending on the need
	public void collisionCheckLocal(ArrayList<genericMovement> objects)
	{
		//This method is only for overloading
	}
	public void collisionCheckLocal(genericMovement[] objects, String r)
	{
		//This method is only for overloading
	}
	
	public void hit()
	{
		//used to tell an object that it has been hit
	}

	//get the distance between two genericMovement objects
	protected double distance(genericMovement g) {
		return this.current.distance(g.getCurrent());
	}
	
	//****************END COLLISION FUNCTIONS****************//
	
	
	//****************Set Functions****************//
	
	//this method is used to set the heading of an object such that it is heading towards another generic movement object
	public void setHeading(genericMovement genericMovement) 
	{
		heading = new point(genericMovement.current, this.current);
		heading.changeLength(speed);
	}

	//This method can be used to set the current location of the object
	public void setCurrent(point c)
	{
		this.current = c;
	}
	
	//sets the name for the object
	public void setName(String name)
		{
			this.name = name;
		}
		
	//sets the dimension of the object to allow potential 3d movement or go back to 2d movement
	public void setDimension(int dim)
		{
			if(dim == 2)
			{
				ThreeD = false;
				this.heading.y = 0;
				this.heading.changeLength(speed);
			}
			else if(dim == 3)
			{
				ThreeD = true;
			}
			else
			{
				System.out.println("Invalid dimension, no change applied");
			}
		}

	//sets the dimension of the object to allow potential 3d movement. Allows for toggling the dimension between 2d and 3d
	public void setDimension(boolean dim)
	{
		if(!dim)
		{
			ThreeD = false;
			this.heading.y = 0;
			this.heading.changeLength(speed);
		}
		else if(dim)
		{
			ThreeD = true;
		}
		else
		{
			System.out.println("Invalid dimension, no change applied");
		}
	}
	
	//toggles the dimension between 2d and 3d
	public void toggleDimension()
	{
		this.setDimension(!ThreeD);
	}
	
	//changes the count to a given value
	protected void setCount(int i)
	{
		if (i != -1){count = i;}
	}
	
	//Sets the pitch of the object
	public void setPitch(double d)
	{
		if(this.ThreeD)
		{
			this.pitch = d;
		}
		else
		{
			System.out.println("Error, This object cannot move in three dimensions");
		}
	}
	
	//sets the point the object will track
	public void setTarget(point p)
	{
		this.target = p;
	}
	
	//sets a genericMovement object the object has the potential to follow
	public void setTarget(genericMovement m)
	{
		this.tar = m;
		setTarget(tar.getCurrent());
	}
	
	//deletes the target data
	public void deleteTarget()
	{
		this.target = null;
		this.tar = null;
	}
	//Tells the object to stay in place and not move
	public void stay()
	{
		this.stay = true;
	}
	
	//tells the object to move
	public void move()
	{
		this.stay = false;
	}
	//****************END SET FUNCTIONS****************//
	
	
	//****************Get Functions****************//
	
	
	public String getName()
	{
		return this.name;
	}
	
	public point getCurrent()
	{
		return this.current;
	}
	
	public point getHeading()
	{
		return this.heading;
	}
	
	public point getTarget()
	{
		return this.target;
	}
	
	public genericMovement getTargetObject()
	{
		return this.tar;
	}
	//****************END GET FUNCTIONS****************//
	
	
	//****************MISC Functions****************//
	@Override
	public String toString()
	{
		String out  = "";
		out = out + "Name: " + name;
		out = out + " Current Location: " + current;
		out = out + " Current Heading:" + heading;
		out = out + " Current Speed: " + speed;
		return out;
	}
}

