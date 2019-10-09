import java.io.FileWriter;
import java.io.IOException;

public class FileWriterHandler {
    public void writeDataToFile(String data, String fileNameWithPath) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileNameWithPath);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
