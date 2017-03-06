
public class blimp extends genericMovement {
	
	char genHeading = 'E';
	point N = new point(200,0,200); 
	point E = new point(200,0,-200);
	point S = new point(-200,0,-200);
	point W = new point(-200,0,200);
	point r1 = null;
	point r1Home = null;
	point r2 = null;
	point r2Home = null;
	boolean move = false;
	double displace = 0;
	boolean main;
	
	public blimp()
	{
		super();
		heading = new point(10,0,0);
		heading.changeLength(speed);
		count = 500;
	}
	
	public blimp(point p)
	{
		super();
		current = p;
		heading = new point(10,0,0);
		count = 500;
	}
	
	@Override
	public point[] nextLocation()
	{
		if(!(r1 == null) && !(r2 == null))
		{
			//System.out.println("Finding position!");
			move = true;
			point myLocation = triangulate2();
			adjust_position(myLocation);
			double a;
			/*if(turn == 0)
			{
				a = solveAngle(myLocation);
				turn = a;
			}*/
		
		}
		else if(displace != 0 && !main)
		{
			adjust_position();
		}
		//if(r1 != null){System.out.println("r1 is not null");}
		//else{System.out.println("r1 is null");}
		r1 = null;
		//if(r2 != null){System.out.println("r2 is not null");}
		//else{System.out.println("r2 is null");}
		r2 = null;
		checkNextTarget();
		count = 20;
		if(move)
			return super.nextLocation();
		else
		{
			point c = this.current;
			point[] p = super.nextLocation();
			p[0] = c;
			this.current = c;
			return p;
		}
	}
	
	

	public void copyData(blimp b)
	{
		this.move = b.move;
		this.turn = b.turn;
		this.right = b.right;
		this.heading = b.heading;
		this.genHeading = b.genHeading;
		this.displace = b.displace;
		b.displace = 0;
	}
	
	private void checkNextTarget() {
		switch(genHeading){
		case 'N':
			if (current.distance(N) < 10)
			{
				genHeading = 'W';
				heading = new point(-10,0,0);
				heading.changeLength(speed);
			}
			break;
		case 'E':
			if (current.distance(E) < 10)
			{
				genHeading = 'N';
				heading = new point(0,0,10);
				heading.changeLength(speed);
			}
			break;
		case 'S':
			if (current.distance(S) < 10)
			{
				genHeading = 'E';
				heading = new point(10,0,0);
				heading.changeLength(speed);
			}
			break;
		case 'W':
			if (current.distance(W) < 10)
			{
				genHeading = 'S';
				heading = new point(0,0,-10);
				heading.changeLength(speed);
			}
			break;
	}
	}

	private double solveAngle(point myLocation) {
		// TODO Auto-generated method stub
		double a = 0;
		point p;
		point nLoc = myLocation.add(heading);
		switch(genHeading){
			case 'N':
				p = new point(N,myLocation);
				a = nLoc.angle(myLocation, p);
				break;
			case 'E':
				//p = new point(E,myLocation);
				a = nLoc.angle(myLocation, E);
				//System.out.println(a);
				break;
			case 'S':
				p = new point(S,myLocation);
				a = nLoc.angle(myLocation, p);
				break;
			case 'W':
				p = new point(W,myLocation);
				a = nLoc.angle(myLocation, p);
				break;
		}
		//if(a < 0){right = true;}
		return a;
	}

	private void adjust_position(point myLocation) {
		double d = 0;
		point nLoc = myLocation.add(heading);
		switch(genHeading){
			case 'N':
				d = myLocation.x - N.x;
				//System.out.println(myLocation + " distance: " + d);
				current.x -= d;
				break;
			case 'E':
				d = myLocation.z - E.z;
				//System.out.println(myLocation + " distance: " + d);
				current.z -= d;
				break;
			case 'S':
				d = myLocation.x - S.x;
				//System.out.println(myLocation + " distance: " + d);
				current.x -= d;
				break;
			case 'W':
				d = myLocation.z - W.z;
				//System.out.println(myLocation + " distance: " + d);
				current.z -= d;
				break;
		}
		this.displace = d;
	}
	
