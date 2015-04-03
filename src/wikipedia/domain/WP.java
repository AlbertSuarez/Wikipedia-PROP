package wikipedia.domain;
import es.upc.fib.prop.shared13.*;

public class WP
{
	// UNA COMMUNITY COLLECTION ES UN SET<SET<CATEGORY>>, ENTONCES
	// NO EXISTEN PAGINAS. Y DEBERIAN EXISTIR!!!
	// DEBERIA SER UN GRAPH (DE LA CLASE COMPARTIDA) Y NO UNA COMMUNITY COLLECTION.
	
	// private Graph
	private CommunityCollection cc;	// <- ESTO DIRIA QUE NO!
	
	//private Graph graf;			   <- ESTO TIENE MEJOR PINTA CREO!
	
	// Pre:  True.
	// Post: Create a empty WP.
	public WP()
	{
		cc = new CommunityCollection();
	}
	
	// Pre:  Category with title 'title' exists in graph.
	// Post: Return Graph get category by title.
	Category getCategory(String title)
	{
		// PROVISIONAL
		Category c = new Category("Provisional");
		return c;		
	}
	
	// Pre:  Page with title 'title' exists in graph.
	// Post: Return Graph get page by title.
	Page getPage(String title)
	{
		// PROVISIONAL
		Page p = new Page("Provisional");
		return p;
	}
	
	// Pre:  Category c is not in the Graph.
	// Post: Category c is in the Graph.
	void addCategory(Category c)
	{
		
	}
	
	// Pre:  Page c is not in the Graph.
	// Post: Page c is in the Graph.
	void addPage(Page p)
	{
		
	}
	
	// Pre:  Category c is in the Graph.
	// Post: Category c is not in the Graph.
	void delCategory(Category c)
	{
		// Graph del c y tus enlaces
	}
	
	// Pre:  Page c is in the Graph.
	// Post: Page c is not in the Graph.
	void delPage(Page p)
	{
		// Graph del p y sus enlaces
	}
	
	// Pre:  True.
	// Post: Return true if category with title 'title' exists in graph,
	//		 false alternately.
	boolean catExists(String title)
	{
		// PROVISIONAL
		return true;
		// return Graph contains cat(title)?
	}

	// Pre:  True.
	// Post: Return true if page with title 'title' exists in graph,
	//		 false alternately.
	boolean pagExists(String title)
	{
		return true; // Porvisional
		// return Graph contains pag(title)?
	}

	// Pre:  c1 and c2 exist in cc.
	// Post: c1 is a supercategory of c2.	
	void addSuperLink(Category c1, Category c2)
	{
	
	}
	
	// Pre:  c1 and c2 exist in cc.
	// Post: c1 is a subcategory of c2.
	void addSubLink(Category c1, Category c2)
	{
	
	}
	
	// Pre:  c and p exist in cc.
	// Post: c is the category of p.
	void addCPLink(Category c, Page p)
	{
	
	}
	
	// Pre:  p and c exist in cc.
	// Post: p is a page of c.
	void addPCLink(Page p, Category c)
	{
	
	}

}