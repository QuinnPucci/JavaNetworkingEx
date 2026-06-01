// example 3-13
// Executors and threadpools
// submit tasks to the executor and teh thread pool
// suppose you want to gzip every file in current dir 

import java.io.*;
import java.util.zip.*;

public class GZipRunnable implements Runnable {

	private final File input;

	public GZipRunnable(File input){
		this.input = input;
	}

	public void run() {
		// don't compress if already compressed
		if (!input.getName().endsWith(".gz")) {
			File output = new File(input.getParent(), input.getName() + ".gz");
			if (!output.exists()) { // Dont overwrite existing
				try ( // with resources 
					InputStream in = new BufferedInputStream(new FileInputStream(input));
					OutputStream out = new BufferedOutputStream (
						new GZIPOutputStream(
							new FileOutputStream(output)));
				) {
					int b;
					while ((b = in.read()) != -1) out.write(b);
					out.flush();
				} catch (IOException ex) {
					System.err.println(ex);
				}
			}
		}
	}
}