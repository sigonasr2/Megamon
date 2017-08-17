package sig.megamon;

import java.io.File;

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

public class Megamon extends ApplicationAdapter{
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	//AssetManager assets;
	TiledMap map;
	OrthogonalTiledMapRenderer render;
	OrthographicCamera camera;
	int x=0,y=0;
	
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
		render = new OrthogonalTiledMapRenderer(map,1/16f);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 20, 20);
		//System.out.println("Camera position: "+camera.position);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			x-=1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			y+=1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			x+=1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			y-=1;
		}
		camera.position.set(x, y, camera.position.z);
		System.out.println("Camera position: "+camera.position);
		camera.update();
		render.setView(camera);
		render.render();
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.draw(img, 40, 40, 64, 64);
		GlyphLayout size = font.draw(batch, "Test Text", 128, 128);
		font.draw(batch, "Test Text", Gdx.graphics.getWidth()-size.width, 128-size.height);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
