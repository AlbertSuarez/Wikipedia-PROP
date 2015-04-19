package wikipedia.domain;
import wikipedia.persistence.*;

public class DomainController
{
	private WP wikipedia;

	public DomainController() {
		wikipedia = new WP();
	}
	
	public void Run(){
		PersistenceController pc = new PersistenceController();
		pc.load(wikipedia);
	}
	
	public void readWPformat() {
		wikipedia.setGraph(GraphIO.readGraphWPformat());
	}
	
	public void readGraph() {
		wikipedia.setGraph(GraphIO.readGraph());
	}
	
	public void writeWPformat() {
		GraphIO.writeGraphWPformat(wikipedia.getGraph());
	}
	
	public void writeGraph() {
		GraphIO.writeGraph(wikipedia.getGraph());
	}
	
	public void runNG(){
		wikipedia.setCC(wikipedia.applyNewmanGirvan());
	}

	public void printCC()
	{
		wikipedia.getCC().printCollection();
	}
}
