package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * Created by oksuz on 29/10/2017.
 */
public class Proxy implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Proxy.class);
    private final Socket in;
    private final Socket out;

    public Proxy(Socket in, Socket out) {
        this.in = in;
        this.out = out;
    }

    protected byte[] onBytesReceived(byte[] bytes, int nbytes){
        return Arrays.copyOf(bytes, nbytes);
    }

    @Override
    public void run() {
        LOGGER.info("project.Proxy {}:{} --> {}:{}", in.getInetAddress().getHostName(), in.getPort(), out.getInetAddress().getHostName(), out.getPort());
        try {
            InputStream inputStream = getInputStream();
            OutputStream outputStream = getOutputStream();

            if (inputStream == null || outputStream == null) {
                return;
            }

            byte[] reply = new byte[4096];
            int bytesRead;
            while (-1 != (bytesRead = inputStream.read(reply))) {
                byte[] transformedReply = onBytesReceived(reply, bytesRead);
                outputStream.write(transformedReply, 0, transformedReply.length);
            }
        } catch (SocketException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private InputStream getInputStream() {
        try {
            return in.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private OutputStream getOutputStream() {
        try {
            return out.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}