// example 3-12 modernized
// for joining threads
// if one thread needs the result of another thread
// join() methods to allow one thread to 
// wait for another thread to finish before continuing.

import java.util.HexFormat;

public class JoinDigestUserInterface {

    public static void main(String[] args) {

        ReturnDigest[] digestThreads = new ReturnDigest[args.length];

        for (int i = 0; i < args.length; i++) {
            digestThreads[i] = new ReturnDigest(args[i]);
            digestThreads[i].start();
        }

        for (int i = 0; i < args.length; i++) {
            try {
                digestThreads[i].join();

                StringBuilder result = new StringBuilder(args[i]);
                result.append(": ");

                byte[] digest = digestThreads[i].getDigest();

                if (digest != null) {
                    result.append(
                        HexFormat.of().formatHex(digest).toUpperCase()
                    );
                } else {
                    result.append("digest not available");
                }

                System.out.println(result);

            } catch (InterruptedException ex) {
                System.err.println("Thread interrupted before completion");
            }
        }
    }
}