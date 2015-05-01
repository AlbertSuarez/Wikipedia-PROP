package g13;

import wikipedia.domain.Element;

public class ONode extends Node
{
	private Element e;
	
	// Pre:  True
	// Post: Constructor of ONode with Element 'e'.
	public ONode(Element e)
	{
		this.e = e;
	}
	
	// Pre:  True
	// Post: Compares the specified object with this Node for equality.
	//		 Return true if it's equal, false alternately.
	@Override public boolean equals(Object o)
	{
		return o instanceof ONode
				&&((ONode)o).e.equals(this.e);
	}
	
	// Pre:  True
	// Post: Returns a hash code value for this Node.
	@Override public int hashCode()
	{
		if (e == null) return 0;
		
		// Le pedimos al title (String) su hashCode,
		// el cual estará calculado en base al texto
		// que tenga
		return e.getTitle().hashCode();
	}
	
	// Pre:  True
	// Post: Returns a string representation of this Node.
	@Override public String toString()
	{
		return e.toString();
	}
	
	// Pre:  True
	// Post: Returns whether this Node is greater than the parameter node.
	public boolean isGreater(Node n) {
		return (this.toString()).compareTo(((ONode)n).toString()) > 0;
	}

	// Pre:  True
	// Post: Returns the element of this node
	public Element getElement()
	{
		return e;
	}
}
