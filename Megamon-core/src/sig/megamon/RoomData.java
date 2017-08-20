package sig.megamon;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RoomData {
	MAP_1("Test Map",
			Arrays.asList(
				new Object("MEGASPINNN.png",new Point2D.Double(0, 4)),
				new Object("megamonlogowip.png",new Point2D.Double(3, 7))
			)),
	MAP_2("Test Map 2",
			new ArrayList<Object>()),
	MAP_3("Test Map 3",
			new ArrayList<Object>());
	
	String map;
	List<Object> objList;
	
	RoomData(String map, List<Object> objList) {
		this.map=map;
		this.objList=objList;
	}
	
	public static RoomData getRoomData(String str) {
		for (RoomData rd : RoomData.values()) {
			if (rd.map.equalsIgnoreCase(str)) {
				return rd;
			}
		}
		System.out.println("WARNING! Could not find map with name "+str+"! This should not be happening!");
		return null;
	}
	
	public List<Object> load() {
		/*List<Object> objs = new ArrayList<Object>();
		switch (this) {
			case MAP_1:
					;
					;
				break;
			case MAP_2:
				
				break;
			case MAP_3:
				
				break;
			default:
					System.out.println("WARNING! Could not find map "+this.name()+"! Could not load object data.");
				break;
		}*/
		return objList;
	}
}
