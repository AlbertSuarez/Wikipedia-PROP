package wikipedia.domain;
import wikipedia.persistence.*;
import g13.*;
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
	// Post: Implicit graph is write it with WP format.
	public void writeWPformat() {
		GraphIO.writeGraphWPformat(wikipedia.getGraph());
	}

	// Pre:  True
	// Post: Apply Newman-Girvan Algorithm in Implicit Community Collection of WP.
	public void runNG(int nCom){
		CommunityCollection cc = wikipedia.applyNewmanGirvan(nCom);
		for (int i = 0; i < cc.getCommunityCount(); i++) {
			Community c = cc.getCommunity(i);
			Object nodes[] = c.getNodes().toArray();
			for (int j = 0; j < nodes.length; ++j) {
				ONode n = (ONode) nodes[j];
				if (n.getElement() instanceof Page) c.eraseNode(n);
			}
			
			if (c.isEmpty()) { cc.eraseCommunity(c); --i;}
			else cc.setCommunity(i, c);
		}
		wikipedia.setCC(cc);
	}

	// Pre:  True
	// Post: Print Implicit Community Collection.
	public void printCC()
	{
		CommunityCollection COM = wikipedia.getCC();
		if (COM.getCommunities().isEmpty()) print("The community collection is empty.");
		else COM.printCollection();
	}
}
