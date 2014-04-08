package skorulis.hacker.level;


public class LevelBuilder {

	public NetworkLevel level;
	
	public LevelBuilder(NetworkLevel level) {
		this.level = level;
	}
	
	public void addNode(NetworkNode node) {
		level.computers.add(node);
		level.addActor(node);
	}
	
	public void createConnection(NetworkNode comp1, NetworkNode comp2,String square1, String square2) {
		NetworkConnection cd = new NetworkConnection(comp1, comp2, square1, square2);
		level.connections.add(cd);
	}
	
}
