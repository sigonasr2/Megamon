package sig.megamon.utils;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import sig.megamon.Megamon;

public class PlayerUtils {
	public static boolean isSnappedToGrid(Point2D.Double coords) {
		//System.out.println(Math.round((coords.x%1)*10)%10+":"+Math.round((coords.y%1)*10)%10);
		return Math.round((coords.x%1)*10)%10==0&&Math.round((coords.y%1)*10)%10==0;
	}

	public static boolean isLocationPassable(TiledMap map, Double coords) {
		//System.out.println("Checking coords "+Math.round(coords.x)+","+Math.round(coords.y));
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(Megamon.HIDDENLAYERNAME);
		//TiledMapTileLayer layer2 = (TiledMapTileLayer)map.getLayers().get("Tile Layer 1");
		int targetx = (int)Math.round(coords.x);
		int targety = (int)Math.round(coords.y);
		/*if (layer.getCell(targetx, targety)!=null) {
			System.out.println(layer.getCell(targetx,targety).getTile().getId());
		}*/
		return layer.getCell(targetx,targety)==null ||
				(layer.getCell(targetx,targety).getTile().getId()!=90 &&
				layer.getCell(targetx,targety).getTile().getId()!=91);
	}
	
	public static String getDoorPositionHash(Point2D.Double position) {
		//position = getRoundedPosition(position);
		String hash = Megamon.currentLevel.getMapName()+"_"+(int)Math.round(position.getX())+","+(int)Math.round(position.getY());
		//System.out.println("Getting hash "+hash);
		return hash;
	}
	
	public static Point2D.Double getRoundedPosition(Point2D.Double position) {
		position.setLocation((int)Math.round(position.x), (int)Math.round(position.y));
		return position;
	}
}
