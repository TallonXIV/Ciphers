
public class AutokeyCipher {
	public static void main(String[] args) {
		char[][] cipherKey = new char[26][26];
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				           'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				           'W', 'X', 'Y', 'Z'};
		
		//this segment creates a 2D array necessary to encrypting
		int starterLocation = 0;
		for(int i = 0; i < 26; i++) {
			starterLocation = i;
			for(int j = 0; j < 26; j++) {
				cipherKey[i][j] = alphabet[starterLocation];
				starterLocation++;
				
				if(starterLocation == 26) {
					starterLocation = 0;
				}
				System.out.print(cipherKey[i][j]);
			}//end for
			System.out.print("\n");
		}//end for
	}//end main
}//end class
