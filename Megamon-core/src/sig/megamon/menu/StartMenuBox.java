package sig.megamon.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sig.megamon.Megamon;

public class StartMenuBox {
	static Texture startmenu_background = new Texture("startmenu_box_middle.png");
	static Texture startmenu_background_bottom = new Texture("startmenu_box_bottom.png");
	static Texture startmenu_background_top = new Texture("startmenu_box.png");
	public static Texture startmenu_highlight = new Texture("startmenu_highlight.png");
	int selection=0;
	MenuItem[] menuitems = MenuItem.values();
	int menuitem_spacing = 28;
	
	public StartMenuBox(int cursorStartingPosition) {
		this.selection=cursorStartingPosition;
	}
	
	public void run() {
		if (Gdx.input.isKeyJustPressed(Megamon.MOVEUPKEY)) {
			selection = Math.floorMod(selection-1, menuitems.length);
		}
		if (Gdx.input.isKeyJustPressed(Megamon.MOVEDOWNKEY)) {
			selection = Math.floorMod(selection+1, menuitems.length);
		}
		if (Gdx.input.isKeyJustPressed(Megamon.MENUKEY)) {
			Megamon.startmenubox=null;
			return;
		}
		if (Gdx.input.isKeyJustPressed(Megamon.ACTIONKEY)) {
			switch (menuitems[selection]) {
				case MEGADEX:
					Megamon.megadexscreen = new MegadexMenu();
					break;
				case MEGAMON:
					Megamon.partyscreen = new MegamonPartyScreen();
					break;
				case BAG:
					break;
				case TRAINER:
					break;
				case OPTIONS:
					break;
				case SAVE:
					break;
				case EXIT:
					Megamon.startmenubox = null;
					break;
			}
		}
		if (Gdx.input.isKeyJustPressed(Megamon.CANCELKEY)) {
			Megamon.startmenubox=null;
		}
	}
	
	public void draw(SpriteBatch batch) {
		int windowx = Megamon.WINDOW_WIDTH-startmenu_background_top.getWidth();
		int windowy = Megamon.WINDOW_HEIGHT-startmenu_background_top.getHeight();
		int spacingpixels = menuitem_spacing*menuitems.length-startmenu_background_bottom.getHeight();
		int menubot = windowy-spacingpixels;
		batch.draw(startmenu_background_top, windowx,windowy);
		batch.draw(startmenu_background, windowx, windowy-spacingpixels, 0,0,startmenu_background.getWidth(),
				spacingpixels);
		batch.draw(startmenu_background_bottom, windowx,menubot-startmenu_background_bottom.getHeight());
		int i=0;
		for (MenuItem s : menuitems) {
			if (i==selection) {
				batch.draw(startmenu_highlight, windowx-4, windowy-((i+1)*menuitem_spacing)+8);
			}
			DialogBox.messageboxfont.draw(batch, s.getDisplayText(), windowx+28, windowy-(i++*menuitem_spacing));
		}
		Megamon.font.draw(batch, ">", windowx+8, windowy-(selection*menuitem_spacing)+8);
	}
}
