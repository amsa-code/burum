package au.gov.amsa.burum;

import java.io.IOException;
import java.net.UnknownHostException;

public class LesMain {

    public static void main(String[] args) throws UnknownHostException, IOException {
        String host = args[0];
        int port = Integer.parseInt(args[1]); // default telnet port
        String username = args[2];
        String password = args[3];
        new Les(host, port, username, password).test();
    }

}
