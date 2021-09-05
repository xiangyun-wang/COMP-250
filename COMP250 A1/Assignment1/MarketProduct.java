package comp250Assignment1;

public abstract class MarketProduct {				//abstract class is created
	private String name;								//create String name, and make it private
	
	public MarketProduct(String name) {				//constructor of the class
		this.name = name;
	}
	
	public final String getName() {					//public method use to get the name of the Market product
		return name;
	}
	
	public abstract int getCost();					//abstract method, will be defined later in each of the subclass
	
	public abstract boolean equals(Object thing);	//abstract method, will be defined later in each of the subclass
}
