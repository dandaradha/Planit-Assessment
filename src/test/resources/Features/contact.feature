@contact @feature
Feature: Log Feedback

  @feedback @scenario
  Scenario Outline:  Log Feedback

    Given I navigate from home page to contact page
    When I enter Mandatory Values as "<ForeName>" and "<Email>" and "<Message>" and "<Surname>" and "<Telephone>"
    And Click submit button
    Then I validate successful submission message "<expectedMessage>"

    @data
    Examples:
      | ForeName | Email         | Message    | Surname   | Telephone    | expectedMessage                          |
      | abc      | abc@email.com | It is good |           |              | Thanks abc, we appreciate your feedback. |
      | bcd      | bcd@email.com | It is good | b         | 02 1234 5678 | Thanks bcd, we appreciate your feedback. |
      | cde      | cde@email.com | It is good | c         |              | Thanks cde, we appreciate your feedback. |
      | def      | def@email.com | It is good |           | 02 1234 6789 | Thanks def, we appreciate your feedback. |
      | efg      | efg@email.com | It is good |           |              | Thanks efg, we appreciate your feedback. |