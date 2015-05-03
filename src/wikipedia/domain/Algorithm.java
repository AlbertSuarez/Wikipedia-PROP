package wikipedia.domain;
import g13.*;

/**
 * Algorithm common interface
 * @author G13.2
 */
public interface Algorithm {

	/**
	 * Applies the implemented Algorithm to a graph until
	 * there are nCom's left or the algorithm can't be applied
	 * anymore, and returns the CommunityCollection of them
	 * @param G The Graph to apply the Algorithm
	 * @param nCom The number of communities one wants to split the graph into
	 * @return The CommunityCollection result
	 */
	public abstract CommunityCollection runAlgorithm(Graph G, int nCom);

};
