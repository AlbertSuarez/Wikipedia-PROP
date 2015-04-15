package wikipedia;

import static wikipedia.utils.Print.*;
import wikipedia.domain.*;
import wikipedia.presentation.*;
import wikipedia.persistence.*;
import g13.*;

public class Main
{	
	public static void main(String[] args)
	{
		print("PROP - Wikipedia - Start main");
		
		OGraph g = GraphIO.readGraph();
		
		NewmanGirvan ng = new NewmanGirvan();
		CommunityCollection cc = ng.runAlgorithm(g);
		
		
		GraphIO.writeGraph(g);

		PresentationController pc = new PresentationController();
		pc.run();

		DomainController dc = new DomainController();
		dc.Run();
		print("PROP - Wikipedia - End main");
	}
}
