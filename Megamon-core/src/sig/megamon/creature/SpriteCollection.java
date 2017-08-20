package sig.megamon.creature;

import com.badlogic.gdx.graphics.Texture;

/**
 * A collection of 3 images that represents each Megamon.
 * One is the overworld/mini icon when looking at stats of the Megamon.
 * One is the forward-facing sprite that players see when facing the Megamon.
 * One if the backward-facing sprite that players see when they send out their Megamon.
 *
 */
public class SpriteCollection {
	final static String MEGAMON_ASSETS = "Megamon/";
	Texture mini_icon;
	Texture sprite;
	Texture back_sprite;
	/**
	 * Provide the file names of the sprites relative to the 'Megamon' assets folder.
	 */
	public SpriteCollection(String mini_icon, String sprite, String back_sprite) {
		this.mini_icon = new Texture(MEGAMON_ASSETS+mini_icon); 
		this.sprite = new Texture(MEGAMON_ASSETS+sprite);
		this.back_sprite = new Texture(MEGAMON_ASSETS+back_sprite);
	}
	
	public Texture getMiniIcon() {
		return mini_icon;
	}
	public Texture getSprite() {
		return sprite;
	}
	public Texture getBackSprite() {
		return back_sprite;
	}
}
