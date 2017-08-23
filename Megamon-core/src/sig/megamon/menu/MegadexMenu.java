package sig.megamon.menu;

import java.awt.geom.Point2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import sig.megamon.*;

public class MegadexMenu {
	static FancyBackground background = new FancyBackground(new Texture("interface/tiledbackground.png"),new Point2D.Double(0.4, 0.2),true);
	static Texture menu = new Texture("interface/megadex.png");
	final static int TOTALMEGAMON = 151;
	int selection = 0;
	int offset_displayed_selection = 0; //The top most selection that is visible.
	int largestNumberSeen = 0;
	boolean released=true;
	int delaytimer = Megamon.CURSORDELAYTIMER*3;
	final int fontheight = 36;
	final int smallfontheight = 20;
	final int boxheight = 458;
	int lastkey = 0;
	int hpval=0;
	int atkval=0;
	int defval=0;
	int spcval=0;
	int spdval=0;
	final static int ticktime=2;
	int changedelay=ticktime;
	
	public MegadexMenu() {
		largestNumberSeen = calculateLargestNumberSeen();
	}
	
	private int calculateLargestNumberSeen() {
		for (int i=Megamon.megamonDatabase.size()-1;i>0;i--) {
			if (Megamon.megamonDatabase.get(i).isSeenByPlayer()) {
				return i;
			}
		}
		return 0;
	}

	public void run() {
		background.run();
		if (Gdx.input.isKeyJustPressed(Megamon.CANCELKEY) ||
				Gdx.input.isKeyJustPressed(Megamon.MENUKEY)) {
			Megamon.megadexscreen=null;
			return;
		}
		int keycheck = Megamon.MOVEDOWNKEY;
		if (Gdx.input.isKeyJustPressed(keycheck)) {
			released=false;
			lastkey = keycheck;
			MoveDown();
		}
		if (lastkey == keycheck) {
			if (Gdx.input.isKeyPressed(keycheck) && !released) {
				if (delaytimer--<=0) {
					MoveDown();
					delaytimer = Megamon.CURSORDELAYTIMER;
				}
			} else
			if (!Gdx.input.isKeyPressed(keycheck) && !released) {
				delaytimer = Megamon.CURSORDELAYTIMER*3;
				released=true;
			}
		}
		keycheck = Megamon.MOVEUPKEY;
		if (Gdx.input.isKeyJustPressed(keycheck)) {
			released=false;
			lastkey = keycheck;
			MoveUp();
		}
		if (lastkey == keycheck) {
			if (Gdx.input.isKeyPressed(keycheck) && !released) {
				if (delaytimer--<=0) {
					MoveUp();
					delaytimer = Megamon.CURSORDELAYTIMER;
				}
			} else
			if (!Gdx.input.isKeyPressed(keycheck) && !released) {
				delaytimer = Megamon.CURSORDELAYTIMER*3;
				released=true;
			}
		}
		keycheck = Megamon.MOVERIGHTKEY;
		if (Gdx.input.isKeyJustPressed(keycheck)) {
			released=false;
			lastkey = keycheck;
			MoveRight();
		}
		if (lastkey == keycheck) {
			if (Gdx.input.isKeyPressed(keycheck) && !released) {
				if (delaytimer--<=0) {
					MoveRight();
					delaytimer = Megamon.CURSORDELAYTIMER;
				}
			} else
			if (!Gdx.input.isKeyPressed(keycheck) && !released) {
				delaytimer = Megamon.CURSORDELAYTIMER*3;
				released=true;
			}
		}
		keycheck = Megamon.MOVELEFTKEY;
		if (Gdx.input.isKeyJustPressed(keycheck)) {
			released=false;
			lastkey = keycheck;
			MoveLeft();
		}
		if (lastkey == keycheck) {
			if (Gdx.input.isKeyPressed(keycheck) && !released) {
				if (delaytimer--<=0) {
					MoveLeft();
					delaytimer = Megamon.CURSORDELAYTIMER;
				}
			} else
			if (!Gdx.input.isKeyPressed(keycheck) && !released) {
				delaytimer = Megamon.CURSORDELAYTIMER*3;
				released=true;
			}
		}
		if (changedelay>0) {
			changedelay--;
			if (changedelay==0) {
				changedelay = ticktime;
				UpdateValues(Megamon.megamonDatabase.get(selection));
			}
		}
	}
	
