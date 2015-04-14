package g13;

import wikipedia.domain.Element;
// import java.lang.Comparable;

public class ONode implements Node
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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final ONode n = (ONode) o;

		// Revisar
		//return (n.e.getTitle() == this.e.getTitle());
		return n.e.equals(this.e);
	}
	
	// Pre:  True
	// Post: Returns a hash code value for this Node.
	@Override public int hashCode()
	{
		if (e == null) return 0;
		return e.hashCode();
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
		return true; // Por poner algo
		// return (this.compareTo(n) > 0)
	}
}