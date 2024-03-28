import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
public class NioExample {
    public static void main(String[] args) throws IOException {
        String[] inputFiles = {"file1.txt", "file2.txt"};
        String outputFile = "nioOutput.txt";
        FileOutputStream fos = new FileOutputStream(outputFile);
        FileChannel outputChannel = fos.getChannel();
        int outputBufferSize = (int) outputChannel.size();
        ByteBuffer outputBuffer = ByteBuffer.allocate(outputBufferSize);
        for (String file : inputFiles) {
            FileInputStream fis = new FileInputStream(file);
            FileChannel inputChannel = fis.getChannel();
            int inputBufferSize = (int) inputChannel.size();
            ByteBuffer inputBuffer = ByteBuffer.allocate(inputBufferSize);
            System.out.println((char) inputBuffer.get());
            while (inputChannel.read(inputBuffer) > 0) {
                inputBuffer.flip();
                while (inputBuffer.hasRemaining()) {
                    //System.out.println((char) inputBuffer.get());
                    outputChannel.write(inputBuffer);
                }
            }

        }
        outputChannel.close();
        fos.close();
    }
}
