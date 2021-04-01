package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@34.228.41.120:1521:xe";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void MetaDataExample2() throws SQLException {

        // 1. Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // 2. Create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //3. run query and get the result in resultset object

        /*ResultSet resultSet = statement.executeQuery("select first_name, last_name, salary, job_id from employees\n" +
                " where rownum<6");*/

        //ResultSet resultSet = statement.executeQuery("select * from departments");

        ResultSet resultSet = statement.executeQuery("select * from countries");

        //get the resultSet object metaData
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //to create list for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        //in order to use dynamic columns
        //number of columns

        // 1. We need to know number of columns (how many columns we have for iterating)
        int colCount = rsMetadata.getColumnCount();

        //we use while loop through each row to know number of rows
        while (resultSet.next()) {  //while loop executing horizontal in table (looking for row count)

            /*in order to implement to same thing one by one for the each row
            we need one empty Map inside the while loop to add our map to list(to keep row info)*/


            Map<String, Object> row = new HashMap<>();

            for (int i = 1; i < colCount; i++) { //for loop executing vertical in table (looking for column count)

                //some code here put some information
                row.put(rsMetadata.getColumnName(i), resultSet.getObject(i));

            }

            //add your map to your list
            queryData.add(row);

        }

        //print the result (outside the our while loop)
        for (Map<String, Object> row : queryData) {

            System.out.println(row.toString());
            /*we get the all results by dynamically
            to change the queries in =>>ResultSet resultSet = statement.executeQuery("select * from countries");*/
        }

        // close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

}
