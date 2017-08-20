package sig.megamon.ref;

import java.awt.geom.Point2D.Double;

import sig.megamon.Megamon;

public class SignRef extends Ref{
	String message;

	public SignRef(Double position, String room) {
		super(position, room);
		Megamon.infoDatabase.put(generateHash(),this);
	}
	
	public String getRoom() {
		return roomName;
	}
	
	public String getMessage() {
		return message;
	}
 }
