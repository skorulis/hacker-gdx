package skorulis.hacker.def;

public class ConnectionDef {

	public NodePosDef comp1;
	public NodePosDef comp2;
	public String squareId1;
	public String squareId2;
	
	public ConnectionDef(NodePosDef comp1, NodePosDef comp2, String square1, String square2) {
		this.comp1 = comp1;
		this.comp2 = comp2;
		this.squareId1 = square1;
		this.squareId2 = square2;
	}
	
}
