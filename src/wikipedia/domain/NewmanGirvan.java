package wikipedia.domain;
import g13.*;
import java.util.*;

/**
 * Newman-Girvan algorithm implementation
 * @author G13.2
 */
public class NewmanGirvan implements Algorithm {

	/**
	 * Put the nodes of connected component to community
	 * @param G The Graph where the nodes are
	 * @param nodes The Nodes of the Graph
	 * @param vist Indicate if the node are visited or not
	 * @param i The index of the first node of the connected component
	 * @param nodeMap Node to Idx
	 * @return A community with nodes of the connected component
	 */
	private Community putToCollectionIt(Graph G, Node[] nodes, boolean[] vist, int i, Map<Node, Integer> nodeMap) {
		Stack<Integer> s = new Stack<Integer>();
		Community c = new Community();
		vist[i] = true;

		s.push(i);
		while (!s.isEmpty()) {
			int j = s.peek();
			c.addNode(nodes[j]);
			s.pop();
			Collection<Edge> adjEdgesSet = G.getValidAdjacencyList(nodes[j]);
			for (Edge e : adjEdgesSet) {
				Node adjNode = e.getNeighbor(nodes[j]);
				int k = nodeMap.get(adjNode);
				if (!vist[k]) {
					vist[k] = true;
					s.push(k);
				}
			}
		}
		return c;
	}

	/**
	 * Put the nodes of Graph to community collection
	 * @param G The Graph where the nodes are
	 * @param nodes The Nodes of the Graph
	 * @param nodeMap Node to Idx
	 * @return The community collection of the Graph
	 */
	private CommunityCollection putToCollection(Graph G, Node[] nodes, Map<Node, Integer> nodeMap) {
		int nodeCount = G.getOrder();
		CommunityCollection cc = new CommunityCollection();

		boolean[] vist = new boolean[nodeCount];
		Arrays.fill(vist, false);

		for (int i = 0; i < nodeCount; ++i) {
			if (!vist[i]) {
				cc.addCommunity(putToCollectionIt(G, nodes, vist, i, nodeMap));
			}
		}
		return cc;
	}

	/**
	 * Travel the nodes of connected component
	 * @param G The Graph where the nodes are
	 * @param nodes The Nodes of the Graph
	 * @param vist Indicate if the node are visited or not
	 * @param i The index of the first node of the connected component
	 * @param nodeMap Node to Idx
	 */
	private void getConnectedComponentCountIt(Graph G, Node[] nodes, boolean[] vist, int i, Map<Node, Integer> nodeMap) {
		Stack<Integer> s = new Stack<Integer>();
		vist[i] = true;

		s.push(i);
		while (!s.isEmpty()) {
			int j = s.peek();
			s.pop();
			Collection<Edge> adjEdgesSet = G.getValidAdjacencyList(nodes[j]);
			for (Edge e : adjEdgesSet) {
				Node adjNode = e.getNeighbor(nodes[j]);
				int k = nodeMap.get(adjNode);
				if (!vist[k]) {
					vist[k] = true;
					s.push(k);
				}
			}
		}
	}

	/**
	 * Get the number of connected components
	 * @param G The Graph where the nodes are
	 * @param nodes The Nodes of the Graph
	 * @param nodeMap Node to Idx
	 * @return The number of connected components
	 */
	private int getConnectedComponentCount(Graph G, Node[] nodes, Map<Node, Integer> nodeMap) {
		int nodeCount = G.getOrder();

		boolean[] vist = new boolean[nodeCount];
		Arrays.fill(vist, false);

		int count = 0;
		for (int i = 0; i < nodeCount; ++i) {
			//if (!vist[i]) {
			// Arregla incoherencia con el boton de la vista?
			if (!vist[i] && ((ONode)nodes[i]).getElement().getElementType() != Element.ElementType.ELEMENT_PAGE) {
				getConnectedComponentCountIt(G, nodes, vist, i, nodeMap);
				++count;
			}
		}
		return count;
	}

	/**
	 * The first step of Newman-Girvan algorithm
	 * @param G The Graph
	 * @param nodes The Nodes of the Graph
	 * @param d The distances from source to s
	 * @param w The number shortest path from source to s
	 * @param s The node that applies BFS
	 * @param pila The integer stack that be used for the algorithm
	 * @param nodeMap Node to Idx
	 */
	private void stage1_BFS(Graph G, Node[] nodes, double[] d, double[] w, int s, Stack<Integer> pila, Map<Node, Integer> nodeMap) {

		int nodeCount = G.getOrder();
		boolean[] vist = new boolean[nodeCount];
		Arrays.fill(vist, false);
		Arrays.fill(d, Integer.MAX_VALUE);
		Arrays.fill(w, 0);

		d[s] = 0;
		w[s] = 1;

		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		pila.push(s); // Guardar orden inverso

		while (!q.isEmpty()) {
			int u = q.poll();
			if (!vist[u]) {
				vist[u] = true;
				Collection<Edge> adjEdgesSet = G.getValidAdjacencyList(nodes[u]);

				for (Edge e : adjEdgesSet) {
					Node adjNode = e.getNeighbor(nodes[u]);
					int v = nodeMap.get(adjNode);
					if (d[v] > d[u] + 1) {
						d[v] = d[u] + 1;
						w[v] = w[u];
						q.add(v);
						pila.push(v); // Guardar orden inverso
					} else if (d[v] == d[u] + 1) {
						w[v] += w[u];
					}
				}
			}
		}
	}

