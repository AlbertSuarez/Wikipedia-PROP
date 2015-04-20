package wikipedia.persistence;
import wikipedia.domain.*;
import g13.*;
import java.util.Scanner;
import static wikipedia.utils.Print.*;
import static wikipedia.utils.read.*;
import static wikipedia.utils.write.*;
import java.util.*;

public class GraphIO {
	
	// Pre:  True
	// Post: A GraphIO empty is created.
	public GraphIO() {
		
	}
	
	// Pre:  True
	// Post: Read a Graph and return it.
	public static OGraph readGraph() {
		
		OGraph g = new OGraph();
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		int m = cin.nextInt();
		
		while ((m--) > 0) {
			
			ONode ni = new ONode(new Page(cin.next()));
			ONode nj = new ONode(new Page(cin.next()));
			//double peso = cin.nextInt();
			/*ONode n1 = null;
			ONode n2 = null;
			
			for (Node nn : g.getNodeSet()) {
				if (nn.equals(ni)) {
					n1 = (ONode) nn;
				}
				if (nn.equals(nj)) {
					n2 = (ONode) nn;
				}
			}
			if (n1 == null) n1 = ni;
			if (n2 == null) n2 = nj;*/
			
			OEdge e = new OEdge(ni, nj, 1, OEdge.EdgeType.CsupC);
			
			if (!g.hasNode(ni)) g.addNode(ni);
			if (!g.hasNode(nj)) g.addNode(nj);
			g.addEdge(e);
		}

		return g;
	}
	

	// Pre:  True
	// Post: Write a Graph.
	public static void writeGraph(OGraph g) {
		
		Collection<Edge> edgeSet = g.getEdges();;
		for (Edge e: edgeSet) {
			
			Node n1 = e.getNode();
			Node n2 = e.getNeighbor(n1);
			double peso = e.getWeight();

			print(n1 + " " + n2 + " " + peso);
		}

		/*Set<Node> nodeSet = g.getNodeSet();
		for (Node nn : nodeSet) print(nn);*/

		/*for (int i = 0; i < G.size(); ++i) {
			cout << "Nodo " << i << ": ";
			for (int j = 0; j < G[i].size(); ++j) {
				cout << "nodoDest " << G[i][j].first << " idArco " << G[i][j].second << " ";
			}
			cout << endl;
		}
		
		for (int i = 0; i < G.size(); ++i) {
			for (int j = 0; j < G[i].size(); ++j) {
				if (i < G[i][j].first) cout << i << " " << G[i][j].first << " -> " << arco[G[i][j].second] << endl;
			}
		}*/
	}

	// Pre:  True
	// Post: Read a Graph with WP format and return it.
	public static OGraph readGraphWPformat() {
		OGraph g = new OGraph();
		Scanner cin = new Scanner(System.in);
		Map<String, ONode> nodeMap = new LinkedHashMap<String, ONode>();
		while (cin.hasNext()) {
			String name1 = cin.next();    // Read name1
			String type1 = cin.next();    // Read type1 (cat or page)
			String linkType = cin.next(); // Read linkType (CsupC, Csubc, CP, PA)
			String name2 = cin.next();    // Read name2
			String type2 = cin.next();    // Read type2 (cat or page)

			ONode node1 = nodeMap.get(name1);
			if (node1 == null) {
				// New node
				if (type1.equals("cat")) {
					node1 = new ONode(new Category(name1));
				} else {
					node1 = new ONode(new Page(name1));
				}
				nodeMap.put(name1, node1);
			}

			ONode node2 = nodeMap.get(name2);
			if (node2 == null) {
				// New node
				if (type2.equals("cat")) {
					node2 = new ONode(new Category(name2));
				} else {
					node2 = new ONode(new Page(name2));
				}
				nodeMap.put(name2, node2);
			}

			// Create the new edge
			OEdge e = new OEdge(node1, node2, 1, OEdge.toEdgeType(linkType));

			if (!g.hasNode(node1)) g.addNode(node1);
			if (!g.hasNode(node2)) g.addNode(node2);
			g.addEdge(e);
		}

		return g;

	}

