/**
* @mainpage Wikipedia - PROP Project
* This is the documentation of the PROP Project: Wikipedia
*/

package wikipedia;

import static wikipedia.utils.utils.*;

import java.io.IOException;

import wikipedia.domain.*;
//import wikipedia.presentation.*;

/**
 * Main Class
 * @author G13.2
 */
public class Main
{	
	 private static final String[] OPTION_LIST = new String[] {
	        "READ", "LOAD", "COMMUNITY_DETECTION", "PRINT_CC", "PRINT_WP",
	        "SAVE","PRINT_OPTION_LIST", "EXIT"
	    };
	 
 	private static void printOptions() {
        print("OPTION LIST:");
        for (String option : OPTION_LIST) print("* " + option);
        print("END OF OPTION LIST\n");
    }
 	
 	
	public static void main(String[] args) throws IOException
	{
		print("PROP - Wikipedia - Start main");
		
		//no necesario para la segunda entrega, para tercera solo crear pc, dc se carga desde pc
		//PresentationController pc = new PresentationController();
		//pc.run();

		DomainController dc = new DomainController();
		
		print("welcome to the wikipedia project, select one of the following options by "
				+ "writting it");
		printOptions();
		print("\nEnter an option: ");
		String s = readln();
		 while (!s.equals("EXIT")) {
	            switch(s) {
	            	case "READ":
	            		print("write on screen the graph you wanna enter in wikipedia format\n"
	            				+ "Once you end, write \"-1\" to finish the input");
	            		dc.readWPformat();
	            		break;
	            	case "LOAD":
	            		print("select the file you want to load\n"
	            				+ "**HINT: if you don't see the window to choose, maybe it's running on backwards");
	            		dc.loadWP();
	            		break;
	            	case "COMMUNITY_DETECTION":
	            		print("insert how many communities you want to detect");
	            		s = readln();
	            		dc.runNG(Integer.parseInt(s));
	            		break;
	            	case "PRINT_CC":
	            		print("the community collection of WP is:");
	            		dc.printCC();
	            		break;
	            	case "PRINT_WP":
	            		print("This is the content of the wikipedia in wikipedia format");
	            		dc.writeWPformat();
	            		break;
	            	case "SAVE":
	            		print("choose where do you want to save the wikipedia\n "
	            				+ "**HINT: if you don't see the window to choose, maybe it's running on backwards");
	            		dc.saveWP();
	            		break;
	                case "PRINT_OPTION_LIST":
	            		printOptions();
	            		break;
	                default:
	                    print("Invalid operation name\n*** HINT: Enter " +
	                            "PRINT_OPTION_LIST to list available options");
	                    break;
	            }
	        print("\nEnter an option: ");
	        s = readln();
	        }
		print("PROP - Wikipedia - End main");
	}
}
