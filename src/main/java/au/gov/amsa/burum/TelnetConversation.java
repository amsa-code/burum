package au.gov.amsa.burum;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;

final class TelnetConversation {
    private static final String crlf = "\r\n";
    private final Reader r;
    private final PrintStream out;
    private final StringBuilder s = new StringBuilder();

    TelnetConversation(InputStream is, OutputStream os) {
        this.r = new InputStreamReader(is);
        this.out = new PrintStream(os);
    }

    void println(String s) {
        out.print(s);
        out.print(crlf);
        out.flush();
    }

    void waitFor(String value) {
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
