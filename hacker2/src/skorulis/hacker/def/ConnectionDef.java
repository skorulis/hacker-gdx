package skorulis.hacker.def;

import skorulis.hacker.def.square.CompSquareDef;

public class ConnectionDef {

	public NodePosDef comp1;
	public NodePosDef comp2;
	public CompSquareDef square1;
	public CompSquareDef square2;
	
	public ConnectionDef(NodePosDef comp1, NodePosDef comp2, CompSquareDef square1, CompSquareDef square2) {
		this.comp1 = comp1;
		this.comp2 = comp2;
		this.square1 = square1;
		this.square2 = square2;
	}
	
}
