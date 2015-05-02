/**
 * @file WP.java
 * @author G13.2
 * @date 2 May 2015
 * @brief WikiPedia class
 */

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
	
	/**
	 * @brief Creates a new WP with an empty CommunityCollection and an empty Graph
	 */
	public WP()
	{
		cc = new CommunityCollection();
		graph = new OGraph();
		algoritmeNG = new NewmanGirvan();
	}

	/**
	 * @brief Returns the OGraph
	 * @return the OGraph
	 */
	public OGraph getGraph()
	{
		return graph;
	}
	
	/**
	 * @brief Sets the OGraph
	 * @param g the OGraph to set
	 */
	public void setGraph(OGraph g)
	{
		this.graph = g;
	}
	
	/**
	 * @brief Returns the CommunityCollection
	 * @return the CommunityCollection
	 */
	public CommunityCollection getCC()
	{
		return cc;
	}
		
	/**
	 * @brief Sets the CommunityCollection
	 * @param cc the CommunityCollection to set
	 */
	public void setCC(CommunityCollection cc)
	{
		this.cc = cc;
	}
	
	/**
	 * @brief Applies the Newman-Girvan algorithm to the graph
	 * @param nCom the number of Communities to split the graph into
	 * @return the CommunityCollecion that Newman-Girvan Algorithm produces
	 */
	public CommunityCollection applyNewmanGirvan(int nCom)
	{
		return algoritmeNG.runAlgorithm(graph, nCom);
	}

	/**
	 * @brief Returns the Category identified by a String
	 * @param title the title of the Category to search for
	 * @return the Category identified by the title, or null if not found
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
	 * @brief Returns the Page identified by a String
	 * @param title the title of the Page to search for
	 * @return the Page identified by the title, or null if not found
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
	 * @brief Adds a new Category to the graph
	 * @param c the new Category to add
	 */
	public void addCategory(Category c)
	{
		ONode n = new ONode(c);
		graph.addNode(n);
	}
	
	/**
	 * @brief Adds a new Page to the graph
	 * @param p the new Page to add
	 */
	public void addPage(Page p)
	{
		ONode n = new ONode(p);
		graph.addNode(n);
	}
	
	/**
	 * @brief Removes a Category from the graph
	 * @param c te Category to remove
	 */
	public void delCategory(Category c)
	{
		ONode n = new ONode(c);
		graph.removeNode(n);
	}
	
	/**
	 * @brief Removes a Page from the graph
	 * @param p te Page to remove
	 */
	public void delPage(Page p)
	{
		ONode n = new ONode(p);
		graph.removeNode(n);
	}
	
	/**
	 * @brief Returns if the Category with title 'title' exists in graph
	 * @param title the title of the Category
	 * @return true if the Category with title 'title' exists in graph,
	 *         false otherwise
	 */
	public boolean catExists(String title)
	{
		Category c = new Category(title);
		ONode n = new ONode(c);
		return graph.hasNode(n);
	}

	/**
	 * @brief Returns if the Page with title 'title' exists in graph
	 * @param title the title of the Page
	 * @return true if the Page with title 'title' exists in graph,
	 *         false otherwise
	 */
	public boolean pagExists(String title)
	{
		Page p = new Page(title);
		ONode n = new ONode(p);
		return graph.hasNode(n);
	}

	/**
	 * @brief Creates a new Edge between two Categories
	 * @param c1 one of the Categories to create the Edge to
	 * @param c2 the other Category
	 */
	public void addSuperLink(Category c1, Category c2)
	{
		ONode n1 = new ONode(c1);
		ONode n2 = new ONode(c2);
		OEdge e = new OEdge(n1, n2, OEdge.EdgeType.CsupC);
		graph.addEdge(e);
	}
	
	/**
	 * @brief Creates a new Edge between a Category and a Page
	 * @param c the Category to create the Edge to
	 * @param p the Page to create the Edge to
	 */
	public void addCPLink(Category c, Page p)
	{
		ONode n1 = new ONode(c);
		ONode n2 = new ONode(p);
		OEdge e = new OEdge(n1, n2, OEdge.EdgeType.CP);
		graph.addEdge(e);
	}
}
