package wikipedia.utils;

public class Chooser {
	//añadir filter para filtrar que el archivo debe ser formato txt;
	public static void Choose(String s){
		javax.swing.JFileChooser j= new javax.swing.JFileChooser();
		j.showOpenDialog(j);
		s = j.getSelectedFile().getAbsolutePath();
	}
}
