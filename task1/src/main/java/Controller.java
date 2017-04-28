/**
 * Created by paul on 27.04.17.
 */
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;

import java.util.LinkedList;
import java.util.List;

public class Controller {

    private Graph graph;
    private Base base;
    private List<Ant> ants;

    public Controller(){
        //new graph from input
        this.graph = new SingleGraph("Aufgabe 1");

        Node a = graph.addNode("A" );
        a.setAttribute("food", 2);
        a.setAttribute("xy", 0,1);

        Node b = graph.addNode("B" );
        b.setAttribute("xy", 0,2);

        Node c = graph.addNode("C" );
        c.setAttribute("xy", 1,1);

        Node d = graph.addNode("D" );
        d.setAttribute("xy", 2,1);

        graph.addEdge("AB", "A", "B").setAttribute("pheromon", 0f);

        graph.addEdge("BC", "B", "C").setAttribute("pheromon", 0f);
        graph.addEdge("CA", "C", "A").setAttribute("pheromon", 0f);
        graph.addEdge("CD", "C", "D").setAttribute("pheromon", 0f);

        //new Base
        this.base = new Base(d);

        //new Ants
        ants = new LinkedList<Ant>();
        for (int i = 0; i<1;i++) {
            this.ants.add(new Ant(base.getNode()));
        }
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
