package ex1.src;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;


public class WGraph_DS implements weighted_graph,java.io.Serializable {
    private HashMap<Integer, HashMap<Integer, vertex>> neigh;
    private HashMap<Integer, node_info> vertices;
    private int MC;
    private int nodeSize;
    private int edgeSize;

    public WGraph_DS() {
        this.neigh = new HashMap<Integer, HashMap<Integer, vertex>>();
        this.MC = 0;
        this.nodeSize = 0;
        this.edgeSize = 0;
        vertices = new HashMap<Integer, node_info>();
    }

    /**
     * simple method to get a node from the node list
     * @param key - the node_id
     * @return
     */
    @Override
    public node_info getNode(int key) {
        if (vertices.containsKey(key))
            return vertices.get(key);
        else
            return null;
    }

    /**
     * simple method to check if there is an edge between 2 nodes
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (getNode(node1) == null || getNode(node2) == null)
            return false;
        if (neigh.get(node1).get(node2) == null)
            return false;

        return true;

    }

    /**
     * simple method that return the wegiht of an edge between 2 nodes if exists
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (!hasEdge(node1, node2))
            return -1;
        return neigh.get(node1).get(node2).getWeight();

    }

    /**
     * method to add a node
     * create the node info add him to the list and create him a neighboor list
     * @param key
     */
    @Override
    public void addNode(int key) {
        if(!(getNode(key)==null))
            return;
        node_info node = new NodeInfo(key);
        HashMap<Integer, vertex> edgelist = new HashMap<>();
        vertices.put(key, node);
        neigh.put(key, edgelist);
        nodeSize++;
        MC++;

    }

    /**
     * method to connect 2 nodes
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if(w<0)
            return;
        if (getNode(node1) == null || getNode(node2) == null)
            return;
        if(node1==node2)
            return;
        if(hasEdge(node1,node2)){
            if(neigh.get(node1).get(node2).weight==w)
                return;
            neigh.get(node1).get(node2).setWeight(w);
            neigh.get(node2).get(node1).setWeight(w);
            return;
        }
        vertex x1 = new vertex(w, vertices.get(node1));
        vertex y2 = new vertex(w, vertices.get(node2));
        neigh.get(node1).put(node2, y2);
        neigh.get(node2).put(node1, x1);
        edgeSize++;
        MC++;
    }

    /**
     * method to return a collection list of all the nodes in graph
     * @return
     */
    @Override
    public Collection<node_info> getV() {
        if (vertices == null)
            return null;
        Collection<node_info> temp = vertices.values();
        return temp;
    }

    /**
     * method that return a collection of all neighboors of given node
     * @param node_id
     * @return
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        if (getNode(node_id) == null)
            return null;
        Collection<node_info> list = new LinkedList<>();
        Collection<vertex> temp = neigh.get(node_id).values();
        for (WGraph_DS.vertex vertex : temp) {
            list.add(vertex.getNode());
        }
        return list;
    }

    /**
     * removing a node from the list and removin all the edges which he was connected to
     * @param key
     * @return
     */
    @Override
    public node_info removeNode(int key) {
        if (getNode(key) == null)
            return null;
        Collection<node_info> list = getV(key);
        for (node_info node_info : list) {
            neigh.get(node_info.getKey()).remove(key);
            edgeSize--;
            MC++;
        }
        neigh.remove(key);
        nodeSize--;
        MC++;
        return vertices.remove(key);
    }

    /**
     * removin a edge
     * remove from neighboors list of the 2 given nodes
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (!hasEdge(node1, node2))
            return;
        neigh.get(node1).remove(node2);
        neigh.get(node2).remove(node1);
        edgeSize--;
        MC++;
    }

    /**
     * return node size of the graph
     * @return
     */
    @Override
    public int nodeSize() {
        return nodeSize;
    }

    /**
     * return the the amount of edge in the graph
     * @return
     */
    @Override
    public int edgeSize() {
        return edgeSize;
    }

    /**
     * return the counter of all change the graph made
     * @return
     */
    @Override
    public int getMC() {
        return MC;
    }

    /**
     * inner class implements the Node_info
     */
    public class NodeInfo implements node_info,java.io.Serializable,Comparable<node_info> {
        private int Key;
        private String Info;
        private double Tag;

        NodeInfo(int key) {
            this.Key = key;
            this.Info = "";
            this.Tag = 0;
        }

        /**
         * return key of this node
         * @return
         */
        @Override
        public int getKey() {
            return this.Key;
        }

        /**
         * return info
         * @return
         */
        @Override
        public String getInfo() {
            return this.Info;
        }

        /**
         * set info
         * @param s
         */
        @Override
        public void setInfo(String s) {
            this.Info = s;
        }

        /**
         * get tag
         * @return
         */
        @Override
        public double getTag() {
            return this.Tag;
        }

        /**
         * settag
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            this.Tag = t;

        }

        /**
         * method which chek if 2 nodes are equal
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeInfo nodeInfo = (NodeInfo) o;
            return Key == nodeInfo.Key &&
                    Double.compare(nodeInfo.Tag, Tag) == 0 &&
                    Objects.equals(Info, nodeInfo.Info);
        }

        /**
         * method that compares 2 nodes by their tags
         * return 1 if the first is bigger
         * -1 if the second is bigger
         * 0 if are equals
         * @param o
         * @return
         */
        @Override
        public int compareTo(node_info o) {
            if(this.Tag>o.getTag())
                return 1;
            if(this.Tag<o.getTag())
                return-1;
            return 0;
        }
    }

    /**
     * inne rclass that implements vertex
     * a vertex is a node with weight
     * udes to make edges between nodes
     */
    private class vertex implements java.io.Serializable {
        node_info node;
        private double weight;

        vertex(double weight, node_info node) {
            this.weight = weight;
            this.node = node;
        }

        /**
         * return the node
         * @return
         */
        public node_info getNode() {
            return this.node;
        }

        /**
         * return the weight
         * @return
         */
        public double getWeight() {
            return weight;
        }

        /**
         * set weight
         * @param weight
         */
        public void setWeight(double weight) {
            if(weight<0)
                return;
            this.weight = weight;
        }

        /**
         * method that check if 2 nodes are equal
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            vertex vertex = (WGraph_DS.vertex) o;
            return Double.compare(vertex.weight, weight) == 0 &&
                    Objects.equals(node, vertex.node);
        }

    }

    /**
     * method that print the graph
     * @return
     */
    public String toString(){
        Collection<node_info>list=getV();

        for (node_info node_info : list) {
            System.out.print("{ node is: "+node_info.getKey()+" and neighbores are:[ ");
            Collection<node_info>temp=getV(node_info.getKey());
            int size= temp.size();
            for (node_info nodeInfo : temp) {
                if(size==1)
                    System.out.println(nodeInfo.getKey()+" ] }");
                else
                    System.out.print(nodeInfo.getKey()+" , ");
                size--;
            }
        }
        return "";
    }

    /**
     * method to check if 2 graphs are equal
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_DS wGraph_ds = (WGraph_DS) o;
        return nodeSize == wGraph_ds.nodeSize &&
                edgeSize == wGraph_ds.edgeSize &&
                Objects.equals(neigh, wGraph_ds.neigh) &&
                Objects.equals(vertices, wGraph_ds.vertices);
    }

}
