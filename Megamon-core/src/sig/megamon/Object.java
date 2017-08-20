package sig.megamon;

import java.awt.geom.Point2D;

import com.badlogic.gdx.graphics.Texture;

public class Object {
	Point2D.Double position;
	Texture sprite;
	boolean passable=false;
	boolean visible=true;
	public Object(String sprite_img, Point2D.Double position) {
		this(sprite_img,position,false);
	}
	
	public Object(String sprite_img, Point2D.Double position, boolean passable) {
		this.sprite = new Texture(sprite_img);
		this.position = position;
		this.passable = passable;
	}
	
	public void run() {
		
	}
	
	public void draw() {
		
	}
	
	public void setVisible(boolean isVisible) {
		this.visible=isVisible;
	}
	
	/**
	 * Destroys the object, freeing all resources.
	 */
	public void destroy() {
		//sprite.dispose();
		//Megamon.objects.remove(this);
		Megamon.currentLevel.removeObject(this);
	}
}
