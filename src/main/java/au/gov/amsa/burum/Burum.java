package au.gov.amsa.burum;

import java.io.IOException;

public final class Burum {

    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public Burum(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        try (TelnetSession client = new TelnetSession(host, port)) {
            TelnetConversation c = client.createConversation();
            c.waitFor("Please enter username: ");
            c.println(username);
            c.waitFor("Please enter password: ");
            c.println(password);
            c.waitFor("> ");
            c.println("?");
            c.waitFor("> ");
            c.println("quit");
            c.waitFor("Logging off...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
