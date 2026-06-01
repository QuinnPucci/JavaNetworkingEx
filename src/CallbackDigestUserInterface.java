// ex 3.6 (works with 3.5) 
// this is the "main thread"
// where the main method is and the callback method 
import java.util.*;

public class CallbackDigestUserInterface {
	// this method handles the read, print, and processing of the calculation
	public static void receiveDigest (byte[] digest, String name) {
		StringBuilder result = new StringBuilder(name); 
		result.append(": ");
		result.append(HexFormat.of().formatHex(digest).toUpperCase());
		System.out.println(result);
	}

	public static void main(String[] args) {
		for (String filename : args) {
			// Calculate digest
			CallbackDigest cb = new CallbackDigest(filename);
			Thread t = new Thread(cb);
			t.start();
		}
	}
}