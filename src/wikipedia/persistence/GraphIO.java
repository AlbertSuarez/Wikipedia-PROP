package wikipedia.persistence;
import wikipedia.domain.*;
import g13.*;
import java.util.Scanner;
import static wikipedia.utils.Print.*;
import java.util.*;

public class GraphIO {
	
	public GraphIO() {
		
	}
	
	public static OGraph readGraph() {
		
		OGraph g = new OGraph();
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		int m = cin.nextInt();
		
		while ((m--) > 0) {
			
			ONode ni = new ONode(new Page(cin.next()));
			ONode nj = new ONode(new Page(cin.next()));
			
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
			
			OEdge e = new OEdge(ni, nj, 1, OEdge.edgeType.CsubC);
			
			if (!g.hasNode(ni)) g.addNode(ni);
			if (!g.hasNode(nj)) g.addNode(nj);
			g.addEdge(e);
		}

		return g;
	}
	
	public static void writeGraph(OGraph g) {
		
		Set<Edge> edgeSet = g.getEdgeSet();
		for (Edge e: edgeSet) {
			
			Node n1 = e.getNode();
			Node n2 = e.getNeighbor(n1);
			
			print(n1 + " " + n2);
		}

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
		

}
