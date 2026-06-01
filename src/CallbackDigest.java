import java.io.*;
import java.security.*;

// this class is the worker thread

public class CallbackDigest implements Runnable {
	private String filename;

	public CallbackDigest(String filename){
		this.filename = filename;
	}

	@Override 
	public void run() {
		try {
			
			// all the thread is doing is creating an input stream and getting the hash
			FileInputStream in = new FileInputStream(filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while (din.read() != -1);
			din.close();
			byte[] digest = sha.digest();


			// this is the actual callback, to the "main thread"
			// which is name the user interface
			// the calculation is passed to it when complete
			CallbackDigestUserInterface.receiveDigest(digest, filename);

		} catch (IOException ex) {
			System.err.println(ex);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
	}
}