	private void UpdateValues(MegamonCreature mon) {
		hpval+=(mon.getHP()>hpval)?1:(mon.getHP()<hpval)?-1:0;
		atkval+=(mon.getATK()>atkval)?1:(mon.getATK()<atkval)?-1:0;
		defval+=(mon.getDEF()>defval)?1:(mon.getDEF()<defval)?-1:0;
		spcval+=(mon.getSPC()>spcval)?1:(mon.getSPC()<spcval)?-1:0;
		spdval+=(mon.getSPD()>spdval)?1:(mon.getSPD()<spdval)?-1:0;
	}

	private void MoveRight() {
		selection = Math.min(selection+(boxheight/fontheight), largestNumberSeen);
		MenuFollowsCursor();
	}
	private void MoveLeft() {
		selection = Math.max(selection-(boxheight/fontheight), 0);
		MenuFollowsCursor();
	}

	private void MoveDown() {
		if (selection<largestNumberSeen) {
			selection++;
			MenuFollowsCursor();
		}
	}
	private void MoveUp() {
		if (selection>0) {
			selection--;
			MenuFollowsCursor();
		}
	}

	private void MenuFollowsCursor() {
		if (selection<offset_displayed_selection) {
			offset_displayed_selection = selection;
		}
		if (selection - offset_displayed_selection>=(boxheight/fontheight)) {
			offset_displayed_selection = selection - (boxheight/fontheight) + 1;
		}
	}

	public void draw(SpriteBatch batch) {
		background.draw(batch);
		batch.draw(menu, 0, 0);
		
		DrawMegamonMenu(batch);
	}

