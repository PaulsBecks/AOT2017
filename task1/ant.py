import sys

class Node:

    def __init__(self, food):
        self.food = food
        self.edges = []

    def add_Edge(self, edge):
        self.edges.append(edge)

    def print_node(self):
        return str(self.food)

class Edge:

    def __init__(self, pheromon, node1, node2):
        self.pheromon = pheromon
        self.node1 = node1
        self.node2 = node2

class Ant_nest:

    def __init__(self, node):
        self.node = node

class Ant:
    def __init__(self, node, ant_nest):
        self.node = node
        self.ant_nest = ant_nest

class Graph:

    def __init__(self, size, ants):
        #create nodes
        world = []
        edges = []
        for i in range(size):
            column = []
            for j in range(size):
                node = Node(0)
                column.append(node)
                if j > 0:
                    neighbour = column[j-1]
                    edge = Edge(0, node, neighbour)
                    node.add_Edge(edge)
                    neighbour.add_Edge(edge)
                if i > 0:
                    neighbour = world[i-1][j]
                    edge = Edge(0, node, neighbour)
                    #edges.append(edge)
                    node.add_Edge(edge)
                    neighbour.add_Edge(edge)


            world.append(column)

        self.world = world
        self.edges = edges
        #create edges

    def print_world(self):
        for i, column in enumerate(self.world):
            for j, node in enumerate(column):
                if j > 0:
                    sys.stdout.write('-0-\t')
                sys.stdout.write(node.print_node()+'\t')
                #print( "x \t", end='')
            sys.stdout.write('\n')

        sys.stdout.flush()


graph = Graph(5, 5)

print(graph.print_world())
