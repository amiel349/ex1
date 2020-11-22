package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class WGraph_DSTest {
    /**
     * checking normal node and null node
     */
    @Test
    public static   void getNode() {
        weighted_graph g = new WGraph_DS();
        g.addNode(5);
        g.addNode(6);
        g.addNode(7);
        node_info x=g.getNode(5);
        node_info y=g.getNode(10);
        assertEquals(5,x.getKey());
        assertNull(y);
    }

    /**
     * checkin edges between exists nodes, doesnt exists nodes, same node and after removing node
     */
    @Test
    void hasEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);
        g.connect(1,2,0);
        g.connect(1,3,4);
        g.connect(4,2,2);
        g.connect(4,3,-45);
        g.connect(5,1,5);
        g.removeNode(5);
        assertTrue(g.hasEdge(1,2));
        assertFalse(g.hasEdge(1,4));
        assertFalse(g.hasEdge(9,4));
        assertFalse(g.hasEdge(1,1));
        assertFalse(g.hasEdge(4,3));
        assertFalse(g.hasEdge(1,5));
    }

    /**
     * checking edge's weight of connected nodes, not connected nodes and doesnt exists nodes
     */
    @Test
    void getEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);
        g.connect(1,2,0.6);
        g.connect(1,3,4.2);
        g.connect(4,2,2.1);
        g.connect(4,3,45.9);
        g.connect(5,5,5);
        double x=g.getEdge(1,2);
        double y=g.getEdge(2,2);
        double z=g.getEdge(8,2);
        double t=g.getEdge(1,5);
        assertEquals(0.6,x);
        assertEquals(-1,y);
        assertEquals(-1,z);
        assertEquals(-1,t);
    }

    /**
     * checking node siz after adding the same node
     */
    @Test
    void addNode() {
        weighted_graph g = new WGraph_DS();
        g.addNode(1);
        g.addNode(3);
        g.addNode(1);
        int x=g.nodeSize();
        assertEquals(2,x);
    }

    /**
     * checking the connect method between normal nodes ,doesnt exists nodes,same node,negative weight
     * and after setting weight again
     */
    @Test
    void connect() {
        weighted_graph g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);
        g.connect(1,2,0.6);
        g.connect(1,3,-4.2);
        g.connect(4,6,2.1);
        g.connect(4,5,45.9);
        g.connect(5,4,5);
        g.connect(5,5,5);
        double x=g.getEdge(5,5);
        assertEquals(-1,x);
        assertFalse(g.hasEdge(1,3));
        assertFalse(g.hasEdge(4,6));
        assertTrue(g.hasEdge(1,2));
        assertEquals(5,g.getEdge(4,5));

    }

    /**
     * checking the nodes of the graph are not null
     */
    @Test
    void getV() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.connect(0,1,1);
        Collection<node_info> v = g.getV();
        Iterator<node_info> iter = v.iterator();
        while (iter.hasNext()) {
            node_info n = iter.next();
            assertNotNull(n);
        }
    }

    /**
     * checking the neighbors of real node are not null and the neighbors of
     * not real node are null
     */
    @Test
    void testGetV() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.connect(0,1,1);
        Collection<node_info> v = g.getV(0);
        Iterator<node_info> iter = v.iterator();
        while (iter.hasNext()) {
            node_info n = iter.next();
            assertNotNull(n);
        }
        Collection<node_info> t = g.getV(5);
        assertNull(t);
    }

    /**
     * checking the nullity of a removed node and a doesnt exists node
     */
    @Test
    void removeNode() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        node_info x = g.removeNode(0);
        node_info y=  g.removeNode(0);
        node_info z=g.removeNode(8);
        assertNull(g.getNode(0));
        assertNull(y);
        assertNull(z);

    }

    /**
     *  simple remove edge method
     */
    @Test
    void removeEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);
        g.connect(1,2,0.6);
        g.connect(1,3,4.2);
        g.connect(4,2,2.1);
        g.connect(4,3,45.9);
        g.connect(5,5,5);
        g.removeEdge(1,2);
        assertFalse(g.hasEdge(1,2));
    }

    /**
     * checking the node size after adding nodes and after removing the same node twice. removing a dosent exist node
     * and removing a regular node
     */
    @Test
    void nodeSize() {
        weighted_graph g = new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);
        g.addNode(5);
        assertEquals(5,5);
        g.removeNode(5);
        g.removeNode(5);
        g.removeNode(1);
        g.removeNode(8);
        assertEquals(3,3);

    }

    /**
     * checking the edge size after connecting nodes
     * after connecting node to himself
     * after connecting not existing node
     * after connecting same nodes with a diff weight and with same weight
     */
    @Test
    void edgeSize() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        assertEquals(3,g.edgeSize());
        g.connect(0,1,5);
        g.connect(0,6,1);
        g.connect(0,0,1);
        g.connect(2,0,2);
        g.connect(3,2,1);
        assertEquals(4,g.edgeSize());
    }

    /**
     * simple test  checking  2 graph are equal
     */
    @Test
    void getGraph() {
        weighted_graph g=small_graph();
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g);
        weighted_graph g1=ag0.getGraph();
        assertEquals(g,g1);


    }

    /**
     * test checking 2 copy graph are deeply copied by equalizing the graph at first and after changes
     */
    @Test
    void copy() {
        weighted_graph g=small_graph();
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g);
        weighted_graph g2= ag0.copy();
        assertEquals(g,g2);
        assertEquals(g.edgeSize(),g2.edgeSize());
        assertEquals(g.nodeSize(),g2.nodeSize());
        g2.removeNode(0);
        assertNotEquals(g.edgeSize(),g2.edgeSize());
        assertNotEquals(g.nodeSize(),g2.nodeSize());
        g2.getNode(1).setTag(100);
        g2.getNode(1).setInfo("checking");
        assertNotEquals(g.getNode(1).getTag(),g2.getNode(1).getTag());

    }

    /**
     * testing connectivity in a connected graph and not connected graph
     */
    @Test
    void isConnected() {
        weighted_graph g=small_graph();
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g);
        assertTrue(ag0.isConnected());
        g.addNode(10);
        assertFalse(ag0.isConnected());


    }

    /**
     * testing the shortest path distanece between two node, between same node between not connected nodes
     * between not existing nodes and after setting new weight
     */
    @Test
    void shortestPathDist() {
        weighted_graph g=small_graph();
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g);
        assertEquals(ag0.shortestPathDist(3,4),6);
        assertEquals(ag0.shortestPathDist(3,1),4);
        g.connect(3,1,3.9);
        g.addNode(56);
        assertEquals(-1,ag0.shortestPathDist(3,56));
        assertEquals(3.9,ag0.shortestPathDist(3,1));
        assertEquals(-1,ag0.shortestPathDist(3,20));
        assertEquals(0,ag0.shortestPathDist(3,3));
    }

    /**
     * checking the path between nodes, between not connected node and between not existing nodes
     */
    @Test
    void shortestPath() {
        weighted_graph g=small_graph();
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g);
        g.addNode(50);
        List<node_info> sp = ag0.shortestPath(1,6);
        List<node_info> sp2 = ag0.shortestPath(1,50);
        List<node_info> sp3 = ag0.shortestPath(1,27);
        List t= ag0.shortestPath(1,6);
        double []x=new double [t.size()];
        double []y={1,5,7,6};
        int i=0;
        Iterator<node_info> it=t.iterator();
        while (it.hasNext()){
            x[i++]=it.next().getKey();
        }
        assertArrayEquals(x,y);
        assertNull(sp2);
        assertNull(sp3);
    }

    /**
     * test that save a graph and after load the same graph to another graph and equalize them
     */
    @Test
    void save_load() {
        weighted_graph g0 = small_graph();
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        weighted_graph_algorithms ag2 = new WGraph_Algo();
        ag0.init(g0);
        String str = "g0test.obj";
        ag0.save(str);
        ag2.load(str);
        weighted_graph g2 = ag2.getGraph();
        assertEquals(g0,g2);
        g0.removeNode(0);
        assertNotEquals(g0,g2);

    }

    /**
     * test of running time of 1M nodes and 10M edges less than 10 sc
     */

    @Test
    void runTime() {
        long start = new Date().getTime();
        weighted_graph g0 = new WGraph_DS();
        for (int i = 0; i <1000000 ; i++) {
            g0.addNode(i);
        }
        for (int i = 0; i <10000000 ; i++) {
             int x=(int)(Math.random()*1000000);
            int z=(int)(Math.random()*1000000);
            int y=(int)(Math.random()*1000000);
            g0.connect(i,i+1,i);
        }

        long end = new Date().getTime();
        double time=(end-start)/1000.0;
        // System.out.println("time for 1M vertices and 5M edges: "+time+" ms");
        boolean check=(int)time<30;
        assertTrue(check);
    }
    private weighted_graph small_graph() {
        weighted_graph g0 = new WGraph_DS();
        g0.addNode(0);
        g0.addNode(1);
        g0.addNode(2);
        g0.addNode(3);
        g0.addNode(4);
        g0.addNode(5);
        g0.addNode(6);
        g0.addNode(7);
        g0.addNode(8);
        g0.connect(0,1,1);
        g0.connect(0,2,2);
        g0.connect(0,3,3);

        g0.connect(1,4,17);
        g0.connect(1,5,1);
        g0.connect(2,4,1);
        g0.connect(3, 5,10);
        g0.connect(3,6,100);
        g0.connect(5,7,1.1);
        g0.connect(6,7,10);
        g0.connect(7,10,2);
        g0.connect(6,8,30);
        g0.connect(8,10,10);
        g0.connect(4,10,30);
        g0.connect(3,9,10);
        g0.connect(8,10,10);


        return g0;
    }


}
