package pet_store_smoke_test;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class S4Delete extends PetStoreBaseUrl {
    /*
    Given
         https://petstore.swagger.io/v2/pet/4444
    When
         user send delete request
    Then
         http status code is  200
    And
        response body is like {
                                "code": 200,
                                "type": "unknown",
                                "message": "4444"
                              }
     */
    @Test
    public void delete01(){
       spec.pathParams("first","pet","second",4444) ;

       Response response = given().spec(spec).when().delete("/{first}/{second}");
       response.prettyPrint();

       assertEquals(200,response.statusCode());

    }
}
