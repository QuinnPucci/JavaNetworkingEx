// ex 3.1

import java.io.*;
import java.security.*;
// NOT MODERN import javax.xml.bind.*;
import java.util.*;

public class DigestThread extends Thread {
	private String filename;

	public DigestThread(String filename){
		this.filename = filename;
	}

	@Override
	public void run() {
		try { 
			FileInputStream in = new FileInputStream(filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while (din.read() != -1);
			din.close();
			byte[] digest = sha.digest(); 

			StringBuilder result = new StringBuilder(filename);
			result.append(": ");

			// THIS LINE DOES NOT WORK IN JDK 25
			// result.append(DatatypeConverter.printHexBinary(digest));
			// alternative: 
			result.append(HexFormat.of().formatHex(digest).toUpperCase());

			System.out.println(result);
		} catch (IOException ex){
			System.err.println(ex);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
	}

	public static void main(String[] args) {
		for (String filename : args) {
			Thread t = new DigestThread(filename);
			t.start(); 
		}
	}

}