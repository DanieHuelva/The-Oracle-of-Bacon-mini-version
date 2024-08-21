import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MST {
	private HashSet<Edge> mst;
	HashSet<String> movies;
	public MST(){
		this.mst = new HashSet<Edge>();
		this.movies = new HashSet<String>();
	}
	public void kruskalsAlgorithm(Graph graph) {
		// ccm = connected component marker (used to detect cycles)
		HashMap<String, Integer> ccm = new HashMap<String, Integer>();
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>();
		int i = 0;
		for (Edge edge : graph.getEdges()) {
			edgeQueue.add(edge);
			String[] vertices = edge.getVertices();
			i++;
			ccm.put(vertices[0], i);
			i++;
			ccm.put(vertices[1], i);
		}

		while (!edgeQueue.isEmpty()) {

			Edge check = edgeQueue.poll();
			String[] vertices = check.getVertices();
			if (ccm.get(vertices[0]) != ccm.get(vertices[1])) {
				mst.add(check);
				int newMarker = ccm.get(vertices[0]);
				int oldMarker = ccm.get(vertices[1]);
				Set<String> keys = ccm.keySet();
				for (String key : keys) {
					if (ccm.get(key) == oldMarker) {
						ccm.put(key, newMarker);
					}
				}
			}

			// if(ccm[check.getVertices()[0]] != ccm[check.getVertices()[1]]) {

			// mst.add(check);

			// int newMarker = ccm[check.getVertices()[0]];
			// int oldMarker = ccm[check.getVertices()[1]];

			// for(int i =0; i < ccm.length; i++) {

			// if(ccm[i] == oldMarker) {
			// ccm[i] = newMarker;
			// }
			// }

			// }

		}




	}

	public void printMST(){
		int k = 0;
		for (Edge edge : mst) {
			k++;
			System.out.println(Integer.toString(k) +". "+ edge);
			movies.add(edge.getMovie());
		}
		int h = 1;
		System.out.println("List of movies to watch that covers all 30 Actors:\n---------------------");
        for (String movie: movies){
            System.out.println(Integer.toString(h)+ ". " + movie);
            h++;
        }
	}

	public HashSet<Edge> getMST(){
		return mst;
	}
}
