
public class Bug extends genericMovement {

	private boolean seen = false;
	private boolean dead = false;
	private boolean removed = false;
	
	public Bug() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bug(boolean dimension) {
		super(dimension);
		// TODO Auto-generated constructor stub
	}

	public Bug(String nm) {
		super(nm);
		// TODO Auto-generated constructor stub
	}

	public Bug(String nm, boolean dimension) {
		super(nm, dimension);
		// TODO Auto-generated constructor stub
	}

	public Bug(double sp) {
		super(sp);
		// TODO Auto-generated constructor stub
	}

	public Bug(double sp, boolean dimension) {
		super(sp, dimension);
		// TODO Auto-generated constructor stub
	}

	public Bug(String nm, double sp) {
		super(nm, sp);
		// TODO Auto-generated constructor stub
	}

	public Bug(String nm, double sp, boolean dimension) {
		super(nm, sp, dimension);
		//stay();
		// TODO Auto-generated constructor stub
	}

	public void setSeen()
	{
		seen = !seen;
	}
	
	public boolean getSeen()
	{
		return seen;
	}
	
	public void setDead(boolean b)
	{
		dead = b;
	}
	
	public boolean getDead()
	{
		return dead;
	}
	
	public void setRemove()
	{
		removed = !removed; 
	}
	
	public boolean getRemove()
	{
		return removed;
	}
}
