import java.util.LinkedList;
import java.util.PriorityQueue;

// Java implementation of Dijkstra's Algorithm
// using Priority Queue

public class DijkstraAlgo {
	public double[] distTo;
	public double[] edgeTo;
	String s;
	String t;
	Graph g;

	public DijkstraAlgo(Graph g, String s, String t) {
		this.g = g;
		this.s = s;
		this.t = t;
		distTo = new double[g.vertexCount];
		edgeTo = new double[g.vertexCount];

	}

	public void initialization() {

		for (String s : g.listofKeys()) {
			// set the distance to all nodes as infinity
			Node MyNode = g.get(s);
			MyNode.setDistance(Double.POSITIVE_INFINITY);
			MyNode.unchecked();
			MyNode.removePath();

		}
	}

	public LinkedList<Node> calculateMyShortestpath() {

		initialization();

		Node from = g.get(s);
		Node to = g.get(t);

		if (from == null) {
			System.out.println("We do not have a source");
			return null;
		}
		if (to == null) {
			System.out.println("Invalid destination!!");
			return null;
		}
		LinkedList<Node> shortestPath = new LinkedList<>();
		from.setDistance(0.0);

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(from);

		while (!pq.isEmpty()) {
			Node focus = pq.poll();
			focus.checkIT();

			if (focus.isEqual(to)) {
				shortestPath = to.getPath();
				System.out.println("Algorithm terminated with success!!");
				return shortestPath;

			}

			for (Edge e : focus.adjList) {
				Node x = e.i2;

				if (x.hasChecked()) {
					continue;
				}

				double GoodDistance = focus.distance + e.weight;

				if (GoodDistance < x.distance) {

					x.setDistance(GoodDistance);
					x.setPrevious(focus);

					pq.offer(x);
				}

			}
		}
		System.out.println("there is no path between the places_____ " + from + " - " + to);
		return null;
	}

	public LinkedList<Node> calculateShortestPath(String s, String t) {
		this.s = s;
		this.t = t;
		long start = System.nanoTime();
		System.out.println("Computing the shortest path from " + s + "to " + t);

		LinkedList<Node> theAns = calculateMyShortestpath();
		long end = System.nanoTime();
		long elapsed = end - start;
		elapsed = (long) (elapsed / Math.pow(10, 6));
		System.out.println("\t" + "time taken to compute the shortest path: " + elapsed + " miliseconds.");

		return theAns;

	}


}
