package apitest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class SpartanTestWithJsonPath {
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
     /*
          Given accept type is json
          And path param spartan id is 11
          When user sends a get request to /spartans/{id}
         Then status code is 200
         And content type is Json
         And   "id": 11,
               "name": "Berry K",
              "gender": "Female",
              "phone": 2242301437
    */

    @Test
    public void test1(){

       Response response =  given().accept(ContentType.JSON)
                            .and().pathParam("id",11)
                            .when().get("/api/spartans/{id}");

       assertEquals(response.statusCode(),200);
       assertEquals(response.contentType(),"application/json");

       //verify id and name with path()
        int id = response.path("id");
        String name = response.path("name");

        assertEquals(id,11);
        assertEquals(name,"Berry K");

        //assign response to jsonpath(we put all inform inside the json path object)
        //if we don't want to use path method, we have jsonPath method look below; (Both are same)
        JsonPath jsonPath = response.jsonPath();

        int idJson = jsonPath.getInt("id");
        String nameJson = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");


        //print the values
        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //verification
        assertEquals(idJson,11);
        assertEquals(nameJson,"Berry K");
        assertEquals(gender,"Female");
        assertEquals(phone,2242301437l);



    }



}
