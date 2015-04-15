package wikipedia.domain;

public abstract class Element
{
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
}
