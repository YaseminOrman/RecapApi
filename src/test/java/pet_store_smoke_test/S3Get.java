package pet_store_smoke_test;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojos.Category;
import pojos.PetStorePet;
import pojos.Tags;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class S3Get extends PetStoreBaseUrl {
    /*
    Given
         https://petstore.swagger.io/v2/pet/4444
    When
         User send Get Request to the url
    Then
         Http status code is 200
     And
         Response body be like {
                                "id": 4444,
                                "category": {
                                    "id": 0,
                                    "name": "Cat"
                                },
                                "name": "Cotton",
                                "photoUrls": [
                                    "string"
                                ],
                                "tags": [
                                    {
                                        "id": 0,
                                        "name": "My lovely cat"
                                    }
                                ],
                                "status": "available"
                            }
     */
    @Test
    public void get01() throws IOException {
        spec.pathParams("first","pet","second",4444);

        //set the expected data
        Category category = new Category(0,"Cat");
        Tags tags = new Tags(0,"My lovely cat");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("string");
        ArrayList<Tags> arrayListTags = new ArrayList<>();
        arrayListTags.add(tags);
        PetStorePet expectedData = new PetStorePet(4444,category,"Cotton",arrayList,arrayListTags,"available");
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        PetStorePet actualData= new ObjectMapper().readValue(response.asString(),PetStorePet.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getId(),actualData.getId());
        assertEquals(expectedData.getCategory().getId(),actualData.getCategory().getId());
        assertEquals(expectedData.getCategory().getName(),actualData.getCategory().getName());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getPhotoUrls(),actualData.getPhotoUrls());
        assertEquals(tags.getId(),tags.getId());
        assertEquals(tags.getName(),tags.getName());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
    }
}
