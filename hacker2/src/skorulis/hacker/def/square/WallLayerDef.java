package skorulis.hacker.def.square;

public class WallLayerDef extends TerrainLayerDef {

	public String textureHorizontal;
	public String textureCornerNE;
	public String textureStubN;
	public String textureCornerNWE;
	
	
	public WallLayerDef(String name, TerrainType type) {
		super(name,type);
	}
	
	public CompSquareTexture calculateTexture(ComputerSquare north, ComputerSquare east, ComputerSquare south, ComputerSquare west) {
		boolean hasNorth = north != null && north.hasLayer(name);
		boolean hasSouth = south != null && south.hasLayer(name);
		boolean hasEast = east != null && east.hasLayer(name);
		boolean hasWest = west != null && west.hasLayer(name);
		
		short bitMask = 0;
		bitMask += hasNorth ? 1 : 0;
		bitMask += hasEast ? 2 : 0;
		bitMask += hasSouth ? 4 : 0;
		bitMask += hasWest ? 8 : 0;
		
		if(bitMask == 5)  { //0101
			return new CompSquareTexture(textureHorizontal,90);
		} else if(bitMask == 10) { //1010
			return new CompSquareTexture(textureHorizontal);
		} else if(bitMask == 15) { //1111
			return new CompSquareTexture(textureMain);
		} else if(bitMask == 12) { //1100
			return new CompSquareTexture(textureCornerNE);
		} else if(bitMask == 6) { //0110
			return new CompSquareTexture(textureCornerNE,90);
		} else if(bitMask == 9) { //1001
			return new CompSquareTexture(textureCornerNE,270);
		} else if(bitMask == 3) { //0011
			return new CompSquareTexture(textureCornerNE,180);
		} else if(bitMask == 1) { //0001
			return new CompSquareTexture(textureStubN,0);
		} else if(bitMask == 2) { //0010
			return new CompSquareTexture(textureStubN,90);
		} else if(bitMask == 4) { //0100
			return new CompSquareTexture(textureStubN,180);
		} else if(bitMask == 8) { //1000
			return new CompSquareTexture(textureStubN,270);
		} else if(bitMask == 11) { //1011
			return new CompSquareTexture(textureCornerNWE,0);
		} else if(bitMask == 7) { //0111
			return new CompSquareTexture(textureCornerNWE,90);
		} else if(bitMask == 14) { //1110
			return new CompSquareTexture(textureCornerNWE,180);
		} else if(bitMask == 13) { //1101
			return new CompSquareTexture(textureCornerNWE,270);
		}
		
		
		return new CompSquareTexture(textureMain);
	}
	
	public String[] allTextures() {
		return new String[] {textureMain, textureHorizontal, textureCornerNE, textureStubN, textureCornerNWE};
	}

}
