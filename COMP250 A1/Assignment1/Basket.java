package comp250Assignment1;

public class Basket {

	public MarketProduct inBasket[];										//create an array of object MarketProduct

	public Basket() {									//constructor of the class
		inBasket = new MarketProduct[0];					//define an empty array
	}

	public MarketProduct[] getProducts() {						//this method is used to get shallow copy of the array
		MarketProduct[] shallow_copy = new MarketProduct[inBasket.length];
		for (int i = 0; i < inBasket.length; i++) {
			shallow_copy[i] = inBasket[i];
		}
		return shallow_copy;					//return the list of products
	}

	//the following method add a new product to the basket
	public void add(MarketProduct willing_to_buy) {
		int new_length = inBasket.length+1;					//this integer is used to define the new length of inBasket array
		MarketProduct[] updating = new MarketProduct[inBasket.length];	//create a temporary array to update inBasket array
		for (int i=0; i<inBasket.length;i++) {				//copy inBasket to the temporary array
			updating[i] = inBasket[i];
		}

		inBasket = new MarketProduct[new_length];			//redefine inBasket array with the new length

		for (int i=0; i<updating.length; i++) {				//put the products back into the new inBasket array
			inBasket[i] = updating[i];
		}

		inBasket[inBasket.length-1] = willing_to_buy;		//add the new product to the end of the new array

	}

	//the following method remove a product from the basket
	public boolean remove(MarketProduct change_the_mind) {
		for(int i=0; i<inBasket.length; i++) {										//scan the basket using a for loop
			if(inBasket[i].equals(change_the_mind)) {							// if the same product is found
				inBasket[i] = null;												//replace the product with null
				int new_length = inBasket.length-1;
				MarketProduct[] updating = new MarketProduct[inBasket.length];	//create a temporary array to update the new basket
				for (int m=0; m<inBasket.length;m++) {							//copy the basket to this temporary array
					updating[m] = inBasket[m];
				}
				inBasket = new MarketProduct[new_length];						//redefine the inBasket array using the new length
				int array_tracker = 0;											//array_tracker is used to track the new inBasket array
				for (int m=0; m<updating.length; m++) {							//use for loop to update the new inBasket array
					if(updating[m] != null) {									//if the product in the updating array is not null, put it into the inBasket array
						inBasket[array_tracker] = updating[m];
						array_tracker += 1;										//array_tracker +1 after each product is added to the inBasket array
					}
				}
				return true;														//after successfully remove the product, return true
			}
		}
		return false;																//if the target is not found, return false
	}

	public void clear() {						//this method is used to clear the inBasket array
		inBasket = new MarketProduct[0];			//redefine the inBasket array to an empty array
	}

	public int getNumOfProducts() {				//this method is used to get the number of product in the basket
		return inBasket.length;					//return the length of the array, which is also the number of products in the array
	}

	public int getSubTotal() {					//this method is used to get the subtotal of the products in the basket
		int subtotal = 0;						//create an integer to hold the subtotal, first set it to 0
		for (int i=0; i<inBasket.length;i++) {
			subtotal += inBasket[i].getCost();		//as long as the cost of the product is positive, add them together
		}

		return subtotal;							// return the subtotal of the products in the basket
	}

	public int getTotalTax() {						//this method is used to get the total tax of the products
		int need_to_tax = 0;							//set the amount of money need to be taxed to 0
		for (int i=0; i<inBasket.length;i++) {
			if(inBasket[i] instanceof Jam) {		//if the product is jam, and the cost is positive
				need_to_tax += inBasket[i].getCost();					//add the money to need_to/tax
			}
		}
		return (int)(need_to_tax*0.15);						//return the tax at last in total
	}

	public int getTotalCost() {				//this method is used get the total cost of of the basket (subtotal + tax)

		return (this.getSubTotal()+this.getTotalTax());     //return the result of adding subtotal and tax together

	}

	private String toProperString(String input) {
		for (int i = 0; i < input.length();i++) {
			if(input.charAt(i)=='.'&&i==input.length()-2) {
				return input+"0";
			}else if(input.charAt(i)=='.'&&i==input.length()-3) {
				return input;
			}
		}
		return input+".00";
	}

	public String toString() {				//this method is used to print the product names, prices, subtotal, tax, and total cost in an organized form
		String output = "";
		for(int i=0; i<inBasket.length; i++) {
			output = output + inBasket[i].getName() + "\t";
			if (inBasket[i].getCost() > 0) {
				String temp = Double.toString((double)inBasket[i].getCost()/100);
				output = output + toProperString(temp)+"\n";
			}else {
				output = output + "-\n";
			}
		}
		if(this.getSubTotal()>0){
			String temp = Double.toString((double)this.getSubTotal()/100);
			output = output + "\nSubtotal"+ "\t" + toProperString(temp);
		}else{
			output = output + "\nSubtotal"+ "\t" + "-";
		}
		if(this.getTotalTax()>0){
			String temp = Double.toString((double)this.getTotalTax()/100);
			output = output + "\nTotal Tax" + "\t" + toProperString(temp);
		}else{
			output = output + "\nTotal Tax" + "\t" + "-";
		}
		if(this.getTotalCost()>0){
			String temp = Double.toString((double)this.getTotalCost()/100);
			output = output + "\n\nTotal Cost" + "\t" + toProperString(temp);
		}else{
			output = output + "\n\nTotal Cost" + "\t" + "-";
		}

		return output;				//return the organized receipt

	}

}
