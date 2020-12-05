package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;
import java.util.Arrays;
import java.util.Base64;

import project.packets.c2s.EncryptionResponse;

public class C2SProxy extends Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(C2SProxy.class);

    public C2SProxy(Socket in, Socket out) {
        super(in, out);
    }

    @Override
    protected byte[] onBytesReceived(byte[] bytes, int nbytes) {
        try {
            int packetId = PacketUtils.getPacketId(bytes);
            PacketUtils.ReadVarIntResult result1 = PacketUtils.readVarInt(bytes);
            PacketUtils.ReadVarIntResult result2 = PacketUtils.readVarInt(Arrays.copyOfRange(bytes, result1.getBytesRead(), bytes.length));
            byte[] body = Arrays.copyOfRange(bytes, result1.getBytesRead() + result2.getBytesRead(), bytes.length);
            if(packetId == 0){
                LOGGER.info("Client Handshake");
            } else if(packetId == 1){
                LOGGER.info("Client Encryption Response");
                EncryptionResponse encryptionResponse = EncryptionResponse.make(body);
                LOGGER.info("Stole shared secret of length " + encryptionResponse.getSharedSecret().length);
            } else {
                LOGGER.info("Client sent packet ID " + String.valueOf(packetId) + " to server");
            }
        } catch (RuntimeException ex){
            LOGGER.error("Exception while parsing packet", ex);
        }
        return super.onBytesReceived(bytes, nbytes);
    }
}
