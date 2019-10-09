import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnect {
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost:3306/practice?autoReconnect=true&useSSL=false";
    final private String username = "root";
    final private String password = "root";

    private Connection connection = null;
    private Statement statement = null;
    private List<Employee> employees=null;

    public List<Employee> getAllEmployees() throws SQLException {

        employees = new ArrayList<Employee>();
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(url,username,password);
            statement=connection.createStatement();
            String sqlQuery = "select * from employee";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("Emp_ID"));
                employee.setFirstName(resultSet.getString("First_Name"));
                employee.setLastName(resultSet.getString("Last_Name"));
                employee.setSalary(resultSet.getInt("Salary"));

                employees.add(employee);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
