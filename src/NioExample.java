import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.*;

public class NioExample {
    public static void main(String[] args) throws IOException {
        String[] inputFiles = {"file1.txt", "file2.txt"};
        var outputFile = "nioOutput.txt";

        try (var outputChannel = FileChannel.open(Path.of(outputFile), CREATE, WRITE)) {
            for (var file : inputFiles) {
                try (var inputChannel = FileChannel.open(Path.of(file), READ)) {
                    var inputBuffer = ByteBuffer.allocate((int) inputChannel.size());

                    while (inputChannel.read(inputBuffer) > 0) {
                        inputBuffer.flip();
                        outputChannel.write(inputBuffer);
                        inputBuffer.clear();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
