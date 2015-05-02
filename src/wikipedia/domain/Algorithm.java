/**
 * @file Algorithm.java
 * @author G13.2
 * @date 2 May 2015
 * @brief Algorithm common interface
 */


package wikipedia.domain;
import g13.*;

public interface Algorithm {

	/**
	 * @brief Applies the implemented Algorithm to a graph until
	 *        there are nCom's left or the algorithm can't be applied
	 *        anymore, and returns the CommunityCollection of them
	 * @param G the Graph to apply the Algorithm
	 * @param nCom the number of communities one wants to split the graph into
	 * @return the CommunityCollection result
	 */
	public abstract CommunityCollection runAlgorithm(Graph G, int nCom);

};
