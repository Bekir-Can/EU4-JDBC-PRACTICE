package apitest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class simpleGetTest {

    String hrUrl = "http://34.228.41.120:1000/ords/hr/regions";


    @Test
    public void test1(){

        Response response = RestAssured.get(hrUrl);

    }


}
