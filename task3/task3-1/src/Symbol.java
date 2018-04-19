
public class Symbol {
	private static final int SYMBOL_CASE_UPPER= 1;
	private static final int SYMBOL_CASE_LOWER= 2;
	
	private int symbolCase = Symbol.SYMBOL_CASE_LOWER;
	private char symbolVal;

	Symbol(char s) {
		this.symbolVal = s;
		if (Character.isUpperCase(s)) {
			this.symbolCase = Symbol.SYMBOL_CASE_UPPER;
		}
	}

	public char toUpperCase() {
		this.symbolCase = Symbol.SYMBOL_CASE_UPPER;
		return Character.toUpperCase(this.symbolVal);
	}
}