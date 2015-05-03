package wikipedia.domain;

/**
 * Category class
 * @author G13.2
 */
public class Category extends Element
{
	/**
	 * Create an empty Category
	 */
	public Category()
	{
		super();
		super.et = ElementType.ELEMENT_CATEGORY;
	}
	

	/**
	 * Create a Category with title 'title'
	 */
	public Category(String title)
	{
		super(title);
		super.et = ElementType.ELEMENT_CATEGORY;
	}
	
	/**
	 * Compares the specified object with this category for equality.
	 * Returns true if the specified object is also a category and the two categories
	 * have the same title
	 * @param o Object to be compared for equality
	 * @return True if the specified object is equal to this category
	 */
	@Override public boolean equals(Object o) {
		return o instanceof Category
				&& (((Category)o).title.equals(this.title));
	}
}
