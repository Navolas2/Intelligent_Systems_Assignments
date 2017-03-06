
public class pesticide extends genericMovement {

	private double h;
	
	public pesticide() {
		// TODO Auto-generated constructor stub
		stay();
	}

	public pesticide(boolean dimension) {
		super(dimension);
		// TODO Auto-generated constructor stub
		stay();
	}

	public pesticide(String nm) {
		super(nm);
		// TODO Auto-generated constructor stub
		stay();
	}

	public pesticide(String nm, boolean dimension) {
		super(nm, dimension);
		// TODO Auto-generated constructor stub
		stay();
		h = -1 * ((Math.E * ((-1 * Math.pow(current.x,2))/30000)) * (Math.E * ((-1 * Math.pow(current.z, 2))/30000))/2);
	}

	public pesticide(double sp) {
		super(sp);
		// TODO Auto-generated constructor stub
		stay();
	}

	public pesticide(double sp, boolean dimension) {
		super(sp, dimension);
		// TODO Auto-generated constructor stub
		stay();
	}

	public pesticide(String nm, double sp) {
		super(nm, sp);
		// TODO Auto-generated constructor stub
		stay();
	}

	public pesticide(String nm, double sp, boolean dimension) {
		super(nm, sp, dimension);
		// TODO Auto-generated constructor stub
		stay();
	}

	public double raise(double l)
	{
		if (l > 2000)
		{
			return 0;
		}
		else
		{
			double p = l / 2000;
			p = h * p;
			return h - p;
		}
	}
	
}
