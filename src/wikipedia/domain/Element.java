package wikipedia.domain;

/**
 * Element class
 * @author G13.2
 */
public abstract class Element
{
	public enum ElementType {
		ELEMENT_PAGE,
		ELEMENT_CATEGORY
	}

	protected ElementType et;

	protected String title;

	/**
	 * Create a empty Element
	 */
	public Element()
	{
		title = null;
	}

	/**
	 * Create a Element with title 'title'
	 * @param title The title to set
	 */
	public Element(String title)
	{
		this.title = title;
	}

	/**
	 * Set element with title 'title'
	 * @param title The title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Getter of title
	 * @return The title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Compares the specified object with this element for equality.
	 * Returns true if the specified object is also a element and the two elements
	 * have the same title
	 * @param o Object to be compared for equality
	 * @return True if the specified object is equal to this element
	 */
	@Override public boolean equals(Object o) {
		return o instanceof Element
				&& (((Element)o).title.equals(this.title));
	}

	/**
	 * String representation of Element
	 * @return A string representation of the value of this Element object
	 */
	@Override public String toString()
	{
		return title;
	}

	/**
	 * Getter of type of Element (Category or Page)
	 * @return ElementType object
	 */
	public ElementType getElementType()
	{
		return et;
	}

	/**
	 * String to ElementType
	 * @param s String that indicates 'cat' or 'page'
	 * @return The associated ElementType
	 */
	public static ElementType toElementType(String s)
	{
		if (s.equals("cat")) return ElementType.ELEMENT_CATEGORY;
		else return ElementType.ELEMENT_PAGE;
	}

	/**
	 * ElementType to String
	 * @param et ElementType
	 * @return The associated String
	 */
	public static String toElementTypeString(ElementType et)
	{
		if (et == ElementType.ELEMENT_CATEGORY) return "cat";
		else if (et == ElementType.ELEMENT_PAGE) return "page";
		return null;
	}
}
