package sig.megamon.ref;

import java.awt.geom.Point2D.Double;

import sig.megamon.Megamon;

public class SignRef extends Ref{
	String[] messages;

	public SignRef(Double position, String room, String...messages) {
		super(position, room);
		Megamon.infoDatabase.put(generateHash(),this);
		this.messages=messages;
	}
	
	public String getRoom() {
		return roomName;
	}
	
	public String[] getMessages() {
		return messages;
	}
 }
