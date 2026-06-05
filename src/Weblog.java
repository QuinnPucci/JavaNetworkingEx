// example 4-10

import java.io.*;
import java.net.*;

public class Weblog {
	public static void main(String[] args){
		try (FileInputStream fin = new FileInputStream(args[0]);
			Reader in = new InputStreamReader(fin);
			BufferedReader bin = new BufferedReader(in);) {

			for (String entry = bin.readLine(); // this for loops is a common strategy for looping an input steram
				entry != null;
				entry = bin.readLine()) {
				// seperate ip address
				int index = entry.indexOf(' ');
				String ip = entry.substring(0, index);
				String theRest = entry.substring(index);

				// ask dns for hostname

				try {
					InetAddress address = InetAddress.getByName(ip);
					System.out.println(address.getHostName() + theRest);
				} catch (UnknownHostException ex) {
					System.out.println(entry);
				}

			}
		} catch (IOException ex) {
			System.out.println("Exception: " + ex);
		}
	}
}