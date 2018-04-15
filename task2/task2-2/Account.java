import java.util.ArrayList;

class Account implements IAccount {
    public Long number;
	public Double balance;
	public ArrayList <Card> cards = new ArrayList <Card> ();

	Account (Long n, Double b) {
		number = n;
		balance = b;
	}

	public void setNumber (Long accNumber) {
		number = accNumber;
	}

	public Long getNumber () {
		return number;
	}

	public Double getBalance () {
		return balance;
	}
	
	public void addCard (Card newCard) {
		cards.add(newCard);
		System.out.println("Card added to setlement account");
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void deleteCard (Card c) {
		cards.remove(c);
	}

	public void changeBalance (Double newBalance) {
		balance = newBalance;
	}
}