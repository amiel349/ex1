package ex1.src;

import java.io.*;
import java.util.*;


public class WGraph_Algo implements weighted_graph_algorithms,java.io.Serializable {
    private weighted_graph graph;

    /**
     * simple constructor
     */
    public WGraph_Algo(){
        this.graph=new WGraph_DS();
    }

    /**
     * method that init a weighted graph and aloww you to use the methods of this class in the grapg were init
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        this.graph=g;
    }

    /**
     * method that return the graph
     * @return
     */
    @Override
    public weighted_graph getGraph() {
        return this.graph;
    }

    /**
     * method that make a deep copy of given graph
     * at first copy all the nodes
     * and then connect the edges between the nodes which were added
     * @return graph
     */
    @Override
    public weighted_graph copy() {
        if(this.graph==null)
            return null;
        weighted_graph g=new WGraph_DS();

        Collection<node_info> temp=this.graph.getV();
        for (node_info node : temp) {
            g.addNode(node.getKey());
            g.getNode(node.getKey()).setTag(this.graph.getNode(node.getKey()).getTag());
            g.getNode(node.getKey()).setInfo(this.graph.getNode(node.getKey()).getInfo());
        }
        for (node_info node2 : temp) {
            Collection<node_info>neigh=this.graph.getV(node2.getKey());
            node_info y=g.getNode(node2.getKey());
            for (node_info node3 : neigh) {
                g.connect(y.getKey(),g.getNode(node3.getKey()).getKey(),this.graph.getEdge(y.getKey(),node3.getKey()) );
            }
        }
        return g;
    }

    /**
     * method that usa a BFS algorithm to check if the graph is connected
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        if(this.graph==null)
            return true;
        if(this.graph.nodeSize()==0)
            return true;
        Queue<node_info> q=new LinkedList<node_info>();
        Collection<node_info>t= this.graph.getV();
        int size=t.size();
        for (node_info x : t) {
            size--;
            this.graph.getNode(x.getKey()).setTag(0);
            if(size==0)
                q.add(x);
        }

        q.peek().setTag(1);
        while(!q.isEmpty()) {
            t=this.graph.getV(q.peek().getKey());
            q.remove();
            for (node_info node1 : t) {
                if(node1.getTag()==0)
                {node1.setTag(1);
                    q.add(node1);
                }

            }
        }
        Collection<node_info>y= this.graph.getV();
        for (node_info node2 : y) {
            if(node2.getTag()==0)
                return false;
        }
        return true;
    }

    /**
     * method that use Dijikstra algorithm to return the shortest distance between 2 nodes
     * the return distance is the  minor sum of  edges
     * between nodes
     * return -1 if the nodes are not connected
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if(this.graph==null)
            return -1;
        if(this.graph.getNode(src)==null||this.graph.getNode(dest)==null)
            return -1;
        if(src==dest)
            return 0;
        HashMap<node_info, node_info>PrevNode=new HashMap<node_info, node_info>();
        PriorityQueue<node_info> q =new PriorityQueue(this.graph.nodeSize(), new Comparator<node_info>() {
            @Override
            public int compare(node_info o1, node_info o2) {
                if(o1.getTag() > o2.getTag()) {
                    return 1;
                } else if (o1.getTag() < o2.getTag()) {
                    return -1;
                } else {
                    return 0;
                }
            }

        });
        Collection<node_info> list= graph.getV();
        for (node_info node_info : list) {
            node_info.setTag(Integer.MAX_VALUE);
            node_info.setInfo("f");
        }graph.getNode(src).setTag(0);
        q.add(graph.getNode(src));
        while (!q.isEmpty()){
            node_info x= graph.getNode(q.remove().getKey());
            x.setInfo("t");
            Collection<node_info> neighbors= graph.getV(x.getKey());
            for (node_info neighbor : neighbors) {
                if(neighbor.getInfo().equals("f"))
                {     double weightsum=x.getTag()+ graph.getEdge(x.getKey(),neighbor.getKey());
                    if(weightsum<neighbor.getTag()){
                        neighbor.setTag(weightsum);
                        PrevNode.put(neighbor,x);
                        q.add(neighbor);
                    }

                }
            }
        }      if(graph.getNode(dest).getTag()==Integer.MAX_VALUE)
            return -1;
        return graph.getNode(dest).getTag();
    }

    /**
     * method that use the Dijikstra to return the list of the nodes which are the shortest path between 2 nodes
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        List<node_info> pathlist=new LinkedList<node_info>();
        if(this.graph==null)
            return null;
        if(this.graph.getNode(src)==null||this.graph.getNode(dest)==null)
            return null;
        if(src==dest){
            pathlist.add(graph.getNode(src));
            return pathlist;}
        if(shortestPathDist(src,dest)==-1)
            return null;
        HashMap<Integer,Integer>PrevNode=new HashMap<Integer,Integer>();
        PriorityQueue<node_info> q =new PriorityQueue(this.graph.nodeSize(), new Comparator<node_info>() {
            @Override
            public int compare(node_info o1, node_info o2) {
                if(o1.getTag() > o2.getTag()) {
                    return 1;
                } else if (o1.getTag() < o2.getTag()) {
                    return -1;
                } else {
                    return 0;
                }
            }

        });
        Collection<node_info> list= graph.getV();
        for (node_info node_info : list) {
            node_info.setTag(Integer.MAX_VALUE);
            node_info.setInfo("f");
        }graph.getNode(src).setTag(0);
        q.add(graph.getNode(src));
        while (!q.isEmpty()){
            node_info x= graph.getNode(q.remove().getKey());
            x.setInfo("t");
            Collection<node_info> neighbors= graph.getV(x.getKey());
            for (node_info neighbor : neighbors) {
                if(neighbor.getInfo().equals("f"))
                {
                    double weightsum=x.getTag()+ graph.getEdge(x.getKey(),neighbor.getKey());
                    if(weightsum<neighbor.getTag()){
                        neighbor.setTag(weightsum);
                        PrevNode.put(neighbor.getKey(),x.getKey());
                        q.add(neighbor);
                    }

                }
            }
        }pathlist.add(getGraph().getNode(dest));
        int index=PrevNode.get(dest);
        int y=PrevNode.size();
        for (int i=0;i<y;i++){
            pathlist.add(graph.getNode(index));
            if(index==src)
                break;
            index=PrevNode.get(index);
        }
        Collections.reverse(pathlist);
        return pathlist;
    }

    /**
     * method that use Serializable to save a graph in a new file
     * @param file - the file name (may include a relative path).
     * @return
     */
    @Override
    public boolean save(String file) {
        try {

            // Saving of object in a file
            FileOutputStream File = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream
                    (File);

            // Method for serialization of object
            out.writeObject(graph);

            out.close();
            File.close();
            return true;
        }

        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * method that make a graph from a file saved
     * @param file - file name
     * @return
     */
    @Override
    public boolean load(String file) {
        try {

            // Reading the object from a file
            FileInputStream File = new FileInputStream
                    (file);
            ObjectInputStream in = new ObjectInputStream(File);

            // Method for deserialization of object
            this.graph = (weighted_graph)in.readObject();

            in.close();
            File.close();
            return true;

            // System.out.println("z = " + object1.z);
        }

        catch (IOException ex) {
            return false;
        }

        catch (ClassNotFoundException ex) {
            return false;
        }
    }



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        WGraph_Algo that = (WGraph_Algo) o;
//        return Objects.equals(graph, that.graph);
//    }


}


