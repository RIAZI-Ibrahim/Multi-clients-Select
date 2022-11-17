
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class Server {
  public static void main(String[] args) throws IOException {
    ServerSocketChannel ssc = ServerSocketChannel.open();
    ssc.bind(new InetSocketAddress(2022));
    ssc.configureBlocking(false);

    while (true) {
      System.out.print("\rWaiting for Connection! " + new Date());
      SocketChannel sc = ssc.accept();
      if (sc != null) {
        ByteBuffer bufferIn = ByteBuffer.allocate(256);

        sc.read(bufferIn);
        String result = new String(bufferIn.array()).trim();

        System.out.println("\n" + "#".repeat(40) + "\n");

        System.out.println("\nReceived connection from " + sc.getRemoteAddress());
        System.out.println("The client message is:\n" + result);
        System.out.println("the message from client contains: " + result.trim().lines().count());
        System.out.println("\n" + "#".repeat(40) + "\n");
        bufferIn.clear();
        bufferIn.rewind();
        String msg = "You sent " + result.trim().lines().count() + " lines!";

        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        buffer.rewind();
        sc.write(buffer);
        sc.close();
      }
    }
  }
}
