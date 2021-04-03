package apitest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SpartanTestWithParameters {

    @BeforeClass
    public void beforeclass(){
        baseURI="http://34.228.41.120:8000";
    }
    /*
          Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json;charset=UTF-8
          And "Blythe" should be in response payload
       */
    @Test
    public void getSpartanID_Positive_PathParam(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",539)
                .when().get("/api/spartans/{id}");

       assertEquals(response.statusCode(),200);

       assertEquals(response.contentType(),"application/json");

       assertTrue(response.body().asString().contains("Male"));
    }

     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json;charset=UTF-8
        And "Spartan Not Found" message should be in response payload
     */

    @Test
    public void getSpartanID_Negative_PathParam(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),404);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Spartan Not Found"));

    }

     /*
        Given accept type is Json
        And query parameter values are :
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json;charset=UTF-8 but (my postman does not give this content!!! due to version)
        mine gives ==>>content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @Test
    public void positiveTestWithQueryParam(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        //verify status code
        assertEquals(response.statusCode(),200);
        //verify content-type
        assertEquals(response.contentType(),"application/json");
        //verify Female in the response
        assertTrue(response.body().asString().contains("Female"));
        //verify Janette in the response
        assertTrue(response.body().asString().contains("Janette"));

    }

    @Test
    public void positiveTestWithQueryParamWithMaps(){

        //***When we key/value structure to remember <<MAP>>!!!!

        //create a map and add query parameters
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");

        //response verification
        //verify status code
        assertEquals(response.statusCode(),200);
        //verify content-type
        assertEquals(response.contentType(),"application/json");
        //verify Female in the response
        assertTrue(response.body().asString().contains("Female"));
        //verify Janette in the response
        assertTrue(response.body().asString().contains("Janette"));

        /*The different is between above the two coding/ last two different test case ; First We pass queryParam key&value
        structure queryParam method, second;we use queryParams and we put key&value in one Map and passed the (queryMap) in
        queryParams method to structure
        look below ;

            Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");
        */





    }





}