	private void DrawMegamonMenu(SpriteBatch batch) {
		//First usable pixel: (12,12)
		for (int i=0;i<boxheight/fontheight;i++) {
			if (i+offset_displayed_selection<largestNumberSeen+1) {
				MegamonCreature megamon = Megamon.megamonDatabase.get(offset_displayed_selection+i);
				Megamon.font.setColor(new Color(1.0f,0.5f,0.5f,0.9f));
				Megamon.font.draw(batch, Integer.toString(i+offset_displayed_selection+1), 20+16, Megamon.WINDOW_HEIGHT-fontheight-12-(i*fontheight)+fontheight/2, 10, Align.center, false);
				if (megamon.isCaughtByPlayer()) {
					Megamon.font.setColor(new Color(0.7f,1.0f,0.7f,1.0f));
				} else {
					Megamon.font.setColor(new Color(0.7f,0.7f,0.7f,1.0f));
				}
				String namedisplay = "  "+(megamon.isSeenByPlayer()?megamon.getName():createTwitchyName());
				Megamon.font.draw(batch, namedisplay, 32+16, Megamon.WINDOW_HEIGHT-fontheight-12-(i*fontheight)+fontheight/2, 0, namedisplay.length(), 256, Align.left, false, "...");
				Megamon.font.setColor(Color.WHITE);
				if (i+offset_displayed_selection == selection) {
					Megamon.font.setColor(new Color(0.8f,0.8f,1f,0.9f));
					Megamon.font.draw(batch, ">", 16, Megamon.WINDOW_HEIGHT-fontheight-12-(i*fontheight)+fontheight/2, 10, Align.left, false);
					Megamon.font.setColor(Color.WHITE);
					batch.draw(StartMenuBox.startmenu_highlight,16, Megamon.WINDOW_HEIGHT-fontheight-12-(i*fontheight)+fontheight/2-StartMenuBox.startmenu_highlight.getHeight()*2, StartMenuBox.startmenu_highlight.getWidth(),StartMenuBox.startmenu_highlight.getHeight()*3);
				}
			}
		}
		
		/*Image box: (332,120)
		 * Stats: (462,120)
		 * Bio: (332,150)
		 * Map: (332,468)
		 */
		MegamonCreature mon = Megamon.megamonDatabase.get(selection);
		boolean seen = mon.isSeenByPlayer();
		if (seen) {
			Texture monImage = mon.getSprites().getSprite();
			batch.draw(monImage, 332, Megamon.WINDOW_HEIGHT-120, 110, 110);
		
			int i=1;
			DialogBox.messageboxfont.draw(batch, "HP", 472, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 16, Align.center, false);
			DialogBox.messageboxfont.draw(batch, "ATK", 472, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 16, Align.center, false);
			DialogBox.messageboxfont.draw(batch, "DEF", 472, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 16, Align.center, false);
			DialogBox.messageboxfont.draw(batch, "SPC", 472, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 16, Align.center, false);
			DialogBox.messageboxfont.draw(batch, "SPD", 472, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 16, Align.center, false);
			i=1;
			/*batch.draw(Megamon.healthbar, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getHP()/600f))*Megamon.healthbar.getHeight()), 1, 1, 270, 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getHP()/600f))*Megamon.healthbar.getHeight()), false, false);
			batch.draw(Megamon.healthbar, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getATK()/600f))*Megamon.healthbar.getHeight()), 1, 1, 270, 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getATK()/600f))*Megamon.healthbar.getHeight()), false, false);
			batch.draw(Megamon.healthbar, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getDEF()/600f))*Megamon.healthbar.getHeight()), 1, 1, 270, 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getDEF()/600f))*Megamon.healthbar.getHeight()), false, false);
			batch.draw(Megamon.healthbar, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getSPC()/600f))*Megamon.healthbar.getHeight()), 1, 1, 270, 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getSPC()/600f))*Megamon.healthbar.getHeight()), false, false);
			batch.draw(Megamon.healthbar, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight), 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getSPD()/600f))*Megamon.healthbar.getHeight()), 1, 1, 270, 0, 0, Megamon.healthbar.getWidth(), Megamon.healthbar.getHeight()-(int)((1-(mon.getSPD()/600f))*Megamon.healthbar.getHeight()), false, false);*/
			if (mon.getHP()>=100) {batch.setColor(new Color(1f,0.7f,0.7f,1f));} else {batch.setColor(Color.WHITE);}
			batch.draw(Megamon.healthbar_horizontal, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight)+1-Megamon.healthbar_horizontal.getHeight(),0,0,(int)((hpval/200f)*Megamon.healthbar_horizontal.getWidth()*2),Megamon.healthbar_horizontal.getHeight());
			if (mon.getATK()>=100) {batch.setColor(new Color(1f,0.7f,0.7f,1f));} else {batch.setColor(Color.WHITE);}
			batch.draw(Megamon.healthbar_horizontal, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight)+1-Megamon.healthbar_horizontal.getHeight(),0,0,(int)((atkval/200f)*Megamon.healthbar_horizontal.getWidth()*2),Megamon.healthbar_horizontal.getHeight());
			if (mon.getDEF()>=100) {batch.setColor(new Color(1f,0.7f,0.7f,1f));} else {batch.setColor(Color.WHITE);}
			batch.draw(Megamon.healthbar_horizontal, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight)+1-Megamon.healthbar_horizontal.getHeight(),0,0,(int)((defval/200f)*Megamon.healthbar_horizontal.getWidth()*2),Megamon.healthbar_horizontal.getHeight());
			if (mon.getSPC()>=100) {batch.setColor(new Color(1f,0.7f,0.7f,1f));} else {batch.setColor(Color.WHITE);}
			batch.draw(Megamon.healthbar_horizontal, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight)+1-Megamon.healthbar_horizontal.getHeight(),0,0,(int)((spcval/200f)*Megamon.healthbar_horizontal.getWidth()*2),Megamon.healthbar_horizontal.getHeight());
			if (mon.getSPD()>=100) {batch.setColor(new Color(1f,0.7f,0.7f,1f));} else {batch.setColor(Color.WHITE);}
			batch.draw(Megamon.healthbar_horizontal, 500, Megamon.WINDOW_HEIGHT-(i++*smallfontheight)+1-Megamon.healthbar_horizontal.getHeight(),0,0,(int)((spdval/200f)*Megamon.healthbar_horizontal.getWidth()*2),Megamon.healthbar_horizontal.getHeight());
			batch.setColor(Color.WHITE);
		}
	}

	private String createTwitchyName() {
		StringBuilder sb = new StringBuilder("");
		for (int i=0;i<8;i++) {
			if (Math.random()<=0.01) {
				Character c = (char)(Character.getNumericValue('A')+(int)(Math.random()*26));
				if (Math.random()<=0.5) {
					c = (char)(Character.getNumericValue('a')+(int)(Math.random()*26));
				}
				sb.append(c);
			} else {
				sb.append("-");
			}
		}
		return sb.toString();
	}
}
