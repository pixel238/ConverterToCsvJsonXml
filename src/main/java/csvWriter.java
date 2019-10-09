//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//public class csvWriter {
//    List<List<String>> rows = Arrays.asList(
//            Arrays.asList("Jean", "author", "Java"),
//            Arrays.asList("David", "editor", "Python"),
//            Arrays.asList("Scott", "editor", "Node.js")
//    );
//
//    FileWriter writer = new FileWriter("test.csv");
//    writer.append("Name");
//    writer.append(",");
//    writer.append("Role");
//    writer.append(",");
//    writer.append("Topic");
//    writer.append("\n");
//
//for (List<String> rowData : rows) {
//        writer.append(String.join(",", rowData));
//        writer.append("\n");
//    }
//
//    public csvWriter() throws IOException {
//    }
//
//csvWriter.flush();
//csvWriter.close();
//}
