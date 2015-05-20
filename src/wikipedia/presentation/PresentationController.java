package wikipedia.presentation;

import g13.*;
import java.io.File;
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
	private VistaGraph graph;
	
	/**
	 * Create a PresentacionController
	 */
	public PresentationController() {
		dc = new DomainController();
		inici = new VistaInici(this);
		write = new VistaWrite(this);
		options = new VistaOptions(this);
	}

	/**
	 * Execute the Presentation Controller
	 */
	public void run() {
		inici.setVisible(true);
	}
	
	// FUNCIONS PER CANVIAR DE VISTA
	
	public void iniciToWrite() {
		inici.setVisible(false);
		write.setVisible(true);
	}
	
	public void writeToInici() {
		write.setVisible(false);
		inici.setVisible(true);
	}
	
	public void writeToOptions() {
		write.setVisible(false);
		options.setVisible(true);
	}
	
	public void iniciToOptions() {
		inici.setVisible(false);
		options.setVisible(true);
	}
	
	public void optionsToInici() {
		options.setVisible(false);
		inici.setVisible(true);
	}
	
	public void optionsToGraph() {
		//options.setVisible(false);
		new VistaGraph(this).setVisible(true);
	}
	
	// FUNCIONS DELS BOTONS
	
	public void loadWiki(File f){
		dc.loadWP(f);
	}
	
	public void readWiki(ArrayList<String> a){
		dc.readWPformat(a);
	}
	
	public void addCat(String a) {
		dc.addCategory(a);
	}
	
	public void delCat(String a) {
		dc.delCategory(a);
	}
	
	public void addPage(String a) {
		dc.addPage(a);
	}
	
	public void delPage(String a) {
		dc.delPage(a);
	}
	
	public void addLink(String a, String b) {
		dc.addLink(a, b);
	}
	
	public void delLink(String a, String b) {
		dc.delLink(a, b);
	}
	
	public void modElement(String a, String b) {
		dc.modElement(a, b);
	}
	
	public void modCommunity(String a, String b) {
		dc.modCommunity(a, b);
	}
	
	public String printGraph() {
		return dc.writeWPformat();
	}
	
	public void saveGraph() {
		dc.saveWP();
	}
	
	public String communityDetection(Integer a) {
		if (a == 0) dc.runNG(10);
		else if (a == 1) dc.runLouvain();
		else if (a == 2) dc.runCPMaxim();
		return dc.printCC();
	}
	
	public String printCC() {
		return dc.printCC();
	}
	
	public void showGraph() {
		graph = new VistaGraph(this);
		graph.setVisible(true);
	}
	
	public void showCC() {
		graph = new VistaGraph(this);
		graph.setVisible(true);
	}
	
	public String calculateGolden()
	{
		return dc.calculateGolden();
	}
	
	
	// FUNCIONS AUXILIARS
	
	public OGraph getGraph() {
		return dc.getGraph();
	}
}
