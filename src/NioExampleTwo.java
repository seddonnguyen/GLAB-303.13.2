import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class NioExampleTwo {
    public static void main(String[] args) throws IOException {
        String[] inputFiles = {"file1.txt", "file2.txt"};
        String outputFile = "OutputExampleTwo.txt";

        try (WritableByteChannel outputChannel = FileChannel.open(Path.of(outputFile), CREATE, WRITE)) {
            for (var file : inputFiles) {
                try (var inputChannel = FileChannel.open(Path.of(file), READ)) {
                    // transferTo(long position, long count, WritableByteChannel target)
                    // Transfers bytes from this channel's file to the given writable byte channel.
                    inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                }
            }
        }
    }
}
