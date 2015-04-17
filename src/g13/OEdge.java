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
	
	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 'w'.
	public OEdge(Node m1, Node m2, double w)
	{
		super(m1, m2, w);
	}

	// Pre: True
	// Post: WP has an edge between node m1 and node m2 with weight 'w' and type 'et'.
	public OEdge(Node m1, Node m2, double w, edgeType et)
	{
		super(m1, m2, w);
		this.et = et;
	}
	
	// Pre: True
	// Post: Return de type of Edge.
	public edgeType getEdgeType()
	{
		return et;
	}
	
	// Pre: True
	// Post: Set Edge with edgeType 'et'.
	public void setEdgeType(edgeType et)
	{
		this.et = et;
	}
	
	// Pre: True
	// Post: Returns a String representation of the Edge.
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

	public static edgeType toEdgeType(String s)
	{
		if (s.equals("CsubC")) return edgeType.CsubC;
		else if (s.equals("CsupC")) return edgeType.CsuperC;
		else if (s.equals("CP")) return edgeType.CP;
		else return edgeType.PC;
	}
}
