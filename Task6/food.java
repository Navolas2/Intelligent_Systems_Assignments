
public class food extends genericMovement {

	private double h;
	
	public food() {
		// TODO Auto-generated constructor stub
		stay();
	}

	public food(boolean dimension) {
		super(dimension);
		// TODO Auto-generated constructor stub
		stay();
	}

	public food(String nm) {
		super(nm);
		// TODO Auto-generated constructor stub
		stay();
	}

	public food(String nm, boolean dimension) {
		super(nm, dimension);
		// TODO Auto-generated constructor stub
		stay();
		h = (Math.E * ((-1 * Math.pow(current.x,2))/30000)) * (Math.E * ((-1 * Math.pow(current.z, 2))/30000));
	}

	public food(double sp) {
		super(sp);
		// TODO Auto-generated constructor stub
		stay();
	}

	public food(double sp, boolean dimension) {
		super(sp, dimension);
		// TODO Auto-generated constructor stub
		stay();
		h = (Math.E * ((-1 * Math.pow(current.x,2))/30000)) * (Math.E * ((-1 * Math.pow(current.z, 2))/30000));
	}

	public food(String nm, double sp) {
		super(nm, sp);
		// TODO Auto-generated constructor stub
		stay();
	}

	public food(String nm, double sp, boolean dimension) {
		super(nm, sp, dimension);
		stay();
		// TODO Auto-generated constructor stub
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
