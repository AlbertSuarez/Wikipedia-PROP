package wikipedia.domain;

import java.util.Set;
import java.util.HashSet;

public class CommunityCollection {
	private Set<Community> collection;
	
	public CommunityCollection(){ //crea la collection vacia!
		collection = new HashSet<Community>();
	}
	public void PrintCollection(){ //muestra una lista de las comunidades y sus categorias correspondientes
		for(Community com: collection){
			com.PrintCommunity();
		}
	}
	public void GoldenCompare(){ //comparacion de la coleccion con la golden	
	}
	public void AddCommunity(Community c){ //Add una comunidad a la coleccion
		collection.add(c);
	}
	public void ResetCollection(){ //deja la collection vacia
		collection.clear();
	}
	public Community Belongs(Category c){ //retorna la comunidad que contiene la categoria c
		Community pertany = null;
		for (Community com: collection){
			if (com.Belongs(c)) {
				pertany = com;
				break;
			}
		}
		return pertany;
	}
}
