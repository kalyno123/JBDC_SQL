package db.database;

import util.DBUtil;

import java.util.List;

public class TestDBUtil {
    public static void main(String[] args) {
        DBUtil.createDBConnection();
        //DBUtil.executeQuery("select * from countries");
        List<List<Object>> result= DBUtil.getQueryResultList("select first_name, last_name from employees"); // this is how the method getQueryResultList() know what columns to return
        System.out.println(result);
    }
}