import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        copyFile("data.txt", "data-copy.txt");
    }

    public static void copyFile(String src, String dest) {
        File in = new File(src);
        File out = new File(dest);
        if (!in.exists()) {
            System.out.println("Source file does not exist");
            return;
        }
        if (out.exists()) {
            System.out.println("Destination file already exists and will be overwritten");
        }
        try (
                InputStream is = Files.newInputStream(in.toPath());
                OutputStream os = Files.newOutputStream(out.toPath())
        ) {
            int sum = 0;
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                sum += len;
                os.write(buffer, 0, len);
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}