import java.io.Serializable;

public class BingoBall implements Serializable {
    public int number;		
    private char letter;

    public static final int FREE_SPACE = -1;
    public static final int GAME_OVER = -2;

    public static final int MIN = 1;
    public static final int MAX = 75;

    public static final int RANGE = 15;

    public static final int MAX_B = 15;
    public static final int MAX_I = 30;
    public static final int MAX_N = 45;
    public static final int MAX_G = 60;
    public static final int MAX_O = 75;

    public BingoBall(byte[] b) {
	this(b[0]);
    }
    
//o arithos prepei na einai anamesa sto 1 kai to 75
    
    public BingoBall(int n) {
	if (n >= MIN && n <= MAX) {
	    number = n;
            //vazw ta grammata
	    if (n <= MAX_B) letter = 'B';
	    else if (n <= MAX_I) letter = 'I';
	    else if (n <= MAX_N) letter = 'N';
	    else if (n <= MAX_G) letter = 'G';
	    else if (n <= MAX_O) letter = 'O';
	} else if (n == GAME_OVER) {
	    number = GAME_OVER;
	    letter = ' ';
	} else {
	    number = FREE_SPACE;
	    letter = ' ';
	}
    }

    public boolean equals(Object obj) {
	if (!(obj instanceof BingoBall))
	    return false;
	return ((BingoBall)obj).number == number;
    }
//pernei ta grammata kai tous arithous

    public int getNumber() {
	return number;
    }
    public char getLetter() {
	return letter;
    }
//ta emfanizei
    public String toString() {
	return new StringBuffer().append(letter).append(number).toString();
    }
}