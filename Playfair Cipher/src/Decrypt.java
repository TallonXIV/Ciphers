
public class Decrypt {
	boolean hasATail = false;
	void decryptAlgorithm(String message, char[] alphabet, char[] used, char[][] table) {
		String decryption = "";
		char[] pair = new char[2];
		int iterator = 0;
		int swapper = 0;
		
		//coordinate declarations
		int colOfElOne = 0;
		int colOfElTwo = 0;
		int rowOfElOne = 0;
		int rowOfElTwo = 0;
		
		//discovery variables declarations
		boolean El1Found = false;
		boolean El2Found = false;
		
		//input prompt
		System.out.println("\n" + "Message before decryption: \n" + message + "\n");
		System.out.println("Pairs derived from message: ");
		while(iterator != message.length()) {
			
			//alternate between array elements to fill using swapper value
			pair[swapper] = message.charAt(iterator);
			if(swapper == 0) {
				swapper = 1;
			}
			else {
				swapper = 0;
			}
			iterator++;
			
			//print every time 2 pairs are joined
			if(iterator % 2 == 0) {
				for(int i = 0; i < 2; i++) {
					System.out.print(pair[i]);
				}
				System.out.print("  ");
				
				//get row (i) and column(j) values for characters
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 5; j++) {
						
						//if the coordinated if the Letter are found, put the coordinates into variables and set it to found
						if(table[i][j] == pair[0]) {
							rowOfElOne = i;
							colOfElOne = j;
							El1Found = true;
						}
						if(table[i][j] == pair[1]) {
							rowOfElTwo = i;
							colOfElTwo = j;
							El2Found = true;
						}
						//if both values of the pair have had their coordinates discovered
						if(El1Found == true && El2Found == true) {	
							
							//Enforcement of Rule 1: matching row
							if(rowOfElOne == rowOfElTwo) {
								//this check allows to wrap around to the other side if it goes of
								if(colOfElOne != 0){
									pair[0] = table[rowOfElOne][colOfElOne - 1];
								}
								else {
									pair[0] = table[rowOfElOne][4];
								}
								
								if(colOfElTwo != 0) {
									pair[1] = table[rowOfElTwo][colOfElTwo - 1];
								}
								else {
									pair[1] = table[rowOfElTwo][4];
								}
								decryption += pair[0];
								decryption += pair[1];
							}//end if
							
							//Enforcement of Rule 2: matching columns
							else if(colOfElOne == colOfElTwo) {
								//this check allows to wrap around to the other side if it goes off
								if(rowOfElOne != 0) {
									pair[0] = table[rowOfElOne - 1][colOfElOne];
								}
								else {
									pair[0] = table[4][colOfElOne];
								}
								
								if(rowOfElTwo != 0) {
									pair[1] = table[rowOfElTwo - 1][colOfElTwo];
								}
								else {
									pair[1] = table[4][colOfElTwo];
								}
								decryption += pair[0];
								decryption += pair[1];
							}//end else
							
							//Enforcement of Rule 3: Rectangular formation
							else {
								pair[0] = table[rowOfElOne][colOfElTwo];
								pair[1] = table[rowOfElTwo][colOfElOne];
								decryption += pair[0];
								decryption += pair[1];
							}
							//this stops these values from carrying over into the next pair searches
							if(i == 4 && j == 4) {
								El1Found = false;
								El2Found = false;
							}
							//this is necessary so it does not loop trying to search the 2d array for its new values
							El1Found = false;
							El2Found = false;
							break;
						}//end if
					}//end for
				}//end for
			}//end if
		}//while
		
		//output of decryption string before post processing
		System.out.println("\n\nRaw Decryption: ");
		System.out.println(decryption);
		
		//post-string formatting to remove extraneous X's put during encrypt formatting
		for(int i = 0; i < decryption.length(); i++) {
			if(decryption.charAt(i) == 'X' && i != (decryption.length() - 1)) {
				if(decryption.charAt(i - 1) == decryption.charAt(i + 1)) {
					decryption = decryption.replace("X", "");
				}
			}//end if
		}//end for
		
		//remove tail if one was added
		if(getAddedToTail()) {
			decryption = decryption.substring(0, decryption.length() - 1);
		}
		System.out.println("\nDecryption after removal of added Xs: ");
		System.out.println(decryption);
	}//end decryptAlgorithm
	
	//these methods indicate whether or not there was an X appended to the end of the phrase
	void setHasATail(boolean hasBeenSet) {
		hasATail = hasBeenSet;
	}//end setHasATail
	
	boolean getAddedToTail() {
		return hasATail;
	}//end getAddedToTail
}//end Decipher class
