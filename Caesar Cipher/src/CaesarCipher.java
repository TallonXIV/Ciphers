import java.util.Scanner;

public class CaesarCipher {
	public static void main(String[] args) {
		String message = "";
		String shift = "";
		int shiftNum = 0;
		
		boolean validMessage = false;
		boolean validShift = false;
		
		Scanner input = new Scanner(System.in);
		
		//take input for message string
		System.out.print("Enter the message you want to encrypt: ");
		while(!validMessage) {
			message = input.nextLine();
			if(message.matches("^.*[^a-zA-Z ].*$")) {
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
		BuildCipherAndOutput bc = new BuildCipherAndOutput();
		input.close();
		bc.buildCipher(message, shiftNum);
	}//end main
}//end CeasarCipher
