package sig.megamon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
	List<String> remainingMessages;
	String messageBody = "";
	String displayedMessage = "";
	int cursor=0;
	int message_split_marker=0;
	GlyphLayout layout;
	
	public DialogBox(String...messages) {
		remainingMessages=new LinkedList<String>(Arrays.asList(messages));
		this.messageBody = remainingMessages.remove(0);
	}
	
	public void run() {
		//TODO accept keyboard inputs for the dialog box.
		if (Gdx.input.isKeyJustPressed(Megamon.ACTIONKEY)) {
			System.out.println("Cursor is at position "+cursor+"/"+displayedMessage.length());
			if (cursor==displayedMessage.length()) {
				if (cursor!=messageBody.length()) {
					displayedMessage="";
					messageBody=messageBody.substring(cursor, messageBody.length());
					cursor=0;
				} else {
					if (remainingMessages.size()>0) {
						messageBody = remainingMessages.remove(0);
						displayedMessage="";
						cursor=0;
					} else {
						DestroyMessageBox();
					}
				}
			}
		}
	}
	
	private void DestroyMessageBox() {
		Megamon.messagebox=null;
	}

	public void draw(SpriteBatch batch) {
		DrawUtils.drawTextureWithColor(batch, dialog_background, (Megamon.WINDOW_WIDTH-dialog_background.getWidth())/2,0,new Color(0.5f,1f,0.5f,1f));
		DrawUtils.drawTextureWithColor(batch, dialog_box, (Megamon.WINDOW_WIDTH-dialog_box.getWidth())/2,0,new Color(0.5f,1f,0.5f,1f));
		//System.out.println(dialog_box.getTextureData().getWidth());
		layout = messageboxfont.draw(batch, displayedMessage, (Megamon.WINDOW_WIDTH-dialog_box.getWidth())/2+96, dialog_box.getHeight()-24, 0, displayedMessage.length(), 420, Align.left, true);
		if (cursor!=displayedMessage.length()) {
			cursor++;
		} else
		if (layout.height<64) {
			//Check if the next set of text with a space is not going to maximize the window.
			int checkcursor = findNextSpace(messageBody,cursor)+cursor;
			//layout.setTe
			String nextStr="";
			if (checkcursor==messageBody.length()) {
				nextStr = messageBody;
			} else {
				nextStr = messageBody.substring(0, checkcursor+1);
			}
			layout.setText(messageboxfont, nextStr, Color.BLACK, 420, Align.left, true);
			if (layout.height<64 && cursor!=messageBody.length()) {
				displayedMessage = nextStr;
				cursor++;
			}
		}
		//Megamon.font.draw(batch, messageBody, (Megamon.WINDOW_WIDTH-dialog_box.getWidth())/2-dialog_box.getWidth()/2, dialog_box.getHeight()+, start, end, targetWidth, halign, wrap);
	}

	private int findNextSpace(String messageBody, int cursor) {
		String subString=messageBody.substring(cursor, messageBody.length());
		//System.out.println("Substring is "+subString);
		for (int i=0;i<subString.length();i++) {
			System.out.println("Character at "+i+" is "+subString.charAt(i));
			if (subString.charAt(i)==' ') {
				System.out.println("Found a space at position "+i);
				return i;
			}
		}
		return subString.length();
	}
}
