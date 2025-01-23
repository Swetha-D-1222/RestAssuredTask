Feature: Create a booking information and store the booking id

  @CreationTest
  Scenario Outline: Create booking information using the following data
    Given The base URI "https://restful-booker.herokuapp.com/"
    When The data is passed "<firstname>","<lastname>","<totalprice>","<deposit>","<checkin>","<checkout>","<additionalneeds>"
    Then Validate the status code using the Response message
    Examples:
      | firstname | lastname | totalprice | deposit | checkin    | checkout   | additionalneeds |
      | Evangalin | Marry    | 350        | true    | 2023-10-10 | 2023-10-15 | Breakfast       |
      | Akshay    | Kumar    | 460        | false   | 2024-02-22 | 2024-03-03 | Lunch           |
      | Sruthi    | Shri     | 760        | true    | 2022-05-03 | 2022-05-07 | Breakfast       |


  @GetResultByPassingId
  Scenario: Pass the Booking Id , fetch and verify the Booking information
    Given The base URI "https://restful-booker.herokuapp.com/"
    When The data is retrived by passing bookingId
    And Validate the data in response is same as the data sent in request for the bookingid


  @CheckCreatedIdIsPresent
  Scenario: Check whether the created records booking ids are present in the list of booking ids
    Given The base URI "https://restful-booker.herokuapp.com/"
    When Get the booking ids
    Then verify if the creator id is present in the id


  @GetResult
  Scenario Outline: Call GetBookingIds by using FirstName/LastName and Filter by checkin/checkout date
    Given The base URI "https://restful-booker.herokuapp.com/"
    When Get the booking ids using firstName,lastName and checkIn,CheckOut "<firstname>","<lastname>","<checkIn>","<checkOut>"
    Then verify if the creator id is present in the id
    Examples:
      | firstname | lastname | checkIn    | checkOut   |
      | Sruthi    | Shri     |            |            |
      |           |          | 2024-02-22 | 2024-03-03 |

  @NegativeTest
  Scenario Outline: Create booking information with null values and validate the response
  Given The base URI "https://restful-booker.herokuapp.com/"
  When The data is passed "<firstname>","<lastname>","<totalprice>","<deposit>","<additionalneeds>"
  Then Validate the response for negative case
  Examples:
    | firstname | lastname | totalprice | deposit | additionalneeds |
    | Evangalin | Marry    | 350        | true    | Breakfast       |




