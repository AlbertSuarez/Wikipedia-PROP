package wikipedia.domain;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Collections;
import g13.*;

public class Community {
	
	private Set<Node> nodeSet;
	
	// Pre:  True.
	// Post: Create a empty Community.
	public Community()
	{
		nodeSet = new LinkedHashSet<Node>();
	}
	
	// Pre:  Node n is not in the Community.
	// Post: Node n is in the Community.
	public void addNode(Node n)
	{ 
		nodeSet.add(n);
	}
	
	// Pre:  True.
	// Post: Return an non-modificable of all Nodes of the Community.	
	public Set<Node> getNodes()
	{
		return Collections.unmodifiableSet(nodeSet);
	}
	
	// Pre:  Node n is in the Community.
	// Post: Node n is not in the Community.
	public void eraseNode(Node n)
	{
		nodeSet.remove(n);
	}
	
	// Pre:  True.
	// Post: Print a ordered list of all nodes.
	public void printCommunity()
	{ 
		for (Node nn : nodeSet){
			ONode onn = (ONode)nn;
			System.out.println(onn.toString());
		}
	}
	
	// Pre:  True.
	// Post: Return true if 'n' is in 'nodeSet', false alternatively.
	public boolean belongs(Node n)
	{
		return nodeSet.contains(n);
	}
}
