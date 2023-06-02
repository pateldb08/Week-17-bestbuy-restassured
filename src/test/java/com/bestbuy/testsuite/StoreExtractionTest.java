package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoreExtractionTest {
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

    // extract the value of limit
    @Test
    public void test01() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //extract the total
    public void test02() {
        int total = response.extract().path("total");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("-----------------End of Test-----------------------------");
    }

    @Test
    //Extract the name of 5th store
    public void test03() {
        String name = response.extract().path("data[4].name");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The store name : " + name);
        System.out.println("-----------------End of Test-----------------------------");
    }

    @Test
    //Extract the names of all the store
    public void test04() {
        List<String> storenames = response.extract().path("data.name");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("All Stores names are : " + storenames);
        System.out.println("-----------------End of Test-----------------------------");
    }

    @Test
    //Extract the storeId of all the store
    public void test05() {
        List<Integer> storeId = response.extract().path("data.id");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("All storeId are : " + storeId);
        System.out.println("-----------------End of Test-----------------------------");
    }

    @Test
    //Print the size of the data list
    public void test06() {
        List<Object> sizeOfData = response.extract().path("data");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The size of data is : " + sizeOfData.size());
        System.out.println("-----------------End of Test-----------------------------");
    }

    @Test
    //Get all the value of the store where store name = St Cloud
    public void test07() {
        // List<HashMap<String, ?>> valueOfStore = response.extract().path("data[6]");
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The size of data is : " + values);
        System.out.println("-----------------End of Test-----------------------------");
    }

    @Test
    //Get the address of the store where store name = Rochester
    public void test08() {
        List<String> address = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        //  String address = response.extract().path("data[8].address");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The value of St Cloud : " + address);
        System.out.println("-----------------End of Test-----------------------------");
    }

    @Test
    //Get all the services of 8th store
    public void test09() {
        // List<HashMap<String,?>> services = response.extract().path("data[7].services");
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.id == 8 }.services");

        System.out.println("----------------StartingTest---------------------------");
        System.out.println("Get all the services of 8th store : " + services);
        System.out.println("-----------------End of Test-----------------------------");
    }

    @Test
    //Get storeservices of the store where service name = Windows Store
    public void test10() {
      List<String> stroreServices = response.extract().path("data.services.findAll{it.name == 'Windows Store'}.services");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The Store services  : " + stroreServices);
        System.out.println("-----------------End of Test-----------------------------");
    }
    @Test
    //Get all the storeId of all the store
    public void test11(){
        List<Integer> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("All StoreId are  : " + storeId);
        System.out.println("-----------------End of Test-----------------------------");
    }
    @Test
    //Get id of all the store
    public void test12(){
        List<Integer> id = response.extract().path("data.id");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("All StoreId are  : " + id);
        System.out.println("-----------------End of Test-----------------------------");
    }
    @Test
    //Find the store names Where state = ND
    public void test13(){
        List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("All Store names of state ND are  : " + storeName);
        System.out.println("-----------------End of Test-----------------------------");
    }
    @Test
    //Find the Total number of services for the store where store name = Rochester
    public void test14(){
        List<Integer> noOfServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("Total number of services for Rochester are  : " + noOfServices.size());
        System.out.println("-----------------End of Test-----------------------------");
    }
    @Test
    //Find the createdAt for all services whose name = “Windows Store”
    public void test15(){
        //List<?> createdAt = response.extract().path("data.findAll{it.services == 'Windows Store'}.storeservices.createdAt");
        List<?> createdAt = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The createdAt for all services of Windows Store : " + createdAt);
        System.out.println("-----------------End of Test-----------------------------");
    }
    @Test
    //16.Find the name of all services Where store name = “Fargo”
    public void test16(){
        List<HashMap<String,?>> services = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The name of all services : " + services);
        System.out.println("-----------------End of Test-----------------------------");
    }
    @Test
    //17. Find the zip of all the store
    public void test17(){
        List<Integer> Allzip = response.extract().path("data.zip");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The zip of all services : " + Allzip);
        System.out.println("-----------------End of Test-----------------------------");
    }
    @Test
   // 18. Find the zip of store name = Roseville
    public void test18(){
        List<Integer> zip = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The zip of all services : " + zip);
        System.out.println("-----------------End of Test-----------------------------");
    }
    @Test
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    public void test19() {
        List<HashMap<String, ?>> serviceDetail = response.extract().path("data[2].services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("----------------StartingTest---------------------------");
        System.out.println("The serviceDetail : " + serviceDetail);
        System.out.println("-----------------End of Test-----------------------------");
    }
        //20. Find the lat of all the stores
        @Test
    public void test20(){
            List<Double> latStore = response.extract().path("data.lat");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The lat of All Store : " + latStore);
            System.out.println("------------------End of Test---------------------------");
        }


    }







