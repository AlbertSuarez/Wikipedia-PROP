package wikipedia.domain;

public class WP
{
	// private Graph
	private CommunityCollection cc;
	
	public WP()
	{
		// Ini graph
		cc = new CommunityCollection();
	}
	
	//Pre: La categoria existe en Graph
	Category getCategory(String title)
	{
		Category c = new Category("Provisional");
		return c;		// Provisional
		// return Graph get category by title
		
	}
	//Pre: La page existe en Graph
	Page getPage(String title)
	{
		Page p = new Page("Provisional");	//
		return p;	// Provisional
		// return Graph get page by title
	}
	//Pre: c no existe en Graph
	void addCategory(Category c)
	{
		// Graph add category
	}
	//Pre: p no existe en Graph
	void addPage(Page p)
	{
		// Graph add page
	}
	//Pre: c existe en Graph
	void delCategory(Category c)
	{
		// Graph del c y tus enlaces
	}
	void delPage(Page p)
	{
		// Graph del p y sus enlaces
	}
	boolean catExists(String title)
	{
		return true; // Porvisional
		// return Graph contains cat(title)?
	}

	boolean pagExists(String title)
	{
		return true; // Porvisional
		// return Graph contains pag(title)?
	}

	void addSuperLink(Category c1, Category c2)
	{
	
	}
	void addSubLink(Category c1, Category c2)
	{
	
	}
	void addCPLink(Category c, Page p)
	{
	
	}
	void addPCLink(Page p, Category c)
	{
	
	}

}
