//By Luke Glayat
package edu.cofc.cs.csci230;
import java.io.*;
import java.util.*;

public class TestHashing {
	
	//Helper function to prepare the text to be inserted
	public static String[] createArray(String text){  
		text = text.replaceAll("\n", " ");
		text = text.trim().replaceAll(" +", " ");
		String words[] = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		int noOfUniqueElements = words.length;
        for (int i = 0; i < noOfUniqueElements; i++) {
            for (int j = i+1; j < noOfUniqueElements; j++) {  
                if(words[i].equals(words[j])){    
                	words[j] = words[noOfUniqueElements-1];
                	noOfUniqueElements--;         
                    j--;
                }
            }
        }      
        String[] processedText = Arrays.copyOf(words, noOfUniqueElements);
        
        return processedText;
	}
	
	
	public static void main(String[] args){		
		
		try{
		//Prepare text arrays for both files
			String fileName = "ansi_tut.txt";
			File file = new File(fileName);
			Scanner scanner;
			String text = "";
			
			scanner = new Scanner(file);	
			while(scanner.hasNextLine()){
				text += scanner.nextLine() + "\n";
			}
			scanner.close();
			
		//newWords[] holds the processed array
			String newWords[] = createArray(text);
			int size = newWords.length;
			
		//Instantiate Open and Closed Hashtables
			ClosedHashing closedTable1 = new ClosedHashing(size, 0);
			ClosedHashing closedTable2 = new ClosedHashing(size, 1);
			OpenHashing openTable1 = new OpenHashing(size/2, 0);
			OpenHashing openTable2 = new OpenHashing(size/2, 1);
			
		//Populate the hashtables with first half of text file
			for(int i = 0; i < newWords.length/2 ; i++){
				closedTable1.insert(newWords[i]);
				closedTable2.insert(newWords[i]);
				openTable1.insert(newWords[i]);
				openTable2.insert(newWords[i]);
			}
		

		//Display HashTables 
//			closedTable1.printElements();
//			closedTable2.printElements();
//			System.out.println(openTable1.toString());
//			System.out.println(openTable2.toString());
		

	//Successful Tests Only		
		//TEST 1: Closed Hashing with first hash function 
			long searchAvg = 0;
			for(int i = 0; i < newWords.length/2; i++){
				long time = System.nanoTime();
				closedTable1.search(newWords[i]);
				long end = System.nanoTime();
				long duration = end - time;
				//System.out.println("Search # " + (i+1) + " = "+ duration + " ns");
				//System.out.println(duration);
				searchAvg += duration;
			}
			System.out.println("---------------");

		//TEST 2: Closed Hashing with second hash function
			long searchAvg2 = 0;
			for(int i = 0; i < newWords.length/2; i++){
				long time2 = System.nanoTime();
				closedTable2.search(newWords[i]);
				long end2 = System.nanoTime();
				long duration2 = end2 - time2;
				//System.out.println("Search # " + (i+1) + " = "+ duration2 + " ns");
				//System.out.println(duration2);
				searchAvg2 += duration2;
			}
			System.out.println("---------------");

	
		//TEST 3: Open Hashing with first hash function 
			long searchAvg3 = 0;
			for(int i = 0; i < newWords.length/2; i++){
				long time3 = System.nanoTime();
				openTable1.search(newWords[i]);
				long end3 = System.nanoTime();
				long duration3 = end3 - time3;
				//System.out.println("Search # " + (i+1) + " = "+ duration3 + " ns");
				//System.out.println(duration3);
				searchAvg3 += duration3;
			}
			System.out.println("---------------");	
	
	 	//TEST 4: Open Hashing with second hash function 
			long searchAvg4 = 0;
			for(int i = 0; i < newWords.length/2; i++){
				long time4 = System.nanoTime();
				openTable2.search(newWords[i]);
				long end4 = System.nanoTime();
				long duration4 = end4 - time4;
				//System.out.println("Search # " + (i+1) + " = "+ duration4 + " ns");
				//System.out.println(duration4);
				searchAvg4 += duration4;
			}
			System.out.println("---------------");

	
			
		
		//Unsuccessful Search Test Only		
			//TEST 5: Closed Hashing with first hash function 
				System.out.println();
				long searchAvg5 = 0;
				int searchNo = 1;
				for(int i = newWords.length/2 ; i < newWords.length; i++){
					long time5 = System.nanoTime();
					closedTable1.search(newWords[i]);
					long end5 = System.nanoTime();
					long duration5 = end5 - time5;
					//System.out.println("Search # " + (searchNo) + " = "+ duration5 + " ns");
					//System.out.println(duration5);
					searchAvg5 += duration5;
					searchNo++;
				}
				System.out.println("---------------");
			
			//TEST 6: Closed Hashing with second hash function
				long searchAvg6 = 0;
				int searchNo2 = 1;
				for(int i = newWords.length/2; i < newWords.length; i++){
					long time6 = System.nanoTime();
					closedTable2.search(newWords[i]);
					long end6 = System.nanoTime();
					long duration6 = end6 - time6;
					//System.out.println("Search # " + (searchNo2) + " = "+ duration6 + " ns");
					searchAvg6 += duration6;
					//System.out.println(duration6);
					searchNo2++;
				}
				System.out.println("---------------");

			//TEST 7: Open Hashing with first hash function 
				long searchAvg7 = 0;
				int searchNo3 = 1;
				for(int i = newWords.length/2; i < newWords.length; i++){
					long time7 = System.nanoTime();
					openTable1.search(newWords[i]);
					long end7 = System.nanoTime();
					long duration7 = end7 - time7;
					//System.out.println("Search # " + (searchNo3) + " = "+ duration7 + " ns");
					//System.out.println(duration7);
					searchAvg7 += duration7;
					searchNo3++;
				}
				System.out.println("---------------");
				
			//TEST 8: Open Hashing with second hash function 
				long searchAvg8 = 0;
				int searchNo4 = 1;
				for(int i = newWords.length/2; i < newWords.length; i++){
					long time8 = System.nanoTime();
					openTable2.search(newWords[i]);
					long end8 = System.nanoTime();
					long duration8 = end8 - time8;
					//System.out.println("Search # " + (searchNo4) + " = "+ duration8 + " ns");
					searchAvg8 += duration8;
					//System.out.println(duration8);
					searchNo4++;
				}
				System.out.println("---------------");

				
				
				
			//Display Results
				System.out.println("-------------------SuccessFul Test--------------------------");
				System.out.println();
				searchAvg = searchAvg / (newWords.length/2);
				System.out.println("-----Closed Hashing Function 1-----");
				System.out.println();
				System.out.println("Average Search time = " + searchAvg + " nanoseconds");
				//closedTable1.calcProbes();
				System.out.println();

		
				searchAvg2 = searchAvg2 / (newWords.length/2);
				System.out.println("-----Closed Hashing Function 2-----");
				System.out.println();
				System.out.println("Average Search time = " + searchAvg2 + " nanoseconds");
				//closedTable1.calcProbes();
				System.out.println();

				searchAvg3 = searchAvg3 / (newWords.length/2);
				System.out.println();
				System.out.println("-----Open Hashing Function 1-----");
				System.out.println();
				System.out.println("The Average Search time = " + searchAvg3 + " nanoseconds");
				//openTable1.calcProbes();
				System.out.println();

				searchAvg4 = searchAvg4 / (newWords.length/2);
				System.out.println();
				System.out.println("-----Open Hashing Function 2-----");
				System.out.println();
				System.out.println("The Average Search time = " + searchAvg4 + " nanoseconds");
				//openTable2.calcProbes();
				System.out.println("------------------------------------------------------------");
				System.out.println();

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();

				System.out.println("-------------------Unsuccessful Test--------------------------");
				searchAvg5 = searchAvg5 / (newWords.length/2);
				System.out.println("-----Closed Hashing Function 1-----");
				System.out.println();
				System.out.println("Average Search time = " + searchAvg5 + " nanoseconds");
				//closedTable1.calcProbes();
				System.out.println();

				searchAvg6 = searchAvg6 / (newWords.length/2);
				System.out.println("-----Closed Hashing Function 2-----");
				System.out.println();
				System.out.println("Average Search time = " + searchAvg6 + " nanoseconds");
			//	closedTable1.calcProbes();
				System.out.println();

				searchAvg7 = searchAvg7 / (newWords.length/2);
				System.out.println();
				System.out.println("-----Open Hashing Function 1-----");
				System.out.println();
				System.out.println("The Average Search time = " + searchAvg7 + " nanoseconds");
			//	openTable1.calcProbes();
				System.out.println();

				searchAvg8 = searchAvg8 /(newWords.length/2);
				System.out.println();
				System.out.println("-----Open Hashing Function 2-----");
				System.out.println();
				System.out.println("The Average Search time = " + searchAvg8 + " nanoseconds");
				//openTable2.calcProbes();
				System.out.println();


				
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	
	}
}
