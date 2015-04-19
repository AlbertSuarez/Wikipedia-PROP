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
	private Algorithm algoritmeLouvain;
	private Algorithm algoritmeClique;
	
	// Pre:  True.
	// Post: Create a empty CommunityCollection and empty Graph.
	public WP()
	{
		cc = new CommunityCollection();
		graph = new OGraph();
		algoritmeNG = new NewmanGirvan();
	}

	
	public OGraph getGraph()
	{
		return graph;
	}
	
	public void setGraph(OGraph g)
	{
		this.graph = g;
	}
	
	public CommunityCollection getCC()
	{
		return cc;
	}
	
	public void setCC(CommunityCollection cc)
	{
		this.cc = cc;
	}
	
	// Pre:  True
	// Post: Return the community collecion that NewmanGirvan Algorithm produces produces 
	public CommunityCollection applyNewmanGirvan()
	{
		return algoritmeNG.runNGAlgorithm(graph);
	}
	
	// Pre:  Category with title 'title' exists in graph.
	// Post: Return Graph get category by title.
	Category getCategory(String title)
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
	Page getPage(String title)
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
	void addCategory(Category c)
	{
		ONode n = new ONode(c);
		graph.addNode(n);
	}
	
	// Pre:  Page c is not in the Graph.
	// Post: Page c is in the Graph.
	void addPage(Page p)
	{
		ONode n = new ONode(p);
		graph.addNode(n);
	}
	
	// Pre:  Category c is in the Graph.
	// Post: Category c is not in the Graph.
	void delCategory(Category c)
	{
		ONode n = new ONode(c);
		graph.removeNode(n);
	}
	
	// Pre:  Page c is in the Graph.
	// Post: Page c is not in the Graph.
	void delPage(Page p)
	{
		ONode n = new ONode(p);
		graph.removeNode(n);
	}
	
	// Pre:  True.
	// Post: Return true if category with title 'title' exists in graph,
	//		 false alternately.
	boolean catExists(String title)
	{
		Category c = new Category(title);
		ONode n = new ONode(c);
		return graph.hasNode(n);
	}

	// Pre:  True.
	// Post: Return true if page with title 'title' exists in graph,
	//		 false alternately.
	boolean pagExists(String title)
	{
		Page p = new Page(title);
		ONode n = new ONode(p);
		return graph.hasNode(n);
	}

	// Pre:  c1 and c2 exist in cc.
	// Post: c1 is a supercategory of c2.	
	void addSuperLink(Category c1, Category c2)
	{
		ONode n1 = new ONode(c1);
		ONode n2 = new ONode(c2);
		OEdge e = new OEdge(n1,n2,OEdge.toEdgeType("CsupC"));
		graph.addEdge(e);
	}
	
	// Pre:  c1 and c2 exist in cc.
	// Post: c1 is a subcategory of c2.
	void addSubLink(Category c1, Category c2)
	{
		ONode n1 = new ONode(c1);
		ONode n2 = new ONode(c2);
		OEdge e = new OEdge(n1,n2,OEdge.toEdgeType("CsubC"));
		graph.addEdge(e);
	}
	
	// Pre:  c and p exist in cc.
	// Post: c is the category of p.
	void addCPLink(Category c, Page p)
	{
		ONode n1 = new ONode(c);
		ONode n2 = new ONode(p);
		OEdge e = new OEdge(n1,n2,OEdge.toEdgeType("CP"));
		graph.addEdge(e);
	}
	
	// Pre:  p and c exist in cc.
	// Post: p is a page of c.
	void addPCLink(Page p, Category c)
	{
		ONode n1 = new ONode(p);
		ONode n2 = new ONode(c);
		OEdge e = new OEdge(n1,n2,OEdge.toEdgeType("PC"));
		graph.addEdge(e);
	}

}
