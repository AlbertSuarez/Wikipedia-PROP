package g13;

public class OGraph extends Graph
{
	// Pre:  True
	// Post: Creates an empty graph.
	public OGraph()
	{
		super();
	}
	
	// Pre:  True
	// Post: It doesn't exist an edge between node n1 and n2.
	public boolean removeEdge(Node n1, Node n2)
	{
		if (n1 == null || n2 == null) throw new NullPointerException();
		if (this.hasNode(n1) || this.hasNode(n2)) return false;
		--edgeCount;
		for (Edge e : getAdjacencyList(n1)) {
			if (e.getNeighbor(n1).equals(n2)) {
				return removeEdge(e);
			}
		}
		return false;
	}
}