	/**
	 * The second step of Newman-Girvan algorithm
	 * @param G The Graph
	 * @param nodes The Nodes of the Graph
	 * @param edgeMap The Map representation of Edge's Set
	 * @param pila The integer stack that be used for the algorithm
	 * @param d The distances from source to s
	 * @param b Number shortest path between source to any vertex in graph pass through vertex i
	 * @param w The number shortest path from source to s
	 * @param arco The weight of the nodes
	 * @param nodeMap Node to Idx
	 */
	private void stage2_betweenness(Graph G, Node[] nodes, Map<Edge, Integer> edgeMap, Stack<Integer> pila, double[] d,
							double[] b, double[] w, double[] arco, Map<Node, Integer> nodeMap) {

		while (!pila.isEmpty()) {
			int u = pila.pop();
			b[u] += 1;

			Collection<Edge> adjEdgesSet = G.getValidAdjacencyList(nodes[u]);

			for (Edge e : adjEdgesSet) {
				Node adjNode = e.getNeighbor(nodes[u]);
				int v = nodeMap.get(adjNode);
				int uvArco = edgeMap.get(e);
				if (d[v] < d[u]) {
					double calc = w[v]/w[u]*b[u];
					arco[uvArco] += calc; // fraccion de shortest paths
					                      // que pasan por la arista
					b[v] += calc;         // fraccion de shortest paths
					                      // que pasan por el vertice
				}
			}
		}
	}

	/**
	 * Applies a iteration of the Newman-Girvan algorithm
	 * @param G the Graph to apply the Newman-Girvan algorithm
	 * @param nodes The nodes of the Graph
	 * @param edges The edges of the Graph
	 * @param edgeMap The Map representation of Edge's Set
	 * @param nodeMap Node to Idx
	 */
	private void runNGAlgorithmIt(Graph G, Node[] nodes, Edge[] edges, Map<Edge, Integer> edgeMap, Map<Node, Integer> nodeMap) {
		int nodeCount = G.getOrder();
		// Build the edgeId -> Edge hashtable (*TODO*)
		double[] arco = new double[G.getEdgeCount()];
		// Reset vector<> arco
		Arrays.fill(arco, 0);

		for (int i = 0; i < nodeCount; ++i) {
			double[] d; //Distancia
			double[] w; //Number shortest path from source to i

			d = new double[nodeCount];
			w = new double[nodeCount];

			Stack<Integer> pila = new Stack<Integer>();
			stage1_BFS(G, nodes, d, w, i, pila, nodeMap);

			double[] b = new double[G.getOrder()]; //Number shortest path between source to any
											//vertex in graph pass through vertex i
			stage2_betweenness(G, nodes, edgeMap, pila, d, b, w, arco, nodeMap);

		}

		/*for (int i = 0; i < arco.length; i++) {
			print(arco[i]);
		}*/

		int imax = -1;
		double max = -1;
		for (int i = 0; i < arco.length; i++) {
			if (!edges[i].isValid()) continue;
			if (max	< arco[i]/edges[i].getWeight()) {
				imax = i;
				max = arco[i]/edges[i].getWeight();
			}
		}
		edges[imax].setValidity(false);
	}

	/**
	 * Applies the Newman-Girvan repeatedly to a graph until
	 * there are nCom's left or the algorithm can't be applied
	 * anymore, and returns the CommunityCollection of them
	 * @param G the Graph to apply the Newman-Girvan algorithm
	 * @param nCom the number of communities one wants to split the graph into
	 * @return the CommunityCollection result
	 */
	public CommunityCollection runAlgorithm(Graph G, int nCom) {

		// Create node array
		Node[] nodes = G.getNodes().toArray(new Node[G.getOrder()]);
		// Create edges array
		Edge[] edges = G.getEdges().toArray(new Edge[G.getEdgeCount()]);
		// Create nodeMap
		Map<Node, Integer> nodeMap = new LinkedHashMap<Node, Integer>();
		for (int i = 0; i < nodes.length; i++) {
			nodeMap.put(nodes[i], i);
		}
		// Create edgeMap
		Map<Edge, Integer> edgeMap = new LinkedHashMap<Edge, Integer>();
		for (int i = 0; i < edges.length; i++) {
			edgeMap.put(edges[i], i);
		}


		for (Edge e : G.getEdges()) e.setValidity(true);
		int ncc = getConnectedComponentCount(G, nodes, nodeMap);

		// Si no hay mas aristas en el grafo paramos
		while (nCom > ncc && G.getValidEdgeCount() > 0) {
			runNGAlgorithmIt(G, nodes, edges, edgeMap, nodeMap);
			ncc = getConnectedComponentCount(G, nodes, nodeMap);
		}
		//GraphIO.saveDOTformat((OGraph)G, "graph_out.dot");
		return putToCollection(G, nodes, nodeMap);
	}
};
