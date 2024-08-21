

public class Edge implements Comparable<Edge> {

        private String vertex1;
        private String vertex2;
        private double weight;
        private String movie;
        
        public Edge(String vertex1, String vertex2, String movie) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.movie = movie;
            this.weight = 1.0;
        }
        
        public String[] getVertices(){
            return new String[] {vertex1, vertex2};
        }
        
        public String getMovie() {
            return this.movie;
        }
        
        public String toString() {
            return vertex1 + " - " + vertex2 + ": " + "(" + movie + ")";
        }
        //override the == operator (to ensure to duplicate edges in Graph class)
        public boolean equals(Object obj) {
            Edge otherEdge = (Edge) obj;
            if(vertex1 == otherEdge.vertex1 && vertex2 == otherEdge.vertex2) {
                //0,1 == 0,1 --> true
                return true;
            }
            else if(vertex1 == otherEdge.vertex2 && vertex2 == otherEdge.vertex1) {
                //(0,1) == (1,0) -- > true
                return true;
            }
            else {
                return false;
            }
            
            
            
        }

        public String getFrom(){
            return vertex1;
        }

        public String getTo(){
            return vertex2;
        }

        public double getWeight(){
            return this.weight;
        }
    
        @Override
        // make sure out priority queue sorts from least to greatest based on edge weights
        public int compareTo(Edge otherEdge) {
            
            if(weight < otherEdge.weight) {
                return  -1;
            }
            else if(weight > otherEdge.weight) {
                return 1;
            }
            else {
                return 0;
            }
            
        }
        
}
