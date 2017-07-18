import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.IOException;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		String input = null;
		
		while(input != "encrypt" || input != "decrypt"){
			System.out.println("Welcome to the CaesarCipher tool, do you want to encrypt or decrypt?");
			input = br.readLine();
			if(input.equals("encrypt")){
				encrypt();
			} else if(input.equals("decrypt")){
				decrypt();
			}
			
		}
	}

	public static void encrypt() throws IOException{	
		int shift = 0;

		System.out.println("This is the Caesar Cipher encrypter");
		System.out.println("Enter the amount of characters you want to shift the alphabet.");
		
		while(shift < 1 || shift > 25){
			System.out.println("Pick a number between 1 and 25");
	        try{
	    		shift = Integer.parseInt(br.readLine());
	        }catch(NumberFormatException nfe){
	            System.err.println("Invalid Format!");
	        }
		}
        
		System.out.println("Enter the message that you want to encrypt: ");
		String secretMessage = br.readLine();
		
		System.out.println("Your secret message is:");
		
		System.out.println(encryptMessage(shift, secretMessage));
	}	
	
	public static String encryptMessage(int shift, String secretMessage){ 
		byte[] ascii_input = secretMessage.getBytes(StandardCharsets.US_ASCII);
		int len = secretMessage.length();
		byte[] ascii_output = new byte[len];
		
		//Hij pakt de ascii output en verhoogt de waarde met hoeveel posities het alfabet geshift moet worden.
		for(int i=0; i<ascii_input.length; i++){
			if((ascii_input[i] > 64 && ascii_input[i] < (91 - shift)) || (ascii_input[i] > 96 && ascii_input[i] < (123-shift))){
				ascii_output[i] = (byte) (ascii_input[i]+shift);
			} else if((ascii_input[i] > (64) && ascii_input[i] < (91 + shift) || (ascii_input[i] > (96) && ascii_input[i] < (123 + shift)))){
				ascii_output[i] = (byte) (ascii_input[i]-26+shift);
			} else {
				ascii_output[i] = (byte) (ascii_input[i]);
			}
		}
		
		String cipherMessage = new String(ascii_output);
		return cipherMessage;
	}
	
	private static void decrypt() throws IOException {
		System.out.println("This is the Caesar Cipher decrypter");
		System.out.println("Enter the string you wish to decrypt");
		String secretMessage = br.readLine();
		System.out.println("The most used letter in your secret message is the letter: " + getMax(secretMessage));
		System.out.println("We think this must be the letter E. Therefore the shift to decode your message should be: " + getShift(getMax(secretMessage)));
		System.out.println("Your decrypted message is: ");
		System.out.println(encryptMessage(getShift(getMax(secretMessage)), secretMessage));
	}
	
	public static String getMax(String word) {
	    if (word == null || word.isEmpty()) {
	        throw new IllegalArgumentException("input word must have non-empty value.");
	    }
	    char maxchar = ' ';
	    int maxcnt = 0;
	    // if you are confident that your input will be only ascii, then this array can be size 128.
	    int[] charcnt = new int[Character.MAX_VALUE + 1];
	    for (int i = word.length() - 1; i >= 0; i--) {
	        char ch = word.charAt(i);
	        // increment this character's cnt and compare it to our max.
	        if (++charcnt[ch] >= maxcnt) {
	            maxcnt = charcnt[ch];
	            maxchar = ch;
	        }
	    }
	    String string_maxChar = String.valueOf(maxchar);
	    return string_maxChar;
	}
	
	public static int getShift(String maxChar) {
	    int shift = 0;
	    byte[] ascii_maxChar = maxChar.getBytes(StandardCharsets.US_ASCII);
	    
	    if(ascii_maxChar[0] > 96 && ascii_maxChar[0] < 123){
	    	shift = ascii_maxChar[0] - 101;
	    } else if(ascii_maxChar[0] > 64 && ascii_maxChar[0] < 91){
	    	shift = ascii_maxChar[0] - 69;
	    }
	    return 26 - shift;
	}
}
