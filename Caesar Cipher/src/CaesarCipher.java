import java.util.Scanner;

public class CaesarCipher {
	public static void main(String[] args) {
		String message = "";
		String shift = "";
		int shiftNum = 0;
		
		boolean encrypt = false;
		boolean decrypt = false;
		boolean validProcess = false;
		
		boolean validMessage = false;
		boolean validShift = false;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Would you like to encrypt or dectrypt a message?(E/D): ");
		//reprompt if input is incorrect
		while(!validProcess) {
			String selection = input.nextLine();
			switch(selection) {
			case "E":
			case "e":
				encrypt = true;
				validProcess = true;
				break;
			case "D":
			case "d":
				decrypt = true;
				validProcess = true;
				break;
			default:
				System.out.print("Incorrect input. Try again: ");
			}//end switch
		}//end while
		
		//take input for message string
		if(encrypt) {
			System.out.print("Enter the message you want to encrypt: ");
		}
		else if(decrypt) {
			System.out.print("Enter the message you want to decrypt: ");
		}
		while(!validMessage) {
			message = input.nextLine();
			if(message.matches("^.*[^a-zA-Z .,?!].*$")) {
				System.out.print("Only enter letters. Try again: ");
			}
			else {
				validMessage = true;
			}
		}//end while
		
		//take input for shift amount
		System.out.println("Enter the shift amount: ");
		while(!validShift) {
			shift = input.nextLine();
			try {
				shiftNum = Integer.parseInt(shift);
				if(shiftNum < 0 || shiftNum > 26) {
					System.out.print("Please input a valid number: ");
				}
				else {
					validShift = true;
				}
			}//end try
			catch(NumberFormatException e){
				System.out.print("Incorrect input. Try again: ");
			}
		}//end while
		//decide where to go based off of process selection
		ProcessAndOutput bc = new ProcessAndOutput();
		input.close();
		bc.buildCipher(message, shiftNum, encrypt, decrypt);
	}//end main
}//end CeasarCipher
