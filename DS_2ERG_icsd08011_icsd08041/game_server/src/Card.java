import java.util.Random;
import java.util.Vector;
import java.util.Stack;
import java.io.Serializable;
import java.security.*;

//klasi gia tin dimiourgia stis kartes

public class Card implements Serializable {
    public static final int SIZE = 5;
    public static char[] columnTitles = { 'B', 'I', 'N', 'G', 'O' };
    public BingoBall[][] boardValues = new BingoBall[SIZE][SIZE];


    public Card() {
	this(new Random(System.currentTimeMillis()));
    }

    public Card(Random generator) {
	int min=0, max=0;

	for (int i = 0; i < SIZE; i ++) {
            int numBalls = BingoBall.RANGE;
            Vector balls = new Vector(numBalls);
	    Stack randomBalls = new Stack();

	    switch (i) {
	    case 0:
		min = BingoBall.MIN;
		max = BingoBall.MAX_B;
		break;
	    case 1:
		min = max + 1;
		max = BingoBall.MAX_I;
		break;
	    case 2:
		min = max + 1;
		max = BingoBall.MAX_N;
		break;
	    case 3:
		min = max + 1;
		max = BingoBall.MAX_G;
		break;
	    case 4:
		min = max + 1;
		max = BingoBall.MAX_O;
		break;
	    }
           
            //genitria gia tis 15 mpales
             
            for (int j = min; j <= max; j++) {
                balls.addElement(new BingoBall(j));
	    }

               //tis tyxaiopei
            for (int k = BingoBall.RANGE - 1; k >= 0; k--) {
                int num = (int)(generator.nextDouble() * (k+1));
                randomBalls.push(balls.elementAt(num));
                balls.removeElementAt(num);
            }

            //dialegei 5 gia na balei stin karta
	    for (int j = 0; j < SIZE; j ++) {
		boardValues[j][i] = (BingoBall)randomBalls.pop();
	    }
	}

	boardValues[2][2] = new BingoBall(BingoBall.FREE_SPACE); //dimiougei to kouti free

    }

}