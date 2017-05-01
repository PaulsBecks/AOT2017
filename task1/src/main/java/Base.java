/**
 * Created by paul on 27.04.17.
 */

import org.graphstream.graph.*;

public class Base {

    private Node node;
    private int food;

    public Base(Node node){
        this.food = 0;
        this.node = node;
    }

    public Node getNode() {
        return this.node;
    }

    public void addFood() {
        this.food += 1;
    }
}
