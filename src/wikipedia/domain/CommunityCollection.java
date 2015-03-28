package wikipedia.domain;

public class CommunityCollection {
	public CommunityCollection(){ //crear la collection mediante un map,set, array... vacia!
	}
	public Community GetCommunity(int id){
		Community c = new Community();
		return c;
	}
	public void PrintCollection(){ //muestra una lista de las comunidades y sus categorias correspondientes
		//mediante for llamar a printCommunity por cada comunidad
	}
	public void GoldenCompare(){ //comparacion de la coleccion resultante con la golden	
	}
	public void AddCommunity(){ //añade una comunidad a la coleccion
		
	}
	public void ModifyCommunity(Community c){ //modifica la comunidad c --> c es una comunidad modificada cuyo id esta
												// en la collection, simplemente intercanviamos la 
												//comunidad de la collection por c
		
	}
}
