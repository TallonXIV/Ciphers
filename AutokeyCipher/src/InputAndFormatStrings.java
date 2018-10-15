import java.util.Scanner;

public class InputAndFormatStrings {
	public String takeAndFormatInputs(String input) {
		Scanner in = new Scanner(System.in);
		boolean validInput = false;
		while(!validInput) {
			input = in.nextLine();
			if(input.matches("^.*[^a-zA-Z ].*$")) {
				System.out.print("Only enter letters. Try again: ");
				validInput = false;
			}
			else {
				validInput = true;
			}
		}//end while
		input = input.replaceAll(" ", "");
		input = input.toUpperCase();
		return input;
	}//end formatInputs
	
	public String buildKeywordAndMessageString(String keyword, String message) {
		String keywordAndMessage = "";
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
		return keywordAndMessage;
	}//end buildKeyWordAndMessageString
}
