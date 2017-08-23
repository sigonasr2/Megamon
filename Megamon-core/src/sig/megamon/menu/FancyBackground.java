package sig.megamon.menu;

import java.awt.geom.Point2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sig.megamon.utils.DrawUtils;

public class FancyBackground {
	Point2D.Double offset = new Point2D.Double(0, 0);
	Point2D.Double scrollspd = new Point2D.Double(0, 0);
	Texture scrollbackground;
	ColorCycler cycler;
	Color color;
	
	public FancyBackground(Texture background, Point2D.Double scrollspd, boolean cycleColors) {
		this.scrollbackground=background;
		this.scrollspd=scrollspd;
		if (cycleColors) {
			cycler = new ColorCycler(new Color(1,0,0,1),10);
		} else {
			color = new Color(0,0,0.5f,1);
		}
	}
	
	public FancyBackground(Texture background, Point2D.Double scrollspd, Color staticColor) {
		this(background,scrollspd,false);
		this.color = staticColor;
	}
	
	public void run() {
		if (cycler!=null) {
			cycler.run();
		}
		offset.setLocation(offset.x+scrollspd.x, offset.y+scrollspd.y);
	}
	
	public void draw(SpriteBatch batch) {
		if (cycler!=null) {
			batch.setColor(cycler.getCycleColor());
		} else {
			batch.setColor(color);
		}
		DrawUtils.drawTiledImage(batch, scrollbackground, offset);
		batch.setColor(new Color(1,1,1,1));
	}
}
