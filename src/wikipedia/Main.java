package wikipedia;

import static wikipedia.utils.utils.*;
import wikipedia.domain.*;
//import wikipedia.presentation.*;

public class Main
{	
	 private static final String[] OPTION_LIST = new String[] {
	        "READ", "LOAD", "COMMUNITY_DETECTION", "PRINTWP",
	        "SAVE","PRINT_OPTION_LIST", "EXIT"
	    };
	 
 	private static void printOptions() {
        print("OPTION LIST:");
        for (String option : OPTION_LIST) print("* " + option);
        print("END OF OPTION LIST\n");
    } 
 	
	public static void main(String[] args)
	{
		print("PROP - Wikipedia - Start main");
		
		//no necesario para la segunda entrega, para tercera solo crear pc, dc se carga desde pc
		//PresentationController pc = new PresentationController();
		//pc.run();

		DomainController dc = new DomainController();
		dc.Run();
		
		print("welcome to the wikipedia project, select one of the following options by "
				+ "writting it");
		printOptions();
		String s = readln();
		 while (!s.equals("EXIT")) {
	            switch(s) {
	            	case "READ":
	            		print("write on screen the graph you wanna enter in wikipedia format");
	            		dc.readWPformat();
	            		break;
	            	case "LOAD":
	            		print("select if you want to load an external wikipedia from a file, or the wikipedia "
	            				+ "saved by the system. Insert 0 for first option and 1 for second");
	            		int opcion = Integer.parseInt(readln());
	            		if(opcion == 0){
	            			print("select the file which contains the wikipedia you wanna load\n*** "
	            					+ "HINT: if you don't see the window to select, maybe it's running on backward");
	            			dc.loadWP(false);
	            		}
	            		else dc.loadWP(true);
	            		break;
	            	case "COMMUNITY_DETECTION":
	            		print("insert how many communities you want to detect");
	            		s = readln();
	            		dc.runNG(Integer.parseInt(s));
	            		print("this is the result of theoperation:");
	            		dc.printCC();
	            		break;
	            	case "PRINTWP":
	            		print("This is the content of the wikipedia in wikipedia format");
	            		dc.writeWPformat();
	            		break;
	            	case "SAVE":
	            		print("the wikipedia has been saved in a default data file ");
	            		break;
	                case "PRINT_OPTION_LIST":
	            		printOptions();
	            		break;
	                default:
	                    print("Invalid operation name\n*** HINT: Enter " +
	                            "PRINT_OPTION_LIST to list available options");
	                    break;
	            }
	        s = readln();
	        }
		print("PROP - Wikipedia - End main");
	}
}
