
public class EncryptFormat {
	public String inputFormatter(String message) {
		Decrypt d = new Decrypt();
		boolean hasTail;
		System.out.println("\nMessage before preparation and encryption: \n" + message);
		
		//take out spaces and capitalize all letters and replaces all J with Z
		message = message.replaceAll("\\s+", "");
		message = message.replaceAll("Q", "Z");
		message = message.toUpperCase();
		
		//adds 'X' in between duplicate characters
		for(int i = 0; i < message.length() - 1; i++) {
			if (message.charAt(i) == message.charAt(i + 1)) {
				message = new StringBuffer(message).insert(i + 1, 'X').toString();
				hasTail =  true;
			}
		}//end for
		
		//add 'X' to the end if there are an odd number of characters
		if(message.length() % 2 == 1) {
			message+='X';
			hasTail =  true;
		}
		return message;
	}//end messageFormatter
}
