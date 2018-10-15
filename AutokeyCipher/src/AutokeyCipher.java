public class AutokeyCipher {
	public static void main(String[] args) {
		String message = "";
		String keyword = "";
		String keywordAndMessage = "";
		String encryptedMessage = "";
		
		int pair1XVal = 0;
		int pair2YVal = 0;
		
		char[] pairArray = new char[2];
		char[][] vigenereTable = new char[26][26];
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				           'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				           'W', 'X', 'Y', 'Z'};
		
		//this segment creates a vigenere table with a 2D array
		int starterLocation = 0;
		System.out.println("Vigenere Table: \n");
		for(int i = 0; i < 26; i++) {
			starterLocation = i;
			for(int j = 0; j < 26; j++) {
				vigenereTable[i][j] = alphabet[starterLocation];
				starterLocation++;
				
				if(starterLocation == 26) {
					starterLocation = 0;
				}
				System.out.print(vigenereTable[i][j] + " ");
			}//end for
			System.out.print("\n");
		}//end for
		
		//take and format strings
		InputAndFormatStrings fs = new InputAndFormatStrings();
		
		System.out.println("Enter a keyword to use");
		keyword = fs.takeAndFormatInputs(keyword);
		
		System.out.println("Enter a message to encrypt");
		message = fs.takeAndFormatInputs(message);
		keywordAndMessage = fs.buildKeywordAndMessageString(keyword, message);
		
		System.out.println(message + "\n" + keywordAndMessage);
		
		//encrypt message based on each letter of the keyword and message
		for(int i = 0; i < message.length(); i++) {
			pairArray[0] = message.charAt(i);
			pairArray[1] = keywordAndMessage.charAt(i);
			for(int x = 0; x < 26; x++) {
				if(pairArray[0] == vigenereTable[0][x]) {
					pair1XVal = x;
				}
				if(pairArray[1] == vigenereTable[x][0]) {
					pair2YVal = x;
				}
			}//end for
			encryptedMessage += vigenereTable[pair1XVal][pair2YVal];
		}//end for
		System.out.println(encryptedMessage);
	}//end main
	
}//end class
