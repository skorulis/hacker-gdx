package skorulis.hacker.def.square;

import java.util.ArrayList;

public class WallLayerDef extends TerrainLayerDef {

	public String textureHorizontal;
	public String textureVertical;
	public String textureCornerNW;
	public String textureCornerNE;
	public String textureCornerSW;
	public String textureCornerSE;
	
	
	public WallLayerDef(String name) {
		super(name);
	}
	
	public String calculateTexture(CompSquareDef north, CompSquareDef east, CompSquareDef south, CompSquareDef west) {
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
			return textureVertical;
		} else if(bitMask == 10) { //1010
			return textureHorizontal;
		} else if(bitMask == 15) { //1111
			return textureMain;
		} else if(bitMask == 12) { //1100
			return textureCornerNE;
		} else if(bitMask == 6) { //0110
			return textureCornerNW;
		} else if(bitMask == 9) { //1001
			return textureCornerSE;
		} else if(bitMask == 3) { //0011
			return textureCornerSW;
		} 
		
		
		return textureMain;
	}
	
	public String[] allTextures() {
		return new String[] {textureMain, textureHorizontal, textureVertical, textureCornerNE, textureCornerNW, textureCornerSE, textureCornerSW };
	}

}
