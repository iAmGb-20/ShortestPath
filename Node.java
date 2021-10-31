import java.util.LinkedList;

public class Node implements Comparable<Node> {
	String iid;
	double latitude;
	double longitude;
	double distance;
	LinkedList<Edge> adjList;
	boolean check;
	public Node previous;

	public Node(String iid, double latitude, double longitude) {
		this.iid = iid;
		this.latitude = latitude;
		this.longitude = longitude;
		adjList = new LinkedList<>();

		this.check = false;

	}

	public LinkedList<Edge> adjList() {
		return this.adjList;
	}

	public boolean isEqual(Node K) {
		if (iid.equals(K.iid)) {
			return (latitude == K.latitude) && (longitude == K.longitude);
		}

		return false;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double distance() {
		return this.distance;
	}

	public void setPrevious(Node K) {
		this.previous = K;
	}

	public void unchecked() {
		this.check = false;
	}

	public void checkIT() {
		this.check = true;
	}

	public boolean hasChecked() {
		return this.check;
	}

	public void removePath() {
		this.previous = null;
	}

	@Override
	public int compareTo(Node n) {
		if (this.distance < n.distance) {
			return -1;
		} else if (this.distance > n.distance) {
			return 1;
		} else {
			return 0;
		}
	}

	public LinkedList<Node> getPath() {

		LinkedList<Node> ans = new LinkedList<>();
		Node curr = this;

		while (curr != null) {

			ans.add(curr);
			curr = curr.previous;

		}

		return ans;

	}

}
