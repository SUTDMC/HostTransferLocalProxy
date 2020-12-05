package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;

public class S2CProxy extends Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(S2CProxy.class);

    public S2CProxy(Socket in, Socket out) {
        super(in, out);
    }

    @Override
    protected byte[] onBytesReceived(byte[] bytes, int nbytes) {
        try {
            int packetId = PacketUtils.getPacketId(bytes);
            if(packetId == 1){
                LOGGER.info("Server Encryption Request");
            } else {
                LOGGER.info("Server sent packet ID " + String.valueOf(packetId) + " to client");
            }
        } catch (RuntimeException ex){
            LOGGER.warn("Server sent packet ID with too large VarInt");
        }
        return super.onBytesReceived(bytes, nbytes);
    }
}
