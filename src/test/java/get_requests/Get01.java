package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Get01 {
    /*
  Given
      https://petstore.swagger.io/v2/pet/12
  When
      User sends a GET Request to the url
  Then
      HTTP Status Code should be 200
  And
      Content Type should be JSON
  And
      Status Line should be HTTP/1.1 200 OK
*/
    public static void main(String[] args) {
        //set the url
        String url ="https://petstore.swagger.io/v2/pet/12";

        //set the expected data

        //send the request and get the response
        Response response =given().when().get(url);
        response.prettyPrint();

        //Do assertion
        if(response.statusCode()==200){
            System.out.println("Test Passed:Status Code is 200");
        }else{
            System.out.println("Test Failed:Status Code "+response.statusCode());
        }
        if(response.contentType().equals("application/json")){
            System.out.println("Test Passed:content Type is application/json");
        }else{
            System.out.println("Test Failed: Content Type is"+response.contentType());
        }
        if(response.statusLine().equals("HTTP/1.1 200 OK")){
            System.out.println("Test Passed: statusLine is HTTP/1.1 200 OK");
        }else{
            System.out.println("Test Failed: statusLine is"+response.statusLine());
        }

    }
}
