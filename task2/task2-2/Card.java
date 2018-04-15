class Card {
    protected Long number;
	protected String expire;
	protected int pin;
	protected String cardholder;
	protected String codeWord;

	Card (Long n, String e, int p, String ch, String cw) {
		number = n;
		expire = e;
		pin = p;
		cardholder = ch;
		codeWord = cw;
		
		System.out.println(this.getClass().getSimpleName()+" object created");
	}
}