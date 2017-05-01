import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by paul on 30.04.17.
 */
public class Tree{
    private AntNode root;
    private AntNode current;

    public Tree(Node rootData) {
        this.root = new AntNode(rootData, null);
        this.current = this.root;
    }

    public AntNode getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current.setData(current);
    }


    public static class AntNode {

        private Node data;
        private AntNode parent;
        private List<AntNode> children;

        public AntNode(Node data, AntNode parent){
            this.data = data;
            this.parent = parent;
            this.children = new ArrayList<AntNode>();
        }

        public AntNode getParent() {
            return parent;
        }

        public List<AntNode> getChildren() {
            return children;
        }

        public Node getData() {
            return data;
        }

        public void setData(Node data) {
            this.data = data;
        }
    }

    public void addNode(Node node){
        this.current = new AntNode(node, this.current);
    }

    public List<Node> getWayBack(){
        List<Node> way_back = new LinkedList<Node>();
        AntNode node = this.current;
        while(true){
            System.out.println("Add " + node.getData().toString() + " to way back");
            way_back.add(node.data);
            if(node.getParent() == null){
                break;
            }
            else{
                node = node.getParent();
            }
        }
        return way_back;
    }

    public Node getNodeWithNotVisitedNeighbour(Set<Node> visited) throws EveryNodeVisitedException{
        
        while(NodeHelper.getNeighboursNotVisited(visited, this.current.getData()).size() == 0){
            System.out.println("Current node "+ this.current.getData().toString() +" got no neighbours new current:" + this.current.getParent());
            this.current = this.current.getParent();
            if(this.current == null){
                throw new EveryNodeVisitedException("No food anymore");
            }
        }
        
        return this.current.getData();
    }
}