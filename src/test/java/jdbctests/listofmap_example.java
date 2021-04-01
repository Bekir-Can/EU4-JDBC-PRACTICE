package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listofmap_example {

    String dbUrl = "jdbc:oracle:thin:@34.228.41.120:1521:xe";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void MetaDataExample() throws SQLException {

        // 1. Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // 2. Create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //3. run query and get the result in resultset object

        ResultSet resultSet = statement.executeQuery("select * from regions");


        //get the resultSet object metaData
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //to create list for keeping all rows a map
        List<Map<String,Object>> queryData =  new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();
        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRES");

        System.out.println(row1.toString());

        Map<String,Object> row2 = new HashMap<>();
        row2.put("first_name","Neena");
        row2.put("last_name","Koachhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        System.out.println(row2.toString());

        //we want to see salary of Neena for this to use get(columnName)
        System.out.println(row2.get("salary"));


        /*adding rows to my list as use  add().row1 etc. => List<Map<String,Object>> queryData =  new ArrayList<>();
        so we put our two separate maps (row1, row2) empty list of maps to use add(); which we prepared above */
        queryData.add(row1);
        queryData.add(row2);

        //get the Steven last name directly from the list to use index number (index starts from Zero(0) dont forget again)
        queryData.get(0).get("last_name");
        System.out.println(queryData.get(0).get("last_name"));    //index 0 represents row1

        queryData.get(1).get("last_name");
        System.out.println(queryData.get(1).get("last_name"));   //index 1 represents row2



        // close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void MetaDataExample2() throws SQLException {

        // 1. Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // 2. Create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //3. run query and get the result in resultset object

        ResultSet resultSet = statement.executeQuery("select first_name, last_name, salary, job_id from employees\n" +
                " where rownum<6");

        //get the resultSet object metaData
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //to create list for keeping all rows a map
        List<Map<String,Object>> queryData =  new ArrayList<>();

        // move to first row row1
        resultSet.next();

        Map<String,Object> row1 = new HashMap<>();
        row1.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row1.toString());

        //to move second row2
        resultSet.next();

        Map<String,Object> row2 = new HashMap<>();
        row2.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row2.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row2.toString());

        //we want to see salary of Neena for this to use get(columnName)
        System.out.println(row2.get("SALARY"));


        /*adding rows to my list as use  add().row1 etc. => List<Map<String,Object>> queryData =  new ArrayList<>();
        so we put our two separate maps (row1, row2) empty list of maps to use add(); which we prepared above */
        queryData.add(row1);
        queryData.add(row2);

        //get the Steven last name directly from the list to use index number (index starts from Zero(0) dont forget again)
        queryData.get(0).get("LAST_NAME");
        System.out.println(queryData.get(0).get("LAST_NAME"));    //index 0 represents row1

        queryData.get(1).get("LAST_NAME");
        System.out.println(queryData.get(1).get("LAST_NAME"));   //index 1 represents row2




        // close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

}
