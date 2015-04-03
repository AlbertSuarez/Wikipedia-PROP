package wikipedia.domain;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
//import java.util.Locale.Category;

public class CommunityCollection {
	
	private Set<Community> collection;
	
	// Pre:  True.
    // Post: Create a empty CommunityCollection.
	public CommunityCollection()
	{ 
		collection = new HashSet<Community>();
	}
	
	// Pre:  Community c is not in the Community.
    // Post: Community c is in the Community.
	public void AddCommunity(Community c)
	{
		collection.add(c);
	}
	
	// Pre:  True.
    // Post: Return the list of all Communities of the Collection.	
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
			// NI ZORRA IDEA
	}
	
	// Pre:  Community c is in the Community.
    // Post: Community c is not in the Community.
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
