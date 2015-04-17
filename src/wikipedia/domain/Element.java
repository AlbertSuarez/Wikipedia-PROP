package wikipedia.domain;

public abstract class Element
{
	// For WP visibility public
	public enum ElementType {
		ELEMENT_PAGE,
		ELEMENT_CATEGORY
	}

	protected ElementType et;

	private String title;

	// Pre:  True.
	// Post: Create a empty Element.
	public Element()
	{
		title = null;
	}

	// Pre:  Element with title 'title' doesn't exist. 
	// Post: Create a Element with title 'title'.	
	public Element(String title)
	{
		this.title = title;
	}

	// Pre:  Element with title 'title' doesn't exist.
	// Post: Set element wiht title 'title'.
	public void setTitle(String title)
	{
		this.title = title;
	}

	// Pre:  True.
	// Post: Retrun the title of Element.
	public String getTitle()
	{
		return title;
	}

	@Override public boolean equals(Object o) {
				
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Element e = (Element) o;

		return (e.title.equals(this.title));
	}

	@Override public String toString()
	{
		return title;
	}

	public ElementType getElementType()
	{
		return et;
	}

	// Pre:  True
	// Post: Returns the ElementType representing the string
	public static ElementType toElementType(String s)
	{
		if (s.equals("cat")) return ElementType.ELEMENT_CATEGORY;
		else return ElementType.ELEMENT_PAGE;
	}

	// Pre:  True
	// Post: Returns the string representation of the element type
	public static String toElementTypeString(ElementType et)
	{
		if (et == ElementType.ELEMENT_CATEGORY) return "cat";
		else if (et == ElementType.ELEMENT_PAGE) return "page";
		return null;
	}
}
