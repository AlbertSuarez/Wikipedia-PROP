package wikipedia.domain;
import g13.*;
import java.util.*;
import static wikipedia.utils.Print.*;

public class NewmanGirvan implements Algorithm {

	final private int PESO = 1;

	private void stage1_BFS(Graph G, int[] d, int[] w, int s, Stack<Integer> pila) {

		int nodeCount = G.getOrder();

		boolean[] vist = new boolean[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			d[i] = Integer.MAX_VALUE;
			w[i] = 0;
			vist[i] = false;
		}
		
		d[s] = 0;
		w[s] = 1;

		Node[] nodes = G.getNodeSet().toArray(new Node[G.getOrder()]);
				
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		pila.push(s); // Guardar orden inverso
		
		while (!q.isEmpty()) {
			int u = q.poll();
			if (!vist[u]) {
				vist[u] = true;
				Set<Edge> adjEdgesSet = G.getAdjacencyList(nodes[u]);
				Edge[] adjEdges = adjEdgesSet.toArray(new Edge[adjEdgesSet.size()]);
				
				for (int i = 0; i < adjEdges.length; ++i) {

					Node adjNode = adjEdges[i].getNeighbor(nodes[u]);
					int v = java.util.Arrays.asList(nodes).indexOf(adjNode);

					if (d[v] > d[u] + PESO) {
						d[v] = d[u] + PESO;
						w[v] = w[u];
						q.add(v);
						pila.push(v); // Guardar orden inverso
					} else if (d[v] == d[u] + PESO) {
						w[v] += w[u];
					}
				}
			}
		}
	}

	private  void stage2_betweenness(Graph G, Stack<Integer> pila, int[] d, 
							double[] b, int[] w, double[] arco) {

		Node[] nodes = G.getNodeSet().toArray(new Node[G.getOrder()]);
		Set<Edge> edgeSet = G.getEdgeSet();
		Map<Edge, Integer> edgeMap = new LinkedHashMap<Edge, Integer>();
		Integer id = 0;
		
		for (Edge e: edgeSet) {
			edgeMap.put(e, id++);
		}

		while (!pila.isEmpty()) {
			int u = pila.pop();
			b[u] += 1;

			Set<Edge> adjEdgesSet = G.getAdjacencyList(nodes[u]);
			Edge[] adjEdges = adjEdgesSet.toArray(new Edge[adjEdgesSet.size()]);

			for (int i = 0; i < adjEdges.length; ++i) {
				Node adjNode = adjEdges[i].getNeighbor(nodes[u]);
				int v = java.util.Arrays.asList(nodes).indexOf(adjNode);
				int uvArco = edgeMap.get(adjEdges[i]);
				if (d[v] < d[u]) {
					arco[uvArco] += (double)w[v]/w[u]*b[u]; // fraccion de shortest paths
					                                        // que pasan por la arista
					b[v] += (double)w[v]/w[u]*b[u];         // fraccion de shortest paths
															// que pasan por el vertice
				}
			}
		}
	}

	public CommunityCollection runAlgorithm(Graph G) {

		int nodeCount = G.getOrder();

		// Build the edgeId -> Edge hashtable (*TODO*)
		double[] arco = new double[G.getEdgeCount()];
		// Reset vector<> arco
		for (int i = 0; i < arco.length; ++i) arco[i] = 0;
		
		for (int i = 0; i < nodeCount; ++i) {
			int[] d; //Distancia
			int[] w; //Number shortest path from source to i

			d = new int[nodeCount];
			w = new int[nodeCount];
			
			Stack<Integer> pila = new Stack<Integer>();
			stage1_BFS(G, d, w, i, pila);
			
			double[] b = new double[G.getOrder()]; //Number shortest path between source to any
			                                   //vertex in graph pass through vertex i
			
			stage2_betweenness(G, pila, d, b, w, arco);
		}

		/*for (int i = 0; i < arco.length; i++) {
			print(arco[i]);
		}*/
		
		return null;
	}


};
