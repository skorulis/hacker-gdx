package skorulis.hacker.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;

import skorulis.hacker.avatar.Avatar;
import skorulis.hacker.computer.Computer;
import skorulis.hacker.def.ComputerPosDef;
import skorulis.hacker.def.ConnectionDef;
import skorulis.hacker.def.LevelDef;

public class NetworkLevel extends Group implements Disposable, GestureListener {

	private LevelDef def;
	private ArrayList<Computer> computers;
	private ArrayList<Avatar> avatars;
	private Avatar playerAvatar;
	private ShapeRenderer shapeRenderer;
	private Vector3 translation;
	
	public NetworkLevel(LevelDef def) {
		this.def = def;
		
		translation = new Vector3();
		computers = new ArrayList<Computer>();
		avatars = new ArrayList<Avatar>();
		
		buildLevel();
		
		playerAvatar = new Avatar(findEntryComputer());
		
		this.addActor(playerAvatar);
		avatars.add(playerAvatar);
		
		shapeRenderer = new ShapeRenderer();
		this.setBounds(0, 0, 500, 500);
	}
	
	private void buildLevel() {
		for(ComputerPosDef cd : this.def.computers) {
			Computer comp = new Computer(cd);
			this.addActor(comp);
			computers.add(comp);
		}
	}
	
	@Override
    public void draw (Batch batch, float parentAlpha) {
		Matrix4 transform = new Matrix4();
		transform.translate(translation);
		batch.end();
		shapeRenderer.setTransformMatrix(transform);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		for(ConnectionDef cd : def.connections) {
			shapeRenderer.line(cd.comp1.location, cd.comp2.location);
		}
		shapeRenderer.end();
		
		batch.begin();
		batch.setTransformMatrix(transform);
		drawChildren(batch, parentAlpha);
	}
	
	public Computer findEntryComputer() {
		for(Computer c: computers) {
			if(c.def.name().equals(def.entryComputer.name())) {
				return c;
			}
		}
		return null;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		x -= translation.x;
		y += translation.y;
		y = this.getStage().getHeight() - y;
		
		System.out.println("tap " + x + "," + y);
		for(Computer c: computers) {
			if(c.hit(x, y, true) != null) {
				if(c != playerAvatar.currentComputer) {
					playerAvatar.travelTo(c);
				}
			}
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		System.out.println("long press");
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		System.out.println("fling");
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		//System.out.println("pan " + deltaX + "," + deltaY);
		translation.x += deltaX;
		translation.y -= deltaY;
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		System.out.println("pan stop");
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		System.out.println("zoom");
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		System.out.println("pinch");
		return false;
	}
	
	public void dispose() {
		for(Computer c : computers) {
			c.dispose();
		}
	}
	
	
}
