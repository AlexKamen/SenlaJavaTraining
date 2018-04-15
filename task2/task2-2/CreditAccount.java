class CreditAccount extends Account {
    protected Double stake;
	protected int term;
	protected Double creditSum;

	CreditAccount (Double s, int t, Double c, Long n, Double b) {
		super(n, b);
		stake = s;
		term = t;
		creditSum = c;

		System.out.println(this.getClass().getSimpleName()+" object created");
	}
}