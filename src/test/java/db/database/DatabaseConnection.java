package db.database;

import org.testng.Assert;

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) throws SQLException {
        /* IN ORDER TO CONNECT TO THE DATABASE, WE NEED A URL, USERNAME, PASSWORD, AND QUERY */

        // to find URL open Oracle SQL database and click on (+) sign in connection tab and then second item on the right copy URL.
        String url = "jdbc:oracle:thin:@batch4db1.cup7q3kvh5as.us-east-2.rds.amazonaws.com:1521/ORCL";
        String username = "chriskaly";
        String password = "chriskaly123!";
        String query = "select * from employees";
git
        //CREATING THE CONNECTION TO THE DATABASE WITH THE PARAMETERS
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connection is successful"); // print to show the connection with those parameters are passing

        // STATEMENT KEEPS THE CONNECTION BETWEEN PROGRAM AND THE DATA SOURCE(DATABASE) FOR SENDING THE SQL QUERIES
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query); // here is where the query we want is passed in to be executed

        // ResulSetMetaData gives the information about the table.
        // You cannot simply print out the column values. We need to put them into iterations.
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("Number of column query returns = " + resultSetMetaData.getColumnCount()); // 11
        System.out.println("Column_Name the query returns = " + resultSetMetaData.getColumnName(1)); // EMPLOYEE_ID
        System.out.println("Column_Name the query returns = " + resultSetMetaData.getColumnDisplaySize(1)); //  this means that column can hold up to 7 chars

        //String first_name = resultSet.getString("FIRST_NAME");

        System.out.println("\n" + "First Name                  Last Name");
        while (resultSet.next()) {
            String firstName = resultSet.getString("FIRST_NAME"); // FIRST_NAME is the column name
            //System.out.println("First name of the employees: " + firstName);
            String lastName = resultSet.getString("LAST_NAME");
            //System.out.println("Last name of the employees: " + lastName);
            System.out.println(firstName + "                          " + lastName);

            if (firstName.equals("David") && lastName.equals("Austin")) {
                String actualName = firstName;
                Assert.assertEquals(actualName, "David");
                System.out.println("Actual name: " + firstName + "  " + lastName);
                break;
            }
        }



    }
}
