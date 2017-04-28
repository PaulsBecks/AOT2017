/**
 * Created by paul on 27.04.17.
 */

import org.graphstream.graph.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ant {

    private Node node;
    private int id;
    private List<Edge> walked;

    public Ant(Node start){
        this.node = start;
    }

    public void update() {
        System.out.println(this.node.toString());
        Edge edge = this.whichEdgeToGo();
        this.walk(edge);
    }

    private void walk(Edge edge) {
        //remove yourself from node
        int ants = this.node.getAttribute("ants");
        this.node.setAttribute("ants", ants-1);

        if(edge.getNode0() != this.node){
            this.node = edge.getNode0();
        }
        else{
            this.node = edge.getNode1();
        }
        if(this.node == null){
            System.out.println("fuck");
        }
        //lay down pheromone
        double pheromone = edge.getAttribute("pheromone");
        edge.setAttribute("pheromone", pheromone+1.0);

        //add yourself to node
        ants = this.node.getAttribute("ants");
        this.node.setAttribute("ants", ants+1);
    }

    private Edge whichEdgeToGo() {
        Iterable<Edge> edges = this.node.getEachEdge();
        List<Edge> good_edges = new LinkedList<Edge>();

        float allTogether = 0f;
        Edge choice = null;
        Random rand = new Random();
        int number = rand.nextInt(this.node.getEdgeSet().size());
        int i = 0;
        for(Edge edge:edges){
            if(i == number) {
                choice = edge;
            }
            i++;
            /*
            System.out.println(edge.getAttribute("pheromone"));
            double pheromon = edge.getAttribute("pheromone");
            allTogether += pheromon;
            if(pheromon > 0f){
                good_edges.add(edge);
            }*/

        }
        //decide which edge to go


        return choice;
    }
}
