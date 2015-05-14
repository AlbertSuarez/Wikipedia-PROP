package wikipedia.domain;

import g13.*;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

import com.google.common.collect.Sets;

public class FourClique implements Algorithm {
		
    private final int K = 4;
 
    private class CliqueGraph extends Graph {}
    private class CliqueNode extends Node {
        private final Set<Node> S;
        private final boolean isKclique;
        private boolean isVisited;
 
        public CliqueNode(Set<Node> S, boolean isKclique) {
            this.S = S;
            this.isKclique = isKclique;
            this.isVisited = false;
        }
 
        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            // We don't check o's class for efficiency
            return this.S.equals(((CliqueNode) o).S);
        }
 
        @Override
        public int hashCode() {
            return S.hashCode();
        }
 
        @Override
        public String toString() {
            return null;
        }
 
        @Override
        public int compareTo(Node n) {
            return 0;
        }
 
        public Set<Node> getSet() {
            return S;
        }
 
        public boolean isKclique() {
            return isKclique;
        }
 
        public boolean isVisited() {return isVisited;}
 
        public void setVisited(boolean b){
            isVisited = b;
        }
        
    }
    private class CliqueEdge extends Edge {
        public CliqueEdge(CliqueNode m1, CliqueNode m2) {
            super(m1, m2);
        }
    }
 
    private final TreeSet<Edge> E;
    private final CliqueGraph CG;
 
    
    /**
     * Creates a CPM algorithm with an input graph
     * @param graph The input graph of the algorithm
     * @throws NullPointerException if graph is null
     */
    public FourClique() {
        super();
        E = new TreeSet<Edge>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.getWeight() == o2.getWeight()) {
                    Node n1 = o1.getNode();
                    Node n2 = o2.getNode();
                    if (n1.equals(n2)) {
                        return o1.getNeighbor(n1).compareTo(o2.getNeighbor(n2));
                    }
                    return n1.compareTo(n2);
                }
                if (o1.getWeight() < o2.getWeight()) return 1;
                return -1;
            }
        });
        CG = new CliqueGraph();
    }
 
    private CommunityCollection computeCommunities() {
        Queue<Node> que = new LinkedBlockingDeque<>();
        CommunityCollection cc = new CommunityCollection();
        for(Node n : CG.getNodes()){
            CliqueNode cn = (CliqueNode) n;
            if(cn.isKclique() && !(cn.isVisited())){
                que.add(n);
                cc.addCommunity(BFS(que));
            }
        }
        return cc;
    }
 
    private Community BFS(Queue<Node> q){
        Community c = new Community();
        while(!(q.isEmpty())){
            CliqueNode n = (CliqueNode) q.poll();
            if(n.isKclique && !(n.isVisited())){
            	for (Node nn : n.getSet()) c.addNode(nn);
                n.setVisited(true);
            }
            for(Node nn : CG.getNeighbours(n)){
                CliqueNode cn = (CliqueNode) nn;
                if( !(cn.isVisited())){
                    q.add(nn);
                    if(cn.isKclique()){
                    	for (Node nnn : n.getSet()) c.addNode(nnn);
                    }
                    cn.setVisited(true);
                }
            }
        }
        return c;
    }
 
    private void computeCliques(Graph G) {
        E.addAll(G.getEdges());
        for (Edge e : E) {
            e.setValidity(true);
            Node n1 = e.getNode();
            Node n2 = e.getNeighbor(n1);
            if (G.getValidNodeDegree(n1) >= K - 1 &&
                    G.getValidNodeDegree(n2) >= K - 1) {
                Set<Node> intersection = Sets.intersection(
                        G.getValidNeighbours(n1),
                        G.getValidNeighbours(n2));
                for(Node n: intersection){
                    Set<Node> int2 = Sets.intersection(intersection,
                            G.getValidNeighbours(n));
                    for(Node nn : int2){
                        Set<Node> ss = new LinkedHashSet<>();
                        ss.add(nn); ss.add(n1); ss.add(n2); ss.add(n);
                        CliqueNode cn1 = new CliqueNode(ss, true);
                        CG.addNode(cn1);
                        Set<Node> ss2 = new LinkedHashSet<>();
                        ss2.add(n); ss2.add(n1); ss2.add(n2);
                        CliqueNode cn2 = new CliqueNode(ss2, false);
                        CG.addNode(cn2);
                        Set<Node> ss3 = new LinkedHashSet<>();
                        ss3.add(nn); ss3.add(n1); ss3.add(n2);
                        CliqueNode cn3 = new CliqueNode(ss3, false);
                        CG.addNode(cn3);
                        Set<Node> ss4 = new LinkedHashSet<>();
                        ss4.add(n); ss4.add(nn); ss4.add(n2);
                        CliqueNode cn4 = new CliqueNode(ss4, false);
                        CG.addNode(cn4);
                        Set<Node> ss5 = new LinkedHashSet<>();
                        ss5.add(n); ss5.add(nn); ss5.add(n1);
                        CliqueNode cn5 = new CliqueNode(ss5, false);
                        CG.addNode(cn5);
                        CG.addEdge(new CliqueEdge(cn1, cn2));
                        CG.addEdge(new CliqueEdge(cn1, cn3));
                        CG.addEdge(new CliqueEdge(cn1, cn4));
                        CG.addEdge(new CliqueEdge(cn1, cn5));
                    }
                }
            }
        }
    }
    
	/**
	 * Applies the implemented Algorithm to a graph until
	 * there are nCom's left or the algorithm can't be applied
	 * anymore, and returns the CommunityCollection of them
	 * @param G The Graph to apply the Algorithm
	 * @param nCom The number of communities one wants to split the graph into
	 * @return The CommunityCollection result
	 */
    public CommunityCollection runAlgorithm(Graph G, int nCom)  {
        G.invalidateAllEdges();
        computeCliques(G);
        return computeCommunities();
    }
}
