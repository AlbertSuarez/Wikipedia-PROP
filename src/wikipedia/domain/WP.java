package wikipedia.domain;
import g13.*;

public class WP
{
	// The Community Collection of Wikipedia that only contains Categories.
	private CommunityCollection cc;
	
	// The Graph of Wikipedia that contains Categories and Pages.
	private OGraph graph;

	// The instances of the algorithms.
	private Algorithm algoritmeNG;
	
	// Pre:  True.
	// Post: Create a empty CommunityCollection and empty Graph.
	public WP()
	{
		cc = new CommunityCollection();
		graph = new OGraph();
		algoritmeNG = new NewmanGirvan();
	}

	// Pre:  True
	// Post: Return the Graph of Wikipedia	
	public OGraph getGraph()
	{
		return graph;
	}
	
	// Pre:  True
	// Post: Graph of Wikipedia is 'g'
	public void setGraph(OGraph g)
	{
		this.graph = g;
	}
	
	// Pre:  True
	// Post: Return the community collection of Wikipedia
	public CommunityCollection getCC()
	{
		return cc;
	}
		
	// Pre:  True
	// Post: Community collection of Wikipedia is 'cc'
	public void setCC(CommunityCollection cc)
	{
		this.cc = cc;
	}
	
	// Pre:  True
	// Post: Return the community collecion that NewmanGirvan Algorithm produces
	public CommunityCollection applyNewmanGirvan(int nCom)
	{
		return algoritmeNG.runNGAlgorithm(graph, nCom);
	}
	
	// Pre:  Category with title 'title' exists in graph.
	// Post: Return Graph get category by title.
	public Category getCategory(String title)
	{
		Category c = new Category(title);
		ONode node = new ONode(c);
		for (Node n : graph.getNodes()) {
			if (n.equals(node)) return c;
		}
		System.out.println("The category doesn't belong to the graph");
		return new Category(null);
	}
	
	// Pre:  Page with title 'title' exists in graph.
	// Post: Return Graph get page by title.
	public Page getPage(String title)
	{
		Page p = new Page(title);
		ONode node = new ONode(p);
		for (Node n : graph.getNodes()) {
			if (n.equals(node)) return p;
		}
		System.out.println("The page doesn't belong to the graph");
		return new Page(null);
	}
	
	// Pre:  Category c is not in the Graph.
	// Post: Category c is in the Graph.
	public void addCategory(Category c)
	{
		ONode n = new ONode(c);
		graph.addNode(n);
	}
	
	// Pre:  Page c is not in the Graph.
	// Post: Page c is in the Graph.
	public void addPage(Page p)
	{
		ONode n = new ONode(p);
		graph.addNode(n);
	}
	
	// Pre:  Category c is in the Graph.
	// Post: Category c is not in the Graph.
	public void delCategory(Category c)
	{
		ONode n = new ONode(c);
		graph.removeNode(n);
	}
	
	// Pre:  Page c is in the Graph.
	// Post: Page c is not in the Graph.
	public void delPage(Page p)
	{
		ONode n = new ONode(p);
		graph.removeNode(n);
	}
	
	// Pre:  True.
	// Post: Return true if category with title 'title' exists in graph,
	//		 false alternately.
	public boolean catExists(String title)
	{
		Category c = new Category(title);
		ONode n = new ONode(c);
		return graph.hasNode(n);
	}

	// Pre:  True.
	// Post: Return true if page with title 'title' exists in graph,
	//		 false alternately.
	public boolean pagExists(String title)
	{
		Page p = new Page(title);
		ONode n = new ONode(p);
		return graph.hasNode(n);
	}

	// Pre:  c1 and c2 exist in cc.
	// Post: c1 is a supercategory of c2.	
	public void addSuperLink(Category c1, Category c2)
	{
		ONode n1 = new ONode(c1);
		ONode n2 = new ONode(c2);
		OEdge e = new OEdge(n1,n2,OEdge.toEdgeType("CsupC"));
		graph.addEdge(e);
	}
	
	// Pre:  c and p exist in cc.
	// Post: c is the category of p.
	public void addCPLink(Category c, Page p)
	{
		ONode n1 = new ONode(c);
		ONode n2 = new ONode(p);
		OEdge e = new OEdge(n1,n2,OEdge.toEdgeType("CP"));
		graph.addEdge(e);
	}
}
