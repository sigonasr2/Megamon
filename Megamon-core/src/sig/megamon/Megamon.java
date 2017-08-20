package sig.megamon;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import sig.megamon.ref.Ref;
import sig.megamon.ref.RoomRef;
import sig.megamon.ref.SignRef;
import sig.megamon.utils.DrawUtils;
import sig.megamon.utils.GraphicUtils;
import sig.megamon.utils.PlayerUtils;

public class Megamon extends ApplicationAdapter implements ApplicationListener{
	SpriteBatch batch;
	Texture img;
	public static BitmapFont font;
	DialogBox messagebox;
	//AssetManager assets;
	//TiledMap map;
	OrthographicCamera camera;
	final public static int TILESIZE=16;
	final public static int WINDOW_WIDTH=640;
	final public static int WINDOW_HEIGHT=480;
	final public static double CHAR_SPD=0.1;
	Point2D.Double direction = new Point2D.Double(0,0);
	final public static Point2D.Double position=new Point2D.Double(1,1);
	Calendar lastCheck = Calendar.getInstance();
	int framesPassed=0;
	final public static String HIDDENLAYERNAME = "Tile Layer 2";
	//public static List<Object> objects = new ArrayList<Object>();
	public static Room currentLevel;
	public static HashMap<String,RoomRef> doorDatabase = new HashMap<String,RoomRef>();
	public static HashMap<String,SignRef> infoDatabase = new HashMap<String,SignRef>();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("megamon_icon64.png");
		font = new BitmapFont(Gdx.files.internal("fonts/AgencyFB.fnt"));
		
		//assets = new AssetManager();
		
		/*assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assets.load("tilesets/Test Map.tmx",TiledMap.class);
		map = assets.get("tilesets/Test Map.tmx");*/
		//map = new TmxMapLoader().load("tilesets/Test Map.tmx");
		//map.getLayers().get(HIDDENLAYERNAME).setVisible(false);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 20, (float)(20*(GraphicUtils.getAspectRatio())));
		//System.out.println("Camera position: "+camera.position);
		/*objects.add(new Object("MEGASPINNN.png",new Point2D.Double(0, 4)));
		objects.add(new Object("megamonlogowip.png",new Point2D.Double(3, 7)));*/
		Database.SetupDoorDatabase();
		Database.SetupInfoDatabase();
		currentLevel = new Room(position,"Test Map");
		messagebox = new DialogBox("This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(currentLevel.backgroundColor.r, currentLevel.backgroundColor.g, currentLevel.backgroundColor.b, currentLevel.backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*if (Gdx.graphics.getWidth()/Gdx.graphics.getHeight()>1.34 || Gdx.graphics.getWidth()/Gdx.graphics.getHeight()<1.32) {
			Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth(), (int)(Gdx.graphics.getWidth()/1.33));
			Gdx.graphics.set
			//Potential resizing code.
		}*/
		if (messagebox!=null) {
			messagebox.run();
		}
		else {
			for (Object o : currentLevel.getObjects()) {
				o.run();
			}
			if (PlayerUtils.isSnappedToGrid(position)) {
				direction=new Point2D.Double(0, 0);
				if (Gdx.input.isKeyPressed(Input.Keys.A)) {
					direction=new Point2D.Double(-CHAR_SPD,0);
				} else
				if (Gdx.input.isKeyPressed(Input.Keys.W)) {
					direction=new Point2D.Double(0,CHAR_SPD);
				} else 
				if (Gdx.input.isKeyPressed(Input.Keys.D)) {
					direction=new Point2D.Double(CHAR_SPD,0);
				} else
				if (Gdx.input.isKeyPressed(Input.Keys.S)) {
					direction=new Point2D.Double(0,-CHAR_SPD);
				}
				if (direction.x!=0 || direction.y!=0) {
					//System.out.println("("+position.x+","+Math.signum(direction.x)+","+position.y+","+Math.signum(direction.y)+")");
					Point2D.Double destinationposition = new Point2D.Double(position.x+Math.signum(direction.x),position.y+Math.signum(direction.y));
					if (PlayerUtils.isLocationPassable(currentLevel.getMap(),destinationposition)) {
						position.setLocation(position.x+direction.x, position.y+direction.y);
					} else {
						//We hit a wall.
					}
					System.out.println(infoDatabase.keySet());
					CheckForDoor(destinationposition);
					CheckForInfo(destinationposition);
				}
			} else {
				position.setLocation(position.x+direction.x, position.y+direction.y);
				//System.out.println(position);
			}
		}
		camera.position.set((float)position.x+0.5f, (float)position.y+0.5f, camera.position.z);
		//System.out.println("Camera position: "+camera.position);
		camera.update();
		System.out.println(Megamon.WINDOW_WIDTH+","+Megamon.WINDOW_HEIGHT+";"+camera.viewportWidth+","+camera.viewportHeight);
		currentLevel.renderer.setView(camera);
		currentLevel.renderer.render();
		batch.begin();
		//batch.draw(img, 0, 0);
		for (Object o : currentLevel.getObjects()) {
			batch.draw(o.sprite, 
					(float)GraphicUtils.getRelativePixelPositionFromPlayer(camera, o.position).x, 
					(float)GraphicUtils.getRelativePixelPositionFromPlayer(camera, o.position).y);
			o.draw();
		}
		batch.draw(img, (int)GraphicUtils.getPlayerPixelPosition(camera).getX(), (int)GraphicUtils.getPlayerPixelPosition(camera).getY(), (int)GraphicUtils.getTileSize(camera).getX(), (int)GraphicUtils.getTileSize(camera).getY());
		if (messagebox!=null) {
			messagebox.draw(batch);
		}
		GlyphLayout size = font.draw(batch, "Test Text", 0, 32);
		//font.draw(batch, "Test Text", Megamon.WINDOW_WIDTH-size.width, 128-size.height);
		batch.end();
		framesPassed++;
		if (lastCheck.getTime().getSeconds()!=Calendar.getInstance().getTime().getSeconds()) {
			System.out.println("FPS: "+framesPassed);
			framesPassed=0;
			lastCheck=Calendar.getInstance();
		}
	}

	private void CheckForDoor(Point2D.Double destinationposition) {
		if (doorDatabase.containsKey(PlayerUtils.getDoorPositionHash(destinationposition))) {
			//System.out.println("This is a door!");
			RoomRef door = doorDatabase.get(PlayerUtils.getDoorPositionHash(destinationposition));
			if (!door.destinationRoom.equalsIgnoreCase(currentLevel.mapName)) {
				currentLevel.destroy();
				currentLevel = new Room(door.doorDestination,door.destinationRoom);
			} else {
				position.setLocation(door.doorDestination);
			}
		}
	}

	private void CheckForInfo(Point2D.Double destinationposition) {
		if (infoDatabase.containsKey(PlayerUtils.getDoorPositionHash(destinationposition))) {
			//System.out.println("This is a door!");
			SignRef info = infoDatabase.get(PlayerUtils.getDoorPositionHash(destinationposition));
			//TODO Do interface stuff here.
			
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
