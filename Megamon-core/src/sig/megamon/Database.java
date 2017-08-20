package sig.megamon;

import java.awt.geom.Point2D;

import sig.megamon.ref.RoomRef;
import sig.megamon.ref.SignRef;

public class Database {

	public static void SetupDoorDatabase() {
		new RoomRef(
				new Point2D.Double(9, 95),
				"Test Map",
				new Point2D.Double(7, 98),
				"Test Map 2"
				).addInverseDoorLink();
	}
	
	
	public static void SetupInfoDatabase() {
		new SignRef(
				new Point2D.Double(9,99),
				"Test Map",
				new String[]{
						"Hello there! You have found the invisible space that happens to be a dialog box.",
						"\n\nCongratulations~!"
				}
				);
	}
}
