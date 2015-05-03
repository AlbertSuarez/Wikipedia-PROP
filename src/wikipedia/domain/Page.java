package wikipedia.domain;

/**
 * Page class
 * @author G13.2
 */
public class Page extends Element
{
	
	/**
	 * Create an empty Page
	 */
	public Page()
	{
		super();
		super.et = ElementType.ELEMENT_PAGE;
	}
	
	/**
	 * Create a Page with title 'title'
	 * @param title The title to set
	 */
	public Page(String title)
	{
		super(title);
		super.et = ElementType.ELEMENT_PAGE;
	}
	
	/**
	 * Compares the specified object with this page for equality.
	 * Returns true if the specified object is also a page and the two pages
	 * have the same title
	 * @param o Object to be compared for equality
	 * @return True if the specified object is equal to this page
	 */
	@Override public boolean equals(Object o) {
		return o instanceof Page
				&& (((Page)o).title.equals(this.title));
	}
}
