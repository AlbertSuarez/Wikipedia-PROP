package wikipedia.presentation;

import g13.*;

import java.io.*;
import java.util.*;
import wikipedia.domain.*;

/**
 * The Controller of Presentation Package
 * @author G13.2
 */
public class PresentationController
{
	/**
	 * The Controller of Domain
	 */
	private DomainController dc;
	private VistaInici inici;
	private VistaWrite write;
	private VistaOptions options;
	private String language;
	
	/**
	 * Create a PresentacionController
	 */
	public PresentationController() {
		dc = new DomainController();
		language = "english.";
		inici = new VistaInici(this);
		write = new VistaWrite(this);
	}

	/**
	 * Execute the Presentation Controller
	 */
	public void run() {
		inici.setVisible(true);
	}
	
	// FUNCIONS PER CANVIAR DE VISTA
	
	/**
	 * Refresh inici window
	 */
	public void refreshInici() {
		inici = new VistaInici(this);
		write = new VistaWrite(this);
		options = new VistaOptions(this);
		inici.setVisible(true);
	}
	
	/**
	 * Refresh write window
	 */
	public void refreshWrite() {
		inici = new VistaInici(this);
		write = new VistaWrite(this);
		options = new VistaOptions(this);
		write.setVisible(true);
	}
	
	/**
	 * Refresh options window
	 */
	public void refreshOptions() {
		inici = new VistaInici(this);
		write = new VistaWrite(this);
		options = new VistaOptions(this);
		options.setVisible(true);
	}
	
	/**
	 * Close inici window
	 */
	public void closeInici() {
		inici.dispose();
	}
	
	/**
	 * Close write window
	 */
	public void closeWrite() {
		write.dispose();
	}
	
	/**
	 * Close options window
	 */
	public void closeOptions() {
		options.dispose();
	}
	
	/**
	 * Change inici to write window
	 */
	public void iniciToWrite() {
		inici.setVisible(false);
		write.setVisible(true);
	}
	
	/**
	 * Change write to inici window
	 */
	public void writeToInici() {
		write.setVisible(false);
		inici.setVisible(true);
	}
	
	/**
	 * Change write to options window
	 */
	public void writeToOptions() {
		write.setVisible(false);
		options = new VistaOptions(this);
		options.setVisible(true);
	}
	
	/**
	 * Change inici to options window
	 */
	public void iniciToOptions() {
		inici.setVisible(false);
		options = new VistaOptions(this);
		options.setVisible(true);
	}
	
	/**
	 * Change options to inici window
	 */
	public void optionsToInici() {
		options.setVisible(false);
		inici.setVisible(true);
	}
	
	/**
	 * Change options to graph window
	 */
	public void optionsToGraph() {
		new VistaGraph(this, false).setVisible(true);
	}
	
	/**
	 * Change options to cc window
	 */
	public void optionsToCC() {
		new VistaGraph(this, true).setVisible(true);
	}
	
	// FUNCIONS DELS BOTONS
	
	/**
	 * Get Language
	 * @return language
	 */
	public String getLanguage()
	{
		return language;
	}
	
	/**
	 * Set Language
	 * @param s new language
	 */
	public void setLanguage(String s)
	{
		language = s;
	}
	
	/**
	 * Load wikipedia
	 * @param f file text
	 */
	public void loadWiki(File f){
		dc.loadWP(f);
	}
	
	/**
	 * Read wikipedia
	 * @param a string's array representation of wikipedia
	 */
	public void readWiki(ArrayList<String> a){
		dc.readWPformat(a);
	}
	
	/**
	 * Add category
	 * @param a the title of category
	 */
	public void addCat(String a) {
		dc.addCategory(a);
	}
	
	/**
	 * Delete category
	 * @param a the title of category
	 */
	public void delCat(String a) {
		dc.delCategory(a);
	}
	
	/**
	 * Add page
	 * @param a the title of page
	 */
	public void addPage(String a) {
		dc.addPage(a);
	}
	
	/**
	 * Delete page
	 * @param a the title of page
	 */
	public void delPage(String a) {
		dc.delPage(a);
	}
	
	/**
	 * Add link between two nodes
	 * @param a the title of first node
	 * @param b the title of second node
	 */
	public void addLink(String a, String b) {
		dc.addLink(a, b);
	}
	
	/**
	 * Delete link between two nodes
	 * @param a the title of first node
	 * @param b the title of second node
	 */
	public void delLink(String a, String b) {
		dc.delLink(a, b);
	}
	
	/**
	 * Modify title of an Element
	 * @param a the old title
	 * @param b the new title
	 */
	public void modElement(String a, String b) {
		dc.modElement(a, b);
	}
	
	/**
	 * Modify the node's community
	 * @param a the title of node
	 * @param b the string representation of community's identifier
	 */
	public void modCommunity(String a, String b) {
		dc.modCommunity(a, b);
	}
	
	/**
	 * Writes with the WP format from the implicit graph
	 * @return the string representation
	 */
	public String printGraph() {
		return dc.writeWPformat();
	}
	
	/**
	 * Saves with the WP format from the implicit graph
	 */
	public void saveGraph() {
		dc.saveWP();
	}
	
	/**
	 * Applies the community detection algorithm
	 * @param a identifier of which algorithm applies
	 * @param nCom number of communities that the result gets (NG)
	 * @return the community collection representation
	 */
	public String communityDetection(Integer a, Integer nCom) {
		if (a == 0) dc.runNG(nCom);
		else if (a == 1) dc.runLouvain();
		else if (a == 2) dc.runCPMaxim();
		return dc.printCC();
	}
	
	/**
	 * Prints the implicit CommunityCollection
	 * @return the string representation
	 */
	public String printCC() {
		return dc.printCC();
	}
	
	/**
	 * Validate Golden
	 * @return the algorithm with less difference respect golden case output
	 */
	public String calculateGolden()
	{
		return dc.calculateGolden();
	}
	
	
	// FUNCIONS AUXILIARS
	
	/**
	 * Get Graph
	 */
	public OGraph getGraph() {
		return dc.getGraph();
	}
	
	/**
	 * Check if the community collection is empty
	 * @return true if the community collection is empty, false alternately
	 */
	public boolean CCisEmpty() {
		return dc.CCisEmpty();
	}
	
	/**
	 * Clean the community collection
	 */
	public void cleanCC() {
		dc.cleanCC();
	}
	
	/**
	 * Check the size of the community collection
	 * @return the size of the community collection
	 */
	public Integer sizeCC() {
		return dc.sizeCC();
	}
	
	public boolean isCat(String a) {
		return dc.isCat(a);
	}
}
