package g13;

/**
 * Own Graph implementation
 * @author G13.2
 */
public class OGraph extends Graph
{
	/**
	 * Creates a empty OGraph.
	 */ 
	public OGraph()
	{
		super();
	}
	
	/**
	 * Validates all edges of implicit OGraph.
	 */ 
	public void validateAllEdges() {
        for (Edge e : getEdges()) e.setValidity(true);
    }
}
