package wikipedia.domain;

import java.util.Set;
import java.util.HashSet;

public class CommunityCollection {
	
	private Set<Community> collection;
	
	// Pre:  True.
    // Post: Create a empty CommunityCollection.
	public CommunityCollection()
	{ 
		collection = new HashSet<Community>();
	}
	
	// Pre:  True.
    // Post: Return the list of all Communities of the Collection.	
	public void GetCommunities()
	{
		//canviar void por metodo de guardado "array,list,map..."
	}
	
	// Pre:  True.
    // Post: Print a list of all communities of collection.
	public void PrintCollection()
	{
		for(Community com: collection){
			com.PrintCommunity();
		}
	}
	
	// Pre:  True.
    // Post: Compare the goodness with Golden Collection.
	public void GoldenCompare()
	{
			
	}
	
	// Pre:  Community c is not in the Community.
    // Post: Community c is in the Community.
	public void AddCommunity(Community c)
	{
		collection.add(c);
	}
	
	// Pre:  Community c is in the Community.
    // Post: Community c is not in the Community.
	public void EraseCommunity(Community c)
	{
		
	}
	
	// Pre:  True.
    // Post: Collection is empty.
	public void ResetCollection()
	{
		collection.clear();
	}
	
	// Pre:  True.
    // Post: Return community that contains category 'c' if exists,
	//		 return a empty community alternatively.
	public Community Belongs(Category c)
	{
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
