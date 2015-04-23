package wikipedia.utils;

import java.io.File;

public class Chooser {
	//añadir filter para filtrar que el archivo debe ser formato txt;
	public static File Choose(){
		javax.swing.JFileChooser j= new javax.swing.JFileChooser();
		j.showOpenDialog(j);
		File f = j.getSelectedFile();
		return f;
	}
}
