package sig.megamon.ref;

import java.awt.geom.Point2D;

import sig.megamon.Megamon;

public class RoomRef extends Ref{
	Point2D.Double doorStartingPosition;
	String roomName;
	public Point2D.Double doorDestination;
	public String destinationRoom;
	
	public RoomRef(Point2D.Double position, String room) {
		super(position,room);
		this.doorStartingPosition=position;
		this.roomName=room;
	}
	
	public RoomRef(Point2D.Double doorStartingPosition, String startingRoom, Point2D.Double doorDestination, String destinationRoom) {
		super(doorStartingPosition,startingRoom);
		doorDestination.y=99-doorDestination.y;
		this.doorStartingPosition=doorStartingPosition;
		this.roomName=startingRoom;
		this.doorDestination=doorDestination;
		this.destinationRoom=destinationRoom;
		Megamon.doorDatabase.put(generateHash(), this);
	}
	
	public RoomRef addInverseDoorLink() {
		doorDestination.y=99-doorDestination.y;
		doorStartingPosition.y=99-doorStartingPosition.y;
		return new RoomRef(doorDestination,destinationRoom,doorStartingPosition,roomName);
	}
}
