package g13;



public class OEdge extends Edge
{
	// For WP visibility public
	public enum EdgeType {
		CsupC,
		CP
	}

	private EdgeType et;
	
	// BEGIN CONSTRUCTORS --------------------
	
	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 'w' and boolean valid.
	public OEdge(Node m1, Node m2, double w, boolean valid)
	{
		super(m1, m2, w, valid);
	}
	
	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 'w'.
	public OEdge(Node m1, Node m2, double w)
	{
		super(m1, m2, w);
	}

	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 'w', boolean valid and type 'et'.
	public OEdge(Node m1, Node m2, double w, boolean valid, EdgeType et)
	{
		super(m1, m2, w, valid);
		this.et = et;
	}
	
	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 'w' and type 'et'.
	public OEdge(Node m1, Node m2, double w, EdgeType et)
	{
		super(m1, m2, w);
		this.et = et;
	}
	
	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 1.
	public OEdge(Node m1, Node m2)
	{
		super(m1, m2);
	}

	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 1 and type 'et'.
	public OEdge(Node m1, Node m2, EdgeType et)
	{
		super(m1, m2);
		this.et = et;
	}
	
	// END CONSTRUCTORS -----------------------
	
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
		case CsupC:
			return "CsupC";
		case CP:
			return "CP";
		}
		return null;
	}

	// Pre:  True
	// Post: Returns the EdgeType representing the string
	public static EdgeType toEdgeType(String s)
	{
		
		if (s.equals("CsupC")) return EdgeType.CsupC;
		else return EdgeType.CP;
	}
}
