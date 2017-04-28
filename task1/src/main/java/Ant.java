/**
 * Created by paul on 27.04.17.
 */

import org.graphstream.graph.*;

import java.util.LinkedList;
import java.util.List;

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
        if(edge.getNode0() != this.node){
            this.node = edge.getNode0();
        }
        else{
            this.node = edge.getNode1();
        }
    }

    private Edge whichEdgeToGo() {
        Iterable<Edge> edges = this.node.getEachEdge();
        List<Edge> good_edges = new LinkedList<Edge>();

        float allTogether = 0f;
        Edge choice = null;

        for(Edge edge:edges){
            choice = edge;
            System.out.println(edge.getAttribute("pheromon"));
            float pheromon = edge.getAttribute("pheromon");
            allTogether += pheromon;
            if(pheromon > 0f){
                good_edges.add(edge);
            }
        }
        //decide which edge to go


        return choice;
    }
}
