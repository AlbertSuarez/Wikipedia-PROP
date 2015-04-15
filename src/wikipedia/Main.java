package wikipedia;

import static wikipedia.utils.Print.*;
import wikipedia.domain.*;
import wikipedia.presentation.*;

public class Main
{	
	public static void main(String[] args)
	{
		print("PROP - Wikipedia - Start main");

		PresentationController pc = new PresentationController();
		pc.run();

		DomainController dc = new DomainController();
		dc.Run();
		print("PROP - Wikipedia - End main");
	}
}
