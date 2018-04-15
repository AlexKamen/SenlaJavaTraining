class Client {
    protected String family;
	protected String name;
	protected String patronymic;
	protected String passport;
	
	Client (String f, String n, String pat, String pas) {
		family = f;
		name = n;
		patronymic = pat;
		passport = pas;
		
		System.out.println(this.getClass().getSimpleName()+" object created");
	}

	void addDeposit (DepositAccount newAccount) {
		
	}

	void deleteDeposit (DepositAccount d) {
		
	}

	void addCredit (CreditAccount newAccount) {
		
	}

	void deleteCredit (CreditAccount c) {
		
	}

	void addSetlement (SetlementAccount newAccount) {
		
	}

	void deleteSetlement (SetlementAccount d) {
		
	}
}