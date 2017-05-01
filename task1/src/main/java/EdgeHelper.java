import org.graphstream.graph.Edge;

/**
 * Created by paul on 30.04.17.
 */
public class EdgeHelper {
    public static void addPheromone(Edge edge, double pheromone) {
        edge.setAttribute("pheromone", (Double) getPheromone(edge) + pheromone);
    }

    public static double getPheromone(Edge edge) {
        return (Double) edge.getAttribute("pheromone");
    }
}