	// Pre:  True
	// Post: Write a Graph with WP format.
	public static void writeGraphWPformat(OGraph g) {
		Collection<Edge> edgeSet = g.getEdges();
		for (Edge e: edgeSet) {
			OEdge oe = (OEdge)e;
			ONode node1 = (ONode)e.getNode();
			ONode node2 = (ONode)e.getNeighbor(node1);

			String[] wiki = new String[5];
			wiki[0] = node1.getElement().getTitle();
			wiki[1] = Element.toElementTypeString(node1.getElement().getElementType());
			wiki[2] = OEdge.toEdgeTypeString(oe.getEdgeType());
			wiki[3] = node2.getElement().getTitle();
			wiki[4]	= Element.toElementTypeString(node2.getElement().getElementType());
			print(wiki[0] + " " + wiki[1] + " " + wiki[2] + " " + wiki[3] + " " + wiki[4]);
			String aux;
			aux = wiki[0];
			wiki[0] = wiki[3];
			wiki[3] = aux;
			aux = wiki[1];
			wiki[1] = wiki[4];
			wiki[4] = wiki[1];
			if (wiki[2] == "CP")wiki[2] = "PC";
			else wiki[2] = "CsubC";
			print(wiki[0] + " " + wiki[1] + " " + wiki[2] + " " + wiki[3] + " " + wiki[4]);
		}
	}
	
	// Pre:  True
	// Post: Read a Graph with WP format from an external file.
	public static OGraph loadWP() {
		OGraph g = new OGraph();
		Map<String, ONode> nodeMap = new LinkedHashMap<String, ONode>();
		ArrayList<String> wiki = new ArrayList<String>();
		readWP(wiki,"data.txt");
		Iterator<String> itwiki = wiki.iterator();
		while(itwiki.hasNext()){
			String[] parts = itwiki.next().split(" ");
			String name1 = parts[0];    // Read name1
			String type1 = parts[1];    // Read type1 (cat or page)
			String linkType = parts[2]; // Read linkType (CsupC, Csubc, CP, PA)
			String name2 = parts[3];    // Read name2
			String type2 = parts[4];    // Read type2 (cat or page)

			ONode node1 = nodeMap.get(name1);
			if (node1 == null) {
				// New node
				if (type1.equals("cat")) {
					node1 = new ONode(new Category(name1));
				} else {
					node1 = new ONode(new Page(name1));
				}
				nodeMap.put(name1, node1);
			}

			ONode node2 = nodeMap.get(name2);
			if (node2 == null) {
				// New node
				if (type2.equals("cat")) {
					node2 = new ONode(new Category(name2));
				} else {
					node2 = new ONode(new Page(name2));
				}
				nodeMap.put(name2, node2);
			}

			// Create the new edge
			OEdge e = new OEdge(node1, node2, 1, OEdge.toEdgeType(linkType));

			if (!g.hasNode(node1)) g.addNode(node1);
			if (!g.hasNode(node2)) g.addNode(node2);
			g.addEdge(e);
		}

		return g;
	}
	
	// Pre:  True
	// Post: Write Graph 'g' in an external file.
	public static void saveWP(OGraph g) {
		Collection<Edge> edgeSet = g.getEdges();
		for (Edge e: edgeSet) {
			OEdge oe = (OEdge)e;
			ONode node1 = (ONode)e.getNode();
			ONode node2 = (ONode)e.getNeighbor(node1);
			String[] wiki = new String[5];
			wiki[0] = node1.getElement().getTitle();
			wiki[1] = Element.toElementTypeString(node1.getElement().getElementType());
			wiki[2] = OEdge.toEdgeTypeString(oe.getEdgeType());
			wiki[3] = node2.getElement().getTitle();
			wiki[4]	= Element.toElementTypeString(node2.getElement().getElementType());
			writeWPline(wiki,"data.txt");
			String aux;
			aux = wiki[0];
			wiki[0] = wiki[3];
			wiki[3] = aux;
			aux = wiki[1];
			wiki[1] = wiki[4];
			wiki[4] = wiki[1];
			if (wiki[2] == "CP")wiki[2] = "PC";
			else wiki[2] = "CsubC";
			writeWPline(wiki,"data.txt");
		}
	}
	
}
