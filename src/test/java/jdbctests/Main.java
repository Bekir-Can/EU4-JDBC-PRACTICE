package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        //Set up the JDBC in IntelliJ
        // String dbUrl = "jdbc:oracle:thin:@yourIPaddressandport:xe";

        String dbUrl = "jdbc:oracle:thin:@34.228.41.120:1521:xe";
        String dbUserName = "hr";
        String dbPassword = "hr";

        // 1. Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // 2. Create statement object
        Statement statement = connection.createStatement();

        //3. run query and get the result in resultset object
      //  ResultSet resultSet = statement.executeQuery("Select * from regions");
      //  ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary from employees");

        //String query = "select * from departments";
        //ResultSet resultSet = statement.executeQuery(query);

        ResultSet resultSet = statement.executeQuery("select * from departments");

//        //First=> we have to move point to first row
//        resultSet.next();
//
//        // we have two different way to reach the column info(column name and index number)
//        //getting information with column name
//        System.out.println(resultSet.getString("region_name"));
//
//        //getting information with column index (starts from 1)
//        System.out.println(resultSet.getString(2));
//
//
//        System.out.println(resultSet.getString(1)+ "-" + resultSet.getString("region_name"));
//
//
//        // move pointer to second row
//        // if we want to print out second value, for this we use next() again
//        resultSet.next(); // pointing second row
//
//        //getting information with column name
//        System.out.println(resultSet.getString("region_name"));
//
//        //getting information with column index (starts from 1)
//        System.out.println(resultSet.getString(2));
//
//
//
//        //the other options
//       // System.out.println(resultSet.getString("region_id")+ "-" + resultSet.getString("region_name"));
//        //the other options
//       // System.out.println(resultSet.getString(1)+ "-" + resultSet.getString(1));
//        resultSet.next();
//
//        System.out.println(resultSet.getString(1)+ "-" + resultSet.getString("region_name"));
//        resultSet.next();

//    while (resultSet.next()){
//        System.out.println(resultSet.getString(1)+ "-" + resultSet.getString("region_name"));
//
//    }

        while (resultSet.next()){
            //the below syntax for employees
//            System.out.println(resultSet.getString("fisrt_name")+" -" +resultSet.getString("last_name")
//                    + resultSet.getString("salary"));
            //the below syntax for employees
        //    System.out.println(resultSet.getString(1)+"-"+resultSet.getString(2)+"-"+resultSet.getString(3));

        //the below syntax for departments
            System.out.println(resultSet.getString(1)+"-"+resultSet.getString(2)
                    +"-"+resultSet.getString(3)+"-"+resultSet.getString(4));
        }

        resultSet.previous();


        // close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
