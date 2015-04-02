package wikipedia.domain;

public class Category extends Element
{
	
    // Pre:  True.
    // Post: Create a empty Category.
	public Category()
	{
		super();
	}
	
	// Pre:  Category with title 'title' doesn't exist.
    // Post: Create a Category with title 'title'. 
	public Category(String title)
	{
		super(title);
	}
	
}
