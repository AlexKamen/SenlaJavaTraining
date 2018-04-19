
public class Word {
	private String fullWord;
	private Symbol wordFirstPart;
	private String wordSecondPart;
	
	Word (String w) {
		this.fullWord = w;
		this.wordFirstPart = new Symbol(w.charAt(0));
		this.wordSecondPart = w.substring(1);
	}

	public String getNewWord() {
		String newWord = this.wordFirstPart.toUpperCase() + this.wordSecondPart;
		return newWord;
	}
}
