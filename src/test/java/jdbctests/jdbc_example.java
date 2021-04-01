package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@34.228.41.120:1521:xe";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void countNavigate() throws SQLException {

        // 1. Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // 2. Create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //3. run query and get the result in resultset object

        ResultSet resultSet = statement.executeQuery("select * from departments");


        //we need move before first row to get full list since we are at the last row right now.
        resultSet.beforeFirst();

        while (resultSet.next()) {

            System.out.println(resultSet.getString(2));


        }


        // close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }


    @Test
    public void test1() throws SQLException {

        // 1. Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // 2. Create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //3. run query and get the result in resultset object

        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to find how many rows we have for the query?
        // 1. Go to last row
        resultSet.last();

        // 2. Get the row count
        int rowCount = resultSet.getRow();
        System.out.println("The rowNumber is " + rowCount);

        //we need move before first row to get full list since we are at the last row right now.
        resultSet.beforeFirst();

        while (resultSet.next()) {

            System.out.println(resultSet.getString(2));


        }

        // we create the different query in the same resultSet object and manipulate
        resultSet = statement.executeQuery("select * from regions");

        while (resultSet.next()) {

            System.out.println(resultSet.getString(2));


        }

        // close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }

    @Test
    public void MetaDataExample() throws SQLException {

        // 1. Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // 2. Create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //3. run query and get the result in resultset object

        ResultSet resultSet = statement.executeQuery("select * from employees");

        //get the database related data inside the dbMetedata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User =" + dbMetadata.getUserName());
        System.out.println("Database Product Name" + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version" + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver name" + dbMetadata.getDriverName());
        System.out.println("Driver Version" + dbMetadata.getDriverVersion());

        //get the resultSet object metaData
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have?
        int columnData = rsMetadata.getColumnCount();
        System.out.println("columnData = " + columnData);

           /* We have 11 column according to above sout, we determinate the index number until 11,
        and to get cloumnNames from the employees table*/

//        System.out.println(rsMetadata.getColumnName(1));
//        System.out.println(rsMetadata.getColumnName(2));


        //print all the column names dynamically
        for (int i = 1; i <= columnData; i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }


        // close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

}


