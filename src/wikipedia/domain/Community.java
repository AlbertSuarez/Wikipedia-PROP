package wikipedia.domain;

import java.util.Iterator;
//import java.util.Locale.Category;
import java.util.Set;
import java.util.TreeSet;
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
	public void AddCategory(Category c)
	{ 
		catSet.add(c);
	}
	
	// Pre:  True.
    // Post: Return a array of all Categories of the Community.	
	public Category[] GetCategories()
	{
		Category[] vector = new Category[catSet.size()];
		Iterator<Category> it = catSet.iterator();
		for (int i = 0; i < catSet.size(); i++){
			vector[i] = (Category)it.next();
		}
		return vector;
	}
	
	// Pre:  Category c is in the Community.
    // Post: Category c is not in the Community.
	public void EraseCategory(Category c)
	{
		catSet.remove(c);
	}
	
	// Pre:  True.
    // Post: Print a ordered list of all categories.
	public void PrintCommunity()
	{ 
		for (Iterator<Category> it = catSet.iterator(); it.hasNext();){ 
		    Category aux = (Category)it.next();
		    print(aux.getTitle());
		}
	}
	
	// Pre:  True.
    // Post: Return true if 'c' is in 'catSet', false alternatively.
	public boolean Belongs(Category c)
	{
		return catSet.contains(c);
	}
}