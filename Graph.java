import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;


public class Graph {
    private HashMap<String, LinkedList<Edge>> adList;
    private HashMap<String, LinkedList<String>> movieActors;
    private int numVer;
    private int numEd;
    private HashSet<Edge> edges;

    public Graph() {
        this.adList = new HashMap<String, LinkedList<Edge>>();
        this.movieActors = new HashMap<String, LinkedList<String>>();
        this.numVer = 0;
        this.numEd = 0;
        this.edges = new HashSet<Edge>();
    }

    public void addVertice(String string) {
        adList.put(string, new LinkedList<Edge>());
        numVer++;
    }

    public void addEdges(String v1, String v2, String movie) {
        if (movieActors.get(movie) == null){
            movieActors.put(movie, new LinkedList<String>());
        }
        LinkedList<String> actors = movieActors.get(movie);
        Edge edge = new Edge(v1, v2, movie);
        LinkedList<Edge> newLinkedList = adList.get(v1);
        newLinkedList.add(edge);
        adList.put(v1, newLinkedList); // adding edge to the first actor
        actors.add(v1);

        newLinkedList = adList.get(v2); // adding to the second one
        newLinkedList.add(new Edge(v2, v1, movie));
        adList.put(v2, newLinkedList);
        edges.add(edge);
        numEd++;
        actors.add(v2);
        movieActors.put(movie, actors);
    }

    public void printAdList() {
        for (Entry<String, LinkedList<Edge>> entry : adList.entrySet()) {
            if (entry.getValue().size() != 0) {
                System.out.println(entry.getKey() + " : " + "(" + entry.getValue() + ")");
            }
        }
    }

    public Set<String> getVertices(){
        return adList.keySet();
    }

    public int numOfvertices() {
        return this.numVer;
    }

    public int numOfEdges() {
        return numEd;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

    public void printMovies(){
        int j = 1;
        for (String movie: movieActors.keySet()){
            System.out.println(Integer.toString(j)+ ". " + movie);
            j++;
        }
    }

    public LinkedList<Edge> getAdjacencyEdges(String vertex) {
        return adList.get(vertex);
    }

    public void addActor(String actor, String movie){
        addVertice(actor);
        HashMap<String, String> matchedActors = new HashMap<String, String>();
        String[] actorMovies = movie.split(",");
        for (String mov: actorMovies){
            if (movieActors.keySet().contains(mov)){
                LinkedList<String> all = movieActors.get(mov);
                for (String coActors: all){
                    matchedActors.put(coActors,mov);
                }
            }
            else{
                movieActors.put(mov, new LinkedList<String>());
                LinkedList<String> adding = movieActors.get(mov);
                adding.add(actor);
                movieActors.put(movie, adding);

            }
        }
        for (String actedWith: matchedActors.keySet()){
            addEdges(actedWith, actor, matchedActors.get(actedWith));
        }
        updateFile(actor,actorMovies);

    }

    private void updateFile(String actor, String[] actorMovies) {
        String all = "";
        try {
            File myObj = new File("actors.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                all += data + "\n";
            }
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    try {
 
        // Create a FileWriter object
        // to write in the file
        FileWriter fWriter = new FileWriter(
            "actors.txt");

        // Writing into file
        // Note: The content taken above inside the
        // string
        for (String theMovies: actorMovies){
            all = all + actor+"|"+theMovies + "\n";
        }
        fWriter.write(all);

        // Closing the file writing connection
        fWriter.close();

    }

    // Catch block to handle if exception occurs
    catch (IOException e) {

        // Print the exception
        System.out.print(e.getMessage());
    }
}


}

