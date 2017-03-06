
public class point {
	double x;
	double y;
	double z;
	double len;
	
	public point()
	{
		x = 0;
		y = 0;
		z = 0;
		len = length();
	}
	
	public point (double X, double Y, double Z)
	{
		x = X;
		y = Y;
		z = Z;
		len = length();
	}
	
	public point (point p1, point trans)
	{
		x = p1.x - trans.x;
		y = p1.y - trans.y;
		z = p1.z - trans.z;
		len = length();
	}
	
	public point(point p) {
		x = p.x;
		y = p.y;
		z = p.z;
		len = length();
	}

	double distance (point p)
	{
		double xDist = p.x - this.x;
		double yDist = p.y - this.y;
		double zDist = p.z - this.z;
		double xzDist = Math.sqrt(Math.pow(xDist, 2) + Math.pow(zDist, 2));
		double dist = Math.sqrt(Math.pow(xzDist, 2) + Math.pow(yDist, 2));
		return dist;
	}
	
	boolean equal (point p)
	{
		boolean status = true;
		status = status && (this.x == p.x);
		status = status && (this.y == p.y);
		status = status && (this.z == p.z);
		return status;
	}

	public double length()
	{
		double len = Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
		len = Math.sqrt(len);
		return len;
	}
	//ASSUMES POINT HAS END AT 0,0,0
	@SuppressWarnings("unused")
	public void convertRotation()
	{
		double rotxz;
		double rotyz;
		double len = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
		double dot = (x * 0) + (z * 10);
		if (len > 0){
			double val = dot/(len * 10);
			rotxz = Math.acos(val);
		}
		else{rotxz = 0;	}
		
		double rotZ, rotX;
		if (x >= 0){
			rotZ = (x * Math.sin(rotxz)) + (z * Math.cos(rotxz));
			rotX = (x * Math.cos(rotxz)) - (z * Math.sin(rotxz));
		}
		else{
			rotZ = (-x * Math.sin(rotxz)) + (z * Math.cos(rotxz));
			rotX = (-x * Math.cos(rotxz)) - (z * Math.sin(rotxz));
		}
		double rotY = y;

		double len2 = Math.sqrt(Math.pow(rotY, 2) + Math.pow(rotZ, 2));
		double dot2 = (rotY * 0) + (rotZ * 10);
		if (len2 > 0){
			double val2 = dot2/(len2 * 10);
			rotyz = Math.acos(val2);
		}
		else{rotyz = 0;	}
		
		if (x < 0){	z = Math.toDegrees(rotxz) * -1;}
		else{z = Math.toDegrees(rotxz);}
		if (y < 0){	y = Math.toDegrees(rotyz) * -1;}
		else{y = Math.toDegrees(rotyz);}
		//if (y > 90 || y < -90) {x = 180;}
		//else{x = 0;}
		x = 0;
		while (y > 91){ y = y - 90;}
		while (y < -91){ y = y + 90;}
	}
	
	public double angle(point center, point p)
	{
		double nX = this.x - center.x;
		double nY = this.y - center.y;
		double nZ = this.z - center.z;
		
		double rotation;
		double len1 = Math.sqrt(Math.pow(nX, 2) + Math.pow(nZ, 2) + Math.pow(nY, 2));
		double len2 = Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.z, 2) + Math.pow(p.y, 2));
		double dot = (nX * p.x) + (nZ * p.z) + (nY * p.y);
		if (len1 > 0 && len2 > 0){
			double val = dot/(len1 * len2);
			rotation = Math.acos(val);
		}
		else{rotation = 0;}
		rotation = Math.toDegrees(rotation);
		return rotation;
	}

	public void rotateAxis(point p1, point p2) {
		// TODO Auto-generated method stub
		double rotxz, rotyz, rotxy;
		double len = Math.sqrt(Math.pow(p1.x, 2) + Math.pow(p1.z, 2));
		double dot = (p1.x * 0) + (p1.z * 10);
		if (len > 0){
			double val = dot/(len * 10);
			rotxz = Math.acos(val);
		}
		else{rotxz = 0;	}
		
		double P1rotZ, P1rotX, P1rotY, P2rotZ, P2rotX, P2rotY;
		if (x >= 0){
			P1rotZ = (p1.x * Math.sin(rotxz)) + (p1.z * Math.cos(rotxz));
			P1rotX = (p1.x * Math.cos(rotxz)) - (p1.z * Math.sin(rotxz));
			P2rotZ = (p2.x * Math.sin(rotxz)) + (p2.z * Math.cos(rotxz));
			P2rotX = (p2.x * Math.cos(rotxz)) - (p2.z * Math.sin(rotxz));
		}
		else{
			P1rotZ = (-p1.x * Math.sin(rotxz)) + (p1.z * Math.cos(rotxz));
			P1rotX = (-p1.x * Math.cos(rotxz)) - (p1.z * Math.sin(rotxz));
			P2rotZ = (-p2.x * Math.sin(rotxz)) + (p2.z * Math.cos(rotxz));
			P2rotX = (-p2.x * Math.cos(rotxz)) - (p2.z * Math.sin(rotxz));
		}

		double len2 = Math.sqrt(Math.pow(p1.y, 2) + Math.pow(P1rotZ, 2));
		double dot2 = (p1.y * 0) + (P1rotZ * 10);
		if (len2 > 0){
			double val2 = dot2/(len2 * 10);
			rotyz = Math.acos(val2);
		}
		else{rotyz = 0;	}
		
		P2rotY = (p2.y * Math.cos(rotyz)) + (P2rotZ * Math.sin(rotyz));
		
		double len3 = Math.sqrt(Math.pow(P2rotY, 2) + Math.pow(P2rotX, 2));
		double dot3 = (P2rotY * 10) + (P2rotX * 0);
		if (len3 > 0){
			double val3 = dot3/(len3 * 10);
			rotxy = Math.acos(val3);
		}
		else{rotxy = 0;	}
		
		this.x = rotxz;
		this.y = rotyz;
		this.z = rotxy;
	}

	public void changeLength(double speed) {
		// TODO Auto-generated method stub
		x = x/len;
		y = y/len;
		z = z/len;
		x = x * speed;
		y = y * speed;
		z = z * speed;
		len = length();
	}

	public point add(point heading) {
		double x2 = x + heading.x;
		double y2 = y + heading.y;
		double z2 = z + heading.z;
		return new point(x2, y2, z2);
	}
	
	public void rotateY(double degree){
		double l = length();
		double r = Math.toRadians(degree);
		x = (x * Math.cos(r)) - (z * Math.sin(r));
		z = ((x * Math.sin(r))) + (z * Math.cos(r)) ;
		
		len = length();
		changeLength(l);
	}

	public boolean boundcheck(int i, int j, int k, int l, int m, int n) {
		// TODO Auto-generated method stub
		boolean status = false;
		if (x >= i || x <= j){status = true;}
		if (y >= k || y <= l){status = true;}
		if (z >= m || z <= n){status = true;}
		return status;
	}
}
