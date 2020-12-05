package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by oksuz on 29/10/2017.
 */
public class AppMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppMain.class);

    public static void main(String args[]) {

        String remoteHost = args[0];
        int remotePort = 25565;
        int port = 25565;

        LOGGER.info("Starting proxy server on port {} for remote {}:{}", port, remoteHost, remotePort);

        TcpIpProxy tcpIpProxy = new TcpIpProxy(remoteHost, remotePort, port);
        tcpIpProxy.listen();
    }


}