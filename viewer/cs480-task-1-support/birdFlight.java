import java.util.ArrayList;


public class birdFlight {
	private static final double variationRange = 10;
	public static final double maxDist = 50;
	public static final double variation = 5;
	public static final double maxAngle = 30;
	
	public static ArrayList<point> startingPoint()
	{
		ArrayList<point> firstPoint = new ArrayList<point>();
		firstPoint = addPoint(firstPoint);
		return firstPoint;
	}

	public static ArrayList<point> startingPoint(double x, double y, double z)
	{
		ArrayList<point> firstPoint = new ArrayList<point>();
		firstPoint = addPoint(firstPoint, x, y, z);
		return firstPoint;
	}
	
	public static ArrayList<point> addPoint(ArrayList<point> list)
	{
		double x = 1000 * Math.random();
		double y = 490 * Math.random() + 10;
		double z = 1000 * Math.random();
		int lastIndex = list.size() - 1;
		if (x > 500){x = (x - 500) * -1;}
		if (z > 500){z = (z - 500) * -1;}
		point nPoint = new point(x, y, z);
		/*if (list.size() > 0){
			point prior = list.get(list.size() - 1);
			list = breakUpSegment(prior, nPoint, list, 0);
			//list = fixAngleManage(list, lastIndex);
		}
		else{
			list.add(nPoint);
		}*/
		list.add(nPoint);
		return list;
	}
	
	public static ArrayList<point> addPoint(ArrayList<point> list, double x, double y, double z)
	{
		int lastIndex = list.size() - 1;
		point nPoint = new point(x, y, z);
		if (list.size() > 0){
			point prior = list.get(list.size() - 1);
			list = breakUpSegment(prior, nPoint, list, 0);
			list = fixAngleManage(list, lastIndex);
		}
		else{
			list.add(nPoint);
		}
		//list.add(nPoint);
		return list;
	}
		
	private static ArrayList<point> fixAngleManage(ArrayList<point> list, int lastIndex)
	{
		if (lastIndex == 0){
			lastIndex++;
		}
		point p1 = list.get(lastIndex - 1);
		point p2 = list.get(lastIndex);
		point p3 = list.get(lastIndex + 1);
		
		list = fixAngle(p1, p2, p3, lastIndex, list, 0);
		
		return list;
	}
	
	private static ArrayList<point> fixAngle(point p1, point p2, point p3, int lastIndex, ArrayList<point> list, int count)
	{
		point ptemp = new point(p1, p2);
		double angle = p3.angle(p2, ptemp);
		if (count < 5){
		if ((180 - maxAngle) > angle || angle > (180 + maxAngle))
		{
			count++;
			//list.remove(lastIndex);
			list.remove(p2);
			//lastIndex--;
			list = splitSegment(p1, lastIndex, p3, list);
			//list = divideSegment(p1, lastIndex, p3, list);
			lastIndex--;
			point right = p3;
			p2 = p1;
			if (lastIndex == 0){/*p2 = list.get(lastIndex + 1); p3 = list.get(lastIndex + 2); lastIndex++;*/}
			else{p1 = list.get(lastIndex - 1); p3 = list.get(lastIndex + 1); list = fixAngle(p1,p2,p3,lastIndex,list, count);}
			
			
			lastIndex = list.indexOf(right);
			p2 = right;
			if (lastIndex >= list.size() - 1){lastIndex = list.size() - 2;}
			else if (lastIndex <= 0){lastIndex = 1;}
			else{
				p1 = list.get(lastIndex - 1);
				p3 = list.get(lastIndex + 1);
				list = fixAngle(p1,p2,p3,lastIndex,list, count);
			}
			
		}
		}
		return list;
	}

	private static ArrayList<point> breakUpSegment(point left, point right, ArrayList<point> list, int count)
	{
		double dist = left.distance(right);
		if (dist > maxDist && count < 20)
		{
			count++;
			point mid = calcMid(left, right);
			//mid = varyPoint(mid);
			list = breakUpSegment(left, mid, list, count);
			list = breakUpSegment(mid, right, list, count);
		}
		else
		{
			if(!checkPrior(left, list)){list.add(left);}
			if(!checkPrior(right, list)){list.add(right);}
		}
		return list;
	}
	
	private static ArrayList<point> splitSegment(point left, int leftIndex, point right, ArrayList<point> list) 
	{
		// TODO Auto-generated method stub
		double x = left.x + right.x;
		double y = left.y + right.y;
		double z = left.z + right.z;
		double x2 = x/3;
		double y2 = y/3;
		double z2 = z/3;
		point left1 = new point(x2, y2, z2);
		point right1 = new point(x * (2/3), y * (2/3), z * (2/3));
		point mid2 = calcMid(left, right);
		//if(!checkPrior(right, list)){list.add(leftIndex, right);}
		point mid3 = calcMid(mid2, right);
		point mid1 = calcMid(left, mid2);
		
		//if(!checkPrior(right, list)){list.add(leftIndex, right1);}
		//if(!checkPrior(right, list)){list.add(leftIndex, left1);}
		if(!checkPrior(right, list)){list.add(leftIndex, mid3);}
		if(!checkPrior(right, list)){list.add(leftIndex, mid2);}
		if(!checkPrior(right, list)){list.add(leftIndex, mid1);}
		//if(!checkPrior(left, list)){list.add(leftIndex, left);}
		//list = divideSegment(mid, list.indexOf(mid), right, list);
		//list = divideSegment(left, leftIndex, mid, list);
		return list;
	}

	
	private static ArrayList<point> divideSegment(point left, int leftIndex, point right, ArrayList<point> list)
	{
		double dist = left.distance(right);
		if (dist > maxDist)
		{
			point mid = calcMid(left, right);
			list = divideSegment(mid, leftIndex, right, list);
			list = divideSegment(left, leftIndex, mid, list);
		}
		else
		{
			if(!checkPrior(right, list)){list.add(leftIndex, right);}
			if(!checkPrior(left, list)){list.add(leftIndex, left);}
		}
		return list;
	}
	
