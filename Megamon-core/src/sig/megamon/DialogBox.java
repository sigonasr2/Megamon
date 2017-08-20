package sig.megamon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import sig.megamon.utils.DrawUtils;

public class DialogBox {
	static Texture dialog_background = new Texture("dialog.png");
	static Texture dialog_box = new Texture("dialog_box.png");
	static BitmapFont messageboxfont = new BitmapFont(Gdx.files.internal("fonts/messageboxfont.fnt"));
	String messageBody = "";
	String displayedMessage = "";
	int cursor=0;
	int message_split_marker=0;
	GlyphLayout layout;
	
	public DialogBox(String messageBody) {
		this.messageBody=messageBody;
	}
	
	public void run() {
		//TODO accept keyboard inputs for the dialog box.
		/*if (layout!=null) {
			if (layout.height<64 && cursor<messageBody.length()) {
				cursor++;
			}
		}*/
	}
	
	public void draw(SpriteBatch batch) {
		DrawUtils.drawTextureWithColor(batch, dialog_background, (Megamon.WINDOW_WIDTH-dialog_background.getWidth())/2,0,new Color(0.5f,1f,0.5f,1f));
		DrawUtils.drawTextureWithColor(batch, dialog_box, (Megamon.WINDOW_WIDTH-dialog_box.getWidth())/2,0,new Color(0.5f,1f,0.5f,1f));
		//System.out.println(dialog_box.getTextureData().getWidth());
		layout = messageboxfont.draw(batch, "Can we display\nNewline characters?", (Megamon.WINDOW_WIDTH-dialog_box.getWidth())/2+96, dialog_box.getHeight()-24, 0, 30, 420, Align.left, true);
		if (layout.height<64) {
			//Check if the next set of text with a space is not going to maximize the window.
			cursor++;
			//layout.setTe
		}
		//Megamon.font.draw(batch, messageBody, (Megamon.WINDOW_WIDTH-dialog_box.getWidth())/2-dialog_box.getWidth()/2, dialog_box.getHeight()+, start, end, targetWidth, halign, wrap);
	}
}
