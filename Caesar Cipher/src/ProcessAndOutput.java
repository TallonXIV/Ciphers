
public class ProcessAndOutput {
	public void buildCipher(String message, int shiftNum, boolean encrypt, boolean decrypt){
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
		
		//if the user wants to encrypt
		if(encrypt) {
			for(int i = 0; i < message.length(); i++) {
				char e = message.charAt(i);
				switch(e) {
					case ' ':
						encryption += ' ';
						break;
					case '.':
						encryption += '.';
						break;
					case ',':
						encryption += ',';
						break;
					case '?':
						encryption += '?';
						break;
					case '!':
						encryption += '!';
						break;
					default:
						for(int j = 0; j < 26; j++) {
							if(e == alphabet[j]) {
								encryption += cipherKey[j];
							}
						}//end for
				}//end switch
			}//end for
		}//end if
		
		//if the user wants to decrypt
		if(decrypt) {
			for(int i = 0; i < message.length(); i++) {
				char e = message.charAt(i);
				switch(e) {
					case ' ':
						encryption += ' ';
						break;
					case '.':
						encryption += '.';
						break;
					case ',':
						encryption += ',';
						break;
					case '?':
						encryption += '?';
						break;
					case '!':
						encryption += '!';
						break;
					default:
						for(int j = 0; j < 26; j++) {
							if(message.charAt(i) == cipherKey[j]) {
								encryption += alphabet[j];
							}
						}//end for
				}//end switch
			}//end for
		}//end if
		//output result
		System.out.println("Result: " + encryption);
	}//end buildCipher
}
