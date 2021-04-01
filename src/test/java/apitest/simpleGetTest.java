package apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class simpleGetTest {

    String hrUrl = "http://34.228.41.120:1000/ords/hr/regions";


    @Test
    public void test1(){

        Response response = RestAssured.get(hrUrl);

        //response.statusCode(); => to verify response code
        System.out.println("response.statusCode() = " + response.statusCode());

        //response.prettyPrint(); => to print the json body
        System.out.println("response.prettyPrint() = " + response.prettyPrint());


    }
    /*
     Given accept type is json
     When user sends get request to regions endpoint
     Then response status code must be 200
     and body is json format
    */

    //Now how to automate the above the sample!!! Follw the below..
    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON).when().get(hrUrl);

        // to verify response status code is 200
        Assert.assertEquals(response.statusCode(),200);

        System.out.println("response.contentType() = " + response.contentType());

        //to verify content type is json
        Assert.assertEquals(response.contentType(),"application/json");




    }




}
