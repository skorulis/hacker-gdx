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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;

import skorulis.hacker.avatar.Avatar;
import skorulis.hacker.computer.Computer;
import skorulis.hacker.def.ComputerPosDef;
import skorulis.hacker.def.ConnectionDef;
import skorulis.hacker.def.LevelDef;

public class NetworkLevel extends Group implements Disposable, EventListener, GestureListener {

	private LevelDef def;
	private ArrayList<Computer> computers;
	private ArrayList<Avatar> avatars;
	private Avatar playerAvater;
	private ShapeRenderer shapeRenderer;
	private Vector3 translation;
	private Vector2 touchStart;
	
	public NetworkLevel(LevelDef def) {
		this.def = def;
		
		translation = new Vector3();
		computers = new ArrayList<Computer>();
		avatars = new ArrayList<Avatar>();
		
		buildLevel();
		
		playerAvater = new Avatar();
		this.addActor(playerAvater);
		avatars.add(playerAvater);
		
		shapeRenderer = new ShapeRenderer();
		this.setBounds(0, 0, 500, 500);
	}
	
	private void buildLevel() {
		for(ComputerPosDef cd : this.def.computers) {
			Computer comp = new Computer(cd);
			comp.addListener(this);
			this.addActor(comp);
			computers.add(comp);
		}
	}
	
	public boolean handle(Event event) {
		Actor actor = event.getListenerActor();
		System.out.println("EVENT " + event + " " + actor);
		return false;
	}
	
	@Override
    public void draw (Batch batch, float parentAlpha) {
		Matrix4 transform = new Matrix4();
		transform.translate(translation);
		batch.end();
		shapeRenderer.setTransformMatrix(transform);
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
	
	
	public boolean isTouchable() {
		return true;
	}
	
	public void dispose() {
		for(Computer c : computers) {
			c.dispose();
		}
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		touchStart = new Vector2(x,y);
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		System.out.println("tap");
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
		System.out.println("pan " + deltaX + "," + deltaY);
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
	
	
}
