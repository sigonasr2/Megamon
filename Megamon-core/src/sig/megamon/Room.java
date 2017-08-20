package sig.megamon;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import sig.megamon.utils.ColorUtils;

public class Room {
	Point2D.Double startingLocation;
	TiledMap map;
	OrthogonalTiledMapRenderer renderer;
	List<Object> objects = new ArrayList<Object>();
	final static String MAPFOLDER = "tilesets/";
	String mapName;
	Color backgroundColor;
	
	/**
	 * 
	 * @param startingLoc
	 * @param mapFileRef The map file name by itself with the extension or other subdirectories.
	 */
	public Room(Point2D.Double startingLoc, String mapFileRef) {
		this.startingLocation=startingLoc;
		Megamon.mainP.position.setLocation(startingLoc);
		this.map = new TmxMapLoader().load(MAPFOLDER+mapFileRef+".tmx");
		this.map.getLayers().get(Megamon.HIDDENLAYERNAME).setVisible(false);
		renderer = new OrthogonalTiledMapRenderer(this.map,1/(float)Megamon.TILESIZE);
		this.mapName=mapFileRef;
		//System.out.println(map.getProperties().getKeys());
		/*Iterator<String> properties = map.getProperties().getKeys();
		while (properties.hasNext()) {
			System.out.println("Key: "+properties.next());
		}*/
		if (map.getProperties().containsKey("backgroundcolor")) {
			backgroundColor = ColorUtils.convertHexadecimalToColor((String)map.getProperties().get("backgroundcolor"));
		} else {
			backgroundColor = new Color(0,0,0,1);
		}
		LoadRoomData(mapFileRef,objects);
	}
	
	public String getMapName() {
		return mapName;
	}
	
	private void LoadRoomData(String mapName, List<Object> objects) {
		RoomData rd = RoomData.getRoomData(mapName);
		objects.addAll(rd.load());
	}

	public List<Object> getObjects() {
		return objects;
	}
	
	public void addObject(Object obj) {
		objects.add(obj);
	}
	
	public void addObject(List<Object> obj) {
		objects.addAll(obj);
	}
	
	public boolean removeObject(Object obj) {
		return objects.remove(obj);
	}
	
	public TiledMap getMap() {
		return map;
	}
	
	public OrthogonalTiledMapRenderer getRenderer() {
		return renderer;
	}
	
	public Color getColor() {
		return backgroundColor;
	}
	
	/**
	 * Frees all resources associated with this room.
	 */
	public void destroy() {
		objects.clear();
		map.dispose();
		renderer.dispose();
	}
}
