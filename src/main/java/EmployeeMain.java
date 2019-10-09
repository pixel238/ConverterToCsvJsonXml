import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeMain {
    final static private String DIRECTORY_PATH = "C:\\Users\\cchowdhary\\Documents\\workSpace\\ConverterToCsvJsonXml\\filesConverted";
    public static void main(String args[]) throws SQLException, JAXBException {
        Scanner sc = new Scanner(System.in);

        DbConnect dbConnect = new DbConnect();
        Employees employees = new Employees();
        List<Employee> emps = dbConnect.getAllEmployees();
        employees.setEmployees(emps);

        System.out.println("\n\n ============ MENU ============");
        System.out.println("1. JSON File");
        System.out.println("2. XML File");
        System.out.println("3. CSV File");
        System.out.print("\nType the option:");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                writeToJsonFile(employees.getEmployees());
                System.out.println("\n\t\tJson File Written Successfully.");
                break;

            case 2:
                writeToXMLFile(employees);
                System.out.println("\n\t\tXML File Written Successfully.");
                break;

            case 3:
                writeToCsvFile(employees.getEmployees());
                System.out.println("\n\t\tCSV File Written Successfully.");
                break;

            default:
                System.out.println("Invalid Choice. Aborting...");
                break;
        }
        sc.close();
    }

    private static void writeToCsvFile(List<Employee> employees) {
        final String CSV_SEPARATOR = ",";
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("products.csv"), "UTF-8"));
            for (Employee employee : employees)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(employee.getId() <=0 ? "" : employee.getId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(employee.getFirstName().trim().length() == 0? "" : employee.getFirstName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(employee.getLastName().trim().length() == 0? "" : employee.getLastName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(employee.getSalary() < 0 ? "" : employee.getSalary());
                oneLine.append(CSV_SEPARATOR);
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}



//        String csvDataToWrite = "";
//        for (Employee employee : employees) {
//            csvDataToWrite += employee.toString();
//        }
//        FileWriterHandler fileWriterHandler = new FileWriterHandler();
//        fileWriterHandler.writeDataToFile(csvDataToWrite, DIRECTORY_PATH + "\\" + "Employee.csv");
    }

    private static void writeToXMLFile(Employees employees) throws JAXBException {
        Marshaller jaxbMarshaller = null;
        //Create a JAXB Context of the object
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        //Create a Marshaller of the JAXB Context
        jaxbMarshaller = jaxbContext.createMarshaller();
        //required formatting for the xml
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //print the file
        //jaxbMarshaller.marshal(employees, System.out);
        jaxbMarshaller.marshal(employees, new File(DIRECTORY_PATH + "\\" + "Employee.xml"));
    }

    private static void writeToJsonFile(List<Employee> employees) {
        String jsonDataToWrite = "";

        for (Employee employee : employees) {
            ObjectToJsonConverter objectToJsonConverter = new ObjectToJsonConverter();
            jsonDataToWrite += objectToJsonConverter.toJson(employee);
        }

        FileWriterHandler fileWriterHandler = new FileWriterHandler();
        fileWriterHandler.writeDataToFile(jsonDataToWrite, DIRECTORY_PATH + "\\" + "Employee.txt");

    }
}
