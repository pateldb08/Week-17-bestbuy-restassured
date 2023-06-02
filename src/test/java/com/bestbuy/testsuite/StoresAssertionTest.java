package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class StoresAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //verify that the total is equal to 1561
    @Test
    public void verifyTheTotal() {
        response.body("total", equalTo(1561));
    }

    //verify that the stores of limit is equal to 10
    @Test
    public void verifyTheLimit() {
        response.body("limit", equalTo(10));
    }

    @Test
    public void verifyTheName() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    @Test
    public void verifyMulipleNames() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));
    }

    @Test
    public void verifystoreId() {
        response.body("data[2].services[1].storeservices.storeId", equalTo(7));
    }

    @Test
    public void verifyHashMapValuescreatedAt() {
//        response
//                .body("data.name", hasItem("Roseville"))
//                .body("data[0].services[0].storeservices", hasKey("createdAt"));
        response.body("data[2].services[0].storeservices.createdAt",equalTo("2016-11-17T17:57:09.417Z"));
    }
    @Test
    public void verifyStateOfForthStore(){
        response.body("data[3].state",equalTo("MN"));
    }
    @Test
    public void storeNameOfNinethStore(){
        response.body("data[8].name",equalTo("Rochester"));
    }
    @Test
    public void verifyStoreIdForSixthStore(){
        response.body("data[5].id", equalTo("11"));
    }
    @Test
    public void verifyserviceIdOfSeventhStoreOfFourthService(){
        response.body("data[6].services[3].storeservices.serviceId", equalTo("4"));
    }
}


