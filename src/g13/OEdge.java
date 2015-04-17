package g13;


public class OEdge extends Edge
{
	// For WP visibility public
	public enum EdgeType {
		CsubC,
		CsupC,
		CP,
		PC
	}

	private EdgeType et;
	
	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 'w'.
	public OEdge(Node m1, Node m2, double w)
	{
		super(m1, m2, w);
	}

	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 'w' and type 'et'.
	public OEdge(Node m1, Node m2, double w, EdgeType et)
	{
		super(m1, m2, w);
		this.et = et;
	}
	
	// Pre: True
	// Post: Return de type of Edge.
	public EdgeType getEdgeType()
	{
		return et;
	}
	
	// Pre: True
	// Post: Set Edge with edgeType 'et'.
	public void setEdgeType(EdgeType et)
	{
		this.et = et;
	}
	
	// Pre: True
	// Post: Returns a String representation of the Edge.
	@Override public String toString()
	{
		String s1 = super.toString();
		String s2 = toEdgeTypeString(this.et);
		return s1 + " " + s2;
	}

	// Pre:  True
	// Post: Returns the string representation of the edge type
	public static String toEdgeTypeString(EdgeType et)
	{
		switch (et) {
		case CsubC:
			return "CsubC";
		case CsupC:
			return "CsupC";
		case CP:
			return "CP";
		case PC:
			return "PC";
		}
		return null;
	}

	// Pre:  True
	// Post: Returns the EdgeType representing the string
	public static EdgeType toEdgeType(String s)
	{
		if (s.equals("CsubC")) return EdgeType.CsubC;
		else if (s.equals("CsupC")) return EdgeType.CsupC;
		else if (s.equals("CP")) return EdgeType.CP;
		else return EdgeType.PC;
	}
}
