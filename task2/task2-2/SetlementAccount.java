class SetlementAccount extends Account {
	SetlementAccount (Long n, Double b) {
		super(n, b);
		
		System.out.println(this.getClass().getSimpleName()+" object created");
	}
}