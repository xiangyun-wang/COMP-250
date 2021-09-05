package comp250Assignment1;

public class Jam extends MarketProduct{
	
	private int num_jar;											//number of jar of jam
	private int price_per_jar;									//price per jar of jam
	
	public Jam(String name, int num_jar, int price) {				//constructor of the jam class
		super(name);												//call the constructor of the superclass
		this.num_jar = num_jar;									//define num_jar
		price_per_jar = price;								//define price_per_jar
	}
	
	public int getCost() {										//this method is used to get the total cost of jam
		if(num_jar<0||price_per_jar<0) {
			return 0;
		}
		return num_jar*price_per_jar;							//price = number of jar * price per jar
	}
	
	private int getNum() {										//this method is used to get the number of jar of jam
		return num_jar;
	}
	
	public boolean equals(Object compare) {						//this method is used to compare an object with jam
		if (compare instanceof Jam) {							//if the object is an instance of jam, keep comparing, otherwise, return false
			if(this.getName().equals(((Jam)compare).getName())&&num_jar == ((Jam)compare).getNum()&&this.getCost()==((Jam)compare).getCost()) {
			//if names, number of jars, and the total cost of them are the same, return true, otherwise return false
				return true;
			}
		}
		return false;
	}
	
}
