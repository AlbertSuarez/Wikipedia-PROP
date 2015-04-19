package g13;

public class OGraph extends Graph
{
	// Pre:  True
	// Post: Creates an empty graph.
	public OGraph()
	{
		super();
	}
	
	// Pre: True
	// Post: Validates all edges in the graph
	public void validateAllEdges() {
        for (Edge e : getEdges()) e.setValidity(true);
    }
}
