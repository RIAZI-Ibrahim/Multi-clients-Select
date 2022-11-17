import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client2 {

    private String url;
    private int port;

    public Client2(String url, int port) {
        this.port = port;
        this.url = url;
    }

    public void open(String text) {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            InetSocketAddress addr = new InetSocketAddress("localhost", 2022);
            sc.connect(addr);
            StringBuilder sb = new StringBuilder();

            while (!sc.finishConnect()) System.out.println("waiting");
            byte[] message = text.getBytes();
            ByteBuffer bufferGo = ByteBuffer.wrap(message);
            sc.write(bufferGo);
            bufferGo.clear();

            ByteBuffer buffer = ByteBuffer.allocate(text.length());
            while (sc.read(buffer) >= 0) {
                buffer.flip();
                while (buffer.hasRemaining()) sb.append((char) buffer.get());
                buffer.clear();
            }
            System.out.println("Sent " + text.lines().count() + " lines to server:\n" + text);
            System.out.println("the server response: " + sb);

            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
