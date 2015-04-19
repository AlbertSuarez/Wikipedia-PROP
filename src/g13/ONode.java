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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		// Revisar
		// return ((ONode)o).getElement() == this.e;
		// return (n.e.getTitle() == this.e.getTitle());
		return ((ONode)o).e.equals(this.e);
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
		return compareTo(this.toString(),((ONode)n).toString()) > 0;
	}

	// Pre:  True
	// Post: Returns the element of this node
	public Element getElement()
	{
		return e;
	}

	private int compareTo(String e1, String e2)
	{
		if (e1 == e2) return 0;
		int i = 0;
		while (i < e1.length() || i < e2.length()) {
			if (e1.charAt(i) == e2.charAt(i)) ++i;
			else if (e1.charAt(i) > e2.charAt(i)) return 1;
			else return -1;
		}
		return -1;
	}
}
