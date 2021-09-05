package comp250Assignment1;

public class SeasonalFruit extends Fruit{
	//this class is a subclass of Fruit
	
	public SeasonalFruit(String name, double weight, int price) { 		//constructor of the class
		super(name, weight, price);										//call the constructor of the superclass
	}
	
	public int getCost() {							//get the cost of the seasonal fruit
		return (int)(super.getCost()*0.85);			//first, get the cost of the fruit using the method of superclass; 
													//since there is a 15% off for seasonal fruit, the price multiply by 0.85
	}

}
