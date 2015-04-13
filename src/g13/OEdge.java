package g13;


public class OEdge extends Edge
{
	// For WP visibility public
	public enum edgeType {
		CsubC,
		CsuperC,
		CP,
		PC
	}

	private edgeType et;
	
	public OEdge(Node m1, Node m2, double w)
	{
		super(m1, m2, w);
	}
	
	public OEdge(Node m1, Node m2, double w, edgeType et)
	{
		super(m1, m2, w);
		this.et = et;
	}
	
	@Override public String toString()
	{
		String s = null;
		
		switch (et)
		{
		case CsubC:
			s = "CsubC";
			break;
		case CsuperC:
			s = "CsuperC";
			break;
		case CP:
			s = "CP";
			break;
		case PC:
			s = "PC";
			break;
		}
		
		String s1 = super.toString();
		return s1 + " " + s;
	}
}