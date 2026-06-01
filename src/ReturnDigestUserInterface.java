// example 3-4 modernized
// intentionally flawed race-condition example

import java.util.HexFormat;

public class ReturnDigestUserInterface {

    public static void main(String[] args) {

        for (String filename : args) {

            // calculate the digest
            ReturnDigest dr = new ReturnDigest(filename);
            dr.start();

            // now print the result
            StringBuilder result = new StringBuilder(filename);
            result.append(": ");

            byte[] digest = dr.getDigest();

            result.append(
                HexFormat.of().formatHex(digest).toUpperCase()
            );

            System.out.println(result);
        }
    }
}