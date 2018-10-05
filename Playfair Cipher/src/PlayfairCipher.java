import java.util.Scanner;
public class PlayfairCipher {	
	public static void main(String[] args) {
		PlayfairCipher pc = new PlayfairCipher();
		pc.userInput();
	}//end main method
	
	public void userInput() {		
		Scanner input = new Scanner(System.in);
		
		//validation states
		boolean keywordValid = false;
		boolean messageValid = false;
		
		String keyword = "";
		String message = "";
		
		//user input validation for keywordValid and messageValid based on input
		System.out.print("Enter a keyword/phrase to act as the encryption key (NO SYMBOLS OR NUMBERS): ");
		while(!keywordValid) {
			keyword = input.nextLine();
			//checks if contains symbols
			if(keyword.matches("^.*[^a-zA-Z ].*$")) {
				System.out.print("Incorrect. Try again (NO SYMBOLS OR NUMBERS): ");
			}
			else {
				keywordValid = true;
			}
		}//end while
		System.out.print("Enter a message to encrypt: ");
		while(!messageValid) {
			message = input.nextLine();
			if(message.matches("^.*[^a-zA-Z ].*$")) {
				System.out.print("Incorrect. Try again (NO SYMBOLS OR NUMBERS): ");
			}
			else {
				messageValid = true;
			}
		}//end while
		
		//method calls
		PlayfairCipher pc = new PlayfairCipher();
		message = pc.messageFormatter(message);
		pc.buildTable(keyword, message);
	}//end userInput
	
	public String messageFormatter(String message) {
		System.out.println("\nMessage before preparation and encryption: \n" + message);
		
		//take out spaces and capitalize all letters
		message = message.replaceAll("\\s+", "");
		message = message.replaceAll("J", "Z");
		message = message.toUpperCase();
		
		//adds 'X' in between duplicate characters
		for(int i = 0; i < message.length() - 1; i++) {
			if (message.charAt(i) == message.charAt(i + 1)) {
				message = new StringBuffer(message).insert(i + 1, 'X').toString();
			}
		}//end for
		
		//add 'X' to the end if there are an odd number of characters
		if(message.length() % 2 == 1) {
			message+='X';
		}
		
		return message;
	}//end messageFormatter
	
	//this builds the table, used array, and allocated the phrase and alphabet in order
	public void buildTable(String keyword, String message){	
		int iterationCounter = 0;
		int numberUsed = 0;
		boolean hasBeenUsed = false;
		boolean running = true;
		boolean validAlpha = true;
		int alphabetNum = 0;
		
		//declaration of necessary arrays
		char[] alphabet = {'A','B','C','D','E',
				   'F','G','H','I','K',
				   'L','M','N','O','P',
				   'Q','R','S','T','U',
				   'V','W','X','Y','Z'};
		char[] used = new char[25];
		char[][] table = new char[5][5];
		
		//remove all spaces in keyword/phrase
		keyword = keyword.replaceAll("\\s+","");
		keyword = keyword.replaceAll("J", "Z");
		keyword = keyword.toUpperCase();
		
		while(running) {
			//iterates through entire 2D array
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					//check to see if the entire key word/phrase has been put into the table yet
					if(iterationCounter < keyword.length()) {
						//check for duplicates
						for(int check = 0; check < iterationCounter; check++) {
							char e = keyword.charAt(iterationCounter);
							//if it is a duplicate
							if(e == used[check]) {
								hasBeenUsed = true;
								break;
							}
						}//end for
						//if not a duplicate...
						if(!hasBeenUsed) {
							table[i][j] = keyword.charAt(iterationCounter);						
							used[numberUsed] = keyword.charAt(iterationCounter);
							iterationCounter++;
							numberUsed++;
						}
						//else if it is...
						else {
							//reset boolean, increment iteration counter
							hasBeenUsed = false;
							iterationCounter++;
							
							//make it so that it does not leave a blank element in the array
							j--;
						}
					}//end if
					//after all elements of the key word/phrase have been put into the table...
					else {
						if(alphabetNum < 25) {
							//check for repeats in the used array
							for(int alphaCheck = 0; alphaCheck < 25; alphaCheck++) {
								if(alphabet[alphabetNum] == used[alphaCheck]) {
									validAlpha = false;
								}
							}//end for
							if(validAlpha == true) {
								table[i][j] = alphabet[alphabetNum];
							}
							else {
								j--;
								validAlpha = true;
							}
							alphabetNum++;
						}//end for
						else {
							running = false;
							//harsh but works by breaking the for-loop iterations
							i = 5;
							j = 5;
							break;
						}
					}//end else
				}//end for
			}//end for
		}//end while
		//Print table
		System.out.println("\nEncryption Table:");
		System.out.println("___________________");
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print("_" + table[i][j] + "_|");
			}
			System.out.print("\n");
		}//end for
		
		//method call to algorithms
		PlayfairCipher pc = new PlayfairCipher();
		pc.encryptionAlgorithm(message, alphabet, used, table);
	}//end buildTable
	
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
}//end class