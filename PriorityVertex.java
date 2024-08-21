
//This class represents the elements of our priorityQueue
public class PriorityVertex implements Comparable<PriorityVertex>{

	private String vertex;
	private double distanceFromSource;
	
	public PriorityVertex(String start, double d) {
		this.vertex = start;
		this.distanceFromSource = d;
	}
	
	public String getVertex() {
		return this.vertex;
	}
	
	public double getDistance() {
		return this.distanceFromSource;
	}

	@Override
	// Helps our priority queue sort by cost/distance from source
	public int compareTo(PriorityVertex other) {
		
		int num = Double.valueOf(distanceFromSource).compareTo(other.distanceFromSource);
		
		return num;
	}
	
	// During Dikjstra's algorithm, we need to remove old PriorityVertex elements,
	//    this method tells java that we want to remove instances that have matching vertex numbers
	public boolean equals(Object obj) {
		
		if( !(obj instanceof PriorityVertex) ){
			return false;
		}
		PriorityVertex other = (PriorityVertex) obj;
		if(vertex == other.vertex) {
			return true;
		}
		else {
			return false;
		}
		
		
		
	}
	
	
	
}
