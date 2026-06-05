import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class PooledWebLog {
	private final static int NUM_THREADS = 4; 

	public static void main(String[] args) throws IOException {
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		Queue<LogEntry> results = new LinkedList<LogEntry>();
		// linked list implements queue
		
		try (BufferedReader in = new BufferedReader(
			new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));) {
			for (String entry = in.readLine(); entry != null; entry = in.readLine()){
				LookupTask task = new LookupTask(entry);
				Future<String> future = executor.submit(task);
				LogEntry result = new LogEntry(entry, future);
				results.add(result);
			}
		}

		// Start printing results
		for (LogEntry result : results) {
			try {
				System.out.println(result.future.get());
			} catch (InterruptedException | ExecutionException ex) {
				System.out.println(result.original);
			}
		}

		executor.shutdown();
	}

	private static class LogEntry {
		String original;
		Future<String> future;

		LogEntry(String original, Future<String> future) {
			this.original = original;
			this.future = future; 
		}
	}
}