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
		
		PresentationController pc = new PresentationController();
		pc.run();

		DomainController dc = new DomainController();
		dc.Run();
		
		dc.readGraph();
		//dc.readWPformat();
		
		dc.runNG(3);
		
		//dc.printCC();
		
		//dc.writeGraph();
		dc.writeWPformat();
		
		//OGraph g = GraphIO.readGraphWPformat();
		//OGraph g = GraphIO.readGraph();		

		//NewmanGirvan ng = new NewmanGirvan();
		//CommunityCollection cc = Algorithm.runNGAlgorithm(g);
		
		//cc.printCollection();
		
		//GraphIO.writeGraphWPformat(g);
		//GraphIO.writeGraph(g);
		
		print("PROP - Wikipedia - End main");
	}
}
