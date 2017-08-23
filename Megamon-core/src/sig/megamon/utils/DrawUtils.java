package sig.megamon.utils;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sig.megamon.Megamon;

public class DrawUtils {
	public static void drawTextureWithColor(SpriteBatch batch, Texture tex, int x, int y, Color col) {
		batch.setColor(col);
		batch.draw(tex, x, y);
		batch.setColor(new Color(1,1,1,1));
	}

	public static void drawTiledImage(SpriteBatch batch, Texture image, Point2D.Double offset) {
		int tileAmtX = (int)Math.ceil((double)Megamon.WINDOW_WIDTH/image.getWidth());
		int tileAmtY = (int)Math.ceil((double)Megamon.WINDOW_HEIGHT/image.getHeight());
		//int calls = 0;
		for (int j=-1;j<tileAmtY+2;j++) {
			for (int i=-1;i<tileAmtX+2;i++) {
				//calls++;
				batch.draw(image, (int)((offset.x%image.getWidth())+(i*image.getWidth())), (int)((offset.y%image.getHeight())+(j*image.getHeight())));
			}
		}
		//System.out.println("Draw "+calls+" times.");
	}
}
