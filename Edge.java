public class Edge implements Comparable<Edge> {
	String rid;
	Node i1;
	Node i2;
	double weight;

	public Edge(String rid, Node i1, Node i2) {
		this.rid = rid;
		this.i1 = i1;
		this.i2 = i2;
		setWeight(i1, i2);
	}

	public void setWeight(Node x, Node y) {
		double lat = Math.pow(x.latitude - y.latitude, 2);
		double lon = Math.pow(x.longitude - y.longitude, 2);

		weight = Math.sqrt(lat + lon);
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Edge e) {
		// TODO Auto-generated method stub
		if (this.weight < e.weight) {
			return -1;
		} else if (this.weight > e.weight) {
			return 1;
		} else {
			return 0;
		}
	}
}
