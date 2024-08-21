import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class GraphDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Graph myGraph = new Graph();
        HashMap<String, LinkedList<String>> hash = new HashMap<String, LinkedList<String>>(); // makes a hashmap for the
                                                                                              // movie and the actors in
                                                                                              // it
        try {
            File myObj = new File("actors.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] myarr = data.split("\\|");
                String actor = myarr[0]; // actor per line
                String movie = myarr[1]; // the movie
                myGraph.addVertice(actor); // make a vertice for the actor
                if (hash.get(movie) == null) { // if not there yet
                    hash.put(movie, new LinkedList<>());
                    LinkedList link = hash.get(movie);
                    link.add(actor);
                    hash.put(movie, link);

                } else {
                    LinkedList link = hash.get(movie);
                    link.add(actor); // adds the actor to the movie that its in
                    hash.put(movie, link);
                }

            }
            for (String movie : hash.keySet()) { // doing this to connect the actors thats in the same movie
                LinkedList actors = hash.get(movie);
                for (int i = 0; i < actors.size() - 1; i++) {
                    for (int j = 1; j < actors.size(); j++) {
                        String actor1 = (String) actors.get(i);
                        String actor2 = (String) actors.get(j);
                        if (!actor1.equals(actor2)) { // making sure not to connect an actor to itself
                            myGraph.addEdges((String) actors.get(i), (String) actors.get(j), movie);
                        }
                    }
                }
            }
            ;
            //myGraph.printAdList();

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner myObj = new Scanner(System.in);
        int ans = 0;
        while (ans != 4) {
            System.out.println(
                    "\nEnter your choice: \n1. Print out MST Information\n2. Find Shortest Path from one\nActor to another\n3. Add an actor/actress to our data\n4. Exit\n");
            ans = myObj.nextInt();
            if (ans == 1) {
                MST myMST = new MST();
                System.out.println("Edges in MST\n---------------------");
                myMST.kruskalsAlgorithm(myGraph);
                myMST.printMST();
            } else if (ans == 2) {
                ShortestPath path = new ShortestPath();
                System.out.println("Enter starting actor: ");
                String actor1 = scan.nextLine();
                System.out.println("Enter a destination actor: ");
                String actor2 = scan.nextLine();
                Set<String> actors = myGraph.getVertices();
                if (actors.contains(actor1)){
                    path.computeShortestPath(myGraph, actor1);
                    if (actors.contains(actor2)){
                        if (path.hasPath(actor2)){
                        LinkedList<Edge> edges = path.getPathTo(actor2);
                        for (Edge edge: edges){
                            String[] ver = edge.getVertices();
                            System.out.println(ver[0] + " has acted with " + ver[1] + " in " + edge.getMovie());
                        }
                        System.out.println("Number of hops: " + path.getHops());
                        }
                        else{
                            System.out.println("Path does not exist");
                        }
                    }
                    else{
                        System.out.println("Path does not exist"
                        );
                    }
                }
                else{
                    System.out.println("Path does not exist"
                    );
                }
            }
            else if(ans ==3){
                System.out.println("Add another actor to our data!");
                System.err.println("Name of actor: ");
                String star = scan.nextLine();
                while (myGraph.getVertices().contains(star)){
                    System.err.println("That actor is already in our dataset");
                    System.out.println("Add another actor to our data!");
                    System.err.println("Name of actor: ");
                    star = scan.nextLine();
                }
                System.out.println("What movies has this actor have been in? (Please separate the movies with a comma) (eg. Star Wars,Avengers,Barbie)");
                String movies = scan.nextLine();
                myGraph.addActor(star, movies);
                System.out.println("Succesfully added the actor to the dataset");
            }
        }

    }
}
