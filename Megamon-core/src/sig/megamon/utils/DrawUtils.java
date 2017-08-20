package sig.megamon.utils;

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
}
