// ex 3-14 for working with 3-13
/* constructs the pool with a fixed thread count of four, 
and iterates through all the files and directories listed on the command line. 
Each of those files and files in those directories is used to 
construct a GZipRunnable. This runnable is submitted to the pool 
for eventual processing by one of the four threads.
*/

import java.io.*;
import java.util.concurrent.*;

public class GZipAllFiles {

	public final static int THREAD_COUNT = 4;

	public static void main(String[] args) {

		ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

		for (String filename : args) {
			File f = new File(filename);
			if (f.exists()) {
				if (f.isDirectory()) {
					File[] files = f.listFiles();
					for (int i = 0; i < files.length; i++) {
						if (!files[i].isDirectory()) { // dont recurse
							Runnable task = new GZipRunnable(files[i]);
							pool.submit(task);
						}
					}
				} else {
					Runnable task = new GZipRunnable(f);
					pool.submit(task);
				}
			}
		}
		pool.shutdown();
	}
}