package wikipedia.domain;

public class Page extends Element
{
	
	// Pre:  True.
	// Post: Create an empty Page.
	public Page()
	{
		super();
		super.et = ElementType.ELEMENT_PAGE;
	}
	
	// Pre:  Page with title 'title' doesn't exist.
	// Post: Create a Page with title 'title'.
	public Page(String title)
	{
		super(title);
		super.et = ElementType.ELEMENT_PAGE;
	}
	
	@Override public boolean equals(Object o) {
		return o instanceof Page
				&& (((Page)o).title.equals(this.title));
	}
}
