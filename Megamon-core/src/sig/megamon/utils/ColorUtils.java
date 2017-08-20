package sig.megamon.utils;

import com.badlogic.gdx.graphics.Color;

public class ColorUtils {
	/**
	 * Converts a hexadecimal color to internal Color.
	 * @param hex Hex format is: #XXXXXX
	 * @return
	 */
	public static Color convertHexadecimalToColor(String hex) {
		String r = hex.substring(1, 3);
		String g = hex.substring(3, 5);
		String b = hex.substring(5, 7);
		System.out.println("Colors: "+r+","+g+","+b);
		float r_col = ConvertHexStringToDecimal(r);
		float g_col = ConvertHexStringToDecimal(g);
		float b_col = ConvertHexStringToDecimal(b);
		System.out.println("Colors: "+r_col+","+g_col+","+b_col);
		return new Color(r_col/255,g_col/255,b_col/255,1);
	}

	private static float ConvertHexStringToDecimal(String b) {
		int hexPlaceValue = 0;
		long total = 0;
		for (int i=b.length()-1;i>=0;i--) {
			Character letter = b.charAt(i);
			total += ConvertHexadecimalLetterToNumber(letter)*((hexPlaceValue>0)?(hexPlaceValue*16):1);
			hexPlaceValue++;
		}
		return total; 
	}

	private static int ConvertHexadecimalLetterToNumber(Character letter) {
		switch (letter) {
			case '0':return 0;
			case '1':return 1;
			case '2':return 2;
			case '3':return 3;
			case '4':return 4;
			case '5':return 5;
			case '6':return 6;
			case '7':return 7;
			case '8':return 8;
			case '9':return 9;
			case 'a':
			case 'A':return 10;
			case 'b':
			case 'B':return 11;
			case 'c':
			case 'C':return 12;
			case 'd':
			case 'D':return 13;
			case 'e':
			case 'E':return 14;
			case 'f':
			case 'F':return 15;
		}
		System.out.println("Could not interpret Hexadecimal character "+letter+"! This should not be happening!");
		return -1;
	}
}
