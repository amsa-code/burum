package au.gov.amsa.burum;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.telnet.TelnetClient;

public final class TelnetSession implements Closeable {

    private final String host;
    private final int port;
    private final TelnetClient client = new TelnetClient();

    public TelnetSession(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public TelnetSession connect() {
        try {
            client.connect(host, port);
            return this;
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
    }

    @Override
    public void close() throws IOException {
        client.disconnect();
    }

    public OutputStream getOutputStream() {
        return client.getOutputStream();
    }

    public InputStream getInputStream() {
        return client.getInputStream();
    }

    public TelnetConversation createConversation() {
        connect();
        return new TelnetConversation(getInputStream(), getOutputStream());
    }
}
