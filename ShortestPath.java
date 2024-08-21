import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ShortestPath {

	private int numOfHops;
	private HashMap<String, Double> distance; //best estimates to each vertex
	private HashMap<String, Edge> previous; //keeps track of the Edge that got us to that vertex

	//For program these, these two data structures will be HashMaps
	
	public void computeShortestPath(Graph graph, String start) {
		//Dijkstra's Algorithm
		distance = new HashMap<String, Double>();
		previous = new HashMap<String, Edge>();
		for(String vertice: graph.getVertices()) {
			distance.put(vertice, Double.POSITIVE_INFINITY); //best estimates are all infinity at first
		}
		distance.put(start,0.0); //the distance to our starting point is always 0
		
		//previous = new Edge[graph.numOfvertices()];
		PriorityQueue<PriorityVertex> visitQueue = new PriorityQueue<PriorityVertex>();
		visitQueue.add( new PriorityVertex(start, 0) );
		while(!visitQueue.isEmpty()) {
			
			PriorityVertex vertex = visitQueue.poll();
			//Check all the paths we can travel on with our new vertex
			for(Edge edge: graph.getAdjacencyEdges(vertex.getVertex())) {
				
				String neighbor = edge.getTo();
				// if (neighbor.equals(start)){
				// 	neighbor = edge.getFrom();
				// }
				if(distance.get(vertex.getVertex()) + edge.getWeight() < distance.get(neighbor)) {
					
					//we found a new best estimate, so update our data structures

					distance.put(neighbor, distance.get(vertex.getVertex()) + edge.getWeight());
					previous.put(neighbor, edge);
					//distance[neighbor] = distance[vertex.getVertex()] + edge.getWeight();
					//previous[neighbor] = edge;
					
					//We need to remove the old vertex from the PQ and insert the new one
					visitQueue.remove( new PriorityVertex(neighbor, 0)  ); // 0 can be any number
					visitQueue.add( new PriorityVertex(neighbor, distance.get(neighbor)));
					
					
				}
				
			}
			
			
			
		}
		
		
	}

	//Determines if we have a path after Dijkstra's.
	//    The best estimate for destination will still be infinity if we never touched it
	public boolean hasPath(String destination) {
		
		return distance.get(destination)< Double.POSITIVE_INFINITY;
	}
	
	
	//Backtracks through the "previous" array and assembles our shortest path
	public LinkedList<Edge> getPathTo(String destination) {
		
		if( !hasPath(destination)) {
			return null;
		}
		else {
			LinkedList<Edge> solution = new LinkedList<Edge>();
			for(Edge edge = previous.get(destination); edge != null; edge = previous.get(edge.getFrom())) {
				solution.addFirst(edge);
				numOfHops++;
			}
			return solution;
			
		}
		
	}

	public int getHops(){
		return numOfHops;
	}
	
	

}
