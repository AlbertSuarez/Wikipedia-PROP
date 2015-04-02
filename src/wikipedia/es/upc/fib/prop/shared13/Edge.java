package es.upc.fib.prop.shared13;

/**
 * Created by miquel on 20/03/15.
 */
public abstract class Edge
{
    private Node n1;
    private Node n2;
    private int weight;


	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

    /**
     * @pre True
     * @post An Edge between the nodes n1 and n2 with weight w is created
     * @param m1
     * @param m2
     * @param w
     */
    public Edge(Node m1,Node m2,int w)
    {
        n1 = m1;
        n2 = m2;
        weight = w;
    }

	/**
     * @pre The Node n is part of the Edge
     * @post The other Node from the Edge is returned
     * @param n
     * @return
	 * Given one of the two nodes of the Edge, it returns the other one.
	 It is implemented like this due to the fact that it is not a directed edge
	 therefore there no "origin" or "destiny" node.
	 */
    public Node getNeighbor(Node n)
    {
        if(n==n1){
            return n2;
        }else if(n==n2){
            return n1;
        }else{
            System.out.println("Not part of the edge");
            return null;
        }
    }

	/**
	 * @return RANDOM node of the edge
	 */
    public Node getNode()
    {
        return n1;
    }


}
