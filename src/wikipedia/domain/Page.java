package wikipedia.domain;

public class Page extends Element
{
	
	// Pre:  True.
    // Post: Create a empty Page.
	public Page()
	{
		super();
	}
	
	// Pre:  Page with title 'title' doesn't exist.
    // Post: Create a Page with title 'title'.
	public Page(String title)
	{
		super(title);
	}
	
}
