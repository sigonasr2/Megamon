package sig.megamon;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import sig.megamon.utils.GraphicUtils;
import sig.megamon.utils.PlayerUtils;

public class Megamon extends ApplicationAdapter{
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	//AssetManager assets;
	TiledMap map;
	OrthogonalTiledMapRenderer render;
	OrthographicCamera camera;
	final public static int TILESIZE=16;
	final public static double CHAR_SPD=0.1;
	Point2D.Double direction = new Point2D.Double(0,0);
	final public static Point2D.Double position=new Point2D.Double(1,1);
	Calendar lastCheck = Calendar.getInstance();
	int framesPassed=0;
	final public static String HIDDENLAYERNAME = "Tile Layer 2";
	public static List<Object> objects = new ArrayList<Object>();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("megamon_icon64.png");
		font = new BitmapFont(Gdx.files.internal("fonts/AgencyFB.fnt"));
		//assets = new AssetManager();
		
		/*assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assets.load("tilesets/Test Map.tmx",TiledMap.class);
		map = assets.get("tilesets/Test Map.tmx");*/
		map = new TmxMapLoader().load("tilesets/Test Map.tmx");
		map.getLayers().get(HIDDENLAYERNAME).setVisible(false);
		render = new OrthogonalTiledMapRenderer(map,1/(float)TILESIZE);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 20, (float)(20*(GraphicUtils.getAspectRatio())));
		//System.out.println("Camera position: "+camera.position);
		objects.add(new Object("MEGASPINNN.png",new Point2D.Double(0, 4)));
		objects.add(new Object("megamonlogowip.png",new Point2D.Double(3, 7)));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for (Object o : objects) {
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
				if (PlayerUtils.isLocationPassable(map,new Point2D.Double(position.x+Math.signum(direction.x),position.y+Math.signum(direction.y)))) {
					position.setLocation(position.x+direction.x, position.y+direction.y);
				} else {
					//We hit a wall.
				}
			}
		} else {
			position.setLocation(position.x+direction.x, position.y+direction.y);
			//System.out.println(position);
		}
		camera.position.set((float)position.x+0.5f, (float)position.y+0.5f, camera.position.z);
		//System.out.println("Camera position: "+camera.position);
		camera.update();
		render.setView(camera);
		render.render();
		batch.begin();
		//batch.draw(img, 0, 0);
		for (Object o : objects) {
			batch.draw(o.sprite, 
					(float)GraphicUtils.getRelativePixelPositionFromPlayer(camera, o.position).x, 
					(float)GraphicUtils.getRelativePixelPositionFromPlayer(camera, o.position).y);
			o.draw();
		}
		batch.draw(img, (int)GraphicUtils.getPlayerPixelPosition(camera).getX(), (int)GraphicUtils.getPlayerPixelPosition(camera).getY(), (int)GraphicUtils.getTileSize(camera).getX(), (int)GraphicUtils.getTileSize(camera).getY());
		GlyphLayout size = font.draw(batch, "Test Text", 128, 128);
		font.draw(batch, "Test Text", Gdx.graphics.getWidth()-size.width, 128-size.height);
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
}
