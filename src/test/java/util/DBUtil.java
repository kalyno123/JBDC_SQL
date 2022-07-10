package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private static String url = ConfigReader.getProperty("DBUrl");
    private static String username = ConfigReader.getProperty("DBUsername");
    private static String password = ConfigReader.getProperty("DBPassword");

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Connection createDBConnection(){
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection is successful");
        }catch (SQLException e){
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
        return connection;
    }

    public static void executeQuery(String query){
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<List<Object>> getQueryResultList(String query){
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>(); // container to hold the result(list of list)

        // WE NEED TO FIND NUMBER OF COLUMN TO STOP OUR LOOP AT THE LIMIT
        ResultSetMetaData resultSetMetaData;

        try {
            resultSetMetaData=resultSet.getMetaData(); // this code is returning the table information
            while (resultSet.next()){// this is looping through each row; while table has next row keep looping
                List<Object> row = new ArrayList<>(); // inner container to hold the result(list)
                for (int i=1; i <= resultSetMetaData.getColumnCount(); i++){ // 2 columns; resultSetMetaData.getColumnCount() --> this giving us the number of column in the table
                    row.add(resultSet.getObject(i)); // adding value selected from the query defined in TESTDBUtil to small list
                }
                rowList.add(row); // adding small list into big list
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowList;
    }

    public static Object getCellValue(String query) {
        // if we have only one value from one query we use this method because we don't need
        // list of list.
        return getQueryResultList(query).get(0).get(0);
    }

}
