import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;

public class GraphGUI extends JComponent {

	Graph data;
	LinkedList<Node> ourpath = new LinkedList<>();
	LinkedList<Edge> treePath = new LinkedList<>();
	double xScale, yScale;
	int h;

	public GraphGUI(Graph g, LinkedList<Node> ourpath, LinkedList<Edge> treePath) {

		data = g;
		System.out.println("INSIDE GUI NOW");
		this.ourpath = ourpath;
		this.treePath = treePath;
		repaint();

	}

	public GraphGUI(Graph g, LinkedList<Edge> treePath) {

		data = g;
		// System.out.println("INSIDE GUI NOW");
		this.treePath = treePath;
		repaint();

	}

	public GraphGUI(LinkedList<Node> ourpath, Graph g) {

		data = g;
		// System.out.println("INSIDE GUI NOW");
		this.ourpath = ourpath;
		repaint();

	}

	@Override
	public void paintComponent(Graphics g) {

		int w = getWidth();
		h = getHeight();

		System.out.println("MAX LAT  " + data.latmax);
		System.out.println("MIN LAT  " + data.latmin);
		System.out.println("MAX Long  " + data.longmax);
		System.out.println("MIN Long  " + data.longmin);

		xScale = w / (data.longmax - data.longmin);
		yScale = h / (data.latmax - data.latmin);

		System.out.print("xScale: " + xScale + "; yScale: " + yScale);

		// draw graph
		for (Node v : data.hmap.values()) {

			int x1 = (int) (xScale * (v.longitude - data.longmin));
			int y1 = (int) (yScale * (v.latitude - data.latmin));
			y1 = h - y1;

			System.out.println("(" + x1 + ", " + y1 + ")");

			for (Edge e : v.adjList) {

				Node u = e.i2;

				int x2 = (int) (xScale * (u.longitude - data.longmin));
				int y2 = (int) (yScale * (u.latitude - data.latmin));
				y2 = h - y2;

				g.drawLine(x1, y1, x2, y2);

			}
		}
		drawPath(g, ourpath);

	}

	public void drawPath(Graphics g, LinkedList<Node> ourpath) throws NullPointerException {
		if (ourpath != null) {
			for (int i = 0; i < ourpath.size() - 1; i++) {
				Node curr = ourpath.get(i);
				int x1 = (int) (xScale * (curr.longitude - data.longmin));
				int y1 = (int) (yScale * (curr.latitude - data.latmin));
				y1 = h - y1;

				System.out.println("(" + x1 + ", " + y1 + ")");

				Node next = ourpath.get(i + 1);
				int x2 = (int) (xScale * (next.longitude - data.longmin));
				int y2 = (int) (yScale * (next.latitude - data.latmin));
				y2 = h - y2;
				g.setColor(Color.GREEN);
				g.drawLine(x1, y1, x2, y2);

			}
			if (treePath != null) {
				System.out.println("Tree path is being plotted");
				drawEdges(treePath, g);
			}
		} else {
			System.out.println("No path was found");
		}
	}

	public void drawEdges(LinkedList<Edge> edgeList, Graphics g) {

		for (Edge e : edgeList) {
			Node int1 = e.i1;
			Node int2 = e.i2;

			int x1 = (int) (xScale * (int1.longitude - data.longmin));
			int y1 = (int) (yScale * (int1.latitude - data.latmin));
			y1 = h - y1;

			System.out.println("(" + x1 + ", " + y1 + ")");

			int x2 = (int) (xScale * (int2.longitude - data.longmin));
			int y2 = (int) (yScale * (int2.latitude - data.latmin));
			y2 = h - y2;

			g.setColor(Color.RED);
			g.drawLine(x1, y1, x2, y2);

		}
	}

}
