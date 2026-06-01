// ex3.10 to work with 3.9
/* The textbook says ExecutorService creates/manages threads, 
Callable jobs are submitted to it, and a Future gives access to 
the result later. That is the highest-yield threading pattern left 
in the chapter. */

import java.util.concurrent.*;

public class MultithreadedMaxFinder {

    public static int max(int[] data)
            throws InterruptedException, ExecutionException {

        if (data.length == 0) {
            throw new IllegalArgumentException("array is empty");
        }

        if (data.length == 1) {
            return data[0];
        }

        FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
        FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);

        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);

        int result = Math.max(future1.get(), future2.get());

        service.shutdown();

        return result;
    }

    public static void main(String[] args) {
        int[] numbers = { 5, 12, -3, 44, 8, 19, 101, 6 };

        try {
            int max = max(numbers);
            System.out.println("Max value: " + max);
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println(ex);
        }
    }
}