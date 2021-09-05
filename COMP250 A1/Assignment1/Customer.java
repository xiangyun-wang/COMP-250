package comp250Assignment1;

public class Customer {
	
	private String name;							//name of the customer
	private int balance;							//balance of the customer
	private Basket current_basket;				//create a basket for the customer
	
	public Customer(String name, int initial_balance) {		//constructor of the Customer class
		this.name = name;									//define name
		balance = initial_balance;							//define initial balance of the customer
		current_basket = new Basket();						//define current_basket
	}
	
	public String getName() {						//this method is used to get the name of the customer
		return name;
	}
	
	public int getBalance() {						//this method is used to get the balance of the customer
		return balance;
	}
	
	public Basket getBasket() {						//get the address of ccurrent_basket array
		return current_basket;
	}
	
	//the following method is used to add funds
	public int addFunds(int topup) throws IllegalArgumentException {	
		if(topup < 0) {			//if the input is negative, throw an exception
			throw new IllegalArgumentException("Negative number is not accepted for adding funds. ");
		}else {
			balance += topup;		//add the topup amount to the current balance
			return balance;
		}
	}
	
	public void addToBasket(MarketProduct willing_to_buy) {		//this method is used to add a product to the basket
		current_basket.add(willing_to_buy);
	}
	
	public boolean removeFromBasket(MarketProduct change_the_mind) {		//this method is used to remove a product from a basket
		return current_basket.remove(change_the_mind);
	}
	
	public String checkOut() throws IllegalStateException{  			//this method is used to check out
		if(current_basket.getTotalCost()>balance) {									//if balance is less than the total cost of the products
			throw new IllegalStateException("There is not enough balance!!");			//throw an exception 
		}else {
			balance -= current_basket.getTotalCost();			//otherwise, deduct the cost from the balance
			String output = current_basket.toString();			//use a String to hold the receipt
			current_basket.clear();								//clear the basket
			return output;										//return the receipt
		}
	}
	
}
