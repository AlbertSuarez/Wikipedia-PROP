package wikipedia.domain;

import java.util.*;
import g13.*;

public class CommunityCollection {
	
	private ArrayList<Community> collection;
	
	// Pre:  True.
	// Post: Create an empty CommunityCollection.
	public CommunityCollection()
	{ 
		collection = new ArrayList<Community>();
	}
	
	// Pre:  Community c is not in the Community.
	// Post: Community c is in the Community.
	public void addCommunity(Community c)
	{
		collection.add(c);
	}
	
	// Pre:  True.
	// Post: Returns a non-modificable set with all the Communities from the Collection.
	public ArrayList<Community> getCommunities()
	{
		return collection;
	}
	
	// Pre:  True
	// Post: Returns the community 'i' of the collection.
	public Community getCommunity(int i)
	{
		return collection.get(i);
	}
	
	// Pre:  True
	// Post: Change the community of the collection that her position is 'i' to 'c'.
	public void setCommunity(int i, Community c)
	{
		collection.set(i,c);
	}
	
	// Pre:	True
	// Post: Returns number of communities
	public int getCommunityCount()
	{
		return collection.size();
	}
	
	// Pre:  Community c is in the Community.
	// Post: Community c is not in the Community anymore.
	public void eraseCommunity(Community c)
	{
		collection.remove(c);
	}
	
	// Pre:  True.
	// Post: Collection is empty.
	public void resetCollection()
	{
		collection.clear();
	}
		
	// Pre:  True.
	// Post: If c belongs to CommunityCollection -> true
	//		 Else -> false
	public boolean belongs(Node n)
	{
		for (Community c : collection) {
			if (c.belongs(n)) return true;
		}
		return false;
	}
	
	// Pre: True
	// Post: Print the community collection
	public void printCollection()
	{
		int i = 1;
		for (Community c : collection) {
			System.out.println("Community num. " + i);
			c.printCommunity(); ++i;
		}
	}
	
}
