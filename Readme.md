#                                                                     Weighted graph

In this assigment i implemnted an weighted graph

In this repo you can find 2 folders- src- which contains the codes of the weighted graph and the code for the functions he have.
and the second folder is- tests- which contain a tests for the whole assigment and check evrythin works.

The src folder contains the following files:

node_info.java
weighted_graph.java
weighted_graph_algorithms.java
WGraph_Algo.java
WGraph_DS.java

The WGraph_DS.java class implements weighted_graph.java and also have the node_info.java as a inner class and the vertex as a inner class
node_info is the class for the nodes and weighted_graph is the class for the graph
Vertex is a class that used for the edges. this class contains  a node_info for destinetion and a double "weight". using the vertex you can keep the edges in the neighboor list  
The WGraph_Algo.java implements weighted_graph_algorithms.java
this class if for all the function that you can do in the weighted graph

### structure:
In this assigmrnt i used for a structure 2 Hashmaps. the first hashmap is called "vertices" and is an hashmap with integer key and node_info value
and he is used to contain all the nodes in the graph.
the second hashmap is called "neigh" and is  an hashmap of integer key and a value of another hashmap. the inner  hashmap have a integer key and a vetex as value
the reasons i used hasmaps are becuase it allowד to get every node in o(1) it allows you to contain many nodes without setting a size at first and beacuase it 
also have the function value which let you convert the hasmap in a list with just a pointer in o(1);

### Algorithms:
In the WGraph_Algo you have these algorithms:
copy
init 
get graph
isconnected
shortestpathdist
shortestpath
save 
load
 
 the first 3 functions are simple functions for make a deep copy to a graph to initialize a weighted grapg for app;y on it the algo functions and
 the getgraph to get the graph.
 
  In the "isconnected" function you check if the graph is connected. for this function i used the BFS algorithm.
  in this algorithm you use a queue and put inside one node then you run with a while loop until the queue is empty
  evrey loop itaration you take the first elemet in the queue mark him as visited remove him from the queue
  and add all is not visited neighboors to the queue in that way you run over all the nodes in the graph that are connected
  when the queue get empty you finish the wihle loop and you check all the nodes in the graph to see if they were visited if all nodes are visited the 
  graph is connected otherwise he is not
  
  The "shortestpathdist" return the shortest distnace between 2 selected nodes and return -1 if they are not connected
  the "shortestpath" return the list of nodes which are the shortes path between 2 selected nodes
  in the "shortestpathdist" and "shortestpath" i used the same algorithm called "Dijikstra".
  in this algorithm you use a priority queue with a comperator wich compare the weight of the edges and mantain the node with the shortest ptha in the 
  top of the queue
  so at first you add the source node to the queue and then ron over a while loop antil the queue is empty.
  inside the loop you mark the top element as visited you remove him from queue and add all his not visited neighbors to the queue. Every node that
  get into the queue have the sum of the edges from source node to him in his Tag value. Thanks to the comparator the node with the minor sum will be in the top of 
  the queue, that permit you to check if there is a shortest path wich passes trough a node which is allready inside the queue and still didnt get marked as 
  not visited, so after the queue get empty you check the destination node if his Tag was changes it mean this is the shortes path if he didnt get changed
  it is mean the src and dst node are not connected
  in the "shortestpath" I added an hashmap that when you add a neighbor you also put in the hashmap the node that was before the neighboore which were added
  that way you can check in the hashmap wich node was before the destination node and which was before this node and you can go like this until 
  you get the src node so that way you actually get the list of nodes which are the shortest path!
  
  In the "save" and "load" functions i used the "serialiazble" to save the given graph in a file and load it after to a new graph.
  
  In tha algorithm of Dijikstra i used help from a youtube video:
  https://www.youtube.com/watch?v=FSm1zybd0Tk
  
  In the save and load code I used help from this web site:
  https://www.geeksforgeeks.org/serialization-in-java/
  
  ### tests
  In this folder you can find a test which i made with "Junit" the test is for all the methodes I implement in this assigment from the simple ones like adding
  a node or removing an edge  to the algo's methods which are more complicated like check if the shortest path given is correct and if the graph is connected
  and there is a test who checked the run time of buliding a graph wit 1 million nodes and 10 million edges in a reasonable time.
  
  
 
  
  
