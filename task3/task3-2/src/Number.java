import java.util.Random;

public class Number {
	private int digit1;
	private int digit2;
	private int digit3;
	private int randNum;

	public int generateNumber() {
		Random rand = new Random();
		this.randNum = rand.nextInt(899) + 100;
		return this.randNum;
	}

	private void decomposeNumber() {
		this.digit1 = this.randNum / 100;
		int num2 = this.randNum % 100;
		this.digit2 = num2 / 10;
		this.digit3 = num2 % 10;
	}

	public int getDigitsSum() {
		this.decomposeNumber();
		return this.digit1 + this.digit2 + this.digit3;
	}
}
