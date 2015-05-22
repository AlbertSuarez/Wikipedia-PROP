package wikipedia.domain;

import java.util.*;

import g13.*;

public class Louvain implements Algorithm {

	Graph original;
    Map<Node,Set<Node>> com2sons;
    CommunityCollection bestPartition;
    Estat bestEstat;

    private class LGraph extends Graph {}
    private static class CNode extends Node {
        static private Integer nextID = 0;
        private Integer id;

        public Integer getId()
        {
            return id;
        }

        public CNode()
        {
            id = CNode.nextID++;
        }

        @Override
        public boolean equals(Object o)
        {
            if(! o.getClass().equals(this.getClass())) return false;
            return ((CNode)o).id.equals(this.id);
        }

        @Override
        public int hashCode()
        {
            return id.hashCode();
        }

        @Override
        public String toString()
        {
            return "c"+id.toString();
        }

        @Override
        public int compareTo(Node n)
        {
            return ((CNode)n).getId()-this.id;
        }
    }
    private class LEdge extends Edge {
        //It doesn't need to do anything special
        public LEdge(Node m1, Node m2, double w, boolean valid) {
            super(m1, m2, w, valid);
        }
        public LEdge(Node m1, Node m2, double w) {
            super(m1, m2, w);
        }
    }
    
    public Louvain()
    {
    	super();
    	original = new LGraph();
        com2sons = new LinkedHashMap<>();
        bestPartition = null;
    }

    /**
     * Calculates the modularity of a graph's partition
     * @param g The graph whose modularity you want to calculate
     * @param part A partition of graph g, it maps each node to an Integer which identifies its communtiy
     * @return The modulartiy of the partition part of the graph g
     */
    static public Double calculateModularity(Graph g,Map<Node,Integer> part)
    {
        if(g.getEdgeCount()==0) throw new IllegalArgumentException("Cannot calculate modularity of a graph with no edges");

        Map<Node,Double> ndegree = new LinkedHashMap<>();
        Map<Integer,Double> inc = new LinkedHashMap<>();
        Map<Integer,Double> dec = new LinkedHashMap<>();
        Double m;

        m = 0.;

        for(Integer i:part.values()){
            inc.put(i,0.);
            dec.put(i,0.);
        }

        //Initialize degrees and weight
        for(Edge e:g.getEdges()){
            m+=e.getWeight();
            Node n1,n2;
            n2 = e.getNode();
            n1 = e.getNeighbor(e.getNode());

            if(!ndegree.containsKey(n1)) ndegree.put(n1,0.);
            if(!ndegree.containsKey(n2)) ndegree.put(n2,0.);

            ndegree.put(n1,ndegree.get(n1)+e.getWeight());
            ndegree.put(n2,ndegree.get(n2)+e.getWeight());

            Integer com1,com2;
            com1 = part.get(n1);
            com2 = part.get(n2);
            if(com1.equals(com2)){
                inc.put(com1,inc.get(com1)+e.getWeight());
            }
        }

        for(Node n:g.getNodes()){
            Integer com = part.get(n);
            dec.put(com,dec.get(com)+ndegree.get(n));
        }

        double mod = 0.;
        LinkedHashSet<Integer> s = new LinkedHashSet<>();
        s.addAll(part.values());
        for(Integer i:s){
            mod+= (inc.get(i)/m)-(java.lang.Math.pow(dec.get(i)/(2*m),2));
        }
        return mod;
    }//

