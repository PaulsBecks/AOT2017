/**
 * Created by paul on 27.04.17.
 */

import org.graphstream.graph.*;

import java.lang.reflect.Array;
import java.util.*;

public class Ant {

    private Base base;
    private int id;
    private Tree walked;
    private Set<Node> visited;
    private boolean carrysFood = false;

    //amount of ticks the ant will live
    private int ttl;

    public Ant(Base base){
        this.walked = new Tree(base.getNode());
        this.base = base;
        this.visited = new HashSet<Node>();

        this.visited.add(this.getCurrent());
    }

    public void update() throws EveryNodeVisitedException{
        //decide what to do
        if(carrysFood){
            returnFood();
        }
        else if(NodeHelper.gotFood(this.getCurrent())){
            NodeHelper.collectFood(this.getCurrent());
            this.setCarrysFood(true);
        }
        else {
            System.out.println(this.getCurrent().toString());
            Edge edge = this.whichEdgeToGo();
            this.walk(edge);
        }
    }

    private void returnFood(){
        //check food available
        if(!this.carrysFood){
            System.out.println("This ant got no food to return");
            return;
        }

        //get way back
        List<Node> way_back = walked.getWayBack();

        //update pheromone on edges
        for(int i = 0; i<way_back.size()-1; i++){
            //get edge between nodes
            Edge edge = NodeHelper.getEdge(way_back.get(i), way_back.get(i+1));

            //update pheromone
            EdgeHelper.addPheromone(edge, 1.0);
        }

        //add food to base
        this.base.addFood();

        //set carryFood to false
        this.carrysFood = false;

        //remove visited
        visited.clear();
        visited.add(this.base.getNode());

        //set node to home
        this.walked = new Tree(this.base.getNode());
    }

    private void walk(Edge edge) {

        //remove yourself from node
        NodeHelper.removeAnt(this.getCurrent());

        if(edge.getNode0() != this.getCurrent()){
            this.walked.addNode(edge.getNode0());
        }
        else{
            this.walked.addNode(edge.getNode1());
        }

        //lay down pheromone
        EdgeHelper.addPheromone(edge, 1.0);


        //add yourself to node
        NodeHelper.addAnt(this.getCurrent());

        //add current node to visited
        visited.add(this.getCurrent());

    }

    private Edge whichEdgeToGo() throws EveryNodeVisitedException{

        //get not visited neighbours
        List<Node> neighbours = NodeHelper.getNeighboursNotVisited(visited, this.getCurrent());
        if(neighbours.size() == 0){
            //all neighbours visited already
            Node node = this.walked.getNodeWithNotVisitedNeighbour(visited);
            //set current node to node
            neighbours = NodeHelper.getNeighboursNotVisited(visited, node);
        }
        if(neighbours.size() == 1){
            return NodeHelper.getEdge(this.getCurrent(), neighbours.get(0));
        }

        //get complete pheromone
        double pheromone = 0.0;
        double[] edge_pheromone = new double[neighbours.size()];

        for(int i = 0; i<neighbours.size(); i++){
            Edge edge = NodeHelper.getEdge(this.getCurrent(), neighbours.get(i));
            double p = EdgeHelper.getPheromone(edge);
            edge_pheromone[i] = p;
            pheromone += p;
        }

        //if pheromone 0 choose random
        if(pheromone == 0.0){
            Random rand = new Random();
            int number = rand.nextInt(neighbours.size());
            return NodeHelper.getEdge(this.getCurrent(), neighbours.get(number));
        }

        //calculate probability
        //TODO: Add random to probability
        double[] probability = new double[neighbours.size()];
        for(int i = 0; i<neighbours.size(); i++){
            probability[i] = edge_pheromone[i]/pheromone;
        }

        Random rand = new Random();
        double d = rand.nextDouble();
        System.out.print("Current probability is " + d);
        for(int i = 0; i<edge_pheromone.length; i++){
            if(d < getAccumulatedProbability(edge_pheromone, i)){
                return NodeHelper.getEdge(this.getCurrent(), neighbours.get(i));
            }
        }

        //None of this methods above was able to get a result
        int i = rand.nextInt(neighbours.size());
        return NodeHelper.getEdge(this.getCurrent(), neighbours.get(i));
    }

    private double getAccumulatedProbability(double[] edge_pheromone, int i) {
        double com = 0.0;
        for(int j = 0; j<=i; j++){
            com += edge_pheromone[j];
        }
        return com;
    }

    public void setCarrysFood(boolean carrys_food) {
        this.carrysFood = carrys_food;
    }

    public Node getCurrent() {
        return this.walked.getCurrent().getData();
    }
}
