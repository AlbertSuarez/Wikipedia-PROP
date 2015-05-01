package wikipedia.domain;
import wikipedia.persistence.*;
import static wikipedia.utils.utils.*;

public class DomainController
{
	private WP wikipedia;

	// Pre:  True
	// Post: Return a WP empty
	public DomainController() {
		wikipedia = new WP();
	}

	// Pre:  True
	// Post: Load data with a external file.
	public void Run(){
		PersistenceController pc = new PersistenceController();
		pc.load(wikipedia);
	}

	// Pre:  True
	// Post: Implicit graph is read it with WP format.
	public void readWPformat() {
		wikipedia.setGraph(GraphIO.readGraphWPformat());
	}
	
	// Pre:  True
	// Post: Implicit graph is loaded with WP format.
	public void loadWP() {
		wikipedia.setGraph(GraphIO.loadWP());
	}
	
	// Pre:  True
	// Post: Implicit graph is loaded with WP format.
	public void saveWP() {
		GraphIO.saveWP(wikipedia.getGraph());
	}

	// Pre:  True
	// Post: Implicit graph is read it.
	public void readGraph() {
		wikipedia.setGraph(GraphIO.readGraph());
	}

	// Pre:  True
	// Post: Implicit graph is write it with WP format.
	public void writeWPformat() {
		GraphIO.writeGraphWPformat(wikipedia.getGraph());
	}

	// Pre:  True
	// Post: Implicit graph is write it.
	public void writeGraph() {
		GraphIO.writeGraph(wikipedia.getGraph());
	}

	// Pre:  True
	// Post: Apply Newman-Girvan Algorithm in Implicit Community Collection of WP.
	public void runNG(int nCom){
		wikipedia.setCC(wikipedia.applyNewmanGirvan(nCom));
	}

	// Pre:  True
	// Post: Print Implicit Community Collection.
	public void printCC()
	{
		CommunityCollection COM = wikipedia.getCC();
		if (COM == null) print("The community collection is empty.");
		else COM.printCollection();
	}
}
