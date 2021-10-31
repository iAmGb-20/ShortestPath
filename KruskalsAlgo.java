import java.util.LinkedList;
import java.util.PriorityQueue;

public class KruskalsAlgo {
	Graph tree;

	public KruskalsAlgo(Graph data) {

		tree = new Graph();

		PriorityQueue<Edge> q = new PriorityQueue<>();
		// have to put all the edges of the graph into the queue

		for (Edge e : data.edgesOfGraph()) {
			q.add(e);
		}

		while (!q.isEmpty()) {

			Edge e = q.poll();
			// decide if we want to add e to the tree
			String name1 = e.i1.iid;
			String name2 = e.i2.iid;

			Node i1inTree = tree.hmap.get(name1);
			Node i2inTree = tree.hmap.get(name2);

			if (i1inTree == null || i2inTree == null) {
				tree.addEdge(e);
				continue;
			}

			// if i1 and i2 will be connected in tree
			// adding a new edge i1->i2 will create a cycle

			if (tree.areConnected(i1inTree, i2inTree)) {
				// dont add e
				continue;
			} else {
				tree.addEdge(e);
			}

		}
		printTree();
	}

	public LinkedList<Edge> accessTree() {

		LinkedList<Edge> treelist = new LinkedList<>();
		for (Node v : tree.hmap.values()) {
			for (Edge e : v.adjList) {
				if (!treelist.contains(e)) {
					treelist.add(e);

				}
			}
		}

		return treelist;

	}

	public void printTree() {
		System.out.println(tree.hmap.keySet());
	}

}
