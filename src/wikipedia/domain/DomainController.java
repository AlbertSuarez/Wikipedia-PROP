/**
 * Domain Controller Class
 * @author G13.2
 */

package wikipedia.domain;

import java.io.*;
import java.util.*;

import wikipedia.persistence.*;
import g13.*;
import static wikipedia.utils.Utils.*;

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

	public void addCategory(String a) {
		wikipedia.addCategory(new Category(a));
	}
	
	public void delCategory(String a) {
		wikipedia.delCategory(new Category(a));
	}
	
	public void addPage(String a) {
		wikipedia.addPage(new Page(a));
	}
	
	public void delPage(String a) {
		wikipedia.delPage(new Page(a));
	}
	
	public void addLink(String a, String b) {
		if (wikipedia.catExists(a) && wikipedia.pagExists(b)) wikipedia.addCPLink(new Category(a), new Page(b));
		else if (wikipedia.catExists(a) && wikipedia.catExists(b)) wikipedia.addSuperLink(new Category(a), new Category(b));
		else if (wikipedia.pagExists(a) && wikipedia.catExists(b)) wikipedia.addCPLink(new Category(b), new Page(a));
	}
	
	public void delLink(String a, String b) {
		if (wikipedia.catExists(a) && wikipedia.pagExists(b)) wikipedia.delLink(new Category(a), new Page(b));
		else if (wikipedia.catExists(a) && wikipedia.catExists(b)) wikipedia.delLink(new Category(a), new Category(b));
		else if (wikipedia.pagExists(a) && wikipedia.catExists(b)) wikipedia.delLink(new Category(b), new Page(a));
	}
	
	public void modElement(String a, String b) {
			wikipedia.modElement(a, b);
	}
	
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
	 */
	public String printCC()
	{
		String s = "";
		CommunityCollection COM = wikipedia.getCC();
		if (COM.getCommunities().isEmpty()) s = "The community collection is empty.";
		else s = COM.printCollection();
		return s;
	}
	
	public OGraph getGraph()
	{
		return wikipedia.getGraph();
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
