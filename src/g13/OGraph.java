package g13;

import wikipedia.domain.Element;

import java.util.*;
import java.awt.Color;
import com.mxgraph.view.mxGraph;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxStylesheet;
import com.mxgraph.model.mxCell;

/**
 * Own Graph implementation
 * @author G13.2
 */
public class OGraph extends Graph
{
	/**
	 * Creates a empty OGraph.
	 */
	public OGraph()
	{
		super();
	}

	/**
	 * Validates all edges of implicit OGraph.
	 */
	public void validateAllEdges() {
		for (Edge e : getEdges()) e.setValidity(true);
	}

	public mxGraph toMxGraph(boolean cc) {
		mxGraph mxg = new mxGraph();
		Object parent = mxg.getDefaultParent();
		Map<String, Object> objMap = new LinkedHashMap<>();
		Collection<Node> nodes = this.getNodes();
		Collection<Edge> edges = this.getEdges();

		Hashtable<String, Object> catStyle = new Hashtable<String, Object>();
		catStyle.put(mxConstants.STYLE_FILLCOLOR, mxUtils.getHexColorString(Color.WHITE));
		catStyle.put(mxConstants.STYLE_STROKEWIDTH, 1.5);
		catStyle.put(mxConstants.STYLE_STROKECOLOR, mxUtils.getHexColorString(Color.RED));
		catStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		catStyle.put(mxConstants.STYLE_PERIMETER, mxConstants.PERIMETER_ELLIPSE);

		Hashtable<String, Object> pageStyle = new Hashtable<String, Object>();
		pageStyle.put(mxConstants.STYLE_FILLCOLOR, mxUtils.getHexColorString(Color.WHITE));
		pageStyle.put(mxConstants.STYLE_STROKEWIDTH, 1.5);
		pageStyle.put(mxConstants.STYLE_STROKECOLOR, mxUtils.getHexColorString(Color.BLUE));
		pageStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
		pageStyle.put(mxConstants.STYLE_PERIMETER, mxConstants.PERIMETER_ELLIPSE);


		mxStylesheet stylesheet = mxg.getStylesheet();
		stylesheet.putCellStyle("CatStyle", catStyle);
		stylesheet.putCellStyle("PageStyle", pageStyle);

		mxg.getModel().beginUpdate();
		for (Node n: nodes) {
			ONode on = (ONode)n;
			String title = on.getElement().getTitle();

			Element.ElementType et = on.getElement().getElementType();
			Object obj;

			if (et == Element.ElementType.ELEMENT_PAGE && !cc) {
				obj = mxg.insertVertex(parent, "Node:" + title, title, 0, 0, 50, 50, "PageStyle");
			} else {
				obj = mxg.insertVertex(parent, "Node:" + title, title, 0, 0, 50, 50, "CatStyle");
			}

			if (!objMap.containsKey(title)) {
				objMap.put(title, obj);
			}
		}

		for (Edge e: edges) {
			OEdge oe = (OEdge)e;
			if ((cc && oe.isValid()) || !cc) {
				ONode node1 = (ONode)oe.getOrigNode();
				ONode node2 = (ONode)oe.getDestNode();
				mxCell cell = (mxCell)mxg.insertEdge(parent, "Edge:" + oe.toString(), null,
					objMap.get(node1.getElement().getTitle()),
					objMap.get(node2.getElement().getTitle()));

			}
		}


		mxIGraphLayout layout = new mxHierarchicalLayout(mxg);
		layout.execute(parent);
		mxg.getModel().endUpdate();
		return mxg;
	}
}
