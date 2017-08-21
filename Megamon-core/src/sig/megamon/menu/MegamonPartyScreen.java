package sig.megamon.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import sig.megamon.Megamon;
import sig.megamon.MegamonPet;

public class MegamonPartyScreen {
	static Color backgroundColor = new Color(63/255f,72/255f,204/255f,1);
	static Texture party_window = new Texture("interface/megamon_party.png");
	static Texture party_window_selection = new Texture("interface/megamon_party_selection.png");
	int selection = 0;
	final static int MEGAMON_SLOT_SPACING = 140;
	final static int WINDOW_MARGIN = 140;
	int alpha=0;
	final int ALPHA_SPD=4;
	boolean alpha_dir=true;
	
	public MegamonPartyScreen() {
		
	}
	
	public void run() {
		alpha+=(alpha_dir)?ALPHA_SPD:-ALPHA_SPD;
		if (Math.abs(alpha)>=255) {
			alpha_dir=!alpha_dir;
		}
		if (Gdx.input.isKeyJustPressed(Megamon.MOVELEFTKEY)) {
			if (selection<3) {
				selection = Math.floorMod(selection-1, 3);
			} else {
				selection = Math.floorMod(selection-4, 3)+3;
			}
		}
		if (Gdx.input.isKeyJustPressed(Megamon.MOVERIGHTKEY)) {
			if (selection<3) {
				selection = Math.floorMod(selection+1, 3);
			} else {
				selection = Math.floorMod(selection-2, 3)+3;
			}
		}
		if (Gdx.input.isKeyJustPressed(Megamon.MOVEUPKEY)) {
			selection = Math.floorMod(selection-3, 6);
		}
		if (Gdx.input.isKeyJustPressed(Megamon.MOVEDOWNKEY)) {
			selection = Math.floorMod(selection+3, 6);
		}
		if (Gdx.input.isKeyJustPressed(Megamon.CANCELKEY) ||
				Gdx.input.isKeyJustPressed(Megamon.MENUKEY)) {
			Megamon.partyscreen=null;
			return;
		}
	}
	
	public void draw(SpriteBatch batch) {
		Gdx.gl.glClearColor(backgroundColor.r,backgroundColor.g,backgroundColor.b,backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		int i=0;
		batch.draw(party_window, 0, 0);
		
		batch.setColor(new Color(1,1,1,Math.abs(alpha)/255f));
		batch.draw(party_window_selection,
				WINDOW_MARGIN + ((selection%3)*MEGAMON_SLOT_SPACING)-36,
				(int)(Megamon.WINDOW_HEIGHT/(selection>2?1.9:1.1))-party_window_selection.getHeight()-32);
		batch.setColor(new Color(1,1,1,1));
		for (MegamonPet mp : Megamon.mainP.getMegamonParty()) {
			batch.draw(
					mp.getCreature().getSprites().getMiniIcon(),
					WINDOW_MARGIN+((i%3)*MEGAMON_SLOT_SPACING)+4,(int)(Megamon.WINDOW_HEIGHT/(i>2?1.9:1.1)-64));
			Megamon.font.draw(batch, "Lv", 
					WINDOW_MARGIN+((i%3)*MEGAMON_SLOT_SPACING),(int)(Megamon.WINDOW_HEIGHT/(i>2?1.9:1.1))-
					mp.getCreature().getSprites().getMiniIcon().getHeight()*2-40);
			DialogBox.messageboxfont.draw(batch, Integer.toString(mp.getLevel()), 
					WINDOW_MARGIN+((i%3)*MEGAMON_SLOT_SPACING),(int)(Megamon.WINDOW_HEIGHT/(i>2?1.9:1.1))-
					mp.getCreature().getSprites().getMiniIcon().getHeight()*2-72,24,Align.center,false);
			DialogBox.messageboxfont.draw(batch, mp.getHP()+"/"+mp.getMaxHP(), 
					WINDOW_MARGIN+((i%3)*MEGAMON_SLOT_SPACING),(int)(Megamon.WINDOW_HEIGHT/(i>2?1.9:1.1))-
					mp.getCreature().getSprites().getMiniIcon().getHeight()*2-96,24,Align.center,false);
			if ((float)mp.getHP()/mp.getMaxHP()<0.3) {
				batch.setColor(Color.RED);
			}
			batch.draw(Megamon.healthbar, 
					WINDOW_MARGIN+((i%3)*MEGAMON_SLOT_SPACING)+
					mp.getCreature().getSprites().getMiniIcon().getWidth()+32,
					(int)(Megamon.WINDOW_HEIGHT/(i>2?1.9:1.1)-48-Megamon.healthbar.getHeight()),
					0,0,Megamon.healthbar.getWidth(),Math.max(3,(int)(((float)mp.getHP()/mp.getMaxHP())*Megamon.healthbar.getHeight())));
			batch.setColor(Color.WHITE);
			Megamon.font.draw(batch, mp.getNickname(), 
					WINDOW_MARGIN+((i%3)*MEGAMON_SLOT_SPACING)-24,(int)(Megamon.WINDOW_HEIGHT/(i>2?1.9:1.1)-
					mp.getCreature().getSprites().getMiniIcon().getHeight()*2-120),0,mp.getNickname().length(),100,Align.center,true,"...");
			i++;
		}
	}
}
