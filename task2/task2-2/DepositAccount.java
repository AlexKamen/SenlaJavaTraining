class DepositAccount extends Account{
    protected Double stake;
	protected int term;
	protected Double depositSum;

	DepositAccount (Double s, int t, Double d, Long n, Double b) {
		super(n, b);
		stake = s;
		term = t;
		depositSum = d;
		
		System.out.println(this.getClass().getSimpleName()+" object created");
	}
}