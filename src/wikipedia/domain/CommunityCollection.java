package wikipedia.domain;

import java.util.*;

public abstract class CommunityCollection {
	
	private Set<Community> collection;
	
	// Pre:  True.
	// Post: Create an empty CommunityCollection.
	public CommunityCollection()
	{ 
		collection = new LinkedHashSet<Community>();
	}
	
	// Pre:  Community c is not in the Community.
	// Post: Community c is in the Community.
	public void addCommunity(Community c)
	{
		collection.add(c);
	}
	
	// Pre:  True.
	// Post: Returns a non-modificable set with all the Communities from the Collection.
	public Set<Community> getCommunities()
	{
		return Collections.unmodifiableSet(collection);
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
	public boolean belongs(Community c)
	{
		return collection.contains(c);
	}
	
}
