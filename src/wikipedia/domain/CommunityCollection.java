package wikipedia.domain;

import java.util.*;
import g13.*;

/**
 * CommunityCollection class
 * @author G13.2
 */
public class CommunityCollection {
	
	/**
	 * Represents the community collection
	 */
	private ArrayList<Community> collection;
	
	/**
	 * Create a empty Community Collection
	 */
	public CommunityCollection()
	{ 
		collection = new ArrayList<Community>();
	}
	
	/**
	 * Add community to collection
	 * @param c The community to be added
	 * @pre c is not in Collection
	 */
	public void addCommunity(Community c)
	{
		collection.add(c);
	}
	
	/**
	 * Get communities from collection
	 * @return An array list of all Communities of the Collection
	 */
	public ArrayList<Community> getCommunities()
	{
		return collection;
	}
	
	/**
	 * Get a determinate community from collection
	 * @param i The community's index to be get
	 * @return A determinate community of collection
	 */
	public Community getCommunity(int i)
	{
		return collection.get(i);
	}
	
	/**
	 * Change a determinate community from collection for an other community
	 * @param i The community's index to be change
	 * @param c The new community
	 */
	public void setCommunity(int i, Community c)
	{
		collection.set(i,c);
	}
	
	/**
	 * Get the number of communities of the collection
	 * @return The number of communities
	 */
	public int getCommunityCount()
	{
		return collection.size();
	}
	
	/**
	 * Erase community to collection
	 * @param c The community to be erased
	 * @pre c is in Collection
	 */
	public void eraseCommunity(Community c)
	{
		collection.remove(c);
	}
	
	/**
	 * Reset the Community Collection
	 */
	public void resetCollection()
	{
		collection.clear();
	}
		
	/**
	 * See if a node belongs to community
	 * @param n The node
	 * @return True if 'n' is in 'collection', false alternatively.
	 */
	public boolean belongs(Node n)
	{
		for (Community c : collection) {
			if (c.belongs(n)) return true;
		}
		return false;
	}
	
	public int getCommunityOfNode(Node n)
	{
		for (int i = 0; i < collection.size(); i++) {
			for (Node nod : collection.get(i).getNodes()) {
				if (nod.equals(n)) return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Print all communities.
	 */
	public String printCollection()
	{
		String s = "";
		int i = 1;
		for (Community c : collection) {
			s = s + ("Community num. " + i) + "\n";
			s = s + c.printCommunity() + "\n"; ++i;
		}
		return s;
	}
	
}
