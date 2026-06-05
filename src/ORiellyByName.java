// example 4-1

import java.net.*;

public class ORiellyByName {

	public static void main (String[] args) {
		try {
			InetAddress address = InetAddress.getByName("www.orielly.com");
			System.out.println(address);
		} catch (UnknownHostException ex) {
			System.out.println("Could not find");
		}
	}
}