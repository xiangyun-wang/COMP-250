
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
    		numEntries = 0;
        numBuckets = initialCapacity;
        buckets = new ArrayList<LinkedList<HashPair<K,V>>>(numBuckets);
        for(int i = numBuckets; i>0; i--) {
        		buckets.add(null);
        }
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {////////////////////////////////////////////////need to be fixed
        //  ADD YOUR CODE BELOW HERE
    		if(key==null) {
    			return null;
    		}
    		int temphash = hashFunction(key);
    		LinkedList<HashPair<K,V>> cur_bucket = buckets.get(temphash);
    		if(cur_bucket==null) {
    			buckets.set(temphash,new LinkedList<HashPair<K,V>>());
    			buckets.get(temphash).add(new HashPair<K, V>(key, value));
    			numEntries = numEntries +1;
    			if(((double)numEntries/numBuckets)>MAX_LOAD_FACTOR) { /////////////////////////////////////////////////////////////////////////////////////////
    				this.rehash();
    			}
    			return null;
    		}else {
    			LinkedList<HashPair<K,V>> update = new LinkedList<HashPair<K,V>>();//
    			V output = null;
    			HashPair<K, V> temp = null;
    			Boolean add = false;
    			for(int i = cur_bucket.size(); i > 0; i--) {
    				temp = cur_bucket.pop();
    				if (temp.getKey().equals(key)) {
    					output = temp.getValue();
    					temp.setValue(value);
    					update.push(temp);
    					add = true;
    				}else {
    					update.push(temp);
    				}
    			}
    			if(add == false) {
    				update.push(new HashPair<K, V>(key, value));
    				numEntries = numEntries +1;
    			}
    			buckets.set(temphash, update);
    			if(((double)numEntries/numBuckets)>MAX_LOAD_FACTOR) { /////////////////////////////////////////////////////////////////////////////////////
    				this.rehash();
    			}
    			return output;
    		}
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
    		if(key==null) {
    			return null;
    		}
    		int temphash = hashFunction(key);
		V output = null;
		HashPair<K, V> temp = null;
		//LinkedList<HashPair<K,V>> update = new LinkedList<HashPair<K,V>>();
		LinkedList<HashPair<K,V>> cur_bucket = buckets.get(temphash);
		//System.out.println("size before get: " + cur_bucket.size());////////////
		if (cur_bucket!=null) {
			for(int i = cur_bucket.size(); i > 0; i--) {
				temp = cur_bucket.pop();
				cur_bucket.add(temp);
				//update.push(temp);
				if (temp.getKey().equals(key)) {
					output = temp.getValue();
				}
			}
			//buckets.set(temphash, update);
		}
		//System.out.println("size after get: " + buckets.get(temphash).size());//////////////
        return output;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
    		if(key == null) {
    			return null;
    		}
        //ADD YOUR CODE BELOW HERE
    		int temphash = hashFunction(key);
    		V output = null;
    		HashPair<K, V> temp = null;
    		LinkedList<HashPair<K,V>> update = new LinkedList<HashPair<K,V>>();
    		LinkedList<HashPair<K,V>> cur_bucket = buckets.get(temphash);
    		Boolean removed = false;
    		//System.out.println("size before remove: " + cur_bucket.size());/////////
    		if(cur_bucket != null) {
    			for(int i = cur_bucket.size(); i > 0; i--) {
    				temp = cur_bucket.pop();
    				if (temp.getKey().equals(key)&&removed == false) {
    					output = temp.getValue();
    					removed = true;
    					numEntries = numEntries -1;
    				}else {
    					cur_bucket.add(temp);
    					//update.push(temp);
    				}
    			}
    			//buckets.set(temphash, update);
    		}
    		//System.out.println("size after remove: " + buckets.get(temphash).size());//////////
        return output;//remove
        //ADD YOUR CODE ABOVE HERE
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
        numBuckets = numBuckets*2;
        numEntries = 0;
        Iterator<HashPair<K,V>> a = this.iterator();
        buckets = new ArrayList<LinkedList<HashPair<K,V>>>(numBuckets);
        for(int i = numBuckets; i>0; i--) {
        		buckets.add(null);
        }
        while(a.hasNext()) {
        		HashPair<K,V> temp = a.next();
        		this.put(temp.getKey(), temp.getValue());
        }
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {														//Still need to be tested
        //ADD YOUR CODE BELOW HERE
    		ArrayList<K> output = new ArrayList<K>();
    		Iterator<HashPair<K,V>> a = this.iterator();
    		while(a.hasNext()) {
    			output.add(a.next().getKey());
    		}
        return output;//remove
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {													//Still need to be tested
        //ADD CODE BELOW HERE
    		ArrayList<LinkedList<V>> temphash = new ArrayList<LinkedList<V>>(numBuckets);	//1
    		ArrayList<V> output = new ArrayList<V>();									//1
    		for(int i = numBuckets; i>0;i--) {											//n
    			temphash.add(null);
    		}
    		Iterator<HashPair<K,V>> a = this.iterator();									//n
    		while(a.hasNext()) {															//n	
    			V cur = a.next().getValue();
    			int hashValue = Math.abs(cur.hashCode())%this.numBuckets;
    			LinkedList<V> temp = temphash.get(hashValue);
    			if(temp == null) {
    				temp = new LinkedList<V>();
    				temp.push(cur);
    				temphash.set(hashValue, temp);
    			}else {
    				Boolean same = false;
    				LinkedList<V> update = new LinkedList<V>();
    				for(int i = temp.size(); i>0; i--) {								//1
    					V cur_v = temp.pop();
    					if(cur_v.equals(cur)) {
    						same = true;
    					}
    					update.push(cur_v);
    				}
    				if(!same) {
    					update.push(cur);
    				}
    				temphash.set(hashValue, update);
    			}
    		}
    		for(LinkedList<V> currentTerm: temphash) {										//n
    			if(currentTerm != null) {
    				for(int i = currentTerm.size();i>0;i--) {							//1
    					output.add(currentTerm.pop());
    				}
    			}
    		}
        return output;																//maximum 4n
        //ADD CODE ABOVE HERE
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;
        
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
        		entries = new LinkedList<HashPair<K,V>>();
        		for(LinkedList<HashPair<K,V>> currentTerm: buckets) {
        			if(currentTerm!=null) {
        				//LinkedList<HashPair<K,V>> update = new LinkedList<HashPair<K,V>>();
        				for(int i = currentTerm.size();i>0;i--) {
        					HashPair<K,V> temp = currentTerm.pop();
        					entries.push(temp);
        					currentTerm.add(temp);
        				}
        				//currentTerm = update;
        			}
        		}
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public boolean hasNext() {
        		//ADD YOUR CODE BELOW HERE
        		if(entries.size()>0) {
        			return true;
        		}else {
        			return false;
        		}
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
        		if(this.hasNext()) {
        			return entries.pop();
        		}else {
        			return null;
        		}
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}
