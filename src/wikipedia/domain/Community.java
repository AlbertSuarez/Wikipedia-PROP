package wikipedia.domain;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;
import static wikipedia.utils.Print.*;

public class Community {
	
	// catSet is only of Category because the statement reflects this. 
	private Set<Category> catSet;
	
	// Pre:  True.
    // Post: Create a empty Community.
	public Community()
	{
		catSet = new TreeSet<Category>();
	}
	
	// Pre:  Category c is not in the Community.
    // Post: Category c is in the Community.
	public void addCategory(Category c)
	{ 
		catSet.add(c);
	}
	
	// Pre:  True.
    // Post: Return an non-modificable of all Categories of the Community.	
	public Set<Category> getCategories()
	{
		return Collections.unmodifiableSet(catSet);
	}
	
	// Pre:  Category c is in the Community.
    // Post: Category c is not in the Community.
	public void eraseCategory(Category c)
	{
		catSet.remove(c);
	}
	
	// Pre:  True.
    // Post: Print a ordered list of all categories.
	public void printCommunity()
	{ 
		for (Iterator<Category> it = catSet.iterator(); it.hasNext();){ 
		    Category aux = (Category)it.next();
		    print(aux.getTitle());
		}
	}
	
	// Pre:  True.
    // Post: Return true if 'c' is in 'catSet', false alternatively.
	public boolean belongs(Category c)
	{
		return catSet.contains(c);
	}
}