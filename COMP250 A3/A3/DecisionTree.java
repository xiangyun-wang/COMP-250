/*
 * Name: Xiangyun Wang
 * Student ID: 260771591
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
	//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
	public static final long serialVersionUID = 343L;
	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min;
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}

	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf;
		int label = -1;      // only defined if node is a leaf
		int attribute; // only defined if node is not a leaf
		double threshold;  // only defined if node is not a leaf



		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}



		// this method takes in a datalist (ArrayList of type datum) and a minSizeInClassification (int) and returns
		// the calling DTNode object as the root of a decision tree trained using the datapoints present in the
		// datalist variable
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold
		DTNode fillDTNode(ArrayList<Datum> datalist) {///////////////////////////////////////////////////////////////////////////////////////////////////////////////

			//YOUR CODE HERE
			
			int length = datalist.size();
			//System.out.println("the size of the data list is: " + length);
			/*if(calcEntropy(datalist)==0) {
				DTNode create = new DTNode();
				create.label = datalist.get(0).y;
				return create;
			}*/
			if(length >= minSizeDatalist) {
				int example = datalist.get(0).y;
				Boolean same = true;
				for (Datum current: datalist) {
					if(current.y!=example) {
						same = false;
						break;
					}
				}
				if(same) {
					DTNode create = new DTNode();
					create.label = datalist.get(0).y;
					create.left = null; create.right = null;
					return create;
				}else {
					Double best_avg = Double.MAX_VALUE;
					//System.out.println("best_entropy is:"+best_avg);
					int best_attribute = -1;
					double best_threshold = -1.0;
					ArrayList<Datum> best_left_side = new ArrayList<Datum>(0);
					ArrayList<Datum> best_right_side = new ArrayList<Datum>(0);
					for (int i = 0; i<datalist.get(0).x.length;i++) {
						for(Datum current_data: datalist) {
							double current_threshold = current_data.x[i];
							ArrayList<Datum> left_side = new ArrayList<Datum>(0);
							ArrayList<Datum> right_side = new ArrayList<Datum>(0);
							for (Datum currentTerm: datalist) {
								if((currentTerm.x)[i]<current_threshold) {
									left_side.add(currentTerm);
								}else {
									right_side.add(currentTerm);
								}
							}
					
							double avg_entr = calcEntropy(left_side)*((double)left_side.size()/length)+calcEntropy(right_side)*((double)right_side.size()/length);
							//System.out.println("left_faction is:"+calcEntropy(left_side)*(left_side.size()/length) +"\right_fraction is: "+ calcEntropy(right_side)*(right_side.size()/length));
							//System.out.println("left_entropy is:"+calcEntropy(left_side) +"\right_entropy is: "+ calcEntropy(right_side));
							//System.out.println("best_entropy is:"+best_avg +"\tcurrent_entropy is: "+ avg_entr);
							if(best_avg>avg_entr) {
								best_left_side = left_side;
								best_right_side = right_side;
							
								best_avg = avg_entr;
								best_attribute = i;
								best_threshold = current_threshold;
							}
						}
					}
					DTNode create = new DTNode();
					create.leaf = false; create.attribute = best_attribute; create.threshold = best_threshold;
					create.left = fillDTNode(best_left_side);
					create.right = fillDTNode(best_right_side);
					return create;
					}
				} else {
					DTNode create = new DTNode();
					create.label = findMajority(datalist);
					create.left = null; create.right = null;
					return create;
				}
		}



		//This is a helper method. Given a datalist, this method returns the label that has the most
		// occurences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist)
		{
			int l = datalist.get(0).x.length;
			int [] votes = new int[l];

			//loop through the data and count the occurrences of datapoints of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}
			int max = -1;
			int max_index = -1;
			//find the label with the max occurrences
			for (int i = 0 ; i < l ;i++)
			{
				if (max<votes[i])
				{
					max = votes[i];
					max_index = i;
				}
			}
			return max_index;
		}


		//this is a helper method of classifyAtNode
		/*private int classify_helper(DTNode a, double[] Input) {
			
			if(a.leaf) {
				return a.label;
			}else {
				if(a.threshold>Input[a.attribute]) {
					return classify_helper(a.left,Input);
				}else {
					return classify_helper(a.right,Input);
				}
			}
		}*/
		
		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		int classifyAtNode(double[] xQuery) {///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//YOUR CODE HERE
			DTNode classify = rootDTNode;
			while (!classify.leaf) {
				if(classify.threshold>xQuery[classify.attribute]) {
					classify = classify.left;
				}else {
					classify = classify.right;
				}
			}
			return classify.label;
			//return classify_helper(rootDTNode, xQuery); //dummy code.  Update while completing the assignment.
		}

		//helper method for equals
		private boolean check(DTNode a, DTNode b) {
			
			if(a==null&&b==null) {
				return true;
			}
			
			if(a!=null&&b!=null) {
				if(a.leaf==b.leaf) {
					Boolean datacheck = true;
					if(a.leaf==false) {
						if(a.threshold!=b.threshold||a.attribute!=b.attribute) {
							datacheck = false;/////
						}
					}else {
						if(a.label!=b.label) {
							datacheck = false;//////////
						}
					}
					return (datacheck&&check(a.left,b.left)&&check(a.right,b.right));
				}
			}
			
			return false;
		}
		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		//at DTNode object passed as the parameter
		public boolean equals(Object dt2)//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// thinking about how to get rid of the helper method 
		{

			//YOUR CODE HERE 
			/*
			DTNode check_equal = rootDTNode;
			Boolean previous_check = true;
			while (previous_check) {
				if()
					
			}
			
			DTNode check_equal_left = check_equal.left;
			
			DTNode check_equal_right = check_equal.right;
			*/
			if(dt2 instanceof DTNode) {
				return check((DTNode)dt2,rootDTNode);
			}else {
				return false;
			}
		}
	}



	//Given a dataset, this retuns the entropy of the dataset
	double calcEntropy(ArrayList<Datum> datalist)
	{
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the rootnode of the calling DecisionTree object
	int classify(double[] xQuery ) {
		DTNode node = this.rootDTNode;
		return node.classifyAtNode( xQuery );
	}

    // Checks the performance of a DecisionTree on a dataset
    //  This method is provided in case you would like to compare your
    //results with the reference values provided in the PDF in the Data
    //section of the PDF

    String checkPerformance( ArrayList<Datum> datalist)
	{
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	//calling onto the DTNode.equals() method
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}

}
