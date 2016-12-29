//By Luke Glayat
package edu.cofc.cs.csci230;
public class OpenHashing {
	
	int tableSize;
	private ArrayList[] hashtable;
	int hashPicker = 0;
	
	
    public OpenHashing(int size, int hash){
    	this.tableSize = size;
    	hashtable = new ArrayList[tableSize];
    	this.hashPicker = hash;
    	
    	for(int i = 0; i < tableSize; i++){
    		hashtable[i] = new ArrayList();
    	}
    }
    
	public int hash(String word){
 		
		if(hashPicker == 0){
			int hash = 7;
	        for (int i = 0; i < word.length(); i++) {
	            hash = (hash*29 + word.charAt(i))%tableSize;
	        }
	        return hash;
		}
	
		else{
			int hash = 7;
	        for (int i = 0; i < word.length(); i++) {
	            hash = (hash*word.length() + word.charAt(i))%tableSize;
	        }
	        return hash;
		}
    }
	
	public void insert(String word){
		int index = hash(word);
		hashtable[index].add(word);;		
	}

	public boolean search(String word) throws NullPointerException{
		int index = hash(word);
		for(int i = 0; i < hashtable[index].size();i++){
			if(hashtable[index].get(i).equals(word) ){
				return true;
			}
		}
		return false;
	}
	
	public void remove(String word){
		int index = hash(word);
		hashtable[index].remove(index);
	}
	
	public String toString(){
		String rtnString = "-----\n";
		for(int i =0; i< tableSize ; i++){
			rtnString+= "Key:" + i + "||";
			for(int k = 0; k<hashtable[i].size(); k++){
				rtnString+= hashtable[i].get(k) + " --> ";
			}
			rtnString +=  "\n";
		}		
		rtnString += "------------------------\n";	
		return rtnString;
	}

	public double calcLoadFactor(){
		double alpha;
		double fullBuckets = 0;	
		for(int i = 0; i < tableSize; i++){
			if(hashtable[i] != null){
				fullBuckets += hashtable[i].size();
			}
		}
		alpha = fullBuckets / tableSize;
		return alpha;
	}
	
	public void calcProbes(){
		double alpha = calcLoadFactor();
		double S = 1 + (alpha/2);
		System.out.println();
		System.out.println("Successful Probes (S): " + S );
		System.out.println("Unsuccessful Probes (U): " + alpha );
		System.out.println("-------------------------------");
	}
    
}
