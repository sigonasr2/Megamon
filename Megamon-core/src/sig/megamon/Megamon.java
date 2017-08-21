package sig.megamon;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
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
import com.badlogic.gdx.Input.Keys;
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

import sig.megamon.creature.CreatureMove;
import sig.megamon.creature.CreatureMoveLinker;
import sig.megamon.creature.CreatureType;
import sig.megamon.creature.ExperienceRate;
import sig.megamon.menu.DialogBox;
import sig.megamon.menu.MegamonPartyScreen;
import sig.megamon.menu.StartMenuBox;
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
	public static DialogBox messagebox;
	public static StartMenuBox startmenubox;
	public static MegamonPartyScreen partyscreen;
	public static Texture healthbar;
	//AssetManager assets;
	//TiledMap map;
	static OrthographicCamera camera;
	final public static int TILESIZE=16;
	final public static int WINDOW_WIDTH=640;
	final public static int WINDOW_HEIGHT=480;
	final public static double CHAR_SPD=0.1;
	Calendar lastCheck = Calendar.getInstance();
	public static Player mainP;
	int framesPassed=0;
	final public static String HIDDENLAYERNAME = "Tile Layer 2";
	final public static int ACTIONKEY = Keys.Z;
	final public static int CANCELKEY = Keys.X;
	final public static int MOVELEFTKEY = Keys.LEFT;
	final public static int MOVERIGHTKEY = Keys.RIGHT;
	final public static int MOVEUPKEY = Keys.UP;
	final public static int MOVEDOWNKEY = Keys.DOWN;
	final public static int MENUKEY = Keys.ENTER;
	public static List<MegamonCreature> megamonDatabase = new ArrayList<MegamonCreature>();
	public static HashMap<String,CreatureMove> moveDatabase = new HashMap<String,CreatureMove>();
	//public static List<Object> objects = new ArrayList<Object>();
	public static Room currentLevel;
	public static HashMap<String,RoomRef> doorDatabase = new HashMap<String,RoomRef>();
	public static HashMap<String,SignRef> infoDatabase = new HashMap<String,SignRef>();
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/AgencyFB.fnt"));
		mainP = new Player(new Point2D.Double(1,1),"megamon_icon64.png");
		healthbar = new Texture("interface/healthbar.png");
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
		Database.SetupMoveDatabase();
		Database.SetupMegamonDatabase();
		currentLevel = new Room(mainP.position,"Test Map");
		
		MegamonPet testPet = new MegamonPet("Test Name",
				new MegamonCreature("test_mini_sprite.png","test_sprite.png","test_sprite.png",
						"Test Creature", "This is for testing purposes only.", 14, 60, CreatureType.NORMAL,
						40,60,30,30,50,55,30,ExperienceRate.MEDIUM_FAST,new CreatureMoveLinker[]{}),
				100);
		MegamonPet testPet2 = new MegamonPet("Test Name",
				new MegamonCreature("test_mini_sprite.png","test_sprite.png","test_sprite.png",
						"Test Creature", "This is for testing purposes only.", 14, 60, CreatureType.NORMAL,
						40,60,30,30,50,55,30,ExperienceRate.MEDIUM_FAST,new CreatureMoveLinker[]{}),
				100);
		MegamonPet testPet3 = new MegamonPet("Test Name",
				new MegamonCreature("test_mini_sprite.png","test_sprite.png","test_sprite.png",
						"Test Creature", "This is for testing purposes only.", 14, 60, CreatureType.NORMAL,
						40,60,30,30,50,55,30,ExperienceRate.MEDIUM_FAST,new CreatureMoveLinker[]{}),
				100);
		MegamonPet testPet4 = new MegamonPet("Test Name",
				new MegamonCreature("test_mini_sprite.png","test_sprite.png","test_sprite.png",
						"Test Creature", "This is for testing purposes only.", 14, 60, CreatureType.NORMAL,
						40,60,30,30,50,55,30,ExperienceRate.MEDIUM_FAST,new CreatureMoveLinker[]{}),
				100);
		testPet.setHP((int)(Math.random()*testPet.getMaxHP()));
		testPet2.setHP((int)(Math.random()*testPet2.getMaxHP()));
		testPet3.setHP((int)(Math.random()*testPet3.getMaxHP()));
		testPet4.setHP((int)(1));
		mainP.getMegamonParty().add(testPet);
		mainP.getMegamonParty().add(testPet2);
		mainP.getMegamonParty().add(testPet3);
		mainP.getMegamonParty().add(testPet4);
		/*System.out.println(testPet);*/
		//messagebox = new DialogBox("This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.This is a test message.");
		//startmenubox = new StartMenuBox(0);
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
		if (partyscreen!=null) {
			partyscreen.run();
		} else
		if (messagebox!=null) {
			messagebox.run();
		}
		else 
		if (startmenubox!=null){
			startmenubox.run();
		}
		else {
			for (Object o : currentLevel.getObjects()) {
				o.run();
			}
			if (Gdx.input.isKeyJustPressed(Megamon.MENUKEY)) {
				Megamon.startmenubox = new StartMenuBox(0);
			}
			if (PlayerUtils.isSnappedToGrid(mainP.position)) {
				mainP.run();
			}
		}
		if (!PlayerUtils.isSnappedToGrid(mainP.position)) {
			mainP.position.setLocation(mainP.position.x+mainP.direction.x, mainP.position.y+mainP.direction.y);
			//System.out.println(position);
		}
		camera.position.set((float)mainP.position.x+0.5f, (float)mainP.position.y+0.5f, camera.position.z);
		//System.out.println("Camera position: "+camera.position);
		camera.update();
		//System.out.println(Megamon.WINDOW_WIDTH+","+Megamon.WINDOW_HEIGHT+";"+camera.viewportWidth+","+camera.viewportHeight);
		currentLevel.renderer.setView(camera);
		currentLevel.renderer.render();
		batch.begin();
		//batch.draw(img, 0, 0);

		if (partyscreen!=null) {
			partyscreen.draw(batch);
		}
		else
		{
			for (Object o : currentLevel.getObjects()) {
				batch.draw(o.sprite, 
						(float)GraphicUtils.getRelativePixelPositionFromPlayer(camera, o.position).x, 
						(float)GraphicUtils.getRelativePixelPositionFromPlayer(camera, o.position).y);
				o.draw();
			}
			mainP.draw(batch);
			if (messagebox!=null) {
				messagebox.draw(batch);
			}
			if (startmenubox!=null) {
				startmenubox.draw(batch);
			}
		}
		//GlyphLayout size = font.draw(batch, "Test Text", 0, 32);
		//font.draw(batch, "Test Text", Megamon.WINDOW_WIDTH-size.width, 128-size.height);
		batch.end();
		framesPassed++;
		if (lastCheck.getTime().getSeconds()!=Calendar.getInstance().getTime().getSeconds()) {
			System.out.println("FPS: "+framesPassed);
			framesPassed=0;
			lastCheck=Calendar.getInstance();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName()+"(");
		boolean first=true;
		for (Field f : this.getClass().getDeclaredFields()) {
			try {
				if (!first) {
					sb.append(",");
				} else {
					first=false;
				}
				sb.append(f.getName()+"="+this.getClass().getDeclaredField(f.getName()).get(this));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		sb.append(")");
		return sb.toString();
	}
}
