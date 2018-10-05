import java.util.Scanner;
public class PlayfairCipher {	
	public static void main(String[] args) {
		PlayfairCipher pc = new PlayfairCipher();
		pc.userInput();
	}//end main method
	
	public void userInput() {		
		Scanner input = new Scanner(System.in);
		
		boolean encrypt = false;
		boolean decrypt = false;
		
		//validation states
		boolean keywordValid = false;
		boolean messageValid = false;
		
		String keyword = "";
		String message = "";
		
		String processSelection;
		boolean correctProcessInput = false;
		
		System.out.println("Would you like to encrypt a message or decrypt a message?(E/D)?");
		while(!correctProcessInput) {
			processSelection = input.nextLine();
			switch(processSelection) {
			case "e":
			case "E":
				encrypt = true;
				correctProcessInput = true;
				break;
			case "d":
			case "D":
				decrypt = true;
				correctProcessInput = true;
				break;
			default:
				System.out.print("Incorrect Slection. Try again:");
			}//end switch
		}//end while
		
		System.out.print("Enter the keyword (NO SYMBOLS OR NUMBERS): ");
		while(!keywordValid) {
			keyword = input.nextLine();
			//checks if contains symbols
			if(keyword.matches("^.*[^a-zA-Z].*$")) {
				System.out.print("Incorrect. Try again (NO SYMBOLS OR NUMBERS): ");
			}
			else {
				keywordValid = true;
			}
		}//end while
		System.out.print("Enter the messsage (NO SYMBOLS): ");
		while(!messageValid) {
			message = input.nextLine();
			if(message.matches("^.*[^a-zA-Z ].*$")) {
				System.out.print("Incorrect. Try again (NO SYMBOLS OR NUMBERS): ");
			}
			else {
				messageValid = true;
			}
		}//end while
		input.close();
		
		//if the user intends to encrypt, a formatting of input is necessary
		if(encrypt == true) {
			EncryptFormat ef = new EncryptFormat();
			message = ef.inputFormatter(message);
		}
		
		TableBuilder tb = new TableBuilder();
		tb.buildTable(keyword, message, encrypt, decrypt);
	}//end userInput
}//end class