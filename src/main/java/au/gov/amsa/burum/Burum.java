package au.gov.amsa.burum;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

import org.apache.commons.net.telnet.TelnetClient;

public final class Burum {

    private static final String crlf = "\r\n";

    public static void connect(String host, int port, String username, String password) {
        TelnetClient t = new TelnetClient();
        try {
            t.connect(host, port);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        StringBuilder s = new StringBuilder();
        try (Reader r = new InputStreamReader(t.getInputStream());
                PrintStream out = new PrintStream(t.getOutputStream())) {
            waitFor(r, s, "Please enter username: ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                t.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void waitFor(Reader r, StringBuilder s, String value) {
        char ch;
        try {
            while ((ch = (char) r.read()) != -1) {
                s.append(ch);
                System.out.print(ch);
                if (s.toString().endsWith(value))
                    return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
