
public class BuildCipherAndOutput {
	public void buildCipher(String message, int shiftNum){
		String encryption = "";
		message = message.toUpperCase();
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F',
						   'G', 'H', 'I', 'J', 'K', 'L',
						   'M', 'N', 'O', 'P', 'Q', 'R',
						   'S', 'T', 'U', 'V', 'W', 'X',
						   'Y', 'Z'};
		char[] cipherKey = new char[26];
		int iterator = 0;
		
		//fills array with values starting at entry point (shiftNum)
		for(int i = shiftNum; i < 26; i++) {
			cipherKey[i] = alphabet[iterator];
			iterator++;
		}
		
		//fills array with remaining values, giving a "wrap effect"
		int wrapValue = shiftNum;
		for(int i = 0; i < shiftNum; i++) {
			cipherKey[i] = alphabet[(26 - (wrapValue - i))];
		}
		
		for(int i = 0; i < message.length(); i++) {
			if(message.charAt(i) == ' ') {
				encryption += ' ';
			}
			else {
				for(int j = 0; j < 26; j++) {
					if(message.charAt(i) == alphabet[j]) {
						encryption += cipherKey[j];
					}
				}//end for
			}//end else
		}//end for
		System.out.println(encryption);
		/*output cipherKey array for test
		for(int i = 0; i < 26; i++) {
			System.out.println(cipherKey[i]);
		}*/
	}//end buildCipher
}
