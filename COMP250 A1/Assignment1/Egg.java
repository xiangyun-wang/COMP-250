package comp250Assignment1;

public class Egg extends MarketProduct{
	
	private int num_egg;										//number of eggs
	private int price_per_dozen;								//price of a dozen of egg
	
	public Egg(String name, int eggnum, int price) {			//constructor of the class
		super(name);											//call the constructor of the super class
		num_egg = eggnum;									//define num_egg
		price_per_dozen = price;								//define price_per_dozen
	}

	public int getCost() {									//this method will get the total cost of the egg
		if(num_egg<0||price_per_dozen<0) {
			return 0;
		}
		return num_egg*price_per_dozen/12;		//cost = number of egg * price of a dozen of egg / 12, and return an integer
	}
	
	private int getEggNum() {								//this method is created to get the number of the egg
		return num_egg;
	}
	
	public boolean equals(Object compare) {					//this method is used to compare two objects, to see if they are completely identical or not
		if (compare instanceof Egg) {						//if the input object is an instance of egg, continue the comparison, otherwise directly return false
			if(this.getName().equals(((Egg)compare).getName())&&num_egg == ((Egg)compare).getEggNum()&&this.getCost() == ((Egg)compare).getCost()) {
			// compare names, number of eggs, and the total cost of the eggs, only all of them are the same, return true; otherwise, return false
				return true;
			}
		}
		return false;
	}
		
}
