package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {


    @Test
    public void test1(){

        // to create connection (from utilities package => DBUtils)
        DBUtils.createConnection();

        // to get below the syntax => to write => DBUtils.getQueryResultMap("select * from departmants");+alt+enter
        //List<Map<String, Object>> queryResult = DBUtils.getQueryResultMap("select * from departments");
        // using method to get result as a list of maps
        List<Map<String, Object>> queryResult = DBUtils.getQueryResultMap("select * from employees");

        //printing the result
        for (Map<String, Object> map : queryResult) {
            System.out.println(map.toString());
            
        }

        //to close connection (from utilities package => DBUtils)
        DBUtils.destroy();


    }
    @Test
    public void test2(){

        // to create connection (from utilities package => DBUtils)
        DBUtils.createConnection();
        // if we get one row to use => getRowMap(write query inside)
        Map<String, Object> rowMap = DBUtils.getRowMap("select first_name, last_name,salary, job_id from employees\n" +
                "where employee_id = 100");
        System.out.println(rowMap.toString());

        //to close connection (from utilities package => DBUtils)
        DBUtils.destroy();


    }
}
