import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph {

	static int count = 0;
	int vertexCount = 0;
	int edgeCount = 0;
	LinkedList<Node> list;
	HashMap<String, Node> hmap;
	double latmax, latmin, longmax, longmin;

	public Graph() {
		latmax = -Double.MAX_VALUE;
		longmax = -Double.MAX_VALUE;
		latmin = Double.MAX_VALUE;
		longmin = Double.MAX_VALUE;
		hmap = new HashMap<String, Node>();
		list = new LinkedList<Node>();
	}

	public Graph(String FileName) {
		latmax = -Double.MAX_VALUE;
		longmax = -Double.MAX_VALUE;
		latmin = Double.MAX_VALUE;
		longmin = Double.MAX_VALUE;
		Scanner s;
		try {
			s = new Scanner(new File(FileName));
		} catch (FileNotFoundException e) {
			System.out.println("File" + FileName + " not found.");
			return;
		}
		// Graph g = new Graph();
		hmap = new HashMap<String, Node>();
		list = new LinkedList<Node>(); // map which stores the id as key and Node object as edge
		String iid;
		double lat;
		double lon;
		String rid;
		String i1;
		String i2;
		Node in;
		Edge re;
		while (s.hasNext()) {
			String ir = s.next();

			if (ir.equals("i")) {
				iid = s.next();
				lat = s.nextDouble();
				lon = s.nextDouble();
				if (latmax <= lat) {
					latmax = lat;
				}

				if (longmax <= lon) {
					longmax = lon;
				}

				if (latmin >= lat) {
					latmin = lat;
				}

				if (longmin >= lon) {
					longmin = lon;
				}
				in = new Node(iid, lat, lon);
				vertexCount++;
				hmap.put(iid, in);
				list.add(in);
			} else {
				rid = s.next();
				// Strings names
				i1 = s.next();
				i2 = s.next();

				Node from = hmap.get(i1);
				Node to = hmap.get(i2);

				if (from == null || to == null) {
					System.out.println("Illegal input");
					continue;
				}

				re = new Edge(rid, from, to);
				from.adjList.add(re);
				re = new Edge(rid, to, from);
				to.adjList.add(re);

				edgeCount++;

			}

			count += 1;
		}

	}

	public void addEdge(Edge e) {

		Node i1 = e.i1, i2 = e.i2;
		String id1 = i1.iid, id2 = i2.iid;

		Node temp1 = hmap.get(id1);
		if (temp1 == null) { // doesn't exist
			i1 = new Node(id1, i1.latitude, i1.longitude);
			hmap.put(id1, i1);
		} else {

			i1 = temp1;
		}
		// i1 is a vertex in the current graph
		Node temp2 = hmap.get(id2);
		if (temp2 == null) {
			i2 = new Node(id2, i2.latitude, i2.longitude);
			hmap.put(id2, i2);
		} else {
			i2 = temp2;
		}

		e = new Edge(e.rid, i1, i2);
		i1.adjList.add(e);
		i2.adjList.add(e);

	}

	public Set<String> listofKeys() {
		return hmap.keySet();
	}

	public Node get(String UID) {
		return hmap.get(UID);
	}

	public LinkedList<Edge> edgesOfGraph() {

		LinkedList<Edge> elist = new LinkedList<>();
		// linked list of edges
		int i = 0;
		while (i < vertexCount) {

			for (Edge curr : this.list.get(i).adjList) {// goes through the adjacency List of edges
				if (elist.contains(curr)) {
					continue;
				} else {
					// add the edge into the list of edges(if it doesn't have it already)
					elist.add(curr);
				}
			}
			i++;

		}

		return elist;
	}

	public boolean areConnected(Node i1, Node i2) {
		DijkstraAlgo da = new DijkstraAlgo(this, i1.iid, i2.iid);

		if (i2.distance < Double.MAX_VALUE) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String args[]) {

		String filename = args[0];
		Graph g = new Graph(filename);

		if (args[1].equals("-show")) {// only do the GUI work if "-show" is present

			String s = args[2];
			String t = args[3];

			System.out.println("creating dja object");
			DijkstraAlgo ad = new DijkstraAlgo(g, s, t);
			LinkedList<Node> path = ad.calculateShortestPath(s, t);

			System.out.println("the shortest path is");
			if (path != null) {
				for (Node n : path) {
					if (n != null) {
						System.out.println(n.iid);
					} else {
						return;
					}

				}
			}

			if (args.length == 5) {

				// System.out.println("Creating kurskals algo object");
				long start = System.nanoTime();
				System.out.println("Computing the minimum spanning tree using KruskalsAlgo ");
				KruskalsAlgo ks = new KruskalsAlgo(g);
				long end = System.nanoTime();
				long elapsed = end - start;
				elapsed = (long) (elapsed / Math.pow(10, 6));
				System.out.println(
						"\t" + "time taken to compute the minimum spanning tree: " + elapsed + " miliseconds.");
				LinkedList<Edge> kslist = new LinkedList<>();
				kslist = ks.accessTree();
				GraphGUI gui = new GraphGUI(g, path, kslist);
				GraphGUI gui1 = new GraphGUI(g, kslist);
				JPanel p = new JPanel();
				JFrame jf = new JFrame("HELLO");
				jf.add(gui);
				jf.add(gui1);
				jf.setSize(500, 500);
				jf.setVisible(true);
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			} else if (args.length == 4) {
				GraphGUI gui = new GraphGUI(path, g);
				JFrame jf = new JFrame("MAP");
				jf.add(gui);
				jf.setSize(500, 500);
				jf.setVisible(true);
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		} // end if
		else {
			String s = args[1];
			String t = args[2];
			DijkstraAlgo ad = new DijkstraAlgo(g, s, t);

			LinkedList<Node> path = ad.calculateShortestPath(s, t);
			System.out.println("the shortest path is");
			if (path != null) {
				for (Node n : path) {
					if (n != null) {
						System.out.println(n.iid);
					} else {
						return;
					}

				}
			}
		}

	}
}
