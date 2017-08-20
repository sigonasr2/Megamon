package sig.megamon.ref;

import java.awt.geom.Point2D;

public class Ref {
	Point2D.Double position;
	String roomName;
	public Ref(Point2D.Double position, String room) {
		position.y=99-position.y;
		this.position=position;
		this.roomName=room;
	}
	
	public String generateHash() {
		return roomName+"_"+(int)position.getX()+","+(int)position.getY();
	}
}
