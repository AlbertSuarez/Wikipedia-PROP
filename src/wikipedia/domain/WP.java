package wikipedia.domain;
import g13.*;

/**
 * WikiPedia class
 * @author G13.2
 */
public class WP
{
	/**
	 * The Community Collection of Wikipedia that only contains Categories.
	 */
	private CommunityCollection cc;
	
	/**
	 * The Graph of Wikipedia that contains Categories and Pages.
	 */
	private OGraph graph;

	/**
	 * The instance of the NewmanGirvan.
	 */
	private Algorithm algoritmeNG;
	
	/**
	 * The instance of the Clique Percolation (Slow version).
	 */
	private Algorithm algoritmeCPMaxim;
	
	/**
	 * The instance of the Louvain.
	 */
	private Algorithm algoritmeLouvain;
	
	/**
	 * Creates a new WP with an empty CommunityCollection and an empty Graph
	 */
	public WP()
	{
		cc = new CommunityCollection();
		graph = new OGraph();
		algoritmeNG = new NewmanGirvan();
		algoritmeCPMaxim = new CliqueMaxim();
		algoritmeLouvain = new Louvain();
	}

	/**
	 * Returns the OGraph
	 * @return The OGraph
	 */
	public OGraph getGraph()
	{
		return graph;
	}
	
	/**
	 * Sets the OGraph
	 * @param g The OGraph to set
	 */
	public void setGraph(OGraph g)
	{
		this.graph = g;
	}
	
	/**
	 * Returns the CommunityCollection
	 * @return The CommunityCollection
	 */
	public CommunityCollection getCC()
	{
		return cc;
	}
		
	/**
	 * Sets the CommunityCollection
	 * @param cc The CommunityCollection to set
	 */
	public void setCC(CommunityCollection cc)
	{
		this.cc = cc;
	}
	
	/**
	 * Applies the Newman-Girvan algorithm to the graph
	 * @param nCom The number of Communities to split the graph into
	 * @return The CommunityCollecion that Newman-Girvan Algorithm produces
	 */
	public CommunityCollection applyNewmanGirvan(int nCom)
	{
		return algoritmeNG.runAlgorithm(graph, nCom);
	}

	/**
	 * Applies the Clique Percolation algorithm to the graph (slow version)
	 * @return The CommunityCollecion that Clique Percolation Algorithm produces
	 */
	public CommunityCollection applyCliquePercolationMaxim()
	{
		return algoritmeCPMaxim.runAlgorithm(graph, 0);
	}
	
	/**
	 * Applies the Louvain algorithm to the graph
	 * @return The CommunityCollecion that Louvain Algorithm produces
	 */
	public CommunityCollection applyLouvain()
	{
		return algoritmeLouvain.runAlgorithm(graph, 0);
	}
	
	/**
	 * Returns the Category identified by a String
	 * @param title The title of the Category to search for
	 * @return The Category identified by the title, or null if not found
	 */
	public Category getCategory(String title)
	{
		Category c = new Category(title);
		ONode node = new ONode(c);
		for (Node n : graph.getNodes()) {
			if (n.equals(node)) return c;
		}
		System.out.println("The category doesn't belong to the graph");
		return null;
	}
	
	/**
	 * Returns the Page identified by a String
	 * @param title The title of the Page to search for
	 * @return The Page identified by the title, or null if not found
	 */
	public Page getPage(String title)
	{
		Page p = new Page(title);
		ONode node = new ONode(p);
		for (Node n : graph.getNodes()) {
			if (n.equals(node)) return p;
		}
		System.out.println("The page doesn't belong to the graph");
		return null;
	}
	
	/**
	 * Adds a new Category to the graph
	 * @param c The new Category to add
	 */
	public void addCategory(Category c)
	{
		ONode n = new ONode(c);
		graph.addNode(n);
	}
	
	/**
	 * Adds a new Page to the graph
	 * @param p The new Page to add
	 */
	public void addPage(Page p)
	{
		ONode n = new ONode(p);
		graph.addNode(n);
	}
	
	/**
	 * Removes a Category from the graph
	 * @param c The Category to remove
	 */
	public void delCategory(Category c)
	{
		ONode n = new ONode(c);
		graph.removeNode(n);
	}
	
	/**
	 * Removes a Page from the graph
	 * @param p The Page to remove
	 */
	public void delPage(Page p)
	{
		ONode n = new ONode(p);
		graph.removeNode(n);
	}
	
	/**
	 * Returns if the Category with title 'title' exists in graph
	 * @param title The title of the Category
	 * @return True if the Category with title 'title' exists in graph,
	 *	   False otherwise
	 */
	public boolean catExists(String title)
	{
		Category c = new Category(title);
		ONode n = new ONode(c);
		return graph.hasNode(n);
	}

	/**
	 * Returns if the Page with title 'title' exists in graph
	 * @param title The title of the Page
	 * @return True if the Page with title 'title' exists in graph,
	 *	   False otherwise
	 */
	public boolean pagExists(String title)
	{
		Page p = new Page(title);
		ONode n = new ONode(p);
		return graph.hasNode(n);
	}

	/**
	 * Modify the title of an Element's Node of the implicit graph
	 * @param a element to modify
	 * @param s new title of the element
	 */
	public void modElement(Element a, String s)
	{
		for (Node n : graph.getNodes()) {
			ONode nn = (ONode) n;
			if (nn.getElement().equals(a)) {
				nn.getElement().setTitle(s);
				break;
			}
		}
	}
	
	/**
	 * Move a Node of a community to another community
	 * @param a title of element's node
	 * @param i identifier of the new community
	 */
	public void modCommunity(String a, Integer i) {
		ONode n = new ONode(new Category(a));
		for (int j = 0; j < cc.getCommunityCount(); j++) {
			if (cc.getCommunity(j).belongs(n)) {cc.getCommunity(j).eraseNode(n); break;}
		}
		cc.getCommunity(i).addNode(n);
	}
	
	
	/**
	 * Creates a new Edge between two Categories
	 * @param c1 One of the Categories to create the Edge to
	 * @param c2 The other Category
	 */
	public void addSuperLink(Category c1, Category c2)
	{
		ONode n1 = new ONode(c1);
		ONode n2 = new ONode(c2);
		OEdge e = new OEdge(n1, n2, OEdge.EdgeType.CsupC);
		graph.addEdge(e);
	}
	
	/**
	 * Creates a new Edge between a Category and a Page
	 * @param c The Category to create the Edge to
	 * @param p The Page to create the Edge to
	 */
	public void addCPLink(Category c, Page p)
	{
		ONode n1 = new ONode(c);
		ONode n2 = new ONode(p);
		OEdge e = new OEdge(n1, n2, OEdge.EdgeType.CP);
		graph.addEdge(e);
	}
	
	/**
	 * Deletes the edge between the nodes with Elements e1 and e2
	 * @param e1 The element of the first Node
	 * @param e2 The element of the second Node
	 */
	public void delLink(Element e1, Element e2)
	{
		ONode n1 = new ONode(e1);
		ONode n2 = new ONode(e2);
		graph.removeEdge(n1, n2);
	}
}
