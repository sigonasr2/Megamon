package sig.megamon.utils;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

import sig.megamon.Megamon;

public class GraphicUtils {
	public static double getAspectRatio() {
		return (double)Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
	}
	public static Point2D.Double getTileToPixel(Camera camera, Point2D.Double position) {
		return new Point2D.Double((int)(Gdx.graphics.getWidth()/camera.viewportWidth*(position.getX()-0.5)),(int)(Gdx.graphics.getHeight()/camera.viewportHeight*(position.getY()/*-0.5*/)));
	}
	public static Point2D.Double getPlayerPixelPosition(Camera camera) {
		return getTileToPixel(camera,new Point2D.Double((int)(camera.viewportWidth/2),(int)(camera.viewportHeight/2)));
	}
	public static Point2D.Double getTileSize(Camera camera) {
		return new Point2D.Double((int)(Gdx.graphics.getWidth()/camera.viewportWidth),(int)(Gdx.graphics.getHeight()/camera.viewportHeight));
	}
	public static Point2D.Double getRelativePixelPositionFromPlayer(Camera camera,Point2D.Double position) {
		Point2D.Double pixelpos = getTileToPixel(camera,position);
		Point2D.Double playerpos = getTileToPixel(camera,Megamon.position);
		Point2D.Double playerpixelpos = getPlayerPixelPosition(camera);
		return new Point2D.Double(playerpixelpos.x+(pixelpos.x-playerpos.x), playerpixelpos.y+(pixelpos.y-playerpos.y));
	}
}
