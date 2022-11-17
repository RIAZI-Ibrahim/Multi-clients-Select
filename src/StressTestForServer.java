
import java.io.IOException;
import java.security.SecureRandom;

public class StressTestForServer {
  private static SecureRandom sr = new SecureRandom();

  public static void main(String[] args) throws IOException {
    for (int i = 0; i < 20_000; i++) {
      Client client = new Client("localhost", 2022);
      System.out.println("Test numebr#" + (i + 1));
      client.open(getRandomText(sr.nextInt(10) + 1));
      System.out.println("\n" + "#".repeat(40) + "\n");
    }
  }

  public static String getRandomText(int lines) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < lines; i++) {
      for (int j = 0; j < sr.nextInt(10) + 1; j++) sb.append((char) (sr.nextInt(90 - 65 + 1) + 65));
      sb.append("\n");
    }
    return sb.toString();
  }
}
