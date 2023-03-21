
/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple �substitution cipher� where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager {
	
	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds (String plainText) {
		 // Loop over each character in the plaintext
		for (int i = 0; i < plainText.length(); i++) {
			// If the character is out of bounds, return false
			if (plainText.charAt(i) < LOWER_RANGE || plainText.charAt(i) > UPPER_RANGE)
				return false;
		}
		// Else return true
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key) {
		// Convert plainText to uppercase and create an empty string
				plainText = plainText.toUpperCase();
				String str = "";
				
				// Check if string is in bounds
				if (isStringInBounds(plainText)) {
				
					for (int i = 0; i < plainText.length(); i++) {
						// Append char to string
						str += (char)(((int)plainText.charAt(i) + key - 65) % 59 + 65);
					}
				}
				
				// Return encrypted text
				return str;
			}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption (String plainText, String bellasoStr) {
		// Convert plainText to uppercase and create an empty string
				plainText = plainText.toUpperCase();
				String str = "";
				
				// Generate key and convert to uppercase
				String key = getBellasoKey(plainText, bellasoStr);
				key = key.toUpperCase();

				// Iterate through each character
				for (int i = 0; i < plainText.length(); ++i) {
					int n = (((plainText.charAt(i)) + (key.charAt(i) - 32)) % RANGE) + 32;
					
					// Convert ASCII to char and append to the string
					str += (char)(n); 
				}
				
				// Return encrypted text
				return str;
			}
			
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption (String encryptedText, int key) {
		// Convert encrypted text to uppercase and create an empty string
				encryptedText = encryptedText.toUpperCase();
				String str = "";
				
				if (isStringInBounds(encryptedText)) {
					for (int i = 0; i < encryptedText.length(); i++) {
						str += (char)(((int)encryptedText.charAt(i) - key - 65) % 59 + 65);
					}
				}
				
				// Return original text
				return str;
			}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr) {
		// Convert encrypted text to uppercase and create an empty string
				encryptedText = encryptedText.toUpperCase();
				String str = "";
				
				// Generate key and convert to uppercase
				String key = getBellasoKey(encryptedText, bellasoStr);
				key = key.toUpperCase();
				
				for (int i = 0; i < encryptedText.length(); i++) {
					int n = (((encryptedText.charAt(i)) - (key.charAt(i) - 32)) % RANGE) + 32;
					
					if (n < LOWER_RANGE || n > UPPER_RANGE) {
						n = (((encryptedText.charAt(i)) - (key.charAt(i) - 32)) % RANGE) + (32 + RANGE);
					}
					
					// Convert to char and append
					str += (char)(n);
				}
				
				// Return original text
				return str;
			}
			
			/** Zakir Faizi
			 * Generates bellaso key by repeating string until length is matched
			 * @param text Desired key
			 * @param bellasoStr New, formatted key
			 * @return
			 */
			public static String getBellasoKey(String text, String bellasoStr) {
				// Store key length
				int textLen = text.length();
				
				// Iterate until length is matched
				for (int i = 0; ; i++) {
					// If the length has caught up to i, reset i
					if (textLen == i)
						i = 0;
					// If the lengths match, break
					if (bellasoStr.length() == text.length())
						break;
					// Append the character at the ith position to the end of the key
					bellasoStr += (bellasoStr.charAt(i));
				}
				
				// Return formatted key
				return bellasoStr;
			}
		}
