package au.gov.amsa.burum;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.net.telnet.TelnetClient;

public final class Burum {

    public static void connect(String host, int port) {
        TelnetClient t = new TelnetClient();
        try {
            t.connect(host, port);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        try (InputStreamReader isr = new InputStreamReader(t.getInputStream())) {
            int ch;
            while ((ch = isr.read()) != -1) {
                System.out.print((char) ch);
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
