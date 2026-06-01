// example 3-8 modernized

import java.util.HexFormat;

public class InstanceCallbackDigestUserInterface {

    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }

    public void calculateDigest() {
        InstanceCallbackDigest cb =
            new InstanceCallbackDigest(filename, this);

        Thread t = new Thread(cb);
        t.start();
    }

    void receiveDigest(byte[] digest) {
        this.digest = digest;
        System.out.println(this);
    }

    @Override
    public String toString() {
        String result = filename + ": ";

        if (digest != null) {
            result += HexFormat.of().formatHex(digest).toUpperCase();
        } else {
            result += "digest not available";
        }

        return result;
    }

    public static void main(String[] args) {
        for (String filename : args) {
            InstanceCallbackDigestUserInterface d =
                new InstanceCallbackDigestUserInterface(filename);

            d.calculateDigest();
        }
    }
}