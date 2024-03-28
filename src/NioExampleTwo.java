import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
public class NioExampleTwo {
    public static void main(String[] args) throws IOException {
        String[] inputFiles = {"file1.txt", "file2.txt"};
        String outputFile = "OutputExampleTwo.txt";
        FileOutputStream fos = new FileOutputStream(outputFile);
        WritableByteChannel outputChannel = fos.getChannel();
        for(String file : inputFiles) {
            FileInputStream fis = new FileInputStream(file);
            FileChannel inputChannel = fis.getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            inputChannel.close();
            fis.close();
        }
        outputChannel.close();
        fos.close();
    }
}
