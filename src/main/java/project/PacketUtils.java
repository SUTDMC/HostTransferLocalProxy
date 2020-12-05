package project;

import java.util.Arrays;

public class PacketUtils {

    public static class ReadVarIntResult {
        private final int result;
        private final int bytesRead;

        public ReadVarIntResult(int result, int bytesRead) {
            this.result = result;
            this.bytesRead = bytesRead;
        }

        public int getResult() {
            return result;
        }

        public int getBytesRead() {
            return bytesRead;
        }

    }

    public static ReadVarIntResult readVarInt(byte[] bytes) {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = bytes[numRead];
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);

        return new ReadVarIntResult(result, numRead);
    }

    public static int getPacketId(byte[] bytes){
        ReadVarIntResult result1 = readVarInt(bytes);
        ReadVarIntResult result2 = readVarInt(Arrays.copyOfRange(bytes, result1.getBytesRead(), bytes.length));
        return result2.getResult();
    }
}
