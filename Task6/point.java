
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

	public point(point p, char d1, char d2)
	{
		if(d1 == 'x' || d2 == 'x')
		{
			x = p.x;
		}
		else
		{
			x = 0;
		}
		
		if(d1 == 'y' || d2 == 'y')
		{
			y = p.y;
		}
		else
		{
			y = 0;
		}
		
		if(d1 == 'z' || d2 == 'z')
		{
			z = p.z;
		}
		else
		{
			z = 0;
		}
		
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
		double dot = this.dot(0, 0, 10);
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
		double dot = p.dot(nX, nY, nZ);
		point cross = p.cross(nX, nY, nZ);
		double lenCross = cross.len;
		rotation = lenCross / dot;
		rotation = Math.atan(rotation);
		rotation = Math.toDegrees(rotation);
		rotation *= -1;
		return rotation;
	}

	public double angle(point p)
	{
		double rotation;
		double len1 = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.z, 2) + Math.pow(this.y, 2));
		double len2 = Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.z, 2) + Math.pow(p.y, 2));
		double dot = p.dot(this.x, this.y, this.z);
		point cross = p.cross(this.x, this.y, this.z);
		double lenCross = cross.len;
		rotation = lenCross / dot;
		rotation = Math.atan(rotation);
		rotation = Math.toDegrees(rotation);
		//rotation *= -1;
		return rotation;
	}

	public double angleForm2(point p)
	{
		double rotation;
		double len1 = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.z, 2) + Math.pow(this.y, 2));
		double len2 = Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.z, 2) + Math.pow(p.y, 2));
		double dot = p.dot(this.x, this.y, this.z);
		point cross = p.cross(this.x, this.y, this.z);
		double lenCross = cross.len;
		rotation = dot / (len1 * len2);
		rotation = Math.acos(rotation);
		rotation = Math.toDegrees(rotation);
		//rotation *= -1;
		return rotation;
	}
	
	private point cross(double nX, double nY, double nZ) {
		double x1 = this.y * nZ - this.z * nY;
		double y1 = this.z * nX - this.x * nZ;
		double z1 = this.x * nY - this.y * nX;
		return new point(x1, y1, z1);
	}

	private double dot(double nX, double nY, double nZ)
	{
		return (nX * this.x) + (nZ * this.z) + (nY * this.y);
	}
	
	public void rotateAxis(point p1, point p2) {
		double rotxz, rotyz, rotxy;
		double len = Math.sqrt(Math.pow(p1.x, 2) + Math.pow(p1.z, 2));
		double dot = p1.dot(0, 0, 10);
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
		if(degree < 0)
		{
			degree = 360 + degree;
		}
		double r = Math.toRadians(degree);
		x = (x * Math.cos(r)) - (z * Math.sin(r));
		z = ((x * Math.sin(r))) + (z * Math.cos(r)) ;
		
		len = length();
		changeLength(l);
	}
	
	public void rotateX(double degree) {
		double d = rotateOrigin();
		double l = length();
		double r = Math.toRadians(degree);
		z = (z * Math.cos(r)) - (y * Math.sin(r));
		y = ((z * Math.sin(r))) + (y * Math.cos(r)) ;
		len = length();
		changeLength(l);
		restoreRotation(d);
	}

	private void restoreRotation(double d) {
		rotateY(d * -1);
	}

	//rotates a point such at it will be oriented to face towards 0,0,10
	private double rotateOrigin() {
		point p = new point(this);
		p.convertRotation();
		this.rotateY(p.z);
		return p.z;
	}

	public boolean boundcheck(int i, int j, int k, int l, int m, int n) {
		boolean status = false;
		if (x >= i || x <= j){status = true;}
		if (y >= k || y <= l){status = true;}
		if (z >= m || z <= n){status = true;}
		return status;
	}

	public void increment(char dir) {
		switch(dir)
		{
			case 'x':
				this.x += 1;
				break;
			case 'y':
				this.y += 1;
				break;
			case 'z':
				this.z += 1;
				break;
		}
	}
	
	public void decrament(char dir) {
		switch(dir)
		{
			case 'x':
				this.x -= 1;
				break;
			case 'y':
				this.y -= 1;
				break;
			case 'z':
				this.z -= 1;
				break;
		}
	}
	
	public void divide(double d)
	{
		double l = len/d;
		this.changeLength(l);
	}
	
	@Override
	public String toString()
	{
		String ret = "";
		ret = "X:" + x;
		ret = ret + ", Y:" + y;
		ret = ret + ", Z:" + z;
		return ret;
	}

	public void translate(char c, point base) {
		switch(c)
		{
		case '-':
				this.x = this.x - base.x;
				this.y = this.y - base.y;
				this.z = this.z - base.z;
			break;
		case '+':
				this.x = this.x + base.x;
				this.y = this.y + base.y;
				this.z = this.z + base.z;
			break;
		default:
			System.out.println("Invalid Char");
		}
		
	}
}
