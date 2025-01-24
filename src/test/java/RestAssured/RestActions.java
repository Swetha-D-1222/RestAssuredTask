package RestAssured;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import requestPojo.Bookingdates;
import requestPojo.ReadRequest;
import responsePojo.BookingResponse;
import responsePojo.ReadResponse;
import responsePojo.ResponseBookingdates;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class RestActions {

    private static final Logger logger = LoggerFactory.getLogger(RestActions.class);

    Response response;
    String requestBody="";
    int statuscode=0;
    static List<Integer> createdBookingId = new ArrayList<>();
    static List<Integer> bookingId = new ArrayList<>();
    static List<Integer> bookingIds =new ArrayList<>();
    static RequestSpecification requestSpecification = null;
    static Map<Integer, ReadRequest> requestMap = new HashMap<>();
    ReadRequest request= new ReadRequest();
    Bookingdates bookingDate = new Bookingdates();
    ReadResponse readResponse;
    BookingResponse bookingResponse;
    ResponseBookingdates dateResponse;
    ObjectMapper obj = new ObjectMapper();


    @Given("The base URI {string}")
    public void theBaseURI(String baseURI) {
        requestSpecification = given().baseUri(baseURI).contentType("application/JSON");
    }

    @When("The data is passed {string},{string},{string},{string},{string},{string},{string}")
    public void theDataIsPassed(String firstname, String lastname, String totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) throws JsonProcessingException
    {

        request.setFirstname(firstname);
        request.setLastname(lastname);
        request.setTotalprice(Integer.parseInt(totalprice));
        request.setDepositpaid(Boolean.parseBoolean(depositpaid));
        bookingDate.setCheckin(checkin);
        bookingDate.setCheckout(checkout);
        request.setBookingdates(bookingDate);
        request.setAdditionalneeds(additionalneeds);
        requestBody = obj.writeValueAsString(request);
        response = requestSpecification.body(requestBody).request(Method.POST,"/booking");
        readResponse=response.getBody().as(ReadResponse.class);
        response.prettyPrint();
        requestMap.put(readResponse.getBookingid(), request);
        createdBookingId.add(readResponse.getBookingid());
    }

    @Then("Validate the status code using the Response message")
    public void validateTheStatusCodeUsingTheResponseMessage()
    {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/MySchema.json"));
        Assert.assertEquals(response.getStatusCode(),200,"Data not created");
    }


    @When("The data is retrived by passing bookingId")
    public void theBookingIdIsPassed()
    {
        System.out.println(createdBookingId.size());
        for (int bookingid : createdBookingId) {
            response = requestSpecification.request(Method.GET, "/booking/" + bookingid);
            System.out.println("Response Body: " + response.getBody().asString());
            statuscode = response.getStatusCode();
            Assert.assertEquals(statuscode, 200, "Invalid status code");
        }
    }

    @Then("Validate the data in response is same as the data sent in request for the bookingid")
    public void validateTheDataInResponseIsSameAsTheDataSentInRequestForTheBookingid()
    {
        for(int id:createdBookingId)
        {
            response=requestSpecification.request(Method.GET,"/booking/"+id);
            bookingResponse = response.getBody().as(BookingResponse.class);
            dateResponse = bookingResponse.getBookingdates();
            ReadRequest originalRequest = requestMap.get(id);
            assertEquals(bookingResponse.getFirstname(),originalRequest.getFirstname(),"Firstname not equal");
            assertEquals(bookingResponse.getLastname(),originalRequest.getLastname(),"Lastname not equal");
            assertEquals(bookingResponse.getTotalprice(),originalRequest.getTotalprice(),"Total price not equal");
            assertEquals(bookingResponse.isDepositpaid(),originalRequest.isDepositpaid(),"Depositpaid not equal");
            assertEquals(dateResponse.getCheckin(),originalRequest.getBookingdates().getCheckin(),"Checkin date not equal");
            assertEquals(dateResponse.getCheckout(),originalRequest.getBookingdates().getCheckout(),"Checkout date not equal");
            assertEquals(bookingResponse.getAdditionalneeds(),originalRequest.getAdditionalneeds(),"Additional needs not equal");
        }
    }



    @When("Get the booking ids")
    public void getTheBookingIds()
    {
        response = requestSpecification.request(Method.GET,"/booking");
        bookingIds = response.jsonPath().getList("bookingid");

    }
    @Then("verify if the creator id is present in the id")
    public void verifyIfTheCreatorIdIsPresentInTheId()
    {
        for (Integer id : createdBookingId)
        {
            Assert.assertTrue(bookingIds.contains(id),"Booking id not present");
        }
    }

    String firstName="";
    String lastName="";
    @When("Get the booking ids using firstName,lastName and checkIn,CheckOut {string},{string},{string},{string}")
    public void getTheBookingIds(String firstname, String lastname, String checkin, String checkout)
    {
        firstName=firstname;
        lastName=lastname;
        if (!(firstname.isEmpty() && lastname.isEmpty()))
        {
            response = requestSpecification
                    .queryParam("firstname",firstname)
                    .queryParam("lastname",lastname )
                    .request(Method.GET,"/booking");
            statuscode=response.getStatusCode();
            System.out.println(statuscode);

            bookingId = response.getBody().jsonPath().getList("bookingid");
            System.out.println(bookingId);
            response.prettyPrint();
        }
        else if(!(checkin.isEmpty() && checkout.isEmpty()))
        {
            Map<String, Object> eventObjects = new HashMap<>();
            eventObjects.put("checkIn",checkin);
            eventObjects.put("checkOut", checkout);

            response = requestSpecification
                    .queryParam("checkin",eventObjects.get("checkIn") )
                    .queryParam("checkout",eventObjects.get("checkOut"))
                    .request(Method.GET,"/booking");
            System.out.println("Response Body: " + response.getBody().asString());
            statuscode=response.getStatusCode();
            bookingId = response.getBody().jsonPath().getList("bookingid");
            System.out.println(bookingId);
        }

    }

    @And("Validate the Response message")
    public void validateTheResponseMessage()
    {
        for(Integer id:bookingId) {
//            Assert.assertTrue(createdBookingId.contains(id), "Ids don,t match!!");
            ReadRequest originalRequest = requestMap.get(id);
            System.out.println("BookingId : "+id+" FirstName : "+originalRequest.getFirstname()+" LastName : "+originalRequest.getLastname());
            Assert.assertEquals(originalRequest.getFirstname(),firstName,"FirstNames not equal");
            Assert.assertEquals(originalRequest.getLastname(),lastName,"LastNames not equal");

        }
    }

    @When("The data is passed {string},{string},{string},{string},{string}")
    public void theDataIsPassed(String firstname, String lastname, String totalprice, String depositpaid, String additionalneeds) throws JsonProcessingException {
        request.setFirstname(firstname);
        request.setLastname(lastname);
        request.setTotalprice(Integer.parseInt(totalprice));
        request.setDepositpaid(Boolean.parseBoolean(depositpaid));
        request.setAdditionalneeds(additionalneeds);
        requestBody = obj.writeValueAsString(request);
        response = requestSpecification.body(requestBody).request(Method.POST,"/booking");
        statuscode=response.getStatusCode();
    }

    @Then("Validate the response for negative case")
    public void validateTheResponseForNegativeCase()
    {
        System.out.println(statuscode);
        Assert.assertEquals(statuscode,500,"values don't match");
    }

}
