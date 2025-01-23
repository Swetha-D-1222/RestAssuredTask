Feature: Fill the form

  @Regression
  Scenario Outline: Fill all the required fields and submit the form
    Given open the form
    When Enter the all the required values "<firstname>","<lastname>","<gender>","<mobile>" and click submit
    Then verify the values "<firstname>","<lastname>","<gender>","<mobile>" filled are correct
    Examples:
      | firstname | lastname | gender | mobile     |
      | Swetha    | D        | Female | 9876543210 |


  @Negative
  Scenario Outline: Fill the form with invalid email format and text value in mobile number and submit
    Given open the form
    When Enter the field values "<firstname>","<lastname>","<email>","<gender>","<mobile>"
    Then validate the form with these values "<firstname>","<lastname>","<email>","<gender>","<mobile>"
    Examples:
      | firstname | lastname | email | gender | mobile |
      | swetha    | D        | swethad2020#gmail| Female | helloAll |

  @Positive
  Scenario Outline: Fill all the fields and submit the form
  Given open the form
  When Enter the values "<firstname>","<lastname>","<email>","<gender>","<mobile>","<month>","<year>","<date>","<hobby1>","<address>" and submit
  Then Validate the submission "<firstname>","<lastname>","<email>","<gender>","<mobile>","<month>","<year>","<date>","<hobby1>","<address>"
    Examples:
      | firstname | lastname | email | gender | mobile | month | year | date | hobby1 | address |
      |  Swetha   | D        |swethad2021@gmail.com| Female | 9876543210 | May| 2004 | 22 | Music |103, Kothari Nagar, Singanallur, Coimbatore|


  @Negative
  Scenario Outline: Fill the form by leaving an one of the required fields
  Given open the form
  When Enter the values "<firstname>","<lastname>","<gender>","<mobile>" and submit
  Then validate the form when any of the required field is not filled "<firstname>","<lastname>","<gender>","<mobile>"
    Examples:
      | firstname | lastname | gender | mobile |
      |  Abcdef   |  gh      |        |  9876550432|

#  @Negative
#  Scenario Outline:
#    Given open the form
#    When Enter the required field values and submit
#    Then Validate the required fields
#    Examples:



