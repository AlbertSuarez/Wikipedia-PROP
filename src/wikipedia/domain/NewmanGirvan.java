package wikipedia.domain;
import g13.*;
import java.util.*;
import static wikipedia.utils.Print.*;

public class NewmanGirvan extends Algorithm {

	private class CData implements Comparable<CData> {
		private final double dist;
		private final int idNode;

		public CData(double dist, int idNode) {
			this.dist = dist;
			this.idNode = idNode;
		}

		public double getDist() { return dist; }

		public int getIdNode() { return idNode; }

		@Override
		public int compareTo(CData other) {
			//ascending order
			return (int)(this.dist - other.dist);

			//descending order
			//return (int)(other.dist - this.dist);
		}

		/*@Override
		public boolean equals(Object o) {
			return o instanceof Data
				&& (this.dist == (Data)o.dist)
				&& (this.idNode == (Data)o.idNode);
		}*/
	}

	private void stage1_Dijkstra(Graph G, double[] d, double[] w, int s, Stack<Integer> pila) {

		int nodeCount = G.getOrder();

		//int p[] = new int[nodeCount];
		boolean[] vist = new boolean[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			d[i] = Integer.MAX_VALUE;
			w[i] = 0;
			vist[i] = false;
			//p[i] = -1;
		}

		d[s] = 0;
		w[s] = 1;

		Node[] nodes = G.getNodes().toArray(new Node[G.getOrder()]);

		PriorityQueue<CData> q = new PriorityQueue<CData>();
		q.add(new CData(d[s], s));
		pila.push(s); // Guardar orden inverso

		while (!q.isEmpty()) {
			CData dataU = q.poll();
			int u = dataU.getIdNode();
			if (!vist[u]) {
				vist[u] = true;
				Collection<Edge> adjEdgesSet = G.getAdjacencyList(nodes[u]);

				for (Edge e : adjEdgesSet) {

					Node adjNode = e.getNeighbor(nodes[u]);
					int v = java.util.Arrays.asList(nodes).indexOf(adjNode);
					if (d[v] > d[u] + e.getWeight()) {
						d[v] = d[u] + e.getWeight();
						w[v] = w[u];
						//p[v] = u;
						q.add(new CData(d[v], v));
						pila.push(v); // Guardar orden inverso
					} else if (d[v] == d[u] + e.getWeight()) {
						w[v] += w[u];
					}
				}
			}
		}
	}

	private void stage2_betweenness(Graph G, Stack<Integer> pila, double[] d,
							double[] b, double[] w, double[] arco) {

		Node[] nodes = G.getNodes().toArray(new Node[G.getOrder()]);
		Collection<Edge> edgeSet = G.getEdges();
		Map<Edge, Integer> edgeMap = new LinkedHashMap<Edge, Integer>();

		Integer id = 0;
		for (Edge e: edgeSet) {
			edgeMap.put(e, id++);
		}

		while (!pila.isEmpty()) {
			int u = pila.pop();
			b[u] += 1;

			Collection<Edge> adjEdgesSet = G.getAdjacencyList(nodes[u]);

			for (Edge e : adjEdgesSet) {
				Node adjNode = e.getNeighbor(nodes[u]);
				int v = java.util.Arrays.asList(nodes).indexOf(adjNode);
				int uvArco = edgeMap.get(e);
				if (d[v] < d[u]) {
					double calc = w[v]/w[u]*b[u];
					arco[uvArco] += calc; // fraccion de shortest paths
					                                        // que pasan por la arista
					b[v] += calc;		// fraccion de shortest paths
										// que pasan por el vertice
				}
			}
		}
	}

	public CommunityCollection runNGAlgorithm(Graph G) {

		int nodeCount = G.getOrder();

		// Build the edgeId -> Edge hashtable (*TODO*)
		double[] arco = new double[G.getEdgeCount()];
		// Reset vector<> arco
		for (int i = 0; i < arco.length; ++i) arco[i] = 0;

		for (int i = 0; i < nodeCount; ++i) {
			double[] d; //Distancia
			double[] w; //Number shortest path from source to i

			d = new double[nodeCount];
			w = new double[nodeCount];

			Stack<Integer> pila = new Stack<Integer>();
			stage1_Dijkstra(G, d, w, i, pila);

			double[] b = new double[G.getOrder()]; //Number shortest path between source to any
			                                   //vertex in graph pass through vertex i

			stage2_betweenness(G, pila, d, b, w, arco);
		}

		for (int i = 0; i < arco.length; i++) {
			print(arco[i]);
		}

		return null;
	}


};
