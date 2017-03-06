import java.awt.geom.Line2D;


public class fishBrain {
	point heading;
	point current;
	int count;
	int right = -1;
	boolean rightD = false;
	double swimAngle = 0;
	protected double speed = 20;
	
	public fishBrain ()
	{
		double x = Math.random() * 2;
		if (x > 1){x -= 1; x = x *-1;}
		double y = 0;
		double z = Math.random() * 2;
		if (z > 1){z -= 1; z = z *-1;}
		heading = new point(x,y,z);
		heading.changeLength(speed);
		initRot();
		newCount();
		x = (Math.random() * 800);
		if (x > 400){x -= 400; x = x *-1;}
		y = 0;
		z = (Math.random() * 800);
		if (z > 400){z -= 400; z = z *-1;}
		current = new point(x, y, z);
	}
	
	public point[] nextLocation()
	{
		if (count <= 0){
			newHeading();
		}
		point nloc = current.add(heading);
		
		if (nloc.boundcheck (400, -400, 200,-200, 400, -400))
		{
			count -= 2;
		}
		else{
			count--;
		}
		if (nloc.boundcheck (490, -490, 200,-200, 490, -490))
		{
			flipHeading();
			nloc = current.add(heading);
		}
		rotHead();
		point rot = new point(heading.x, heading.y, heading.z);
		rot.convertRotation();
		current = nloc;
		point [] ret = {current, rot};
		return ret;
	}

	private void flipHeading() {
		double rot = Math.random() - .5;
		//rot = 0;
		rot = Math.acos(rot);
		rot = Math.toDegrees(rot);
		heading.rotateY(rot);
		//double angle = heading.angle(new point(0,0,0), new point(0,0,1));
		//System.out.println("rot:" + rot + " currentAngle:" + angle);
		initRot();
		newCount();
	}

	protected void newHeading() {
		double rot = (Math.random());
		//rot = 90;
		rot = Math.asin(rot);
		rot = Math.toDegrees(rot);
		heading.rotateY(rot);
		//double angle = heading.angle(new point(0,0,0), new point(0,0,1));
		//System.out.println("rot:" + rot + " currentAngle:" + angle);
		initRot();
		newCount();
	}
	
	private void newCount(){
		count = (int) (Math.random() * 10) + 5;
	}
	
	private void initRot(){
		if (rightD){
			swimAngle = Math.random() * 2;
			heading.rotateY(swimAngle);
			right = 1;
			rightD = false;
		}
		else if (!rightD){
			swimAngle = Math.random() * -2;
			heading.rotateY(swimAngle);
			right = -1;
			rightD = true;
		}
	}
	
	private void rotHead(){
		if (right == 0)
			swimAngle = Math.random() * 4;
		if (rightD && right == 0){
			heading.rotateY(swimAngle);
			right++;
			rightD = false;
		}
		else if (!rightD && right == 0){
			swimAngle *= -1;
			heading.rotateY(swimAngle);
			right--;
			rightD = true;
		}
		else
		{
			heading.rotateY(swimAngle * -1);
			right = 0;
		}
	}
	
	protected void changeCount(int i) {
		if (i != -1){count = i;}
	}
	
	public static fishBrain[] avoidCollision(fishBrain[] fish)
	{
		for (int i = 0; i < fish.length; i++)
		{
			for (int j = i + 1; j < fish.length; j++)
			{
				int[] t = checkCollision(fish[i], fish[j]);
				fish[i].changeCount(t[0]);
				fish[j].changeCount(t[1]);
			}
		}
		return fish;
	}

	private static int[] checkCollision(fishBrain f1, fishBrain f2) 
	{
		point fish1 = f1.getCurrent();
		point fish2 = f2.getCurrent();
		double d = fish1.distance(fish2);
		double d2 = f1.nextDistance(f2);
		if (d <= 120 && d >= d2)
		{
			int[] i = {0,0};
			return i;
		}
		int[] i = {-1,-1};
		return i;
	}

	private double nextDistance(fishBrain f2) {
		point next = current.add(heading);
		point next2 = f2.current.add(f2.heading);
		return next.distance(next2);
	}

	public point getCurrent() {
		return current;
	}

}