	private static point calcMid(point left, point right) 
	{
		double x = left.x + right.x;
		double y = left.y + right.y;
		double z = left.z + right.z;
		x = x/2;
		y = y/2;
		z = z/2;
		point ret = new point(x, y, z);
		return ret;
	}

	private static boolean checkPrior(point check, ArrayList<point> list)
	{
		point last = list.get(list.size() - 1);
		return last.equals(check);
		
	}

	public static point calcRotate(point current, point next) {
		point r = new point(next, current);
		r.convertRotation();
		
		return r;
	}
	
	private static point varyPoint(point p)
	{
		//p.x = p.x + (Math.random() * variationRange) - variation;
		p.y = p.y + (Math.random() * variationRange) - variation;
		//p.z = p.z + (Math.random() * variationRange) - variation;
		return p;
	}
	
	/* private static void fixAngles(ArrayList<point> list, int lastIndex) {
	//int curIndex = lastIndex;
	if (lastIndex == 0){lastIndex++;}
	int curIndex = lastIndex;
	while( curIndex != list.size()-1)
	{
		point p1 = list.get(curIndex - 1);
		point p2 = list.get(curIndex);
		point p3 = list.get(curIndex + 1);
		//check angle
		point ptemp = new point(p1, p2);
		double angle = p3.angle(p2, ptemp);
		//if outside range adjust
		if ((180 - maxAngle) > angle || angle > (180 + maxAngle))
		{
			//find perpendicular vector P4
			double x = (p1.y * p3.z) - (p3.y * p1.z); 
			double y = (p1.z * p3.x) - (p1.x * p3.z); 
			double z = (p1.x * p3.y) - (p1.y * p3.x); 
			point pPerp = new point(x, y, z);
			double len = pPerp.length();
			pPerp.x = pPerp.x / len;
			pPerp.y = pPerp.y / len;
			pPerp.z = pPerp.z / len;
			len = pPerp.length();
			x = p3.x;
			y = p3.y;
			z = p3.z;
			//rotate such that P1 and P4 and aligned with Z and Y Axis
			double angleAdjust = 0;
			if (angle > (180 + maxAngle)){angleAdjust = -1 * (angle - (180 + maxAngle));}
			else if (angle < (180 - maxAngle)){angleAdjust = -1 * ((180 - maxAngle) - angle);}
			angleAdjust = Math.toRadians(angleAdjust);
			double moveX, moveY, moveZ;
			//abc = p2, xyz = p3, uvw = pPerp
			moveX = (p2.x * (Math.pow(pPerp.y,2) + Math.pow(pPerp.z,2)) - pPerp.x * (p2.y * pPerp.y + p2.z * pPerp.z - pPerp.x * x - pPerp.y * y - pPerp.z * z)) * (1-Math.cos(angleAdjust)) + x * Math.cos(angleAdjust) +(-p2.z * pPerp.y + p2.y * pPerp.z - pPerp.z * y + pPerp.y * z) * Math.sin(angleAdjust);
			moveY = (p2.y * (Math.pow(pPerp.x,2) + Math.pow(pPerp.z,2)) - pPerp.y * (p2.x * pPerp.x + p2.z * pPerp.z - pPerp.x * x - pPerp.y * y - pPerp.z * z)) * (1-Math.cos(angleAdjust)) + y * Math.cos(angleAdjust) +(p2.z * pPerp.x - p2.x * pPerp.z + pPerp.z * x - pPerp.x * z) * Math.sin(angleAdjust);
			moveZ = (p2.z * (Math.pow(pPerp.x,2) + Math.pow(pPerp.y,2)) - pPerp.z * (p2.x * pPerp.x + p2.y * pPerp.y - pPerp.x * x - pPerp.y * y - pPerp.z * z)) * (1-Math.cos(angleAdjust)) + z * Math.cos(angleAdjust) +(-p2.y * pPerp.x + p2.x * pPerp.y - pPerp.y * x + pPerp.x * y) * Math.sin(angleAdjust);
			//rotate P3 to be at edge of range
			p3 = new point (moveX, moveY, moveZ);
			list.set(curIndex + 1, p3);
			try{
				point p4 = list.get(curIndex+2);
				if (p3.distance(p4) > 100)
					list = divideSegment(p3,p4, list, 0);
			}
			catch(Exception e){}
		}
		curIndex++;
		//list = divideSegment(p3, list.get(curIndex + 2), list);
	}
	String s = "ran loop";
	}*/
}
