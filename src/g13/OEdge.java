/**
 * @file OEdge.java
 * @author G13.2
 * @date 2 May 2015
 * @brief Own Edge implementation
 */

package g13;

public class OEdge extends Edge
{
	// For WP visibility public

	/**
	 * @brief Represents a Wikipedia edge type
	 */
	public enum EdgeType {
		CsupC,         /**< Category to Category edge */
		CP             /**< Category to Page edge */
	}

	private EdgeType et;


	// BEGIN CONSTRUCTORS --------------------

	/**
	 * @brief Creates a new OEdge
	 * @param n1 one of the nodes of the edge
	 * @param n2 the other node of the edge
	 * @param w the weight of the edge
	 * @param valid whether the edge will be valid or not
	 */
	public OEdge(Node n1, Node n2, double w, boolean valid)
	{
		super(n1, n2, w, valid);
	}
	
	/**
	 * @brief Creates a new valid OEdge
	 * @param n1 one of the nodes of the edge
	 * @param n2 the other node of the edge
	 * @param w the weight of the edge
	 */
	public OEdge(Node n1, Node n2, double w)
	{
		super(n1, n2, w);
	}

	/**
	 * @brief Creates a new OEdge
	 * @param n1 one of the nodes of the edge
	 * @param n2 the other node of the edge
	 * @param w the weight of the edge
	 * @param valid whether the edge will be valid or not
	 * @param et the EdgeType
	 */
	public OEdge(Node n1, Node n2, double w, boolean valid, EdgeType et)
	{
		super(n1, n2, w, valid);
		this.et = et;
	}
	
	/**
	 * @brief Creates a new valid OEdge
	 * @param n1 one of the nodes of the edge
	 * @param n2 the other node of the edge
	 * @param w the weight of the edge
	 * @param et the EdgeType
	 */
	public OEdge(Node n1, Node n2, double w, EdgeType et)
	{
		super(n1, n2, w);
		this.et = et;
	}
	
	/**
	 * @brief Creates a new valid OEdge with weight 1
	 * @param n1 one of the nodes of the edge
	 * @param n2 the other node of the edge
	 */
	public OEdge(Node n1, Node n2)
	{
		super(n1, n2);
	}

	/**
	 * @brief Creates a new valid OEdge with weight 1
	 * @param n1 one of the nodes of the edge
	 * @param n2 the other node of the edge
	 * @param et the EdgeType
	 */
	public OEdge(Node n1, Node n2, EdgeType et)
	{
		super(n1, n2);
		this.et = et;
	}
	
	// END CONSTRUCTORS -----------------------
	
	/**
	 * @brief Returns the edge type
	 * @return the edge type
	 */
	public EdgeType getEdgeType()
	{
		return et;
	}
	
	/**
	 * @brief Sets the edge type
	 * @param et the edge type to set
	 */
	public void setEdgeType(EdgeType et)
	{
		this.et = et;
	}
	
	/**
	 * @brief Returns the String representation of the Edge
	 * @return the String representation of the Edge
	 */
	@Override
	public String toString()
	{
		String s1 = super.toString();
		String s2 = toEdgeTypeString(this.et);
		return s1 + " " + s2;
	}

	/**
	 * @brief Returns the String representation of the EdgeType
	 * @param et the EdgeType to set
	 * @return the String representation of the EdgeType
	 */
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

	/**
	 * @brief Returns an EdgeType from a String
	 * @param s the String to get the EdgeType from
	 * @return the EdgeType that the String represents
	 */
	public static EdgeType toEdgeType(String s)
	{
		
		if (s.equals("CsupC")) return EdgeType.CsupC;
		else return EdgeType.CP;
	}
}
