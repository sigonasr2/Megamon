package sig.megamon;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sig.megamon.menu.DialogBox;
import sig.megamon.ref.RoomRef;
import sig.megamon.ref.SignRef;
import sig.megamon.utils.GraphicUtils;
import sig.megamon.utils.PlayerUtils;
import sig.megamon.utils.TrainerUtils;

public class Player {

	public Point2D.Double position = new Point2D.Double(1,1);
	Point2D.Double direction = new Point2D.Double(0,0);
	Point2D.Double lastdirection = new Point2D.Double(0,1);
	Texture sprite;
	List<MegamonPet> megamon_party = new ArrayList<MegamonPet>();
	int trainer_id;
	String name;
	
	public Player(Point2D.Double position, String sprite) {
		this.position=position;
		this.sprite=new Texture(sprite);
		this.trainer_id = TrainerUtils.getRandomTrainerID();
		this.name = "Rob";
	}
	
	public void run() {
		direction=new Point2D.Double(0, 0);
		if (Gdx.input.isKeyPressed(Megamon.MOVELEFTKEY)) {
			direction=lastdirection=new Point2D.Double(-Megamon.CHAR_SPD,0);
		} else
		if (Gdx.input.isKeyPressed(Megamon.MOVEUPKEY)) {
			direction=lastdirection=new Point2D.Double(0,Megamon.CHAR_SPD);
		} else 
		if (Gdx.input.isKeyPressed(Megamon.MOVERIGHTKEY)) {
			direction=lastdirection=new Point2D.Double(Megamon.CHAR_SPD,0);
		} else
		if (Gdx.input.isKeyPressed(Megamon.MOVEDOWNKEY)) {
			direction=lastdirection=new Point2D.Double(0,-Megamon.CHAR_SPD);
		}
		Point2D.Double destinationposition = new Point2D.Double(position.x+Math.signum(lastdirection.x),position.y+Math.signum(lastdirection.y));
		if (Gdx.input.isKeyJustPressed(Megamon.ACTIONKEY)) {
			CheckForInfo(destinationposition);
		}
		if (direction.x!=0 || direction.y!=0) {
			//System.out.println("("+position.x+","+Math.signum(direction.x)+","+position.y+","+Math.signum(direction.y)+")");
			//Point2D.Double destinationposition = new Point2D.Double(position.x+Math.signum(direction.x),position.y+Math.signum(direction.y));
			if (PlayerUtils.isLocationPassable(Megamon.currentLevel.getMap(),destinationposition)) {
				position.setLocation(position.x+direction.x, position.y+direction.y);
			} else {
				//We hit a wall.
			}
			//System.out.println(Megamon.infoDatabase.keySet());
			CheckForDoor(destinationposition);
		}
	}

	private void CheckForDoor(Point2D.Double destinationposition) {
		if (Megamon.doorDatabase.containsKey(PlayerUtils.getDoorPositionHash(destinationposition))) {
			//System.out.println("This is a door!");
			RoomRef door = Megamon.doorDatabase.get(PlayerUtils.getDoorPositionHash(destinationposition));
			if (!door.destinationRoom.equalsIgnoreCase(Megamon.currentLevel.mapName)) {
				Megamon.currentLevel.destroy();
				Megamon.currentLevel = new Room(door.doorDestination,door.destinationRoom);
			} else {
				position.setLocation(door.doorDestination);
			}
		}
	}

	private void CheckForInfo(Point2D.Double destinationposition) {
		if (Megamon.infoDatabase.containsKey(PlayerUtils.getDoorPositionHash(destinationposition))) {
			//System.out.println("This is a door!");
			SignRef info = Megamon.infoDatabase.get(PlayerUtils.getDoorPositionHash(destinationposition));
			//TODO Do interface stuff here.
			Megamon.messagebox = new DialogBox(info.getMessages());
		}
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(sprite, (int)GraphicUtils.getPlayerPixelPosition(Megamon.camera).getX(), (int)GraphicUtils.getPlayerPixelPosition(Megamon.camera).getY(), (int)GraphicUtils.getTileSize(Megamon.camera).getX(), (int)GraphicUtils.getTileSize(Megamon.camera).getY());
	}
}
