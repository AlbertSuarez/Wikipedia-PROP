package wikipedia.domain;

import java.util.Set;
import java.util.TreeSet;

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
	public void AddCategory(Category c)
	{ 
		
	}
	
	// Pre:  True.
    // Post: Return the list of all Categories of the Community.	
	public void GetCategories()
	{
		//canviar void por metodo de guardado "array,list,map..."
	}
	
	// Pre:  Category c is in the Community.
    // Post: Category c is not in the Community.
	public void EraseCategory(Category c)
	{
		
	}
	
	// Pre:  True.
    // Post: Print a ordered list of all categories.
	public void PrintCommunity()
	{ 
				
	}
	
	// Pre:  True.
    // Post: Return true if 'c' is in 'catSet', false alternatively.
	public boolean Belongs(Category c)
	{
		return catSet.contains(c);
	}
}