package wikipedia.domain;

import java.util.Set;
import java.util.TreeSet;

public class Community {
	
	private Set<Category> catSet;
	
	public Community(){
		catSet = new TreeSet<Category>();
	}
	public void AddCategory(Category c){ // Add la categoria c a la comunidad
	}
	public void GetCategories(){ //canviar void por metodo de guardado "array,list,map..."
	}
	// Pre: c pertenece a la comunidad
	public void EraseCategory(Category c){ // borra la categoria c de la comunidad (establecer como pre q este??)
	}
	public void PrintCommunity(){ //Muestra una lista ordenada de las categorias pertenecientes a la comunidad		
	}
	public boolean Belongs(Category c){ //retorna true si la categoria c pertenece a la comunidad
		return catSet.contains(c);
	}
}