    /**
     * Calculates the optimal partition of the graph given in the constructor using Louvain's modularity optimization algorithm
     * @return An arrayList containing a set of nodes for each community, each set contains the nodes that conform the community
     */
    public CommunityCollection calculate()
    {
        if(bestPartition != null) return bestPartition;
        Graph current = original;
        Estat currentState = null;
        boolean gain = true;
        while(gain) {//While there is gain
            gain = false;
            currentState = new Estat(current);//Initialization
            boolean localgain = true;
            //Start of first phase, get best partition
            while(localgain) {//while there is local gain
                localgain = false;
                for(Node n:current.getNodes()){
                    double bestInc = 0.;
                    int bestCom = currentState.getCommunity(n);
                    Set<Node> neigh_nodes = currentState.getNeighborsDiffCommuntity(n);
                    currentState.removeFromCommunity(n);
                    for (Node neigh : neigh_nodes) {
                        Integer neightCom = currentState.getCommunity(neigh);
                        double inc = currentState.getIncChange(n,neightCom);
                        if(inc>bestInc){//New partition
                            gain = true;
                            bestCom = neightCom;
                            bestInc = inc;
                        }
                    }
                    currentState.changeCommunity(n,bestCom);
                }

                /* //CODI PRE OPTIMITZACIO
                double bMod = currentState.modularity();
                for (Node n : current.getNodes()) {
                    Integer com = currentState.getCommunity(n);
                    for (Node neigh : currentState.getNeighborsDiffCommuntity(n)) {
                        currentState.changeCommunity(n, currentState.getCommunity(neigh));
                        double mod = currentState.modularity();
                        if (mod > bMod) {//New best partition
                            localgain = true;
                            gain = true;
                            bMod = mod;
                            com = currentState.getCommunity(neigh);
                        } else {
                            currentState.changeCommunity(n, com);
                        }
                    }
                }
                */
            }
            //Start of second phase, create new graph
            //if(gain){//If there was a gain
                current = calculateNewGraph(current,currentState);
            //}
        }
        bestPartition = new CommunityCollection();
        bestEstat = currentState;
        for(Node n:current.getNodes()){
            bestPartition.addCommunity(getComNodes(n));
        }

        return bestPartition;
    }

    private Community getComNodes(Node n)
    {
        Community retorn = new Community();
        for(Node s:com2sons.get(n)){
            if(!com2sons.containsKey(s)){
                retorn.addNode(s);
            }else {
            	for (Node ss : getComNodes(s).getNodes()) retorn.addNode(ss);
            }
        }
        return retorn;
    }

    /**
     * Calculates the modularity of the best partition and returns itCalculates the modularity of the best partition and resturns it
     * @return The modularity of the best partition of the graph
     */
    public Double resultModularity()
    {
        if(bestPartition == null) this.calculate();//If the best partition is not calculated calculate it
        return this.bestEstat.modularity();

    }

    private Graph calculateNewGraph(Graph g,Estat state)
    {
        Graph nou = new LGraph();
        Map<Integer,CNode> lnk = new LinkedHashMap<>();

        //Create link between integers and communities
        for(Integer i:state.getCommunities()){
            lnk.put(i, new CNode());
        }
        //Add all the new nodes to the graph
        for(Node n:lnk.values()){
            nou.addNode(n);
        }
        //Make com2sons calculations
        for(Node n:g.getNodes()){
            Node father = lnk.get(state.getCommunity(n));
            if(!com2sons.containsKey(father)) com2sons.put(father,new LinkedHashSet<Node>());
            com2sons.get(father).add(n);
        }
        //End of com2sons calculations

        for(Edge e:g.getEdges()){
            CNode cn1,cn2;
            //We get the Communtiy node that corresponds to each of the nodes
            cn1 = lnk.get(state.getCommunity(e.getNeighbor(e.getNode())));
            cn2 = lnk.get(state.getCommunity(e.getNode()));

            Double pes = e.getWeight();
            if(!nou.hasEdge(cn1, cn2)){
                nou.addEdge(new LEdge(cn1, cn2, pes));
            }else{
                Edge ee = nou.getEdge(cn1,cn2);
                ee.setWeight(ee.getWeight() + e.getWeight());
            }
        }
        return nou;
    }

    
    /**
	 * Applies the implemented Algorithm to a graph until
	 * there are nCom's left or the algorithm can't be applied
	 * anymore, and returns the CommunityCollection of them
	 * @param G The Graph to apply the Algorithm
	 * @param nCom The number of communities one wants to split the graph into
	 * @return The CommunityCollection result
	 */
	public CommunityCollection runAlgorithm(Graph G, int nCom) {
		original = G;
        CommunityCollection cc = calculate();
        G.invalidateAllEdges();
        for(Edge e: G.getEdges()){
           if(cc.getCommunityOfNode(e.getNode()) == cc.getCommunityOfNode(e.getNeighbor(e.getNode()))){
	           e.setValidity(true);
           }
        }        
        
		return cc;
	}
    
    
    /**
     * Used to keep track of the data of each communtiy (the sum of the weights of edges inside and
     * the sum of the degrees of the nodes inside), some data of the graph (the degree of each node and the total
     * weight) to be able to calculate modularities in an efficient way;
     */
    private class Estat
    {
        //Creates the status and initializes it with the data of the graph g
        //And the partition where each node is its own communtiy
        Map<Node,Integer> partition;
        Graph g;
        Map<Integer,Double> inc;//Sum of the weights of the edges inside a community
        Map<Integer,Double> dec;//Sum of the degrees of the nodes inside a community
        Map<Node,Double> ndegree;//Maps each node to its degree (loop increase the degree by 2 times their value)
        Double m;//Graph total weight
        int npart;



