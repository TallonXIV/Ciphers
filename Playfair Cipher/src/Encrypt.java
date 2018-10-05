
public class Encrypt {
	public void encryptionAlgorithm(String message, char[] alphabet, char[] used, char[][] table) {
		String encryption = "";
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
		System.out.println("\n" + "Message after preparation, but before encryption: \n" + message + "\n");
		System.out.println("Pairs derived from message string: ");
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
						//this stops these values from carrying over into the next pair searches
						if(i == 4 && j == 4) {
							El1Found = false;
							El2Found = false;
						}
						
						//if both values of the pair have had their coordinates discovered
						if(El1Found == true && El2Found == true) {	
							//Enforcement of Rule 1: matching columns
							if(rowOfElOne == rowOfElTwo) {
								//this check allows to wrap around to the other side if it goes of
								if(colOfElOne != 4){
									pair[0] = table[rowOfElOne][colOfElOne + 1];
								}
								else {
									pair[0] = table[rowOfElOne][0];
								}
								
								if(colOfElTwo != 4) {
									pair[1] = table[rowOfElTwo][colOfElTwo + 1];
								}
								else {
									pair[1] = table[rowOfElTwo][0];
								}
								encryption += pair[0];
								encryption += pair[1];
							}//end if
							//Enforcement of Rule 2: matching rows
							else if(colOfElOne == colOfElTwo) {
								//this check allows to wrap around to the other side if it goes off
								if(rowOfElOne != 4) {
									pair[0] = table[rowOfElOne + 1][colOfElOne];
								}
								else {
									pair[0] = table[0][colOfElOne];
								}
								
								if(rowOfElTwo != 4) {
									pair[1] = table[rowOfElTwo + 1][colOfElTwo];
								}
								else {
									pair[1] = table[0][colOfElTwo];
								}
								encryption += pair[0];
								encryption += pair[1];
							}//end else
							//Enforcement of Rule 3: Rectangular formation
							else {
								pair[0] = table[rowOfElOne][colOfElTwo];
								pair[1] = table[rowOfElTwo][colOfElOne];
								encryption += pair[0];
								encryption += pair[1];
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
		System.out.println("\n");
		System.out.println(encryption);
	}//end encryptionAlgorithm
}
