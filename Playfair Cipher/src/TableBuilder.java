public class TableBuilder {
	public void buildTable(String keyword, String message, boolean encrypt, boolean decrypt){			
		int iterationCounter = 0;
		int numberUsed = 0;
		boolean hasBeenUsed = false;
		boolean running = true;
		boolean validAlpha = true;
		int alphabetNum = 0;
		
		//declaration of necessary arrays
		char[] alphabet = {'A','B','C','D','E',
				   'F','G','H','I','J',
				   'K','L','M','N','O',
				   'P','R','S','T','U',
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
		System.out.println("\nEncryption Table based on keyword/phrase:");
		System.out.println("___________________");
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print("_" + table[i][j] + "_|");
			}
			System.out.print("\n");
		}//end for
		
		//method call to algorithms
		if(encrypt == true) {
			Encrypt e = new Encrypt();
			e.encryptionAlgorithm(message, alphabet, used, table);
		}
		else if(decrypt == true){
			Decrypt d = new Decrypt();
			d.decryptAlgorithm(message, alphabet, used, table);
		}
	}//end buildTable
}
