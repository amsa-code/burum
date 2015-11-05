package au.gov.amsa.burum;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.net.telnet.TelnetClient;

public final class Burum {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String crlf = "\r\n";

    public static void connect(String host, int port, String username, String password) {
        TelnetClient t = new TelnetClient();
        try {
            t.connect(host, port);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        StringBuilder s = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(t.getInputStream());
                PrintStream out = new PrintStream(t.getOutputStream())) {
            char ch;
            while ((ch = (char) isr.read()) != -1) {
                s.append(ch);
                System.out.print(ch);
                if (s.toString().endsWith("Please enter username:")) {
                    out.print(username);
                    out.print(crlf);
                }
            }
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

}
