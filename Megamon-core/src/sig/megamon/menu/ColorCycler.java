package sig.megamon.menu;

import com.badlogic.gdx.graphics.Color;

public class ColorCycler {
	double r = 0;
	double g = 0;
	double b = 0;
	double cyclespd = 0;
	int stage = 1; //1 = Green+, 2 = Red-, 3 = Blue+, 4 = Green-, 5 = Red+, 6 = Blue-
	
	public ColorCycler(Color startingColor, double cyclespd) {
		this.r = startingColor.r;
		this.g = startingColor.g;
		this.b = startingColor.b;
		this.cyclespd=cyclespd;
	}
	
	public void run() {
		switch (stage) {
			case 1:{
				if (g<255) {
					g=Math.min(255, g+cyclespd);
				} else {
					stage++;
				}
			}break;
			case 2:{
				if (r>0) {
					r=Math.max(0, r-cyclespd);
				} else {
					stage++;
				}
			}break;
			case 3:{
				if (b<255) {
					b=Math.min(255, b+cyclespd);
				} else {
					stage++;
				}
			}break;
			case 4:{
				if (g>0) {
					g=Math.max(0, g-cyclespd);
				} else {
					stage++;
				}
			}break;
			case 5:{
				if (r<255) {
					r=Math.min(255, r+cyclespd);
				} else {
					stage++;
				}
			}break;
			case 6:{
				if (b>0) {
					b=Math.max(0, b-cyclespd);
				} else {
					stage=1;
				}
			}break;
		}
	}
	
	public Color getCycleColor() {
		return new Color((float)(r/255),(float)(g/255),(float)(b/255),1f);
	}
}
