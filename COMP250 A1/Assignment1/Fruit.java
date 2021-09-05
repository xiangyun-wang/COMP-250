package comp250Assignment1;

public class Fruit extends MarketProduct{
	
	private double weight;							//weight of the fruit(has a type of double)
	private int price_per_kg;						//price of the fruit per kilogram
	
	public Fruit(String name, double weight, int price) {		//constructor of fruit class
		super(name);											//call the constructor of the super class (MarketProduct)
		this.weight = weight;								//define weight
		price_per_kg = price;								//define price
	}
	
	public int getCost() {									//this method is used to get the total cost of the fruit
		if(weight<0||price_per_kg<0) {
			return 0;
		}
		return (int)(weight*price_per_kg);					//price = weight of the fruit * price per kilogram
	}
	
	private double getWeight() {								//this method is used to get the weight of the fruit
		return weight;
	}
	
	public boolean equals(Object compare) {					//this method is used to compare the fruit with an object to see of they are the same or not
		if (compare instanceof Fruit) {						//if the object is an instance of fruit class, keep comparing, otherwise, return false
			if(this.getName().equals(((Fruit)compare).getName())&&weight == ((Fruit)compare).getWeight()&&this.getCost()==((Fruit)compare).getCost()) {
				//if names, weight, and total cost of the two object are the same, return true, otherwise, return false
				return true;
			}
		}
		return false;
	}
	
}
