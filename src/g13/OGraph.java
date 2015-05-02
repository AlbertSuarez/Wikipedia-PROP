/**
 * @file OGraph.java
 * @author G13.2
 * @date 2 May 2015
 * @brief Own Graph implementation
 */

package g13;


public class OGraph extends Graph
{
	/**
	 * @brief Creates a empty OGraph
	 */ 
	public OGraph()
	{
		super();
	}
	
	/**
	 * @brief Validates all edges of implicit OGraph
	 */ 
	public void validateAllEdges() {
        for (Edge e : getEdges()) e.setValidity(true);
    }
}