        public Estat(Graph ag)
        {
            g = ag;
            m = 0.;
            partition = new LinkedHashMap<>();
            dec = new LinkedHashMap<>();
            inc = new LinkedHashMap<>();
            ndegree = new LinkedHashMap<>();

            //Initialize degrees and weight
            for(Edge e:g.getEdges()){
                m+=e.getWeight();
                Node n1,n2;
                n2 = e.getNode();
                n1 = e.getNeighbor(e.getNode());

                if(!ndegree.containsKey(n1)) ndegree.put(n1,0.);
                if(!ndegree.containsKey(n2)) ndegree.put(n2,0.);

                ndegree.put(n1,ndegree.get(n1)+e.getWeight());
                ndegree.put(n2,ndegree.get(n2)+e.getWeight());
            }
            for(Node n:g.getNodes()){
                if(!ndegree.containsKey(n)) ndegree.put(n,0.);
            }
            npart = 0;
            //Initialize Node degrees
            for(Node n:g.getNodes()){
                partition.put(n,npart);//Create partitions
                dec.put(npart,ndegree.get(n));//Initialize partitions
                //If the node has a loop
                if(g.hasEdge(n,n)){
                    inc.put(npart,g.getEdge(n,n).getWeight());
                }else{
                    inc.put(npart,0.);
                }
                npart++;
            }

        }

        public Set<Integer> getCommunities()
        {
            Set<Integer> s = new LinkedHashSet<>();
            s.addAll(partition.values());
            return s;
        }

        public String toString()
        {
            return "m:"+m
            +"\nPartition:"+partition
            +"\nNDegree:"+ndegree
            +"\nInc:"+inc
            +"\nDec:"+dec
            +"\nModularity:" +this.modularity();
        }

        public Integer getCommunity(Node n)
        {
            return partition.get(n);
        }

        public void changeCommunity(Node n,Integer newcom)
        {//n can not be inside i communtiy
            Integer com = partition.get(n);
            for(Edge e:g.getAdjacencyList(n)){//For all the edges of n
                Node m = e.getNeighbor(n);
                Integer neighbcom = partition.get(m);
                //Removal operations
                if(com.equals(neighbcom)){
                    inc.put(com, inc.get(com) - e.getWeight());
                }
                //End of removal operations
                //Addition operations
                if(newcom.equals(neighbcom) || n.equals(m)){
                    inc.put(newcom,inc.get(newcom)+e.getWeight());
                }
            }
            //degree removal
            dec.put(com, dec.get(com) - ndegree.get(n));
            //degree addition
            dec.put(newcom,dec.get(newcom)+ndegree.get(n));
            //Change communtiy
            partition.put(n,newcom);
        }

        public Set<Node> getNeighborsDiffCommuntity(Node n)
        {
            Set<Node> ret = new LinkedHashSet<>();
            for(Edge e:g.getAdjacencyList(n)){
                Node m = e.getNeighbor(n);
                if(!partition.get(n).equals(partition.get(m))) ret.add(m);
            }
            return ret;
        }

        public void removeFromCommunity(Node n)
        {
            int com = npart++;//New community for the node
            inc.put(com,0.);
            dec.put(com,0.);
            changeCommunity(n,com);
        }

        public Double getIncChange(Node n,Integer com)
        {
            double dnc = getWeightEdgesToCommunity(n,com);
            return dnc - ndegree.get(n)*dec.get(com)/(2*m);
        }

        public Double getWeightEdgesToCommunity(Node n,Integer com)
        {
            Double retorn = 0.;
            for(Edge e:g.getAdjacencyList(n)){
                if(partition.get(e.getNeighbor(n))==com){
                    retorn+=e.getWeight();
                }
            }
            return retorn;
        }

        public Double modularity()
        {
            double mod = 0.;
            LinkedHashSet<Integer> s = new LinkedHashSet<>();
            s.addAll(partition.values());
            for(Integer i:s){
                mod+= (inc.get(i)/m)-(java.lang.Math.pow(dec.get(i)/(2*m),2));
            }
            return mod;
        }
    }
    
    
	
}