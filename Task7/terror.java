
public class terror extends genericMovement {
	private double slope = 0.00833333333333334; //approximately right
	private double weight = 1000;
	private double roll = 0;
	
	public terror()
	{
		super("Terror", true);
		this.setCurrent(new point(-100,00,100));
		//this.setCurrent(new point(500,200,500));
		this.heading = new point(-10,0,-10);
		//this.heading.rotateY(45);
		this.speed = 12.0;
		//this.heading.changeLength(speed);
	}
	
	@Override
	public point[] nextLocation()
	{
		int test = 4;
		this.setCount(50);
		if(weight > 0){this.weight -= this.speed;}
		double currLift = weight/1000;
		double genLift = (this.speed * 10) * slope;
		//double liftAngle = currLift - genLift;
		double liftAngle = genLift - currLift;
		double a = Math.sin(liftAngle);
		a = Math.toDegrees(a);
		point p = new point(heading);
		p.convertRotation();
		double y = p.y;
		this.heading.rotateX(-y);
		this.heading.rotateX(a);
		//level test
		
		switch(test)
		{
			case 1:
				if(this.getCurrent().y > 200)
				{
					double n = currLift - (liftAngle/2);
					this.speed = (n/slope)/10;
				}
				else if(this.getCurrent().y < 200)
				{
					double n = currLift + (liftAngle/2);
					this.speed = (n/slope)/10;
				}
				break;
			case 2:
				if(a != 20)
				{
					double n = Math.toRadians(20);
					
					this.speed = ((n + currLift)/slope)/10;
				}
				break;
			case 3:
				//circles
				//turn  function of sin
				if (roll == 0)
					roll = 5;
				
				heading.rotateY(Math.toDegrees(Math.sin(Math.toRadians(roll))));
				double lift = Math.toRadians(roll);
				lift = Math.cos(lift);
				liftAngle = (genLift * lift) - currLift;
				double a2 = Math.sin(liftAngle);
				a2 = Math.toDegrees(a2);
				p.convertRotation();
				this.heading.rotateX(-a);
				this.heading.rotateX(a2);
				//heading.rotateX(Math.toDegrees(Math.toRadians(roll)));
				
				//lift function of cosine
				if(this.getCurrent().y > 200)
				{
					double n = 0;
					if(lift != 0)
					{
						n = currLift  * (1/lift);
						double nSpeed = (n/slope)/10;
						double oldSpeed = this.speed;
						this.speed = nSpeed;
						this.roll = (this.roll * (nSpeed/oldSpeed));
					}
					else
					{
						this.speed = 0;
						this.roll = 0;
					}
				}
				else if(this.getCurrent().y < 200)
				{
					double n = 0;
					if(lift != 0)
					{
						n = currLift  * (1/lift);
						double nSpeed = (n/slope)/10;
						double oldSpeed = this.speed;
						this.speed = nSpeed;
						if (!Double.isNaN(nSpeed/oldSpeed))
							this.roll = (this.roll * (nSpeed/oldSpeed));
						else
							this.roll = 0;
					}
					else
					{
						this.speed = 0;
						this.roll = 0;
					}
					
				}
				break;
			case 4:
				//upward spiral
				if (roll == 0)
					roll = 5;
				
				heading.rotateY(Math.toDegrees(Math.sin(Math.toRadians(roll))));
				lift = Math.toRadians(roll);
				lift = Math.cos(lift);
				liftAngle = (genLift * lift) - currLift;
				a2 = Math.sin(liftAngle);
				a2 = Math.toDegrees(a2);
				p.convertRotation();
				this.heading.rotateX(-a);
				this.heading.rotateX(a2);
				if(a2 != 20)
				{
					double n = Math.toRadians(20);
					if(lift != 0)
					{
						double nSpeed = ((n +  (currLift * (1/lift)))/slope)/10;
						double oldSpeed = this.speed;
						this.speed = nSpeed;
						this.roll = (this.roll * (nSpeed/oldSpeed));
					}
				}
				break;
		}
		System.out.println(roll);
		point [] points = super.nextLocation();
		points[1].x = roll * -1;
		return points;
	}
	
	public boolean weightCheck()
	{
		return this.weight > 0;
	}
	
	@Override
	protected void newHeading()	{/*Overrided so it doesn't mess with the flight*/}
	
	@Override
	protected void flipHeading(){/*Overrided so it doesn't mess with the flight*/}
	
	@Override
	protected void newHeadingThreeD(double dir)	{/*Overrided so it doesn't mess with the flight*/}
}
