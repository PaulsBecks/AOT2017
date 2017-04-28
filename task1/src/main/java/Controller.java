/**
 * Created by paul on 27.04.17.
 */

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import org.json.*;
import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Controller {

    private Graph graph;
    private Base base;
    private List<Ant> ants;

    public Controller(){
        //new graph from input
        String path = "src"+File.separator+ "main"+File.separator+"resources"+File.separator+"world"+File.separator;
        this.graph = this.loadGraphFile(path+"simple_world.json");


    }

    private Graph loadGraphFile(String location)  {
        String jsonString = "";

        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {

            Object obj = parser.parse(new FileReader(location));

            json = (JSONObject) obj;
        }
        catch (Exception e){
            //TODO: handle exception
            System.out.println("No file at given location");
            e.printStackTrace();
        }
        JSONObject world = (JSONObject) json.get("world");
        JSONArray nodes = (JSONArray) world.get("nodes");
        JSONArray edges = (JSONArray) world.get("edges");

        String name = (String) world.get("name");

        Graph graph = new SingleGraph("Aufgabe 1");

        //add nodes to graph
        for(int i=0; i<nodes.size(); i++){
            JSONObject node = (JSONObject) nodes.get(i);
            Node n = graph.addNode((String) node.get("label"));
            n.addAttribute("xy", (Long) node.get("x"), (Long) node.get("y"));
            n.addAttribute("food", (Long) node.get("food"));
            n.addAttribute("ui.label", (String)node.get("label"));
        }

        //add edges to graph

        for(int i=0; i<edges.size(); i++){
            JSONObject edge = (JSONObject) edges.get(i);
            Edge e = graph.addEdge((String)edge.get("label"), (String)edge.get("node1"), (String)edge.get("node2"));
            e.addAttribute("pheromone", (Double)edge.get("pheromone"));
            e.addAttribute("ui.label", (Double)edge.get("pheromone"));
        }

        //new Base
        this.base = new Base(graph.getNode("D"));

        //new Ants
        ants = new LinkedList<Ant>();
        for (int i = 0; i<1;i++) {
            this.ants.add(new Ant(base.getNode()));
        }

        return graph;
    }

    public static void main(String... args){
        final Controller controller = new Controller();

        Thread thread = new Thread(new Runnable() {
            public void run() {
                for(int i=0; i<10;i++) {
                    controller.displayGraph();
                    controller.updateAnts();
                    controller.updateGraph();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private void updateAnts() {
        //update Ants
        for(Ant ant: this.ants){
            ant.update();
        }
    }

    private void updateGraph() {
        //update pheromon on edges

    }

    private void addAdge() {
        graph.addEdge("AB", "A", "B");
    }

    public void displayGraph(){

        Viewer viewer = this.graph.display();
        viewer.disableAutoLayout();
    }

    private void removeEdge() {
        this.graph.removeEdge("AB");
    }
}
