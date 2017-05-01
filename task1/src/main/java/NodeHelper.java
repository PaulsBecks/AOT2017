import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by paul on 30.04.17.
 */
public class NodeHelper {


    public static boolean gotFood(Node node) {
        return (Long) node.getAttribute("food") > 0l ? true : false;
    }

    public static void collectFood(Node node) {
        node.setAttribute("food", (Long) node.getAttribute("food") - 1l);
    }

    public static void removeAnt(Node node) {
        node.setAttribute("ants", (Integer) node.getAttribute("ants") - 1);
    }

    public static void addAnt(Node node) {
        node.setAttribute("ants", (Integer) node.getAttribute("ants") + 1);
    }

    public static Edge getEdge(Node from, Node to) {
        return from.getEdgeBetween(to.toString());
    }

    public static List<Node> getNeighboursNotVisited(Set<Node> visited, Node node) {
        List<Node> neighbours = new LinkedList<Node>();
        Iterator<Node> iterator = node.getNeighborNodeIterator();
        while(iterator.hasNext()){
            Node neighbour = iterator.next();
            if(!visited.contains(neighbour)){
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }
}
