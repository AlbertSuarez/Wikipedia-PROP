package g13;

import wikipedia.domain.Element;

public class ONode implements Node
{
	private Element e;
	
	public ONode(Element e)
	{
		this.e = e;
	}
	
	@Override public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final ONode n = (ONode) o;

		// Revisar
		// return (n.e.getTitle() == this.e.getTitle());
		return n.e.equals(this.e);
	}
	
	@Override public int hashCode()
	{
		return 666;// ???
	}
	
	@Override public String toString()
	{
		return e.toString();
	}
	
	public boolean isGreater(Node n) {
		return true; // Por poner algo
	}
}