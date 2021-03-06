package wikipedia.domain;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Collections;
import g13.*;

/**
 * Community class
 * @author G13.2
 */
public class Community {

	/**
	 * Represents the community
	 */
	private Set<Node> nodeSet;

	/**
	 * Create a empty Community
	 */
	public Community()
	{
		nodeSet = new LinkedHashSet<Node>();
	}

	/**
	 * Add node to community
	 * @param n The node to be added
	 * @pre n is not in Community
	 */
	public void addNode(Node n)
	{
		nodeSet.add(n);
	}

	/**
	 * Get nodes from community
	 * @return An non-modificable set of all Nodes of the Community
	 */
	public Set<Node> getNodes()
	{
		return Collections.unmodifiableSet(nodeSet);
	}

	/**
	 * Erase node from community
	 * @param n The node to be erased
	 * @pre n is in Community
	 */
	public void eraseNode(Node n)
	{
		nodeSet.remove(n);
	}

	/**
	 * See if community is empty
	 * @return True if the community is empty, false alternatively
	 */
	public boolean isEmpty()
	{
		return nodeSet.isEmpty();
	}

	/**
	 * Print a ordered list of all nodes.
	 * @return Community to String
	 */
	public String printCommunity()
	{
		String s = "";
		for (Node nn : nodeSet){
			s = s + (nn.toString()) + "\n";
		}
		return s;
	}

	/**
	 * See if a node belongs to community
	 * @param n The node
	 * @return True if 'n' is in 'nodeSet', false alternatively.
	 */
	public boolean belongs(Node n)
	{
		return nodeSet.contains(n);
	}
}
