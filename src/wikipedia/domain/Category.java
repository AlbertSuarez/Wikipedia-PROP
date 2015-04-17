package wikipedia.domain;

public class Category extends Element
{
	
	// Pre:  True.
	// Post: Create an empty Category.
	public Category()
	{
		super();
		super.et = ElementType.ELEMENT_CATEGORY;
	}
	
	// Pre:  Category with title 'title' doesn't exist.
	// Post: Create a Category with title 'title'. 
	public Category(String title)
	{
		super(title);
		super.et = ElementType.ELEMENT_CATEGORY;
	}
	
}
