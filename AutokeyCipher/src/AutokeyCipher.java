import java.util.Scanner;

public class AutokeyCipher {
	public static void main(String[] args) {
		boolean validKeyword = false;
		boolean validMessage = false;
		
		String message = "";
		String keyword = "";
		String keywordAndMessage = "";
		String encryptedMessage = "";
		
		int keywordFiller = 0;
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
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a keyword to use");
		while(!validKeyword) {
			keyword = input.nextLine();
			if(keyword.matches("^.*[^a-zA-Z ].*$")) {
				System.out.print("Only enter letters. Try again: ");
			}
			else {
				validKeyword = true;
			}
		}//end while
		
		System.out.println("Enter a message");
		while(!validMessage) {
			message = input.nextLine();
			if(message.matches("^.*[^a-zA-Z ].*$")) {
				System.out.print("Only letters. Try again: ");
			}
			else {
				validMessage = true;
			}
		}//end while
		
		keyword = keyword.replaceAll(" ", "");
		message = message.replaceAll(" ", "");
		
		keyword = keyword.toUpperCase();
		message = message.toUpperCase();
		
		for(int j = 0; j < keyword.length(); j++) {
			keywordAndMessage += keyword.charAt(j);
		}
		if(keywordAndMessage.length() <= message.length()) {
			for(int i = 0; i < message.length(); i++) {
				keywordAndMessage += message.charAt(i);
				if(keywordAndMessage.length() == message.length()) {
					break;
				}
			}//end for
		}//end if
		System.out.println(message + "\n" + keywordAndMessage);
		
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
