package wikipedia.domain;
import g13.*;

public abstract class Algorithm {

	// Pre:  True
	// Post: Apply Newman-Girvan Algorithm to Graph 'G'.
	public abstract CommunityCollection runNGAlgorithm(Graph G);

};
