package project.packets.c2s;

import project.PacketUtils;
import project.PacketUtils.ReadVarIntResult;

import java.util.Arrays;

public class EncryptionResponse {

    private final byte[] shared_secret;
    private final byte[] verify_token;

    public EncryptionResponse(byte[] shared_secret, byte[] verify_token) {
        this.shared_secret = shared_secret;
        this.verify_token = verify_token;
    }


    public static EncryptionResponse make(byte[] body){
        int cursor = 0;
        ReadVarIntResult result1 = PacketUtils.readVarInt(body);
        int shared_secret_length = result1.getResult();
        cursor += result1.getBytesRead();
        byte[] shared_secret = Arrays.copyOfRange(body,cursor, cursor + shared_secret_length);
        cursor += shared_secret_length;
        ReadVarIntResult result2 = PacketUtils.readVarInt(body);
        int verify_token_length = result2.getResult();
        cursor += result2.getBytesRead();
        byte[] verify_token = Arrays.copyOfRange(body, cursor, cursor + verify_token_length);
        return new EncryptionResponse(shared_secret, verify_token);
    }


    public byte[] getSharedSecret() {
        return shared_secret;
    }

    public byte[] getVerifyToken() {
        return verify_token;
    }
}
