//By Luke Glayat


package edu.cofc.cs.csci230;



public class ClosedHashing {
	
	int tableSize;
    private String[] table;
	double load;
    int hashPicker;
	
    //Constructor
	public ClosedHashing(int size, int hash){
		this.tableSize = size;
        table = new String[tableSize];
        this.hashPicker = hash;
	}

	public void insert(String word){
		int index = hash(word);
		
		boolean inserted = false;
		
		while (inserted == false){
			if(index == tableSize){
				index = 0;
			}
			if(table[index] != null){
				index++;
			}
			else if(table[index] == null){
				table[index] = word;
				inserted = true;
			}
		}
	}
	
	public int hash(String word){
		if(this.hashPicker == 0){
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
	
	public boolean search(String word){
		int index = hash(word);
		for(int i = index; i < tableSize; i++){
			if(table[i] == (word) ){
				return true;
			}
			if(table[i] == null){
				return false;
			}
		}
		return false;
	
	}
	
	public void delete(String word){
		int index = hash(word);
		
		if( search(word) == true){
			table[index] = null;
		}
		else{
			for(int i = index + 1; i < tableSize; i++){
				if(table[index] == word){
					table[index] = null;
				}
			}
		}
		
		
	}
	
	public void printElements(){
		for(int i = 0; i < tableSize; i++){
			System.out.println("Table index " + i + " = " + table[i]);
		}
		System.out.println("-----------------------------------");
	}

	public void calcLoadFactor(){
		double alpha;
		double fullBuckets = 0;	
		for(int i = 0; i < tableSize; i++){
			if(table[i] != null){
				fullBuckets++;
			}
		}
		alpha = fullBuckets / tableSize;
		System.out.println("Load Factor: " + alpha );
	}
	
	public void calcProbes(){
		double alpha;
		double fullBuckets = 0;	
		for(int i = 0; i < tableSize; i++){
			if(table[i] != null){
				fullBuckets++;
			}
		}
		alpha = fullBuckets / tableSize;
		double S = (.5) * ( 1 + (1 / 1 - alpha));
		double U = (.5) * ( 1 + (1 / Math.pow(1-alpha, 2)));
		System.out.println();
		System.out.println("Successful Probes (S): " + S );
		System.out.println("Unsuccessful Probes (U): " + U );
		System.out.println("---------------------------------");
	}
	
}
