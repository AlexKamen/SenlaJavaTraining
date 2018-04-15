class Main {
    public static void main(String args[]) {

        DepositAccount deposit;
		deposit = new DepositAccount(8.2, 6, 300000.00, 4081715478963205874L, 300000.00);
		
		CreditAccount credit;
		credit = new CreditAccount(12.3, 12, 100000.00, 4081778979456549879L, 100000.00);

		SetlementAccount setlement;
		setlement = new SetlementAccount(4081778952145803245L, 200.00);
		
		Client client;
		client = new Client("Myfamily", "Myname", "Mypatronymic", "5499 123456");

		Card card;
		card = new Card (22022456987458965L, "09/29", 7282, "MYNAME MYFAMILY", "MYNAME");

		setlement.addCard(card);
    }
}