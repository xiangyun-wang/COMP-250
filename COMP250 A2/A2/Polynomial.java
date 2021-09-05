package Assignment2;

/*
 * Name: Xiangyun Wang
 * Student ID: 260771591 
 */

import java.math.BigInteger;
import java.util.Iterator;

public class Polynomial 
{
	private SLinkedList<Term> polynomial;
	public int size()
	{	
		return polynomial.size();
	}
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}
	
	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}
	
	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */
	public void addTerm(Term t)
	{	
		/**** ADD CODE HERE ****/
		
		// Hint: Notice that the function SLinkedList.get(index) method is O(n), 
		// so if this method were to call the get(index) 
		// method n times then the method would be O(n^2).
		// Instead, use a Java enhanced for loop to iterate through 
		// the terms of an SLinkedList.
		/*
		for (Term currentTerm: polynomial)
		{
			// The for loop iterates over each term in the polynomial!!
			// Example: System.out.println(currentTerm.getExponent()) should print the exponents of each term in the polynomial when it is not empty.  
		}
		*/
		/*if(t.getExponent()>=0&&!t.getCoefficient().equals(new BigInteger("0"))) {
			int track = 0;
			for(Term currentTerm: polynomial) {
				if(currentTerm.getExponent()==t.getExponent()) {
					if(!currentTerm.getCoefficient().add(t.getCoefficient()).equals(new BigInteger("0"))) {
						currentTerm.setCoefficient(currentTerm.getCoefficient().add(t.getCoefficient()));
					}
					break;
				}else if(currentTerm.getExponent()<t.getExponent()) {
					polynomial.add(track, t);
					break;
				}else {
					track=track+1;
				}
			}
			if(track==polynomial.size()) {
				polynomial.addLast(t);
			}
		}*/
		if(t.getExponent()>= 0&&!t.getCoefficient().equals(new BigInteger("0"))) {
			SLinkedList<Term> update = new SLinkedList<Term>();
			Boolean added = false;
			for(Term currentTerm: polynomial) {
				if(currentTerm.getExponent()==t.getExponent()) {
					if(!currentTerm.getCoefficient().add(t.getCoefficient()).equals(new BigInteger("0"))) {
						update.addLast(new Term(currentTerm.getExponent(),currentTerm.getCoefficient().add(t.getCoefficient())));
					}
					added = true;
				}else if(currentTerm.getExponent()<t.getExponent()&&!added) {
					update.addLast(t);
					update.addLast(currentTerm.deepClone());
					added = true;
				}else {
					update.addLast(currentTerm.deepClone());
				}
			}
			if(!added) {
				update.addLast(t);
			}
			polynomial = update;
		}
	}
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)////////////////////////////////////////////////////////////////////////////////
	{
		/**** ADD CODE HERE ****/
		Polynomial addition = new Polynomial();
		
		if(p1.polynomial.isEmpty()&&p2.polynomial.isEmpty()) {
			
		}else if(p1.polynomial.isEmpty()) {
			addition = p2.deepClone();
		}else if(p2.polynomial.isEmpty()) {
			addition = p1.deepClone();
		}else {
			Iterator<Term> scan1 = p1.polynomial.iterator();
			Iterator<Term> scan2 = p2.polynomial.iterator();
			Term cur1 = scan1.next();
			Term cur2 = scan2.next();
			///////////////////
			while(cur1!=null&&cur2!=null) {
				if(cur1.getExponent()>cur2.getExponent()) {
					addition.addTermLast(cur1.deepClone());
					if(scan1.hasNext())		cur1 = scan1.next();
					else 					cur1 = null;
					
				}else if(cur1.getExponent()<cur2.getExponent()) {
					addition.addTermLast(cur2.deepClone());
					if(scan2.hasNext())		cur2 = scan2.next();
					else 					cur2 = null;
				}else {
					if(!cur1.getCoefficient().add(cur2.getCoefficient()).equals(new BigInteger("0")))
						addition.addTermLast(new Term(cur1.getExponent(),cur1.getCoefficient().add(cur2.getCoefficient())));
					if(scan2.hasNext()&&scan1.hasNext()) {
						cur1 = scan1.next();
						cur2 = scan2.next();
					}else if ((!scan2.hasNext())&&(!scan1.hasNext())){
						cur1 = null;
						cur2 = null;
					}else if(!scan2.hasNext()) {
						cur1 = scan1.next();
						cur2 = null;
					}else {
						cur2 = scan2.next();
						cur1 = null;
					}
				}
			}
			while(cur1!=null) {
				addition.addTermLast(cur1.deepClone());
				if(scan1.hasNext()) 		cur1 = scan1.next();
				else 					cur1 = null;
			}
			while(cur2!=null) {
				addition.addTermLast(cur2.deepClone());
				if(scan2.hasNext()) 		cur2 = scan2.next();
				else 					cur2 = null;
			}
			//////////////////
			/*
			while(scan1.hasNext()||scan2.hasNext()) {
				if(scan1.hasNext()&&scan2.hasNext()) {
					if(cur1.getExponent()>cur2.getExponent()) {
						addition.addTermLast(cur1.deepClone());
						cur1 = scan1.next();
					}else if(cur1.getExponent()<cur2.getExponent()) {
						addition.addTermLast(cur2.deepClone());
						cur2 = scan2.next();
					}else {
						if(!cur1.getCoefficient().add(cur2.getCoefficient()).equals(new BigInteger("0"))) {
							addition.addTermLast(new Term(cur1.getExponent(), cur1.getCoefficient().add(cur2.getCoefficient())));
						}
						cur1 = scan1.next();
						cur2 = scan2.next();
					}
				}else if(scan1.hasNext()) {
					if(cur2!=null) {
						if(cur1.getExponent()>cur2.getExponent()) {
							addition.addTermLast(cur1.deepClone());
							cur1 = scan1.next();
						}else if(cur1.getExponent()<cur2.getExponent()) {
							addition.addTermLast(cur2.deepClone());
							cur2 = null;
						}else {
							if(!cur1.getCoefficient().add(cur2.getCoefficient()).equals(new BigInteger("0"))) {
								addition.addTermLast(new Term(cur1.getExponent(), cur1.getCoefficient().add(cur2.getCoefficient())));
							}
							cur1 = scan1.next();
							cur2 = null;
						}
					}else {
						addition.addTermLast(cur1.deepClone());
						cur1 = scan1.next();
					}
				}else {
					if(cur1!=null) {
						if(cur1.getExponent()>cur2.getExponent()) {
							addition.addTermLast(cur1.deepClone());
							cur1 = null;
						}else if(cur1.getExponent()<cur2.getExponent()) {
							addition.addTermLast(cur2.deepClone());
							cur2 = scan2.next();
						}else{
							if(!cur1.getCoefficient().add(cur2.getCoefficient()).equals(new BigInteger("0"))) {
								addition.addTermLast(new Term(cur1.getExponent(), cur1.getCoefficient().add(cur2.getCoefficient())));
							}
							cur1 = null;
							cur2 = scan2.next();
						}
					}else {
						addition.addTermLast(cur2.deepClone());
						cur2 = scan2.next();
					}
				}
			}
			if(cur1!=null&&cur2!=null) {
				if(cur1.getExponent()>cur2.getExponent()) {
					addition.addTermLast(cur1.deepClone());
					addition.addTermLast(cur2.deepClone());
				}else if(cur1.getExponent()<cur2.getExponent()) {
					addition.addTermLast(cur2.deepClone());
					addition.addTermLast(cur1.deepClone());
				}else {
					if(!cur1.getCoefficient().add(cur2.getCoefficient()).equals(new BigInteger("0"))) {
						addition.addTermLast(new Term(cur1.getExponent(), cur1.getCoefficient().add(cur2.getCoefficient())));
					}
				}
			}else if(cur1!=null) {
				addition.addTermLast(cur1.deepClone());
			}else if(cur2!=null){
				addition.addTermLast(cur2.deepClone());
			}
			
			*/
		}
		
		return addition;
	}
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)////////////////////////////////////////////////////
	{	
		/**** ADD CODE HERE ****/
		if(t.getExponent()>=0) {
			if(t.getCoefficient().equals(new BigInteger("0"))) {
				polynomial = new SLinkedList<Term>();
			}else {
				SLinkedList<Term> updating = new SLinkedList<Term>();
				for(Term currentTerm: polynomial) {
					updating.addLast(new Term(currentTerm.getExponent()+t.getExponent(),currentTerm.getCoefficient().multiply(t.getCoefficient())));
				}
				polynomial = updating;
			}
		}
	}
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)		//still need to check complexity
	{
		/**** ADD CODE HERE ****/
		Polynomial result = new Polynomial();
		
		//if((!p2.polynomial.isEmpty())&&(!p1.polynomial.isEmpty())) {
			//for(Term currentTerm: p2.polynomial) {
				////////////////////////////////////////////////////
				/*SLinkedList<Term> update = new SLinkedList<Term>();
				for(Term currentTerm1: p1.polynomial) {
					update.addLast(new Term(currentTerm.getExponent()+currentTerm1.getExponent(),currentTerm.getCoefficient().multiply(currentTerm1.getCoefficient())));
				}
				result = add(result,new Polynomial(update));
				*/
				///////////////////////////////////////////////////
				//Polynomial updating = new Polynomial(p1.polynomial.deepClone());
				//updating.multiplyTerm(currentTerm);
				//result = add(result,updating);
			//}
		//}
		if((!p2.polynomial.isEmpty())&&(!p1.polynomial.isEmpty())) {
			Iterator<Term> scan1 = p1.polynomial.iterator();
			Iterator<Term> scan2 = p2.polynomial.iterator();
			Term[] tmp = new Term[scan1.next().getExponent()+scan2.next().getExponent()+1];
			for(Term current1: p2.polynomial) {
				for(Term current2: p1.polynomial) {
					int exponent = current1.getExponent()+current2.getExponent();
					BigInteger coefficient = current1.getCoefficient().multiply(current2.getCoefficient());
					if(tmp[exponent]== null) {
						tmp[exponent] = new Term(exponent,coefficient);
					}else if(!tmp[exponent].getCoefficient().add(coefficient).equals(new BigInteger("0"))){
						tmp[exponent].setCoefficient(tmp[exponent].getCoefficient().add(coefficient));
					}else {
						tmp[exponent] = null;
					}
				}
			}
			for(int i = tmp.length-1;i>=0;i--) {
				if(tmp[i]!=null) {
					result.addTermLast(tmp[i]);
				}
			}
		}
		return result;
	}
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
		/**** ADD CODE HERE ****/
		BigInteger result = BigInteger.valueOf(0);
		Iterator<Term> scan = polynomial.iterator();
		if(scan.hasNext()) {
			Term cur = scan.next();
			int cur_degree = cur.getExponent();
			for(int i = cur_degree;i>0;i--) {
				if(cur_degree==i) {
					result = result.add(cur.getCoefficient()).multiply(x);
					if(scan.hasNext()) {
						cur = scan.next();
						cur_degree = cur.getExponent();
					}else {
						cur = null;
						cur_degree = 0;
					}
				}else {
					result = result.multiply(x);
				}
			}
			if(cur!=null) {
				result = result.add(cur.getCoefficient());
			}
		}
		return result;
	}
	
	// Checks if this polynomial is same as the polynomial in the argument
	public boolean checkEqual(Polynomial p)
	{	
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() ||
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}
	
	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}