	private void adjust_position() {
		switch(genHeading){
			case 'N':
				current.x -= displace;
				break;
			case 'E':
				current.z -= displace;
				break;
			case 'S':
				current.x -= displace;
				break;
			case 'W':
				current.z -= displace;
				break;
		}
		displace = 0;
	}

	
	private point triangulate() {
		double AB = r1Home.distance(r2Home);
		double AC = r1.distance(r1Home);
		double BC = r2.distance(r2Home);
		//Calculate the X coordinate
		double xVal = Math.pow(AC, 2);
		xVal = xVal + Math.pow(AB, 2);
		xVal = xVal - Math.pow(BC, 2);
		xVal = xVal/2;
		xVal = xVal/AB;
		//Calculate the Y coordinate
		double yVal = -1 * Math.pow(xVal, 2);
		yVal = yVal + Math.pow(AC, 2);
		if(yVal < 0)
			yVal = Math.sqrt(yVal * -1) * -1;
		else
			yVal = Math.sqrt(yVal);
		//Create the new point
		point p = new point(xVal,0,yVal);
		point pTest = new point(xVal * -1,0,yVal);
		point pTest2 = new point(xVal * -1,0,yVal);
		/*System.out.println("p: " + p);
		System.out.println("ptest: " + pTest);
		System.out.println("ptest2: " + pTest2);*/
		point p2 = new point(this.r2Home, this.r1Home);
		double a = p.angle(new point(0,0,0), p2);
		double aTest = pTest.angleNoTrans(new point(0,0,0), p2);
		double aTest2 = pTest2.angleNoTrans(new point(0,0,0), p2);
		//Translate the point to it's proper location
		//This requires rotation then translation
		p.rotateY(a/2);
		p = new point(p.x + r1Home.x, p.y + r1Home.y, p.z + r1Home.z);
		pTest.rotateY(aTest/2);
		pTest = new point(pTest.x + r1Home.x, pTest.y + r1Home.y, pTest.z + r1Home.z);
		pTest2.rotateY(aTest/2);
		pTest2 = new point(pTest2.x + r1Home.x, pTest2.y + r1Home.y, pTest2.z + r1Home.z);
		/*System.out.println("p: " + p);
		System.out.println("ptest: " + pTest);
		System.out.println("ptest2: " + pTest2);*/
		
		return pTest;
	}
	
	private point triangulate2()
	{
		double AB = r1Home.distance(r2Home);
		double AC = r1.distance(r1Home);
		double BC = r2.distance(r2Home);
		double zLen = Math.abs(r1Home.z) + Math.abs(r2Home.z);
		double xLen = Math.abs(r1Home.x) + Math.abs(r2Home.x);
		//angles in right triangle between Station1 and Station2
		double a = ((zLen * zLen) + (AB * AB)) - (xLen * xLen);
		double d = (2 * zLen * AB);
		a = a/d;
		a = Math.toDegrees(Math.acos(a));
		double b = 90 - a;
		//angles in triangle formed by blimp, Station1, and Station2
		double a1 = ((AC * AC) + (AB * AB)) - (BC * BC);
		a1 = a1 / (2 * AC * AB);
		a1 = Math.toDegrees(Math.acos(a1));
		double a2 = ((BC * BC) + (AB * AB)) - (AC * AC);
		a2 = a2 / (2 * BC * AB);
		a2 = Math.toDegrees(Math.acos(a2));
		//find angles of right triangles between station1, blimp and arbitrary point
		double aTop = a - a1;
		//find angles of right triangle between station2, blimp and arbitrary point
		double aBottom = b - a2;
		//use SOHCAHTOA to find sides of above 2 triangles
		double x1 = Math.sin(Math.toRadians(aTop)) * AC;
		double z1 = Math.cos(Math.toRadians(aTop)) * AC;
		
		double x2 = Math.sin(Math.toRadians(aBottom)) * AC;
		double z2 = Math.cos(Math.toRadians(aBottom)) * AC;
		//Derive from sides where point is
		double pointX,pointZ;
		pointX = closerPoint(r2Home.x, r1Home.x, x1, x2);
		pointZ = closerPoint(r2Home.z, r1Home.z, z1, z2);
		
		point p = new point(pointX, 0, pointZ);
		return p;
	}
	
	private double closerPoint(double home2, double home1, double x1, double x2) {
		// TODO Auto-generated method stub
		double[] points = {home2 + x2, home2 - x2, home1 - x1, home1 + x1};
		int i = 0;
		double d = 500;
		if(genHeading == 'W' || genHeading == 'N')
		{
			for(int j = 0; j < points.length; j++)
			{
				if(Math.abs(points[j] - 200) < d)
				{
					d = Math.abs(points[j] - 200);
					i = j;
				}
			}
		}
		else if(genHeading == 'E' || genHeading == 'S')
		{
			for(int j = 0; j < points.length; j++)
			{
				if(Math.abs(points[j] - (200 * -1)) < d)
				{
					d = Math.abs(points[j] - (200 * -1));
					i = j;
				}
			}
		}
		return points[i];
	}

	@Override
	public void collisionCheckLocal(genericMovement[] rays, String r)
	{
		boolean in_range = false;
		int i = 0;
		while(!in_range && i < rays.length)
		{
			if(this.seeObject(rays[i].current, 2.5, 360))
			{
				in_range = true;
				//System.out.println("This is in range:" + rays[i].current);
			}
			else
				i++;
		}
		if(in_range)
		{
			ray t = (ray)rays[i];
			if(r.equals("r1"))
			{
				r1 = t.current;
				r1Home = t.base;
			}
			else if(r.equals("r2"))
			{
				r2 = t.current;
				r2Home = t.base;
			}
		}
	}
	
	public boolean hasRay(String ray)
	{
		if(ray.equals("r1"))
			return !(r1 == null);
		else
			return !(r2 == null);
	}
}
