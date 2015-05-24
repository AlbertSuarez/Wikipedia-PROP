/**
 * Domain Controller Class
 * @author G13.2
 */

package wikipedia.domain;

import java.io.*;
import java.util.*;

import wikipedia.persistence.*;
import g13.*;

public class DomainController
{
	/**
	 * Represents the domain package
	 */
	private WP wikipedia;

	/**
	 * Creates a new DomainController
	 */
	public DomainController() {
		wikipedia = new WP();
	}

	/**
	 * Add category
	 * @param a the title of category
	 */
	public void addCategory(String a) {
		wikipedia.addCategory(new Category(a));
	}

	/**
	 * Delete category
	 * @param a the title of category
	 */
	public void delCategory(String a) {
		wikipedia.delCategory(new Category(a));
	}

	/**
	 * Add page
	 * @param a the title of page
	 */
	public void addPage(String a) {
		wikipedia.addPage(new Page(a));
	}

	/**
	 * Delete page
	 * @param a the title of page
	 */
	public void delPage(String a) {
		wikipedia.delPage(new Page(a));
	}

	/**
	 * Add link between two nodes
	 * @param a the title of first node
	 * @param b the title of second node
	 */
	public void addLink(String a, String b) {
		if (wikipedia.catExists(a) && wikipedia.pagExists(b)) wikipedia.addCPLink(new Category(a), new Page(b));
		else if (wikipedia.catExists(a) && wikipedia.catExists(b)) wikipedia.addSuperLink(new Category(a), new Category(b));
		else if (wikipedia.pagExists(a) && wikipedia.catExists(b)) wikipedia.addCPLink(new Category(b), new Page(a));
	}

	/**
	 * Delete link between two nodes
	 * @param a the title of first node
	 * @param b the title of second node
	 */
	public void delLink(String a, String b) {
		if (wikipedia.catExists(a) && wikipedia.pagExists(b)) wikipedia.delLink(new Category(a), new Page(b));
		else if (wikipedia.catExists(a) && wikipedia.catExists(b)) wikipedia.delLink(new Category(a), new Category(b));
		else if (wikipedia.pagExists(a) && wikipedia.catExists(b)) wikipedia.delLink(new Category(b), new Page(a));
	}

	/**
	 * Modify title of an Element
	 * @param a the old title
	 * @param b the new title
	 */
	public void modElement(String a, String b) {
			wikipedia.modElement(a, b);
	}

	/**
	 * Modify the node's community
	 * @param a the title of node
	 * @param b the string representation of community's identifier
	 */
	public void modCommunity(String a, String b) {
		wikipedia.modCommunity(a, Integer.parseInt(b));
	}

	/**
	 * Reads with the WP format into the implicit graph
	 */
	public void readWPformat(ArrayList<String> wiki) {
		wikipedia.setGraph(GraphIO.readGraphWPformat(wiki));
	}

	/**
	 * Loads with the WP format into the implicit graph
	 */
	public void loadWP(File f) {
		wikipedia.setGraph(GraphIO.loadWP(f));
	}

	/**
	 * Saves with the WP format from the implicit graph
	 */
	public void saveWP() {
		GraphIO.saveWP(wikipedia.getGraph());
	}

	/**
	 * Writes with the WP format from the implicit graph
	 * @return the string representation
	 */
	public String writeWPformat() {
		return GraphIO.writeGraphWPformat(wikipedia.getGraph());
	}

	/**
	 * Applies the Newman-Girvan Algorithm to the implicit graph
	 * and saves it to the implicit CommunityCollection
	 * @param nCom the number of Communities to split the graph into
	 */
	public void runNG(int nCom) {
		wikipedia.setCC(erasePages(wikipedia.applyNewmanGirvan(nCom)));
	}

	/**
	 * Applies the Clique Percolation Algorithm (slow version) to the implicit graph
	 * and saves it to the implicit CommunityCollection
	 */
	public void runCPMaxim() {
		wikipedia.setCC(erasePages(wikipedia.applyCliquePercolationMaxim()));
	}

	/**
	 * Applies the Louvain Algorithm (fast version) to the implicit graph
	 * and saves it to the implicit CommunityCollection
	 */
	public void runLouvain() {
		wikipedia.setCC(erasePages(wikipedia.applyLouvain()));
	}

	/**
	 * Prints the implicit CommunityCollection
	 * @return the string representation
	 */
	public String printCC()
	{
		String s = "";
		CommunityCollection COM = wikipedia.getCC();
		if (COM.getCommunities().isEmpty()) s = "The community collection is empty.";
		else s = COM.printCollection();
		return s;
	}

	/**
	 * Check if the community collection is empty
	 * @return true if the community collection is empty, false alternately
	 */
	public boolean CCisEmpty()
	{
		return (wikipedia.getCC().getCommunityCount() == 0);
	}

	/**
	 * Clean the community collection
	 */
	public void cleanCC()
	{
		wikipedia.setCC(new CommunityCollection());;
	}

	/**
	 * Check the size of the community collection
	 * @return the size of the community collection
	 */
	public Integer sizeCC()
	{
		return wikipedia.getCC().getCommunityCount();
	}

	/**
	 * Get Graph
	 * @return the graph of wikipedia
	 */
	public OGraph getGraph()
	{
		return wikipedia.getGraph();
	}

	/**
	 * Validate Golden
	 * @return the algorithm with less difference respect golden case output
	 */
	public String calculateGolden()
	{
		return wikipedia.calculateGolden();
	}

	/**
	 * Erase pages of a Community Collection
	 * @param cc The community collection with Pages and Categories
	 * @return The community collection with only Categories
	 */
	private CommunityCollection erasePages(CommunityCollection cc) {
		for (int i = 0; i < cc.getCommunityCount(); i++) {
			Community c = cc.getCommunity(i);
			Object nodes[] = c.getNodes().toArray();
			for (int j = 0; j < nodes.length; ++j) {
				ONode n = (ONode) nodes[j];
				if (n.getElement().getElementType() == Element.ElementType.ELEMENT_PAGE) {
					c.eraseNode(n);
				}
			}

			if (c.isEmpty()) { cc.eraseCommunity(c); --i;}
			else cc.setCommunity(i, c);
		}
		return cc;
	}
}
