package wikipedia.domain;

import java.util.*;
//import java.util.Locale.Category;

public class CommunityCollection {
	
	private Set<Community> collection;
	
	// Pre:  True.
	// Post: Create an empty CommunityCollection.
	public CommunityCollection()
	{ 
		collection = new LinkedHashSet<Community>();
	}
	
	// Pre:  Community c is not in the Community.
	// Post: Community c is in the Community.
	public void AddCommunity(Community c)
	{
		collection.add(c);
	}
	
	// Pre:  True.
	// Post: Returns an array with all the Communities from the Collection.	
	public Community[] GetCommunities()
	{
		Community[] vector = new Community[collection.size()];
		Iterator<Community> it = collection.iterator();
		for (int i = 0; i < collection.size(); i++){
			vector[i] = (Community)it.next();
		}
		return vector;
	}
	
	// Pre:  True.
	// Post: Prints a list of all the communities from the collection.
	public void PrintCollection()
	{
		for(Community com: collection){
			com.PrintCommunity();
		}
	}
	
	// Pre:  Community c is in the Community.
	// Post: Community c is not in the Community anymore.
	public void EraseCommunity(Community c)
	{
		collection.remove(c);
	}
	
	// Pre:  True.
	// Post: Collection is empty.
	public void ResetCollection()
	{
		collection.clear();
	}
	
	/*hacer abstracta
	
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
	*/
}
