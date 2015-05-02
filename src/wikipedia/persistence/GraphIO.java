/**
 * @file GraphIO.java
 * @author G13.2
 * @date 2 May 2015
 * @brief Graph loading/saving helping routines
 */

package wikipedia.persistence;
import wikipedia.domain.*;
import g13.*;

import java.io.File;
import static wikipedia.utils.utils.*;
import java.util.*;

/**
 * @class GraphIO
 * @brief Graph Input/Output utility
 */
public final class GraphIO {

	private GraphIO() {
		// Utility classes should always be final and have a private constructor
	}

	/**
	 * @brief Reads an OGraph with the WP format from the standard input
	 * @returns the read OGraph
	 */
	public static OGraph readGraphWPformat() {
		OGraph g = new OGraph();
		Scanner cin = new Scanner(System.in);
		Map<String, ONode> nodeMap = new LinkedHashMap<String, ONode>();
		while (cin.hasNext()) {
			String name1 = cin.next();    // Read name1
			if(name1.equals("-1"))break;
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
		print("Node count: " + g.getOrder() + "\nEdge count: " + g.getEdgeCount());
		return g;

	}

	/**
	 * @brief Writes an OGraph with the WP format to the standard output
	 * @param g the OGraph to write
	 */
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

	/**
	 * @brief Loads an OGraph with the WP format from an external file
	 * @returns the loaded OGraph
	 */
	public static OGraph loadWP() {
		OGraph g = new OGraph();
		Map<String, ONode> nodeMap = new LinkedHashMap<String, ONode>();
		ArrayList<String> wiki = new ArrayList<String>();
		File f = Choose();
		readWP(wiki,f);
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

	/**
	 * @brief Saves an OGraph with the WP format to an external file
	 * @param g the OGraph to save
	 */
	public static void saveWP(OGraph g) {
		Collection<Edge> edgeSet = g.getEdges();
		File f = save();
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
			writeWPline(wiki,f);
			String aux;
			aux = wiki[0];
			wiki[0] = wiki[3];
			wiki[3] = aux;
			aux = wiki[1];
			wiki[1] = wiki[4];
			wiki[4] = wiki[1];
			if (wiki[2] == "CP")wiki[2] = "PC";
			else wiki[2] = "CsubC";
			writeWPline(wiki,f);
		}
	}

	/**
	 * @brief Writes an OGraph with the DOT format to the standard output
	 * @param g the OGraph to write
	 */
	public static void writeDOTformat(OGraph g) {

		Collection<Node> nodes = g.getNodes();
		Collection<Edge> edges = g.getEdges();

		print("graph {");

		// Print nodes
		for (Node n: nodes) {
			ONode on = (ONode)n;
			print(
				"\t" + on.getElement().getTitle() + "\t[name=\"node " + on.getElement().getTitle() + "\",\n" +
				"\t\tlabel=\"" + on.getElement().getTitle() + "\"" +
				((on.getElement().getElementType() == Element.ElementType.ELEMENT_PAGE) ? (",\n\t\tshape=box") : "") +
				"];\n"
			);
		}

		// Print edges
		for (Edge e: edges) {
			OEdge oe = (OEdge)e;
			if (oe.isValid()) {
				ONode node1 = (ONode)e.getNode();
				ONode node2 = (ONode)e.getNeighbor(node1);
				print(
					"\t" + node1.getElement().getTitle() + " -- " + node2.getElement().getTitle() +
					((oe.getEdgeType() == OEdge.EdgeType.CP) ? (" [style=dashed, color=blue]") : "") +
					";\n"
				);
			}
		}

		print("}");
	}

	/**
	 * @brief Saves an OGraph with the DOT format to an external file
	 * @param g the OGraph to save
	 * @param file the filename to save the OGraph to
	 */
	public static void saveDOTformat(OGraph g, String file) {

		Collection<Node> nodes = g.getNodes();
		Collection<Edge> edges = g.getEdges();

		write("graph {\n", file);

		// Print nodes
		for (Node n: nodes) {
			ONode on = (ONode)n;
			write(
				"\t" + on.getElement().getTitle() + "\t[name=\"node " + on.getElement().getTitle() + "\",\n" +
				"\t\tlabel=\"" + on.getElement().getTitle() + "\"" +
				((on.getElement().getElementType() == Element.ElementType.ELEMENT_PAGE) ? (",\n\t\tshape=box") : "") +
				"];\n",
				file
			);
		}

		// Print edges
		for (Edge e: edges) {
			OEdge oe = (OEdge)e;
			if (oe.isValid()) {
				ONode node1 = (ONode)e.getNode();
				ONode node2 = (ONode)e.getNeighbor(node1);
				write(
					"\t" + node1.getElement().getTitle() + " -- " + node2.getElement().getTitle() +
					((oe.getEdgeType() == OEdge.EdgeType.CP) ? (" [style=dashed, color=blue]") : "") +
					";\n",
					file
				);
			}
		}

		write("}", file);
	}
}
